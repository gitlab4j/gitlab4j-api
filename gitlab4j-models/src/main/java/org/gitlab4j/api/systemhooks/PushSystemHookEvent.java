package org.gitlab4j.api.systemhooks;

import org.gitlab4j.api.webhook.AbstractPushEvent;
import org.gitlab4j.models.utils.JacksonJson;

public class PushSystemHookEvent extends AbstractPushEvent implements SystemHookEvent {
    private static final long serialVersionUID = 1L;

    public static final String PUSH_EVENT = "push";

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
