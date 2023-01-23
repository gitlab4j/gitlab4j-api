package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.util.Map;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.junit.jupiter.api.BeforeAll;
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
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestAvatarUpload extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_PROXY_URI;
    private static final String TEST_PROXY_USERNAME;
    private static final String TEST_PROXY_PASSWORD;
    static {
        TEST_PROXY_URI = HelperUtils.getProperty("TEST_PROXY_URI");
        TEST_PROXY_USERNAME = HelperUtils.getProperty("TEST_PROXY_USERNAME");
        TEST_PROXY_PASSWORD = HelperUtils.getProperty("TEST_PROXY_PASSWORD");
    }

    private static final String AVATAR_FILENAME = "avatar.png";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestAvatarUpload() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @Test
    public void testSetProjectAvatar() throws GitLabApiException {

        assumeTrue(testProject != null);

        File avatarFile = new File("src/test/resources/org/gitlab4j/api", AVATAR_FILENAME);
        Project updatedProject = gitLabApi.getProjectApi().setProjectAvatar(testProject.getId(), avatarFile);
        assertNotNull(updatedProject);
        assertTrue(updatedProject.getAvatarUrl().endsWith(AVATAR_FILENAME));
    }

    @Test
    public void testSetProjectAvatarWithProxy() throws GitLabApiException {

        assumeTrue(testProject != null);
        assumeTrue(TEST_PROXY_URI != null && TEST_PROXY_USERNAME != null && TEST_PROXY_PASSWORD != null);
        assumeTrue(TEST_PROXY_URI.length() > 0 && TEST_PROXY_USERNAME.length() > 0 && TEST_PROXY_PASSWORD.length() > 0);

        // Setup a GitLabApi instance to use a proxy
        Map<String, Object> clientConfig = ProxyClientConfig.createProxyClientConfig(TEST_PROXY_URI, TEST_PROXY_USERNAME, TEST_PROXY_PASSWORD);
        GitLabApi gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN, null, clientConfig);

        File avatarFile = new File("src/test/resources/org/gitlab4j/api", AVATAR_FILENAME);
        Project updatedProject = gitLabApi.getProjectApi().setProjectAvatar(testProject.getId(), avatarFile);
        assertNotNull(updatedProject);
        assertTrue(updatedProject.getAvatarUrl().endsWith(AVATAR_FILENAME));

        gitLabApi.close();
    }

    @Test
    public void testSetUserAvatar() throws GitLabApiException {

        assumeTrue(gitLabApi != null);

        User user = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(user);

        File avatarFile = new File("src/test/resources/org/gitlab4j/api", AVATAR_FILENAME);
        User updatedUser = gitLabApi.getUserApi().setUserAvatar(user, avatarFile);
        assertNotNull(updatedUser);
        assertTrue(updatedUser.getAvatarUrl().endsWith(AVATAR_FILENAME));
    }
}
