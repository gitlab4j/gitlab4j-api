package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Badge implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum BadgeKind {
        PROJECT,
        GROUP;

        private static JacksonJsonEnumHelper<BadgeKind> enumHelper = new JacksonJsonEnumHelper<>(BadgeKind.class);

        @JsonCreator
        public static BadgeKind forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /**
     * The unique identifier of the badge.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the badge.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The URL link associated with the badge.
     */
    @JsonProperty("link_url")
    private String linkUrl;

    /**
     * The image URL associated with the badge.
     */
    @JsonProperty("image_url")
    private String imageUrl;

    /**
     * The rendered URL link associated with the badge.
     */
    @JsonProperty("rendered_link_url")
    private String renderedLinkUrl;

    /**
     * The rendered image URL associated with the badge.
     */
    @JsonProperty("rendered_image_url")
    private String renderedImageUrl;

    /**
     * The kind of the badge (e.g., success, failure).
     */
    @JsonProperty("kind")
    private BadgeKind kind;

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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRenderedImageUrl() {
        return renderedImageUrl;
    }

    public void setRenderedImageUrl(String renderedImageUrl) {
        this.renderedImageUrl = renderedImageUrl;
    }

    public String getRenderedLinkUrl() {
        return renderedLinkUrl;
    }

    public void setRenderedLinkUrl(String renderedLinkUrl) {
        this.renderedLinkUrl = renderedLinkUrl;
    }

    public BadgeKind getKind() {
        return kind;
    }

    public void setKind(BadgeKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
