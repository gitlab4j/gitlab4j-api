package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Badge {

    public enum BadgeKind {
        PROJECT, GROUP;

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

    private Integer id;
    private String linkUrl;
    private String imageUrl;
    private String renderedLinkUrl;
    private String renderedImageUrl;
    private BadgeKind kind;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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
