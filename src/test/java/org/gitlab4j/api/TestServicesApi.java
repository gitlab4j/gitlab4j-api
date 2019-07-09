package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.services.BugzillaService;
import org.gitlab4j.api.services.CustomIssueTrackerService;
import org.gitlab4j.api.services.ExternalWikiService;
import org.gitlab4j.api.services.JiraService;
import org.gitlab4j.api.services.MattermostService;
import org.gitlab4j.api.services.SlackService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestServicesApi extends AbstractIntegrationTest {

    private static final String TEST_ENDPOINT = "https://foobar.com/gitlab_service/webhooks/";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestServicesApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        if (testProject != null) {
            try { gitLabApi.getServicesApi().deleteJiraService(testProject); } catch (Exception ignore) {}
            try { gitLabApi.getServicesApi().deleteSlackService(testProject); } catch (Exception ignore) {}
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(testProject);
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

        // Make sure the jira_issue_transition_id is retrievable.
        // This is testing that a class cast exception is not thrown.
        Integer jiraIssueTransitionId = jiraService.getJiraIssueTransitionId();
        assertTrue(jiraIssueTransitionId == null || jiraIssueTransitionId != null);
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

    @Test
    public void testGetExternalWiki() throws GitLabApiException {
        ExternalWikiService wikiService =  gitLabApi.getServicesApi().getExternalWikiService(testProject);
        assertNotNull(wikiService);
    }

    @Test
    public void testUpdateExternalWiki() throws GitLabApiException {
        try {
            ExternalWikiService wikiService =  new ExternalWikiService()
                    .withExternalWikiUrl("http://wiki.io");
            ExternalWikiService updatedExternalWikiService =  gitLabApi.getServicesApi().updateExternalWikiService(testProject, wikiService);
            assertNotNull(updatedExternalWikiService);
        } finally {
            try { gitLabApi.getServicesApi().deleteExternalWikiService(testProject); } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDeleteExternalWikiService() throws GitLabApiException {
        ExternalWikiService wikiService =  new ExternalWikiService()
                .withExternalWikiUrl("http://wiki.io");
        ExternalWikiService updatedExternalWikiService =  gitLabApi.getServicesApi().updateExternalWikiService(testProject, wikiService);
        assertNotNull(updatedExternalWikiService);
        assertTrue(updatedExternalWikiService.getActive());

        gitLabApi.getServicesApi().deleteExternalWikiService(testProject);
        ExternalWikiService deleteExternalWikiService =  gitLabApi.getServicesApi().getExternalWikiService(testProject);
        assertNotNull(deleteExternalWikiService);
        assertFalse(deleteExternalWikiService.getActive());
    }

    @Test
    public void testGetMattermostService() throws GitLabApiException {
        MattermostService service =  gitLabApi.getServicesApi().getMattermostService(testProject);
        assertNotNull(service);
    }

    @Test
    public void testUpdateMattermostService() throws GitLabApiException {

        try {
            MattermostService service =  new MattermostService()
                    .withMergeRequestsEvents(true)
                    .withWebhook("https://hooks.mattermost.com/services/ABCDEFGHI/KJLMNOPQR/wetrewq7897HKLH8998wfjjj")
                    .withUsername("GitLab4J");
            MattermostService updatedService =  gitLabApi.getServicesApi().updateMattermostService(testProject, service);
            assertNotNull(updatedService);
            assertEquals(service.getWebhook(), updatedService.getWebhook());
        } finally {
            try { gitLabApi.getServicesApi().deleteMattermostService(testProject); } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDeleteMattermostService() throws GitLabApiException {
        MattermostService service =  new MattermostService()
                .withMergeRequestsEvents(true)
                .withWebhook("https://hooks.mattermost.com/services/ABCDEFGHI/KJLMNOPQR/wetrewq7897HKLH8998wfjjj")
                .withUsername("GitLab4J");
        MattermostService updatedService =  gitLabApi.getServicesApi().updateMattermostService(testProject, service);
        assertNotNull(updatedService);
        assertTrue(updatedService.getActive());

        gitLabApi.getServicesApi().deleteMattermostService(testProject);
        MattermostService deleteMattermostService =  gitLabApi.getServicesApi().getMattermostService(testProject);
        assertNotNull(deleteMattermostService);
        assertFalse(deleteMattermostService.getActive());
    }

    @Test
    public void testGetBugzillaService() throws GitLabApiException {
        BugzillaService service =  gitLabApi.getServicesApi().getBugzillaService(testProject);
        assertNotNull(service);
    }

    @Test
    public void testUpdateBugzillaService() throws GitLabApiException {

        try {

            BugzillaService service =  new BugzillaService()
                    .withIssuesUrl(TEST_ENDPOINT + "issues")
                    .withNewIssueUrl(TEST_ENDPOINT + "new_issue")
                    .withProjectUrl(TEST_ENDPOINT + "project");
            BugzillaService updatedService =  gitLabApi.getServicesApi().updateBugzillaService(testProject, service);
            assertNotNull(updatedService);
            assertEquals(service.getIssuesUrl(), updatedService.getIssuesUrl());
            assertEquals(service.getNewIssueUrl(), updatedService.getNewIssueUrl());
            assertEquals(service.getProjectUrl(), updatedService.getProjectUrl());

        } finally {
            try { gitLabApi.getServicesApi().deleteBugzillaService(testProject); } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDeleteBugzillaService() throws GitLabApiException {

        BugzillaService service =  new BugzillaService()
                .withIssuesUrl(TEST_ENDPOINT + "issues")
                .withNewIssueUrl(TEST_ENDPOINT + "new_issue")
                .withProjectUrl(TEST_ENDPOINT + "project");
        BugzillaService updatedService =  gitLabApi.getServicesApi().updateBugzillaService(testProject, service);
        assertNotNull(updatedService);
        assertTrue(updatedService.getActive());

        gitLabApi.getServicesApi().deleteBugzillaService(testProject);
        BugzillaService deletedService =  gitLabApi.getServicesApi().getBugzillaService(testProject);
        assertNotNull(deletedService);
        assertFalse(deletedService.getActive());
    }

    @Test
    public void testGetCustomIssueTrackerService() throws GitLabApiException {
        CustomIssueTrackerService service =  gitLabApi.getServicesApi().getCustomIssueTrackerService(testProject);
        assertNotNull(service);
    }

    @Test
    public void testUpdateCustomIssueTrackerService() throws GitLabApiException {

        try {

            CustomIssueTrackerService service =  new CustomIssueTrackerService()
                    .withIssuesUrl(TEST_ENDPOINT + "issues")
                    .withNewIssueUrl(TEST_ENDPOINT + "new_issue")
                    .withProjectUrl(TEST_ENDPOINT + "project");
            CustomIssueTrackerService updatedService =  gitLabApi.getServicesApi().updateCustomIssueTrackerService(testProject, service);
            assertNotNull(updatedService);
            assertEquals(service.getIssuesUrl(), updatedService.getIssuesUrl());
            assertEquals(service.getNewIssueUrl(), updatedService.getNewIssueUrl());
            assertEquals(service.getProjectUrl(), updatedService.getProjectUrl());

        } finally {
            try { gitLabApi.getServicesApi().deleteCustomIssueTrackerService(testProject); } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDeleteCustomIssueTrackerService() throws GitLabApiException {

        CustomIssueTrackerService service =  new CustomIssueTrackerService()
                .withIssuesUrl(TEST_ENDPOINT + "issues")
                .withNewIssueUrl(TEST_ENDPOINT + "new_issue")
                .withProjectUrl(TEST_ENDPOINT + "project");
        CustomIssueTrackerService updatedService =  gitLabApi.getServicesApi().updateCustomIssueTrackerService(testProject, service);
        assertNotNull(updatedService);
        assertTrue(updatedService.getActive());

        gitLabApi.getServicesApi().deleteCustomIssueTrackerService(testProject);
        CustomIssueTrackerService deletedService =  gitLabApi.getServicesApi().getCustomIssueTrackerService(testProject);
        assertNotNull(deletedService);
        assertFalse(deletedService.getActive());
    }
}
