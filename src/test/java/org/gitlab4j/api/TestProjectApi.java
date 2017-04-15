package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * In order for these tests to run you must set the following systems properties:
 * 
 * TEST_NAMESPACE
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped. If running from mvn simply
 * use a command line similar to:
 * 
 * mvn test -DTEST_PRIVATE_TOKEN=your_private_token -DTEST_HOST_URL=https://gitlab.com \
 * -DTEST_NAMESPACE=your_namespace
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectApi {

    // The following needs to be set to your test repository
    
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_NAMESPACE = System.getProperty("TEST_NAMESPACE");
        TEST_HOST_URL = System.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = System.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static final String TEST_PROJECT_NAME = "test-gitlab4j-create-project";
    private static GitLabApi gitLabApi;

    public TestProjectApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().length() == 0) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().length() == 0) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().length() == 0) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }
        
        if (gitLabApi != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
                gitLabApi.getProjectApi().deleteProject(project);
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        if (gitLabApi != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
                gitLabApi.getProjectApi().deleteProject(project);
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testCreate() throws GitLabApiException {

        Project project = new Project()
                .withName(TEST_PROJECT_NAME)
                .withDescription("GitLab4J test project.")
                .withIssuesEnabled(true)
                .withMergeRequestsEnabled(true)
                .withWikiEnabled(true)
                .withSnippetsEnabled(true)
                .withPublic(true);

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);
        assertEquals(TEST_PROJECT_NAME, newProject.getName());
        assertEquals(project.getDescription(), newProject.getDescription());
        assertEquals(project.getIssuesEnabled(), newProject.getIssuesEnabled());
        assertEquals(project.getMergeRequestsEnabled(), newProject.getMergeRequestsEnabled());
        assertEquals(project.getWikiEnabled(), newProject.getWikiEnabled());
        assertEquals(project.getSnippetsEnabled(), newProject.getSnippetsEnabled());
        assertEquals(project.getPublic(), newProject.getPublic());
    }

    @Test
    public void testDelete() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        gitLabApi.getProjectApi().deleteProject(project);
    }
}
