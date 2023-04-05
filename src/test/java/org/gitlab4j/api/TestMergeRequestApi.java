package org.gitlab4j.api;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.gitlab4j.api.Constants.MergeRequestSearchIn;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.MergeRequestFilter;
import org.gitlab4j.api.models.MergeRequestParams;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.User;
import org.junit.jupiter.api.AfterAll;
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
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that testCreate() is executed first.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestMergeRequestApi extends AbstractIntegrationTest {

    private static final String TEST_BRANCH_NAME = "feature/gitlab4j-merge-request-test";
    private static final String TEST_MR_TITLE = "Merge Request test: gitlab4j-merge-request-test";
    private static final String TEST_DESCRIPTION = "Description for Merge Request test";
    private static final String TEST_REBASE_BRANCH_NAME = "feature/gitlab4j-merge-request-rebase-test";
    private static final String TEST_REBASE_MR_TITLE = "Merge Request test: gitlab4j-merge-request-rebase-test";

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static User currentUser;

    public TestMergeRequestApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
        currentUser = getCurrentUser();

        teardown();
    }

    @AfterAll
    public static void teardown() {

	if (testProject == null) {
	    return;
	}

	try {

	    List<MergeRequest> mergeRequests = gitLabApi.getMergeRequestApi().getMergeRequests(testProject);
	    MergeRequest mergeRequest = mergeRequests.stream().filter(
                m -> TEST_MR_TITLE.equals(m.getTitle())).findFirst().orElse(null);
	    if (mergeRequest != null) {
                gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject, mergeRequest.getIid());
	    }

	    mergeRequest = mergeRequests.stream().filter(
                m -> TEST_REBASE_MR_TITLE.equals(m.getTitle())).findFirst().orElse(null);
	    if (mergeRequest != null) {
                gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject, mergeRequest.getIid());
	    }

	} catch (GitLabApiException ignore) {
	}

        try {
            gitLabApi.getRepositoryApi().deleteBranch(testProject, TEST_BRANCH_NAME);
        } catch (GitLabApiException ignore) {
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateAndUpdateMergeRequest() throws GitLabApiException {

	// Create a test branch
        Branch branch = gitLabApi.getRepositoryApi().createBranch(testProject, TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        // Create a new file in the test branch
        RepositoryFile repoFile = new RepositoryFile();
        repoFile.setFilePath("README-FOR-TESTING-MERGE-REQUEST.md");
        repoFile.setContent("This is content");
        gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, TEST_BRANCH_NAME, "Initial commit.");

        MergeRequest mr = null;
        try {

            MergeRequestParams params = new MergeRequestParams()
                .withSourceBranch(TEST_BRANCH_NAME)
                .withTargetBranch("master")
                .withTitle(TEST_MR_TITLE);
            mr = gitLabApi.getMergeRequestApi().createMergeRequest(testProject, params);
            assertEquals(TEST_MR_TITLE, mr.getTitle());

            params = new MergeRequestParams()
                .withAssigneeId(currentUser.getId())
                .withDescription(TEST_DESCRIPTION)
                .withDiscussionLocked(true);
            MergeRequest updatedMr = gitLabApi.getMergeRequestApi().updateMergeRequest(testProject, mr.getIid(), params);
            assertEquals(currentUser.getId(), updatedMr.getAssignee().getId());
            assertEquals(TEST_DESCRIPTION, updatedMr.getDescription());
            assertEquals(true, updatedMr.getDiscussionLocked());

            gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject,  mr.getIid());
            Optional<MergeRequest> deletedMr =
                gitLabApi.getMergeRequestApi().getOptionalMergeRequest(testProject, mr.getIid());
            mr = null;
            assertFalse(deletedMr.isPresent());

        } finally {

            if (mr != null) {
                try {
                    gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject,  mr.getIid());
                } catch (Exception ignore) {
                }
            }

            try {
                gitLabApi.getRepositoryApi().deleteBranch(testProject, TEST_BRANCH_NAME);
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Test
    public void testMergeRequestFilter() throws GitLabApiException {

	// Create a test branch
        Branch branch = gitLabApi.getRepositoryApi().createBranch(testProject, TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        // Create a new file in the test branch
        RepositoryFile repoFile = new RepositoryFile();
        repoFile.setFilePath("README-FOR-TESTING-MERGE-REQUEST.md");
        repoFile.setContent("This is content");
        gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, TEST_BRANCH_NAME, "Initial commit.");

        MergeRequest mr = null;
        try {

            MergeRequestParams params = new MergeRequestParams()
                .withSourceBranch(TEST_BRANCH_NAME)
                .withTargetBranch("master")
                .withTitle(TEST_MR_TITLE);
            mr = gitLabApi.getMergeRequestApi().createMergeRequest(testProject, params);
            assertEquals(TEST_MR_TITLE, mr.getTitle());

            MergeRequestFilter filter = new MergeRequestFilter()
        	    .withSearch("itriuoewrtiuertuieuitruiyewr")
        	    .withIn(MergeRequestSearchIn.TITLE);
            List<MergeRequest> mergeRequests = gitLabApi.getMergeRequestApi().getMergeRequests(filter);
            assertTrue(mergeRequests.isEmpty());

            filter = new MergeRequestFilter()
        	    .withSearch(TEST_MR_TITLE)
        	    .withIn(MergeRequestSearchIn.TITLE);
            mergeRequests = gitLabApi.getMergeRequestApi().getMergeRequests(filter);
            assertFalse(mergeRequests.isEmpty());

            List<String> titles = mergeRequests.stream().map(MergeRequest::getTitle).collect(toList());
            assertTrue(titles.contains(TEST_MR_TITLE));

        } finally {

            if (mr != null) {
                try {
                    gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject,  mr.getIid());
                } catch (Exception ignore) {
                }
            }

            try {
                gitLabApi.getRepositoryApi().deleteBranch(testProject, TEST_BRANCH_NAME);
            } catch (GitLabApiException ignore) {
            }
        }
    }    

    @Test
    public void testRebaseMergeRequest() throws GitLabApiException {

	// Create a test branch
        Branch branch = gitLabApi.getRepositoryApi().createBranch(testProject, TEST_REBASE_BRANCH_NAME, "master");
        assertNotNull(branch);

        // Create a new file in the test branch
        RepositoryFile repoFile = new RepositoryFile();
        repoFile.setFilePath("README-FOR-TESTING-REBASE-MERGE-REQUEST.md");
        repoFile.setContent("This is content");
        gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, TEST_REBASE_BRANCH_NAME, "Initial commit.");

        MergeRequest mr = null;
        try {

            MergeRequestParams params = new MergeRequestParams()
                .withSourceBranch(TEST_REBASE_BRANCH_NAME)
                .withTargetBranch("master")
                .withTitle(TEST_REBASE_MR_TITLE);
            mr = gitLabApi.getMergeRequestApi().createMergeRequest(testProject, params);
            assertEquals(TEST_REBASE_MR_TITLE, mr.getTitle());

            MergeRequest rebaseMr = gitLabApi.getMergeRequestApi().rebaseMergeRequest(testProject, mr.getIid());
            assertEquals(true, rebaseMr.getRebaseInProgress());

            // Wait up to 10 seconds for the rebase to complete
            System.out.print("Waiting for rebase to complete");
            int retries = 0;
            while (true) {

                System.out.print(".");
                rebaseMr = gitLabApi.getMergeRequestApi().getRebaseStatus(testProject, mr.getIid());
                if (!rebaseMr.getRebaseInProgress()) {
                    System.out.println("done");
                    break;
                }

                if (retries >= 10) {
                    System.out.println("aborting!");
                    fail("Merge request rebase is taking too long, failing test.");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                retries++;
            }

            gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject,  mr.getIid());
            Optional<MergeRequest> deletedMr =
                gitLabApi.getMergeRequestApi().getOptionalMergeRequest(testProject, mr.getIid());
            mr = null;
            assertFalse(deletedMr.isPresent());

        } finally {

            if (mr != null) {
                try {
                    gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject,  mr.getIid());
                } catch (Exception ignore) {
                }
            }

            try {
                gitLabApi.getRepositoryApi().deleteBranch(testProject, TEST_REBASE_BRANCH_NAME);
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Test
    public void testGetMergeRequestPipelines() throws GitLabApiException {

	// Create a test branch
        Branch branch = gitLabApi.getRepositoryApi().createBranch(testProject, TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        // Create a new file in the test branch
        RepositoryFile repoFile = new RepositoryFile();
        repoFile.setFilePath("README-FOR-TESTING-MERGE-REQUEST-PIPELINES.md");
        repoFile.setContent("This is content");
        gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, TEST_BRANCH_NAME, "Initial commit.");

        MergeRequest mr = null;
        try {

            MergeRequestParams params = new MergeRequestParams()
                .withSourceBranch(TEST_BRANCH_NAME)
                .withTargetBranch("master")
                .withTitle(TEST_MR_TITLE);
            mr = gitLabApi.getMergeRequestApi().createMergeRequest(testProject, params);
            assertNotNull(mr);

            List<Pipeline> pipelines = gitLabApi.getMergeRequestApi().getMergeRequestPipelines(testProject, mr.getIid());
            assertNotNull(pipelines);

            Stream<Pipeline> pipelineStream = gitLabApi.getMergeRequestApi().getMergeRequestPipelinesStream(testProject, mr.getIid());
            assertNotNull(pipelineStream);

        } finally {

            if (mr != null) {
                try {
                    gitLabApi.getMergeRequestApi().deleteMergeRequest(testProject,  mr.getIid());
                } catch (Exception ignore) {}
            }

            try {
                gitLabApi.getRepositoryApi().deleteBranch(testProject, TEST_BRANCH_NAME);
            } catch (GitLabApiException ignore) {}
        }
    }
}
