package org.gitlab4j.api;

import javax.ws.rs.core.Response;
import org.gitlab4j.api.models.PersonalAccessToken;
import org.gitlab4j.api.utils.ISO8601;

import java.util.Date;

/**
 * This class provides an entry point to all the GitLab API personal access token calls.
 *
 * @see <a href="https://docs.gitlab.com/ce/api/personal_access_tokens.html">Personal access token API at GitLab</a>
 */
public class PersonalAccessTokenApi extends AbstractApi {

    public PersonalAccessTokenApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Rotates the given personal access token.
     * The token is revoked and a new one which will expire in one week is created to replace it.
     * Only working with GitLab 16.0 and above.
     *
     * <pre><code>GitLab Endpoint: POST /personal_access_tokens/self/rotate</code></pre>
     *
     * @return the newly created PersonalAccessToken.
     * @throws GitLabApiException if any exception occurs
     */
    public PersonalAccessToken rotatePersonalAccessToken() throws GitLabApiException {
        return rotatePersonalAccessToken(null);
    }

    /**
     * Rotates the given personal access token.
     * The token is revoked and a new one which will expire in one week is created to replace it.
     * Only working with GitLab 16.0 and above.
     * 
     * <pre><code>GitLab Endpoint: POST /personal_access_tokens/self/rotate</code></pre>
     *
     * @param expiresAt Expiration date of the access token
     * @return the newly created PersonalAccessToken.
     * @throws GitLabApiException if any exception occurs
     */
    public PersonalAccessToken rotatePersonalAccessToken(Date expiresAt) throws GitLabApiException {
        return rotatePersonalAccessToken("self", expiresAt);
    }

    /**
     * Rotates the given personal access token.
     * The token is revoked and a new one which will expire in one week is created to replace it.
     * Only working with GitLab 16.0 and above.
     *
     * <pre><code>GitLab Endpoint: POST /personal_access_tokens/:id/rotate</code></pre>
     *
     * @param expiresAt Expiration date of the access token
     * @return the newly created PersonalAccessToken.
     * @throws GitLabApiException if any exception occurs
     */
    public PersonalAccessToken rotatePersonalAccessToken(String id, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("expires_at", ISO8601.dateOnly(expiresAt));
        
        Response response = post(Response.Status.OK, formData, "personal_access_tokens", id, "rotate");
        return (response.readEntity(PersonalAccessToken.class));
    }
}
