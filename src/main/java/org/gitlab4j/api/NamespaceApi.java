package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Namespace;

/**
 * This class implements the client side API for the GitLab namespace calls.
 */
public class NamespaceApi extends AbstractApi {

    public NamespaceApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of the namespaces of the authenticated user. If the user is an administrator,
     * a list of all namespaces in the GitLab instance is shown.
     * 
     * GET /namespaces
     * 
     * @return a List of Namespace instances
     * @throws GitLabApiException if any exception occurs
     */
    public List<Namespace> getNamespaces() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "namespaces");
        return (response.readEntity(new GenericType<List<Namespace>>() {
        }));
    }

    /**
     * Get all namespaces that match a string in their name or path.
     *
     * GET /namespaces?search=:query
     * 
     * @param query the search string
     * @return the Namespace List with the matching namespaces
     * @throws GitLabApiException if any exception occurs
     */
    public List<Namespace> findNamespaces(String query) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm().withParam("search", query, true);
        Response response = get(Response.Status.OK, formData.asMap(), "namespaces");
        return (response.readEntity(new GenericType<List<Namespace>>() {
        }));
    }
}
