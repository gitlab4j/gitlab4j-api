package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Tag;
import org.gitlab4j.api.models.TreeItem;

/**
 * This class provides an entry point to all the GitLab API repository calls.
 */
public class RepositoryApi extends AbstractApi {

    public RepositoryApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of repository branches from a project, sorted by name alphabetically.
     * 
     * GET /projects/:id/repository/branches
     * 
     * @param projectId
     * @return the list of repository branches for the specified project ID
     * @throws GitLabApiException
     */
    public List<Branch> getBranches(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "branches");
        return (response.readEntity(new GenericType<List<Branch>>() {}));
    }

    /**
     * Get a single project repository branch.
     * 
     * GET /projects/:id/repository/branches/:branch
     * 
     * @param projectId
     * @param branchName
     * @return the branch info for the specified project ID/branch name pair
     * @throws GitLabApiException
     */
    public Branch getBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName);
        return (response.readEntity(Branch.class));
    }

    /**
     * Creates a branch for the project. Support as of version 6.8.x
     * 
     * POST /projects/:id/repository/branches
     * 
     * @param projectId the project to create the branch for
     * @param branchName the name of the branch to create
     * @param ref Source to create the branch from, can be an existing branch, tag or commit SHA
     * @return the branch info for the created branch
     * @throws GitLabApiException
     */
    public Branch createBranch(Integer projectId, String branchName, String ref) throws GitLabApiException {

        Form formData = new GitLabApiForm()
            .withParam("branch_name", branchName, true)
            .withParam("ref", ref, true);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "repository", "branches");
        return (response.readEntity(Branch.class));
    }

    /**
     * Creates a tag for the project. Support as of version 6.8.x
     *
     * POST /projects/:id/repository/tags
     *
     * @param projectId the project to create the branch for
     * @param tagName the name of the tag to create
     * @param ref Source to create the tag from, can be an existing branch, tag or commit SHA
     * @param message (optional) - Creates annotated tag.
     * @param release_description (optional) - Add release notes to the git tag and store it in the GitLab database.
     * @return the tag info for the created tag
     * @throws GitLabApiException
     */
    public Tag createTag(Integer projectId, String tagName, String ref, String message, String release_description) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("tag_name", tagName, true)
                .withParam("ref", ref, true)
                .withParam("message", message, false)
                .withParam("release_description", release_description, false);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "repository", "tags");
        return (response.readEntity(Tag.class));
    }

    /**
     * Delete a single project repository branch. This is an idempotent function,
     * protecting an already protected repository branch will not produce an error.
     * 
     * DELETE /projects/:id/repository/branches/:branch
     * 
     * @param projectId
     * @param branchName
     * @throws GitLabApiException
     */
    public void deleteBranch(Integer projectId, String branchName) throws GitLabApiException {
        delete(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName);
    }

    /**
     * Protects a single project repository branch. This is an idempotent function,
     * protecting an already protected repository branch will not produce an error.
     * 
     * PUT /projects/:id/repository/branches/:branch/protect
     * 
     * @param projectId
     * @param branchName
     * @return the branch info for the protected branch
     * @throws GitLabApiException
     */
    public Branch protectBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName, "protect");
        return (response.readEntity(Branch.class));
    }

    /**
     * Unprotects a single project repository branch. This is an idempotent function, unprotecting an
     * already unprotected repository branch will not produce an error.
     * 
     * PUT /projects/:id/repository/branches/:branch/unprotect
     * 
     * @param projectId
     * @param branchName
     * @return the branch info for the unprotected branch
     * @throws GitLabApiException
     */
    public Branch unprotectBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "branches", branchName, "unprotect");
        return (response.readEntity(Branch.class));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     * 
     * GET /projects/:id/repository/tags
     * 
     * @param projectId
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException
     */
    public List<Tag> getTags(Integer projectId) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "tags");
        return (response.readEntity(new GenericType<List<Tag>>() {
        }));
    }

    /**
     * Get a list of repository files and directories in a project.
     * 
     * GET /projects/:id/repository/tree
     * 
     * @param projectId
     * @return a tree with the root directories and files of a project
     * @throws GitLabApiException
     */
    public List<TreeItem> getTree(Integer projectId) throws GitLabApiException {
        return this.getTree(projectId, "/", "master");
    }

    /**
     * Get a list of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get contend of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     * 
     * @param projectId
     * @param filePath
     * @param refName
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException
     */
    public List<TreeItem> getTree(Integer projectId, String filePath, String refName) throws GitLabApiException {
        return this.getTree(projectId, filePath, refName, false);
    }

    /**
     * Get a list of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get contend of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     * recursive (optional) - Boolean value used to get a recursive tree (false by default)
     *
     * @param projectId
     * @param filePath
     * @param refName
     * @param recursive
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException
     */
    public List<TreeItem> getTree(Integer projectId, String filePath, String refName, Boolean recursive) throws GitLabApiException {
        Form formData = new GitLabApiForm()
            .withParam("id", projectId, true)
            .withParam("path", filePath, false)
            .withParam("ref_name", refName, false)
            .withParam("recursive", recursive, false);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "tree");
        return (response.readEntity(new GenericType<List<TreeItem>>() {
        }));
    }

    /**
     * Get the raw file contents for a file by commit sha and path.
     *
     * GET /projects/:id/repository/blobs/:sha
     *
     * @param projectId
     * @param commitOrBranchName
     * @return a string with the file content for the specified file
     * @throws GitLabApiException
     */
    public String getRawFileContent(Integer projectId, String commitOrBranchName, String filepath) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("filepath", filepath, true);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "blobs", commitOrBranchName);
        return (response.readEntity(String.class));
    }

    /**
     * Get the raw file contents for a blob by blob SHA.
     * 
     * GET /projects/:id/repository/raw_blobs/:sha
     * 
     * @param projectId
     * @param sha
     * @return the raw file contents for the blob
     * @throws GitLabApiException
     */
    public String getRawBlobCotent(Integer projectId, String sha) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "raw_blobs", sha);
        return (response.readEntity(String.class));
    }

    /**
     * Get an archive of the complete repository by SHA (optional).
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectId
     * @param sha
     * @return an input stream that can be used to save as a file
     * or to read the content of the archive
     * @throws GitLabApiException
     */
    public InputStream getRepositoryArchive(Integer projectId, String sha) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "archive");
        return (response.readEntity(InputStream.class));
    }
    

    /**
     * Get an archive of the complete repository by SHA (optional) and saves to the specified directory.
     * If the archive already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectId
     * @param sha
     * @param directory the File instance of the directory to save the archive to, if null will use "java.io.tmpdir"
     * @return a File instance pointing to the downloaded instance
     * @throws GitLabApiException
     */
    public File getRepositoryArchive(Integer projectId, String sha, File directory) throws GitLabApiException {

        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "archive");

        try {

            if (directory == null)
                directory = new File(System.getProperty("java.io.tmpdir"));

            String filename = Utils.getFilenameFromContentDisposition(response);
            File file = new File(directory, filename);

            InputStream in = response.readEntity(InputStream.class);
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return (file);

        } catch (IOException ioe) {
            throw new GitLabApiException(ioe);
        }
    }
}
