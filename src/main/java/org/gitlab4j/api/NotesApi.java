package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Note;

public class NotesApi extends AbstractApi {

    public NotesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of the issues's notes. Only returns the first page
     *
     * GET /projects/:id/issues/:issue_iid/notes
     *
     * @param projectId the project ID to get the issues for
     * @param issueIid the issue ID to get the notes for
     * @return a list of the issues's notes
     * @throws GitLabApiException if any exception occurs
     */
    public List<Note> getNotes(Integer projectId, Integer issueIid) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "issues", issueIid, "notes");
        return (response.readEntity(new GenericType<List<Note>>() {}));
    }

    /**
     * Get a list of the issue's notes using the specified page and per page settings.
     *
     * GET /projects/:id/issues/:issue_iid/notes
     *
     * @param projectId the project ID to get the issues for
     * @param issueIid the issue IID to get the notes for
     * @param page the page to get
     * @param perPage the number of notes per page
     * @return the list of notes in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Note> getNotes(Integer projectId, Integer issueIid, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "issues", issueIid, "notes");
        return (response.readEntity(new GenericType<List<Note>>() {}));
    }

    /**
     * Get a Pager of issues's notes.
     *
     * GET /projects/:id/issues/:issue_iid/notes
     *
     * @param projectId the project ID to get the issues for
     * @param issueIid the issue IID to get the notes for
     * @param itemsPerPage the number of notes per page
     * @return the list of notes in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Note> getNotes(Integer projectId, Integer issueIid, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Note>(this, Note.class, itemsPerPage, null, "projects", projectId, "issues", issueIid, "notes"));
    }
}
