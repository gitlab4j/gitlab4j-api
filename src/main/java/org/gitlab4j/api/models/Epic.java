package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.utils.JacksonJsonEnumHelper;
import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Epic {

   public enum EpicState {
        OPENED, CLOSED, ALL;

       private static JacksonJsonEnumHelper<EpicState> enumHelper = new JacksonJsonEnumHelper<>(EpicState.class);

       @JsonCreator
       public static EpicState forValue(String value) {
           return enumHelper.forValue(value);
       }

       @JsonValue
       public String toValue() {
           return (enumHelper.toString(this));
       }

          public String toString() {
           return (enumHelper.toString(this));
       }
    }

    private Long id;
    private Long iid;
    private Long groupId;
    private Long parentId;
    private Long parentIid;
    private String title;
    private String description;
    private EpicState state;
    private String webUrl;
    private String reference;
    private References references;
    private Author author;
    private List<String> labels;
    private Date startDate;
    private Boolean startDateIsFixed;
    private Date dueDate;
    private Boolean dueDateIsFixed;
    private Date dueDateFromInheritedSource;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;
    private Date closedAt;
    private Integer downvotes;
    private Integer upvotes;
    private String color;
    private Boolean subscribed;
    @JsonProperty("_links")
    private Map<String, String> links;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentIid() {
        return parentIid;
    }

    public void setParentIid(Long parentIid) {
        this.parentIid = parentIid;
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

    public EpicState getState() {
	    return state;
    }

    public void setState(EpicState state) {
	    this.state = state;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
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

    public Boolean getStartDateIsFixed() {
        return startDateIsFixed;
    }

    public void setStartDateIsFixed(Boolean startDateIsFixed) {
        this.startDateIsFixed = startDateIsFixed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

	public Integer getDownvotes() {
        return downvotes;
    }

	public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

	public Integer getUpvotes() {
        return upvotes;
    }

	public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

	public Boolean getSubscribed() {
        return subscribed;
    }

	public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @JsonIgnore
    public String getLinkByName(String name) {
        if (links == null || links.isEmpty()) {
            return (null);
        }

        return (links.get(name));
    }

    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
