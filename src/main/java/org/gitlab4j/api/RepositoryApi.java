package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Contributor;
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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the list of repository branches for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Branch> getBranches(Object projectIdOrPath) throws GitLabApiException {
        return (getBranches(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of repository branches from a project, sorted by name alphabetically.
     *
     * GET /projects/:id/repository/branches
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of Branch instances per page
     * @return the list of repository branches for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public List<Branch> getBranches(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches");
        return (response.readEntity(new GenericType<List<Branch>>() {}));
    }

    /**
     * Get a Pager of repository branches from a project, sorted by name alphabetically.
     *
     * GET /projects/:id/repository/branches
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the list of repository branches for the specified project ID
     *
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Branch> getBranches(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Branch>(this, Branch.class, itemsPerPage, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches"));
    }

    /**
     * Get a Stream of repository branches from a project, sorted by name alphabetically.
     *
     * GET /projects/:id/repository/branches
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream of repository branches for the specified project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Branch> getBranchesStream(Object projectIdOrPath) throws GitLabApiException {
        return (getBranches(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Get a single project repository branch.
     *
     * GET /projects/:id/repository/branches/:branch
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to get
     * @return the branch info for the specified project ID/branch name pair
     * @throws GitLabApiException if any exception occurs
     */
    public Branch getBranch(Object projectIdOrPath, String branchName) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches", urlEncode(branchName));
        return (response.readEntity(Branch.class));
    }

    /**
     * Get an Optional instance with the value for the specific repository branch.
     *
     * GET /projects/:id/repository/branches/:branch
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to get
     * @return an Optional instance with the info for the specified project ID/branch name pair as the value
     * @throws GitLabApiException if any exception occurs
     */
    public Optional<Branch> getOptionalBranch(Object projectIdOrPath, String branchName) throws GitLabApiException {
        try {
            return (Optional.ofNullable(getBranch(projectIdOrPath, branchName)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a branch for the project. Support as of version 6.8.x
     *
     * POST /projects/:id/repository/branches
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to create
     * @param ref Source to create the branch from, can be an existing branch, tag or commit SHA
     * @return the branch info for the created branch
     * @throws GitLabApiException if any exception occurs
     */
    public Branch createBranch(Object projectIdOrPath, String branchName, String ref) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam(isApiVersion(ApiVersion.V3) ? "branch_name" : "branch", branchName, true)
                .withParam("ref", ref, true);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches");
        return (response.readEntity(Branch.class));
    }

    /**
     * Delete a single project repository branch.
     *
     * DELETE /projects/:id/repository/branches/:branch
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to delete
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteBranch(Object projectIdOrPath, String branchName) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches", urlEncode(branchName));
    }

    /**
     * Protects a single project repository branch. This is an idempotent function,
     * protecting an already protected repository branch will not produce an error.
     *
     * PUT /projects/:id/repository/branches/:branch/protect
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to protect
     * @return the branch info for the protected branch
     * @throws GitLabApiException if any exception occurs
     */
    public Branch protectBranch(Object projectIdOrPath, String branchName) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches", urlEncode(branchName), "protect");
        return (response.readEntity(Branch.class));
    }

    /**
     * Unprotects a single project repository branch. This is an idempotent function, unprotecting an
     * already unprotected repository branch will not produce an error.
     *
     * PUT /projects/:id/repository/branches/:branch/unprotect
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param branchName the name of the branch to un-protect
     * @return the branch info for the unprotected branch
     * @throws GitLabApiException if any exception occurs
     */
    public Branch unprotectBranch(Object projectIdOrPath, String branchName) throws GitLabApiException {
        Response response = put(Response.Status.OK, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "branches", urlEncode(branchName), "unprotect");
        return (response.readEntity(Branch.class));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * GET /projects/:id/repository/tags
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     * @deprecated Replaced by TagsApi.getTags(Object)
     */
    @Deprecated
    public List<Tag> getTags(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "tags");
        return (response.readEntity(new GenericType<List<Tag>>() {}));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order and in the specified page range.
     *
     * GET /projects/:id/repository/tags
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of Tag instances per page
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     * @deprecated Replaced by TagsApi.getTags(Object, int, int)
     */
    @Deprecated
    public List<Tag> getTags(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "tags");
        return (response.readEntity(new GenericType<List<Tag>>() {}));
    }

    /**
     * Get a list of repository tags from a project, sorted by name in reverse alphabetical order.
     *
     * GET /projects/:id/repository/tags
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return the list of tags for the specified project ID
     * @throws GitLabApiException if any exception occurs
     * @deprecated Replaced by TagsApi.getTags(Object, int)
     */
    @Deprecated
    public Pager<Tag> getTags(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Tag>(this, Tag.class, itemsPerPage, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "tags"));
    }

    /**
     * Creates a tag on a particular ref of the given project. A message and release notes are optional.
     *
     * POST /projects/:id/repository/tags
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param tagName The name of the tag Must be unique for the project
     * @param ref the git ref to place the tag on
     * @param message the message to included with the tag (optional)
     * @param releaseNotes the release notes for the tag (optional)
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     * @deprecated Replaced by TagsApi.createTag(Object, String, String, String, String)
     */
    public Tag createTag(Object projectIdOrPath, String tagName, String ref, String message, String releaseNotes) throws GitLabApiException {

        Form formData = new GitLabApiForm()
                .withParam("tag_name", tagName, true)
                .withParam("ref", ref, true)
                .withParam("message", message, false)
                .withParam("release_description", releaseNotes, false);
        Response response = post(Response.Status.CREATED, formData.asMap(), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "tags");
        return (response.readEntity(Tag.class));
    }

    /**
     * Creates a tag on a particular ref of a given project. A message and a File instance containing the
     * release notes are optional.  This method is the same as {@link #createTag(Object, String, String, String, String)},
     * but instead allows the release notes to be supplied in a file.
     *
     * POST /projects/:id/repository/tags
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param tagName the name of the tag, must be unique for the project
     * @param ref the git ref to place the tag on
     * @param message the message to included with the tag (optional)
     * @param releaseNotesFile a whose contents are the release notes (optional)
     * @return a Tag instance containing info on the newly created tag
     * @throws GitLabApiException if any exception occurs
     * @deprecated Replaced by TagsApi.CreateTag(Object, String, String, String, File)
     */
    public Tag createTag(Object projectIdOrPath, String tagName, String ref, String message, File releaseNotesFile) throws GitLabApiException {

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

        return (createTag(projectIdOrPath, tagName, ref, message, releaseNotes));
    }

    /**
     * Deletes the tag from a project with the specified tag name.
     *
     * DELETE /projects/:id/repository/tags/:tag_name
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param tagName The name of the tag to delete
     * @throws GitLabApiException if any exception occurs
     * @deprecated Replaced by TagsApi.deleteTag(Object, String)
     */
    public void deleteTag(Object projectIdOrPath, String tagName) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "repository", "tags", tagName);
    }

    /**
     * Get a list of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a tree with the root directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public List<TreeItem> getTree(Object projectIdOrPath) throws GitLabApiException {
        return (getTree(projectIdOrPath, "/", "master"));
    }

    /**
     * Get a Pager of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager containing a tree with the root directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<TreeItem> getTree(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (getTree(projectIdOrPath, "/", "master", false, itemsPerPage));
    }

    /**
     * Get a list of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream containing a tree with the root directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<TreeItem> getTreeStream(Object projectIdOrPath) throws GitLabApiException {
        return (getTreeStream(projectIdOrPath, "/", "master"));
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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public List<TreeItem> getTree(Object projectIdOrPath, String filePath, String refName) throws GitLabApiException {
        return (getTree(projectIdOrPath, filePath, refName, false));
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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager containing a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<TreeItem> getTree(Object projectIdOrPath, String filePath, String refName, int itemsPerPage) throws GitLabApiException {
        return (getTree(projectIdOrPath, filePath, refName, false, itemsPerPage));
    }

    /**
     * Get a Stream of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get content of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @return a Stream containing a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<TreeItem> getTreeStream(Object projectIdOrPath, String filePath, String refName) throws GitLabApiException {
        return (getTreeStream(projectIdOrPath, filePath, refName, false));
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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param recursive flag to get a recursive tree or not
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public List<TreeItem> getTree(Object projectIdOrPath, String filePath, String refName, Boolean recursive) throws GitLabApiException {
        return (getTree(projectIdOrPath, filePath, refName, recursive, getDefaultPerPage()).all());
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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param recursive flag to get a recursive tree or not
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<TreeItem> getTree(Object projectIdOrPath, String filePath, String refName, Boolean recursive, int itemsPerPage) throws GitLabApiException {
        Form formData = new GitLabApiForm()
                .withParam("id", getProjectIdOrPath(projectIdOrPath), true)
                .withParam("path", filePath, false)
                .withParam(isApiVersion(ApiVersion.V3) ? "ref_name" : "ref", refName, false)
                .withParam("recursive", recursive, false);
        return (new Pager<TreeItem>(this, TreeItem.class, itemsPerPage, formData.asMap(), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "tree"));
    }

    /**
     * Get a Stream of repository files and directories in a project.
     *
     * GET /projects/:id/repository/tree
     *
     * id (required) - The ID of a project
     * path (optional) - The path inside repository. Used to get contend of subdirectories
     * ref_name (optional) - The name of a repository branch or tag or if not given the default branch
     * recursive (optional) - Boolean value used to get a recursive tree (false by default)
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param filePath the path inside repository, used to get content of subdirectories
     * @param refName the name of a repository branch or tag or if not given the default branch
     * @param recursive flag to get a recursive tree or not
     * @return a Stream containing a tree with the directories and files of a project
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<TreeItem> getTreeStream(Object projectIdOrPath, String filePath, String refName, Boolean recursive) throws GitLabApiException {
        return (getTree(projectIdOrPath, filePath, refName, recursive, getDefaultPerPage()).stream());
    }

    /**
     * Get the raw file contents for a blob by blob SHA.
     *
     * GET /projects/:id/repository/raw_blobs/:sha
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the file to get the contents for
     * @return the raw file contents for the blob on an InputStream
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream getRawBlobContent(Object projectIdOrPath, String sha) throws GitLabApiException {
        Response response = getWithAccepts(Response.Status.OK, null, MediaType.MEDIA_TYPE_WILDCARD,
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "blobs", sha, "raw");
        return (response.readEntity(InputStream.class));
    }

    /**
     * Get an archive of the complete repository by SHA (optional).
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the archive to get
     * @return an input stream that can be used to save as a file
     * or to read the content of the archive
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream getRepositoryArchive(Object projectIdOrPath, String sha) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "archive");
        return (response.readEntity(InputStream.class));
    }

    /**
     * Get an archive of the complete repository by SHA (optional).
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the archive to get
     * @param format The archive format, defaults to "tar.gz" if null
     * @return an input stream that can be used to save as a file or to read the content of the archive
     * @throws GitLabApiException if format is not a valid archive format or any exception occurs
     */
    public InputStream getRepositoryArchive(Object projectIdOrPath, String sha, String format) throws GitLabApiException {
        ArchiveFormat archiveFormat = ArchiveFormat.forValue(format);
        return (getRepositoryArchive(projectIdOrPath, sha, archiveFormat));
    }

    /**
     * Get an archive of the complete repository by SHA (optional).
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the archive to get
     * @param format The archive format, defaults to TAR_GZ if null
     * @return an input stream that can be used to save as a file or to read the content of the archive
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream getRepositoryArchive(Object projectIdOrPath, String sha, ArchiveFormat format) throws GitLabApiException {

        if (format == null) {
            format = ArchiveFormat.TAR_GZ;
        }

        /*
         * Gitlab-ce has a bug when you try to download file archives with format by using "&format=zip(or tar... etc.)",
         * there is a solution to request .../archive.:format instead of .../archive?format=:format.
         *
         * Issue:  https://gitlab.com/gitlab-org/gitlab-ce/issues/45992
         *         https://gitlab.com/gitlab-com/support-forum/issues/3067
         */
        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "archive" + "." + format.toString());
        return (response.readEntity(InputStream.class));
    }

    /**
     * Get an archive of the complete repository by SHA (optional) and saves to the specified directory.
     * If the archive already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the archive to get
     * @param directory the File instance of the directory to save the archive to, if null will use "java.io.tmpdir"
     * @return a File instance pointing to the downloaded instance
     * @throws GitLabApiException if any exception occurs
     */
    public File getRepositoryArchive(Object projectIdOrPath, String sha, File directory) throws GitLabApiException {

        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "archive");

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
     * Get an archive of the complete repository by SHA (optional) and saves to the specified directory.
     * If the archive already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the archive to get
     * @param directory the File instance of the directory to save the archive to, if null will use "java.io.tmpdir"
     * @param format The archive format, defaults to "tar.gz" if null
     * @return a File instance pointing to the downloaded instance
     * @throws GitLabApiException if format is not a valid archive format or any exception occurs
     */
    public File getRepositoryArchive(Object projectIdOrPath, String sha, File directory, String format) throws GitLabApiException {
        ArchiveFormat archiveFormat = ArchiveFormat.forValue(format);
        return (getRepositoryArchive(projectIdOrPath, sha, directory, archiveFormat));
    }

    /**
     * Get an archive of the complete repository by SHA (optional) and saves to the specified directory.
     * If the archive already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param sha the SHA of the archive to get
     * @param directory the File instance of the directory to save the archive to, if null will use "java.io.tmpdir"
     * @param format The archive format, defaults to TAR_GZ if null
     * @return a File instance pointing to the downloaded instance
     * @throws GitLabApiException if any exception occurs
     */
    public File getRepositoryArchive(Object projectIdOrPath, String sha, File directory, ArchiveFormat format) throws GitLabApiException {

        if (format == null) {
            format = ArchiveFormat.TAR_GZ;
        }

        /*
         * Gitlab-ce has a bug when you try to download file archives with format by using "&format=zip(or tar... etc.)",
         * there is a solution to request .../archive.:format instead of .../archive?format=:format.
         *
         * Issue:  https://gitlab.com/gitlab-org/gitlab-ce/issues/45992
         *         https://gitlab.com/gitlab-com/support-forum/issues/3067
         */
        Form formData = new GitLabApiForm().withParam("sha", sha);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "archive" + "." + format.toString());

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
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param from the commit SHA or branch name
     * @param to the commit SHA or branch name
     * @return a CompareResults containing the results of the comparison
     * @throws GitLabApiException if any exception occurs
     */
    public CompareResults compare(Object projectIdOrPath, String from, String to) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("from", from, true).withParam("to", to, true);
        Response response = get(Response.Status.OK, formData.asMap(), "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "compare");
        return (response.readEntity(CompareResults.class));
    }

    /**
     * Get a list of contributors from a project.
     *
     * GET /projects/:id/repository/contributors
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a List containing the contributors for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Contributor> getContributors(Object projectIdOrPath) throws GitLabApiException {
        return (getContributors(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get a list of contributors from a project and in the specified page range.
     *
     * GET /projects/:id/repository/contributors
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of projects per page
     * @return a List containing the contributors for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public List<Contributor> getContributors(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage),
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "contributors");
        return (response.readEntity(new GenericType<List<Contributor>>() { }));
    }

    /**
     * Get a Pager of contributors from a project.
     *
     * GET /projects/:id/repository/contributors
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of Project instances that will be fetched per page
     * @return a Pager containing the contributors for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Contributor> getContributors(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return new Pager<Contributor>(this, Contributor.class, itemsPerPage, null, "projects",
                getProjectIdOrPath(projectIdOrPath), "repository", "contributors");
    }

    /**
     * Get a list of contributors from a project.
     *
     * GET /projects/:id/repository/contributors
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a List containing the contributors for the specified project ID
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Contributor> getContributorsStream(Object projectIdOrPath) throws GitLabApiException {
        return (getContributors(projectIdOrPath, getDefaultPerPage()).stream());
    }
}
