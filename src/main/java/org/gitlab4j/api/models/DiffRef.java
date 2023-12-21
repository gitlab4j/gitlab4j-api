package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class DiffRef implements Serializable {
    private static final long serialVersionUID = 1L;

    private String baseSha;
    private String headSha;
    private String startSha;

    public DiffRef() {
    }

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
