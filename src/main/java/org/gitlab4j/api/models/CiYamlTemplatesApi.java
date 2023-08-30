package org.gitlab4j.api.models;

import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.gitlab4j.api.AbstractApi;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

/**
 * This class provides an entry point to all the GitLab CI YAML API calls.
 *
 * @see <a href="https://https://docs.gitlab.com/ee/api/templates/gitlab_ci_ymls.html">GitLab CI YAML API</a>
 */
public class CiYamlTemplatesApi extends AbstractApi {

    public CiYamlTemplatesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all GitLab CI/CD YAML templates.
     *
     * <pre><code>GitLab Endpoint: GET /templates/gitlab_ci_ymls</code></pre>
     *
     * @return a list of Gitlab CI YAML Templates
     * @throws GitLabApiException if any exception occurs
     */
    public List<CiYamlTemplatesElement> getAllCiYamlTemplates() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "templates", "gitlab_ci_ymls");
        return (response.readEntity(new GenericType<List<CiYamlTemplatesElement>>() {}));
    }

    /**
     * Get a single GitLab CI/CD YAML template.
     *
     * <pre><code>GitLab Endpoint: GET /templates/gitlab_ci_ymls/:key</code></pre>
     *
     * @param key The key of the GitLab CI YAML template
     * @return an Gitlab CI YAML Template
     * @throws GitLabApiException if any exception occurs
     */
    public CiYamlTemplate getSingleCiYamlTemplate(String key) throws GitLabApiException {
        Response response = get(Status.OK, null, "templates", "gitlab_ci_ymls", key);
        return (response.readEntity(CiYamlTemplate.class));
    }

}
