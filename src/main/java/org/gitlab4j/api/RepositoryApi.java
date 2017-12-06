package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Tag;
import org.gitlab4j.api.models.TreeItem;
import org.gitlab4j.api.utils.FileUtils;

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
     * @param projectId the project to get the list of branches for
     * @return the list of repository branches for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Branch> getBranches(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "repository", "branches");
        return (response.readEntity(new GenericType<List<Branch>>() {}));
    }

    /**
     * Get a list of repository branches from a project, sorted by name alphabetically.
     *
     * GET /projects/:id/repository/branches
     *
     * @param projectId the project to get the list of branches for
     * @return the list of repository branches for the specified project ID
     * @param page the page to get
     * @param perPage the number of Branch instances per page
     * @throws GitLabApiException if any exception occurs
     */
    public List<Branch> getBranches(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "repository", "branches");
        return (response.readEntity(new GenericType<List<Branch>>() {}));
    }

    /**
     * Get a Pager of repository branches from a project, sorted by name alphabetically.
     *
     * GET /projects/:id/repository/branches
     *
     * @param projectId the project to get the list of branches for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the list of repository branches for the specified project ID
     *
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Branch> getBranches(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Branch>(this, Branch.class, itemsPerPage, null, "projects", projectId, "repository", "branches"));
    }

    /**
     * Get a single project repository branch.
     *
     * GET /projects/:id/repository/branches/:branch
     *
     * @param projectId the project to get the branch for
     * @param branchName the name of the branch to get
     * @return the branch info for the specified project ID/branch name pair
     * @throws GitLabApiException if any exception occurs
     */
    public Branch getBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "repository", "branches", urlEncode(branchName));
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
     * @throws GitLabApiException if any exception occurs
     */
    public Branch createBranch(Integer projectId, String branchName, String ref) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam(isApiVersion(ApiVersion.V3) ? "branch_name" : "branch", branchName, true)
                .withParam("ref", ref, true);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "repository", "branches");
        return (response.readEntity(Branch.class));
    }


    /**
     * Delete a single project repository branch.
     *
     * DELETE /projects/:id/repository/branches/:branch
     *
     * @param projectId the project that the branch belongs to
     * @param branchName the name of the branch to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", projectId, "repository", "branches", urlEncode(branchName));
    }

    /**
     * Protects a single project repository branch. This is an idempotent function,
     * protecting an already protected repository branch will not produce an error.
     *
     * PUT /projects/:id/repository/branches/:branch/protect
     *
     * @param projectId the ID of the project to protect
     * @param branchName the name of the branch to protect
     * @return the branch info for the protected branch
     * @throws GitLabApiException if any exception occurs
     */
    public Branch protectBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "branches", urlEncode(branchName), "protect");
        return (response.readEntity(Branch.class));
    }

    /**
     * Unprotects a single project repository branch. This is an idempotent function, unprotecting an
     * already unprotected repository branch will not produce an error.
     *
     * PUT /projects/:id/repository/branches/:branch/unprotect
     *
     * @param projectId the ID of the project to un-protect
     * @param branchName the name of the branch to un-protect
     * @return the branch info for the unprotected branch
     * @throws GitLabApiException if any exception occurs
     */
    public Branch unprotectBranch(Integer projectId, String branchName) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects", projectId, "repository", "branches", urlEncode(branchName), "unprotect");
        return (response.readEntity(Branch.class));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * GET /projects/:id/repository/tags
     *
     * @param projectId the ID of the project to get the tags for
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Tag> getTags(Integer projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "repository", "tags");
        return (response.readEntity(new GenericType<List<Tag>>() {}));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order and in the specified page range.
     *
     * GET /projects/:id/repository/tags
     *
     * @param projectId the ID of the project to get the tags for
     * @param page the page to get
     * @param perPage the number of Tag instances per page
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Tag> getTags(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "repository", "tags");
        return (response.readEntity(new GenericType<List<Tag>>() {}));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * GET /projects/:id/repository/tags
     *
     * @param projectId the ID of the project to get the tags for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Tag> getTags(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Tag>(this, Tag.class, itemsPerPage, null, "projects", projectId, "repository", "tags"));
    }

    /**
     * Creates a tag on a particular ref of the given project. A message and release notes are optional.
     *
     * POST /projects/:id/repository/tags
     *
     * @param projectId the ID of the project
     * @param tagName The name of the tag Must be unique for the project
     * @param ref the git ref to place the tag on
     * @param message the message to included with the tag (optional)
     * @param releaseNotes the release notes for the tag (optional)
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     */
    public Tag createTag(Integer projectId, String tagName, String ref, String message, String releaseNotes) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("tag_name", tagName, true)
                .withParam("ref", ref, true)
                .withParam("message", message, false)
                .withParam("release_description", releaseNotes, false);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects", projectId, "repository", "tags");
        return (response.readEntity(Tag.class));
    }

    /**
     * Creates a tag on a particular ref of a given project. A message and a File instance containing the
     * release notes are optional.  This method is the same as {@link #createTag(Integer, String, String, String, String)},
     * but instead allows the release notes to be supplied in a file.
     *
     * POST /projects/:id/repository/tags
     *
     * @param projectId the ID of the project
     * @param tagName the name of the tag, must be unique for the project
     * @param ref the git ref to place the tag on
     * @param message the message to included with the tag (optional)
     * @param releaseNotesFile a whose contents are the release notes (optional)
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     */
    public Tag createTag(Integer projectId, String tagName, String ref, String message, File releaseNotesFile) throws GitLabApiException {

        String releaseNotes;
        if (releaseNotesFile != null) {
            try {
                releaseNotes = FileUtils.readFileContents(releaseNotesFile);
            } catch (IOException ioe) {
                throw (new GitLabApiException(ioe));
            }
        } else {
            releaseNotes = null;
        }

        return (createTag(projectId, tagName, ref, message, releaseNotes));
    }

    /**
     * Deletes the tag from a project with the specified tag name.
     *
     * DELETE /projects/:id/repository/tags/:tag_name
     *
     * @param projectId the ID of the project
     * @param tagName The name of the tag to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteTag(Integer projectId, String tagName) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", projectId, "repository", "tags", tagName);
    }

    /**
     * Get a list of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * @param projectId the ID of the project to get the files for
     * @return a tree with the root directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public List<TreeItem> getTree(Integer projectId) throws GitLabApiException {
        return (getTree(projectId, "/", "master"));
    }

    /**
     * Get a Pager of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * @param projectId the ID of the project to get the files for
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager containing a tree with the root directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<TreeItem> getTree(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (getTree(projectId, "/", "master", false, itemsPerPage));
    }

    /**
     * Get a list of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get content of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     *
     * @param projectId the ID of the project to get the files for
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public List<TreeItem> getTree(Integer projectId, String filePath, String refName) throws GitLabApiException {
        return (getTree(projectId, filePath, refName, false));
    }

    /**
     * Get a Pager of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get content of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     *
     * @param projectId the ID of the project to get the files for
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager containing a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<TreeItem> getTree(Integer projectId, String filePath, String refName, int itemsPerPage) throws GitLabApiException {
        return (getTree(projectId, filePath, refName, false, itemsPerPage));
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
     * @param projectId the ID of the project to get the files for
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param recursive flag to get a recursive tree or not
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public List<TreeItem> getTree(Integer projectId, String filePath, String refName, Boolean recursive) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("id", projectId, true)
                .withParam("path", filePath, false)
                .withParam(isApiVersion(ApiVersion.V3) ? "ref_name" : "ref", refName, false)
                .withParam("recursive", recursive, false)
                .withParam(PER_PAGE_PARAM, getDefaultPerPage());
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "tree");
        return (response.readEntity(new GenericType<List<TreeItem>>() {}));
    }

    /**
     * Get a Pager of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get contend of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     * recursive (optional) - Boolean value used to get a recursive tree (false by default)
     *
     * @param projectId the ID of the project to get the files for
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param recursive flag to get a recursive tree or not
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<TreeItem> getTree(Integer projectId, String filePath, String refName, Boolean recursive, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("id", projectId, true)
                .withParam("path", filePath, false)
                .withParam(isApiVersion(ApiVersion.V3) ? "ref_name" : "ref", refName, false)
                .withParam("recursive", recursive, false);
        return (new Pager<TreeItem>(this, TreeItem.class, itemsPerPage, formData.asMap(), "projects", projectId, "repository", "tree"));
    }

    /**
     * Get the raw file contents for a blob by blob SHA.
     *
     * GET /projects/:id/repository/raw_blobs/:sha
     *
     * @param projectId the ID of the project
     * @param sha the SHA of the file to get the contents for
     * @return the raw file contents for the blob on an InputStream
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream getRawBlobContent(Integer projectId, String sha) throws GitLabApiException {
        Response response = getWithAccepts(Response.Status.OK, null, MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "repository", "blobs", sha, "raw");
        return (response.readEntity(InputStream.class));
    }

    /**
     * Get an archive of the complete repository by SHA (optional).
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectId the ID of the project
     * @param sha the SHA of the archive to get
     * @return an input stream that can be used to save as a file
     * or to read the content of the archive
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream getRepositoryArchive(Integer projectId, String sha) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "repository", "archive");
        return (response.readEntity(InputStream.class));
    }

    /**
     * Get an archive of the complete repository by SHA (optional) and saves to the specified directory.
     * If the archive already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectId the ID of the project
     * @param sha the SHA of the archive to get
     * @param directory the File instance of the directory to save the archive to, if null will use "java.io.tmpdir"
     * @return a File instance pointing to the downloaded instance
     * @throws GitLabApiException if any exception occurs
     */
    public File getRepositoryArchive(Integer projectId, String sha, File directory) throws GitLabApiException {

        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "repository", "archive");

        try {

            if (directory == null)
                directory = new File(System.getProperty("java.io.tmpdir"));

            String filename = FileUtils.getFilenameFromContentDisposition(response);
            File file = new File(directory, filename);

            InputStream in = response.readEntity(InputStream.class);
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return (file);

        } catch (IOException ioe) {
            throw new GitLabApiException(ioe);
        }
    }

    /**
     * Compare branches, tags or commits. This can be accessed without authentication
     * if the repository is publicly accessible.
     * 
     * @param projectId the ID of the project owned by the authenticated user
     * @param from the commit SHA or branch name
     * @param to the commit SHA or branch name
     * @return a CompareResults containing the results of the comparison
     * @throws GitLabApiException if any exception occurs
     */
    public CompareResults compare(Integer projectId, String from, String to) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("from", from, true).withParam("to", to, true);
        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "compare");
        return (response.readEntity(CompareResults.class));
    }

    /**
     * Compare branches, tags or commits. This can be accessed without authentication
     * if the repository is publicly accessible.
     * 
     * @param projectPath the path of the project owned by the authenticated user
     * @param from the commit SHA or branch name
     * @param to the commit SHA or branch name
     * @return a CompareResults containing the results of the comparison
     * @throws GitLabApiException if any exception occurs
     */
    public CompareResults compare(String projectPath, String from, String to) throws GitLabApiException {

        Form formData = new GitLabApiForm().withParam("from", from, true).withParam("to", to, true);

        try {
            projectPath = URLEncoder.encode(projectPath, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw (new GitLabApiException(uee));
        }

        Response response = get(Response.Status.OK, formData.asMap(), "projects", projectPath, "repository", "compare");
        return (response.readEntity(CompareResults.class));
    }
}
