package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SystemHook implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("url")
    private String url;

    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    @JsonProperty("push_events")
    private Boolean pushEvents;

    @JsonProperty("tag_push_events")
    private Boolean tagPushEvents;

    @JsonProperty("enable_ssl_verification")
    private Boolean enableSslVerification;

    @JsonProperty("repository_update_events")
    private Boolean repositoryUpdateEvents;

    @JsonProperty("merge_requests_events")
    private Boolean mergeRequestsEvents;

    @JsonProperty("url_variables")
    private List<SystemHook.UrlVariable> urlVariables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPushEvents() {
        return pushEvents;
    }

    public void setPushEvents(Boolean pushEvents) {
        this.pushEvents = pushEvents;
    }

    public Boolean getTagPushEvents() {
        return tagPushEvents;
    }

    public void setTagPushEvents(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
    }

    public Boolean getEnableSslVerification() {
        return enableSslVerification;
    }

    public void setEnableSslVerification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
    }

    public void setRepositoryUpdateEvents(Boolean repositoryUpdateEvents) {
        this.repositoryUpdateEvents = repositoryUpdateEvents;
    }

    public Boolean getRepositoryUpdateEvents() {
        return repositoryUpdateEvents;
    }

    public void setMergeRequestsEvents(Boolean mergeRequestsEvents) {
        this.mergeRequestsEvents = mergeRequestsEvents;
    }

    public Boolean getMergeRequestsEvents() {
        return mergeRequestsEvents;
    }

    public List<SystemHook.UrlVariable> getUrlVariables() {
        return urlVariables;
    }

    public void setUrlVariables(List<SystemHook.UrlVariable> urlVariables) {
        this.urlVariables = urlVariables;
    }

    public SystemHook withId(Long id) {
        this.id = id;
        return this;
    }

    public SystemHook withName(String name) {
        this.name = name;
        return this;
    }

    public SystemHook withDescription(String description) {
        this.description = description;
        return this;
    }

    public SystemHook withUrl(String url) {
        this.url = url;
        return this;
    }

    public SystemHook withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SystemHook withPushEvents(Boolean pushEvents) {
        this.pushEvents = pushEvents;
        return this;
    }

    public SystemHook withTagPushEvents(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
        return this;
    }

    public SystemHook withEnableSslVerification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
        return this;
    }

    public SystemHook withRepositoryUpdateEvents(Boolean repositoryUpdateEvents) {
        this.repositoryUpdateEvents = repositoryUpdateEvents;
        return this;
    }

    public SystemHook withMergeRequestsEvents(Boolean mergeRequestsEvents) {
        this.mergeRequestsEvents = mergeRequestsEvents;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }

    public static class UrlVariable implements Serializable {
        private static final long serialVersionUID = 1L;

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }
}
