package org.gitlab4j.api.webhook;

import java.util.Date;

import org.gitlab4j.models.utils.DateChangeContainerDeserializer;
import org.gitlab4j.models.utils.DateWithTimeChangeContainerSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WorkItemChanges extends EventChanges {
    @JsonProperty("health_status")
    private ChangeContainer<String> healthStatus;

    @JsonProperty("last_edited_at")
    @JsonDeserialize(using = DateChangeContainerDeserializer.class)
    @JsonSerialize(using = DateWithTimeChangeContainerSerializer.class)
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
