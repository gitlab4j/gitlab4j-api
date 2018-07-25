package org.gitlab4j.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.RepositoryFile;

/**
 * This class provides an entry point to all the GitLab API repository files calls.
 */
public class RepositoryFileApi extends AbstractApi {

    public RepositoryFileApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get information on a file in the repository. Allows you to receive information about file in repository like name, size.
     * Only works with GitLab 11.1.0+, returns an empty object for earlier versions of GitLab.
     *
     * HEAD /projects/:id/repository/files
     *
     * @param projectIdOrPath the id, path of the project, or a Project instance holding the project ID or path
     * @param filePath (required) - Full path to the file. Ex. lib/class.rb
     * @param ref (required) - The name of branch, tag or commit
     * @return a RepositoryFile instance with the file info
     * @throws GitLabApiException if any exception occurs
     * @since GitLab-11.1.0
     */
    public RepositoryFile getFileInfo(Object projectIdOrPath, String filePath, String ref) throws GitLabApiException {

        Form form = new Form();
        addFormParam(form, "ref", ref, true);
        Response response = head(Response.Status.OK, form.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "repository", "files", urlEncode(filePath));

        RepositoryFile file = new RepositoryFile();
        file.setBlobId(response.getHeaderString("X-Gitlab-Blob-Id"));
        file.setCommitId(response.getHeaderString("X-Gitlab-Commit-Id"));
        file.setEncoding(response.getHeaderString("X-Gitlab-Encoding"));
        file.setFileName(response.getHeaderString("X-Gitlab-File-Name"));
        file.setFilePath(response.getHeaderString("X-Gitlab-File-Path"));
        file.setLastCommitId(response.getHeaderString("X-Gitlab-Last-Commit-Id"));
        file.setRef(response.getHeaderString("X-Gitlab-Ref"));

        String sizeStr = response.getHeaderString("X-Gitlab-Size");
        file.setSize(sizeStr != null ? Integer.valueOf(sizeStr) : -1);

        return (file);
    }

    /**
     * Get file from repository. Allows you to receive information about file in repository like name, size, content.
     * Note that file content is Base64 encoded.
     *
     * GET /projects/:id/repository/files
     *
     * @param projectIdOrPath the id, path of the project, or a Project instance holding the project ID or path
     * @param filePath (required) - Full path to the file. Ex. lib/class.rb
     * @param ref (required) - The name of branch, tag or commit
     * @return a RepositoryFile instance with the file info and file content
     * @throws GitLabApiException if any exception occurs
     */
    public RepositoryFile getFile(Object projectIdOrPath, String filePath, String ref) throws GitLabApiException {
        return (getFile(projectIdOrPath, filePath, ref, true));
    }

    /**
     * Get file from repository. Allows you to receive information about file in repository like name, size, content.
     * Note that file content is Base64 encoded.
     *
     * GET /projects/:id/repository/files
     *
     * @param filePath (required) - Full path to the file. Ex. lib/class.rb
     * @param projectId (required) - the project ID
     * @param ref (required) - The name of branch, tag or commit
     * @return a RepositoryFile instance with the file info and file content
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0, replaced by {@link #getFile(Object, String, String)}
     */
    public RepositoryFile getFile(String filePath, Integer projectId, String ref) throws GitLabApiException {

        if (isApiVersion(ApiVersion.V3)) {
            return (getFileV3(filePath, projectId, ref));
        }

        return (getFile(projectId, filePath, ref, true));
    }

    /**
     * Get file from repository. Allows you to receive information about file in repository like name, size, and optionally content.
     * Note that file content is Base64 encoded.
     *
     * GET /projects/:id/repository/files
     *
     * @param projectIdOrPath the id, path of the project, or a Project instance holding the project ID or path
     * @param filePath (required) - Full path to the file. Ex. lib/class.rb
     * @param ref (required) - The name of branch, tag or commit
     * @param includeContent if true will also fetch file content
     * @return a RepositoryFile instance with the file info and optionally file content
     * @throws GitLabApiException if any exception occurs
     */
    public RepositoryFile getFile(Object projectIdOrPath, String filePath, String ref, boolean includeContent) throws GitLabApiException {

        if (!includeContent) {
            return (getFileInfo(projectIdOrPath, filePath, ref));
        }

        Form form = new Form();
        addFormParam(form, "ref", ref, true);
        Response response = get(Response.Status.OK, form.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "repository", "files", urlEncode(filePath));
        return (response.readEntity(RepositoryFile.class));
    }

    /**
     * Get file from repository. Allows you to receive information about file in repository like name, size, content.
     * Note that file content is Base64 encoded.
     *
     * GET /projects/:id/repository/files
     *
     * @param filePath (required) - Full path to new file. Ex. lib/class.rb
     * @param projectId (required) - the project ID
     * @param ref (required) - The name of branch, tag or commit
     * @return a RepositoryFile instance with the file info
     * @throws GitLabApiException if any exception occurs
     * @deprecated  Will be removed in version 5.0
     */
    protected RepositoryFile getFileV3(String filePath, Integer projectId, String ref) throws GitLabApiException {
        Form form = new Form();
        addFormParam(form, "file_path", filePath, true);
        addFormParam(form, "ref", ref, true);
        Response response = get(Response.Status.OK, form.asMap(), "projects", projectId, "repository", "files");
        return (response.readEntity(RepositoryFile.class));
    }

    /**
     * Create new file in repository
     *
     * POST /projects/:id/repository/files
     *
     * file_path (required) - Full path to new file. Ex. lib/class.rb
     * branch_name (required) - The name of branch
     * encoding (optional) - 'text' or 'base64'. Text is default.
     * content (required) - File content
     * commit_message (required) - Commit message
     *
     * @param file a ReposityoryFile instance with info for the file to create
     * @param projectId the project ID
     * @param branchName the name of branch
     * @param commitMessage the commit message
     * @return a RepositoryFile instance with the created file info
     * @throws GitLabApiException if any exception occurs
     */
    public RepositoryFile createFile(RepositoryFile file, Integer projectId, String branchName, String commitMessage) throws GitLabApiException {
        Form formData = createForm(file, branchName, commitMessage);
        Response response;
        if (isApiVersion(ApiVersion.V3)) {
            response = post(Response.Status.CREATED, formData, "projects", projectId, "repository", "files");
        } else {
            response = post(Response.Status.CREATED, formData, "projects", projectId, "repository", "files", urlEncode(file.getFilePath()));
        }

        return (response.readEntity(RepositoryFile.class));
    }

    /**
     * Update existing file in repository
     *
     * PUT /projects/:id/repository/files
     *
     * file_path (required) - Full path to new file. Ex. lib/class.rb
     * branch_name (required) - The name of branch
     * encoding (optional) - 'text' or 'base64'. Text is default.
     * content (required) - File content
     * commit_message (required) - Commit message
     *
     * @param file a ReposityoryFile instance with info for the file to update
     * @param projectId the project ID
     * @param branchName the name of branch
     * @param commitMessage the commit message
     * @return a RepositoryFile instance with the updated file info
     * @throws GitLabApiException if any exception occurs
     */
    public RepositoryFile updateFile(RepositoryFile file, Integer projectId, String branchName, String commitMessage) throws GitLabApiException {
        Form formData = createForm(file, branchName, commitMessage);
        Response response;
        if (isApiVersion(ApiVersion.V3)) {
            response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "files");
        } else {
            response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "repository", "files", urlEncode(file.getFilePath()));
        }

        return (response.readEntity(RepositoryFile.class));
    }

    /**
     * Delete existing file in repository
     *
     * DELETE /projects/:id/repository/files
     *
     * file_path (required) - Full path to file. Ex. lib/class.rb
     * branch_name (required) - The name of branch
     * commit_message (required) - Commit message
     *
     * @param filePath full path to new file. Ex. lib/class.rb
     * @param projectId the project ID
     * @param branchName the name of branch
     * @param commitMessage the commit message
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteFile(String filePath, Integer projectId, String branchName, String commitMessage) throws GitLabApiException {

        if (filePath == null) {
            throw new RuntimeException("filePath cannot be null");
        }

        Form form = new Form();
        addFormParam(form, isApiVersion(ApiVersion.V3) ? "branch_name" : "branch", branchName, true);
        addFormParam(form, "commit_message", commitMessage, true);
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);

        if (isApiVersion(ApiVersion.V3)) {
            addFormParam(form, "file_path", filePath, true);
            delete(expectedStatus, form.asMap(), "projects", projectId, "repository", "files");
        } else {
            delete(expectedStatus, form.asMap(), "projects", projectId, "repository", "files", urlEncode(filePath));
        }
    }

    /**
     * Get the raw file for the file by commit sha and path. Thye file will be saved to the specified directory.
     * If the file already exists in the directory it will be overwritten.
     *
     * GET /projects/:id/repository/archive
     *
     * @param projectId the ID of the project
     * @param commitOrBranchName the commit or branch name to get the file for
     * @param filepath the path of the file to get
     * @param directory the File instance of the directory to save the file to, if null will use "java.io.tmpdir"
     * @return a File instance pointing to the download of the specified file
     * @throws GitLabApiException if any exception occurs
     */
    public File getRawFile(Integer projectId, String commitOrBranchName, String filepath, File directory) throws GitLabApiException {

        Form formData = new GitLabApiForm().withParam("ref", commitOrBranchName, true);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(), MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "repository", "files", urlEncode(filepath), "raw");

        try {

            if (directory == null)
                directory = new File(System.getProperty("java.io.tmpdir"));

            String filename = new File(filepath).getName();
            File file = new File(directory, filename);

            InputStream in = response.readEntity(InputStream.class);
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return (file);

        } catch (IOException ioe) {
            throw new GitLabApiException(ioe);
        }
    }

    /**
     * Get the raw file contents for a file by commit sha and path.
     *
     * GET /projects/:id/repository/blobs/:sha
     *
     * @param projectId the ID of the project
     * @param commitOrBranchName the commit or branch name to get the file contents for
     * @param filepath the path of the file to get
     * @return an InputStream to read the raw file from
     * @throws GitLabApiException if any exception occurs
     */
    public InputStream getRawFile(Integer projectId, String commitOrBranchName, String filepath) throws GitLabApiException {
        Form formData = new GitLabApiForm().withParam("ref", commitOrBranchName, true);
        Response response = getWithAccepts(Response.Status.OK, formData.asMap(),  MediaType.MEDIA_TYPE_WILDCARD,
                "projects", projectId, "repository", "files", urlEncode(filepath), "raw");
        return (response.readEntity(InputStream.class));
    }

    private Form createForm(RepositoryFile file, String branchName, String commitMessage) {

        Form form = new Form();
        if (isApiVersion(ApiVersion.V3)) {
            addFormParam(form, "file_path", file.getFilePath(), true);
            addFormParam(form, "branch_name", branchName, true);
        } else {
            addFormParam(form, "branch", branchName, true);
        }

        addFormParam(form, "encoding", file.getEncoding(), false);

        // Cannot use addFormParam() as it does not accept an empty or whitespace only string
        String content = file.getContent();
        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        form.param("content", content);

        addFormParam(form, "commit_message", commitMessage, true);
        return (form);
    }
}