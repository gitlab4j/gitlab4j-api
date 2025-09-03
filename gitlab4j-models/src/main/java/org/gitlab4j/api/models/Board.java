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
     * Hide the Open list.
     */
    @JsonProperty("hide_backlog_list")
    private Boolean hideBacklogList;

    /**
     * Hide the Closed list.
     */
    @JsonProperty("hide_closed_list")
    private Boolean hideClosedList;

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

    /**
     * The group associated with the board.
     */
    @JsonProperty("group")
    private Group group;

    /**
     * The assignee associated with the board.
     */
    @JsonProperty("assignee")
    private Assignee assignee;

    /**
     * The labels associated with the board.
     */
    @JsonProperty("labels")
    private List<Label> labels;

    /**
     * The weight range from 0 to 9, to which the board should be scoped to.
     */
    @JsonProperty("weight")
    private Integer weight;

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

    public Boolean getHideBacklogList() {
        return hideBacklogList;
    }

    public void setHideBacklogList(Boolean hideBacklogList) {
        this.hideBacklogList = hideBacklogList;
    }

    public Boolean getHideClosedList() {
        return hideClosedList;
    }

    public void setHideClosedList(Boolean hideClosedList) {
        this.hideClosedList = hideClosedList;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
