package org.gitlab4j.api;

import java.util.Collections;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Key;

/**
 * See:
 * <a href="https://docs.gitlab.com/ee/api/keys.html">GitLab Key API Documentaion</a>
 */
public class KeysApi extends AbstractApi {
    public KeysApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * @param fingerprint The md5 hash of a ssh public key with : separating the bytes Or SHA256:$base64hash
     * @return The Key which includes the user who owns the key
     * @throws GitLabApiException If anything goes wrong
     */
    public Key getUserBySSHKeyFingerprint(String fingerprint) throws GitLabApiException {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.put("fingerprint", Collections.singletonList(fingerprint));
        Response response = get(Response.Status.OK, queryParams, "keys");
        return response.readEntity(Key.class);
    }

    /**
     * Get a single key by id.
     *
     * <pre><code>GitLab Endpoint: GET /keys/:id</code></pre>
     *
     * @param keyId the IID of the key to get
     * @return a Key instance
     * @throws GitLabApiException if any exception occurs
     */
    public Key getKey(String keyId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "keys", keyId);
        return response.readEntity(Key.class);
    }
}
