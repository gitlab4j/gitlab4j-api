package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeNotNull;

import java.util.Optional;
import java.util.stream.Stream;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.MergeRequestParams;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.User;
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
public class TestMergeRequestApi extends AbstractIntegrationTest {

    private static final String TEST_BRANCH_NAME = "feature/gitlab4j-merge-request-test";
    private static final String TEST_MR_TITLE = "Merge Request test: gitlab4j-merge-request-test";
    private static final String TEST_DESCRIPTION = "Description for Merge Request test";

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static User currentUser;

    public TestMergeRequestApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
        currentUser = getCurrentUser();

        teardown();
    }

    @AfterClass
    public static void teardown() {

	if (testProject == null) {
	    return;
	}

	try {

	    Stream<MergeRequest> mergeRequests = gitLabApi.getMergeRequestApi().getMergeRequestsStream(testProject);
	    MergeRequest mergeRequest = mergeRequests.filter(
		    m -> TEST_MR_TITLE.equals(m.getTitle())).findFirst().orElse(null);
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

    @Before
    public void beforeMethod() {
        assumeNotNull(testProject);
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
}
