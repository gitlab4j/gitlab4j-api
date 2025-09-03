package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Epic extends AbstractEpic<Epic> {
    private static final long serialVersionUID = 1L;

    /**
     * Indicates if the start date of the epic is fixed.
     */
    @JsonProperty("start_date_is_fixed")
    private Boolean startDateIsFixed;

    /**
     * Indicates if the due date of the epic is fixed.
     */
    @JsonProperty("due_date_is_fixed")
    private Boolean dueDateIsFixed;

    /**
     * The due date inherited from a source, if applicable.
     */
    @JsonProperty("due_date_from_inherited_source")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date dueDateFromInheritedSource;

    /**
     * Indicates if the epic is subscribed.
     */
    @JsonProperty("subscribed")
    private Boolean subscribed;

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

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
