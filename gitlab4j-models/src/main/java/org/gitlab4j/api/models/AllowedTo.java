package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used by the ProtectedBranchesAPi to set up the
 * allowed_to_push, allowed_to_merge, and allowed_to_unprotect values.
 */
public class AllowedTo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The access level associated with the user or group.
     */
    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    /**
     * The ID of the user associated with the access.
     */
    @JsonProperty("user_id")
    private Long userId;

    /**
     * The ID of the group associated with the access.
     */
    @JsonProperty("group_id")
    private Long groupId;

    public AllowedTo(AccessLevel accessLevel, Long userId, Long groupId) {
        this.accessLevel = accessLevel;
        this.userId = userId;
        this.groupId = groupId;
    }

    public AllowedTo withAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return (this);
    }

    public AllowedTo withUserId(Long userId) {
        this.userId = userId;
        return (this);
    }

    public AllowedTo withGroupId(Long groupId) {
        this.groupId = groupId;
        return (this);
    }

    public GitLabForm getForm(GitLabForm form, String allowedToName) {

        if (form == null) {
            form = new GitLabForm();
        }

        return (form.withParam(allowedToName + "[][access_level]", accessLevel)
                .withParam(allowedToName + "[][user_id]", userId)
                .withParam(allowedToName + "[][group_id]", groupId));
    }
}
