package org.gitlab4j.api;

import jakarta.ws.rs.core.Response;

import org.gitlab4j.api.models.Commit;

/**
 * <p>This class provides an entry point to all the GitLab API repository submodules calls.
 * For more information on the repository APIs see:</p>
 *
 * @see <a href="https://docs.gitlab.com/ee/api/repository_submodules.html">Repository Submodules API</a>
 */
public class RepositorySubmodulesApi extends AbstractApi {

    public RepositorySubmodulesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Update existing submodule reference in repository.
     *
     * <pre><code>GitLab Endpoint: PUT /projects/:id/repository/submodules/:submodule</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project instance
     * @param submodule full path to the submodule
     * @param branch name of the branch to commit into
     * @param commitSha full commit SHA to update the submodule to
     * @param commitMessage commit message (optional). If no message is provided, a default is set
     * @return the created commit
     * @throws GitLabApiException if any exception occurs
     */
    public Commit updateExistingSubmoduleReference(Object projectIdOrPath, String submodule, String branch, String commitSha, String commitMessage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("branch", branch, true)
                .withParam("commit_sha", commitSha, true)
                .withParam("commit_message", commitMessage);
        Response response = put(Response.Status.OK, formData.asMap(), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "submodules", urlEncode(submodule));
        return (response.readEntity(Commit.class));
    }

}
