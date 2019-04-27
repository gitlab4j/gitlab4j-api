package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProtectedBranch;
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
public class TestProtectedBranchesApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    private static final String TEST_BRANCH_REF = "master";
    private static final String TEST_BRANCH_NAME = "feature/test_branch";
    private static final String TEST_PROTECT_BRANCH_NAME = "feature/protect_branch";

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @AfterClass
    public static void teardown() {
        if (gitLabApi != null) {

            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);

                try {
                    gitLabApi.getProtectedBranchesApi().unprotectBranch(project.getId(), TEST_BRANCH_NAME);
                    gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
                } catch (GitLabApiException ignore) {
                }

                try {
                    gitLabApi.getProtectedBranchesApi().unprotectBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);
                    gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);
                } catch (GitLabApiException ignore) {
                }
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Before
    public void beforeMethod() throws GitLabApiException {
        assumeNotNull(gitLabApi);
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        Branch protectedBranch;
        try {
            protectedBranch = gitLabApi.getRepositoryApi().getBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);
        } catch (GitLabApiException e) {
            protectedBranch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_PROTECT_BRANCH_NAME, TEST_BRANCH_REF);
        }
        assertNotNull(protectedBranch);
        gitLabApi.getRepositoryApi().protectBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);

        Branch branch;
        try {
            branch = gitLabApi.getRepositoryApi().getBranch(project.getId(), TEST_BRANCH_NAME);
        } catch (GitLabApiException e) {
            branch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH_NAME, TEST_BRANCH_REF);
        }
        assertNotNull(branch);
    }

    @Test
    public void testGetProtectedBranches() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(project.getId());
        assertNotNull(branches);
        assertTrue(branches.stream()
                .anyMatch((branch) -> branch.getName().equals(TEST_PROTECT_BRANCH_NAME)));
    }

    @Test
    public void testUnprotectBranch() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        gitLabApi.getProtectedBranchesApi().unprotectBranch(project.getId(), TEST_PROTECT_BRANCH_NAME);
        List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(project.getId());
        assertNotNull(branches);
        assertTrue(branches.stream()
                .noneMatch((branch) -> branch.getName().equals(TEST_PROTECT_BRANCH_NAME)));
    }

    @Test
    public void testProtectBranch() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        ProtectedBranch branch = gitLabApi.getProtectedBranchesApi().protectBranch(project.getId(), TEST_BRANCH_NAME);
        assertNotNull(branch);
        assertEquals(TEST_BRANCH_NAME, branch.getName());

        List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(project.getId());
        assertNotNull(branches);
        assertTrue(branches.stream()
                .anyMatch((protectedBranch) -> protectedBranch.getName().equals(TEST_BRANCH_NAME)));
    }
}
