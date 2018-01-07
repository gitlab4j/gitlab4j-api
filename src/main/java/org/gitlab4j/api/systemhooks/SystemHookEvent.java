package org.gitlab4j.api.systemhooks;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
    include=JsonTypeInfo.As.PROPERTY,
    property="event_name")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_CREATE_EVENT),
    @JsonSubTypes.Type(value = ProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_DESTROY_EVENT),
    @JsonSubTypes.Type(value = ProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_RENAME_EVENT),
    @JsonSubTypes.Type(value = ProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_TRANSFER_EVENT),
    @JsonSubTypes.Type(value = ProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_UPDATE_EVENT),
    @JsonSubTypes.Type(value = TeamMemberSystemHookEvent.class, name = TeamMemberSystemHookEvent.NEW_TEAM_MEMBER_EVENT),
    @JsonSubTypes.Type(value = TeamMemberSystemHookEvent.class, name = TeamMemberSystemHookEvent.TEAM_MEMBER_REMOVED_EVENT)
})
public interface SystemHookEvent {
    public String getEventName();
}
