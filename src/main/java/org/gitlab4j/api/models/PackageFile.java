package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PackageFile {

    private Long id;
    private Long packageId;
    private Date createdAt;
    private String fileName;
    private Long size;
    private String fileMd5;
    private String fileSha1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @deprecated Replaced by {@link #getCreatedAt()}
     * @return the created at Date
     */
    @Deprecated
    @JsonIgnore
    public Date getCreated_at() {
        return createdAt;
    }

    /**
     * @deprecated Replaced by {@link #setCreatedAt(Date)}
     * @param createdAt new created at value
     */
    @Deprecated
    @JsonIgnore
    public void setCreated_at(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFileSha1() {
        return fileSha1;
    }

    public void setFileSha1(String fileSha1) {
        this.fileSha1 = fileSha1;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
