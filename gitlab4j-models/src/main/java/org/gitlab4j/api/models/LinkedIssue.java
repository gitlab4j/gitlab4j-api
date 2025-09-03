package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class LinkedIssue extends AbstractIssue {
    private static final long serialVersionUID = 1L;

    @JsonProperty("issue_link_id")
    private Long issueLinkId;

    @JsonProperty("link_type")
    private LinkType linkType;

    @JsonProperty("link_created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date linkCreatedAt;

    @JsonProperty("link_updated_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date linkUpdatedAt;

    public Long getIssueLinkId() {
        return issueLinkId;
    }

    public void setIssueLinkId(Long issueLinkId) {
        this.issueLinkId = issueLinkId;
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
