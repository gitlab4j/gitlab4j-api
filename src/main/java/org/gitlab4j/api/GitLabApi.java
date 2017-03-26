package org.gitlab4j.api;

import java.util.Map;

/**
 * This class is provides a simplified interface to a GitLab API server, and divides the API up into
 * a separate API class for each concern.
 */
public class GitLabApi {

    GitLabApiClient apiClient;
    private CommitsApi commitsApi;
    private GroupApi groupApi;
    private MergeRequestApi mergeRequestApi;
    private ProjectApi projectApi;
    private RepositoryApi repositoryApi;
    private RepositoryFileApi repositoryFileApi;
    private ServicesApi servicesApi;
    private SessionApi sessoinApi;
    private UserApi userApi;

    /**
     * Logs into GitLab using provided {@code username} and {@code password}, and creates a new {@code GitLabApi} instance using returned private token
     * 
     * @param url GitLab URL
     * @param username user name for which private token should be obtained
     * @param password password for a given {@code username}
     * @return new {@code GitLabApi} instance configured for a user-specific token
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public static GitLabApi create(String url, String username, String password) throws GitLabApiException {
        String token = new SessionApi(new GitLabApi(url, null)).login(username, null, password).getPrivateToken();
        return new GitLabApi(url, token);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server
     * specified by hostUrl.
     * 
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     */
    public GitLabApi(String hostUrl, String privateToken) {
        this(hostUrl, privateToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server
     * specified by hostUrl.
     * 
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApi(String hostUrl, String privateToken, String secretToken) {
        this(hostUrl, privateToken, secretToken, null);
    }

    public GitLabApi(String hostUrl, String privateToken, String secretToken, Map<String, Object> clientConfigProperties) {
        apiClient = new GitLabApiClient(hostUrl, privateToken, secretToken, clientConfigProperties);
        commitsApi = new CommitsApi(this);
        groupApi = new GroupApi(this);
        mergeRequestApi = new MergeRequestApi(this);
        projectApi = new ProjectApi(this);
        repositoryApi = new RepositoryApi(this);
        servicesApi = new ServicesApi(this);
        sessoinApi = new SessionApi(this);
        userApi = new UserApi(this);
        repositoryFileApi = new RepositoryFileApi(this);
    }

    /**
     * Return the GitLabApiClient associated with this instance. This is used by all the sub API classes
     * to communicate with the GitLab API.
     * 
     * @return the GitLabApiClient associated with this instance
     */
    GitLabApiClient getApiClient() {
        return (apiClient);
    }

    /**
     * Gets the CommitsApi instance owned by this GitLabApi instance. The CommitsApi is used
     * to perform all commit related API calls.
     * 
     * @return the CommitsApi instance owned by this GitLabApi instance
     */
    public CommitsApi getCommitsApi() {
        return (commitsApi);
    }

    /**
     * Gets the MergeRequestApi instance owned by this GitLabApi instance. The MergeRequestApi is used
     * to perform all merge request related API calls.
     * 
     * @return the MergeRequestApi instance owned by this GitLabApi instance
     */
    public MergeRequestApi getMergeRequestApi() {
        return (mergeRequestApi);
    }

    /**
     * Gets the GroupApi instance owned by this GitLabApi instance. The GroupApi is used
     * to perform all group related API calls.
     * 
     * @return the GroupApi instance owned by this GitLabApi instance
     */
    public GroupApi getGroupApi() {
        return (groupApi);
    }

    /**
     * Gets the ProjectApi instance owned by this GitLabApi instance. The ProjectApi is used
     * to perform all project related API calls.
     * 
     * @return the ProjectApi instance owned by this GitLabApi instance
     */
    public ProjectApi getProjectApi() {
        return (projectApi);
    }

    /**
     * Gets the RepositoryApi instance owned by this GitLabApi instance. The RepositoryApi is used
     * to perform all repository related API calls.
     * 
     * @return the RepositoryApi instance owned by this GitLabApi instance
     */
    public RepositoryApi getRepositoryApi() {
        return (repositoryApi);
    }

    /**
     * Gets the RepositoryFileApi instance owned by this GitLabApi instance. The RepositoryFileApi is used
     * to perform all repository files related API calls.
     * 
     * @return the RepositoryFileApi instance owned by this GitLabApi instance
     */
    public RepositoryFileApi getRepositoryFileApi() {
        return repositoryFileApi;
    }

    /**
     * Gets the ServicesApi instance owned by this GitLabApi instance. The ServicesApi is used
     * to perform all services related API calls.
     * 
     * @return the ServicesApi instance owned by this GitLabApi instance
     */
    public ServicesApi getServicesApi() {
        return (servicesApi);
    }

    /**
     * Gets the SessionApi instance owned by this GitLabApi instance. The SessionApi is used
     * to perform a login to the GitLab API.
     * 
     * @return the SessionApi instance owned by this GitLabApi instance
     */
    public SessionApi getSessionApi() {
        return (sessoinApi);
    }

    /**
     * Gets the UserApi instance owned by this GitLabApi instance. The UserApi is used
     * to perform all user related API calls.
     * 
     * @return the UserApi instance owned by this GitLabApi instance
     */
    public UserApi getUserApi() {
        return (userApi);
    }
}
