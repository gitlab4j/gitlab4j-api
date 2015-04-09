package com.messners.gitlab.api;


/**
 * This class is provides a simplified interface to a GitLab API server, and divides the API up into
 * a separate API class for each concern.
 * 
 * @author Greg Messner <greg@messners.com>
 */
public class GitLabApi {
	
	GitLabApiClient apiClient;
	private CommitsApi commitsApi;
	private GroupApi groupApi;
	private MergeRequestApi mergeRequestApi;
	private ProjectApi projectApi;
	private RepositoryApi repositoryApi;
	private SessionApi sessoinApi;
	private UserApi userApi;

	/**
	 * Logs into GitLab using provided {@code username} and {@code password}, and creates a new
	 * {@code GitLabApi} instance using returned private token
	 * @param url GitLab URL
	 * @param username user name for which private token should be obtained
	 * @param password password for a given {@code username}
	 * @return new {@code GitLabApi} instance configured for a user-specific token
	 */
	static public GitLabApi create(String url, String username, String password) throws GitLabApiException {
		String token = new SessionApi(new GitLabApi(url, null)).login(username, null, password).getPrivateToken();
		return new GitLabApi(url, token);
	}


	/**
	 * Constructs a GitLabApi instance set up to interact with the GitLab server
	 * specified by hostUrl.
	 * 
	 * @param hostUrl
	 * @param privateToken 
	 */
	public GitLabApi (String hostUrl, String privateToken) {	
		apiClient = new GitLabApiClient(hostUrl, privateToken);
		commitsApi = new CommitsApi(this);
		groupApi = new GroupApi(this);
		mergeRequestApi = new MergeRequestApi(this);
		projectApi = new ProjectApi(this);
		repositoryApi = new RepositoryApi(this);
		sessoinApi = new SessionApi(this);
		userApi = new UserApi(this);
	}
	
	
	/**
	 * Return the GitLabApiClient associated with this instance.  This is used by all the sub API classes
	 * to communicate with the GitLab API.
	 * 
	 * @return the GitLabApiClient associated with this instance
	 */
	GitLabApiClient getApiClient () {
		return (apiClient);
	}
	
	
	/**
	 * Gets the CommitsApi instance owned by this GitLabApi instance.  The CommitsApi is used
	 * to perform all commit related API calls.
	 * 
	 * @return the CommitsApi instance owned by this GitLabApi instance
	 */
	public CommitsApi getCommitsApi () {
		return (commitsApi);		
	}
	
		
	/**
	 * Gets the MergeRequestApi instance owned by this GitLabApi instance.  The MergeRequestApi is used
	 * to perform all merge request related API calls.
	 * 
	 * @return the MergeRequestApi instance owned by this GitLabApi instance
	 */
	public MergeRequestApi getMergeRequestApi () {
		return (mergeRequestApi);		
	}
	
	
	/**
	 * Gets the GroupApi instance owned by this GitLabApi instance.  The GroupApi is used
	 * to perform all group related API calls.
	 * 
	 * @return the GroupApi instance owned by this GitLabApi instance
	 */
	public GroupApi getGroupApi () {
		return (groupApi);		
	}
	

	/**
	 * Gets the ProjectApi instance owned by this GitLabApi instance.  The ProjectApi is used
	 * to perform all project related API calls.
	 * 
	 * @return the ProjectApi instance owned by this GitLabApi instance
	 */
	public ProjectApi getProjectApi () {
		return (projectApi);		
	}
	
	
	/**
	 * Gets the RepositoryApi instance owned by this GitLabApi instance.  The RepositoryApi is used
	 * to perform all repository related API calls.
	 * 
	 * @return the RepositoryApi instance owned by this GitLabApi instance
	 */
	public RepositoryApi getRepositoryApi () {
		return (repositoryApi);		
	}	
	
	
	/**
	 * Gets the SessionApi instance owned by this GitLabApi instance.  The SessionApi is used
	 * to perform a login to the GitLab API.
	 * 
	 * @return the SessionApi instance owned by this GitLabApi instance
	 */
	public SessionApi getSessionApi () {
		return (sessoinApi);
	}
	
	
	/**
	 * Gets the UserApi instance owned by this GitLabApi instance.  The UserApi is used
	 * to perform all user related API calls.
	 * 
	 * @return the UserApi instance owned by this GitLabApi instance
	 */
	public UserApi getUserApi () {
		return (userApi);		
	}
}
