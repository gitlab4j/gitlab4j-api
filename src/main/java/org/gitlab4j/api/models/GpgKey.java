package org.gitlab4j.api.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GpgKey {

    private Long id;
    private String key;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
    }}
