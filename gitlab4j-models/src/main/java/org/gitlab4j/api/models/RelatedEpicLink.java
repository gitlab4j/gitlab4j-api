package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedEpicLink implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("source_epic")
    private EpicInLink sourceEpic;

    @JsonProperty("target_epic")
    private EpicInLink targetEpic;

    @JsonProperty("link_type")
    private LinkType linkType;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
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
