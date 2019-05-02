package org.gitlab4j.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.gitlab4j.api.utils.AccessTokenUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/Test*.class"})
@IncludeCategory(IntegrationTest.class)
public class IntegrationTestSuite implements PropertyConstants {

    private static final String TEST_LOGIN_USERNAME = HelperUtils.getProperty(LOGIN_USERNAME_KEY);
    private static final String TEST_LOGIN_PASSWORD = HelperUtils.getProperty(LOGIN_PASSWORD_KEY);
    private static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    
    protected static final String TEST_PROJECT_NAME = HelperUtils.getProperty(PROJECT_NAME_KEY);
    protected static final String TEST_NAMESPACE = HelperUtils.getProperty(NAMESPACE_KEY);

    protected static final String TEST_PRIVATE_TOKEN_NAME = "GitLab4J Test Private Token - " + HelperUtils.getRandomInt(1000);
    protected static String TEST_PRIVATE_TOKEN = HelperUtils.getProperty(PRIVATE_TOKEN_KEY);

    protected static final String TEST_ACCESS_TOKEN_NAME = "GitLab4J Test Access Token - " + HelperUtils.getRandomInt(1000);
    protected static String TEST_ACCESS_TOKEN = HelperUtils.getProperty(ACCESS_TOKEN_KEY);

    private static boolean createdPrivateToken = false;
    private static boolean createdAccessToken = false;
    private static String problems = "";

    @BeforeClass
    public static void suiteSetup() throws GitLabApiException {

        System.out.println("********************************************************");
        System.out.println("*                  Test Suite Setup                    *");
        System.out.println("********************************************************");

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
            fail(problems);
        }

        // If the private token is not in the properties, create it
        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {

            TEST_PRIVATE_TOKEN = AccessTokenUtils.createPersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_PRIVATE_TOKEN_NAME, Arrays.asList("api", "sudo"));
            System.out.println("Created private token: " + TEST_PRIVATE_TOKEN);
            assertNotNull(TEST_PRIVATE_TOKEN);
            assertFalse(TEST_PRIVATE_TOKEN.trim().isEmpty());
            HelperUtils.setProperty(PRIVATE_TOKEN_KEY, TEST_PRIVATE_TOKEN);
            createdPrivateToken = true;
        }

        // If the access token is not in the properties, create it t
        if (TEST_ACCESS_TOKEN == null || TEST_ACCESS_TOKEN.trim().isEmpty()) {

            TEST_ACCESS_TOKEN = AccessTokenUtils.createPersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_ACCESS_TOKEN_NAME, Arrays.asList("api", "sudo"));
            System.out.println("Created access token: " + TEST_ACCESS_TOKEN);
            assertNotNull(TEST_ACCESS_TOKEN);
            assertFalse(TEST_ACCESS_TOKEN.trim().isEmpty());
            HelperUtils.setProperty(ACCESS_TOKEN_KEY, TEST_ACCESS_TOKEN);
            createdAccessToken = true;
        }
    }

    @AfterClass
    public static void suiteTeardown() throws GitLabApiException {

        System.out.println("********************************************************");
        System.out.println("*                 Test Suite Teardown                  *");
        System.out.println("********************************************************");

        if (createdPrivateToken && TEST_PRIVATE_TOKEN != null) {
            try {
                AccessTokenUtils.revokePersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_PRIVATE_TOKEN_NAME, Arrays.asList("api", "sudo"));
                System.out.format("Revoved '%s'%n", TEST_PRIVATE_TOKEN_NAME);
            } catch (Exception ignore) {}
        }

        if (createdAccessToken && TEST_ACCESS_TOKEN != null) {
            try {
                AccessTokenUtils.revokePersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_ACCESS_TOKEN_NAME, Arrays.asList("api", "sudo"));
                System.out.format("Revoved '%s'%n", TEST_ACCESS_TOKEN_NAME);
            } catch (Exception ignore) {}
        }
    }
}
