package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Blame implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The commit associated with the blame.
     */
    @JsonProperty("commit")
    private Commit commit;

    /**
     * The list of lines associated with the blame.
     */
    @JsonProperty("lines")
    private List<String> lines;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
