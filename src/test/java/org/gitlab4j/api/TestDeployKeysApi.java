package org.gitlab4j.api;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 *
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * TEST_USERNAME
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestDeployKeysApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_USERNAME = HelperUtils.getProperty(USERNAME_KEY);

    private static GitLabApi gitLabApi;

    public TestDeployKeysApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        if (TEST_USERNAME == null || TEST_USERNAME.trim().isEmpty()) {
            System.err.println("TEST_USER_NAME cannot be empty");
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }
}
