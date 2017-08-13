package org.gitlab4j.api;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Session;
import org.gitlab4j.api.models.Version;

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
    private GroupApi groupApi;
    private MergeRequestApi mergeRequestApi;
    private NamespaceApi namespaceApi;
    private PipelineApi pipelineApi;
    private ProjectApi projectApi;
    private RepositoryApi repositoryApi;
    private RepositoryFileApi repositoryFileApi;
    private ServicesApi servicesApi;
    private SessionApi sessoinApi;
    private UserApi userApi;
    private JobApi jobApi;
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
        SessionApi sessionApi = new SessionApi(new GitLabApi(apiVersion, url, (String)null));
        Session session = sessionApi.login(username, null, password);
        return (new GitLabApi(apiVersion, url, session));
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
        return (login(ApiVersion.V4, url, username, password));
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
     * @param privateToken to private token to use for access to the API
     */
    public GitLabApi(ApiVersion apiVersion, String hostUrl, String privateToken) {
        this(apiVersion, hostUrl, privateToken, null);
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
        this(apiVersion, hostUrl, session.getPrivateToken(), null);
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
     * @param privateToken to private token to use for access to the API
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApi(String hostUrl, String privateToken, String secretToken) {
        this(ApiVersion.V4, hostUrl, privateToken, secretToken);
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
        this.apiVersion = apiVersion;
        apiClient = new GitLabApiClient(apiVersion, hostUrl, privateToken, secretToken, clientConfigProperties);
        commitsApi = new CommitsApi(this);
        groupApi = new GroupApi(this);
        mergeRequestApi = new MergeRequestApi(this);
        namespaceApi = new NamespaceApi(this);
        pipelineApi = new PipelineApi(this);
        projectApi = new ProjectApi(this);
        repositoryApi = new RepositoryApi(this);
        servicesApi = new ServicesApi(this);
        sessoinApi = new SessionApi(this);
        userApi = new UserApi(this);
        repositoryFileApi = new RepositoryFileApi(this);
        jobApi = new JobApi(this);
        notesApi = new NotesApi(this);
        eventsApi = new EventsApi(this);
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
        this(ApiVersion.V4, hostUrl, privateToken, secretToken, clientConfigProperties);
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
     * WARNING: Setting this to true will affect ALL uses of HttpsURLConnection and Jersey.
     *
     * @param ignoreCertificateErrors if true will set up the Jersey system ignore SSL certificate errors
     */
    public void setIgnoreCerificateErrors(boolean ignoreCertificateErrors) {
        apiClient.setIgnoreCerificateErrors(ignoreCertificateErrors);
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
     * Gets the MergeRequestApi instance owned by this GitLabApi instance. The MergeRequestApi is used
     * to perform all merge request related API calls.
     *
     * @return the MergeRequestApi instance owned by this GitLabApi instance
     */
    public MergeRequestApi getMergeRequestApi() {
        return (mergeRequestApi);
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
     * Gets the GroupApi instance owned by this GitLabApi instance. The GroupApi is used
     * to perform all group related API calls.
     *
     * @return the GroupApi instance owned by this GitLabApi instance
     */
    public GroupApi getGroupApi() {
        return (groupApi);
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

    /**
     * Gets the JobApi instance owned by this GitLabApi instance. The JobApi is used
     * to perform all jobs related API calls.
     *
     * @return the JobsApi instance owned by this GitLabApi instance
     */
    public JobApi getJobApi() {
        return (jobApi);
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
     * Gets the EventsApi instance owned by this GitLabApi instance. The EventsApi is used
     * to perform all events related API calls.
     *
     * @return the EventsApi instance owned by this GitLabApi instance
     */
    public EventsApi getEventsApi() {
        return (eventsApi);
    }
}
