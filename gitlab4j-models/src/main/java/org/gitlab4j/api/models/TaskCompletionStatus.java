package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskCompletionStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("completed_count")
    private Integer completedCount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Integer completedCount) {
        this.completedCount = completedCount;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
