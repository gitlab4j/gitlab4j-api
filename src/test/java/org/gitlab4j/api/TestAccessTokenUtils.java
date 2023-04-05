package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;

import org.gitlab4j.api.utils.AccessTokenUtils;
import org.gitlab4j.api.utils.AccessTokenUtils.Scope;
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
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestAccessTokenUtils {

    // The following needs to be set to your test repository
    private static final String TEST_LOGIN_USERNAME = HelperUtils.getProperty("TEST_LOGIN_USERNAME");
    private static final String TEST_LOGIN_PASSWORD = HelperUtils.getProperty("TEST_LOGIN_PASSWORD");
    private static final String TEST_HOST_URL = HelperUtils.getProperty("TEST_HOST_URL");

    private static String problems = "";

    public TestAccessTokenUtils() {
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

        if (!problems.isEmpty()) {
            System.err.print(problems);
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(problems.isEmpty());
    }

    @Test
    public void testCreatePersonalAccessToken() throws GitLabApiException {

        final String tokenName = "Testing Token Creation-" + HelperUtils.getRandomInt(1000);

        // NOTE: READ_REGISTRY scope is left out because the GitLab server docker instance does not have the
        // registry configured and the test would thus fail.
        Scope[] scopes = {Scope.API, Scope.READ_USER, Scope.READ_REPOSITORY, Scope.WRITE_REPOSITORY, Scope.SUDO};
        String accessToken = AccessTokenUtils.createPersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD, tokenName, scopes);
        System.out.format("Created '%s' personal access token: %s%n", tokenName, accessToken);

        assertNotNull(accessToken);
        assertFalse(accessToken.trim().isEmpty());

        // Go ahead and revoke (delete) the just created access token
        try {
            AccessTokenUtils.revokePersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD, tokenName, scopes);
            System.out.format("Revoked '%s' personal access token: %s%n", tokenName, accessToken);
        } catch (Exception ignore) {}
    }

    @Test
    public void testCreatePersonalAccessTokenFailedLogin() {

        try {
            AccessTokenUtils.createPersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, "INVALID PASSWORD",
                "Testing Token Creation", Arrays.asList(Scope.API, Scope.SUDO));
            fail("Expected a failure, but personal access token was created.");
        } catch (GitLabApiException glae) {
            assertEquals(401, glae.getHttpStatus());
        }
    }

    @Test
    public void testRevokePersonalAccessToken() throws GitLabApiException {

        final String tokenName = "Testing Token Revoke-" + HelperUtils.getRandomInt(1000);

        String accessToken = AccessTokenUtils.createPersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                tokenName, Arrays.asList(Scope.API, Scope.SUDO));
        System.out.format("Created '%s' personal access token: %s%n", tokenName, accessToken);
        assertNotNull(accessToken);
        assertFalse(accessToken.trim().isEmpty());

        AccessTokenUtils.revokePersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                tokenName, Arrays.asList(Scope.API, Scope.SUDO));
        System.out.format("Revoked '%s' personal access token: %s%n", tokenName, accessToken);
    }

    @Test
    public void testGetFeedToken() throws GitLabApiException {

        String feedToken = AccessTokenUtils.getFeedToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD);
        System.out.println("Fetched Feed token: " + feedToken);
        assertNotNull(feedToken);
        assertFalse(feedToken.trim().isEmpty());
    }

    @Test
    public void testGetHealthCheckAccessToken() throws GitLabApiException {

        String accessToken = AccessTokenUtils.getHealthCheckAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD);
        System.out.println("Fetched health check access token: " + accessToken);
        assertNotNull(accessToken);
        assertFalse(accessToken.trim().isEmpty());
    }
}
