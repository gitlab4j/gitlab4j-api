package org.gitlab4j.api;

import org.gitlab4j.api.Constants.TokenType;
import org.gitlab4j.api.models.Session;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.models.Version;

import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * This class is provides a simplified interface to a GitLab API server, and divides the API up into
 * a separate API class for each concern.
 */
public class GitLabApi {

    /** GitLab4J default per page.  GitLab will ignore anything over 100. */
    public static final int DEFAULT_PER_PAGE = 100;

    /** Specifies the version of the GitLab API to communicate with. */
    public enum ApiVersion {
        V3, V4;

        public String getApiNamespace() {
            return ("/api/" + name().toLowerCase());
        }
    }

    GitLabApiClient apiClient;
    private ApiVersion apiVersion;
    private int defaultPerPage = DEFAULT_PER_PAGE;
    private CommitsApi commitsApi;
    private DeployKeysApi deployKeysApi;
    private GroupApi groupApi;
    private IssuesApi issuesApi;
    private MergeRequestApi mergeRequestApi;
    private MileStonesApi mileStonesApi;
    private NamespaceApi namespaceApi;
    private PipelineApi pipelineApi;
    private ProjectApi projectApi;
    private RepositoryApi repositoryApi;
    private RepositoryFileApi repositoryFileApi;
    private ServicesApi servicesApi;
    private SessionApi sessoinApi;
    private UserApi userApi;
    private JobApi jobApi;
    private LabelsApi labelsApi;
    private NotesApi notesApi;
    private EventsApi eventsApi;

    private Session session;


    /**
     * Logs into GitLab using provided {@code username} and {@code password}, and creates a new {@code GitLabApi} instance
     * using returned private token and the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param url GitLab URL
     * @param username user name for which private token should be obtained
     * @param password password for a given {@code username}
     * @return new {@code GitLabApi} instance configured for a user-specific token
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public static GitLabApi login(ApiVersion apiVersion, String url, String username, String password) throws GitLabApiException {
        return (login(apiVersion, url, username, password, false));
    }

    /**
     * Logs into GitLab using provided {@code username} and {@code password}, and creates a new {@code GitLabApi} instance
     * using returned private token using GitLab API version 4.
     *
     * @param url GitLab URL
     * @param username user name for which private token should be obtained
     * @param password password for a given {@code username}
     * @return new {@code GitLabApi} instance configured for a user-specific token
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public static GitLabApi login(String url, String username, String password) throws GitLabApiException {
        return (login(ApiVersion.V4, url, username, password, false));
    }

    /**
     * Logs into GitLab using provided {@code username} and {@code password}, and creates a new {@code GitLabApi} instance
     * using returned private token and the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param url GitLab URL
     * @param username user name for which private token should be obtained
     * @param password password for a given {@code username}
     * @param ignoreCertificateErrors if true will set up the Jersey system ignore SSL certificate errors
     * @return new {@code GitLabApi} instance configured for a user-specific token
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public static GitLabApi login(ApiVersion apiVersion, String url, String username, String password, boolean ignoreCertificateErrors) throws GitLabApiException {

        GitLabApi gitLabApi = new GitLabApi(apiVersion, url, (String)null);
        if (ignoreCertificateErrors) {
            gitLabApi.setIgnoreCertificateErrors(true);
        }

        SessionApi sessionApi = gitLabApi.getSessionApi();
        Session session = sessionApi.login(username, null, password);
        gitLabApi = new GitLabApi(apiVersion, url, session);

        if (ignoreCertificateErrors) {
            gitLabApi.setIgnoreCertificateErrors(true);
        }

        return (gitLabApi);
    }

    /**
     * Logs into GitLab using provided {@code username} and {@code password}, and creates a new {@code GitLabApi} instance
     * using returned private token using GitLab API version 4.
     *
     * @param url GitLab URL
     * @param username user name for which private token should be obtained
     * @param password password for a given {@code username}
     * @param ignoreCertificateErrors if true will set up the Jersey system ignore SSL certificate errors
     * @return new {@code GitLabApi} instance configured for a user-specific token
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public static GitLabApi login(String url, String username, String password, boolean ignoreCertificateErrors) throws GitLabApiException {
        return (login(ApiVersion.V4, url, username, password, ignoreCertificateErrors));
    }

    /**
     * Logs into GitLab using provided {@code username} and {@code password}, and creates a new {@code GitLabApi} instance
     * using returned private token and specified GitLab API version.
     *
     * @param url GitLab URL
     * @param username user name for which private token should be obtained
     * @param password password for a given {@code username}
     * @return new {@code GitLabApi} instance configured for a user-specific token
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     * @deprecated  As of release 4.2.0, replaced by {@link #login(String, String, String)}
     */
    @Deprecated
    public static GitLabApi create(String url, String username, String password) throws GitLabApiException {
        return (login(url, username, password));
    }

    /**
     * If this instance was created with {@link #login(String, String, String)} this method will
     * return the Session instance returned by the GitLab API on login, otherwise returns null.
     *
     * @return the Session instance
     */
    public Session getSession() {
        return session;
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken the token to use for access to the API
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, TokenType tokenType, String authToken) {
        this(apiVersion, hostUrl, tokenType, authToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, String privateToken) {
        this(apiVersion, hostUrl, privateToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken the token to use for access to the API
     */
    public GitLabApi(String hostUrl, TokenType tokenType, String authToken) {
        this(ApiVersion.V4, hostUrl, tokenType, authToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     */
    public GitLabApi(String hostUrl, String privateToken) {
        this(ApiVersion.V4, hostUrl, privateToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param session the Session instance obtained by logining into the GitLab server
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, Session session) {
        this(apiVersion, hostUrl, TokenType.PRIVATE, session.getPrivateToken(), null);
        this.session = session;
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param session the Session instance obtained by logining into the GitLab server
     */
    public GitLabApi(String hostUrl, Session session) {
        this(ApiVersion.V4, hostUrl, session);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken the token to use for access to the API
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, TokenType tokenType, String authToken, String secretToken) {
        this(apiVersion, hostUrl, tokenType, authToken, secretToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, String privateToken, String secretToken) {
        this(apiVersion, hostUrl, privateToken, secretToken, null);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken the token to use for access to the API
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApi(String hostUrl, TokenType tokenType, String authToken, String secretToken) {
        this(ApiVersion.V4, hostUrl, tokenType, authToken, secretToken);
    }

    /**
     * Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApi(String hostUrl, String privateToken, String secretToken) {
        this(ApiVersion.V4, hostUrl, TokenType.PRIVATE, privateToken, secretToken);
    }

    /**
     *  Constructs a GitLabApi instance set up to interact with the GitLab server specified by GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     * @param secretToken use this token to validate received payloads
     * @param clientConfigProperties Map instance with additional properties for the Jersey client connection
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, String privateToken, String secretToken, Map<String, Object> clientConfigProperties) {
        this(apiVersion, hostUrl, TokenType.PRIVATE, privateToken, secretToken, clientConfigProperties);
    }

    /**
     *  Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken the token to use for access to the API
     * @param secretToken use this token to validate received payloads
     * @param clientConfigProperties Map instance with additional properties for the Jersey client connection
     */
    public GitLabApi(String hostUrl, TokenType tokenType, String authToken, String secretToken, Map<String, Object> clientConfigProperties) {
        this(ApiVersion.V4, hostUrl, tokenType, authToken, secretToken, clientConfigProperties);
    }

   /**
     *  Constructs a GitLabApi instance set up to interact with the GitLab server using GitLab API version 4.
     *
     * @param hostUrl the URL of the GitLab server
     * @param privateToken to private token to use for access to the API
     * @param secretToken use this token to validate received payloads
     * @param clientConfigProperties Map instance with additional properties for the Jersey client connection
     */
    public GitLabApi(String hostUrl, String privateToken, String secretToken, Map<String, Object> clientConfigProperties) {
        this(ApiVersion.V4, hostUrl, TokenType.PRIVATE, privateToken, secretToken, clientConfigProperties);
    }

    /**
     *  Constructs a GitLabApi instance set up to interact with the GitLab server specified by GitLab API version.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL of the GitLab server
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken to token to use for access to the API
     * @param secretToken use this token to validate received payloads
     * @param clientConfigProperties Map instance with additional properties for the Jersey client connection
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, TokenType tokenType, String authToken, String secretToken, Map<String, Object> clientConfigProperties) {
        this.apiVersion = apiVersion;
        apiClient = new GitLabApiClient(apiVersion, hostUrl, tokenType, authToken, secretToken, clientConfigProperties);
        commitsApi = new CommitsApi(this);
        deployKeysApi = new DeployKeysApi(this);
        eventsApi = new EventsApi(this);
        groupApi = new GroupApi(this);
        issuesApi = new IssuesApi(this);
        jobApi = new JobApi(this);
        labelsApi = new LabelsApi(this);
        mergeRequestApi = new MergeRequestApi(this);
        mileStonesApi = new MileStonesApi(this);
        namespaceApi = new NamespaceApi(this);
        notesApi = new NotesApi(this);
        pipelineApi = new PipelineApi(this);
        projectApi = new ProjectApi(this);
        repositoryApi = new RepositoryApi(this);
        repositoryFileApi = new RepositoryFileApi(this);
        servicesApi = new ServicesApi(this);
        sessoinApi = new SessionApi(this);
        userApi = new UserApi(this);
    }

    /**
     * Sets up all future calls to the GitLab API to be done as another user specified by sudoAsUsername.
     * To revert back to normal non-sudo operation you must call unsudo(), or pass null as the username.
     *
     * @param sudoAsUsername the username to sudo as, null will turn off sudo
     * @throws GitLabApiException if any exception occurs
     */
    public void sudo(String sudoAsUsername) throws GitLabApiException {

        if (sudoAsUsername == null || sudoAsUsername.trim().length() == 0) {
            apiClient.setSudoAsId(null);
            return;
        }

        // Get the User specified by username, if you are not an admin or the username is not found, this will fail
        User user = getUserApi().getUser(sudoAsUsername);
        if (user == null || user.getId() == null) {
            throw new GitLabApiException("the specified username was not found");
        }

        Integer sudoAsId = user.getId();
        apiClient.setSudoAsId(sudoAsId);
    }


    /**
     * Turns off the currently configured sudo as ID.
     */
    public void unsudo() {
        apiClient.setSudoAsId(null);
    }

    /**
     * Sets up all future calls to the GitLab API to be done as another user specified by provided user ID.
     * To revert back to normal non-sudo operation you must call unsudo(), or pass null as the sudoAsId.
     *
     * @param sudoAsId the ID of the user to sudo as, null will turn off sudo
     * @throws GitLabApiException if any exception occurs
     */
    public void setSudoAsId(Integer sudoAsId) throws GitLabApiException {

        if (sudoAsId == null) {
            apiClient.setSudoAsId(null);
            return;
        }

        // Get the User specified by the sudoAsId, if you are not an admin or the username is not found, this will fail
        User user = getUserApi().getUser(sudoAsId);
        if (user == null || user.getId() != sudoAsId) {
            throw new GitLabApiException("the specified user ID was not found");
        }

        apiClient.setSudoAsId(sudoAsId);
    }

    /**
     * Get the current sudo as ID, will return null if not in sudo mode.
     *
     * @return the current sudo as ID, will return null if not in sudo mode
     */
    public Integer getSudoAsId() {
        return (this.apiClient.getSudoAsId());
    }

    /**
     * Return the GitLab API version that this instance is using.
     *
     * @return the GitLab API version that this instance is using
     */
    public ApiVersion getApiVersion() {
        return (apiVersion);
    }

    /**
     * Get the default number per page for calls that return multiple items.
     *
     * @return the default number per page for calls that return multiple item
     */
    public int getDefaultPerPage() {
        return (defaultPerPage);
    }

    /**
     * Set the default number per page for calls that return multiple items.
     *
     * @param defaultPerPage the new default number per page for calls that return multiple item
     */
    public void setDefaultPerPage(int defaultPerPage) {
        this.defaultPerPage = defaultPerPage;
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
     * Returns true if the API is setup to ignore SSL certificate errors, otherwise returns false.
     *
     * @return true if the API is setup to ignore SSL certificate errors, otherwise returns false
     */
    public boolean getIgnoreCertificateErrors() {
        return (apiClient.getIgnoreCertificateErrors());
    }

    /**
     * Sets up the Jersey system ignore SSL certificate errors or not.
     *
     * @param ignoreCertificateErrors if true will set up the Jersey system ignore SSL certificate errors
     */
    public void setIgnoreCertificateErrors(boolean ignoreCertificateErrors) {
        apiClient.setIgnoreCertificateErrors(ignoreCertificateErrors);
    }

    /**
     * Get the version info for the GitLab server using the GitLab Version API.
     *
     * @return the version info for the GitLab server
     * @throws GitLabApiException if any exception occurs
     */
    public Version getVersion() throws GitLabApiException {

        class VersionApi extends AbstractApi {
            VersionApi(GitLabApi gitlabApi) {
                super(gitlabApi);
            }
        }

        Response response = new VersionApi(this).get(Response.Status.OK, null, "version");
        return (response.readEntity(Version.class));
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
     * Gets the DeployKeysApi instance owned by this GitLabApi instance. The DeployKeysApi is used
     * to perform all deploy key related API calls.
     *
     * @return the CommitsApi instance owned by this GitLabApi instance
     */
    public DeployKeysApi getDeployKeysApi() {
        return (deployKeysApi);
    }

    /**
     * Gets the EventsApi instance owned by this GitLabApi instance. The EventsApi is used
     * to perform all events related API calls.
     *
     * @return the EventsApi instance owned by this GitLabApi instance
     */
    public EventsApi getEventsApi() {
        return (eventsApi);
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
     * Gets the IssuesApi instance owned by this GitLabApi instance. The IssuesApi is used
     * to perform all iossue related API calls.
     *
     * @return the CommitsApi instance owned by this GitLabApi instance
     */
    public IssuesApi getIssuesApi() {
        return (issuesApi);
    }

    /**
     * Gets the JobApi instance owned by this GitLabApi instance. The JobApi is used
     * to perform all jobs related API calls.
     *
     * @return the JobsApi instance owned by this GitLabApi instance
     */
    public JobApi getJobApi() {
        return (jobApi);
    }

    public LabelsApi getLabelsApi() {
        return labelsApi;
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

    public MileStonesApi getMileStonesApi() {
        return mileStonesApi;
    }

    /**
     * Gets the NamespaceApi instance owned by this GitLabApi instance. The NamespaceApi is used
     * to perform all namespace related API calls.
     *
     * @return the NamespaceApi instance owned by this GitLabApi instance
     */
    public NamespaceApi getNamespaceApi() {
        return namespaceApi;
    }

    public void setNamespaceApi(NamespaceApi namespaceApi) {
        this.namespaceApi = namespaceApi;
    }

    /**
     * Gets the NotesApi instance owned by this GitLabApi instance. The NotesApi is used
     * to perform all notes related API calls.
     *
     * @return the NotesApi instance owned by this GitLabApi instance
     */
    public NotesApi getNotesApi() {
        return (notesApi);
    }

    /**
     * Gets the PipelineApi instance owned by this GitLabApi instance. The PipelineApi is used
     * to perform all pipeline related API calls.
     *
     * @return the PipelineApi instance owned by this GitLabApi instance
     */
    public PipelineApi getPipelineApi() {
        return (pipelineApi);
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
