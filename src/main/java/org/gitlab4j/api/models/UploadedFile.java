package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

public class UploadedFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long size;
    private String filename;
    private Date createdAt;
    private UploadedByUser uploadedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UploadedByUser getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(UploadedByUser uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
