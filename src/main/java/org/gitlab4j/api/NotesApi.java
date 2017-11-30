package org.gitlab4j.api;

import org.gitlab4j.api.models.Note;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

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
    public List<Note> getIssueNotes(Integer projectId, Integer issueIid) throws GitLabApiException {
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
    public List<Note> getIssueNotes(Integer projectId, Integer issueIid, int page, int perPage) throws GitLabApiException {
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
    public Pager<Note> getIssueNotes(Integer projectId, Integer issueIid, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Note>(this, Note.class, itemsPerPage, null, "projects", projectId, "issues", issueIid, "notes"));
    }

    public Note getIssueNote(Integer projectId, Integer issueIid, Integer noteId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "issues", issueIid, "notes", noteId);
        return (response.readEntity(Note.class));
    }

    public Note createIssueNote(Integer projectId, Integer issueIid, String body) throws GitLabApiException {
        return (createIssueNote(projectId, issueIid, body, null));
    }

    public Note createIssueNote(Integer projectId, Integer issueIid, String body, Date createdAt) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("body", body, true)
                .withParam("created_at", createdAt);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "issues", issueIid, "notes");
        return (response.readEntity(Note.class));
    }

    public Note updateIssueNote(Integer projectId, Integer issueIid, String body) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("body", body, true);
        Response response = put(Response.Status.CREATED, formData.asMap(), "projects", projectId, "issues", issueIid, "notes");
        return (response.readEntity(Note.class));
    }

    public void deleteIssueNote(Integer projectId, Integer issueIid, Integer noteId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        if (issueIid == null) {
            throw new RuntimeException("issueIid cannot be null");
        }
        if (noteId == null) {
            throw new RuntimeException("noteId cannot be null");
        }
        Response.Status expectedStatus = (isApiVersion(GitLabApi.ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, getDefaultPerPageParam(), "projects", projectId, "issues", issueIid, "notes", noteId);
    }
}
