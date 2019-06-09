package org.gitlab4j.api;

import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.ProtectedBranch;

/**
 * This class provides an entry point to all the Protected Branches API calls.
 * @see <a href="https://docs.gitlab.com/ee/api/protected_branches.html">Protected branches API at GitLab</a>
 */
public class ProtectedBranchesApi extends AbstractApi {

    public ProtectedBranchesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Gets a list of protected branches from a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/protected_branches</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the list of protected branches for the project
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProtectedBranch> getProtectedBranches(Object projectIdOrPath) throws GitLabApiException {
        return (getProtectedBranches(projectIdOrPath, this.getDefaultPerPage()).all());
    }

    /**
     * Gets a Pager of protected branches from a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/protected_branches</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of instances that will be fetched per page
     * @return the Pager of protected branches for the project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<ProtectedBranch> getProtectedBranches(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<ProtectedBranch>(this, ProtectedBranch.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "protected_branches"));
    }

    /**
     * Gets a Stream of protected branches from a project.
     *
     * <pre><code>GitLab Endpoint: GET /projects/:id/protected_branches</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the Stream of protected branches for the project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<ProtectedBranch> getProtectedBranchesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getProtectedBranches(projectIdOrPath, this.getDefaultPerPage()).stream());
    }

    /**
     * Unprotects the given protected branch or wildcard protected branch.
     *
     * <pre><code>GitLab Endpoint: DELETE /projects/:id/protected_branches/:name</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to un-protect, can be a wildcard
     * @throws GitLabApiException if any exception occurs
     */
    public void unprotectBranch(Integer projectIdOrPath, String branchName) throws GitLabApiException {
        delete(Response.Status.NO_CONTENT, null, "projects", getProjectIdOrPath(projectIdOrPath), "protected_branches", urlEncode(branchName));
    }

    /**
     * Protects a single repository branch or several project repository branches using a wildcard protected branch.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/protected_branches</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to protect, can be a wildcard
     * @return the branch info for the protected branch
     * @throws GitLabApiException if any exception occurs
     */
    public ProtectedBranch protectBranch(Integer projectIdOrPath, String branchName) throws GitLabApiException {
        return protectBranch(projectIdOrPath, branchName, AccessLevel.MAINTAINER, AccessLevel.MAINTAINER);
    }

    /**
     * Protects a single repository branch or several project repository branches using a wildcard protected branch.
     *
     * <pre><code>GitLab Endpoint: POST /projects/:id/protected_branches</code></pre>
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to protect, can be a wildcard
     * @param pushAccessLevel Access levels allowed to push (defaults: 40, maintainer access level)
     * @param mergeAccessLevel Access levels allowed to merge (defaults: 40, maintainer access level)
     * @return the branch info for the protected branch
     * @throws GitLabApiException if any exception occurs
     */
    public ProtectedBranch protectBranch(Integer projectIdOrPath, String branchName, AccessLevel pushAccessLevel, AccessLevel mergeAccessLevel) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("name", branchName, true)
                .withParam("push_access_level", pushAccessLevel.toValue(), false)
                .withParam("merge_access_level", mergeAccessLevel.toValue(), false);
        Response response = post(Response.Status.CREATED, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "protected_branches");
        return (response.readEntity(ProtectedBranch.class));
    }
}
