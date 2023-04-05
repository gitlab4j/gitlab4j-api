package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.utils.ISO8601;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestEventsApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static User testUser;

    public TestEventsApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        if (gitLabApi != null) {
            try {
                testUser = gitLabApi.getUserApi().getCurrentUser();
            } catch (GitLabApiException gle) {
                System.err.print(gle.getMessage());
            }
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testGetAuthenticatedUserEvents() throws GitLabApiException {
        List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, null, null, null, 1, 10);
        assertNotNull(events);
    }

    @Test
    public void testGetAuthenticatedUserEventsWithScope() throws GitLabApiException {
        List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, null, null, null, 1, 10, Constants.EventScope.ALL);
        assertNotNull(events);
    }

    @Test
    public void testGetAllAuthenticatedUserEvents() throws GitLabApiException {
        List<Event> events = gitLabApi.getEventsApi().getAllAuthenticatedUserEvents(null, null, null, null, null);
        assertNotNull(events);
    }

    @Test
    public void testGetAuthenticatedUserEventsWithDates() throws GitLabApiException {
        Date after = new Date(0);
        Date now = new Date();
        List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, now, after, null, 1, 10);
        assertNotNull(events);

        events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, after, null, null);
        assertNotNull(events);
        assertEquals(0, events.size());
    }

    @Test
    public void testGetAuthenticatedUserEventsWithDatesAndScope() throws GitLabApiException {
        Date after = new Date(0);
        Date now = new Date();
        List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, now, after, null, 1, 10, Constants.EventScope.ALL);
        assertNotNull(events);

        events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, after, null, null, 1, 10, Constants.EventScope.ALL);
        assertNotNull(events);
        assertEquals(0, events.size());
    }

    @Test
    public void testGetUserEvents() throws GitLabApiException {
        assertNotNull(testUser);
        List<Event> events = gitLabApi.getEventsApi().getUserEvents(testUser.getId(), null, null, null, null, null, 1, 10);
        assertNotNull(events);
    }

    @Test
    public void testGetUserEventsWithAllParams() throws GitLabApiException, ParseException {
        assertNotNull(testUser);
        Date before = ISO8601.toDate("2017-06-02");
        Date after = new Date();
        List<Event> events = gitLabApi.getEventsApi().getUserEvents(
                testUser.getId(), Constants.ActionType.CREATED, Constants.TargetType.PROJECT, before, after, Constants.SortOrder.DESC, 1, 10);
        assertNotNull(events);
    }

    @Test
    public void testGetProjectEvents() throws GitLabApiException {
        assertNotNull(testProject);
        List<Event> events = gitLabApi.getEventsApi().getProjectEvents(testProject.getId(), null, null, null, null, null, 1, 10);
        assertNotNull(events);
    }

    @Test
    public void testPagedGetAuthenticatedUserEvents() throws GitLabApiException {
        Pager<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, null, null, null, 10);
        assertNotNull(events);
    }

    @Test
    public void testPagedGetAuthenticatedUserEventsWithScope() throws GitLabApiException {
        Pager<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, null, null, null, 10, Constants.EventScope.ALL);
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
