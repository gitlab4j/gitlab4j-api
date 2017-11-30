package org.gitlab4j.api;

import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Milestone;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * This class implements the client side API for the GitLab groups calls.
 */
public class MileStonesApi extends AbstractApi {

    public MileStonesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    public List<Milestone> getMilestones(Integer projectId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {
        }));
    }

    public List<Milestone> getMilestones(Integer projectId, int page, int perPage) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {
        }));
    }

    public List<Milestone> getMilestones(Integer projectId, MilestoneState state) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Form formData = new GitLabApiForm().withParam("state", state).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {
        }));
    }

    public List<Milestone> getMilestones(Integer projectId, String search) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Form formData = new GitLabApiForm().withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {
        }));
    }

    public List<Milestone> getMilestones(Integer projectId, MilestoneState state, String search) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Form formData = new GitLabApiForm().withParam("state", state).withParam("search", search).withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "milestones");
        return (response.readEntity(new GenericType<List<Milestone>>() {
        }));
    }

    public Milestone getMilestone(Integer projectId, int milestoneId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones", milestoneId);
        return (response.readEntity(Milestone.class));
    }

    public List<Issue> getIssues(Integer projectId, Integer milestoneId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones", milestoneId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {
        }));
    }

    public List<MergeRequest> getMergeRequest(Integer projectId, Integer milestoneId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "milestones", milestoneId, "merge_requests");
        return (response.readEntity(new GenericType<List<MergeRequest>>() {
        }));
    }

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

    public Milestone updateMilestone(Integer projectId, Integer milestoneId, String title, String description, Date dueDate, Date startDate, MilestoneState milestoneState) throws GitLabApiException {
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
