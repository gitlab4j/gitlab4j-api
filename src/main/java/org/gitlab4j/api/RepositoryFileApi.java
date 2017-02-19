package org.gitlab4j.api;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.RepositoryFile;

/**
 * This class provides an entry point to all the GitLab API repository files calls.
 */
public class RepositoryFileApi extends AbstractApi {

    public RepositoryFileApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get file from repository. Allows you to receive information about file in repository like name, size, content.
     * Note that file content is Base64 encoded.
     *
     * GET /projects/:id/repository/files
     *
     * @param filePath (required) - Full path to new file. Ex. lib/class.rb
     * @param projectId
     * @param ref (required) - The name of branch, tag or commit
     * @return a RepositoryFile instance with the file info
     * @throws GitLabApiException
     */
    public RepositoryFile getFile(String filePath, Integer projectId, String ref) throws GitLabApiException {
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
     * @param file
     * @param projectId
     * @param branchName
     * @param commitMessage
     * @return a RepositoryFile instance with the created file info
     * @throws GitLabApiException
     */
    public RepositoryFile createFile(RepositoryFile file, Integer projectId, String branchName, String commitMessage) throws GitLabApiException {
        Form formData = file2form(file, branchName, commitMessage);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "repository", "files");
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
     * @param file
     * @param projectId
     * @param branchName
     * @param commitMessage
     * @return a RepositoryFile instance with the updated file info
     * @throws GitLabApiException
     */
    public RepositoryFile updateFile(RepositoryFile file, Integer projectId, String branchName, String commitMessage) throws GitLabApiException {
        Form form = file2form(file, branchName, commitMessage);
        Response response = put(Response.Status.OK, form.asMap(), "projects", projectId, "repository", "files");
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
     * @param filePath
     * @param projectId
     * @param branchName
     * @param commitMessage
     * @throws GitLabApiException
     */
    public void deleteFile(String filePath, Integer projectId, String branchName, String commitMessage) throws GitLabApiException {

        if (filePath == null) {
            throw new RuntimeException("filePath cannot be null");
        }

        Form form = new Form();
        addFormParam(form, "file_path", filePath, true);
        addFormParam(form, "branch_name", branchName, true);
        addFormParam(form, "commit_message", commitMessage, true);
        delete(Response.Status.OK, form.asMap(), "projects", projectId, "repository", "files");
    }

    private Form file2form(RepositoryFile file, String branchName, String commitMessage) {
        Form form = new Form();
        addFormParam(form, "file_path", file.getFilePath(), true);
        addFormParam(form, "branch_name", branchName, true);
        addFormParam(form, "encoding", file.getEncoding(), false);
        addFormParam(form, "content", file.getContent(), true);
        addFormParam(form, "commit_message", commitMessage, true);
        return form;
    }
}
