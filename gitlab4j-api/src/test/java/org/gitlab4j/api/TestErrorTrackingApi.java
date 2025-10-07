package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.gitlab4j.api.models.ErrorTrackingClientKey;
import org.gitlab4j.api.models.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * <p>
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@org.junit.jupiter.api.Disabled(
        "Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
public class TestErrorTrackingApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    @BeforeAll
    public static void setup() {
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateClientKey() throws GitLabApiException {
        assertNotNull(testProject);
        Long projectId = testProject.getId();
        ErrorTrackingClientKey errorTrackingClientKey =
                gitLabApi.getErrorTrackingApi().createClientKey(projectId);
        assertNotNull(errorTrackingClientKey);
    }

    @Test
    public void testGetClientKeys() throws GitLabApiException {
        assertNotNull(testProject);
        Long projectId = testProject.getId();
        ErrorTrackingClientKey errorTrackingClientKey =
                gitLabApi.getErrorTrackingApi().createClientKey(projectId);
        List<ErrorTrackingClientKey> clientKeys =
                gitLabApi.getErrorTrackingApi().getClientKeys(projectId);
        assertEquals(errorTrackingClientKey, clientKeys.get(0));
    }
}
