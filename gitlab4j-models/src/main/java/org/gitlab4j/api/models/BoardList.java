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
    private Assignee assignee;
    private Milestone milestone;
    private Iteration iteration;
    private Integer maxIssueCount;
    private Integer maxIssueWeight;
    private Integer limitMetric;
    private String listType;

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

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }

    public Integer getMaxIssueCount() {
        return maxIssueCount;
    }

    public void setMaxIssueCount(Integer maxIssueCount) {
        this.maxIssueCount = maxIssueCount;
    }

    public Integer getMaxIssueWeight() {
        return maxIssueWeight;
    }

    public void setMaxIssueWeight(Integer maxIssueWeight) {
        this.maxIssueWeight = maxIssueWeight;
    }

    public Integer getLimitMetric() {
        return limitMetric;
    }

    public void setLimitMetric(Integer limitMetric) {
        this.limitMetric = limitMetric;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
