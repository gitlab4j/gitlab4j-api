package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProtectedBranch;
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
public class TestProtectedBranchesApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    private static final String TEST_BRANCH_REF = "master";
    private static final String TEST_BRANCH_NAME = "feature/test_branch";
    private static final String TEST_PROTECT_BRANCH_NAME = "feature/protect_branch";

    @BeforeAll
    public static void setup() {
	// Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @AfterAll
    public static void teardown() {
        if (testProject != null) {
            try {
                gitLabApi.getProtectedBranchesApi().unprotectBranch(testProject.getId(), TEST_BRANCH_NAME);
                gitLabApi.getRepositoryApi().deleteBranch(testProject.getId(), TEST_BRANCH_NAME);
            } catch (GitLabApiException ignore) {
            }

            try {
                gitLabApi.getProtectedBranchesApi().unprotectBranch(testProject.getId(), TEST_PROTECT_BRANCH_NAME);
                gitLabApi.getRepositoryApi().deleteBranch(testProject.getId(), TEST_PROTECT_BRANCH_NAME);
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @BeforeEach
    public void beforeMethod() throws GitLabApiException {
        assumeTrue(testProject != null);

        Branch protectedBranch;
        try {
            protectedBranch = gitLabApi.getRepositoryApi().getBranch(testProject, TEST_PROTECT_BRANCH_NAME);
        } catch (GitLabApiException e) {
            protectedBranch = gitLabApi.getRepositoryApi().createBranch(testProject, TEST_PROTECT_BRANCH_NAME, TEST_BRANCH_REF);
        }
        assertNotNull(protectedBranch);
        gitLabApi.getRepositoryApi().protectBranch(testProject, TEST_PROTECT_BRANCH_NAME);

        Branch branch;
        try {
            branch = gitLabApi.getRepositoryApi().getBranch(testProject, TEST_BRANCH_NAME);
        } catch (GitLabApiException e) {
            branch = gitLabApi.getRepositoryApi().createBranch(testProject, TEST_BRANCH_NAME, TEST_BRANCH_REF);
        }
        assertNotNull(branch);
    }

    @Test
    public void testGetProtectedBranches() throws GitLabApiException {

        assumeTrue(testProject != null);
        List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(testProject);
        assertNotNull(branches);
        assertTrue(branches.stream()
                .anyMatch((branch) -> branch.getName().equals(TEST_PROTECT_BRANCH_NAME)));
    }

    @Test
    public void testUnprotectBranch() throws GitLabApiException {

        assumeTrue(testProject != null);
        gitLabApi.getProtectedBranchesApi().unprotectBranch(testProject, TEST_PROTECT_BRANCH_NAME);
        List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(testProject);
        assertNotNull(branches);
        assertTrue(branches.stream()
                .noneMatch((branch) -> branch.getName().equals(TEST_PROTECT_BRANCH_NAME)));
    }

    @Test
    public void testProtectBranch() throws GitLabApiException {

        assumeTrue(testProject != null);

        ProtectedBranch branch = gitLabApi.getProtectedBranchesApi().protectBranch(testProject, TEST_BRANCH_NAME);
        assertNotNull(branch);
        assertEquals(TEST_BRANCH_NAME, branch.getName());

        List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(testProject);
        assertNotNull(branches);
        assertTrue(branches.stream()
                .anyMatch((protectedBranch) -> protectedBranch.getName().equals(TEST_BRANCH_NAME)));
    }
}
