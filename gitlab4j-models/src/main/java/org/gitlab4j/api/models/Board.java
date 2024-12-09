package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Boolean hideBacklogList;
    private Boolean hideClosedList;
    private Project project;
    private List<BoardList> lists;
    private Group group;
    private Milestone milestone;
    private Assignee assignee;
    private List<Label> labels;
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
