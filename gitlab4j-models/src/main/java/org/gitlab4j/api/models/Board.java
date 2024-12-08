package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the board.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the board.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The project associated with the board.
     */
    @JsonProperty("project")
    private Project project;

    /**
     * The milestone associated with the board.
     */
    @JsonProperty("milestone")
    private Milestone milestone;

    /**
     * The list of board lists associated with the board.
     */
    @JsonProperty("lists")
    private List<BoardList> lists;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public List<BoardList> getLists() {
        return lists;
    }

    public void setLists(List<BoardList> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
