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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.Constants.IssueState;
import org.gitlab4j.api.models.Duration;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.IssueFilter;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.TimeStats;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
@Category(IntegrationTest.class)
public class TestIssuesApi extends AbstractIntegrationTest  {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    private static final String ISSUE_TITLE = "Test Issue Title";
    private static final String ISSUE_DESCRIPTION = "This is a really nice description, not.";
    private static Random randomNumberGenerator = new Random();

    public TestIssuesApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        deleteAllTestIssues();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        deleteAllTestIssues();
    }

    private static void deleteAllTestIssues() {

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
        Integer projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        Issue foundIssue = gitLabApi.getIssuesApi().getIssue(projectId, issue.getIid());
        assertNotNull(foundIssue);
        assertEquals(issue.getIid(), foundIssue.getIid());
    }

    @Test
    public void testGetIssues() throws GitLabApiException {

        assertNotNull(testProject);
        Integer projectId = testProject.getId();
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
    public void testCreateIssue() throws GitLabApiException {

        assertNotNull(testProject);
        Integer projectId = testProject.getId();
        String title = getUniqueTitle();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, title, ISSUE_DESCRIPTION);
        assertNotNull(issue);
        assertEquals(title, issue.getTitle());
        assertEquals(ISSUE_DESCRIPTION, issue.getDescription());
        assertEquals(IssueState.OPENED, issue.getState());

        List<Integer> assigneeIds = Arrays.asList(issue.getAuthor().getId());
        issue = gitLabApi.getIssuesApi().createIssue(projectId, title, ISSUE_DESCRIPTION, null, assigneeIds, null, null, new Date(), null, null, null);
        assertNotNull(issue);
        assertEquals(title, issue.getTitle());
        assertEquals(ISSUE_DESCRIPTION, issue.getDescription());
        assertEquals(IssueState.OPENED, issue.getState());
    }

    @Test
    public void testCloseIssueJustCreated() throws GitLabApiException {

        assertNotNull(testProject);
        Integer projectId = testProject.getId();
        Issue issue = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);

        Issue closedIssue = gitLabApi.getIssuesApi().closeIssue(projectId, issue.getIid());
        assertNotNull(closedIssue);
        assertEquals(IssueState.CLOSED, closedIssue.getState());
        assertEquals(issue.getId(), closedIssue.getId());
    }

    @Test
    public void testCloseIssueClosedAt() throws GitLabApiException {

        assertNotNull(testProject);
        Integer projectId = testProject.getId();
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
        Integer projectId = testProject.getId();
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
        Integer projectId = testProject.getId();
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
        Integer projectId = testProject.getId();

        Issue issueOpen = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        Issue issueClose = gitLabApi.getIssuesApi().createIssue(projectId, getUniqueTitle(), ISSUE_DESCRIPTION);
        issueClose = gitLabApi.getIssuesApi().closeIssue(projectId, issueClose.getIid());

        final Integer openIid = issueOpen.getIid();
        IssueFilter openFilter = new IssueFilter().withState(IssueState.OPENED);
        List<Issue> opens = gitLabApi.getIssuesApi().getIssues(projectId, openFilter);
        assertNotNull(opens);
        assertTrue(opens.stream().map(Issue::getIid).anyMatch(iid -> iid.equals(openIid)));

        final Integer closedIid = issueClose.getIid();
        IssueFilter closeFilter = new IssueFilter().withState(IssueState.CLOSED);       
        List<Issue> closes = gitLabApi.getIssuesApi().getIssues(projectId, closeFilter);
        assertNotNull(closes);
        assertTrue(closes.stream().map(Issue::getIid).anyMatch(iid -> iid.equals(closedIid)));
    }
}
