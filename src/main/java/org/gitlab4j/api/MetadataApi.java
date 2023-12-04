package org.gitlab4j.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.gitlab4j.api.models.Metadata;

/**
 * This class implements the client side API for the Gitlab metadata call.
 *
 * @see <a href="https://https://docs.gitlab.com/ee/api/metadata.html">Metadata API at Gitlab</a>
 */
public class MetadataApi extends AbstractApi {

    public MetadataApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get Gitlab metadata
     *
     * <pre><code>Gitlab Endpoint: GET /metadata</code></pre>
     *
     * @return Gitlab metadata
     * @throws GitLabApiException if any exception occurs
     */
    public Metadata getMetadata() throws GitLabApiException {
        Response response = get(Status.OK, null, "metadata");
        return (response.readEntity(Metadata.class));
    }

}
