package org.gitlab4j.api.systemhooks;

import org.gitlab4j.api.webhook.MergeRequestEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="object_kind")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MergeRequestSystemHookEvent.class, name = MergeRequestSystemHookEvent.OBJECT_KIND),
})
public class MergeRequestSystemHookEvent extends MergeRequestEvent implements SystemHookEvent {

    public static final String X_GITLAB_EVENT = "System Hook";

    @JsonIgnore
    @Override
    public String getEventName() {
        return (OBJECT_KIND);
    }
}
