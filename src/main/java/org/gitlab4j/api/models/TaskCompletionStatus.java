package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class TaskCompletionStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer count;
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
