package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.LicenseTemplate;

/**
 * This class provides an entry point to all the GitLab API licenses calls.
 */
public class LicensesApi extends AbstractApi {

    public LicensesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all license templates.
     *
     * GET /licenses
     *
     * @return a list of LicenseTemplate instances
     * @throws GitLabApiException if any exception occurs
     */
    public List<LicenseTemplate> getAllLicenseTemplates() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "licenses");
        return (response.readEntity(new GenericType<List<LicenseTemplate>>() {}));
    }

    /**
     * Get popular license templates.
     *
     * GET /licenses
     *
     * @return a list of LicenseTemplate instances
     * @throws GitLabApiException if any exception occurs
     */
    public List<LicenseTemplate> getPopularLicenseTemplates() throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("popular", true, true);
        Response response = get(Response.Status.OK, formData.asMap(), "licenses");
        return (response.readEntity(new GenericType<List<LicenseTemplate>>() {}));
    }

    /**
     * Get a single license template.
     *
     * GET /licenses
     *
     * @param key The key of the license template
     * @return a LicenseTemplate instance
     * @throws GitLabApiException if any exception occurs
     */
    public LicenseTemplate getSingleLicenseTemplate(String key) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "licenses", key);
        return (response.readEntity(LicenseTemplate.class));
    }
}