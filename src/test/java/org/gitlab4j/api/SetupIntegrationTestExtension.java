package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.models.Visibility;
import org.gitlab4j.api.utils.AccessTokenUtils;
import org.gitlab4j.api.utils.AccessTokenUtils.Scope;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * This extension for integration tests will check for the users, projects, groups, and repo files needed for testing
 * and create them if they do not exist.  It will also create temporary personal access tokens needed for testing.
 *
 * <p>NOTE: This class relies on a minimal amount of the GitLab4J-API library to set things up,
 * so if there are any failures the test suite will fail.  Consider it the first integration tests
 * that are being performed.</p>
 */
public class SetupIntegrationTestExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource, PropertyConstants {

    private static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    private static final String TEST_LOGIN_USERNAME = HelperUtils.getProperty(LOGIN_USERNAME_KEY);
    private static final String TEST_LOGIN_PASSWORD = HelperUtils.getProperty(LOGIN_PASSWORD_KEY);
    private static final String TEST_NAMESPACE = HelperUtils.getProperty(NAMESPACE_KEY, TEST_LOGIN_USERNAME);
    private static final String TEST_PROJECT_NAME = HelperUtils.getProperty(PROJECT_NAME_KEY);
    private static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);
    private static final String TEST_GROUP_PROJECT_NAME = HelperUtils.getProperty(GROUP_PROJECT_KEY);
    private static final String TEST_SUB_GROUP = HelperUtils.getProperty(SUB_GROUP_KEY);

    protected static final String TEST_PRIVATE_TOKEN_NAME = "GitLab4J Test Private Token - " + HelperUtils.getRandomInt(1000);
    protected static String TEST_PRIVATE_TOKEN = HelperUtils.getProperty(PRIVATE_TOKEN_KEY);

    protected static final String TEST_ACCESS_TOKEN_NAME = "GitLab4J Test Access Token - " + HelperUtils.getRandomInt(1000);
    protected static String TEST_ACCESS_TOKEN = HelperUtils.getProperty(ACCESS_TOKEN_KEY);

    private static boolean createdPrivateToken = false;
    private static boolean createdAccessToken = false;
    private static String problems = "";

    private static boolean alreadySetup = false;
	final static Lock lock = new ReentrantLock();

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		lock.lock();
		if (!alreadySetup) {

			System.out.println("********************************************************");
	        System.out.println("*               Integration Tests Setup                *");
	        System.out.println("********************************************************");

	        if (TEST_LOGIN_USERNAME == null || TEST_LOGIN_USERNAME.trim().isEmpty()) {
	            problems += "TEST_LOGIN_USERNAME cannot be empty\n";
	        }

	        if (TEST_LOGIN_PASSWORD == null || TEST_LOGIN_PASSWORD.trim().isEmpty()) {
	            problems += "TEST_LOGIN_PASSWORD cannot be empty\n";
	        }

	        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
	            problems += "TEST_HOST_URL cannot be empty\n";
	        }

	        if (!problems.isEmpty()) {
	            fail(problems);
	        }

	        seedData();
	        createAccessTokens();

			alreadySetup = true;
		}
		lock.unlock();

    }

	@Override
	public void close() throws Throwable {
        System.out.println("********************************************************");
        System.out.println("*             Integration Tests Teardown               *");
        System.out.println("********************************************************");

        revokeAccessTokens();
    }

    private static void createAccessTokens() throws GitLabApiException {

        // If the private token is not in the properties, create it
        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {

            TEST_PRIVATE_TOKEN = AccessTokenUtils.createPersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_PRIVATE_TOKEN_NAME, Arrays.asList(Scope.API, Scope.SUDO));
            System.out.println("Created private token: " + TEST_PRIVATE_TOKEN);
            assertNotNull(TEST_PRIVATE_TOKEN);
            assertFalse(TEST_PRIVATE_TOKEN.trim().isEmpty());
            HelperUtils.setProperty(PRIVATE_TOKEN_KEY, TEST_PRIVATE_TOKEN);
            createdPrivateToken = true;
        }

        // If the access token is not in the properties, create it t
        if (TEST_ACCESS_TOKEN == null || TEST_ACCESS_TOKEN.trim().isEmpty()) {

            TEST_ACCESS_TOKEN = AccessTokenUtils.createPersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_ACCESS_TOKEN_NAME, Arrays.asList(Scope.API, Scope.SUDO));
            System.out.println("Created access token: " + TEST_ACCESS_TOKEN);
            assertNotNull(TEST_ACCESS_TOKEN);
            assertFalse(TEST_ACCESS_TOKEN.trim().isEmpty());
            HelperUtils.setProperty(ACCESS_TOKEN_KEY, TEST_ACCESS_TOKEN);
            createdAccessToken = true;
        }
    }

    private static void revokeAccessTokens() throws GitLabApiException {

        if (createdPrivateToken && TEST_PRIVATE_TOKEN != null) {
            try {
                AccessTokenUtils.revokePersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_PRIVATE_TOKEN_NAME, Arrays.asList(Scope.API, Scope.SUDO));
                System.out.format("Revoked '%s'%n", TEST_PRIVATE_TOKEN_NAME);
            } catch (Exception ignore) {}
        }

        if (createdAccessToken && TEST_ACCESS_TOKEN != null) {
            try {
                AccessTokenUtils.revokePersonalAccessToken(
                    TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD,
                    TEST_ACCESS_TOKEN_NAME, Arrays.asList(Scope.API, Scope.SUDO));
                System.out.format("Revoked '%s'%n", TEST_ACCESS_TOKEN_NAME);
            } catch (Exception ignore) {}
        }
    }

    /**
     * This method will check for the users, prjects, groups, and repo files needed for testing
     * and create them if they do not exist.
     *
     * @throws GitLabApiException if any error occurs
     */
    private static void seedData() throws GitLabApiException {

        // Use OAUTH2 and the provided admin credentials to create the user to run the tests as if it doesn't exist
        String username = HelperUtils.getProperty(ADMIN_USERNAME_KEY);
        if (username == null || username.trim().isEmpty()) {
            username = System.getProperty(ADMIN_USERNAME_KEY);
            username = (username == null || username.trim().isEmpty() ? "root" : username);
        }

        String password = HelperUtils.getProperty(ADMIN_PASSWORD_KEY);
        if (password == null || password.trim().isEmpty()) {
            password = System.getProperty(ADMIN_PASSWORD_KEY);
            password = (password == null || password.trim().isEmpty() ? "password" : password);
        }

        GitLabApi gitLabApi = GitLabApi.oauth2Login(TEST_HOST_URL, username, password, null, null, true);

        // If the tester user doen't exists, create it
        Optional<User> optionalUser = gitLabApi.getUserApi().getOptionalUser(TEST_LOGIN_USERNAME);
        if (!optionalUser.isPresent()) {
            User userSettings = new User()
                    .withUsername(TEST_LOGIN_USERNAME)
                    .withEmail(TEST_LOGIN_USERNAME + "@gitlab4j.org")
                    .withName("GitLab4J Tester")
                    .withSkipConfirmation(true)
                    .withIsAdmin(true);
            gitLabApi.getUserApi().createUser(userSettings, TEST_LOGIN_PASSWORD, false);
            System.out.format("Created %s user (%s)%n", userSettings.getName(), userSettings.getUsername());
        }

        // The reset of the operations will use the test user to do things,
        // so use OAUTH2 to get the GitLabApi instance
        gitLabApi = GitLabApi.oauth2Login(TEST_HOST_URL, TEST_LOGIN_USERNAME, TEST_LOGIN_PASSWORD, null, null, true);

        // Create the sudo as user if it does not exists
        username = HelperUtils.getProperty(SUDO_AS_USERNAME_KEY, "user1");
        optionalUser = gitLabApi.getUserApi().getOptionalUser(username);
        if (!optionalUser.isPresent()) {
            User userSettings = new User()
                    .withUsername(username)
                    .withEmail(username + "@gitlab4j.org")
                    .withName("Test User")
                    .withSkipConfirmation(true)
                    .withIsAdmin(false);
            gitLabApi.getUserApi().createUser(userSettings, TEST_LOGIN_PASSWORD, false);
            System.out.format("Created %s user (%s)%n", userSettings.getName(), userSettings.getUsername());
        }

        // Create the test project
        Optional<Project> optionalProject = gitLabApi.getProjectApi().getOptionalProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        Project testProject = optionalProject.orElse(null);
        if (testProject == null) {

            Project projectSettings = new Project()
                    .withName(TEST_PROJECT_NAME)
                    .withDefaultBranch("master")
                    .withPublic(true)
                    .withInitializeWithReadme(true)
                    .withRequestAccessEnabled(true);
            testProject = gitLabApi.getProjectApi().createProject(projectSettings);
            System.out.format("Created %s project%n", projectSettings.getName());

            // Update the contents of README.md, so we have at minimum 2 commits
            RepositoryFile repoFile = new RepositoryFile();
            repoFile.setFilePath("README.md");
            repoFile.setContent("This is a test project used to test GitLab4J-API.");
            gitLabApi.getRepositoryFileApi().updateFile(testProject, repoFile, "master", "Updated contents");
            System.out.format("Updated content of %s repository file%n", repoFile.getFilePath());

            // Create a file in a subdirectory
            repoFile.setFilePath(TEST_PROJECT_SUBDIRECTORY_PATH);
            gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, "master", "Initial commit.");
            System.out.format("Created %s repository file%n", repoFile.getFilePath());

        } else if (!gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, "README.md", "master").isPresent()) {

            // Create the README.md file since it does not exists
            RepositoryFile repoFile = new RepositoryFile();
            repoFile.setFilePath("README.md");
            repoFile.setContent("");
            gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, "master", "Initial commit.");
            System.out.format("Created %s repository file%n", repoFile.getFilePath());

            // Update the contents so we have at minimum 2 commits
            repoFile.encodeAndSetContent("This is a test project used to test GitLab4J-API.");
            gitLabApi.getRepositoryFileApi().updateFile(testProject, repoFile, "master", "Updated contents");
            System.out.format("Updated content of %s repository file%n", repoFile.getFilePath());
        }

        // Create the test group if it does not exist
        Optional<Group> optionalGroup = gitLabApi.getGroupApi().getOptionalGroup(TEST_GROUP);
        Group testGroup = optionalGroup.orElse(null);
        if (testGroup == null) {
            Group groupSettings = new Group()
                    .withName("Test Group")
                    .withPath(TEST_GROUP)
                    .withDescription("Test Group")
                    .withVisibility(Visibility.PUBLIC)
                    .withRequestAccessEnabled(true);
            testGroup = gitLabApi.getGroupApi().addGroup(groupSettings);
            System.out.format("Created %s group (%s)%n", groupSettings.getName(), groupSettings.getPath());
        }

        // Create the test project in the test group namespace if it does not exist
        optionalProject = gitLabApi.getProjectApi().getOptionalProject(TEST_GROUP, TEST_GROUP_PROJECT_NAME);
        testProject = optionalProject.orElse(null);
        if (testProject == null) {

            Project projectSettings = new Project()
                    .withName(TEST_GROUP_PROJECT_NAME)
                    .withDefaultBranch("master")
                    .withPublic(true)
                    .withInitializeWithReadme(true);
            Project groupProject = gitLabApi.getProjectApi().createProject(testGroup.getId(), projectSettings);
            System.out.format("Created %s project%n", groupProject.getNameWithNamespace());

            // Update the contents of README.md, so we have at minimum 2 commits
            RepositoryFile repoFile = new RepositoryFile();
            repoFile.setFilePath("README.md");
            repoFile.encodeAndSetContent("This is a test project used to test GitLab4J-API.");
            gitLabApi.getRepositoryFileApi().updateFile(groupProject, repoFile, "master", "Updated contents");
            System.out.format("Updated content of %s repository file%n", repoFile.getFilePath());

        }  else if (!gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, "README.md", "master").isPresent()) {

            // Create the README.md file since it does not exists
            RepositoryFile repoFile = new RepositoryFile();
            repoFile.setFilePath("README.md");
            repoFile.setContent("");
            gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, "master", "Initial commit.");
            System.out.format("Created %s repository file in %s%n", repoFile.getFilePath(), TEST_GROUP_PROJECT_NAME);

            // Update the contents so we have at minimum 2 commits
            repoFile.encodeAndSetContent("This is a test project used to test GitLab4J-API.");
            gitLabApi.getRepositoryFileApi().updateFile(testProject, repoFile, "master", "Updated contents");
            System.out.format("Updated %s repository file in %s%n", repoFile.getFilePath(), TEST_GROUP_PROJECT_NAME);
        }

        // Create a subgroup
        List<Group> subGroups = gitLabApi.getGroupApi().getSubGroups(TEST_GROUP, null, null, TEST_SUB_GROUP, null, null, null, null);
        if (subGroups.isEmpty()) {
            Group groupSettings = new Group()
                    .withName(TEST_SUB_GROUP)
                    .withPath(TEST_SUB_GROUP)
                    .withDescription("Test Sub-Group")
                    .withVisibility(Visibility.PUBLIC)
                    .withRequestAccessEnabled(true)
                    .withParentId(testGroup.getId());
            Group testSubGroup = gitLabApi.getGroupApi().addGroup(groupSettings);
            System.out.format("Created %s sub-group (%s)%n", testSubGroup.getName(), testSubGroup.getPath());
        }
    }
}
