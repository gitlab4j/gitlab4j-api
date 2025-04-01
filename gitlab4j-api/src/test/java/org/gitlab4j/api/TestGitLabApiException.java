package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import jakarta.ws.rs.core.Response.Status;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;
import org.junit.jupiter.api.Test;

public class TestGitLabApiException {

    private static final String TEST_PROJECT_NAME_DUPLICATE = "test-gitlab4j-create-project-duplicate";
    private static final String TEST_ERROR_MESSAGE =
            "Another open merge request already exists for this source branch: !6";
    private static final String TEST_RESPONSE_JSON_STRING = "{\"message\": \"" + TEST_ERROR_MESSAGE + "\"}";
    private static final String TEST_RESPONSE_JSON_ARRAY = "{\"message\": [\"" + TEST_ERROR_MESSAGE + "\"]}";
    private static final String TEST_RESPONSE_ERROR_JSON_STRING = "{\"error\": \"" + TEST_ERROR_MESSAGE + "\"}";

    private static GitLabApi gitLabApi;

    //    public TestGitLabApiException() {
    //        super();
    //    }
    //
    //    @BeforeAll
    //    public static void setup() {
    //        // Must setup the connection to the GitLab test server
    //        gitLabApi = baseTestSetup();
    //
    //        deleteAllTestProjects();
    //    }
    //
    //    @AfterAll
    //    public static void teardown() throws GitLabApiException {
    //        deleteAllTestProjects();
    //    }
    //
    //    private static void deleteAllTestProjects() {
    //        if (gitLabApi != null) {
    //            try {
    //                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE,
    // TEST_PROJECT_NAME_DUPLICATE);
    //                gitLabApi.getProjectApi().deleteProject(project);
    //            } catch (GitLabApiException ignore) {
    //            }
    //        }
    //    }
    //
    //    @BeforeEach
    //    public void beforeMethod() {
    //        assumeTrue(gitLabApi != null);
    //    }

    @Test
    @org.junit.jupiter.api.Disabled(
            "Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
    public void testNotFoundError() throws GitLabApiException {

        try {
            gitLabApi.getProjectApi().getProject(123456789L);
            fail("GitLabApiException not thrown");
        } catch (GitLabApiException gae) {
            assertFalse(gae.hasValidationErrors());
            assertEquals(404, gae.getHttpStatus());
            assertTrue(gae.getMessage().contains("404"));
            assertFalse(gae.getHeaders().isEmpty());
            assertTrue(
                    gae.getHeaders().containsKey("X-Request-Id"),
                    () -> "headers contains key 'X-Request-Id'. Available keys: "
                            + String.join(", ", gae.getHeaders().keySet()));
        }
    }

    @Test
    @org.junit.jupiter.api.Disabled(
            "Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
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
    public void testObjectMessage() throws GitLabApiException {
        String expectedMessage = ""
                + "The following fields have validation errors: project_namespace.name, name\n"
                + "* project_namespace.name\n"
                + "     - has already been taken\n"
                + "* name\n"
                + "     - has already been taken";
        final MockResponse response = new MockResponse(
                Status.BAD_REQUEST,
                "{\"message\":{\"project_namespace.name\":[\"has already been taken\"],\"name\":[\"has already been taken\"]}}");
        GitLabApiException glae = new GitLabApiException(response);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), glae.getHttpStatus());
        assertEquals(expectedMessage, glae.getMessage());
        assertEquals(2, glae.getValidationErrors().size());
        assertEquals(1, glae.getValidationErrors().get("project_namespace.name").size());
        assertEquals(
                "has already been taken",
                glae.getValidationErrors().get("project_namespace.name").get(0));
        assertEquals(1, glae.getValidationErrors().get("name").size());
        assertEquals(
                "has already been taken", glae.getValidationErrors().get("name").get(0));
    }

    @Test
    public void testObjectMessage1() throws GitLabApiException {
        String message = "{\n"
                + "    \"message\":{\n"
                + "        \"f1\":[\n"
                + "            \"m1\",\n"
                + "            \"m2\"\n"
                + "        ],\n"
                + "        \"f2\":[\n"
                + "            \"n1\",\n"
                + "            \"n2\",\n"
                + "            \"n3\"\n"
                + "        ]\n"
                + "    }\n"
                + "}";
        String expectedMessage = ""
                + "The following fields have validation errors: f1, f2\n"
                + "* f1\n"
                + "     - m1\n"
                + "     - m2\n"
                + "* f2\n"
                + "     - n1\n"
                + "     - n2\n"
                + "     - n3";
        final MockResponse response = new MockResponse(Status.BAD_REQUEST, message);
        GitLabApiException glae = new GitLabApiException(response);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), glae.getHttpStatus());
        assertEquals(expectedMessage, glae.getMessage());
        assertEquals(2, glae.getValidationErrors().size());
        assertEquals(2, glae.getValidationErrors().get("f1").size());
        assertEquals("m1", glae.getValidationErrors().get("f1").get(0));
        assertEquals("m2", glae.getValidationErrors().get("f1").get(1));
        assertEquals(3, glae.getValidationErrors().get("f2").size());
        assertEquals("n1", glae.getValidationErrors().get("f2").get(0));
        assertEquals("n2", glae.getValidationErrors().get("f2").get(1));
        assertEquals("n3", glae.getValidationErrors().get("f2").get(2));
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
