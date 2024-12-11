package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiffRef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The base SHA for the diff reference.
     */
    @JsonProperty("base_sha")
    private String baseSha;

    /**
     * The head SHA for the diff reference.
     */
    @JsonProperty("head_sha")
    private String headSha;

    /**
     * The start SHA for the diff reference.
     */
    @JsonProperty("start_sha")
    private String startSha;

    public DiffRef() {}

    public String getBaseSha() {
        return baseSha;
    }

    public void setBaseSha(final String baseSha) {
        this.baseSha = baseSha;
    }

    public String getHeadSha() {
        return headSha;
    }

    public void setHeadSha(final String headSha) {
        this.headSha = headSha;
    }

    public String getStartSha() {
        return startSha;
    }

    public void setStartSha(final String startSha) {
        this.startSha = startSha;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
