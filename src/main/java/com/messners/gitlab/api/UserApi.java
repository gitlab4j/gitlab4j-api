package com.messners.gitlab.api;

import java.io.IOException;
import java.util.List;

import com.messners.gitlab.api.models.ErrorMessage;
import com.messners.gitlab.api.models.User;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.representation.Form;

public class UserApi extends AbstractApi {

	UserApi (GitLabApi gitLabApi) {
		super(gitLabApi);
	}
	

	/**
	 * GET /users
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<User> getProjects () throws IOException {	
		 ClientResponse response = get(null, "users");
		 return (response.getEntity(new GenericType<List<User>>() {}));
	}
	
	
	/**
	 * GET /users/:id
	 * 
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public User getUser (int userId) throws IOException {
		ClientResponse response = get(null, "users", userId);
		return (response.getEntity(User.class));
	}
	
	
	/**
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
	 * @return
	 * @throws IOException
	 */
	public User createUser (User user, String password, Integer projectsLimit) throws IOException {
		
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
	
		ClientResponse response = post(formData, "users");
		if (response.getStatus() != ClientResponse.Status.CREATED.getStatusCode()) {
			ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
			throw new RuntimeException(errorMessage.getMessage());
		}
		
		return (response.getEntity(User.class));
	}
	
	
	/**
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
	 * @return
	 * @throws IOException
	 */
	public User modifyUser (User user, String password, Integer projectsLimit) throws IOException {
		
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
	
		ClientResponse response = put(formData, "users", user.getId());
		return (response.getEntity(User.class));
	}	
	
	
	/**
	 * DELETE /users/:id
	 * 
	 * @param userId
	 */
	public boolean deleteUser (Integer userId) throws IOException {
		
		if (userId == null) {
			throw new RuntimeException("userId cannot be null");
		}
		
		ClientResponse response = delete(null, "users", userId);
		return (response.getStatus() == ClientResponse.Status.OK.getStatusCode());		
	}


	/**
	 * DELETE /users/:id
	 * 
	 * @param user
	 */
	public boolean deleteUser (User user)  throws IOException {
		return (deleteUser(user.getId()));
	}
}
