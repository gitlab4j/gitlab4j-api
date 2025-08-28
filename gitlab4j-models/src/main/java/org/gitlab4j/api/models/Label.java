package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Label implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("color")
    private String color;

    @JsonProperty("description")
    private String description;

    @JsonProperty("description_html")
    private String descriptionHtml;

    @JsonProperty("text_color")
    private String textColor;

    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;

    @JsonProperty("closed_issues_count")
    private Integer closedIssuesCount;

    @JsonProperty("open_merge_requests_count")
    private Integer openMergeRequestsCount;

    @JsonProperty("subscribed")
    private Boolean subscribed;

    @JsonProperty("priority")
    private Integer priority;

    @JsonProperty("is_project_label")
    private Boolean isProjectLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Label withName(String name) {
        this.name = name;
        return (this);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Label withColor(String color) {
        this.color = color;
        return (this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Label withDescription(String description) {
        this.description = description;
        return (this);
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public Integer getClosedIssuesCount() {
        return closedIssuesCount;
    }

    public void setClosedIssuesCount(Integer closedIssuesCount) {
        this.closedIssuesCount = closedIssuesCount;
    }

    public Integer getOpenMergeRequestsCount() {
        return openMergeRequestsCount;
    }

    public void setOpenMergeRequestsCount(Integer openMergeRequestsCount) {
        this.openMergeRequestsCount = openMergeRequestsCount;
    }

    public Boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Label withPriority(Integer priority) {
        this.priority = priority;
        return (this);
    }

    public Boolean getIsProjectLabel() {
        return isProjectLabel;
    }

    public void setIsProjectLabel(Boolean isProjectLabel) {
        this.isProjectLabel = isProjectLabel;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }

    /**
     * Get the form params specified by this instance.
     *
     * @param isCreate set to true if the params are for a create label call, false for an update
     * @return a GitLabApiForm instance holding the form parameters for this LabelParams instance
     */
    @JsonIgnore
    public GitLabForm getForm(boolean isCreate) {
        GitLabForm form = new GitLabForm()
                .withParam("description", description)
                .withParam("color", color, isCreate)
                .withParam("priority", priority);

        if (isCreate) {
            form.withParam("name", name, true);
        } else {
            form.withParam("new_name", name);
        }

        return (form);
    }
}
