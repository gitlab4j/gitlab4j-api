package org.gitlab4j.api;

import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Discussion;

/**
 * This class implements the client side API for the GitLab Discussions API.
 * See <a href="https://docs.gitlab.com/ee/api/discussions.html">Discussions API at GitLab</a> for more information.
 */
public class DiscussionsApi extends AbstractApi {

    public DiscussionsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }


    /**
     * Get a list of all discussions for the specified issue.
     *
     * GET /projects/:id/issues/:issue_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param issueIid the internal ID of the issue
     * @return a list containing all the discussions for the specified issue
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getIssueDiscussions(Object projectIdOrPath, Integer issueIid) throws GitLabApiException {
        Pager<Discussion> pager = getIssueDiscussionsPager(projectIdOrPath, issueIid, getDefaultPerPage());
        return (pager.all());
    }
 
    /**
     * Get a list of discussions for the specified issue.
     *
     * GET /projects/:id/issues/:issue_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param issueIid the internal ID of the issue
     * @param maxItems the maximum number of Discussion instances to get, if &lt; 1 will fetch all Discussion instances for the issue
     * @return a list containing the discussions for the specified issue
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getIssueDiscussions(Object projectIdOrPath, Integer issueIid, int maxItems) throws GitLabApiException {
        if (maxItems < 1) {
            return (getIssueDiscussions(projectIdOrPath, issueIid));
        } else {
            Response response = get(Response.Status.OK, getPerPageQueryParam(maxItems),
                "projects", getProjectIdOrPath(projectIdOrPath), "issues", issueIid, "discussions");
            return (response.readEntity(new GenericType<List<Discussion>>() {}));
        }
    }

    /**
     * Get a Pager of Discussion instances for the specified issue.
     *
     * GET /projects/:id/issues/:issue_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param issueIid the internal ID of the issue
     * @param itemsPerPage the number of Discussion instances that will be fetched per page
     * @return a Pager containing the Discussion instances for the specified issue
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Discussion> getIssueDiscussionsPager(Object projectIdOrPath, Integer issueIid, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Discussion>(this, Discussion.class, itemsPerPage, null,
              "projects", getProjectIdOrPath(projectIdOrPath), "issues", issueIid, "discussions"));
    }

    /**
     * Get a Stream of Discussion instances for the specified issue.
     *
     * GET /projects/:id/issues/:issue_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param issueIid the internal ID of the issue
     * @return a Stream instance containing the Discussion instances for the specified issue
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Stream<Discussion> getIssueDiscussionsStream(Object projectIdOrPath, Integer issueIid) throws GitLabApiException {
        Pager<Discussion> pager = getIssueDiscussionsPager(projectIdOrPath, issueIid, getDefaultPerPage());
        return (pager.stream());
    }

    /**
     * Get a list of all discussions for the specified snippet.
     *
     * GET /projects/:id/snippets/:snippet_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param snippetId the ID of the snippet
     * @return a list containing all the discussions for the specified snippet
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getSnippetDiscussions(Object projectIdOrPath, Integer snippetId) throws GitLabApiException {
        Pager<Discussion> pager = getSnippetDiscussionsPager(projectIdOrPath, snippetId, getDefaultPerPage());
        return (pager.all());
    }
 
    /**
     * Get a list of discussions for the specified snippet.
     *
     * GET /projects/:id/snippets/:snippet_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param snippetId the ID of the snippet
     * @param maxItems the maximum number of Discussion instances to get, if &lt; 1 will fetch all Discussion instances for the snippet
     * @return a list containing the discussions for the specified snippet
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getSnippetDiscussions(Object projectIdOrPath, Integer snippetId, int maxItems) throws GitLabApiException {
        if (maxItems < 1) {
            return (getSnippetDiscussions(projectIdOrPath, snippetId));
        } else {
            Response response = get(Response.Status.OK, getPerPageQueryParam(maxItems),
                "projects", getProjectIdOrPath(projectIdOrPath), "snippets", snippetId, "discussions");
            return (response.readEntity(new GenericType<List<Discussion>>() {}));
        }
    }

    /**
     * Get a Pager of Discussion instances for the specified snippet.
     *
     * GET /projects/:id/snippets/:snippet_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param snippetId the ID of the snippet
     * @param itemsPerPage the number of Discussion instances that will be fetched per page
     * @return a Pager containing the Discussion instances for the specified snippet
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Discussion> getSnippetDiscussionsPager(Object projectIdOrPath, Integer snippetId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Discussion>(this, Discussion.class, itemsPerPage, null,
              "projects", getProjectIdOrPath(projectIdOrPath), "snippets", snippetId, "discussions"));
    }

    /**
     * Get a Stream of Discussion instances for the specified snippet.
     *
     * GET /projects/:id/snippets/:snippet_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param snippetId the ID of the snippet
     * @return a Stream instance containing the Discussion instances for the specified snippet
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Stream<Discussion> getSnippetDiscussionsStream(Object projectIdOrPath, Integer snippetId) throws GitLabApiException {
        Pager<Discussion> pager = getSnippetDiscussionsPager(projectIdOrPath, snippetId, getDefaultPerPage());
        return (pager.stream());
    }


    /**
     * Get a list of all discussions for the specified epic.
     *
     * GET /projects/:id/epics/:epic_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param epicId the internal ID of the epic
     * @return a list containing all the discussions for the specified epic
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getEpicDiscussions(Object projectIdOrPath, Integer epicId) throws GitLabApiException {
        Pager<Discussion> pager = getEpicDiscussionsPager(projectIdOrPath, epicId, getDefaultPerPage());
        return (pager.all());
    }
 
    /**
     * Get a list of discussions for the specified epic.
     *
     * GET /projects/:id/epics/:epic_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param epicId the internal ID of the epic
     * @param maxItems the maximum number of Discussion instances to get, if &lt; 1 will fetch all Discussion instances for the epic
     * @return a list containing the discussions for the specified epic
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getEpicDiscussions(Object projectIdOrPath, Integer epicId, int maxItems) throws GitLabApiException {
        if (maxItems < 1) {
            return (getEpicDiscussions(projectIdOrPath, epicId));
        } else {
            Response response = get(Response.Status.OK, getPerPageQueryParam(maxItems),
                "projects", getProjectIdOrPath(projectIdOrPath), "epics", epicId, "discussions");
            return (response.readEntity(new GenericType<List<Discussion>>() {}));
        }
    }

    /**
     * Get a Pager of Discussion instances for the specified epic.
     *
     * GET /projects/:id/epics/:epic_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param epicId the internal ID of the epic
     * @param itemsPerPage the number of Discussion instances that will be fetched per page
     * @return a Pager containing the Discussion instances for the specified epic
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Discussion> getEpicDiscussionsPager(Object projectIdOrPath, Integer epicId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Discussion>(this, Discussion.class, itemsPerPage, null,
              "projects", getProjectIdOrPath(projectIdOrPath), "epics", epicId, "discussions"));
    }

    /**
     * Get a Stream of Discussion instances for the specified epic.
     *
     * GET /projects/:id/epics/:epic_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param epicId the internal ID of the epic
     * @return a Stream instance containing the Discussion instances for the specified epic
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Stream<Discussion> getEpicDiscussionsStream(Object projectIdOrPath, Integer epicId) throws GitLabApiException {
        Pager<Discussion> pager = getEpicDiscussionsPager(projectIdOrPath, epicId, getDefaultPerPage());
        return (pager.stream());
    }

    /**
     * Get a list of all discussions for the specified merge request.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return a list containing all the discussions for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getMergeRequestDiscussions(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        Pager<Discussion> pager = getMergeRequestDiscussionsPager(projectIdOrPath, mergeRequestIid, getDefaultPerPage());
        return (pager.all());
    }
 
    /**
     * Get a list of discussions for the specified merge request.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param maxItems the maximum number of Discussion instances to get, if &lt; 1 will fetch all Discussion instances for the merge request
     * @return a list containing the discussions for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getMergeRequestDiscussions(Object projectIdOrPath, Integer mergeRequestIid, int maxItems) throws GitLabApiException {
        if (maxItems < 1) {
            return (getMergeRequestDiscussions(projectIdOrPath, mergeRequestIid));
        } else {
            Response response = get(Response.Status.OK, getPerPageQueryParam(maxItems),
                "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "discussions");
            return (response.readEntity(new GenericType<List<Discussion>>() {}));
        }
    }

    /**
     * Get a Pager of Discussion instances for the specified merge request.
     *
     * GET /projects/:id/merge_requests/:merge_request_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @param itemsPerPage the number of Discussion instances that will be fetched per page
     * @return a Pager containing the Discussion instances for the specified merge request
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Discussion> getMergeRequestDiscussionsPager(Object projectIdOrPath, Integer mergeRequestIid, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Discussion>(this, Discussion.class, itemsPerPage, null,
              "projects", getProjectIdOrPath(projectIdOrPath), "merge_requests", mergeRequestIid, "discussions"));
    }

    /**
     * Get a Stream of Discussion instances for the specified merge request.
     *
     * GET /projects/:id/issues/:issue_iid/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param mergeRequestIid the internal ID of the merge request
     * @return a Stream instance containing the Discussion instances for the specified issue
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Stream<Discussion> getMergeRequestDiscussionsStream(Object projectIdOrPath, Integer mergeRequestIid) throws GitLabApiException {
        Pager<Discussion> pager = getMergeRequestDiscussionsPager(projectIdOrPath, mergeRequestIid, getDefaultPerPage());
        return (pager.stream());
    }

    /**
     * Get a list of all discussions for the specified commit.
     *
     * GET /projects/:id/commits/:commit_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param commitId the internal ID of the commit
     * @return a list containing all the discussions for the specified commit
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getCommitDiscussions(Object projectIdOrPath, Integer commitId) throws GitLabApiException {
        Pager<Discussion> pager = getCommitDiscussionsPager(projectIdOrPath, commitId, getDefaultPerPage());
        return (pager.all());
    }
 
    /**
     * Get a list of discussions for the specified commit.
     *
     * GET /projects/:id/commits/:commit_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param commitId the internal ID of the commit
     * @param maxItems the maximum number of Discussion instances to get, if &lt; 1 will fetch all Discussion instances for the commit
     * @return a list containing the discussions for the specified commit
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public List<Discussion> getCommitDiscussions(Object projectIdOrPath, Integer commitId, int maxItems) throws GitLabApiException {
        if (maxItems < 1) {
            return (getCommitDiscussions(projectIdOrPath, commitId));
        } else {
            Response response = get(Response.Status.OK, getPerPageQueryParam(maxItems),
                "projects", getProjectIdOrPath(projectIdOrPath), "commits", commitId, "discussions");
            return (response.readEntity(new GenericType<List<Discussion>>() {}));
        }
    }

    /**
     * Get a Pager of Discussion instances for the specified commit.
     *
     * GET /projects/:id/commits/:commit_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param commitId the internal ID of the commit
     * @param itemsPerPage the number of Discussion instances that will be fetched per page
     * @return a Pager containing the Discussion instances for the specified commit
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Pager<Discussion> getCommitDiscussionsPager(Object projectIdOrPath, Integer commitId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Discussion>(this, Discussion.class, itemsPerPage, null,
              "projects", getProjectIdOrPath(projectIdOrPath), "commits", commitId, "discussions"));
    }

    /**
     * Get a Stream of Discussion instances for the specified commit.
     *
     * GET /projects/:id/commits/:commit_id/discussions
     *
     * @param projectIdOrPath projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param commitId the internal ID of the commit
     * @return a Stream instance containing the Discussion instances for the specified commit
     * @throws GitLabApiException GitLabApiException if any exception occurs during execution
     */
    public Stream<Discussion> getCommitDiscussionsStream(Object projectIdOrPath, Integer commitId) throws GitLabApiException {
        Pager<Discussion> pager = getCommitDiscussionsPager(projectIdOrPath, commitId, getDefaultPerPage());
        return (pager.stream());
    }
}
