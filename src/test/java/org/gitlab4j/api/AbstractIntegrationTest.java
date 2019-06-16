package org.gitlab4j.api;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

/**
 * In order for the integration tests to run you must set the following properties in test-gitlab4j.properties
 * and the resources pointed to must exist.
 *
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 */
public class AbstractIntegrationTest implements PropertyConstants {

    // Get the values of the minimum required test properties.
    protected static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    protected static final String TEST_LOGIN_USERNAME = HelperUtils.getProperty(LOGIN_USERNAME_KEY);
    protected static final String TEST_LOGIN_PASSWORD = HelperUtils.getProperty(LOGIN_PASSWORD_KEY);
    protected static final String TEST_PROJECT_NAME = HelperUtils.getProperty(PROJECT_NAME_KEY);
    protected static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);
    protected static final String TEST_GROUP_PROJECT_NAME = HelperUtils.getProperty(GROUP_PROJECT_KEY);
    protected static final String TEST_NAMESPACE = HelperUtils.getProperty(NAMESPACE_KEY, TEST_LOGIN_USERNAME);
    protected static final String TEST_PRIVATE_TOKEN = HelperUtils.getProperty(PRIVATE_TOKEN_KEY);
    protected static final String TEST_SUB_GROUP = HelperUtils.getProperty(SUB_GROUP_KEY);

    protected static class BaseTestResources {
        protected GitLabApi gitLabApi;
        protected Project testProject;

        protected BaseTestResources(GitLabApi gitLabApi) {
            this.gitLabApi = gitLabApi;
        }
    }

    // Used to keep track of GitLabApi and Project instances for each test class
    private static final Map<String, BaseTestResources> baseTestResourcesMap =
            Collections.synchronizedMap(new WeakHashMap<String, BaseTestResources>());

    public AbstractIntegrationTest() {
        super();
    }

    /**
     * Verifies that the required test properties are present and returns an instance of GitLabApi
     * set up to use the TEST_PRIVATE_TOKEN property to authenticate.
     *
     * @return an instance of GitLabApi set up to use the TEST_PRIVATE_TOKEN property to authenticate
     */
    protected static GitLabApi baseTestSetup() {

        Throwable t = new Throwable();
        StackTraceElement directCaller = t.getStackTrace()[1];
        String callingClassName = directCaller.getClassName();
        BaseTestResources baseResources = baseTestResourcesMap.get(callingClassName);
        if (baseResources != null && baseResources.gitLabApi != null) {
            return (baseResources.gitLabApi);
        }

        String problems = "";
        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().isEmpty()) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (problems.isEmpty()) {
            try {
                GitLabApi gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
                baseResources = new BaseTestResources(gitLabApi);
                baseTestResourcesMap.put(callingClassName, baseResources);
                return (gitLabApi);
            } catch (Exception e) {
                problems += e.getMessage() + "\n";
            }
        }

        System.err.print(problems);
        return (null);
    }

    /**
     * Get the test Project instance for the calling test class.
     *
     * @return the test Project instance for the calling test class
     */
    protected static Project getTestProject() {

        Throwable t = new Throwable();
        StackTraceElement directCaller = t.getStackTrace()[1];
        String callingClassName = directCaller.getClassName();
        BaseTestResources baseResources = baseTestResourcesMap.get(callingClassName);
        if (baseResources == null || baseResources.gitLabApi == null) {
            System.err.println("Problems fetching test Project instance: GitLabApi instance is null");
            return (null);
        } else if (baseResources.testProject != null) {
            return (baseResources.testProject);
        }

        try {
            Project testProject =  (baseResources.gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME));
            baseResources.testProject = testProject;
            return (testProject);
        } catch (Exception e) {
            System.err.println("Problems fetching test Project instance: " + e.getMessage());
            return (null);
        }
    }

    /**
     * Get the current user (the testing user).
     *
     * @return the user that is being used for testing
     */
    protected static User getCurrentUser() {

        Throwable t = new Throwable();
        StackTraceElement directCaller = t.getStackTrace()[1];
        String callingClassName = directCaller.getClassName();
        BaseTestResources baseResources = baseTestResourcesMap.get(callingClassName);
        if (baseResources == null || baseResources.gitLabApi == null) {
            System.err.println("Problems fetching current user: GitLabApi instance is null");
            return (null);
        }

        try {
            return (baseResources.gitLabApi.getUserApi().getCurrentUser());
        } catch (Exception e) {
            System.err.println("Problems fetching current user: " + e.getMessage());
            return (null);
        }
    }
}
