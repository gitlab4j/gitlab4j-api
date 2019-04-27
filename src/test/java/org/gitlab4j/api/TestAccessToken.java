package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeNotNull;

import org.gitlab4j.api.Constants.TokenType;
import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Version;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
@Category(IntegrationTest.class)
public class TestAccessToken extends AbstractIntegrationTest {

    // TEST_ACCESS_TOKEN must be defined to run this test
    private static final String TEST_ACCESS_TOKEN = TestUtils.getProperty("TEST_ACCESS_TOKEN");
    private static GitLabApi gitLabApi;

    public TestAccessToken() {
        super();
    }

    @BeforeClass
    public static void testSetup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        if (TEST_ACCESS_TOKEN == null || TEST_ACCESS_TOKEN.trim().isEmpty()) {
            System.err.println("TEST_ACCESS_TOKEN cannot be empty");
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
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
        assumeNotNull(TEST_ACCESS_TOKEN);
        GitLabApi gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TokenType.ACCESS, TEST_ACCESS_TOKEN);
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("tokenType: %s, version=%s, revision=%s%n", TokenType.ACCESS, gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());
    }
}
