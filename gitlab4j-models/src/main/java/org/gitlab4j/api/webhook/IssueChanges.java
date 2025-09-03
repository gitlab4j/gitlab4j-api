package org.gitlab4j.api.webhook;

import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IssueChanges extends EventChanges {

    @JsonProperty("due_date")
    @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
    private ChangeContainer<Date> dueDate;

    @JsonProperty("confidential")
    private ChangeContainer<Boolean> confidential;

    @JsonProperty("health_status")
    private ChangeContainer<String> healthStatus;

    public ChangeContainer<Date> getDueDate() {
        return dueDate;
    }

    public void setDueDate(ChangeContainer<Date> dueDate) {
        this.dueDate = dueDate;
    }

    public ChangeContainer<Boolean> getConfidential() {
        return confidential;
    }

    public void setConfidential(ChangeContainer<Boolean> confidential) {
        this.confidential = confidential;
    }

    public ChangeContainer<String> getHeathStatus() {
        return healthStatus;
    }

    public void setHeathStatus(ChangeContainer<String> healthStatus) {
        this.healthStatus = healthStatus;
    }
}
