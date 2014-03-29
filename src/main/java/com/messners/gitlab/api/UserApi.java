package com.messners.gitlab.api;

import java.util.List;

import com.messners.gitlab.api.models.User;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.representation.Form;

public class UserApi extends AbstractApi {

	UserApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	

	/**
	 * Get a list of users. Only returns the first page
	 * 
	 * GET /users
	 * 
	 * @return a list of Users, this list will only contain the first 20 users in the system.
	 * @throws GitLabApiException 
	 */
	public List<User> getUsers () throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "users");
		return (response.getEntity(new GenericType<List<User>>() {}));
	}
	

	/**
	 * Get a list of users using the specified page and per page settings.
	 * 
	 * GET /users
	 * 
	 * @param page
	 * @param perPage
	 * @return the list of Users in the specified range
	 * @throws GitLabApiException 
	 */
	public List<User> getUsers (int page, int perPage) throws GitLabApiException {
		
		Form formData = new Form();
		addFormParam(formData, "page", page, false);
		addFormParam(formData, "per_page", perPage, false);		
		ClientResponse response = get(ClientResponse.Status.OK, formData, "users");
		return (response.getEntity(new GenericType<List<User>>() {}));
	}
	
	
	/**
	 * Get a single user.
	 * 
	 * GET /users/:id
	 * 
	 * @param userId
	 * @return the User instance for the specified user ID
	 * @throws GitLabApiException 
	 */
	public User getUser (int userId) throws GitLabApiException {		
		ClientResponse response = get(ClientResponse.Status.OK, null, "users", userId);
		return (response.getEntity(User.class));
	}
	
	
	/**
	 * Creates a new user. Note only administrators can create new users.
	 * 
	 * POST /users
	 * 
	 * email (required) - Email
	 * password (required) - Password
	 * username (required) - Username
	 * name (required) - Name
	 * skype (optional) - Skype ID
	 * linkedin (optional) - Linkedin
	 * twitter (optional) - Twitter account
	 * website_url (optional) - Website url
	 * projects_limit (optional) - Number of projects user can create
	 * extern_uid (optional) - External UID
	 * provider (optional) - External provider name
	 * bio (optional) - User's bio
	 * admin (optional) - User is admin - true or false (default)
	 * can_create_group (optional) - User can create groups - true or false
	 * 
	 * @param user
	 * @return created User instance
	 * @throws GitLabApiException 
	 */
	public User createUser (User user, String password, Integer projectsLimit) throws GitLabApiException {
		
		Form formData = new Form();
		addFormParam(formData, "email", user.getEmail(), true);
		addFormParam(formData, "password", password, true);
		addFormParam(formData, "username", user.getUsername(), true);
		addFormParam(formData, "name", user.getName(), true);
		addFormParam(formData, "skype", user.getSkype(), false);
		addFormParam(formData, "linkedin", user.getLinkedin(), false);
		addFormParam(formData, "twitter", user.getTwitter(), false);
		addFormParam(formData, "website_url", user.getWebsiteUrl(), false);
		addFormParam(formData, "projects_limit", projectsLimit, false);
		addFormParam(formData, "extern_uid", user.getExternUid(), false);
		addFormParam(formData, "provider", user.getProvider(), false);
		addFormParam(formData, "bio", user.getBio(), false);
		addFormParam(formData, "admin", user.getIsAdmin(), false);
		addFormParam(formData, "can_create_group", user.getCanCreateGroup(), false);		
	
		ClientResponse response = post(ClientResponse.Status.CREATED, formData, "users");
		return (response.getEntity(User.class));
	}
	
	
	/**
	 * Modifies an existing user. Only administrators can change attributes of a user.
	 * 
	 * PUT /users/:id
	 * 
	 * email (required) - Email
	 * password (required) - Password
	 * username (required) - Username
	 * name (required) - Name
	 * skype (optional) - Skype ID
	 * linkedin (optional) - Linkedin
	 * twitter (optional) - Twitter account
	 * website_url (optional) - Website url
	 * projects_limit (optional) - Number of projects user can create
	 * extern_uid (optional) - External UID
	 * provider (optional) - External provider name
	 * bio (optional) - User's bio
	 * admin (optional) - User is admin - true or false (default)
	 * can_create_group (optional) - User can create groups - true or false
	 * 
	 * @param user
	 * @return the modified User instance
	 * @throws GitLabApiException 
	 */
	public User modifyUser (User user, String password, Integer projectsLimit) throws GitLabApiException {
		
		Form formData = new Form();
		addFormParam(formData, "email", user.getEmail(), false);
		addFormParam(formData, "password", password, false);
		addFormParam(formData, "username", user.getUsername(), false);
		addFormParam(formData, "name", user.getName(), false);
		addFormParam(formData, "skype", user.getSkype(), false);
		addFormParam(formData, "linkedin", user.getLinkedin(), false);
		addFormParam(formData, "twitter", user.getTwitter(), false);
		addFormParam(formData, "website_url", user.getWebsiteUrl(), false);
		addFormParam(formData, "projects_limit", projectsLimit, false);
		addFormParam(formData, "extern_uid", user.getExternUid(), false);
		addFormParam(formData, "provider", user.getProvider(), false);
		addFormParam(formData, "bio", user.getBio(), false);
		addFormParam(formData, "admin", user.getIsAdmin(), false);
		addFormParam(formData, "can_create_group", user.getCanCreateGroup(), false);		
	
		ClientResponse response = put(ClientResponse.Status.OK, formData, "users", user.getId());
		return (response.getEntity(User.class));
	}	
	
	
	/**
	 * Deletes a user. Available only for administrators. 
	 * 
	 * DELETE /users/:id
	 * 
	 * @param userId
	 * @throws GitLabApiException 
	 */
	public void deleteUser (Integer userId) throws GitLabApiException {
		
		if (userId == null) {
			throw new RuntimeException("userId cannot be null");
		}
		
		delete(ClientResponse.Status.OK, null, "users", userId);	
	}


	/**
	 * Deletes a user. Available only for administrators. 
	 * 
	 * DELETE /users/:id
	 * 
	 * @param user
	 * @throws GitLabApiException 
	 */
	public void deleteUser (User user)  throws GitLabApiException {
		deleteUser(user.getId());
	}
}
