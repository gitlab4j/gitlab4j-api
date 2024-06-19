package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApiForm;

import java.io.Serializable;

/**
 * This class is used to filter commit status when getting lists of them.
 */
public class CommitStatusFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ref;
    private String stage;
    private String name;
    private Boolean all;

    public CommitStatusFilter withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public CommitStatusFilter withStage(String stage) {
        this.stage = stage;
        return this;
    }

    public CommitStatusFilter withName(String name) {
        this.name = name;
        return this;
    }


    public CommitStatusFilter withAll(Boolean all) {
        this.all = all;
        return this;
    }

    @JsonIgnore
    public GitLabApiForm getQueryParams(int page, int perPage) {
        return (getQueryParams()
            .withParam(Constants.PAGE_PARAM, page)
            .withParam(Constants.PER_PAGE_PARAM, perPage));
    }

    @JsonIgnore
    public GitLabApiForm getQueryParams() {
        return (new GitLabApiForm()
            .withParam("ref", ref)
            .withParam("stage", stage)
            .withParam("name", name)
            .withParam("all", all));
    }
}
