package org.gitlab4j.api;

import java.util.Date;
import java.util.List;

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
     * Get a list of project milestones.
     *
     * @param projectId the project ID to get the milestones for
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Integer projectId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of project milestones.
     *
     * @param projectId the project ID to get the milestones for
     * @param page the page number to get
     * @param perPage how many milestones per page
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Integer projectId, int page, int perPage) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of project milestones that have the specified state.
     *
     * @param projectId the project ID to get the milestones for
     * @param state the milestone state
     * @return the milestones associated with the specified project and state
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Integer projectId, MilestoneState state) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Form formData = new GitLabApiForm().withParam("state", state).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {
        }));
    }

    /**
     * Get a list of project milestones that have match the search string.
     *
     * @param projectId the project ID to get the milestones for
     * @param search the search string
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Integer projectId, String search) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Form formData = new GitLabApiForm().withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get a list of project milestones that have the specified state and match the search string.
     *
     * @param projectId the project ID to get the milestones for
     * @param state the milestone state
     * @param search the search string
     * @return the milestones associated with the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Milestone> getMilestones(Integer projectId, MilestoneState state, String search) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Form formData = new GitLabApiForm()
                .withParam("state", state)
                .withParam("search", search)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {}));
    }

    /**
     * Get the specified milestone.
     *
     * @param projectId the project ID to get the milestone for
     * @param milestoneId the ID of the milestone tp get
     * @return a Milestone instance for the specified IDs
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone getMilestone(Integer projectId, int milestoneId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Get the list of issues associated with the specified milestone.
     *
     * @param projectId the project ID to get the milestone issues for
     * @param milestoneId the milestone ID to get the issues for
     * @return a List of Issue for the milestone
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues(Integer projectId, Integer milestoneId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones", milestoneId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get the list of merge requests associated with the specified milestone.
     * 
     * @param projectId the project ID to get the milestone merge requests for
     * @param milestoneId the milestone ID to get the merge requests for
     * @return a list of merge requests associated with the specified milestone
     * @throws GitLabApiException if any exception occurs
     */
    public List<MergeRequest> getMergeRequest(Integer projectId, Integer milestoneId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones", milestoneId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {}));
    }

    /**
     * Create a milestone.
     *
     * @param projectId the project ID to create a milestone for
     * @param title the title for the milestone
     * @param description the description for the milestone
     * @param dueDate the due date for the milestone
     * @param startDate the start date for the milestone
     * @return the created Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone createMilestone(Integer projectId, String title, String description, Date dueDate, Date startDate) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("due_date", dueDate)
                .withParam("start_date", startDate);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "milestones");
        return (response.readEntity(Milestone.class));
    }

    /**
     * Close a milestone.
     *
     * @param projectId the project ID of the milestone  
     * @param milestoneId the milestone ID to close
     * @return the closed Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone closeMilestone(Integer projectId, Integer milestoneId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", MilestoneState.CLOSE);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Activate a milestone.
     *
     * @param projectId the project ID of the milestone  
     * @param milestoneId the milestone ID to activate
     * @return the activated Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone activateMilestone(Integer projectId, Integer milestoneId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", MilestoneState.ACTIVATE);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    /**
     * Update the specified milestone.
     *
     * @param projectId the project ID of the milestone  
     * @param milestoneId the milestone ID to update
     * @param title the updated title for the milestone
     * @param description the updated description for the milestone
     * @param dueDate the updated due date for the milestone
     * @param startDate the updated start date for the milestone
     * @param milestoneState the updated milestone state
     * @return the updated Milestone instance
     * @throws GitLabApiException if any exception occurs
     */
    public Milestone updateMilestone(Integer projectId, Integer milestoneId, String title, String description, 
            Date dueDate, Date startDate, MilestoneState milestoneState) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (milestoneId == null) {
            throw new RuntimeException("milestoneId cannot be null");
        }
 
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("due_date", dueDate)
                .withParam("start_date", startDate)
                .withParam("state_event", milestoneState);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }
}
