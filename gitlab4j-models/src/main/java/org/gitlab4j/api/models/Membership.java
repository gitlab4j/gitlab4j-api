package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Membership implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("source_id")
    private Long sourceId;

    @JsonProperty("source_name")
    private String sourceName;

    @JsonProperty("source_type")
    private MembershipSourceType sourceType;

    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public MembershipSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(MembershipSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
