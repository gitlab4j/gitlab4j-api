package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitStats implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The number of additions in the commit.
     */
    @JsonProperty("additions")
    private Integer additions;

    /**
     * The number of deletions in the commit.
     */
    @JsonProperty("deletions")
    private Integer deletions;

    /**
     * The total number of changes in the commit (additions + deletions).
     */
    @JsonProperty("total")
    private Integer total;

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
