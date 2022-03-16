package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.gitlab4j.api.models.Version;
import org.gitlab4j.api.utils.SecretString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestGitLabLogin implements PropertyConstants {

    // The following needs to be set to your test repository
    private static final String TEST_LOGIN_USERNAME = HelperUtils.getProperty(LOGIN_USERNAME_KEY);
    private static final String TEST_LOGIN_PASSWORD = HelperUtils.getProperty(LOGIN_PASSWORD_KEY);
    private static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    private static final String TEST_PRIVATE_TOKEN = HelperUtils.getProperty(PRIVATE_TOKEN_KEY);

    private static String problems = "";

    public TestGitLabLogin() {
        super();
    }

    @BeforeAll
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

        if (!problems.isEmpty()) {
            System.err.print(problems);
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(problems != null && problems.isEmpty());
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
