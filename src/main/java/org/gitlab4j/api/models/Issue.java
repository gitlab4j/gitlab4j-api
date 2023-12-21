
package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Issue extends AbstractIssue {
    private static final long serialVersionUID = 1L;

    private Boolean subscribed;

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
