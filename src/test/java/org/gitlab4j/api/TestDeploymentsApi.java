package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.gitlab4j.api.Constants.DeploymentStatus;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Deployment;
import org.gitlab4j.api.models.DeploymentFilter;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * In order for these tests to run you must set the following properties in
 * test-gitlab4j.properties
 *
 * TEST_HOST_URL TEST_PRIVATE_TOKEN TEST_USERNAME
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
@Category(IntegrationTest.class)
public class TestDeploymentsApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_USERNAME = HelperUtils.getProperty(USERNAME_KEY);

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestDeploymentsApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        if (TEST_USERNAME == null || TEST_USERNAME.trim().isEmpty()) {
            System.err.println("TEST_USER_NAME cannot be empty");
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateDeployment() throws GitLabApiException {

        assertNotNull(testProject);

        String environment = "environment-" + HelperUtils.getRandomInt(1000);
        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject);

        assertTrue("Commits list should not be empty.", commits.size() > 0);

        Deployment deployment = gitLabApi.getDeploymentsApi().addDeployment(testProject,
            environment,
            commits.get(0).getId(),
            testProject.getDefaultBranch(),
            false,
            DeploymentStatus.RUNNING);

        assertNotNull(deployment);
        assertEquals(environment, deployment.getEnvironment().getName());
        assertEquals(commits.get(0).getId(), deployment.getSha());
        assertEquals(testProject.getDefaultBranch(), deployment.getRef());
        assertEquals(DeploymentStatus.RUNNING, deployment.getStatus());
    }

    @Test
    public void testUpdateDeployment() throws GitLabApiException {

        assertNotNull(testProject);

        String environment = "environment-" + HelperUtils.getRandomInt(1000);
        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject);

        assertTrue("Commits list should not be empty.", commits.size() > 0);

        Deployment deployment = gitLabApi.getDeploymentsApi().addDeployment(testProject,
            environment,
            commits.get(0).getId(),
            testProject.getDefaultBranch(),
            false,
            DeploymentStatus.RUNNING);

        assertNotNull(deployment);
        assertEquals(environment, deployment.getEnvironment().getName());
        assertEquals(commits.get(0).getId(), deployment.getSha());
        assertEquals(testProject.getDefaultBranch(), deployment.getRef());
        assertEquals(DeploymentStatus.RUNNING, deployment.getStatus());

        Deployment updatedDeployment = gitLabApi.getDeploymentsApi().updateDeployment(testProject, deployment.getId(),
            DeploymentStatus.SUCCESS);

        assertNotNull(updatedDeployment);
        assertEquals(environment, updatedDeployment.getEnvironment().getName());
        assertEquals(commits.get(0).getId(), updatedDeployment.getSha());
        assertEquals(testProject.getDefaultBranch(), updatedDeployment.getRef());
        assertEquals(DeploymentStatus.SUCCESS, updatedDeployment.getStatus());
    }

    @Test
    public void testGetDeployment() throws GitLabApiException {

        assertNotNull(testProject);

        String environment = "environment-" + HelperUtils.getRandomInt(1000);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject);

        assertTrue("Commits list should not be empty.", commits.size() > 0);

        Deployment deployment = gitLabApi.getDeploymentsApi().addDeployment(testProject,
            environment,
            commits.get(0).getId(),
            testProject.getDefaultBranch(),
            false,
            DeploymentStatus.SUCCESS);

        assertNotNull(deployment);
        assertEquals(environment, deployment.getEnvironment().getName());
        assertEquals(commits.get(0).getId(), deployment.getSha());
        assertEquals(testProject.getDefaultBranch(), deployment.getRef());
        assertEquals(DeploymentStatus.SUCCESS, deployment.getStatus());

        Deployment getDeployment = gitLabApi.getDeploymentsApi().getDeployment(testProject, deployment.getId());

        assertNotNull(getDeployment);
        assertEquals(environment, getDeployment.getEnvironment().getName());
        assertEquals(commits.get(0).getId(), getDeployment.getSha());
        assertEquals(testProject.getDefaultBranch(), getDeployment.getRef());
        assertEquals(DeploymentStatus.SUCCESS, getDeployment.getStatus());
        assertEquals(deployment.getCreatedAt(), getDeployment.getCreatedAt());

        Optional<Deployment> optionalDeployment = gitLabApi.getDeploymentsApi().getOptionalDeployment(testProject,
            getDeployment.getId());

        optionalDeployment.ifPresent(d -> {
            assertEquals(environment, d.getEnvironment().getName());
            assertEquals(commits.get(0).getId(), d.getSha());
            assertEquals(testProject.getDefaultBranch(), d.getRef());
            assertEquals(DeploymentStatus.SUCCESS, d.getStatus());
            assertEquals(deployment.getCreatedAt(), d.getCreatedAt());
        });

        if (!optionalDeployment.isPresent()) {
            fail("A deployment should be present.");
        }
    }

    @Test
    public void testGetDeployments() throws GitLabApiException {

        assertNotNull(testProject);

        String environment = "environment-" + HelperUtils.getRandomInt(1000);

        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject);

        assertTrue("Commits list should not be empty.", commits.size() > 0);

        for (int i = 0; i < 20; i++) {
            gitLabApi.getDeploymentsApi().addDeployment(testProject,
                environment,
                commits.get(0).getId(),
                testProject.getDefaultBranch(),
                false,
                DeploymentStatus.SUCCESS);
        }

        gitLabApi.getDeploymentsApi().addDeployment(testProject,
            environment + "-other",
            commits.get(0).getId(),
            testProject.getDefaultBranch(),
            false,
            DeploymentStatus.SUCCESS);

        Pager<Deployment> pager = gitLabApi.getDeploymentsApi().getProjectDeployments(testProject, 2);
        while (pager.hasNext()) {
            pager.next();
            assertTrue(pager.current().size() == 1 || pager.current().size() == 2);
        }

        List<Deployment> deployments = gitLabApi.getDeploymentsApi().getProjectDeployments(testProject);
        int unfilteredeploymentNb = deployments.size();
        assertTrue(unfilteredeploymentNb >= 10);

        DeploymentFilter deploymentFilter = new DeploymentFilter();
        deploymentFilter.setEnvironment(environment);

        Pager<Deployment> filteredPager = gitLabApi.getDeploymentsApi().getProjectDeployments(testProject, deploymentFilter);
        while (filteredPager.hasNext()) {
            filteredPager.next();
            assertTrue(filteredPager.current().size() > 1 && filteredPager.current().size() < unfilteredeploymentNb);
        }

        deploymentFilter.setEnvironment("none");

        filteredPager = gitLabApi.getDeploymentsApi().getProjectDeployments(testProject, deploymentFilter);
        if (filteredPager.hasNext()) {
            filteredPager.next();
            assertTrue("Should be no deployments for environment `none`", filteredPager.current().size() == 0);
        }

        Stream<Deployment> projectDeploymentsStream = gitLabApi.getDeploymentsApi().getProjectDeploymentsStream(testProject);
        assertTrue(projectDeploymentsStream.count() >= 10);

        projectDeploymentsStream = gitLabApi.getDeploymentsApi().getProjectDeploymentsStream(testProject, deploymentFilter);
        assertEquals(0L, projectDeploymentsStream.count());

    }

}
