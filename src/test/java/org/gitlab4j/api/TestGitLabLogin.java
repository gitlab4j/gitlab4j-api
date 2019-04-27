package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Version;
import org.gitlab4j.api.utils.SecretString;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_HOST_URL
 * TEST_LOGIN_USERNAME
 * TEST_LOGIN_PASSWORD
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
public class TestGitLabLogin {

    // The following needs to be set to your test repository
    private static final String TEST_LOGIN_USERNAME;
    private static final String TEST_LOGIN_PASSWORD;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_LOGIN_USERNAME = TestUtils.getProperty("TEST_LOGIN_USERNAME");
        TEST_LOGIN_PASSWORD = TestUtils.getProperty("TEST_LOGIN_PASSWORD");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static String problems = "";
    private static boolean hasSession;

    public TestGitLabLogin() {
        super();
    }

    @BeforeClass
    public static void setup() {

        problems = "";

        if (TEST_LOGIN_USERNAME == null || TEST_LOGIN_USERNAME.trim().isEmpty()) {
            problems += "TEST_LOGIN_USERNAME cannot be empty\n";
        }

        if (TEST_LOGIN_PASSWORD == null || TEST_LOGIN_PASSWORD.trim().isEmpty()) {
            problems += "TEST_LOGIN_PASSWORD cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            try {
                GitLabApi gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
                Version version = gitLabApi.getVersion();
                String[] parts = version.getVersion().split(".", -1);
                if (parts.length == 3) {
                    if (Integer.parseInt(parts[0]) < 10 || 
                            (Integer.parseInt(parts[0]) == 10 && Integer.parseInt(parts[1]) < 2)) {
                        hasSession = true;
                    }
                }
            } catch (Exception e) {                
            }
        }

        if (!problems.isEmpty()) {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(problems != null && problems.isEmpty());
    }

    @Test
    public void testSessionFallover() throws GitLabApiException {
        assumeFalse(hasSession);
        @SuppressWarnings("deprecation")
        GitLabApi gitLabApi = GitLabApi.login(ApiVersion.V4, TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD);
        assertNotNull(gitLabApi);
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
    }

    @Test
    public void testOauth2LoginWithStringPassword() throws GitLabApiException {
        GitLabApi gitLabApi = GitLabApi.oauth2Login(TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD, null, null, true);
        assertNotNull(gitLabApi);
        Version version = gitLabApi.getVersion();
        System.out.println("ACCESS_TOKEN: " + gitLabApi.getAuthToken());
        assertNotNull(version);
    }

    @Test
    public void testOauth2LoginWithCharSequencePassword() throws GitLabApiException {
        SecretString password = new SecretString(TEST_LOGIN_PASSWORD);
        GitLabApi gitLabApi = GitLabApi.oauth2Login(TEST_HOST_URL, TEST_LOGIN_USERNAME, password, null, null, true);
        assertNotNull(gitLabApi);
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
    }

    @Test
    public void testOauth2LoginWithCharArrayPassword() throws GitLabApiException {
        char[] password = TEST_LOGIN_PASSWORD.toCharArray();
        GitLabApi gitLabApi = GitLabApi.oauth2Login(TEST_HOST_URL, TEST_LOGIN_USERNAME, password, null, null, true);
        assertNotNull(gitLabApi);
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
    }
}
