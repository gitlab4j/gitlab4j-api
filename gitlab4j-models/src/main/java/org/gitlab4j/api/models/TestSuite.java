package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

public class TestSuite implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long totalTime;
    private Long totalCount;
    private Long successCount;
    private Long failedCount;
    private Long skippedCount;
    private Long errorCount;
    private List<Long> buildIds;
    private String suiteError;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    public Long getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Long failedCount) {
        this.failedCount = failedCount;
    }

    public Long getSkippedCount() {
        return skippedCount;
    }

    public void setSkippedCount(Long skippedCount) {
        this.skippedCount = skippedCount;
    }

    public Long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Long errorCount) {
        this.errorCount = errorCount;
    }

    public List<Long> getBuildIds() {
        return buildIds;
    }

    public void setBuildIds(List<Long> buildIds) {
        this.buildIds = buildIds;
    }

    public String getSuiteError() {
        return suiteError;
    }

    public void setSuiteError(String suiteError) {
        this.suiteError = suiteError;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
