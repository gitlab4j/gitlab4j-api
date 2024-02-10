package org.gitlab4j.api.systemhooks;

import org.gitlab4j.api.webhook.MergeRequestEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MergeRequestSystemHookEvent extends MergeRequestEvent implements SystemHookEvent {
    private static final long serialVersionUID = 1L;

    public static final String X_GITLAB_EVENT = "System Hook";
    public static final String MERGE_REQUEST_EVENT = "merge_request";

    private String eventType;
    private String eventName;

    @Override
    public String getObjectKind() {
        return (MERGE_REQUEST_EVENT);
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @deprecated use {@link #setEventType(String)} instead
     * @param eventType
     */
    @Deprecated
    @JsonIgnore
    public void setEvent_type(String eventType) {
        setEventType(eventType);
    }
}
