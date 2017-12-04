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
     * @deprecated As of release 4.7.0, replaced by {@link #getIssueNotes(Integer, Integer)}
     */
    public List<Note> getNotes(Integer projectId, Integer issueIid) throws GitLabApiException {
        return (getIssueNotes(projectId, issueIid));
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
     * @deprecated As of release 4.7.0, replaced by {@link #getIssueNotes(Integer, Integer, int, int)}
     */
    public List<Note> getNotes(Integer projectId, Integer issueIid, int page, int perPage) throws GitLabApiException {
        return (getIssueNotes(projectId, issueIid, page, perPage));
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
     * @deprecated As of release 4.7.0, replaced by {@link #getIssueNotes(Integer, Integer, int)}
     */
    public Pager<Note> getNotes(Integer projectId, Integer issueIid, int itemsPerPage) throws GitLabApiException {
        return (getIssueNotes(projectId, issueIid, itemsPerPage));
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

    /**
     * Get the specified issues's note.
     *
     * @param projectId the project ID to get the issues for
     * @param issueIid  the issue IID to get the notes for
     * @param noteId    the ID of the Note to get
     * @return a Note instance for the specified IDs
     * @throws GitLabApiException if any exception occurs
     */
    public Note getIssueNote(Integer projectId, Integer issueIid, Integer noteId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "issues", issueIid, "notes", noteId);
        return (response.readEntity(Note.class));
    }

    /**
     * Create a issues's note.
     *
     * @param projectId the project ID to create the issues for
     * @param issueIid  the issue IID to create the notes for
     * @param body      the content of note
     * @return the created Note instance
     * @throws GitLabApiException if any exception occurs
     */
    public Note createIssueNote(Integer projectId, Integer issueIid, String body) throws GitLabApiException {
        return (createIssueNote(projectId, issueIid, body, null));
    }

    /**
     * Create a issues's note.
     *
     * @param projectId the project ID to create the issues for
     * @param issueIid  the issue IID to create the notes for
     * @param body      the content of note
     * @param createdAt the created time of note
     * @return the created Note instance
     * @throws GitLabApiException if any exception occurs
     */
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

    /**
     * Update the specified issues's note.
     *
     * @param projectId the project ID to update the issues for
     * @param issueIid  the issue IID to update the notes for
     * @param nodeId    the ID of the node to update
     * @param body      the update content for the Note
     * @return the modified Note instance
     * @throws GitLabApiException if any exception occurs
     */
    public Note updateIssueNote(Integer projectId, Integer issueIid, Integer nodeId, String body) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("body", body, true);
        Response response = put(Response.Status.CREATED, formData.asMap(), "projects", projectId, "issues", issueIid, "notes", nodeId);
        return (response.readEntity(Note.class));
    }

    /**
     * Delete the specified issues's note.
     *
     * @param projectId the project ID to delete the issues for
     * @param issueIid the issue IID to delete the notes for
     * @param noteId the ID of the node to delete
     * @throws GitLabApiException if any exception occurs
     */
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
