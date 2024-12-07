package org.gitlab4j.api;

import static org.gitlab4j.api.TestIssuesApi.deleteAllTestIssues;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.IssueEvent;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.models.Constants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@org.junit.jupiter.api.Disabled(
        "Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
public class TestResourceStateEventsApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    private static final String ISSUE_TITLE = "Test Issue Title";
    private static final String ISSUE_DESCRIPTION = "This is a really nice description, not.";

    public TestResourceStateEventsApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @AfterAll
    public static void teardown() {
        deleteAllTestIssues();
    }

    @Disabled("should be enabled when CI tests will be run against GitLab 13.2+")
    @Test
    public void testGetCloseReopenIssueEvents() throws GitLabApiException {
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, ISSUE_TITLE, ISSUE_DESCRIPTION);

        Issue closedIssue = gitLabApi.getIssuesApi().closeIssue(projectId, issue.getIid());
        assertEquals(closedIssue.getState(), Constants.IssueState.CLOSED);

        List<IssueEvent> issueEvents =
                gitLabApi.getResourceStateEventsApi().getIssueStateEvents(projectId, issue.getIid());
        assertNotNull(issueEvents);
        assertEquals(1, issueEvents.size());

        assertEquals(
                1,
                issueEvents.stream()
                        .filter(issueEvent -> issueEvent.getState().equals(Constants.IssueState.CLOSED.toValue()))
                        .count());

        Issue reopenedIssue = gitLabApi
                .getIssuesApi()
                .updateIssue(
                        projectId,
                        issue.getIid(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        Constants.StateEvent.REOPEN,
                        null,
                        null);
        assertEquals(
                Constants.IssueState.OPENED.toValue(), reopenedIssue.getState().toValue());

        issueEvents = gitLabApi.getResourceStateEventsApi().getIssueStateEvents(projectId, issue.getIid());
        assertNotNull(issueEvents);
        assertEquals(2, issueEvents.size());

        assertEquals(
                1,
                issueEvents.stream()
                        .filter(issueEvent -> issueEvent.getState().equals(Constants.IssueState.CLOSED.toValue()))
                        .count());
        assertEquals(
                1,
                issueEvents.stream()
                        .filter(issueEvent -> issueEvent.getState().equals(Constants.IssueState.REOPENED.toValue()))
                        .count());
    }
}
