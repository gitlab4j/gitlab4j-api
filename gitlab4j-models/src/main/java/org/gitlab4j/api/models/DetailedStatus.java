package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is part of the Pipeline message.
 */
public class DetailedStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The icon associated with the detailed status.
     */
    @JsonProperty("icon")
    private String icon;

    /**
     * The text description of the detailed status.
     */
    @JsonProperty("text")
    private String text;

    /**
     * The label associated with the detailed status.
     */
    @JsonProperty("label")
    private String label;

    /**
     * The group associated with the detailed status.
     */
    @JsonProperty("group")
    private String group;

    /**
     * The tooltip description of the detailed status.
     */
    @JsonProperty("tooltip")
    private String tooltip;

    /**
     * Indicates if the detailed status has additional details.
     */
    @JsonProperty("has_details")
    private Boolean hasDetails;

    /**
     * The path to the additional details.
     */
    @JsonProperty("details_path")
    private String detailsPath;

    /**
     * The illustration associated with the detailed status.
     */
    @JsonProperty("illustration")
    private String illustration;

    /**
     * The favicon associated with the detailed status.
     */
    @JsonProperty("favicon")
    private String favicon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public Boolean getHasDetails() {
        return hasDetails;
    }

    public void setHasDetails(Boolean hasDetails) {
        this.hasDetails = hasDetails;
    }

    public String getDetailsPath() {
        return detailsPath;
    }

    public void setDetailsPath(String detailsPath) {
        this.detailsPath = detailsPath;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
