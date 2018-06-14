package org.gitlab4j.api;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.ProtectedBranch;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ProtectedBranchesApi extends AbstractApi {

    public ProtectedBranchesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Gets a list of protected branches from a project.
     *
     * GET /projects/:id/protected_branches
     *
     * @param projectId the ID of the project to protect
     * @return the list of protected branches for the project
     * @throws GitLabApiException if any exception occurs
     */
    public List<ProtectedBranch> getProtectedBranches(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "protected_branches");
        return (response.readEntity(new GenericType<List<ProtectedBranch>>() {
        }));
    }

    /**
     * Unprotects the given protected branch or wildcard protected branch.
     *
     * DELETE /projects/:id/protected_branches/:name
     *
     * @param projectId  the ID of the project to un-protect
     * @param branchName the name of the branch to un-protect
     * @throws GitLabApiException if any exception occurs
     */
    public void unprotectBranch(Integer projectId, String branchName) throws GitLabApiException {
        delete(Response.Status.NO_CONTENT, null, "projects", projectId, "protected_branches", urlEncode(branchName));
    }

    /**
     * Protects a single repository branch or several project repository branches using a wildcard protected branch.
     *
     * POST /projects/:id/protected_branches
     *
     * @param projectId  the ID of the project to protect
     * @param branchName the name of the branch to protect
     * @return the branch info for the protected branch
     * @throws GitLabApiException if any exception occurs
     */
    public ProtectedBranch protectBranch(Integer projectId, String branchName) throws GitLabApiException {
        return protectBranch(projectId, branchName, AccessLevel.MAINTAINER, AccessLevel.MAINTAINER);
    }

    /**
     * Protects a single repository branch or several project repository branches using a wildcard protected branch.
     *
     * POST /projects/:id/protected_branches
     *
     * @param projectId        the ID of the project to protect
     * @param branchName       the name of the branch to protect
     * @param pushAccessLevel  Access levels allowed to push (defaults: 40, maintainer access level)
     * @param mergeAccessLevel Access levels allowed to merge (defaults: 40, maintainer access level)
     * @return the branch info for the protected branch
     * @throws GitLabApiException if any exception occurs
     */
    public ProtectedBranch protectBranch(Integer projectId, String branchName, AccessLevel pushAccessLevel, AccessLevel mergeAccessLevel) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("id", projectId, true)
                .withParam("name", branchName, true)
                .withParam("push_access_level", pushAccessLevel.toValue(), false)
                .withParam("merge_access_level", mergeAccessLevel.toValue(), false);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "protected_branches");
        return (response.readEntity(ProtectedBranch.class));
    }
}
