package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class ContainerExpirationPolicy implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cadence;
    private Boolean enabled;
    private Integer keepN;
    private String olderThan;
    private String nameRegex;
    private String nameRegexKeep;
    private String nextRunAt;

    public String getCadence() {
        return cadence;
    }

    public void setCadence(String cadence) {
        this.cadence = cadence;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getKeepN() {
        return keepN;
    }

    public void setKeepN(Integer keepN) {
        this.keepN = keepN;
    }

    public String getOlderThan() {
        return olderThan;
    }

    public void setOlderThan(String olderThan) {
        this.olderThan = olderThan;
    }

    public String getNameRegex() {
        return nameRegex;
    }

    public void setNameRegex(String nameRegex) {
        this.nameRegex = nameRegex;
    }

    public String getNameRegexKeep() {
        return nameRegexKeep;
    }

    public void setNameRegexKeep(String nameRegexKeep) {
        this.nameRegexKeep = nameRegexKeep;
    }

    public String getNextRunAt() {
        return nextRunAt;
    }

    public void setNextRunAt(String nextRunAt) {
        this.nextRunAt = nextRunAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
