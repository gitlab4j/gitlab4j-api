package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitAction;
import org.gitlab4j.api.models.CommitAction.Action;
import org.gitlab4j.api.models.CommitPayload;
import org.gitlab4j.api.models.CommitRef;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.utils.ISO8601;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestCommitsApi extends AbstractIntegrationTest {

    private static final String TEST_CREATE_COMMIT_FILEPATH = "gitlab4j-create-commit-test-file.txt";
    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestCommitsApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        if (!gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_PROJECT_SUBDIRECTORY_PATH, "master").isPresent()) {
            try {
                RepositoryFile repoFile = new RepositoryFile();
                repoFile.setFilePath(TEST_PROJECT_SUBDIRECTORY_PATH);
                repoFile.setContent("This is a test project used to test GitLab4J-API.");
                gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, "master", "Initial commit.");
            } catch (GitLabApiException ignore) {}
        }
    }

    @BeforeEach
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
    public void testDiffStream() throws GitLabApiException {

        assertNotNull(testProject);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId());
        assertTrue(commits.size() > 0);

        Stream<Diff> diffs = gitLabApi.getCommitsApi().getDiffStream(testProject.getId(), commits.get(0).getId());
        assertTrue(diffs.count() > 0);
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

        Stream<CommitRef> commitRefsStream = gitLabApi.getCommitsApi().getCommitRefsStream(testProject.getId(), commits.get(0).getId());
        assertNotNull(commitRefsStream);
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
        assertTrue(commits.size() > 1);

        commitsApi = gitLabApi.getCommitsApi();
        commits = commitsApi.getCommits(testProject, "master", "README.md");
        assertNotNull(commits);
        assertTrue(commits.size() > 1);

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

    @Test
    public void testCreateCommit() throws GitLabApiException {

        // Make sure the file to create does not exist.
        if (gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_CREATE_COMMIT_FILEPATH, "master").isPresent()) {
            gitLabApi.getRepositoryFileApi().deleteFile(testProject, TEST_CREATE_COMMIT_FILEPATH, "master", "Deleted test file");
        }

        // Arrange
        CommitAction commitAction = new CommitAction()
                .withAction(Action.CREATE)
                .withContent("This is the original data in the file")
                .withFilePath(TEST_CREATE_COMMIT_FILEPATH);

        // Act
        Commit commit = gitLabApi.getCommitsApi().createCommit(
                testProject, "master", "Testing createCommit() create action", null, null, null, Arrays.asList(commitAction));

        // Assert
        assertNotNull(commit);

        // Arrange
        commitAction = new CommitAction()
                .withAction(Action.DELETE)
                .withFilePath(TEST_CREATE_COMMIT_FILEPATH);

        // Act
        commit = gitLabApi.getCommitsApi().createCommit(
                testProject, "master", "Testing createCommit() delete action", null, null, null, Arrays.asList(commitAction));

        // Assert
        assertNotNull(commit);

        Optional<RepositoryFile> repoFile = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_CREATE_COMMIT_FILEPATH, "master");
        assertFalse(repoFile.isPresent());
    }

    @Test
    public void testCreateCommitFromFile() throws GitLabApiException {

        // Make sure the file to create does not exist.
        if (gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_CREATE_COMMIT_FILEPATH, "master").isPresent()) {
            try {
                gitLabApi.getRepositoryFileApi().deleteFile(testProject, TEST_CREATE_COMMIT_FILEPATH, "master", "Deleted test file");
            } catch (GitLabApiException ignore) {}
        }

        // Arrange
        CommitAction commitAction = new CommitAction()
                .withAction(Action.CREATE)
                .withContent("This is the original data in the file")
                .withFilePath(TEST_CREATE_COMMIT_FILEPATH);

        // Act
        Commit commit = gitLabApi.getCommitsApi().createCommit(
                testProject, "master", "Testing createCommit() create action", null, null, null, Arrays.asList(commitAction));

        // Assert
        assertNotNull(commit);

        // Arrange
        commitAction = new CommitAction()
                .withAction(Action.DELETE)
                .withFilePath(TEST_CREATE_COMMIT_FILEPATH);

        // Act
        commit = gitLabApi.getCommitsApi().createCommit(
                testProject, "master", "Testing createCommit() delete action", null, null, null, Arrays.asList(commitAction));

        // Assert
        assertNotNull(commit);

        Optional<RepositoryFile> repoFile = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_CREATE_COMMIT_FILEPATH, "master");
        assertFalse(repoFile.isPresent());
    }

    @Test
    public void testCreateCommitCreateWithNoContent() throws GitLabApiException {

        // Arrange
        CommitAction commitAction = new CommitAction()
                .withAction(Action.CREATE)
                .withContent(null)
                .withFilePath(TEST_CREATE_COMMIT_FILEPATH + ".bk");

        // Act - expecting exception
        try {
            gitLabApi.getCommitsApi().createCommit(testProject, "master", "Testing createCommit() create action",
                    null, null, null, Arrays.asList(commitAction));
            fail("Commit should have been rejected due to no content.");
        } catch (GitLabApiException ignore) {
        }
    }

    @Test
    public void testCreateCommitWithPayload() throws GitLabApiException {

        String TEST_BRANCH = "create_commit_from_payload";

        Optional<Branch> testBranch = gitLabApi.getRepositoryApi().getOptionalBranch(testProject, TEST_BRANCH);
        if (!testBranch.isPresent()) {
            gitLabApi.getRepositoryApi().createBranch(testProject, TEST_BRANCH, "master");
        }

        if (gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_CREATE_COMMIT_FILEPATH, TEST_BRANCH).isPresent()) {
            try {
                gitLabApi.getRepositoryFileApi().deleteFile(testProject, TEST_CREATE_COMMIT_FILEPATH, TEST_BRANCH, "Deleted test file");
            } catch (GitLabApiException ignore) {}
        }

        // Arrange
        CommitPayload commitPayload = new CommitPayload()
                .withBranch(TEST_BRANCH)
                .withCommitMessage("Testing createCommit() create action")
                .withAction(Action.CREATE, "This is the original data in the file", TEST_CREATE_COMMIT_FILEPATH);

        // Act
        Commit commit = gitLabApi.getCommitsApi().createCommit(testProject, commitPayload);

        // Assert
        assertNotNull(commit);

        // Arrange
        commitPayload = new CommitPayload()
                .withBranch(TEST_BRANCH)
                .withCommitMessage("Testing createCommit() delete action")
                .withAction(Action.DELETE, TEST_CREATE_COMMIT_FILEPATH);

        // Act
        commit = gitLabApi.getCommitsApi().createCommit(testProject, commitPayload);

        // Assert
        assertNotNull(commit);

        Optional<RepositoryFile> repoFile = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_CREATE_COMMIT_FILEPATH, TEST_BRANCH);
        assertFalse(repoFile.isPresent());
    }

    @Test
    public void testRevertCommit() throws GitLabApiException {

        // Make sure the file to create does not exist.
        String filePath = TEST_CREATE_COMMIT_FILEPATH + ".test";
        if (gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, "master").isPresent()) {
            gitLabApi.getRepositoryFileApi().deleteFile(testProject, filePath, "master", "Deleted test file");
        }

        // Arrange
        CommitAction commitAction = new CommitAction()
                .withAction(Action.CREATE)
                .withContent("This is the original data in the file")
                .withFilePath(filePath);

        // Act
        Commit commit = gitLabApi.getCommitsApi().createCommit(
                testProject, "master", "Testing createCommit() create action", null, null, null, commitAction);

        // Assert
        assertNotNull(commit);
        Optional<RepositoryFile> repoFile = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, "master");
        assertTrue(repoFile.isPresent());

        // Act
        Commit revertedCommit = gitLabApi.getCommitsApi().revertCommit(testProject, commit.getId(), "master");

        // Assert
        assertNotEquals(commit.getId(), revertedCommit.getId());
        repoFile = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, "master");
        assertFalse(repoFile.isPresent());
    }

    @Test
    public void testCherryPickCommit() throws GitLabApiException {

        // Make sure the branch to cherry pick does not exist
        if(gitLabApi.getRepositoryApi().getOptionalBranch(testProject, "cherry-pick-branch").isPresent()) {
           gitLabApi.getRepositoryApi().deleteBranch(testProject, "cherry-pick-branch");
        }

        // Make sure the file to create does not exist.
        String filePath = TEST_CREATE_COMMIT_FILEPATH + ".test";
        if (gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, "master").isPresent()) {
            gitLabApi.getRepositoryFileApi().deleteFile(testProject, filePath, "master", "Deleted test file");
        }

        // Act
        Branch branch = gitLabApi.getRepositoryApi().createBranch(testProject, "cherry-pick-branch", "master");

        // Assert
        assertNotNull(branch);
        Optional<RepositoryFile> repoFileBranch = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, branch.getName());
        assertFalse(repoFileBranch.isPresent());

        // Arrange
        CommitAction commitAction = new CommitAction()
                .withAction(Action.CREATE)
                .withContent("This is the original data in the file")
                .withFilePath(filePath);

        // Act
        Commit commit = gitLabApi.getCommitsApi().createCommit(
                testProject, "master", "Testing createCommit() create action", null, null, null, commitAction);

        // Assert
        assertNotNull(commit);
        Optional<RepositoryFile> repoFile = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, "master");
        assertTrue(repoFile.isPresent());

        // Act
        Commit cherryPickedCommit = gitLabApi.getCommitsApi().cherryPickCommit(testProject, commit.getId(), "cherry-pick-branch");

        // Assert
        assertNotNull(cherryPickedCommit);
        Optional<RepositoryFile> repoFileBranchCherryPicked = gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, filePath, branch.getName());
        assertTrue(repoFileBranchCherryPicked.isPresent());
    }
}