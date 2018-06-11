package org.gitlab4j.api;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Event;

/**
 * This class implements the client side API for the GitLab events calls.
 */
public class EventsApi extends AbstractApi {

    public EventsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of events for the authenticated user.
     *
     * GET /events
     *
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @return a list of events for the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getAuthenticatedUserEvents(ActionType action, TargetType targetType,
            Date before, Date after, SortOrder sortOrder) throws GitLabApiException {
        return (getAuthenticatedUserEvents(action, targetType, before, after, sortOrder, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of events for the authenticated user and in the specified page range.
     *
     * GET /events
     *
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of events for the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getAuthenticatedUserEvents(ActionType action, TargetType targetType,
            Date before, Date after, SortOrder sortOrder, int page, int perPage) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action)
                .withParam("target_type", targetType != null ? targetType.toValue().toLowerCase() : null)
                .withParam("before", before)
                .withParam("after", after)
                .withParam("sort", sortOrder)
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);

        Response response = get(Response.Status.OK, formData.asMap(), "events");
        return (response.readEntity(new GenericType<List<Event>>() {}));
    }

    /**
     * Get a list of events for the authenticated user and in the specified page range.
     *
     * GET /events
     *
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @param itemsPerPage the number of Event instances that will be fetched per page
     * @return a Pager of events for the authenticated user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Event> getAuthenticatedUserEvents(ActionType action, TargetType targetType, Date before, Date after, 
            SortOrder sortOrder, int itemsPerPage) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action)
                .withParam("target_type", targetType != null ? targetType.toValue().toLowerCase() : null)
                .withParam("before", before)
                .withParam("after", after)
                .withParam("sort", sortOrder);

        return (new Pager<Event>(this, Event.class, itemsPerPage, formData.asMap(), "events"));
    }

    /**
     * Get a list of events for the specified user.
     *
     * GET /users/:userId/events
     *
     * @param userId the user ID to get the events for, required
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @return a list of events for the specified user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getUserEvents(Integer userId, ActionType action, TargetType targetType,
            Date before, Date after, SortOrder sortOrder) throws GitLabApiException {
        return (getUserEvents(userId, action, targetType, before, after, sortOrder, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of events for the specified user and in the specified page range.
     *
     * GET /users/:userId/events
     *
     * @param userId the user ID to get the events for, required
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of events for the specified user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getUserEvents(Integer userId, ActionType action, TargetType targetType,
            Date before, Date after, SortOrder sortOrder, int page, int perPage) throws GitLabApiException {
  
        if (userId == null) {
            throw new RuntimeException("user ID cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action)
                .withParam("target_type", targetType != null ? targetType.toValue().toLowerCase() : null)
                .withParam("before", before)
                .withParam("after", after)
                .withParam("sort", sortOrder)
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);

        Response response = get(Response.Status.OK, formData.asMap(), "users", userId, "events");
        return (response.readEntity(new GenericType<List<Event>>() {}));
    }

    /**
     * Get a list of events for the specified user and in the specified page range.
     *
     * GET /users/:userId/events
     *
     * @param userId the user ID to get the events for, required
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @param itemsPerPage the number of Event instances that will be fetched per page
     * @return a Pager of events for the specified user and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Event> getUserEvents(Integer userId, ActionType action, TargetType targetType, Date before, Date after, 
            SortOrder sortOrder, int itemsPerPage) throws GitLabApiException {

        if (userId == null) {
            throw new RuntimeException("user ID cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action)
                .withParam("target_type", targetType != null ? targetType.toValue().toLowerCase() : null)
                .withParam("before", before)
                .withParam("after", after)
                .withParam("sort", sortOrder);

        return (new Pager<Event>(this, Event.class, itemsPerPage, formData.asMap(), "users", userId, "events"));
    }

    /**
     * Get a list of events for the specified project.
     *
     * GET /:projectId/events
     *
     * @param projectId the project ID to get the events for, required
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @return a list of events for the specified project and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getProjectEvents(Integer projectId, ActionType action, TargetType targetType,
            Date before, Date after, SortOrder sortOrder) throws GitLabApiException {
        return (getProjectEvents(projectId, action, targetType, before, after, sortOrder, 1, getDefaultPerPage()));
    }

    /**
     * Get a list of events for the specified project and in the specified page range.
     *
     * GET /projects/:projectId/events
     *
     * @param projectId the project ID to get the events for, required
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a list of events for the specified project and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public List<Event> getProjectEvents(Integer projectId, ActionType action, TargetType targetType,
            Date before, Date after, SortOrder sortOrder, int page, int perPage) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("project ID cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action)
                .withParam("target_type", targetType != null ? targetType.toValue().toLowerCase() : null)
                .withParam("before", before)
                .withParam("after", after)
                .withParam("sort", sortOrder)
                .withParam(PAGE_PARAM,  page)
                .withParam(PER_PAGE_PARAM, perPage);

        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "events");
        return (response.readEntity(new GenericType<List<Event>>() {}));
    }

    /**
     * Get a list of events for the specified project and in the specified page range.
     *
     * GET /projects/:projectId/events
     *
     * @param projectId the project ID to get the events for, required
     * @param action include only events of a particular action type, optional
     * @param targetType include only events of a particular target type, optional
     * @param before include only events created before a particular date, optional
     * @param after include only events created after a particular date, optional
     * @param sortOrder sort events in ASC or DESC order by created_at. Default is DESC, optional
     * @param itemsPerPage the number of Event instances that will be fetched per page
     * @return a Pager of events for the specified project and matching the supplied parameters
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Event> getProjectEvents(Integer projectId, ActionType action, TargetType targetType, Date before, Date after, 
            SortOrder sortOrder, int itemsPerPage) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("project ID cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action)
                .withParam("target_type", targetType != null ? targetType.toValue().toLowerCase() : null)
                .withParam("before", before)
                .withParam("after", after)
                .withParam("sort", sortOrder);

        return (new Pager<Event>(this, Event.class, itemsPerPage, formData.asMap(), "projects", projectId, "events"));
    }
}
