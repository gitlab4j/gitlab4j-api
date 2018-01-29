/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Duration;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.TimeStats;
import org.gitlab4j.api.utils.DurationUtils;

/**
 * This class provides an entry point to all the GitLab API Issue calls.
 */
public class IssuesApi extends AbstractApi implements Constants {

    public IssuesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all issues the authenticated user has access to. Only returns issues created by the current user. Only returns the first page
     *
     * GET /issues
     *
     * @return a list of user's issues
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues() throws GitLabApiException {
        return (getIssues(1, getDefaultPerPage()));
    }

    /**
     * Get all issues the authenticated user has access to using the specified page and per page setting. Only returns issues created by the current user.
     *
     * GET /issues
     *
     * @param page the page to get
     * @param perPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues(int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get a Pager of all issues the authenticated user has access to. Only returns issues created by the current user.
     *
     * GET /issues
     *r
     * @param itemsPerPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Issue> getIssues(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Issue>(this, Issue.class, itemsPerPage, null, "issues"));
    }

    /**
     * Get a list of project's issues. Only returns the first page
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @return a list of project's issues
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues(Integer projectId) throws GitLabApiException {
        return (getIssues(projectId, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of project's issues using the specified page and per page settings.
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @param page the page to get
     * @param perPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Issue> getIssues(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "issues");
        return (response.readEntity(new GenericType<List<Issue>>() {}));
    }

    /**
     * Get a Pager of project's issues.
     *
     * GET /projects/:id/issues
     *
     * @param projectId the project ID to get the issues for
     * @param itemsPerPage the number of issues per page
     * @return the list of issues in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Issue> getIssues(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Issue>(this, Issue.class, itemsPerPage, null, "projects", projectId, "issues"));
    }

    /**
     * Get a single project issue.
     *
     * GET /projects/:id/issues/:issue_iid
     *
     * @param projectId the project ID to get the issue for
     * @param issueId the internal ID of a project's issue
     * @return the specified Issue instance
     * @throws GitLabApiException if any exception occurs
     */
    public Issue getIssue(Integer projectId, Integer issueId) throws GitLabApiException {
        
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "issues", issueId);
        return (response.readEntity(Issue.class));
    }

    /**
     * Get a single project issue as an Optional instance.
     *
     * GET /projects/:id/issues/:issue_iid
     *
     * @param projectId the project ID to get the issue for
     * @param issueId the internal ID of a project's issue
     * @return the specified Issue as an Optional instance
     */
    public Optional<Issue> getOptionalIssue(Integer projectId, Integer issueId) {
        try {
            return (Optional.ofNullable(getIssue(projectId, issueId)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Create an issue for the project.
     *
     * POST /projects/:id/issues
     * 
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param title the title of an issue, required
     * @param description the description of an issue, optional
     * @return an instance of Issue
     * @throws GitLabApiException if any exception occurs
     */
    public Issue createIssue(Integer projectId, String title, String description) throws GitLabApiException {
        return (createIssue(projectId, title, description, null, null, null, null, null, null, null, null));
    }

    /**
     * Create an issue for the project.
     *
     * POST /projects/:id/issues
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param title the issue title of an issue, required
     * @param description the description of an issue, optional
     * @param confidential set the issue to be confidential, default is false, optional
     * @param assigneeIds the IDs of the users to assign issue, optional
     * @param milestoneId the ID of a milestone to assign issue, optional
     * @param labels comma-separated label names for an issue, optional
     * @param createdAt the date the issue was created at, optional
     * @param dueDate the due date, optional
     * @param mergeRequestToResolveId the IID of a merge request in which to resolve all issues. This will fill the issue with a default 
     *        description and mark all discussions as resolved. When passing a description or title, these values will take precedence over the default values. Optional
     * @param discussionToResolveId the ID of a discussion to resolve. This will fill in the issue with a default description and mark the discussion as resolved. 
     *        Use in combination with merge_request_to_resolve_discussions_of. Optional
     * @return an instance of Issue
     * @throws GitLabApiException if any exception occurs
     */
    public Issue createIssue(Integer projectId, String title, String description, Boolean confidential, List<Integer> assigneeIds, Integer milestoneId, String labels,
            Date createdAt, Date dueDate, Integer mergeRequestToResolveId, Integer discussionToResolveId) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("confidential", confidential)
                .withParam("assignee_ids", assigneeIds)
                .withParam("milestone_id", milestoneId)
                .withParam("labels", labels)
                .withParam("created_at", createdAt)
                .withParam("due_date", dueDate)
                .withParam("merge_request_to_resolve_discussions_of", mergeRequestToResolveId)
                .withParam("discussion_to_resolve", discussionToResolveId);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "issues");
        return (response.readEntity(Issue.class));        
    }

    /**
     * Closes an existing project issue.
     *
     * PUT /projects/:id/issues/:issue_iid
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param issueIid the issue IID to update, required
     * @return an instance of the updated Issue
     * @throws GitLabApiException if any exception occurs
     */
    public Issue closeIssue(Integer projectId, Integer issueIid) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("project ID cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm().withParam("state_event", StateEvent.CLOSE);          
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "issues", issueIid);
        return (response.readEntity(Issue.class));        
    }

    /**
     * Updates an existing project issue. This call can also be used to mark an issue as closed.
     *
     * PUT /projects/:id/issues/:issue_iid
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param issueIid the issue IID to update, required
     * @param title the title of an issue, optional
     * @param description the description of an issue, optional
     * @param confidential set the issue to be confidential, default is false, optional
     * @param assigneeIds the IDs of the users to assign issue, optional
     * @param milestoneId the ID of a milestone to assign issue, optional
     * @param labels comma-separated label names for an issue, optional
     * @param stateEvent the state event of an issue. Set close to close the issue and reopen to reopen it, optional
     * @param updatedAt sets the updated date, requires admin or project owner rights, optional
     * @param dueDate the due date, optional
     * @return an instance of the updated Issue
     * @throws GitLabApiException if any exception occurs
     */
    public Issue updateIssue(Integer projectId, Integer issueIid, String title, String description, Boolean confidential, List<Integer> assigneeIds, 
            Integer milestoneId, String labels, StateEvent stateEvent, Date updatedAt, Date dueDate) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("project ID cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title, true)
                .withParam("description", description)
                .withParam("confidential", confidential)
                .withParam("assignee_ids", assigneeIds)
                .withParam("milestone_id", milestoneId)
                .withParam("labels", labels)
                .withParam("state_event", stateEvent)
                .withParam("updated_at", updatedAt)
                .withParam("due_date", dueDate);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "issues", issueIid);
        return (response.readEntity(Issue.class));        
    }

    /**
     * Delete an issue.
     *
     * DELETE /projects/:id/issues/:issue_iid
     *
     * @param projectId the project ID to delete the issue from
     * @param issueIid the internal ID of a project's issue
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteIssue(Integer projectId, Integer issueIid) throws GitLabApiException {
        
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, getDefaultPerPageParam(), "projects", projectId, "issues", issueIid);
    }

    /**
     * Sets an estimated time of work in this issue
     * 
     * POST /projects/:id/issues/:issue_iid/time_estimate
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @param duration estimated time in seconds
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats estimateTime(Integer projectId, Integer issueIid, int duration) throws GitLabApiException {
        return (estimateTime(projectId, issueIid, new Duration(duration)));
    }

    /**
     * Sets an estimated time of work in this issue
     * 
     * POST /projects/:id/issues/:issue_iid/time_estimate
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @param duration Human readable format, e.g. 3h30m
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats estimateTime(Integer projectId, Integer issueIid, String duration) throws GitLabApiException {
        return (estimateTime(projectId, issueIid, new Duration(duration)));
    }

    /**
     * Sets an estimated time of work in this issue
     * 
     * POST /projects/:id/issues/:issue_iid/time_estimate
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @param duration set the estimate of time to this duration
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats estimateTime(Integer projectId, Integer issueIid, Duration duration) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        String durationString = (duration != null ? DurationUtils.toString(duration.getSeconds(), false) : null);
        GitLabApiForm formData = new GitLabApiForm().withParam("duration", durationString, true);

        Response response = post(Response.Status.OK, formData.asMap(), "projects", projectId, "issues", issueIid, "time_estimate");
        return (response.readEntity(TimeStats.class));
    }

    /**
     * Resets the estimated time for this issue to 0 seconds.
     * 
     * POST /projects/:id/issues/:issue_iid/reset_time_estimate
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats resetEstimatedTime(Integer projectId, Integer issueIid) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        Response response = post(Response.Status.OK, new GitLabApiForm().asMap(), "projects", projectId, "issues", issueIid, "reset_time_estimate");
        return (response.readEntity(TimeStats.class));
    }

    /**
     * Adds spent time for this issue
     * 
     * POST /projects/:id/issues/:issue_iid/add_spent_time
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @param duration the duration in seconds
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats addSpentTime(Integer projectId, Integer issueIid, int duration) throws GitLabApiException {
        return (addSpentTime(projectId, issueIid, new Duration(duration)));
    }

    /**
     * Adds spent time for this issue
     * 
     * POST /projects/:id/issues/:issue_iid/add_spent_time
     * 
     * @param projectId the project ID to delete the issue from
     * @param issueIid the internal ID of a project's issue
     * @param duration Human readable format, e.g. 3h30m
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats addSpentTime(Integer projectId, Integer issueIid, String duration) throws GitLabApiException {
        return (addSpentTime(projectId, issueIid, new Duration(duration)));
    }

    /**
     * Adds spent time for this issue
     * 
     * POST /projects/:id/issues/:issue_iid/add_spent_time
     * 
     * @param projectId the project ID to delete the issue from
     * @param issueIid the internal ID of a project's issue
     * @param duration the duration of time spent
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats addSpentTime(Integer projectId, Integer issueIid, Duration duration) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        String durationString = (duration != null ? DurationUtils.toString(duration.getSeconds(), false) : null);
        GitLabApiForm formData = new GitLabApiForm().withParam("duration", durationString, true);

        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "issues", issueIid, "add_spent_time");
        return (response.readEntity(TimeStats.class));
    }

    /**
     * Resets the total spent time for this issue to 0 seconds.
     * 
     * POST /projects/:id/issues/:issue_iid/reset_spent_time
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @return a TimeSTats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats resetSpentTime(Integer projectId, Integer issueIid) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        Response response = post(Response.Status.OK, new GitLabApiForm().asMap(), "projects", projectId, "issues", issueIid, "reset_spent_time");
        return (response.readEntity(TimeStats.class));
    }

    /**
     * Get time tracking stats.
     * 
     * GET /projects/:id/issues/:issue_iid/time_stats
     * 
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @return a TimeStats instance
     * @throws GitLabApiException if any exception occurs
     */
    public TimeStats getTimeTrackingStats(Integer projectId, Integer issueIid) throws GitLabApiException {

       if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        if (issueIid == null) {
            throw new RuntimeException("issue IID cannot be null");
        }

        Response response = get(Response.Status.OK, new GitLabApiForm().asMap(), "projects", projectId, "issues", issueIid, "time_stats");
        return (response.readEntity(TimeStats.class));
    }

    /**
     * Get time tracking stats as an Optional instance
     *
     * GET /projects/:id/issues/:issue_iid/time_stats
     *
     * @param projectId the project ID that owns the issue
     * @param issueIid the internal ID of a project's issue
     * @return a TimeStats as an Optional instance
     */
    public Optional<TimeStats> getOptionalTimeTrackingStats(Integer projectId, Integer issueIid) {
        try {
            return (Optional.ofNullable(getTimeTrackingStats(projectId, issueIid)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }
}
