package org.gitlab4j.api.webhook;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class EventLabel {

    public enum LabelType {
    
        PROJECT_LABEL;
    
        private static JacksonJsonEnumHelper<LabelType> enumHelper = new JacksonJsonEnumHelper<>(LabelType.class, true, true);
    
        @JsonCreator
        public static LabelType forValue(String value) {
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
    private String title;
    private String color;
    private Integer projectId;
    private Date createdAt;
    private Date updatedAt;
    private Boolean template;
    private String description;
    private LabelType type;
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Boolean getTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LabelType getType() {
        return type;
    }

    public void setType(LabelType type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
