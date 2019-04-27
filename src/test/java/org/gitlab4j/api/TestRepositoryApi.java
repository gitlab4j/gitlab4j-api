package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryApi extends AbstractIntegrationTest {

    private static final String TEST_BRANCH_NAME = "feature/test_branch";
    private static final String TEST_BRANCH1 = "feature/test_branch1";
    private static final String TEST_BRANCH2 = "feature/test_branch2";
    private static final String TEST_PROTECT_BRANCH_NAME = "feature/protect_branch";
    private static GitLabApi gitLabApi;

    public TestRepositoryApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        teardown();
    }

    @AfterClass
    public static void teardown() {
        if (gitLabApi != null) {

            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);

                try {
                    gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
                } catch (GitLabApiException ignore) {
                }

                try {
                    gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH1);
                } catch (GitLabApiException ignore) {
                }

                try {
                    gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH2);
                } catch (GitLabApiException ignore) {
                }

                gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);

            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
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

        File file = gitLabApi.getRepositoryApi().getRepositoryArchive(project.getId(), "master", (File)null);
        assertTrue(file.length() > 0);
        file.delete();

        file = gitLabApi.getRepositoryApi().getRepositoryArchive(project.getId(), "master", new File("."));
        assertTrue(file.length() > 0);
        file.delete();
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

    @Test
    public void testProtectBranch() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Branch branch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_PROTECT_BRANCH_NAME, "master");
        assertNotNull(branch);

        Branch protectedBranch = gitLabApi.getRepositoryApi().protectBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);
        assertNotNull(protectedBranch);
        assertTrue(protectedBranch.getProtected());

        Branch unprotectedBranch = gitLabApi.getRepositoryApi().unprotectBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);
        assertNotNull(unprotectedBranch);
        assertFalse(unprotectedBranch.getProtected());
    }

    @Test
    public void testMergeBase() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Branch branch1 = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH1, "master");
        assertNotNull(branch1);
        Branch branch2 = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH2, "master");
        assertNotNull(branch2);

        List<String> refs = Arrays.asList(TEST_BRANCH1, TEST_BRANCH2);
        Commit mergeBase = gitLabApi.getRepositoryApi().getMergeBase(project, refs);
        assertNotNull(mergeBase);
    }
}
