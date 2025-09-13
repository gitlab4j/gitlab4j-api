package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class TestReportStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long time;
    private Long count;
    private Long success;
    private Long failed;
    private Long skipped;
    private Long error;
    private String suiteError;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }

    public Long getFailed() {
        return failed;
    }

    public void setFailed(Long failed) {
        this.failed = failed;
    }

    public Long getSkipped() {
        return skipped;
    }

    public void setSkipped(Long skipped) {
        this.skipped = skipped;
    }

    public Long getError() {
        return error;
    }

    public void setError(Long error) {
        this.error = error;
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
