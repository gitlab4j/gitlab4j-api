package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.NotificationSettings;
import org.gitlab4j.api.models.Project;
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
 * TEST_GROUP
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestNotificationSettingsApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_GROUP = TestUtils.getProperty("TEST_GROUP");

    private static GitLabApi gitLabApi;

    public TestNotificationSettingsApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testGlobalNotificationSettings() throws GitLabApiException {
        NotificationSettings settings = gitLabApi.getNotificationSettingsApi().getGlobalNotificationSettings();
        assertNotNull(settings);
    }

    @Test
    public void testSetGlobalNotificationSettings() throws GitLabApiException {
        NotificationSettings settings = new NotificationSettings();
        settings.setLevel(NotificationSettings.Level.DISABLED);
        NotificationSettings newSettings = gitLabApi.getNotificationSettingsApi().updateGlobalNotificationSettings(settings);
        assertNotNull(newSettings);
        assertEquals(NotificationSettings.Level.DISABLED, newSettings.getLevel());
    }

    @Test
    public void testGroupNotificationSettings() throws GitLabApiException {
        assumeFalse(TEST_GROUP == null || TEST_GROUP.trim().isEmpty());
        List<Group> groups = gitLabApi.getGroupApi().getGroups(TEST_GROUP);
        assertNotNull(groups);
        assertFalse(groups.isEmpty());
        NotificationSettings settings = gitLabApi.getNotificationSettingsApi().getGroupNotificationSettings(groups.get(0).getId());
        assertNotNull(settings);
    }

    @Test
    public void testProjectNotificationSettings() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);
        NotificationSettings settings = gitLabApi.getNotificationSettingsApi().getProjectNotificationSettings(project.getId());
        assertNotNull(settings);
    }
}
