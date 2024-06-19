package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

public class RelatedEpicLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private EpicInLink sourceEpic;
    private EpicInLink targetEpic;
    private LinkType linkType;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EpicInLink getSourceEpic() {
        return sourceEpic;
    }

    public void setSourceEpic(EpicInLink sourceEpic) {
        this.sourceEpic = sourceEpic;
    }

    public EpicInLink getTargetEpic() {
        return targetEpic;
    }

    public void setTargetEpic(EpicInLink targetEpic) {
        this.targetEpic = targetEpic;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
