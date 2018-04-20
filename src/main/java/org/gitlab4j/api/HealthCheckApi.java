package org.gitlab4j.api;

import org.gitlab4j.api.models.HealthCheckInfo;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;

public class HealthCheckApi extends AbstractApi {
    public HealthCheckApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get Health Checks from the liveness endpoint.
     *
     * Requires ip_whitelist
     * https://docs.gitlab.com/ee/administration/monitoring/ip_whitelist.html
     *
     * GET /-/liveness
     *
     * @return HealthCheckInfo instance
     * @throws GitLabApiException if any exception occurs
     */
    public HealthCheckInfo getLiveness() throws GitLabApiException, IOException {
        URL livenessUrl = getApiClient().getUrlWithBase("-", "liveness");
        Response response = get(Response.Status.OK, null, livenessUrl);
        return (response.readEntity(HealthCheckInfo.class));
    }

    /**
     * Get Health Checks from the liveness endpoint.
     *
     * Requires ip_whitelist
     * https://docs.gitlab.com/ee/administration/monitoring/ip_whitelist.html
     *
     * GET /-/liveness
     *
     * @param token Health Status token
     * @return HealthCheckInfo instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated
     */
    public HealthCheckInfo getLiveness(String token) throws GitLabApiException, IOException {
        URL livenessUrl = getApiClient().getUrlWithBase("-", "liveness");
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("token", token, false);
        Response response = get(Response.Status.OK, formData.asMap(), livenessUrl);
        return (response.readEntity(HealthCheckInfo.class));
    }

    /**
     * Get Health Checks from the readiness endpoint.
     *
     * Requires ip_whitelist
     * https://docs.gitlab.com/ee/administration/monitoring/ip_whitelist.html
     *
     * GET /-/readiness
     *
     * @return HealthCheckInfo instance
     * @throws GitLabApiException if any exception occurs
     */
    public HealthCheckInfo getReadiness() throws GitLabApiException, IOException {
        URL readinessUrl = getApiClient().getUrlWithBase("-", "readiness");
        Response response = get(Response.Status.OK, null, readinessUrl);
        return (response.readEntity(HealthCheckInfo.class));
    }

    /**
     * Get Health Checks from the readiness endpoint.
     *
     * Requires ip_whitelist
     * https://docs.gitlab.com/ee/administration/monitoring/ip_whitelist.html
     *
     * GET /-/readiness
     *
     * @param token Health Status token
     * @return HealthCheckInfo instance
     * @throws GitLabApiException if any exception occurs
     * @deprecated
     */
    public HealthCheckInfo getReadiness(String token) throws GitLabApiException, IOException {
        URL readinessUrl = getApiClient().getUrlWithBase("-", "readiness");
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("token", token, false);
        Response response = get(Response.Status.OK, formData.asMap(), readinessUrl);
        return (response.readEntity(HealthCheckInfo.class));
    }
}
