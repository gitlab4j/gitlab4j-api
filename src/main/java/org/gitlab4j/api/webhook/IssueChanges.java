package org.gitlab4j.api.webhook;

import java.util.Date;

public class IssueChanges extends EventChanges {

    private ChangeContainer<Date> dueDate;
    private ChangeContainer<Boolean> confidential;
    private ChangeContainer<String> heathStatus;

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
        return heathStatus;
    }

    public void setHeathStatus(ChangeContainer<String> heathStatus) {
        this.heathStatus = heathStatus;
    }
}
