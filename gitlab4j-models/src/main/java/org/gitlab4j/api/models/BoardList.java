package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoardList implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the board list.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The label associated with the board list.
     */
    @JsonProperty("label")
    private Label label;

    /**
     * The position of the board list.
     */
    @JsonProperty("position")
    private Integer position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
