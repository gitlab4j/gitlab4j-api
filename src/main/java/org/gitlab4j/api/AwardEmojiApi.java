package org.gitlab4j.api;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.gitlab4j.api.models.AwardEmoji;

/**
 * This class implements the client side API for the GitLab Award Emoji API calls.
 *
 * @see <a href="https://docs.gitlab.com/ce/api/award_emoji.html">GitLab Award Emoji API
 *     Documentaion</a>
 * @since v4.8.31
 */
public class AwardEmojiApi extends AbstractApi {

  public AwardEmojiApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Get a list of award emoji for the specified issue.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/award_emoji</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID to get the award emojis for
   * @return a list of AwardEmoji for the specified issue
   * @throws GitLabApiException if any exception occurs
   */
  public List<AwardEmoji> getIssueAwardEmojis(final Object projectIdOrPath, final Long issueIid)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "award_emoji");
    return response.readEntity(new GenericType<List<AwardEmoji>>() {});
  }

  /**
   * Get a list of award emoji for the specified merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID to get the award emojis for
   * @return a list of AwardEmoji for the specified merge request
   * @throws GitLabApiException if any exception occurs
   */
  public List<AwardEmoji> getMergeRequestAwardEmojis(
      final Object projectIdOrPath, final Long mergeRequestIid) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "award_emoji");
    return response.readEntity(new GenericType<List<AwardEmoji>>() {});
  }

  /**
   * Get a list of award emoji for the specified snippet.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/snippets/:snippet_id/award_emoji</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param snippetId the snippet ID to get the award emojis for
   * @return a list of AwardEmoji for the specified snippet
   * @throws GitLabApiException if any exception occurs
   */
  public List<AwardEmoji> getSnippetAwardEmojis(final Object projectIdOrPath, final Long snippetId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "snippets",
            snippetId,
            "award_emoji");
    return response.readEntity(new GenericType<List<AwardEmoji>>() {});
  }

  /**
   * Get a list of award emoji for the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/notes/:note_id/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID of the issue that owns the note
   * @param noteId the note ID to get the award emojis for
   * @return a list of AwardEmoji for the specified note
   * @throws GitLabApiException if any exception occurs
   */
  public List<AwardEmoji> getIssueNoteAwardEmojis(
      final Object projectIdOrPath, final Long issueIid, final Long noteId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "notes",
            noteId,
            "award_emoji");
    return response.readEntity(new GenericType<List<AwardEmoji>>() {});
  }

  /**
   * Get a list of award emoji for the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/notes/:note_id/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID of the issue that owns the note
   * @param noteId the note ID to get the award emojis for
   * @return a list of AwardEmoji for the specified note
   * @throws GitLabApiException if any exception occurs
   */
  public List<AwardEmoji> getNoteAwardEmojis(
      final Object projectIdOrPath, final Long issueIid, final Long noteId)
      throws GitLabApiException {
    return getIssueNoteAwardEmojis(projectIdOrPath, issueIid, noteId);
  }

  /**
   * Get a list of award emoji for the specified merge request note.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/notes/:note_id/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID of the merge request that owns the note
   * @param noteId the note ID to get the award emojis for
   * @return a list of AwardEmoji for the specified note
   * @throws GitLabApiException if any exception occurs
   */
  public List<AwardEmoji> getMergeRequestNoteAwardEmojis(
      final Object projectIdOrPath, final Long mergeRequestIid, final Long noteId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "notes",
            noteId,
            "award_emoji");
    return response.readEntity(new GenericType<List<AwardEmoji>>() {});
  }

  /**
   * Get the specified award emoji for the specified issue.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID to get the award emoji for
   * @param awardId the ID of the award emoji to get
   * @return an AwardEmoji instance for the specified award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji getIssueAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long awardId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "award_emoji",
            awardId);
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Get the specified award emoji for the specified merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID to get the award emoji for
   * @param awardId the ID of the award emoji to get
   * @return an AwardEmoji instance for the specified award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji getMergeRequestAwardEmoji(
      final Object projectIdOrPath, final Long mergeRequestIid, final Long awardId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "award_emoji",
            awardId);
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Get the specified award emoji for the specified snippet.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/snippets/:snippet_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param snippetId the snippet ID to get the award emoji for
   * @param awardId the ID of the award emoji to get
   * @return an AwardEmoji instance for the specified award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji getSnippetAwardEmoji(
      final Object projectIdOrPath, final Long snippetId, final Long awardId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "snippets",
            snippetId,
            "award_emoji",
            awardId);
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Get the specified award emoji for the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/notes/:note_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID of the issue that owns the note
   * @param noteId the note ID to get the award emoji from
   * @param awardId the ID of the award emoji to get
   * @return an AwardEmoji instance for the specified award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji getIssueNoteAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long noteId, final Long awardId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "notes",
            noteId,
            "award_emoji",
            awardId);
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Get the specified award emoji for the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/notes/:note_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID of the issue that owns the note
   * @param noteId the note ID to get the award emoji from
   * @param awardId the ID of the award emoji to get
   * @return an AwardEmoji instance for the specified award emoji
   * @throws GitLabApiException if any exception occurs
   * @deprecated use {@link #getIssueNoteAwardEmoji(Object, Long, Long, Long)} instead
   */
  @Deprecated
  public AwardEmoji getNoteAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long noteId, final Long awardId)
      throws GitLabApiException {
    return getIssueNoteAwardEmoji(projectIdOrPath, issueIid, noteId, awardId);
  }

  /**
   * Get the specified award emoji for the specified merge request note.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:merge_request_iid/notes/:note_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID of the merge request that owns the note
   * @param noteId the note ID to get the award emoji from
   * @param awardId the ID of the award emoji to get
   * @return an AwardEmoji instance for the specified award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji getMergeRequestNoteAwardEmoji(
      final Object projectIdOrPath,
      final Long mergeRequestIid,
      final Long noteId,
      final Long awardId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(1, getDefaultPerPage()),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "notes",
            noteId,
            "award_emoji",
            awardId);
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Add an award emoji for the specified issue.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/issues/:issue_iid/award_emoji</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID to add the award emoji to
   * @param name the name of the award emoji to add
   * @return an AwardEmoji instance for the added award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji addIssueAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final String name)
      throws GitLabApiException {
    final GitLabApiForm form = new GitLabApiForm().withParam("name", name, true);
    final Response response =
        post(
            Response.Status.CREATED,
            form.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "award_emoji");
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Add an award emoji to the specified merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: POST /projects/:id/merge_requests/:merge_request_iid/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID to add the award emoji to
   * @param name the name of the award emoji to add
   * @return an AwardEmoji instance for the added award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji addMergeRequestAwardEmoji(
      final Object projectIdOrPath, final Long mergeRequestIid, final String name)
      throws GitLabApiException {
    final GitLabApiForm form = new GitLabApiForm().withParam("name", name, true);
    final Response response =
        post(
            Response.Status.CREATED,
            form.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "award_emoji");
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Add an award emoji to the specified snippet.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/snippets/:snippet_id/award_emoji</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param snippetId the snippet ID to add the award emoji to
   * @param name the name of the award emoji to add
   * @return an AwardEmoji instance for the added award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji addSnippetAwardEmoji(
      final Object projectIdOrPath, final Long snippetId, final String name)
      throws GitLabApiException {
    final GitLabApiForm form = new GitLabApiForm().withParam("name", name, true);
    final Response response =
        post(
            Response.Status.CREATED,
            form.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "snippets",
            snippetId,
            "award_emoji");
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Add an award emoji for the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: POST /projects/:id/issues/:issue_iid/notes/:noteId/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID of the issue that owns the note
   * @param noteId the note ID to add the award emoji to
   * @param name the name of the award emoji to add
   * @return an AwardEmoji instance for the added award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji addIssueNoteAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long noteId, final String name)
      throws GitLabApiException {
    final GitLabApiForm form = new GitLabApiForm().withParam("name", name, true);
    final Response response =
        post(
            Response.Status.CREATED,
            form.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "notes",
            noteId,
            "award_emoji");
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Add an award emoji for the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: POST /projects/:id/issues/:issue_iid/notes/:noteId/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID of the issue that owns the note
   * @param noteId the note ID to add the award emoji to
   * @param name the name of the award emoji to add
   * @return an AwardEmoji instance for the added award emoji
   * @throws GitLabApiException if any exception occurs
   * @deprecated use {@link #addIssueNoteAwardEmoji(Object, Long, Long, String)}
   */
  @Deprecated
  public AwardEmoji addNoteAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long noteId, final String name)
      throws GitLabApiException {
    return addIssueNoteAwardEmoji(projectIdOrPath, issueIid, noteId, name);
  }

  /**
   * Add an award emoji for the specified merge request note.
   *
   * <pre>
   * <code>GitLab Endpoint: POST /projects/:id/merge_requests/:merge_request_iid/notes/:noteId/award_emoji</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID of the merge request that owns the note
   * @param noteId the note ID to add the award emoji to
   * @param name the name of the award emoji to add
   * @return an AwardEmoji instance for the added award emoji
   * @throws GitLabApiException if any exception occurs
   */
  public AwardEmoji addMergeRequestAwardEmoji(
      final Object projectIdOrPath,
      final Integer mergeRequestIid,
      final Integer noteId,
      final String name)
      throws GitLabApiException {
    final GitLabApiForm form = new GitLabApiForm().withParam("name", name, true);
    final Response response =
        post(
            Response.Status.CREATED,
            form.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "notes",
            noteId,
            "award_emoji");
    return (response.readEntity(AwardEmoji.class));
  }

  /**
   * Delete an award emoji from the specified issue.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/issues/:issue_iid/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID to delete the award emoji from
   * @param awardId the ID of the award emoji to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteIssueAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long awardId)
      throws GitLabApiException {
    delete(
        Response.Status.NO_CONTENT,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "issues",
        issueIid,
        "award_emoji",
        awardId);
  }

  /**
   * Delete an award emoji from the specified merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: DELETE /projects/:id/merge_requests/:merge_request_iid/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID to delete the award emoji from
   * @param awardId the ID of the award emoji to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteMergeRequestAwardEmoji(
      final Object projectIdOrPath, final Long mergeRequestIid, final Long awardId)
      throws GitLabApiException {
    delete(
        Response.Status.NO_CONTENT,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "merge_requests",
        mergeRequestIid,
        "award_emoji",
        awardId);
  }

  /**
   * Delete an award emoji from the specified snippet.
   *
   * <pre>
   * <code>GitLab Endpoint: DELETE /projects/:id/snippets/:snippet_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param snippetId the snippet ID to delete the award emoji from
   * @param awardId the ID of the award emoji to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteSnippetAwardEmoji(
      final Object projectIdOrPath, final Long snippetId, final Long awardId)
      throws GitLabApiException {
    delete(
        Response.Status.NO_CONTENT,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "snippets",
        snippetId,
        "award_emoji",
        awardId);
  }

  /**
   * Delete an award emoji from the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: DELETE /projects/:id/issues/:issue_iid/notes/:note_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID that owns the note
   * @param noteId the note ID of the note to delete the award emoji from
   * @param awardId the ID of the award emoji to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteIssueNoteAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long noteId, final Long awardId)
      throws GitLabApiException {
    delete(
        Response.Status.NO_CONTENT,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "issues",
        issueIid,
        "notes",
        noteId,
        "award_emoji",
        awardId);
  }

  /**
   * Delete an award emoji from the specified issue note.
   *
   * <pre>
   * <code>GitLab Endpoint: DELETE /projects/:id/issues/:issue_iid/notes/:note_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the issue IID that owns the note
   * @param noteId the note ID of the note to delete the award emoji from
   * @param awardId the ID of the award emoji to delete
   * @throws GitLabApiException if any exception occurs
   * @deprecated use {@link #deleteIssueNoteAwardEmoji(Object, Long, Long, Long)} instead
   */
  @Deprecated
  public void deleteNoteAwardEmoji(
      final Object projectIdOrPath, final Long issueIid, final Long noteId, final Long awardId)
      throws GitLabApiException {
    deleteIssueNoteAwardEmoji(projectIdOrPath, issueIid, noteId, awardId);
  }

  /**
   * Delete an award emoji from the specified merge request note.
   *
   * <pre>
   * <code>GitLab Endpoint: DELETE /projects/:id/merge_requests/:merge_request_iid/notes/:note_id/award_emoji/:award_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the merge request IID of the merge request that owns the note
   * @param noteId the note ID of the note to delete the award emoji from
   * @param awardId the ID of the award emoji to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteMergeRequestNoteAwardEmoji(
      final Object projectIdOrPath,
      final Long mergeRequestIid,
      final Long noteId,
      final Long awardId)
      throws GitLabApiException {
    delete(
        Response.Status.NO_CONTENT,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "merge_requests",
        mergeRequestIid,
        "notes",
        noteId,
        "award_emoji",
        awardId);
  }
}
