package org.gitlab4j.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.models.LabelEvent;

/**
 * This class provides an entry point to all the GitLab Resource label events API
 *
 * @see <a href="https://docs.gitlab.com/ce/api/resource_label_events.html">Resource label events
 *     API at GitLab</a>
 */
public class ResourceLabelEventsApi extends AbstractApi {

  public ResourceLabelEventsApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Gets a list of all label events for a single issue.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the IID of the issue
   * @return a List of LabelEvent for the specified issue
   * @throws GitLabApiException if any exception occurs
   */
  public List<LabelEvent> getIssueLabelEvents(final Object projectIdOrPath, final Long issueIid)
      throws GitLabApiException {
    return (getIssueLabelEvents(projectIdOrPath, issueIid, getDefaultPerPage()).all());
  }

  /**
   * Gets a Pager of all label events for a single issue.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the IID of the issue
   * @param itemsPerPage the number of LabelEvent instances that will be fetched per page
   * @return the Pager of LabelEvent instances for the specified issue IID
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<LabelEvent> getIssueLabelEvents(
      final Object projectIdOrPath, final Long issueIid, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<LabelEvent>(
        this,
        LabelEvent.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "issues",
        issueIid,
        "resource_label_events"));
  }

  /**
   * Gets a Stream of all label events for a single issue.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the IID of the issue
   * @return a Stream of LabelEvent for the specified issue
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<LabelEvent> getIssueLabelEventsStream(
      final Object projectIdOrPath, final Long issueIid) throws GitLabApiException {
    return (getIssueLabelEvents(projectIdOrPath, issueIid, getDefaultPerPage()).stream());
  }

  /**
   * Get a single label event for a specific project issue.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events/:resource_label_event_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the IID of the issue
   * @param resourceLabelEventId the ID of a label event
   * @return LabelEvent instance for the specified project issue
   * @throws GitLabApiException if any exception occurs
   */
  public LabelEvent getIssueLabelEvent(
      final Object projectIdOrPath, final Long issueIid, final Long resourceLabelEventId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "issues",
            issueIid,
            "resource_label_events",
            resourceLabelEventId);
    return (response.readEntity(LabelEvent.class));
  }

  /**
   * Get an Optional instance holding a LabelEvent for a specific project issue
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/issues/:issue_iid/resource_label_events/:resource_label_event_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param issueIid the IID of the issue
   * @param resourceLabelEventId the ID of a label event
   * @return an Optional instance with the specified LabelEvent as the value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<LabelEvent> getOptionalIssueLabelEvent(
      final Object projectIdOrPath, final Long issueIid, final Long resourceLabelEventId)
      throws GitLabApiException {

    try {
      return (Optional.ofNullable(
          getIssueLabelEvent(projectIdOrPath, issueIid, resourceLabelEventId)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }

  /**
   * Gets a list of all label events for an epic.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/epics/:epic_id/resource_label_events</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param epicId the ID of the epic
   * @return a List of LabelEvent for the specified epic
   * @throws GitLabApiException if any exception occurs
   */
  public List<LabelEvent> getEpicLabelEvents(final Object projectIdOrPath, final Long epicId)
      throws GitLabApiException {
    return (getEpicLabelEvents(projectIdOrPath, epicId, getDefaultPerPage()).all());
  }

  /**
   * Gets a Pager of all label events for the specified epic.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/epics/:epic_id/resource_label_events</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param epicId the ID of the epic
   * @param itemsPerPage the number of LabelEvent instances that will be fetched per page
   * @return the Pager of LabelEvent instances for the specified epic
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<LabelEvent> getEpicLabelEvents(
      final Object projectIdOrPath, final Long epicId, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<LabelEvent>(
        this,
        LabelEvent.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "epics",
        epicId,
        "resource_label_events"));
  }

  /**
   * Gets a Stream of all label events for he specified epic.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/epics/:epic_id/resource_label_events</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param epicId the ID of the epic
   * @return a Stream of LabelEvent for the specified epic
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<LabelEvent> getEpicLabelEventsStream(
      final Object projectIdOrPath, final Long epicId) throws GitLabApiException {
    return (getEpicLabelEvents(projectIdOrPath, epicId, getDefaultPerPage()).stream());
  }

  /**
   * Get a single label event for a specific epic label event.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/epics/:epic_id/resource_label_events/:resource_label_event_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param epicId the ID of the epic
   * @param resourceLabelEventId the ID of a label event
   * @return LabelEvent instance for the specified epic label event
   * @throws GitLabApiException if any exception occurs
   */
  public LabelEvent getEpicLabelEvent(
      final Object projectIdOrPath, final Long epicId, final Long resourceLabelEventId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "epics",
            epicId,
            "resource_label_events",
            resourceLabelEventId);
    return (response.readEntity(LabelEvent.class));
  }

  /**
   * Get an Optional instance holding a LabelEvent for a specific epic label event.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/epics/:epic_id/resource_label_events/:resource_label_event_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param epicId the ID of the epic
   * @param resourceLabelEventId the ID of a label event
   * @return an Optional instance with the specified LabelEvent as the value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<LabelEvent> getOptionalEpicLabelEvent(
      final Object projectIdOrPath, final Long epicId, final Long resourceLabelEventId)
      throws GitLabApiException {

    try {
      return (Optional.ofNullable(
          getEpicLabelEvent(projectIdOrPath, epicId, resourceLabelEventId)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }

  /**
   * Gets a list of all label events for a merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:epic_id/resource_label_events</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the IID of the merge request
   * @return a List of LabelEvent for the specified merge request
   * @throws GitLabApiException if any exception occurs
   */
  public List<LabelEvent> getMergeRequestLabelEvents(
      final Object projectIdOrPath, final Long mergeRequestIid) throws GitLabApiException {
    return (getMergeRequestLabelEvents(projectIdOrPath, mergeRequestIid, getDefaultPerPage())
        .all());
  }

  /**
   * Gets a Pager of all label events for the specified merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:epic_id/resource_label_events</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the IID of the merge request
   * @param itemsPerPage the number of LabelEvent instances that will be fetched per page
   * @return the Pager of LabelEvent instances for the specified merge request
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<LabelEvent> getMergeRequestLabelEvents(
      final Object projectIdOrPath, final Long mergeRequestIid, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<LabelEvent>(
        this,
        LabelEvent.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "merge_requests",
        mergeRequestIid,
        "resource_label_events"));
  }

  /**
   * Gets a Stream of all label events for he specified merge request.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:issue_iid/resource_label_events</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the IID of the merge request
   * @return a Stream of LabelEvent for the specified merge request
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<LabelEvent> getMergeRequestLabelEventsStream(
      final Object projectIdOrPath, final Long mergeRequestIid) throws GitLabApiException {
    return (getMergeRequestLabelEvents(projectIdOrPath, mergeRequestIid, getDefaultPerPage())
        .stream());
  }

  /**
   * Get a single label event for a specific merge request label event.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:epic_id/resource_label_events/:resource_label_event_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the IID of the merge request
   * @param resourceLabelEventId the ID of a label event
   * @return LabelEvent instance for the specified epic label event
   * @throws GitLabApiException if any exception occurs
   */
  public LabelEvent getMergeRequestLabelEvent(
      final Object projectIdOrPath, final Long mergeRequestIid, final Long resourceLabelEventId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "merge_requests",
            mergeRequestIid,
            "resource_label_events",
            resourceLabelEventId);
    return (response.readEntity(LabelEvent.class));
  }

  /**
   * Get an Optional instance holding a LabelEvent for a specific merge request label event.
   *
   * <pre>
   * <code>GitLab Endpoint: GET /projects/:id/merge_requests/:issue_iid/resource_label_events/:resource_label_event_id</code>
   * </pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mergeRequestIid the IID of the merge request
   * @param resourceLabelEventId the ID of a label event
   * @return an Optional instance with the specified LabelEvent as the value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<LabelEvent> getOptionalMergeRequestLabelEvent(
      final Object projectIdOrPath, final Long mergeRequestIid, final Long resourceLabelEventId)
      throws GitLabApiException {

    try {
      return (Optional.ofNullable(
          getMergeRequestLabelEvent(projectIdOrPath, mergeRequestIid, resourceLabelEventId)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }
}
