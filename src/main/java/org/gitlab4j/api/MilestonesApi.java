package org.gitlab4j.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Milestone;

/**
 * This class implements the client side API for the GitLab milestones calls.
 */
public class MilestonesApi extends AbstractApi {

    public MilestonesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of group milestones.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @return the milestones associated with the specified group
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getGroupMilestones(Object groupIdOrPath) throws GitLabApiException {
        return (getGroupMilestones(groupIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of group milestones.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param page the page number to get
     * @param perPage how many milestones per page
     * @return the milestones associated with the specified group
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getGroupMilestones(Object groupIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a Page of group milestones.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param itemsPerPage The number of Milestone instances that will be fetched per page
     * @return the milestones associated with the specified group
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Milestone> getGroupMilestones(Object groupIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Milestone>(this, Milestone.class, itemsPerPage, null,
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones"));
    }

    /**
     * Get a Stream of group milestones.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @return a Stream of the milestones associated with the specified group
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Milestone> getGroupMilestonesStream(Object groupIdOrPath) throws GitLabApiException {
        return (getGroupMilestones(groupIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of group milestones that have the specified state.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param state the milestone state
     * @return the milestones associated with the specified group and state
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getGroupMilestones(Object groupIdOrPath, MilestoneState state) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("state", state).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of group milestones that have match the search string.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param search the search string
     * @return the milestones associated with the specified group
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getGroupMilestones(Object groupIdOrPath, String search) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of group milestones that have the specified state and match the search string.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param state the milestone state
     * @param search the search string
     * @return the milestones associated with the specified group
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getGroupMilestones(Object groupIdOrPath, MilestoneState state, String search) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state)
                .withParam("search", search)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get the specified group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param milestoneId the ID of the milestone tp get
     * @return a Milestone instance for the specified IDs
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone getGroupMilestone(Object groupIdOrPath, Integer milestoneId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Get the list of issues associated with the specified group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param milestoneId the milestone ID to get the issues for
     * @return a List of Issue for the milestone
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getGroupIssues(Object groupIdOrPath, Integer milestoneId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones", milestoneId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get the list of merge requests associated with the specified group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param milestoneId the milestone ID to get the merge requests for
     * @return a list of merge requests associated with the specified milestone
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getGroupMergeRequest(Object groupIdOrPath, Integer milestoneId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones", milestoneId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Create a group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param title the title for the milestone
     * @param description the description for the milestone
     * @param dueDate the due date for the milestone
     * @param startDate the start date for the milestone
     * @return the created Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone createGroupMilestone(Object groupIdOrPath, String title, String description, Date dueDate, Date startDate) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("due_date", dueDate)
                .withParam("start_date", startDate);
        Response response = post(Response.Status.CREATED, formData,
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones");
        return (response.readEntity(Milestone.class));
    }

    /**
     * Close a group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param milestoneId the milestone ID to close
     * @return the closed Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone closeGroupMilestone(Object groupIdOrPath, Integer milestoneId) throws GitLabApiException {

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", MilestoneState.CLOSE);
        Response response = put(Response.Status.OK, formData.asMap(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Activate a group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param milestoneId the milestone ID to activate
     * @return the activated Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone activateGroupMilestone(Object groupIdOrPath, Integer milestoneId) throws GitLabApiException {

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", MilestoneState.ACTIVATE);
        Response response = put(Response.Status.OK, formData.asMap(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Update the specified group milestone.
     *
     * @param groupIdOrPath the group in the form of an Integer(ID), String(path), or Group instance
     * @param milestoneId the milestone ID to update
     * @param title the updated title for the milestone
     * @param description the updated description for the milestone
     * @param dueDate the updated due date for the milestone
     * @param startDate the updated start date for the milestone
     * @param milestoneState the updated milestone state
     * @return the updated Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone updateGroupMilestone(Object groupIdOrPath, Integer milestoneId, String title, String description,
                                     Date dueDate, Date startDate, MilestoneState milestoneState) throws GitLabApiException {

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("due_date", dueDate)
                .withParam("start_date", startDate)
                .withParam("state_event", milestoneState);
        Response response = put(Response.Status.OK, formData.asMap(),
                "groups", getGroupIdOrPath(groupIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Get a list of project milestones.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Object projectIdOrPath) throws GitLabApiException {
        return (getMilestones(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of project milestones.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page number to get
     * @param perPage how many milestones per page
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a Page of project milestones.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage The number of Milestone instances that will be fetched per page
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Milestone> getMilestones(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Milestone>(this, Milestone.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones"));
    }

    /**
     * Get a Stream of project milestones.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream of the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Milestone> getMilestonesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getMilestones(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a list of project milestones that have the specified state.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param state the milestone state
     * @return the milestones associated with the specified project and state
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Object projectIdOrPath, MilestoneState state) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("state", state).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of project milestones that have match the search string.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param search the search string
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Object projectIdOrPath, String search) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of project milestones that have the specified state and match the search string.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param state the milestone state
     * @param search the search string
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Object projectIdOrPath, MilestoneState state, String search) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("state", state)
                .withParam("search", search)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get the specified milestone.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param milestoneId the ID of the milestone tp get
     * @return a Milestone instance for the specified IDs
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone getMilestone(Object projectIdOrPath, Integer milestoneId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Get the list of issues associated with the specified milestone.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param milestoneId the milestone ID to get the issues for
     * @return a List of Issue for the milestone
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues(Object projectIdOrPath, Integer milestoneId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones", milestoneId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get the list of merge requests associated with the specified milestone.
     * 
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param milestoneId the milestone ID to get the merge requests for
     * @return a list of merge requests associated with the specified milestone
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequest(Object projectIdOrPath, Integer milestoneId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones", milestoneId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Create a milestone.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param title the title for the milestone
     * @param description the description for the milestone
     * @param dueDate the due date for the milestone
     * @param startDate the start date for the milestone
     * @return the created Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone createMilestone(Object projectIdOrPath, String title, String description, Date dueDate, Date startDate) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("due_date", dueDate)
                .withParam("start_date", startDate);
        Response response = post(Response.Status.CREATED, formData,
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones");
        return (response.readEntity(Milestone.class));
    }

    /**
     * Close a milestone.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param milestoneId the milestone ID to close
     * @return the closed Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone closeMilestone(Object projectIdOrPath, Integer milestoneId) throws GitLabApiException {

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", MilestoneState.CLOSE);
        Response response = put(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Activate a milestone.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param milestoneId the milestone ID to activate
     * @return the activated Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone activateMilestone(Object projectIdOrPath, Integer milestoneId) throws GitLabApiException {

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", MilestoneState.ACTIVATE);
        Response response = put(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Update the specified milestone.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param milestoneId the milestone ID to update
     * @param title the updated title for the milestone
     * @param description the updated description for the milestone
     * @param dueDate the updated due date for the milestone
     * @param startDate the updated start date for the milestone
     * @param milestoneState the updated milestone state
     * @return the updated Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone updateMilestone(Object projectIdOrPath, Integer milestoneId, String title, String description, 
            Date dueDate, Date startDate, MilestoneState milestoneState) throws GitLabApiException {

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }
 
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("due_date", dueDate)
                .withParam("start_date", startDate)
                .withParam("state_event", milestoneState);
        Response response = put(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }
}
