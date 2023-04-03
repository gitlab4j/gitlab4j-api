package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.ProtectedTag;
import org.gitlab4j.api.models.Release;
import org.gitlab4j.api.models.Tag;
import org.gitlab4j.api.utils.FileUtils;

/**
 * This class provides an entry point to all the GitLab Tags and Protected Tags API calls.
 *
 * @see <a href="https://docs.gitlab.com/ce/api/tags.html">Tags API at GitLab</a>
 * @see <a href="https://docs.gitlab.com/ce/api/protected_tags.html">Protected Tags API at
 *     GitLab</a>
 */
public class TagsApi extends AbstractApi {

  public TagsApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return the list of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public List<Tag> getTags(final Object projectIdOrPath) throws GitLabApiException {
    return (getTags(projectIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Get a list of repository tags from a project, sorted by name in reverse alphabetical order and
   * in the specified page range.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param page the page to get
   * @param perPage the number of Tag instances per page
   * @return the list of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public List<Tag> getTags(final Object projectIdOrPath, final int page, final int perPage)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(page, perPage),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "repository",
            "tags");
    return (response.readEntity(new GenericType<List<Tag>>() {}));
  }

  /**
   * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param itemsPerPage the number of Project instances that will be fetched per page
   * @return the Pager of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Tag> getTags(final Object projectIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Tag>(
        this,
        Tag.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "repository",
        "tags"));
  }

  /**
   * Get a Stream of repository tags from a project, sorted by name in reverse alphabetical order.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a Stream of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Tag> getTagsStream(final Object projectIdOrPath) throws GitLabApiException {
    return (getTags(projectIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param orderBy return tags ordered by name or updated fields. Default is updated
   * @param sortOrder return tags sorted in asc or desc order. Default is desc
   * @param search return list of tags matching the search criteria
   * @return the list of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   * @since GitLab 11.8
   */
  public List<Tag> getTags(
      final Object projectIdOrPath,
      final TagOrderBy orderBy,
      final SortOrder sortOrder,
      final String search)
      throws GitLabApiException {
    return (getTags(projectIdOrPath, orderBy, sortOrder, search, getDefaultPerPage()).all());
  }

  /**
   * Get a list of repository tags from a project, sorted by name in reverse alphabetical order and
   * in the specified page range.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param orderBy return tags ordered by name or updated fields. Default is updated
   * @param sortOrder return tags sorted in asc or desc order. Default is desc
   * @param search return list of tags matching the search criteria
   * @param page the page to get
   * @param perPage the number of Tag instances per page
   * @return the list of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   * @since GitLab 11.8
   */
  public List<Tag> getTags(
      final Object projectIdOrPath,
      final TagOrderBy orderBy,
      final SortOrder sortOrder,
      final String search,
      final int page,
      final int perPage)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("order_by", orderBy)
            .withParam("sort", sortOrder)
            .withParam("search", search)
            .withParam(PAGE_PARAM, page)
            .withParam(PER_PAGE_PARAM, perPage);
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "repository",
            "tags");
    return (response.readEntity(new GenericType<List<Tag>>() {}));
  }

  /**
   * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param orderBy return tags ordered by name or updated fields. Default is updated
   * @param sortOrder return tags sorted in asc or desc order. Default is desc
   * @param search return list of tags matching the search criteria
   * @param itemsPerPage the number of Project instances that will be fetched per page
   * @return the Pager of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   * @since GitLab 11.8
   */
  public Pager<Tag> getTags(
      final Object projectIdOrPath,
      final TagOrderBy orderBy,
      final SortOrder sortOrder,
      final String search,
      final int itemsPerPage)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("order_by", orderBy)
            .withParam("sort", sortOrder)
            .withParam("search", search);
    return (new Pager<Tag>(
        this,
        Tag.class,
        itemsPerPage,
        formData.asMap(),
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "repository",
        "tags"));
  }

  /**
   * Get a Stream of repository tags from a project, sorted by name in reverse alphabetical order.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param orderBy return tags ordered by name or updated fields. Default is updated
   * @param sortOrder return tags sorted in asc or desc order. Default is desc
   * @param search return list of tags matching the search criteria
   * @return a Stream of tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   * @since GitLab 11.8
   */
  public Stream<Tag> getTagsStream(
      final Object projectIdOrPath,
      final TagOrderBy orderBy,
      final SortOrder sortOrder,
      final String search)
      throws GitLabApiException {
    return (getTags(projectIdOrPath, orderBy, sortOrder, search, getDefaultPerPage()).stream());
  }

  /**
   * Get a specific repository tag determined by its name.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags/:tagName</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName the name of the tag to fetch the info for
   * @return a Tag instance with info on the specified tag
   * @throws GitLabApiException if any exception occurs
   */
  public Tag getTag(final Object projectIdOrPath, final String tagName) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "repository",
            "tags",
            urlEncode(tagName));
    return (response.readEntity(Tag.class));
  }

  /**
   * Get an Optional instance holding a Tag instance of a specific repository tag determined by its
   * name.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/repository/tags/:tagName</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName the name of the tag to fetch the info for
   * @return an Optional instance with the specified project tag as the value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<Tag> getOptionalTag(final Object projectIdOrPath, final String tagName)
      throws GitLabApiException {
    try {
      return (Optional.ofNullable(getTag(projectIdOrPath, tagName)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }

  /**
   * Creates a tag on a particular ref of the given project.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName The name of the tag Must be unique for the project
   * @param ref the git ref to place the tag on
   * @return a Tag instance containing info on the newly created tag
   * @throws GitLabApiException if any exception occurs
   */
  public Tag createTag(final Object projectIdOrPath, final String tagName, final String ref)
      throws GitLabApiException {
    return (createTag(projectIdOrPath, tagName, ref, null, (String) null));
  }

  /**
   * Creates a tag on a particular ref of the given project with optional message and release notes.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName The name of the tag Must be unique for the project
   * @param ref the git ref to place the tag on
   * @param message the message to included with the tag (optional)
   * @param releaseNotes the release notes for the tag (optional)
   * @return a Tag instance containing info on the newly created tag
   * @throws GitLabApiException if any exception occurs
   */
  public Tag createTag(
      final Object projectIdOrPath,
      final String tagName,
      final String ref,
      final String message,
      final String releaseNotes)
      throws GitLabApiException {

    final Form formData =
        new GitLabApiForm()
            .withParam("tag_name", tagName, true)
            .withParam("ref", ref, true)
            .withParam("message", message)
            .withParam("release_description", releaseNotes);
    final Response response =
        post(
            Response.Status.CREATED,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "repository",
            "tags");
    return (response.readEntity(Tag.class));
  }

  /**
   * Creates a tag on a particular ref of a given project. A message and a File instance containing
   * the release notes are optional. This method is the same as {@link #createTag(Object, String,
   * String, String, String)}, but instead allows the release notes to be supplied in a file.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName the name of the tag, must be unique for the project
   * @param ref the git ref to place the tag on
   * @param message the message to included with the tag (optional)
   * @param releaseNotesFile a whose contents are the release notes (optional)
   * @return a Tag instance containing info on the newly created tag
   * @throws GitLabApiException if any exception occurs
   */
  public Tag createTag(
      final Object projectIdOrPath,
      final String tagName,
      final String ref,
      final String message,
      final File releaseNotesFile)
      throws GitLabApiException {

    final String releaseNotes;
    if (releaseNotesFile != null) {
      try {
        releaseNotes = FileUtils.readFileContents(releaseNotesFile);
      } catch (final IOException ioe) {
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
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName The name of the tag to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteTag(final Object projectIdOrPath, final String tagName)
      throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "repository",
        "tags",
        urlEncode(tagName));
  }

  /**
   * Add release notes to the existing git tag.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/repository/tags/:tagName/release</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName the name of a tag
   * @param releaseNotes release notes with markdown support
   * @return a Tag instance containing info on the newly created tag
   * @throws GitLabApiException if any exception occurs
   */
  public Release createRelease(
      final Object projectIdOrPath, final String tagName, final String releaseNotes)
      throws GitLabApiException {
    final Form formData = new GitLabApiForm().withParam("description", releaseNotes);
    final Response response =
        post(
            Response.Status.CREATED,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "repository",
            "tags",
            urlEncode(tagName),
            "release");
    return (response.readEntity(Release.class));
  }

  /**
   * Updates the release notes of a given release.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/repository/tags/:tagName/release</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param tagName the name of a tag
   * @param releaseNotes release notes with markdown support
   * @return a Tag instance containing info on the newly created tag
   * @throws GitLabApiException if any exception occurs
   */
  public Release updateRelease(
      final Object projectIdOrPath, final String tagName, final String releaseNotes)
      throws GitLabApiException {
    final Form formData = new GitLabApiForm().withParam("description", releaseNotes);
    final Response response =
        putWithFormData(
            Response.Status.OK,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "repository",
            "tags",
            urlEncode(tagName),
            "release");
    return (response.readEntity(Release.class));
  }

  /**
   * Gets a list of protected tags from a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a List of protected tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public List<ProtectedTag> getProtectedTags(final Object projectIdOrPath)
      throws GitLabApiException {
    return (getProtectedTags(projectIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Gets a list of protected tags from a project and in the specified page range.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param page the page to get
   * @param perPage the number of Tag instances per page
   * @return a List of tags for the specified project ID and page range
   * @throws GitLabApiException if any exception occurs
   */
  public List<ProtectedTag> getProtectedTags(
      final Object projectIdOrPath, final int page, final int perPage) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(page, perPage),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "protected_tags");
    return (response.readEntity(new GenericType<List<ProtectedTag>>() {}));
  }

  /**
   * Get a Pager of protected tags for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param itemsPerPage the number of Project instances that will be fetched per page
   * @return the Pager of protected tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<ProtectedTag> getProtectedTags(final Object projectIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<ProtectedTag>(
        this,
        ProtectedTag.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "protected_tags"));
  }

  /**
   * Get a Stream of protected tags for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags/:name</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a Stream of protected tags for the specified project ID
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<ProtectedTag> getProtectedTagsStream(final Object projectIdOrPath)
      throws GitLabApiException {
    return (getProtectedTags(projectIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Gets a single protected tag or wildcard protected tag
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags/:name</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param name the name of the tag or wildcard
   * @return a ProtectedTag instance with info on the specified protected tag
   * @throws GitLabApiException if any exception occurs
   */
  public ProtectedTag getProtectedTag(final Object projectIdOrPath, final String name)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "protected_tags",
            urlEncode(name));
    return (response.readEntity(ProtectedTag.class));
  }

  /**
   * Get an Optional instance holding a protected tag or wildcard protected tag.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags/:name</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param name the name of the tag or wildcard
   * @return an Optional instance with the specified protected tag as the value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<ProtectedTag> getOptionalProtectedTag(
      final Object projectIdOrPath, final String name) throws GitLabApiException {
    try {
      return (Optional.ofNullable(getProtectedTag(projectIdOrPath, name)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }

  /**
   * Protects a single repository tag or several project repository tags using a wildcard protected
   * tag.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param name the name of the tag or wildcard
   * @param createAccessLevel the access level allowed to create
   * @return a ProtectedTag instance
   * @throws GitLabApiException if any exception occurs
   */
  public ProtectedTag protectTag(
      final Object projectIdOrPath, final String name, final AccessLevel createAccessLevel)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("name", name, true)
            .withParam("create_access_level", createAccessLevel);
    final Response response =
        post(
            Response.Status.OK,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "protected_tags");
    return (response.readEntity(ProtectedTag.class));
  }

  /**
   * Unprotects the given protected tag or wildcard protected tag.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/protected_tags/:name</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param name the name of the tag or wildcard
   * @throws GitLabApiException if any exception occurs
   */
  public void unprotectTag(final Object projectIdOrPath, final String name)
      throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "protected_tags",
        urlEncode(name));
  }
}
