package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.gitlab4j.api.models.Environment;
import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * In order for these tests to run you must set the following properties in
 * ~/test-gitlab4j.properties
 * <p>
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
public class TestEnvironmentsApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    private static final String ENVIRONMENT_NAME = "gitlab4j-testing";
    private static final String EXTERNAL_URL = "https:/testing.example.com/";
    private static Random randomNumberGenerator = new Random();

    public TestEnvironmentsApi() {
	super();
    }

    @BeforeClass
    public static void setup() {

	// Must setup the connection to the GitLab test server and get the test Project
	// instance
	gitLabApi = baseTestSetup();
	testProject = getTestProject();

	deleteAllTestAssets();
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
	deleteAllTestAssets();
    }

    private static void deleteAllTestAssets() {

	if (gitLabApi != null) {
	    try {
		List<Environment> envs = gitLabApi.getEnvironmentsApi().getEnvironments(testProject);
		if (envs != null) {

		    for (Environment env : envs) {
			if (env.getName().startsWith(ENVIRONMENT_NAME)) {
			    gitLabApi.getEnvironmentsApi().deleteEnvironment(testProject, env.getId());
			}
		    }
		}
	    } catch (GitLabApiException ignore) {
	    }
	}
    }

    @Before
    public void beforeMethod() {
	assumeNotNull(gitLabApi);
    }

    private static String getUniqueName() {
	return (ENVIRONMENT_NAME + " - " + (randomNumberGenerator.nextInt() + 1));
    }

    @Test
    public void testGetEnvironments() throws GitLabApiException {

	final Environment env = gitLabApi.getEnvironmentsApi().createEnvironment(
		testProject, getUniqueName(), EXTERNAL_URL);

	try {
	    List<Environment> envs = gitLabApi.getEnvironmentsApi().getEnvironments(testProject);
	    assertTrue(envs.size() > 0);
	    Environment foundEnv = envs.stream().filter(
		    e -> e.getName().equals(env.getName())).findFirst().orElse(null);
	    assertNotNull(foundEnv);
	    assertEquals(env.getName(), foundEnv.getName());
	} catch (Exception e) {
	    gitLabApi.getEnvironmentsApi().deleteEnvironment(testProject, env.getId());
	}
    }

    @Test
    public void testDeleteEnvironment() throws GitLabApiException {

	final Environment env = gitLabApi.getEnvironmentsApi().createEnvironment(
		testProject, getUniqueName(), EXTERNAL_URL);
	gitLabApi.getEnvironmentsApi().deleteEnvironment(testProject, env.getId());

	Stream<Environment> envs = gitLabApi.getEnvironmentsApi().getEnvironmentsStream(testProject);
	Environment foundEnv = envs.filter(e -> e.getName().equals(env.getName())).findFirst().orElse(null);
	assertNull(foundEnv);
    }

    @Test
    public void testOptionalEnvironment() throws GitLabApiException {

	final Environment env = gitLabApi.getEnvironmentsApi().createEnvironment(
		testProject, getUniqueName(), EXTERNAL_URL);
	Optional<Environment> optionalEnv =
		gitLabApi.getEnvironmentsApi().getOptionalEnvironment(testProject, env.getId());
	assertTrue(optionalEnv.isPresent());
	assertEquals(env.getName(), optionalEnv.get().getName());

	gitLabApi.getEnvironmentsApi().deleteEnvironment(testProject, env.getId());
    }
}
