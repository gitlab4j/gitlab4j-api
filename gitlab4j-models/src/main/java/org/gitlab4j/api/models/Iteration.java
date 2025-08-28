package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Iteration implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum IterationState {
        UPCOMMING(1),
        CURRENT(2),
        CLOSED(3);

        private int value;

        IterationState(int value) {
            this.value = value;
        }

        @JsonCreator
        public static IterationState fromIntValue(int value) {
            for (IterationState it : values()) {
                if (it.value == value) {
                    return it;
                }
            }
            throw new IllegalArgumentException("No enum found for value: " + value);
        }

        @JsonValue
        public int toIntValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return name();
        }
    }

    /**
     * The unique identifier of the iteration.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The internal identifier of the iteration.
     */
    @JsonProperty("iid")
    private Long iid;

    /**
     * The sequence number of the iteration.
     */
    @JsonProperty("sequence")
    private Long sequence;

    /**
     * The group ID associated with the iteration.
     */
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * The title of the iteration.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The description of the iteration.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The current state of the iteration.
     */
    @JsonProperty("state")
    private IterationState state;

    /**
     * The creation date of the iteration.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    /**
     * The last updated date of the iteration.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date updatedAt;

    /**
     * The start date of the iteration.
     * Expected in format "yyyy-MM-dd".
     */
    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * The due date of the iteration.
     * Expected in format "yyyy-MM-dd".
     */
    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    /**
     * The web URL for the iteration.
     */
    @JsonProperty("web_url")
    private String webUrl;

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

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IterationState getState() {
        return state;
    }

    public void setState(IterationState state) {
        this.state = state;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
