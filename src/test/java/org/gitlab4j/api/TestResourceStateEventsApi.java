package org.gitlab4j.api;

import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.IssueEvent;
import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.gitlab4j.api.TestIssuesApi.deleteAllTestIssues;
import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class TestResourceStateEventsApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    private static final String ISSUE_TITLE = "Test Issue Title";
    private static final String ISSUE_DESCRIPTION = "This is a really nice description, not.";

    public TestResourceStateEventsApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @AfterClass
    public static void teardown() {
        deleteAllTestIssues();
    }

    @Test
    public void testGetCloseReopenIssueEvents() throws GitLabApiException {
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, ISSUE_TITLE, ISSUE_DESCRIPTION);

        Issue closedIssue = gitLabApi.getIssuesApi().closeIssue(projectId, issue.getIid());
        assertEquals(closedIssue.getState(), Constants.IssueState.CLOSED);

        List<IssueEvent> issueEvents = gitLabApi.getResourceStateEventsApi().getIssueStateEvents(projectId, issue.getIid());
        assertNotNull(issueEvents);
        assertEquals(1, issueEvents.size());

        assertEquals(1, issueEvents.stream()
            .filter(issueEvent -> issueEvent.getState().equals(Constants.IssueState.CLOSED.toValue()))
            .count());

        Issue reopenedIssue = gitLabApi.getIssuesApi()
            .updateIssue(projectId,
                issue.getIid(),
                null, null, null, null, null, null,
                Constants.StateEvent.REOPEN,
                null, null);
        assertEquals(Constants.IssueState.OPENED.toValue(), reopenedIssue.getState().toValue());

        issueEvents = gitLabApi.getResourceStateEventsApi().getIssueStateEvents(projectId, issue.getIid());
        assertNotNull(issueEvents);
        assertEquals(2, issueEvents.size());

        assertEquals(1, issueEvents.stream()
            .filter(issueEvent -> issueEvent.getState().equals(Constants.IssueState.CLOSED.toValue()))
            .count());
        assertEquals(1, issueEvents.stream()
            .filter(issueEvent -> issueEvent.getState().equals(Constants.IssueState.REOPENED.toValue()))
            .count());
    }

}
