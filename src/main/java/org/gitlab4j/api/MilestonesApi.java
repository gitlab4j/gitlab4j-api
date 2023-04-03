package org.gitlab4j.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Milestone;

/**
 * This class implements the client side API for the GitLab milestones calls.
 *
 * @see <a href="https://docs.gitlab.com/ce/api/milestones.html">Project milestones API</a>
 * @see <a href="https://docs.gitlab.com/ce/api/group_milestones.html">Group milestones API</a>
 */
public class MilestonesApi extends AbstractApi {

  public MilestonesApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Get a list of group milestones.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @return the milestones associated with the specified group
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getGroupMilestones(final Object groupIdOrPath) throws GitLabApiException {
    return (getGroupMilestones(groupIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Get a list of group milestones.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param page the page number to get
   * @param perPage how many milestones per page
   * @return the milestones associated with the specified group
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getGroupMilestones(
      final Object groupIdOrPath, final int page, final int perPage) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(page, perPage),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get a Page of group milestones.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param itemsPerPage The number of Milestone instances that will be fetched per page
   * @return the milestones associated with the specified group
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Milestone> getGroupMilestones(final Object groupIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Milestone>(
        this,
        Milestone.class,
        itemsPerPage,
        null,
        "groups",
        getGroupIdOrPath(groupIdOrPath),
        "milestones"));
  }

  /**
   * Get a Stream of group milestones.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @return a Stream of the milestones associated with the specified group
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Milestone> getGroupMilestonesStream(final Object groupIdOrPath)
      throws GitLabApiException {
    return (getGroupMilestones(groupIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Get a list of group milestones that have the specified state.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param state the milestone state
   * @return the milestones associated with the specified group and state
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getGroupMilestones(final Object groupIdOrPath, final MilestoneState state)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("state", state)
            .withParam(PER_PAGE_PARAM, getDefaultPerPage());
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get a list of group milestones that have match the search string.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param search the search string
   * @return the milestones associated with the specified group
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getGroupMilestones(final Object groupIdOrPath, final String search)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("search", search)
            .withParam(PER_PAGE_PARAM, getDefaultPerPage());
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get a list of group milestones that have the specified state and match the search string.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones/:milestone_id</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param state the milestone state
   * @param search the search string
   * @return the milestones associated with the specified group
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getGroupMilestones(
      final Object groupIdOrPath, final MilestoneState state, final String search)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("state", state)
            .withParam("search", search)
            .withParam(PER_PAGE_PARAM, getDefaultPerPage());
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get the specified group milestone.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones/:milestone_id</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the ID of the milestone tp get
   * @return a Milestone instance for the specified IDs
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone getGroupMilestone(final Object groupIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getDefaultPerPageParam(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Get the list of issues associated with the specified group milestone.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones/:milestone_id/issues</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to get the issues for
   * @return a List of Issue for the milestone
   * @throws GitLabApiException if any exception occurs
   */
  public List<Issue> getGroupIssues(final Object groupIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    return (getGroupIssues(groupIdOrPath, milestoneId, getDefaultPerPage()).all());
  }

  /**
   * Get the Pager of issues associated with the specified group milestone.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones/:milestone_id/issues</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to get the issues for
   * @param itemsPerPage The number of Milestone instances that will be fetched per page
   * @return a Pager of Issue for the milestone
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Issue> getGroupIssues(
      final Object groupIdOrPath, final Long milestoneId, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Issue>(
        this,
        Issue.class,
        itemsPerPage,
        null,
        "groups",
        getGroupIdOrPath(groupIdOrPath),
        "milestones",
        milestoneId,
        "issues"));
  }

  /**
   * Get a Stream of issues associated with the specified group milestone.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones/:milestone_id/issues</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to get the issues for
   * @return a Stream of Issue for the milestone
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Issue> getGroupIssuesStream(final Object groupIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    return (getGroupIssues(groupIdOrPath, milestoneId, getDefaultPerPage()).stream());
  }

  /**
   * Get the list of merge requests associated with the specified group milestone.
   *
   * <pre><code>GitLab Endpoint: GET /groups/:id/milestones/:milestone_id/merge_requests</code>
   * </pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to get the merge requests for
   * @return a list of merge requests associated with the specified milestone
   * @throws GitLabApiException if any exception occurs
   */
  public List<MergeRequest> getGroupMergeRequest(final Object groupIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getDefaultPerPageParam(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones",
            milestoneId,
            "merge_requests");
    return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
  }

  /**
   * Create a group milestone.
   *
   * <pre><code>GitLab Endpoint: POST /groups/:id/milestones</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param title the title for the milestone
   * @param description the description for the milestone
   * @param dueDate the due date for the milestone
   * @param startDate the start date for the milestone
   * @return the created Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone createGroupMilestone(
      final Object groupIdOrPath,
      final String title,
      final String description,
      final Date dueDate,
      final Date startDate)
      throws GitLabApiException {
    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("title", title, true)
            .withParam("description", description)
            .withParam("due_date", dueDate)
            .withParam("start_date", startDate);
    final Response response =
        post(
            Response.Status.CREATED,
            formData,
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones");
    return (response.readEntity(Milestone.class));
  }

  /**
   * Close a group milestone.
   *
   * <pre><code>GitLab Endpoint: PUT /groups/:id/milestones/:milestone_id</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to close
   * @return the closed Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone closeGroupMilestone(final Object groupIdOrPath, final Long milestoneId)
      throws GitLabApiException {

    if (milestoneId == null) {
      throw new RuntimeException("milestoneId cannot be null");
    }

    final GitLabApiForm formData =
        new GitLabApiForm().withParam("state_event", MilestoneState.CLOSE);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Activate a group milestone.
   *
   * <pre><code>GitLab Endpoint: PUT /groups/:id/milestones/:milestone_id</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to activate
   * @return the activated Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone activateGroupMilestone(final Object groupIdOrPath, final Long milestoneId)
      throws GitLabApiException {

    if (milestoneId == null) {
      throw new RuntimeException("milestoneId cannot be null");
    }

    final GitLabApiForm formData =
        new GitLabApiForm().withParam("state_event", MilestoneState.ACTIVATE);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Update the specified group milestone.
   *
   * <pre><code>GitLab Endpoint: PUT /groups/:id/milestones/:milestone_id</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param milestoneId the milestone ID to update
   * @param title the updated title for the milestone
   * @param description the updated description for the milestone
   * @param dueDate the updated due date for the milestone
   * @param startDate the updated start date for the milestone
   * @param milestoneState the updated milestone state
   * @return the updated Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone updateGroupMilestone(
      final Object groupIdOrPath,
      final Long milestoneId,
      final String title,
      final String description,
      final Date dueDate,
      final Date startDate,
      final MilestoneState milestoneState)
      throws GitLabApiException {

    if (milestoneId == null) {
      throw new RuntimeException("milestoneId cannot be null");
    }

    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("title", title, true)
            .withParam("description", description)
            .withParam("due_date", dueDate)
            .withParam("start_date", startDate)
            .withParam("state_event", milestoneState);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Get a list of project milestones.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @return the milestones associated with the specified project
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getMilestones(final Object projectIdOrPath) throws GitLabApiException {
    return (getMilestones(projectIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Get a list of project milestones.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param page the page number to get
   * @param perPage how many milestones per page
   * @return the milestones associated with the specified project
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getMilestones(
      final Object projectIdOrPath, final int page, final int perPage) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getPageQueryParams(page, perPage),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get a Pager of project milestones.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param itemsPerPage The number of Milestone instances that will be fetched per page
   * @return the milestones associated with the specified project
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Milestone> getMilestones(final Object projectIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Milestone>(
        this,
        Milestone.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "milestones"));
  }

  /**
   * Get a Stream of project milestones.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @return a Stream of the milestones associated with the specified project
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Milestone> getMilestonesStream(final Object projectIdOrPath)
      throws GitLabApiException {
    return (getMilestones(projectIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Get a list of project milestones that have the specified state.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param state the milestone state
   * @return the milestones associated with the specified project and state
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getMilestones(final Object projectIdOrPath, final MilestoneState state)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("state", state)
            .withParam(PER_PAGE_PARAM, getDefaultPerPage());
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get a list of project milestones that have match the search string.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param search the search string
   * @return the milestones associated with the specified project
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getMilestones(final Object projectIdOrPath, final String search)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("search", search)
            .withParam(PER_PAGE_PARAM, getDefaultPerPage());
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get a list of project milestones that have the specified state and match the search string.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param state the milestone state
   * @param search the search string
   * @return the milestones associated with the specified project
   * @throws GitLabApiException if any exception occurs
   */
  public List<Milestone> getMilestones(
      final Object projectIdOrPath, final MilestoneState state, final String search)
      throws GitLabApiException {
    final Form formData =
        new GitLabApiForm()
            .withParam("state", state)
            .withParam("search", search)
            .withParam(PER_PAGE_PARAM, getDefaultPerPage());
    final Response response =
        get(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones");
    return (response.readEntity(new GenericType<List<Milestone>>() {}));
  }

  /**
   * Get the specified milestone.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones/:milestone_id</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the ID of the milestone tp get
   * @return a Milestone instance for the specified IDs
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone getMilestone(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getDefaultPerPageParam(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Get the list of issues associated with the specified milestone.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones/:milestone_id/issues</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to get the issues for
   * @return a List of Issue for the milestone
   * @throws GitLabApiException if any exception occurs
   */
  public List<Issue> getIssues(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    return (getIssues(projectIdOrPath, milestoneId, getDefaultPerPage()).all());
  }

  /**
   * Get a Pager of issues associated with the specified milestone.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones/:milestone_id/issues</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to get the issues for
   * @param itemsPerPage the number of Milestone instances that will be fetched per page
   * @return a Pager of Issue for the milestone
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Issue> getIssues(
      final Object projectIdOrPath, final Long milestoneId, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Issue>(
        this,
        Issue.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "milestones",
        milestoneId,
        "issues"));
  }

  /**
   * Get a Stream of issues associated with the specified milestone.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones/:milestone_id/issues</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to get the issues for
   * @return a Stream of Issue for the milestone
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Issue> getIssuesStream(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    return (getIssues(projectIdOrPath, milestoneId, getDefaultPerPage()).stream());
  }

  /**
   * Get the list of merge requests associated with the specified milestone.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/milestones/:milestone_id/merge_requests</code>
   * </pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to get the merge requests for
   * @return a list of merge requests associated with the specified milestone
   * @throws GitLabApiException if any exception occurs
   */
  public List<MergeRequest> getMergeRequest(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            getDefaultPerPageParam(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones",
            milestoneId,
            "merge_requests");
    return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
  }

  /**
   * Create a milestone.
   *
   * <pre><code>GitLab Endpoint: POST /projects/:id/milestones</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param title the title for the milestone
   * @param description the description for the milestone
   * @param dueDate the due date for the milestone
   * @param startDate the start date for the milestone
   * @return the created Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone createMilestone(
      final Object projectIdOrPath,
      final String title,
      final String description,
      final Date dueDate,
      final Date startDate)
      throws GitLabApiException {
    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("title", title, true)
            .withParam("description", description)
            .withParam("due_date", dueDate)
            .withParam("start_date", startDate);
    final Response response =
        post(
            Response.Status.CREATED,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones");
    return (response.readEntity(Milestone.class));
  }

  /**
   * Close a milestone.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/milestones/:milestone_id</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to close
   * @return the closed Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone closeMilestone(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {

    if (milestoneId == null) {
      throw new RuntimeException("milestoneId cannot be null");
    }

    final GitLabApiForm formData =
        new GitLabApiForm().withParam("state_event", MilestoneState.CLOSE);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Activate a milestone.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/milestones/:milestone_id</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to activate
   * @return the activated Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone activateMilestone(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {

    if (milestoneId == null) {
      throw new RuntimeException("milestoneId cannot be null");
    }

    final GitLabApiForm formData =
        new GitLabApiForm().withParam("state_event", MilestoneState.ACTIVATE);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Update the specified milestone.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/milestones/:milestone_id</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to update
   * @param title the updated title for the milestone
   * @param description the updated description for the milestone
   * @param dueDate the updated due date for the milestone
   * @param startDate the updated start date for the milestone
   * @param milestoneState the updated milestone state
   * @return the updated Milestone instance
   * @throws GitLabApiException if any exception occurs
   */
  public Milestone updateMilestone(
      final Object projectIdOrPath,
      final Long milestoneId,
      final String title,
      final String description,
      final Date dueDate,
      final Date startDate,
      final MilestoneState milestoneState)
      throws GitLabApiException {

    if (milestoneId == null) {
      throw new RuntimeException("milestoneId cannot be null");
    }

    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("title", title, true)
            .withParam("description", description)
            .withParam("due_date", dueDate)
            .withParam("start_date", startDate)
            .withParam("state_event", milestoneState);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "milestones",
            milestoneId);
    return (response.readEntity(Milestone.class));
  }

  /**
   * Delete a project milestone.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param milestoneId the milestone ID to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteMilestone(final Object projectIdOrPath, final Long milestoneId)
      throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "milestones",
        milestoneId);
  }
}
