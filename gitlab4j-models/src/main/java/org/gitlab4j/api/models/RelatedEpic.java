package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedEpic extends AbstractEpic<RelatedEpic> {
    private static final long serialVersionUID = 1L;

    @JsonProperty("start_date_is_fixed")
    private Boolean startDateIsFixed;

    @JsonProperty("due_date_is_fixed")
    private Boolean dueDateIsFixed;

    @JsonProperty("due_date_from_inherited_source")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDateFromInheritedSource;

    @JsonProperty("related_epic_link_id")
    private Long relatedEpicLinkId;

    @JsonProperty("link_type")
    private LinkType linkType;

    @JsonProperty("link_created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date linkCreatedAt;

    @JsonProperty("link_updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date linkUpdatedAt;

    public Boolean getStartDateIsFixed() {
        return startDateIsFixed;
    }

    public void setStartDateIsFixed(Boolean startDateIsFixed) {
        this.startDateIsFixed = startDateIsFixed;
    }

    public Boolean getDueDateIsFixed() {
        return dueDateIsFixed;
    }

    public void setDueDateIsFixed(Boolean dueDateIsFixed) {
        this.dueDateIsFixed = dueDateIsFixed;
    }

    public Date getDueDateFromInheritedSource() {
        return dueDateFromInheritedSource;
    }

    public void setDueDateFromInheritedSource(Date dueDateFromInheritedSource) {
        this.dueDateFromInheritedSource = dueDateFromInheritedSource;
    }

    public Long getRelatedEpicLinkId() {
        return relatedEpicLinkId;
    }

    public void setRelatedEpicLinkId(Long relatedEpicLinkId) {
        this.relatedEpicLinkId = relatedEpicLinkId;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public Date getLinkCreatedAt() {
        return linkCreatedAt;
    }

    public void setLinkCreatedAt(Date linkCreatedAt) {
        this.linkCreatedAt = linkCreatedAt;
    }

    public Date getLinkUpdatedAt() {
        return linkUpdatedAt;
    }

    public void setLinkUpdatedAt(Date linkUpdatedAt) {
        this.linkUpdatedAt = linkUpdatedAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
