package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AbstractEpic<E extends AbstractEpic<E>> extends AbstractMinimalEpic<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum EpicState {
        OPENED,
        CLOSED,
        ALL;

        private static JacksonJsonEnumHelper<EpicState> enumHelper = new JacksonJsonEnumHelper<>(EpicState.class);

        @JsonCreator
        public static EpicState forValue(String value) {
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

    /**
     * The internal identifier of the parent epic.
     */
    @JsonProperty("parent_iid")
    private Long parentIid;

    /**
     * The description of the epic.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The state of the epic (e.g., open, closed).
     */
    @JsonProperty("state")
    private EpicState state;

    /**
     * The web URL of the epic.
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * The references associated with the epic.
     */
    @JsonProperty("references")
    private References references;

    /**
     * The author of the epic.
     */
    @JsonProperty("author")
    private Author author;

    /**
     * The list of labels associated with the epic.
     */
    @JsonProperty("labels")
    private List<String> labels;

    /**
     * The start date of the epic.
     */
    @JsonProperty("start_date")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date startDate;

    /**
     * The due date of the epic.
     */
    @JsonProperty("due_date")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date dueDate;

    /**
     * The end date of the epic.
     */
    @JsonProperty("end_date")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private Date endDate;

    /**
     * The date when the epic was created.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * The date when the epic was last updated.
     */
    @JsonProperty("updated_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date updatedAt;

    /**
     * The date when the epic was closed.
     */
    @JsonProperty("closed_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date closedAt;

    /**
     * The number of downvotes for the epic.
     */
    @JsonProperty("downvotes")
    private Integer downvotes;

    /**
     * The number of upvotes for the epic.
     */
    @JsonProperty("upvotes")
    private Integer upvotes;

    /**
     * The color associated with the epic.
     */
    @JsonProperty("color")
    private String color;

    /**
     * The links associated with the epic.
     */
    @JsonProperty("_links")
    private Map<String, String> links;

    public Long getParentIid() {
        return parentIid;
    }

    public void setParentIid(Long parentIid) {
        this.parentIid = parentIid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unchecked")
    public E withDescription(String description) {
        this.description = description;
        return (E) (this);
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

    @SuppressWarnings("unchecked")
    public E withAuthor(Author author) {
        this.author = author;
        return (E) (this);
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @SuppressWarnings("unchecked")
    public E withLabels(List<String> labels) {
        this.labels = labels;
        return (E) (this);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @SuppressWarnings("unchecked")
    public E withStartDate(Date startDate) {
        this.startDate = startDate;
        return (E) (this);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @SuppressWarnings("unchecked")
    public E withEndDate(Date endDate) {
        this.endDate = endDate;
        return (E) (this);
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

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
