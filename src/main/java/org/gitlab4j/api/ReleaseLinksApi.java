package org.gitlab4j.api;

import org.gitlab4j.api.models.Link;
import org.gitlab4j.api.models.ReleaseLinkParams;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * This class provides an entry point to all the GitLab ReleaseLinks API calls.
 * @see <a href="https://docs.gitlab.com/ce/api/releases/links.html">ReleaseLinks API at GitLab</a>
 */
public class ReleaseLinksApi extends AbstractApi {

    public ReleaseLinksApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get assets as Links from a Release.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/releases/:tagName/assets/links</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the tag name that the release was created from
     * @return the list of assets for the specified release
     * @throws GitLabApiException if any exception occurs
     */
    public List<Link> getLinks(Object projectIdOrPath, String tagName) throws GitLabApiException {
        return (getLinks(projectIdOrPath, tagName, getDefaultPerPage()).all());
    }

    /**
     * Get assets as Links from a Release.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/releases/:tagName/assets/links</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the tag name that the release was created from
     * @param itemsPerPage the number of Link instances that will be fetched per page
     * @return the Pager of Link instances for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Link> getLinks(Object projectIdOrPath, String tagName, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Link>(this, Link.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "releases", urlEncode(tagName), "assets", "links"));
    }

    /**
     * Get a Stream of assets as Links from a Release.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/releases/:tagName/assets/links</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the tag name that the release was created from
     * @return a Stream of Link instances for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Link> getLinksStream(Object projectIdOrPath, String tagName) throws GitLabApiException {
        return (getLinks(projectIdOrPath, tagName, getDefaultPerPage()).stream());
    }

    /**
     * Get a Link for the given tag name and link id.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/releases/:tagName/assets/links/:linkId</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of the tag to fetch the Link for
     * @param linkId the id of the Link to fetch for
     * @return a Link instance with info on the specified tag and id
     * @throws GitLabApiException if any exception occurs
     */
    public Link getLink(Object projectIdOrPath, String tagName, Integer linkId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "releases", urlEncode(tagName), "assets", "links", linkId);
        return (response.readEntity(Link.class));
    }

    /**
     * Get an Optional instance holding a Link instance for the specific tag name and link id.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/releases/:tagName/assets/links/:linkId</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of the tag to fetch the Link for
     * @param linkId the id of the Link to fetch for
     * @return an Optional instance with the specified Link as the value
     * @throws GitLabApiException if any exception occurs
     */
    public Optional<Link> getOptionalLink(Object projectIdOrPath, String tagName, Integer linkId) throws GitLabApiException {
        try {
            return (Optional.ofNullable(getLink(projectIdOrPath, tagName, linkId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Create a Link. You need push access to the repository to create a Link.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/releases/:tagName/assets/links</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param params a ReleaseLinksParams instance holding the parameters for the link
     * @return a Link instance containing the newly created Link info
     * @throws GitLabApiException if any exception occurs
     */
    public Link createLink(Object projectIdOrPath, ReleaseLinkParams params) throws GitLabApiException {
        String tagName = params.getTagName();
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new RuntimeException("params.tagName cannot be null or empty");
        }

        String name = params.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("params.name cannot be null or empty");
        }

        String url = params.getUrl();
        if (url == null || url.trim().isEmpty()) {
            throw new RuntimeException("params.url cannot be null or empty");
        }

        Response response = post(Response.Status.CREATED, params,
            "projects", getProjectIdOrPath(projectIdOrPath), "releases", urlEncode(tagName), "assets", "links");
        return (response.readEntity(Link.class));
    }

    /**
     * Updates the attributes of a given Link.
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/releases/:tagName/assets/links/:linkId</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param linkId          the id of the Link to fetch for
     * @param params          a ReleaseLinksParams instance holding the parameters for the Link
     * @return a Link instance containing info on the updated Link
     * @throws GitLabApiException if any exception occurs
     */
    public Link updateLink(Object projectIdOrPath, Integer linkId, ReleaseLinkParams params) throws GitLabApiException {
        String tagName = params.getTagName();
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new RuntimeException("params.tagName cannot be null or empty");
        }

        if (linkId == null) {
            throw new RuntimeException("linkId cannot be null");
        }

        Response response = put(Response.Status.OK, params,
            "projects", getProjectIdOrPath(projectIdOrPath), "releases", urlEncode(tagName), "assets", "links", linkId);
        return (response.readEntity(Link.class));
    }

    /**
     * Delete a Link.
     *
     * <pre><code>GitLab Endpoint: DELETE /projects/:id/releases/:tagName/assets/links/:linkId</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param tagName         the tag name that the link was created from
     * @param linkId          the id of the Link to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteLink(Object projectIdOrPath, String tagName, Integer linkId) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "releases", urlEncode(tagName), "assets", "links", linkId);
    }
}
