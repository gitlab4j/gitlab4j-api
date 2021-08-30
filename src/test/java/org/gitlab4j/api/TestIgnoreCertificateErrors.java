package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestIgnoreCertificateErrors implements PropertyConstants {

    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    private static final String TEST_PRIVATE_TOKEN = HelperUtils.getProperty(PRIVATE_TOKEN_KEY);

    private static boolean setupOk;

    public TestIgnoreCertificateErrors() {
        super();
    }

    @BeforeAll
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

    @BeforeEach
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
        gitLabApi.close();

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

        gitLabApi.close();
    }
}
