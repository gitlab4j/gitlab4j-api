package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitRef;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.utils.ISO8601;
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
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCommitsApi extends AbstractIntegrationTest {

    private static final String TEST_PROJECT_SUBDIRECTORY_PATH = "src/main/docs/test-project.txt";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestCommitsApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
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
    public void testComments() throws GitLabApiException, ParseException {

        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, ISO8601.toDate("2000-01-01"), new Date());
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
    public void testCommitsSince() throws GitLabApiException, ParseException {

        assertNotNull(testProject);
        Date since = ISO8601.toDate("2000-01-01");

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(), null);
        assertNotNull(commits);
        assertTrue(commits.isEmpty());

        commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, since, new Date());
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commits = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, since, new Date(), 1, 10);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        Pager<Commit> pager = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, new Date(), null, 10);
        assertNotNull(pager);
        assertTrue(pager.getTotalItems() == 0);

        pager = gitLabApi.getCommitsApi().getCommits(testProject.getId(), null, since, null, 10);
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
    public void testCommitsSinceWithPath() throws GitLabApiException, ParseException {

        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(
                testProject.getId(), null, ISO8601.toDate("2000-01-01"), new Date(), TEST_PROJECT_SUBDIRECTORY_PATH);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);
    }

    @Test
    public void testCommitsByPath() throws GitLabApiException {

        assertNotNull(testProject);

        CommitsApi commitsApi = gitLabApi.getCommitsApi();
        List<Commit> commits = commitsApi.getCommits(testProject, "master", null);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commits = commitsApi.getCommits(testProject, "master", "README");
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commitsApi = gitLabApi.getCommitsApi();
        commits = commitsApi.getCommits(testProject, "master", "README.md");
        assertNotNull(commits);
        assertTrue(commits.size() > 0);

        commits = commitsApi.getCommits(testProject, "master", TEST_PROJECT_SUBDIRECTORY_PATH);
        assertNotNull(commits);
        assertTrue(commits.size() > 0);
    }

    @Test
    public void testCommitsByPathNotFound() throws GitLabApiException {

        try {
            List<Commit> commits = gitLabApi.getCommitsApi().getCommits(
                    testProject, "master", "this-file-does-not-exist.an-extension");
            assertTrue(commits == null || commits.isEmpty());
        } catch (GitLabApiException gle) {
            assertEquals(Response.Status.NOT_FOUND, gle.getHttpStatus());
        }
    }
}