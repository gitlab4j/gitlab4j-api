package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.services.JiraService;
import org.gitlab4j.api.services.SlackService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServicesApi {

    // The following needs to be set to your test repository
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestServicesApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().isEmpty()) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);

            try {
                testProject = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
                try { gitLabApi.getServicesApi().deleteJiraService(testProject); } catch (Exception ignore) {}
                try { gitLabApi.getServicesApi().deleteSlackService(testProject); } catch (Exception ignore) {}
            } catch (GitLabApiException gle) {
                System.err.print(gle.getMessage());
            }

        } else {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null && testProject != null);
    }

    @Test
    public void testProjectIdOrPath() throws GitLabApiException {
        Integer projectId = testProject.getId();
        JiraService jiraServiceById =  gitLabApi.getServicesApi().getJiraService(projectId);
        assertNotNull(jiraServiceById);
        JiraService jiraServiceByPath =  gitLabApi.getServicesApi().getJiraService(testProject.getPathWithNamespace());
        assertNotNull(jiraServiceByPath);
        JiraService jiraServiceByProject =  gitLabApi.getServicesApi().getJiraService(testProject);
        assertNotNull(jiraServiceByProject);
        
        assertEquals(jiraServiceById.getTitle(), jiraServiceByPath.getTitle());
        assertEquals(jiraServiceById.getTitle(), jiraServiceByProject.getTitle());
    }

    @Test
    public void testGetJiraService() throws GitLabApiException {
        JiraService jiraService =  gitLabApi.getServicesApi().getJiraService(testProject);
        assertNotNull(jiraService);
    }

    @Test
    public void testUpdateJiraService() throws GitLabApiException {
        
        try {
            JiraService jiraService =  new JiraService()
                    .withCommitEvents(true)
                    .withMergeRequestsEvents(true)
                    .withUrl("http://jira.example.com")
                    .withUsername("GitLab4J")
                    .withPassword("test")
                    .withProjectKey("GL4J");
            JiraService updatedJiraService =  gitLabApi.getServicesApi().updateJiraService(testProject, jiraService);
            assertNotNull(updatedJiraService);
        } finally {
            try { gitLabApi.getServicesApi().deleteJiraService(testProject); } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDeleteJiraService() throws GitLabApiException {

        JiraService jiraService =  new JiraService()
                .withCommitEvents(true)
                .withMergeRequestsEvents(true)
                .withUrl("http://jira.example.com")
                .withUsername("GitLab4J")
                .withPassword("test")
                .withProjectKey("GL4J");
        JiraService updatedJiraService =  gitLabApi.getServicesApi().updateJiraService(testProject, jiraService);
        assertNotNull(updatedJiraService);
        assertTrue(updatedJiraService.getActive());

        gitLabApi.getServicesApi().deleteJiraService(testProject);
        JiraService deleteJiraService =  gitLabApi.getServicesApi().getJiraService(testProject);
        assertNotNull(deleteJiraService);
        assertFalse(deleteJiraService.getActive());
    }

    @Test
    public void testGetSlackService() throws GitLabApiException {
        SlackService slackService =  gitLabApi.getServicesApi().getSlackService(testProject);
        assertNotNull(slackService);
    }

    @Test
    public void testUpdateSlackService() throws GitLabApiException {
 
        try {
            SlackService slackService =  new SlackService()
                    .withMergeRequestsEvents(true)
                    .withWebhook("https://hooks.slack.com/services/ABCDEFGHI/KJLMNOPQR/wetrewq7897HKLH8998wfjjj")
                    .withUsername("GitLab4J");
            SlackService updatedSlackService =  gitLabApi.getServicesApi().updateSlackService(testProject, slackService);
            assertNotNull(updatedSlackService);
        } finally {
            try { gitLabApi.getServicesApi().deleteSlackService(testProject); } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDeleteSlackService() throws GitLabApiException {
        SlackService slackService =  new SlackService()
                .withMergeRequestsEvents(true)
                .withWebhook("https://hooks.slack.com/services/ABCDEFGHI/KJLMNOPQR/wetrewq7897HKLH8998wfjjj")
                .withUsername("GitLab4J");
        SlackService updatedSlackService =  gitLabApi.getServicesApi().updateSlackService(testProject, slackService);
        assertNotNull(updatedSlackService);
        assertTrue(updatedSlackService.getActive());

        gitLabApi.getServicesApi().deleteSlackService(testProject);
        SlackService deleteSlackService =  gitLabApi.getServicesApi().getSlackService(testProject);
        assertNotNull(deleteSlackService);
        assertFalse(deleteSlackService.getActive());
    }
}
