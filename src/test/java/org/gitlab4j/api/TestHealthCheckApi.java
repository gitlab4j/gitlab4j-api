package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import org.gitlab4j.api.models.HealthCheckInfo;
import org.gitlab4j.api.utils.AccessTokenUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_HOST_URL
 * TEST_HEALTH_CHECK_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
@Category(IntegrationTest.class)
public class TestHealthCheckApi implements PropertyConstants {

    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    private static final String TEST_LOGIN_USERNAME = HelperUtils.getProperty(LOGIN_USERNAME_KEY);
    private static final String TEST_LOGIN_PASSWORD = HelperUtils.getProperty(LOGIN_PASSWORD_KEY);
    private static String TEST_HEALTH_CHECK_TOKEN = HelperUtils.getProperty(HEALTH_CHECK_TOKEN_KEY);
    private static GitLabApi gitLabApi;

    public TestHealthCheckApi() {
        super();
    }

    @BeforeClass
    public static void setup() throws GitLabApiException {

        String problems = "";
        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        // Fetch the Health Check Token if not set already
        if (TEST_HEALTH_CHECK_TOKEN == null || TEST_HEALTH_CHECK_TOKEN.trim().isEmpty()) {
            TEST_HEALTH_CHECK_TOKEN = AccessTokenUtils.getHealthCheckAccessToken(
                   TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD);
            HelperUtils.setProperty(HEALTH_CHECK_TOKEN_KEY, TEST_HEALTH_CHECK_TOKEN);
        }

        if (TEST_HEALTH_CHECK_TOKEN == null || TEST_HEALTH_CHECK_TOKEN.trim().isEmpty()) {
            problems += "TEST_HEALTH_CHECK_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(TEST_HOST_URL, (String)null);
        } else {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testLivelinessHealthCheck() throws GitLabApiException {  
        @SuppressWarnings("deprecation")
        HealthCheckInfo liveness = gitLabApi.getHealthCheckApi().getLiveness(TEST_HEALTH_CHECK_TOKEN);
        assertNotNull(liveness);
    }

    @Test
    public void testReadinessHealthCheck() throws GitLabApiException {  
        @SuppressWarnings("deprecation")
        HealthCheckInfo readiness = gitLabApi.getHealthCheckApi().getReadiness(TEST_HEALTH_CHECK_TOKEN);
        assertNotNull(readiness);
    }
}
