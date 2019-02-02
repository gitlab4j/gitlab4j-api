package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.utils.ISO8601;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
public class TestEventsApi {

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
    private static User testUser;

    public TestEventsApi() {
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
            } catch (GitLabApiException gle) {
                System.err.print(gle.getMessage());
            }

            try {
                testUser = gitLabApi.getUserApi().getCurrentUser();
            } catch (GitLabApiException gle) {
                System.err.print(gle.getMessage());
            }

        } else {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testGetAuthenticatedUserEvents() throws GitLabApiException {
        List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, null, null, null);
        assertNotNull(events);
    }

    @Test
    public void testGetAuthenticatedUserEventsWithDates() throws GitLabApiException {
        Date after = new Date(0);
        Date now = new Date();
        List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, now, after, null);
        assertNotNull(events);

        events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, after, null, null);
        assertNotNull(events);
        assertEquals(0, events.size());
    }

    @Test
    public void testGetUserEvents() throws GitLabApiException {
        assertNotNull(testUser);
        List<Event> events = gitLabApi.getEventsApi().getUserEvents(testUser.getId(), null, null, null, null, null);
        assertNotNull(events);
    }

    @Test
    public void testGetUserEventsWithAllParams() throws GitLabApiException, ParseException {
        assertNotNull(testUser);
        Date before = ISO8601.toDate("2017-06-02");
        Date after = new Date();
        List<Event> events = gitLabApi.getEventsApi().getUserEvents(testUser.getId(), Constants.ActionType.CREATED, Constants.TargetType.PROJECT, before, after, Constants.SortOrder.DESC);
        assertNotNull(events);
    }

    @Test
    public void testGetProjectEvents() throws GitLabApiException {
        assertNotNull(testProject);
        List<Event> events = gitLabApi.getEventsApi().getProjectEvents(testProject.getId(), null, null, null, null, null);
        assertNotNull(events);
    }

    @Test
    public void testPagedGetAuthenticatedUserEvents() throws GitLabApiException {
        Pager<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, null, null, null, 10);
        assertNotNull(events);
    }

    @Test
    public void testPagedGetUserEvents() throws GitLabApiException {
        assertNotNull(testUser);
        Pager<Event> events = gitLabApi.getEventsApi().getUserEvents(testUser.getId(), null, null, null, null, null, 10);
        assertNotNull(events);
    }

    @Test
    public void testPagedGetProjectEvents() throws GitLabApiException {
        assertNotNull(testProject);
        Pager<Event> events = gitLabApi.getEventsApi().getProjectEvents(testProject.getId(), null, null, null, null, null, 10);
        assertNotNull(events);
    }
}
