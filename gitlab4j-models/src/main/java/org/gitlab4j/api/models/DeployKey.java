package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class DeployKey implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the deploy key.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The title of the deploy key.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The actual deploy key.
     */
    @JsonProperty("key")
    private String key;

    /**
     * Indicates whether the deploy key has push permissions.
     */
    @JsonProperty("can_push")
    private Boolean canPush;

    /**
     * The creation date of the deploy key.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCanPush() {
        return canPush;
    }

    public void setCanPush(Boolean canPush) {
        this.canPush = canPush;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
