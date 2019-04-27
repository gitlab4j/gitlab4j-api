package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.util.Map;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
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
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAvatarUpload extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_PROXY_URI;
    private static final String TEST_PROXY_USERNAME;
    private static final String TEST_PROXY_PASSWORD;
    static {
        TEST_PROXY_URI = TestUtils.getProperty("TEST_PROXY_URI");
        TEST_PROXY_USERNAME = TestUtils.getProperty("TEST_PROXY_USERNAME");
        TEST_PROXY_PASSWORD = TestUtils.getProperty("TEST_PROXY_PASSWORD");
    }
    
    private static final String AVATAR_FILENAME = "avatar.png";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestAvatarUpload() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @Test
    public void testSetProjectAvatar() throws GitLabApiException {

        assumeNotNull(testProject);

        File avatarFile = new File("src/test/resources/org/gitlab4j/api", AVATAR_FILENAME);
        Project updatedProject = gitLabApi.getProjectApi().setProjectAvatar(testProject.getId(), avatarFile);
        assertNotNull(updatedProject);
        assertTrue(updatedProject.getAvatarUrl().endsWith(AVATAR_FILENAME));
    }

    @Test
    public void testSetProjectAvatarWithProxy() throws GitLabApiException {

        assumeNotNull(testProject);
        assumeTrue(TEST_PROXY_URI != null && TEST_PROXY_USERNAME != null && TEST_PROXY_PASSWORD != null);
        assumeTrue(TEST_PROXY_URI.length() > 0 && TEST_PROXY_USERNAME.length() > 0 && TEST_PROXY_PASSWORD.length() > 0);

        // Setup a GitLabApi instance to use a proxy
        Map<String, Object> clientConfig = ProxyClientConfig.createProxyClientConfig(TEST_PROXY_URI, TEST_PROXY_USERNAME, TEST_PROXY_PASSWORD);
        GitLabApi gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN, null, clientConfig);

        File avatarFile = new File("src/test/resources/org/gitlab4j/api", AVATAR_FILENAME);
        Project updatedProject = gitLabApi.getProjectApi().setProjectAvatar(testProject.getId(), avatarFile);
        assertNotNull(updatedProject);
        assertTrue(updatedProject.getAvatarUrl().endsWith(AVATAR_FILENAME));
    }

    @Test
    public void testSetUserAvatar() throws GitLabApiException {

        assumeNotNull(gitLabApi);

        User user = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(user);

        File avatarFile = new File("src/test/resources/org/gitlab4j/api", AVATAR_FILENAME);
        User updatedUser = gitLabApi.getUserApi().setUserAvatar(user, avatarFile);
        assertNotNull(updatedUser);
        assertTrue(updatedUser.getAvatarUrl().endsWith(AVATAR_FILENAME));
    }
}
