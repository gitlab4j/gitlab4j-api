package org.gitlab4j.api.webhook;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkItemChanges extends EventChanges {
    @JsonProperty("health_status")
    private ChangeContainer<String> healthStatus;

    @JsonProperty("last_edited_at")
    private ChangeContainer<Date> lastEditedAt;

    public ChangeContainer<String> getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(ChangeContainer<String> healthStatus) {
        this.healthStatus = healthStatus;
    }

    public ChangeContainer<Date> getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(ChangeContainer<Date> lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
}
