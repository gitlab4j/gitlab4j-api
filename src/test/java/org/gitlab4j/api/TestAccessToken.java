package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.gitlab4j.api.Constants.TokenType;
import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Version;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 *
 * TEST_HOST_URL
 * TEST_ACCESS_TOKEN
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestAccessToken extends AbstractIntegrationTest {

    // TEST_ACCESS_TOKEN must be defined to run this test
    private static final String TEST_ACCESS_TOKEN = HelperUtils.getProperty("TEST_ACCESS_TOKEN");
    private static GitLabApi gitLabApi;

    public TestAccessToken() {
        super();
    }

    @BeforeAll
    public static void testSetup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        if (TEST_ACCESS_TOKEN == null || TEST_ACCESS_TOKEN.trim().isEmpty()) {
            System.err.println("TEST_ACCESS_TOKEN cannot be empty");
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testPrivateToken() throws GitLabApiException {

        // This test uses the GitLabApi instance created in setup()
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("tokenType: %s, version=%s, revision=%s%n", TokenType.PRIVATE, gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());
    }

    @Test
    public void testAccessToken() throws GitLabApiException {
        assumeTrue(TEST_ACCESS_TOKEN != null);
        GitLabApi gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TokenType.ACCESS, TEST_ACCESS_TOKEN);
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("tokenType: %s, version=%s, revision=%s%n", TokenType.ACCESS, gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());

        gitLabApi.close();
    }
}
