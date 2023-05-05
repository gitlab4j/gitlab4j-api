package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 *
 * TEST_NAMESPACE
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestGitLabApiException extends AbstractIntegrationTest {

    private static final String TEST_PROJECT_NAME_DUPLICATE = "test-gitlab4j-create-project-duplicate";
    private static final String TEST_ERROR_MESSAGE = "Another open merge request already exists for this source branch: !6";
    private static final String TEST_RESPONSE_JSON_STRING = "{\"message\": \"" + TEST_ERROR_MESSAGE + "\"}";
    private static final String TEST_RESPONSE_JSON_ARRAY = "{\"message\": [\"" + TEST_ERROR_MESSAGE + "\"]}";
    private static final String TEST_RESPONSE_ERROR_JSON_STRING = "{\"error\": \"" + TEST_ERROR_MESSAGE + "\"}";


    private static GitLabApi gitLabApi;

    public TestGitLabApiException() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        deleteAllTestProjects();
    }

    @AfterAll
    public static void teardown() throws GitLabApiException {
        deleteAllTestProjects();
    }

    private static void deleteAllTestProjects() {
        if (gitLabApi != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME_DUPLICATE);
                gitLabApi.getProjectApi().deleteProject(project);
            } catch (GitLabApiException ignore) {}
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testNotFoundError() throws GitLabApiException {

        try {
            gitLabApi.getProjectApi().getProject(123456789L);
            fail("GitLabApiException not thrown");
        } catch (GitLabApiException gae) {
            assertFalse(gae.hasValidationErrors());
            assertEquals(404, gae.getHttpStatus());
            assertTrue(gae.getMessage().contains("404"));
            assertFalse(gae.getHeaders().isEmpty());
            assertTrue(gae.getHeaders().containsKey("X-Request-Id"), () -> "headers contains key 'X-Request-Id'. Available keys: " + String.join(", ", gae.getHeaders().keySet()));
        }
    }

    @Test
    public void testValidationErrors() throws GitLabApiException {

        Project project = new Project()
                .withName(TEST_PROJECT_NAME_DUPLICATE)
                .withDescription("GitLab4J test project.")
                .withVisibility(Visibility.PUBLIC);

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);

        try {
            newProject = gitLabApi.getProjectApi().createProject(project);
            fail("GitLabApiException not thrown");
        } catch (GitLabApiException gae) {
            assertTrue(gae.hasValidationErrors());
            Map<String, List<String>> validationErrors = gae.getValidationErrors();
            assertNotNull(validationErrors);
            assertFalse(validationErrors.isEmpty());
        }
    }

    @Test
    public void testStringMessage() throws GitLabApiException {
        final MockResponse response = new MockResponse(Status.BAD_REQUEST, TEST_RESPONSE_JSON_STRING);
        GitLabApiException glae = new GitLabApiException(response);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), glae.getHttpStatus());
        assertEquals(TEST_ERROR_MESSAGE, glae.getMessage());
    }

    @Test
    public void testArrayMessage() throws GitLabApiException {
        final MockResponse response = new MockResponse(Status.BAD_REQUEST, TEST_RESPONSE_JSON_ARRAY);
        GitLabApiException glae = new GitLabApiException(response);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), glae.getHttpStatus());
        assertEquals(TEST_ERROR_MESSAGE, glae.getMessage());
    }

    @Test
    public void testError() throws GitLabApiException {
        final MockResponse response = new MockResponse(Status.BAD_REQUEST, TEST_RESPONSE_ERROR_JSON_STRING);
        GitLabApiException glae = new GitLabApiException(response);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), glae.getHttpStatus());
        assertEquals(TEST_ERROR_MESSAGE, glae.getMessage());
    }
}
