package org.gitlab4j.api;

import java.util.Date;

import jakarta.ws.rs.core.Response;

import org.gitlab4j.api.models.PersonalAccessToken;
import org.gitlab4j.models.utils.ISO8601;

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
     * Rotates the personal access token used in the request header.
     * The token is revoked and a new one which will expire at the given expiresAt-date is created to replace it.
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
     * Rotates a specific personal access token.
     * The token is revoked and a new one which will expire at the given expiresAt-date is created to replace it.
     * Only working with GitLab 16.0 and above.
     *
     * <pre><code>GitLab Endpoint: POST /personal_access_tokens/:id/rotate</code></pre>
     *
     * @param id ID of the personal access token
     * @param expiresAt Expiration date of the access token
     * @return the newly created PersonalAccessToken.
     * @throws GitLabApiException if any exception occurs
     */
    public PersonalAccessToken rotatePersonalAccessToken(String id, Date expiresAt) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("expires_at", ISO8601.dateOnly(expiresAt));

        Response response = post(Response.Status.OK, formData, "personal_access_tokens", id, "rotate");
        return (response.readEntity(PersonalAccessToken.class));
    }

    /**
     * Get information about the personal access token used in the request header.
     * Only working with GitLab 16.0 and above.
     *
     * <pre><code>GitLab Endpoint: GET /personal_access_tokens/self</code></pre>
     *
     * @return the specified PersonalAccessToken.
     * @throws GitLabApiException if any exception occurs
     */
    public PersonalAccessToken getPersonalAccessToken() throws GitLabApiException {
        return getPersonalAccessToken("self");
    }

    /**
     * Get a specific personal access token.
     * Only working with GitLab 16.0 and above.
     *
     * <pre><code>GitLab Endpoint: GET /personal_access_tokens/:id</code></pre>
     *
     * @param id ID of the personal access token
     * @return the specified PersonalAccessToken.
     * @throws GitLabApiException if any exception occurs
     */
    public PersonalAccessToken getPersonalAccessToken(String id) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "personal_access_tokens", id);
        return (response.readEntity(PersonalAccessToken.class));
    }

    /**
     * Revokes a personal access token.  Available only for admin users.
     *
     * <pre><code>GitLab Endpoint: DELETE /personal_access_tokens/:token_id</code></pre>
     * @param tokenId the personal access token ID to revoke
     * @throws GitLabApiException if any exception occurs
     */
    public void revokePersonalAccessToken(Long tokenId) throws GitLabApiException {
        if (tokenId == null) {
            throw new RuntimeException("tokenId cannot be null");
        }
        Response.Status expectedStatus =
                (isApiVersion(GitLabApi.ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "personal_access_tokens", tokenId);
    }
}
