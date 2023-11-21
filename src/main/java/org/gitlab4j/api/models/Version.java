package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Version {

    private String version;
    private String revision;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
