package org.gitlab4j.api.webhook;

import org.gitlab4j.api.models.User;
import org.gitlab4j.api.utils.JacksonJson;

public class ReleaseEvent extends AbstractEvent {

    public static final String JOB_HOOK_X_GITLAB_EVENT = "Release Hook";
    public static final String OBJECT_KIND = "release";

    private String action;
    private String url;
    private String name;
    private String description;
    private String tag;
    private String releasedAt;
    private EventCommit commit;

    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
