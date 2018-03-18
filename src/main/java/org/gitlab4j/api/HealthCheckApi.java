package org.gitlab4j.api;

import org.gitlab4j.api.models.LivenessHealthCheck;
import org.gitlab4j.api.models.ReadinessHealthCheck;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;

public class HealthCheckApi extends AbstractApi {
    public HealthCheckApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get Health Checks from the liveness endpoint.
     * Requires ip_whitelist
     *
     * GET /-/liveness
     *
     * @return LivenessHealthCheck instance
     * @throws GitLabApiException if any exception occurs
     */
    public LivenessHealthCheck getLiveness() throws GitLabApiException, IOException {
        URL livenessUrl = getApiClient().getUrlWithBase("-", "liveness");
        Response response = get(Response.Status.OK, null, livenessUrl);
        return (response.readEntity(LivenessHealthCheck.class));
    }

    /**
     * Get Health Checks from the liveness endpoint.
     *
     * GET /-/liveness
     *
     * @param token Health Status token
     * @return LivenessHealthCheck instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated
     */
    public LivenessHealthCheck getLiveness(String token) throws GitLabApiException, IOException {
        URL livenessUrl = getApiClient().getUrlWithBase("-", "liveness");
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("token", token, false);
        Response response = get(Response.Status.OK, formData.asMap(), livenessUrl);
        return (response.readEntity(LivenessHealthCheck.class));
    }

    /**
     * Get Health Checks from the readiness endpoint.
     * Requires ip_whitelist
     *
     * GET /-/readiness
     *
     * @return ReadinessHealthCheck instance
     * @throws GitLabApiException if any exception occurs
     */
    public ReadinessHealthCheck getReadiness() throws GitLabApiException, IOException {
        URL readinessUrl = getApiClient().getUrlWithBase("-", "readiness");
        Response response = get(Response.Status.OK, null, readinessUrl);
        return (response.readEntity(ReadinessHealthCheck.class));
    }

    /**
     * Get Health Checks from the readiness endpoint.
     *
     * GET /-/readiness
     *
     * @param token Health Status token
     * @return LivenessHealthCheck instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated
     */
    public ReadinessHealthCheck getReadiness(String token) throws GitLabApiException, IOException {
        URL readinessUrl = getApiClient().getUrlWithBase("-", "readiness");
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("token", token, false);
        Response response = get(Response.Status.OK, formData.asMap(), readinessUrl);
        return (response.readEntity(ReadinessHealthCheck.class));
    }
}
