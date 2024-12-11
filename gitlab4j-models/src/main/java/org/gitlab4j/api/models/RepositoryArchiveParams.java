package org.gitlab4j.api.models;

import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Params for getting file archive of the repository.
 */
public class RepositoryArchiveParams {

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("path")
    private String path;

    /**
     * Add param "The commit SHA to download".
     *
     * @param sha the commit SHA to download
     * @return current params with sha
     */
    public RepositoryArchiveParams withSha(String sha) {
        this.sha = sha;
        return this;
    }

    /**
     * Add param "The subpath of the repository to download".
     *
     * @param path the subpath of the repository to download
     * @return current params with path
     */
    public RepositoryArchiveParams withPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get form with params.
     *
     * @return form with params
     */
    public GitLabForm getForm() {
        return new GitLabForm().withParam("sha", sha).withParam("path", path);
    }
}
