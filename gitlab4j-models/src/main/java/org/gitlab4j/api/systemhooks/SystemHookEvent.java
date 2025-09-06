package org.gitlab4j.api.systemhooks;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "event_name")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_CREATE_EVENT),
    @JsonSubTypes.Type(
            value = DestroyProjectSystemHookEvent.class,
            name = ProjectSystemHookEvent.PROJECT_DESTROY_EVENT),
    @JsonSubTypes.Type(value = RenameProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_RENAME_EVENT),
    @JsonSubTypes.Type(
            value = TransferProjectSystemHookEvent.class,
            name = ProjectSystemHookEvent.PROJECT_TRANSFER_EVENT),
    @JsonSubTypes.Type(value = UpdateProjectSystemHookEvent.class, name = ProjectSystemHookEvent.PROJECT_UPDATE_EVENT),
    @JsonSubTypes.Type(
            value = NewTeamMemberSystemHookEvent.class,
            name = TeamMemberSystemHookEvent.NEW_TEAM_MEMBER_EVENT),
    @JsonSubTypes.Type(
            value = RemoveTeamMemberSystemHookEvent.class,
            name = TeamMemberSystemHookEvent.TEAM_MEMBER_REMOVED_EVENT),
    @JsonSubTypes.Type(
            value = UpdateTeamMemberSystemHookEvent.class,
            name = TeamMemberSystemHookEvent.TEAM_MEMBER_UPDATED_EVENT),
    @JsonSubTypes.Type(value = CreateUserSystemHookEvent.class, name = UserSystemHookEvent.USER_CREATE_EVENT),
    @JsonSubTypes.Type(value = DestroyUserSystemHookEvent.class, name = UserSystemHookEvent.USER_DESTROY_EVENT),
    @JsonSubTypes.Type(
            value = UserFailedLoginSystemHookEvent.class,
            name = UserSystemHookEvent.USER_FAILED_LOGIN_EVENT),
    @JsonSubTypes.Type(value = RenameUserSystemHookEvent.class, name = UserSystemHookEvent.USER_RENAME_EVENT),
    @JsonSubTypes.Type(value = CreateKeySystemHookEvent.class, name = KeySystemHookEvent.KEY_CREATE_EVENT),
    @JsonSubTypes.Type(value = DestroyKeySystemHookEvent.class, name = KeySystemHookEvent.KEY_DESTROY_EVENT),
    @JsonSubTypes.Type(value = CreateGroupSystemHookEvent.class, name = GroupSystemHookEvent.GROUP_CREATE_EVENT),
    @JsonSubTypes.Type(value = DestroyGroupSystemHookEvent.class, name = GroupSystemHookEvent.GROUP_DESTROY_EVENT),
    @JsonSubTypes.Type(value = RenameGroupSystemHookEvent.class, name = GroupSystemHookEvent.GROUP_RENAME_EVENT),
    @JsonSubTypes.Type(
            value = NewGroupMemberSystemHookEvent.class,
            name = GroupMemberSystemHookEvent.NEW_GROUP_MEMBER_EVENT),
    @JsonSubTypes.Type(
            value = RemoveGroupMemberSystemHookEvent.class,
            name = GroupMemberSystemHookEvent.GROUP_MEMBER_REMOVED_EVENT),
    @JsonSubTypes.Type(
            value = UpdateGroupMemberSystemHookEvent.class,
            name = GroupMemberSystemHookEvent.GROUP_MEMBER_UPDATE_EVENT),
    @JsonSubTypes.Type(value = PushSystemHookEvent.class, name = PushSystemHookEvent.PUSH_EVENT),
    @JsonSubTypes.Type(value = TagPushSystemHookEvent.class, name = TagPushSystemHookEvent.TAG_PUSH_EVENT),
    @JsonSubTypes.Type(
            value = RepositorySystemHookEvent.class,
            name = RepositorySystemHookEvent.REPOSITORY_UPDATE_EVENT),
    @JsonSubTypes.Type(
            value = MergeRequestSystemHookEvent.class,
            name = MergeRequestSystemHookEvent.MERGE_REQUEST_EVENT)
})
public interface SystemHookEvent extends Serializable {

    String getEventName();

    void setRequestUrl(String requestUrl);

    @JsonIgnore
    String getRequestUrl();

    void setRequestQueryString(String requestQueryString);

    @JsonIgnore
    String getRequestQueryString();

    void setRequestSecretToken(String requestSecretToken);

    @JsonIgnore
    String getRequestSecretToken();
}

// All of the following class definitions are needed to make the above work.
// Jackson has a tough time mapping the same class to multiple IDs
class CreateProjectSystemHookEvent extends ProjectSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class DestroyProjectSystemHookEvent extends ProjectSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class RenameProjectSystemHookEvent extends ProjectSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class TransferProjectSystemHookEvent extends ProjectSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class UpdateProjectSystemHookEvent extends ProjectSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class NewTeamMemberSystemHookEvent extends TeamMemberSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class RemoveTeamMemberSystemHookEvent extends TeamMemberSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class UpdateTeamMemberSystemHookEvent extends TeamMemberSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class CreateUserSystemHookEvent extends UserSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class DestroyUserSystemHookEvent extends UserSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class RenameUserSystemHookEvent extends UserSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class UserFailedLoginSystemHookEvent extends UserSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class CreateKeySystemHookEvent extends KeySystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class DestroyKeySystemHookEvent extends KeySystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class CreateGroupSystemHookEvent extends GroupSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class DestroyGroupSystemHookEvent extends GroupSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class RenameGroupSystemHookEvent extends GroupSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class NewGroupMemberSystemHookEvent extends GroupMemberSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class RemoveGroupMemberSystemHookEvent extends GroupMemberSystemHookEvent {
    private static final long serialVersionUID = 1L;
}

class UpdateGroupMemberSystemHookEvent extends GroupMemberSystemHookEvent {
    private static final long serialVersionUID = 1L;
}
