package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class RegistryRepositoryTag implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("path")
    private String path;

    @JsonProperty("location")
    private String location;

    @JsonProperty("revision")
    private String revision;

    @JsonProperty("short_revision")
    private String shortRevision;

    @JsonProperty("digest")
    private String digest;

    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    @JsonProperty("total_size")
    private Long totalSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getShortRevision() {
        return shortRevision;
    }

    public void setShortRevision(String shortRevision) {
        this.shortRevision = shortRevision;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
