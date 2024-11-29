package org.gitlab4j.api.webhook;

import java.util.Date;

public class WorkItemChanges extends EventChanges {
    private ChangeContainer<String> heathStatus;
    private ChangeContainer<Date> lastEditedAt;

    public ChangeContainer<String> getHeathStatus() {
        return heathStatus;
    }

    public void setHeathStatus(ChangeContainer<String> heathStatus) {
        this.heathStatus = heathStatus;
    }

    public ChangeContainer<Date> getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(ChangeContainer<Date> lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
}
