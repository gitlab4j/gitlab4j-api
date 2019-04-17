package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class Epic {
    
    private Integer id;
    private Integer iid;
    private Integer groupId;
    private String title;
    private String description;
    private Author author;
    private List<String> labels;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Epic withTitle(String title) {
        this.title = title;
        return (this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Epic withDescription(String description) {
        this.description = description;
        return (this);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Epic withAuthor(Author author) {
        this.author = author;
        return (this);
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Epic withLabels(List<String> labels) {
        this.labels = labels;
        return (this);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Epic withStartDate(Date startDate) {
        this.startDate = startDate;
        return (this);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Epic withEndDate(Date endDate) {
        this.endDate = endDate;
        return (this);
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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
