package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.NotificationSettings;
import org.gitlab4j.api.models.Project;
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
 * TEST_GROUP
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestNotificationSettingsApi {

    // The following needs to be set to your test repository
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_NAMESPACE;
    private static final String TEST_GROUP;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_GROUP = TestUtils.getProperty("TEST_GROUP");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApi;

    public TestNotificationSettingsApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().length() == 0) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().length() == 0) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().length() == 0) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().length() == 0) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }
    }

    @Before
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
