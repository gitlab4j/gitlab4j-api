package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.SshKey;
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
     * @throws GitLabApiException if any exception occurs
     */
    public List<User> getUsers() throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "users");
        return (response.readEntity(new GenericType<List<User>>() {}));
    }

    /**
     * Get a list of users using the specified page and per page settings.
     *
     * GET /users
     *
     * @param page the page to get
     * @param perPage the number of users per page
     * @return the list of Users in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<User> getUsers(int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "users");
        return (response.readEntity(new GenericType<List<User>>() {}));
    }

    /**
     * Get a Pager of users.
     *
     * GET /users
     *
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager of User
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<User> getUsers(int itemsPerPage) throws GitLabApiException {
        return (new Pager<User>(this, User.class, itemsPerPage, null, "users"));
    }

    /**
     * Get a single user.
     *
     * GET /users/:id
     *
     * @param userId the ID of the user to get
     * @return the User instance for the specified user ID
     * @throws GitLabApiException if any exception occurs
     */
    public User getUser(int userId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "users", userId);
        return (response.readEntity(User.class));
    }

    /**
     * Lookup a user by username.
     *
     * NOTE: This is for admin users only.
     *
     * GET /users?username=:username
     *
     * @param username the username of the user to get
     * @return the User instance for the specified username
     * @throws GitLabApiException if any exception occurs
     */
    public User getUser(String username) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("username", username, true);
        Response response = get(Response.Status.OK, formData.asMap(), "users");
        List<User> users = response.readEntity(new GenericType<List<User>>() {});
        return (users.isEmpty() ? null : users.get(0));
    }

    /**
     * Search users by Email or username
     *
     * GET /users?search=:email_or_username
     *
     * @param emailOrUsername the email or username to search for
     * @return the User List with the email or username like emailOrUsername
     * @throws GitLabApiException if any exception occurs
     */
    public List<User> findUsers(String emailOrUsername) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("search", emailOrUsername, true).withParam(PER_PAGE_PARAM,  getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "users");
        return (response.readEntity(new GenericType<List<User>>() {}));
    }

    /**
     * Search users by Email or username in the specified page range.
     *
     * GET /users?search=:email_or_username
     *
     * @param emailOrUsername the email or username to search for
     * @param page the page to get
     * @param perPage the number of users per page
     * @return the User List with the email or username like emailOrUsername in the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<User> findUsers(String emailOrUsername, int page, int perPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("search", emailOrUsername, true).withParam(PAGE_PARAM,  page).withParam(PER_PAGE_PARAM,  perPage);
        Response response = get(Response.Status.OK, formData.asMap(), "users");
        return (response.readEntity(new GenericType<List<User>>() {}));
    }

    /**
     * Search users by Email or username and return a Pager
     *
     * GET /users?search=:email_or_username
     *
     * @param emailOrUsername the email or username to search for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the User Pager with the email or username like emailOrUsername
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<User> findUsers(String emailOrUsername, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("search", emailOrUsername, true);
        return (new Pager<User>(this, User.class, itemsPerPage, formData.asMap(), "users"));
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
     * @param user the User instance with the user info to create
     * @param password the password for the new user
     * @param projectsLimit the maximum number of project
     * @return created User instance
     * @throws GitLabApiException if any exception occurs
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
     * @param user the User instance with the user info to modify
     * @param password the new password for the user
     * @param projectsLimit the maximum number of project
     * @return the modified User instance
     * @throws GitLabApiException if any exception occurs
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
     * @param userId the user ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteUser(Integer userId) throws GitLabApiException {

        if (userId == null) {
            throw new RuntimeException("userId cannot be null");
        }

        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "users", userId);
    }

    /**
     * Deletes a user. Available only for administrators.
     *
     * DELETE /users/:id
     *
     * @param user the User instance to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteUser(User user) throws GitLabApiException {
        deleteUser(user.getId());
    }

   /**
    * Get currently authenticated user.
    *
    * GET /user
    *
    * @return the User instance for the currently authenticated user
    * @throws GitLabApiException if any exception occurs
    */
   public User getCurrentUser() throws GitLabApiException {
       Response response = get(Response.Status.OK, null, "user");
       return (response.readEntity(User.class));
   }

    /**
     * Get a list of currently authenticated user's SSH keys.
     *
     * GET /user/keys
     *
     * @return a list of currently authenticated user's SSH keys
     * @throws GitLabApiException if any exception occurs
     */
    public List<SshKey> getSshKeys() throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "user", "keys");
        return (response.readEntity(new GenericType<List<SshKey>>() {}));
    }

    /**
     * Get a list of a specified user's SSH keys. Available only for admin users.
     *
     * GET /users/:id/keys
     *
     * @param userId the ID of the user to get SSH keys for
     * @return a list of a specified user's SSH keys
     * @throws GitLabApiException if any exception occurs
     */
    public List<SshKey> getSshKeys(Integer userId) throws GitLabApiException {

        if (userId == null) {
            throw new RuntimeException("userId cannot be null");
        }

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "users", userId, "keys");
        return (response.readEntity(new GenericType<List<SshKey>>() {}));
    }

    /**
     * Get a single SSH Key.
     *
     * GET /user/keys/:key_id
     *
     * @param keyId the ID of the SSH key.
     * @return an SshKey instance holding the info on the SSH key specified by keyId
     * @throws GitLabApiException if any exception occurs
     */
    public SshKey getSshKey(Integer keyId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "user", "keys", keyId);
        return (response.readEntity(SshKey.class));
    }

    /**
     * Creates a new key owned by the currently authenticated user.
     *
     * POST /user/keys
     *
     * @param title the new SSH Key's title
     * @param key the new SSH key
     * @return an SshKey instance with info on the added SSH key
     * @throws GitLabApiException if any exception occurs
     */
    public SshKey addSshKey(String title, String key) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("title", title).withParam("key", key);
        Response response = post(Response.Status.CREATED, formData, "user", "keys");
        return (response.readEntity(SshKey.class));
    }

    /**
     * Create new key owned by specified user. Available only for admin users.
     *
     * POST /users/:id/keys
     *
     * @param userId the ID of the user to add the SSH key for
     * @param title the new SSH Key's title
     * @param key the new SSH key
     * @return an SshKey instance with info on the added SSH key
     * @throws GitLabApiException if any exception occurs
     */
    public SshKey addSshKey(Integer userId, String title, String key) throws GitLabApiException {

        if (userId == null) {
            throw new RuntimeException("userId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("title", title).withParam("key", key);
        Response response = post(Response.Status.CREATED, formData, "users", userId, "keys");
        return (response.readEntity(SshKey.class));
    }

    /**
     * Deletes key owned by currently authenticated user. This is an idempotent function and calling it 
     * on a key that is already deleted or not available results in success.
     *
     * DELETE /user/keys/:key_id
     *
     * @param keyId the key ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteSshKey(Integer keyId) throws GitLabApiException {

        if (keyId == null) {
            throw new RuntimeException("keyId cannot be null");
        }

        delete(Response.Status.OK, null, "user", "keys", keyId);
    }

    /**
     * Deletes key owned by a specified user. Available only for admin users.
     *
     * DELETE /users/:id/keys/:key_id
     *
     * @param userId the user ID of the user to delete the key for
     * @param keyId the key ID to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteSshKey(Integer userId, Integer keyId) throws GitLabApiException {

        if (userId == null) {
            throw new RuntimeException("userId cannot be null");
        }

        if (keyId == null) {
            throw new RuntimeException("keyId cannot be null");
        }

        delete(Response.Status.OK, null, "users", userId, "keys", keyId);
    }

    /**
     * Populate the REST form with data from the User instance.
     *
     * @param user the User instance to populate the Form instance with
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
