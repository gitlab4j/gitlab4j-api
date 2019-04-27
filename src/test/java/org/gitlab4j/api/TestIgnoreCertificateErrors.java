package org.gitlab4j.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

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
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
@Category(IntegrationTest.class)
public class TestIgnoreCertificateErrors {

    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static boolean setupOk;

    public TestIgnoreCertificateErrors() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            setupOk = true;
        } else {
            setupOk = false;
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(setupOk);
    }

    @Test
    public void testGetVersion() throws GitLabApiException {

        GitLabApi gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        assertFalse(gitLabApi.getIgnoreCertificateErrors());
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("ignoreCertErrors: %b, version=%s, revision=%s%n", gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());

        gitLabApi.setIgnoreCertificateErrors(true);
        assertTrue(gitLabApi.getIgnoreCertificateErrors());
        version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("ignoreCertErrors:  %b, version=%s, revision=%s%n", gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());

        gitLabApi.setIgnoreCertificateErrors(false);
        assertFalse(gitLabApi.getIgnoreCertificateErrors());
        version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("ignoreCertErrors: %b, version=%s, revision=%s%n", gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());

        gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        gitLabApi.setIgnoreCertificateErrors(true);
        assertTrue(gitLabApi.getIgnoreCertificateErrors());
        version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("ignoreCertErrors:  %b, version=%s, revision=%s%n", gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());

        gitLabApi.setIgnoreCertificateErrors(false);
        assertFalse(gitLabApi.getIgnoreCertificateErrors());
        version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("ignoreCertErrors: %b, version=%s, revision=%s%n", gitLabApi.getIgnoreCertificateErrors(), version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());
    }
}
