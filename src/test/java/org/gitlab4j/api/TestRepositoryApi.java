package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that testCreate() is executed first.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryApi {

    // The following needs to be set to your test repository
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static final String TEST_BRANCH_NAME = "feature/test_branch";
    private static GitLabApi gitLabApi;

    public TestRepositoryApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().length() == 0) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().length() == 0) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().length() == 0) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().length() == 0) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        if (gitLabApi != null) {

            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
                gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testCreateBranch() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Branch branch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        Branch fetchedBranch = gitLabApi.getRepositoryApi().getBranch(project.getId(), TEST_BRANCH_NAME);
        assertNotNull(fetchedBranch);

        assertEquals(branch.getName(), fetchedBranch.getName());
    }

    @Test
    public void testDeleteBranch() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
    }

    @Test
    public void testRepositoryArchiveViaInputStream() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        InputStream in = gitLabApi.getRepositoryApi().getRepositoryArchive(project.getId(), "master");

        Path target = Files.createTempFile(TEST_PROJECT_NAME + "-", ".tar.gz");
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);

        assertTrue(target.toFile().length() > 0);
        Files.delete(target);
    }

    @Test
    public void testRepositoryArchiveViaFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File file = gitLabApi.getRepositoryApi().getRepositoryArchive(project.getId(), "master", null);
        assertTrue(file.length() > 0);
        file.delete();

        file = gitLabApi.getRepositoryApi().getRepositoryArchive(project.getId(), "master", new File("."));
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    public void testRawFileViaFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File file = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README", null);
        assertTrue(file.length() > 0);
        file.delete();

        file = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README", new File("."));
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    public void testRepositoryFileViaInputStream() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        InputStream in = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README");

        Path target = Files.createTempFile(TEST_PROJECT_NAME + "-README", "");
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);

        assertTrue(target.toFile().length() > 0);
        Files.delete(target);
    }

    @Test
    public void testCompare() throws GitLabApiException {
        
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(project.getId());
        assertNotNull(commits);
        assertTrue(commits.size() > 1);
        
        int numCommits = commits.size();
        CompareResults compareResults = gitLabApi.getRepositoryApi().compare(project.getId(), commits.get(numCommits - 1).getId(), commits.get(numCommits - 2).getId());
        assertNotNull(compareResults);

        compareResults = gitLabApi.getRepositoryApi().compare(TEST_NAMESPACE + "/" + TEST_PROJECT_NAME, commits.get(numCommits - 1).getId(), commits.get(numCommits - 2).getId());
        assertNotNull(compareResults);
    }
}
