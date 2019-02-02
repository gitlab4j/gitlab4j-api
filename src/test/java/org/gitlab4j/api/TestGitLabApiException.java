package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
public class TestGitLabApiException {

    // The following needs to be set to your test repository
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;


    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static final String TEST_PROJECT_NAME_DUPLICATE = "test-gitlab4j-create-project-duplicate";
    private static GitLabApi gitLabApi;

    public TestGitLabApiException() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }

        deleteAllTestProjects();
    }

    @AfterClass
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

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testNotFoundError() throws GitLabApiException {

        try {
            gitLabApi.getProjectApi().getProject(123456789);
            fail("GitLabApiException not thrown");
        } catch (GitLabApiException gae) {
            assertFalse(gae.hasValidationErrors());
            assertEquals(404, gae.getHttpStatus());
            assertTrue(gae.getMessage().contains("404"));
        }
    }

    @Test
    public void testValidationErrors() throws GitLabApiException {

        Project project = new Project()
                .withName(TEST_PROJECT_NAME_DUPLICATE)
                .withDescription("GitLab4J test project.")
                .withIssuesEnabled(true)
                .withMergeRequestsEnabled(true)
                .withWikiEnabled(true)
                .withSnippetsEnabled(true)
                .withVisibility(Visibility.PUBLIC)
                .withTagList(Arrays.asList("tag1", "tag2"));

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
}
