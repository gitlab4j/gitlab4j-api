package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.User;

public class UserApi extends AbstractApi {

    UserApi(GitLabApi gitLabApi) {
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
    public List<User> getUsers() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "users");
        return (response.readEntity(new GenericType<List<User>>() {
        }));
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
    public List<User> getUsers(int page, int perPage) throws GitLabApiException {

        Form formData = new Form();
        addFormParam(formData, "page", page, false);
        addFormParam(formData, "per_page", perPage, false);
        Response response = get(Response.Status.OK, formData.asMap(), "users");
        return (response.readEntity(new GenericType<List<User>>() {
        }));
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
    public User getUser(int userId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "users", userId);
        return (response.readEntity(User.class));
    }

    /**
     * Search users by Email or username
     *
     * GET /users?search=:email_or_username
     * 
     * @param emailOrUsername
     * @return the User List with the email or username like emailOrUsername
     * @throws GitLabApiException
     */
    public List<User> findUsers(String emailOrUsername) throws GitLabApiException {
        Form formData = new Form();
        addFormParam(formData, "search", emailOrUsername, true);
        Response response = get(Response.Status.OK, formData.asMap(), "users");
        return (response.readEntity(new GenericType<List<User>>() {
        }));
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
    public User createUser(User user, String password, Integer projectsLimit) throws GitLabApiException {
        Form formData = user2form(user, projectsLimit, password, true);
        Response response = post(Response.Status.CREATED, formData, "users");
        return (response.readEntity(User.class));
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
    public User modifyUser(User user, String password, Integer projectsLimit) throws GitLabApiException {
        Form form = user2form(user, projectsLimit, password, false);
        Response response = put(Response.Status.OK, form.asMap(), "users", user.getId());
        return (response.readEntity(User.class));
    }

    /**
     * Deletes a user. Available only for administrators.
     * 
     * DELETE /users/:id
     * 
     * @param userId
     * @throws GitLabApiException
     */
    public void deleteUser(Integer userId) throws GitLabApiException {

        if (userId == null) {
            throw new RuntimeException("userId cannot be null");
        }

        delete(Response.Status.OK, null, "users", userId);
    }

    /**
     * Deletes a user. Available only for administrators.
     * 
     * DELETE /users/:id
     * 
     * @param user
     * @throws GitLabApiException
     */
    public void deleteUser(User user) throws GitLabApiException {
        deleteUser(user.getId());
    }

    private Form user2form(User user, Integer projectsLimit, String password, boolean isCreate) {
        Form form = new Form();
        addFormParam(form, "email", user.getEmail(), isCreate);
        addFormParam(form, "password", password, isCreate);
        addFormParam(form, "username", user.getUsername(), isCreate);
        addFormParam(form, "name", user.getName(), isCreate);
        addFormParam(form, "skype", user.getSkype(), false);
        addFormParam(form, "linkedin", user.getLinkedin(), false);
        addFormParam(form, "twitter", user.getTwitter(), false);
        addFormParam(form, "website_url", user.getWebsiteUrl(), false);
        addFormParam(form, "projects_limit", projectsLimit, false);
        addFormParam(form, "external", user.getExternal(), false);
        addFormParam(form, "provider", user.getProvider(), false);
        addFormParam(form, "bio", user.getBio(), false);
        addFormParam(form, "location", user.getLocation(), false);
        addFormParam(form, "admin", user.getIsAdmin(), false);
        addFormParam(form, "can_create_group", user.getCanCreateGroup(), false);
        addFormParam(form, "external", user.getExternal(), false);
        return form;
    }
}
