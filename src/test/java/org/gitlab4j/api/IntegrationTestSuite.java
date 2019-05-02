package org.gitlab4j.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.gitlab4j.api.utils.AccessTokenUtils;
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

    protected static String TEST_PRIVATE_TOKEN;
    protected static String TEST_ACCESS_TOKEN;
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

        // Create a new personal access token for both the private and access tokens
        TEST_PRIVATE_TOKEN = AccessTokenUtils.createPersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD, 
                "GitLab4J Test Private Token", Arrays.asList("api", "sudo"));
        System.out.println("Created private token: " + TEST_PRIVATE_TOKEN);
        assertNotNull(TEST_PRIVATE_TOKEN);
        assertFalse(TEST_PRIVATE_TOKEN.trim().isEmpty());
        HelperUtils.setProperty(PRIVATE_TOKEN_KEY, TEST_PRIVATE_TOKEN);

        TEST_ACCESS_TOKEN = AccessTokenUtils.createPersonalAccessToken(
                TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD, 
                "GitLab4J Test Access Token", Arrays.asList("api", "sudo"));
        System.out.println("Created private token: " + TEST_ACCESS_TOKEN);
        assertNotNull(TEST_ACCESS_TOKEN);
        assertFalse(TEST_ACCESS_TOKEN.trim().isEmpty());
        HelperUtils.setProperty(ACCESS_TOKEN_KEY, TEST_ACCESS_TOKEN);        
    }
}
