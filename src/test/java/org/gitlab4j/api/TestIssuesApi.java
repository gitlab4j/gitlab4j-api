/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import jakarta.ws.rs.core.Response;

import org.gitlab4j.api.Constants.IssueState;
import org.gitlab4j.api.models.Duration;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.IssueFilter;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.TimeStats;
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
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestIssuesApi extends AbstractIntegrationTest  {

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static Group testGroup;

    private static final String ISSUE_TITLE = "Test Issue Title";
    private static final String ISSUE_DESCRIPTION = "This is a really nice description, not.";
    private static final String ITERATION_TITLE = "iteration title";
    private static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);
    private static Random randomNumberGenerator = new Random();

    public TestIssuesApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        if (gitLabApi != null) {
            Optional<Group> group = gitLabApi.getGroupApi().getOptionalGroup(TEST_GROUP);
            testGroup = group.get();
        }

        deleteAllTestIssues();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @AfterAll
    public static void teardown() throws GitLabApiException {
        deleteAllTestIssues();
    }

    public static void deleteAllTestIssues() {

        if (gitLabApi != null) {
            try {
                List<Issue> issues = gitLabApi.getIssuesApi().getIssues(testProject.getId());
                if (issues != null) {

                    for (Issue issue : issues) {
                        if (issue.getTitle().startsWith(ISSUE_TITLE)) {
                            gitLabApi.getIssuesApi().deleteIssue(testProject.getId(), issue.getIid());
                        }
                    }
                }
            } catch (GitLabApiException ignore) {
            }
        }
    }

    private static String getUniqueTitle() {
        return (ISSUE_TITLE + " - " + (randomNumberGenerator.nextInt() + 1));
    }

    @Test
    public void testGetIssue() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        Issue foundIssue = gitLabApi.getIssuesApi().getIssue(projectId, issue.getIid());
        assertNotNull(foundIssue);
        assertEquals(issue.getIid(), foundIssue.getIid());
    }

    @Test
    public void testGetIssues() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        List<Issue> issues = gitLabApi.getIssuesApi().getIssues(projectId);
        assertNotNull(issues);

        // Make sure the issue just created is returned
        boolean found = false;
        for (Issue item : issues) {
            if (item.getId().equals(issue.getId())) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void testGetGroupIssues() throws GitLabApiException {
        assumeTrue(testGroup != null);
        List<Issue> issues = gitLabApi.getIssuesApi().getGroupIssues(testGroup);
        assertNotNull(issues);
    }

    @Test
    public void testCreateIssue() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();
        String title = getUniqueTitle();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, title, ISSUE_DESCRIPTION);
        assertNotNull(issue);
        assertEquals(title, issue.getTitle());
        assertEquals(ISSUE_DESCRIPTION, issue.getDescription());
        assertEquals(IssueState.OPENED, issue.getState());

        List<Long> assigneeIds = Arrays.asList(issue.getAuthor().getId());
        issue = gitLabApi.getIssuesApi().createIssue(projectId, title, ISSUE_DESCRIPTION, null, assigneeIds, null, null, new Date(), null, null, null);
        assertNotNull(issue);
        assertEquals(title, issue.getTitle());
        assertEquals(ISSUE_DESCRIPTION, issue.getDescription());
        assertEquals(IssueState.OPENED, issue.getState());
    }

    @Test
    public void testCloseIssueJustCreated() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);

        Issue closedIssue = gitLabApi.getIssuesApi().closeIssue(projectId, issue.getIid());
        assertNotNull(closedIssue);
        assertEquals(IssueState.CLOSED, closedIssue.getState());
        assertEquals(issue.getId(), closedIssue.getId());
    }

    @Test
    public void testCloseIssueClosedAt() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        assertNull(issue.getClosedAt());
        assertNull(issue.getClosedBy());

        Issue closedIssue = gitLabApi.getIssuesApi().closeIssue(projectId, issue.getIid());
        assertNotNull(closedIssue);
        assertEquals(IssueState.CLOSED, closedIssue.getState());
        assertEquals(issue.getId(), closedIssue.getId());

        closedIssue = gitLabApi.getIssuesApi().getIssue(projectId, issue.getIid());
        assertNotNull(closedIssue);
        assertEquals(IssueState.CLOSED, closedIssue.getState());
        assertNotNull(closedIssue.getClosedAt());
    }

    @Test
    public void testDeleteIssue() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        gitLabApi.getIssuesApi().deleteIssue(projectId, issue.getIid());

        try {
            issue = gitLabApi.getIssuesApi().getIssue(projectId, issue.getIid());
            assertNull(issue);
        } catch (GitLabApiException gle) {
            assertEquals(Response.Status.NOT_FOUND.getStatusCode(), gle.getHttpStatus());
        }
    }

    /**
     * Simplify creation of issues
     *
     * @return
     * @throws GitLabApiException
     */
    private Issue ensureIssue() throws GitLabApiException {
        Long projectId = testProject.getId();
        String title = getUniqueTitle();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, title, ISSUE_DESCRIPTION);

        return issue;
    }

    @Test
    public void testGetTimeTrackingStats() throws GitLabApiException {
        Issue issue = ensureIssue();
        TimeStats timeStats = gitLabApi.getIssuesApi().getTimeTrackingStats(issue.getProjectId(), issue.getIid());

        assertEquals(new Integer(0), timeStats.getTimeEstimate());
        assertEquals(new Integer(0), timeStats.getTotalTimeSpent());
    }

    /**
     * Expect the given {@link TimeStats} object to have the values
     *
     * @param timeStats
     * @param timeEstimate
     * @param totalTimeSpent
     */
    private void assertTimeStats(TimeStats timeStats, int timeEstimate, int totalTimeSpent) {
        assertEquals(new Integer(timeEstimate), timeStats.getTimeEstimate());
        assertEquals(new Integer(totalTimeSpent), timeStats.getTotalTimeSpent());
    }

    @Test
    public void testEstimateTime() throws GitLabApiException {
        Issue issue = ensureIssue();
        TimeStats timeStats = gitLabApi.getIssuesApi().estimateTime(issue.getProjectId(), issue.getIid(), "1h");
        assertTimeStats(timeStats, (60 /* seconds */ * 60 /* minutes */), 0);

        timeStats = gitLabApi.getIssuesApi().estimateTime(issue.getProjectId(), issue.getIid(), 60 * 60);
        assertTimeStats(timeStats, (60 /* seconds */ * 60 /* minutes */), 0);

        timeStats = gitLabApi.getIssuesApi().estimateTime(issue.getProjectId(), issue.getIid(), new Duration(60 * 60));
        assertTimeStats(timeStats, (60 /* seconds */ * 60 /* minutes */), 0);
    }

    @Test
    public void testResetEstimatedTime() throws GitLabApiException {
        Issue issue = ensureIssue();
        gitLabApi.getIssuesApi().estimateTime(issue.getProjectId(), issue.getIid(), "1h");
        TimeStats timeStats = gitLabApi.getIssuesApi().resetEstimatedTime(issue.getProjectId(), issue.getIid());

        assertTimeStats(timeStats, 0, 0);
    }

    @Test
    public void testAddSpentTime() throws GitLabApiException {
        Issue issue = ensureIssue();
        TimeStats timeStats = gitLabApi.getIssuesApi().addSpentTime(issue.getProjectId(), issue.getIid(), "1h");
        assertTimeStats(timeStats, 0, (60 /* seconds */ * 60 /* minutes */));

        timeStats = gitLabApi.getIssuesApi().addSpentTime(issue.getProjectId(), issue.getIid(), 60 * 60);
        assertTimeStats(timeStats, 0, 60 * 60 * 2);

        timeStats = gitLabApi.getIssuesApi().addSpentTime(issue.getProjectId(), issue.getIid(), new Duration(60 * 60));
        assertTimeStats(timeStats, 0, 60 * 60 * 3);
    }

    @Test
    public void testResetSpentTime() throws GitLabApiException {
        Issue issue = ensureIssue();
        gitLabApi.getIssuesApi().addSpentTime(issue.getProjectId(), issue.getIid(), "1h");
        TimeStats timeStats = gitLabApi.getIssuesApi().resetSpentTime(issue.getProjectId(), issue.getIid());

        assertTimeStats(timeStats, 0, 0);
    }

    @Test
    public void testGetIssuesWithOptions() throws GitLabApiException {

        assertNotNull(testProject);
        Long projectId = testProject.getId();

        Issue issueOpen = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        Issue issueClose = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        Issue issueInIteration = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION, null, null, null, null, null, null, null, null, ITERATION_TITLE);
        issueClose = gitLabApi.getIssuesApi().closeIssue(projectId, issueClose.getIid());


        final Long openIid = issueOpen.getIid();
        IssueFilter openFilter = new IssueFilter().withState(IssueState.OPENED);
        List<Issue> opens = gitLabApi.getIssuesApi().getIssues(projectId, openFilter);
        assertNotNull(opens);
        assertTrue(opens.stream().map(Issue::getIid).anyMatch(iid -> iid.equals(openIid)));

        final Long closedIid = issueClose.getIid();
        IssueFilter closeFilter = new IssueFilter().withState(IssueState.CLOSED);
        List<Issue> closes = gitLabApi.getIssuesApi().getIssues(projectId, closeFilter);
        assertNotNull(closes);
        assertTrue(closes.stream().map(Issue::getIid).anyMatch(iid -> iid.equals(closedIid)));

        final Long issueInIterationIid = issueInIteration.getIid();
        IssueFilter issueInIterationFilter = new IssueFilter().withIterationTitle(ITERATION_TITLE);
        List<Issue> issuesInIteration = gitLabApi.getIssuesApi().getIssues(projectId, issueInIterationFilter);
        assertNotNull(issuesInIteration);
        assertTrue(issuesInIteration.stream().map(Issue::getIid).anyMatch(iid -> iid.equals(issueInIterationIid)));

    }
}
