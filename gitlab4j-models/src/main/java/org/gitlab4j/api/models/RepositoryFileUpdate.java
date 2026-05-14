package org.gitlab4j.api.models;

/**
 * This class is used as input when updating a file in a repository.
 *
 * @see <a href="https://docs.gitlab.com/api/repository_files/#update-a-file-in-a-repository">Update a file in a repository</a>
 */
public class RepositoryFileUpdate extends RepositoryFileCreate {
    private static final long serialVersionUID = 1L;

    private String lastCommitId;

    public String getLastCommitId() {
        return lastCommitId;
    }

    public void setLastCommitId(String lastCommitId) {
        this.lastCommitId = lastCommitId;
    }

    public RepositoryFileUpdate withLastCommitId(String lastCommitId) {
        this.lastCommitId = lastCommitId;
        return this;
    }
}
