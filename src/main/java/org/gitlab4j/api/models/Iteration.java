package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;
import java.util.Date;

public class Iteration implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum IterationState {
        UPCOMMING(1), CURRENT(2), CLOSED(3);

       private int value;

        IterationState(int value) {
            this.value = value;
        }

       @JsonCreator
       public static IterationState fromIntValue(int value) {
            for (IterationState it : values()) {
                if(it.value == value) {
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

    private Long id;
    private Long iid;
    private Long sequence;
    private Long groupId;
    private String title;
    private String description;
    private IterationState state;
    private Date createdAt;
    private Date updatedAt;
    private Date startDate;
    private Date dueDate;
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
