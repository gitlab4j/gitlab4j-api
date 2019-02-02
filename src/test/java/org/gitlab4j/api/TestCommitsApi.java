package org.gitlab4j.api;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitRef;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCommitsApi {

    // The following needs to be set to your test repository
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    private static final String TEST_PROJECT_SUBDIRECTORY_PATH = "src/main/docs/test-project.txt";
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestCommitsApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().isEmpty()) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);

            try {
                testProject = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
            } catch (GitLabApiException gle) {
                System.err.print(gle.getMessage());
            }

        } else {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testDiff() throws GitLabApiException {
        
        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId());
        assertNotNull(commits);
        assertTrue(commits.size() > 0);
        
        List<Diff> diffs = gitLabApi.getCommitsApi().getDiff(testProject.getId(), commits.get(0).getId());
        assertNotNull(diffs);
        assertTrue(diffs.size() > 0);
 
        diffs = gitLabApi.getCommitsApi().getDiff(TEST_NAMESPACE + "/" + TEST_PROJECT_NAME, commits.get(0).getId());
        assertNotNull(diffs);
        assertTrue(diffs.size() > 0);
    }

    @Test
    public void testComments() throws GitLabApiException {

        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(0), new Date());
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        String note = "This is a note.";
        Comment addedComment = gitLabApi.getCommitsApi().addComment(testProject.getId(), commits.get(0).getId(), note);
        assertNotNull(addedComment);
        assertEquals(note, addedComment.getNote());

        List<Comment> comments = gitLabApi.getCommitsApi().getComments(testProject.getId(), commits.get(0).getId());
        assertNotNull(comments);
        assertTrue(comments.size() > 0);
    }

    @Test
    public void testCommitsSince() throws GitLabApiException {

        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(), null);
        assertNotNull(commits);
        assertTrue(commits.isEmpty());

        commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(0), new Date());
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(0), new Date(), 1, 10);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        Pager<Commit> pager = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(), null, 10);
        assertNotNull(pager);
        assertTrue(pager.getTotalItems() == 0);

        pager = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(0), null, 10);
        assertNotNull(pager);
        assertTrue(pager.getTotalItems() > 0);
    }

    @Test
    public void testCommitRefs() throws GitLabApiException {
        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId());
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        List<CommitRef> commitRefs = gitLabApi.getCommitsApi().getCommitRefs(testProject.getId(), commits.get(0).getId());
        assertNotNull(commitRefs);
        assertTrue(commitRefs.size() > 0);
    }

    @Test
    public void testCommitsSinceWithPath() throws GitLabApiException {

        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(
                testProject.getId(), null, new Date(0), new Date(), TEST_PROJECT_SUBDIRECTORY_PATH);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);
    }

    @Test
    public void testCommitsByPath() throws GitLabApiException {

        assertNotNull(testProject);

        CommitsApi commitsApi = gitLabApi.getCommitsApi();
        List<Commit> commits = commitsApi.getCommits(testProject.getId(), "master", null);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commits = commitsApi.getCommits(testProject.getId(), "master", "README");
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commitsApi = gitLabApi.getCommitsApi();
        commits = commitsApi.getCommits(testProject.getId(), "master", "README.md");
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commits = commitsApi.getCommits(testProject.getId(), "master", TEST_PROJECT_SUBDIRECTORY_PATH);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);
    }

    @Test
    public void testCommitsByPathNotFound() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);

        try {
            List<Commit> commits = gitLabApi.getCommitsApi().getCommits(project.getId(), "master", "this-file-does-not-exist.an-extension");
            assertTrue(commits == null || commits.isEmpty());
        } catch (GitLabApiException gle) {
            assertEquals(Response.Status.NOT_FOUND, gle.getHttpStatus());
        }
    }
}