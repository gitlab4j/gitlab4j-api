package org.gitlab4j.api.models;

/**
 * This class is used when creating or updating a file in a repository.
 *
 * @see <a href="https://docs.gitlab.com/api/repository_files/#create-a-file-in-a-repository">Create a file</a>
 * @see <a href="https://docs.gitlab.com/api/repository_files/#update-a-file-in-a-repository">Update a file</a>
 */
public class RepositoryFileRequest extends RepositoryFile {
    private static final long serialVersionUID = 1L;

    private String authorEmail;
    private String authorName;

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public RepositoryFileRequest withAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return (this);
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public RepositoryFileRequest withAuthorName(String authorName) {
        this.authorName = authorName;
        return (this);
    }
}
