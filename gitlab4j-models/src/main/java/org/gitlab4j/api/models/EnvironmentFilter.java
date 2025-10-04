package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;

public class EnvironmentFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String search;
    private String states;

    public EnvironmentFilter withName(String name) {
        this.name = name;
        return this;
    }

    public EnvironmentFilter withSearch(String search) {
        this.search = search;
        return this;
    }

    public EnvironmentFilter withStates(String states) {
        this.states = states;
        return this;
    }

    /**
     * Get the query params specified by this filter.
     *
     * @return a GitLabApiForm instance holding the query parameters for this EnvironmentFilter instance
     */
    public GitLabForm getQueryParams() {
        return new GitLabForm()
                .withParam("name", name)
                .withParam("search", search)
                .withParam("states", states);
    }
}
