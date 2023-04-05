package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.NotificationSettings;
import org.gitlab4j.api.models.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestNotificationSettingsApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);

    private static GitLabApi gitLabApi;

    public TestNotificationSettingsApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
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
