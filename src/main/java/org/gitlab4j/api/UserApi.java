package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.User;

/**
 * This class provides an entry point to all the GitLab API users calls.
 */
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

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("page", page, false)
                .withParam("per_page", perPage, false);
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
        GitLabApiForm formData = new GitLabApiForm().withParam("search", emailOrUsername, true);
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
        Form formData = userToForm(user, projectsLimit, password, true);
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
        Form form = userToForm(user, projectsLimit, password, false);
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

    /**
     * Populate the REST form with data from the User instance.
     * 
     * @param user the User iunstance to populate the Form instance with
     * @param projectsLimit the maximum number of projects the user is allowed (optional)
     * @param password the password, required when creating a new user
     * @param create whether the form is being populated to create a new user
     * @return the populated Form instance
     */
    Form userToForm(User user, Integer projectsLimit, String password, boolean create) {
        return (new GitLabApiForm()
                .withParam("email", user.getEmail(), create)
                .withParam("password", password, create)
                .withParam("username", user.getUsername(), create)
                .withParam("name", user.getName(), create)
                .withParam("skype", user.getSkype(), false)
                .withParam("linkedin", user.getLinkedin(), false)
                .withParam("twitter", user.getTwitter(), false)
                .withParam("website_url", user.getWebsiteUrl(), false)
                .withParam("projects_limit", projectsLimit, false)
                .withParam("external", user.getExternal(), false)
                .withParam("provider", user.getProvider(), false)
                .withParam("bio", user.getBio(), false)
                .withParam("location", user.getLocation(), false)
                .withParam("admin", user.getIsAdmin(), false)
                .withParam("can_create_group", user.getCanCreateGroup(), false)
                .withParam("external", user.getExternal(), false));
    }
}
