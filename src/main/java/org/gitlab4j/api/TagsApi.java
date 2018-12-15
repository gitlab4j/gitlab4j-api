package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Release;
import org.gitlab4j.api.models.Tag;
import org.gitlab4j.api.utils.FileUtils;

/**
 * This class provides an entry point to all the GitLab Tags API calls.
 *
 * See https://docs.gitlab.com/ce/api/tags.html for more information on the GitLab Tags API.
 */
public class TagsApi extends AbstractApi {

    public TagsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Tag> getTags(Object projectIdOrPath) throws GitLabApiException {
        return (getTags(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order and in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param page the page to get
     * @param perPage the number of Tag instances per page
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Tag> getTags(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags");
        return (response.readEntity(new GenericType<List<Tag>>() { }));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Tag> getTags(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Tag>(this, Tag.class, itemsPerPage, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags"));
    }

    /**
     * Get a Stream of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a Stream of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Tag> getTagsStream(Object projectIdOrPath) throws GitLabApiException {
        return (getTags(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a specific repository tag determined by its name.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags/:tagName</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of the tag to fetch the info for
     * @return a Tag instance with info on the specified tag
     * @throws GitLabApiException if any exception occurs
     */
    public Tag getTag(Object projectIdOrPath, String tagName) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags", tagName);
        return (response.readEntity(Tag.class));
    }

    /**
     * Get an Optional instance holding a Tag instance of a specific repository tag determined by its name.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags/:tagName</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of the tag to fetch the info for
     * @return an Optional instance with the specified project tag as the value
     * @throws GitLabApiException if any exception occurs
     */
    public Optional<Tag> getOptionalTag(Object projectIdOrPath, String tagName) throws GitLabApiException {
        try {
            return (Optional.ofNullable(getTag(projectIdOrPath, tagName)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a tag on a particular ref of the given project. A message and release notes are optional.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName The name of the tag Must be unique for the project
     * @param ref the git ref to place the tag on
     * @param message the message to included with the tag (optional)
     * @param releaseNotes the release notes for the tag (optional)
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     */
    public Tag createTag(Object projectIdOrPath, String tagName, String ref, String message, String releaseNotes) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("tag_name", tagName, true)
                .withParam("ref", ref, true)
                .withParam("message", message)
                .withParam("release_description", releaseNotes);
        Response response = post(Response.Status.CREATED, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags");
        return (response.readEntity(Tag.class));
    }

    /**
     * Creates a tag on a particular ref of a given project. A message and a File instance containing the
     * release notes are optional. This method is the same as {@link #createTag(Object, String, String, String, String)},
     * but instead allows the release notes to be supplied in a file.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of the tag, must be unique for the project
     * @param ref the git ref to place the tag on
     * @param message the message to included with the tag (optional)
     * @param releaseNotesFile a whose contents are the release notes (optional)
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     */
    public Tag createTag(Object projectIdOrPath, String tagName, String ref, String message, File releaseNotesFile) throws GitLabApiException {

        String releaseNotes;
        if (releaseNotesFile != null) {
            try {
                releaseNotes = FileUtils.readFileContents(releaseNotesFile);
            } catch (IOException ioe) {
                throw (new GitLabApiException(ioe));
            }
        } else {
            releaseNotes = null;
        }

        return (createTag(projectIdOrPath, tagName, ref, message, releaseNotes));
    }

    /**
     * Deletes the tag from a project with the specified tag name.
     *
     * <pre><code>GitLab Endpoint: DELETE /projects/:id/repository/tags/:tag_name</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName The name of the tag to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteTag(Object projectIdOrPath, String tagName) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags", tagName);
    }

    /**
     * Add release notes to the existing git tag.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags/:tagName/release</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of a tag
     * @param releaseNotes release notes with markdown support
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     */
    public Release createRelease(Object projectIdOrPath, String tagName, String releaseNotes) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("description", releaseNotes);
        Response response = post(Response.Status.CREATED, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags", tagName, "release");
        return (response.readEntity(Release.class));
    }

    /**
     * Updates the release notes of a given release.
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/repository/tags/:tagName/release</code></pre>
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param tagName the name of a tag
     * @param releaseNotes release notes with markdown support
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     */
    public Release updateRelease(Object projectIdOrPath, String tagName, String releaseNotes) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("description", releaseNotes);
        Response response = put(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags", tagName, "release");
        return (response.readEntity(Release.class));
    }
}
