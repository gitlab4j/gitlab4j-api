package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractMinimalEpic<E extends AbstractMinimalEpic<E>> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the epic.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The internal identifier of the epic.
     */
    @JsonProperty("iid")
    private Long iid;

    /**
     * The ID of the group associated with the epic.
     */
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * The ID of the parent epic.
     */
    @JsonProperty("parent_id")
    private Long parentId;

    /**
     * The title of the epic.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The reference of the epic.
     */
    @JsonProperty("reference")
    private String reference;

    /**
     * The URL of the epic.
     */
    @JsonProperty("url")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SuppressWarnings("unchecked")
    public E withTitle(String title) {
        this.title = title;
        return (E) (this);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
