package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkedIssue extends AbstractIssue {

    @JsonProperty("issue_link_id")
    private Long issueLinkId;

    @JsonProperty("link_type")
    private LinkType linkType;

    @JsonProperty("link_created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date linkCreatedAt;

    @JsonProperty("link_updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
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
