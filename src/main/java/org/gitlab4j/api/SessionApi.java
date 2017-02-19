package org.gitlab4j.api;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Session;

/**
 * This class implements the client side API for the GitLab login call.
 */
public class SessionApi extends AbstractApi {

    public SessionApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Login to get private token.
     * 
     * POST /session
     * 
     * @param username
     * @param email
     * @param password
     * @return a Session instance with info on the logged in user
     * @throws GitLabApiException
     */
    public Session login(String username, String email, String password) throws GitLabApiException {

        if ((username == null || username.trim().length() == 0) && (email == null || email.trim().length() == 0)) {
            throw new IllegalArgumentException("both username and email cannot be empty or null");
        }

        Form formData = new Form();
        addFormParam(formData, "email", email, false);
        addFormParam(formData, "password", password, true);
        addFormParam(formData, "login", username, false);

        Response response = post(Response.Status.CREATED, formData, "session");
        return (response.readEntity(Session.class));
    }
}
