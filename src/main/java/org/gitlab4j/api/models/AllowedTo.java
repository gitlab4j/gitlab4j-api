package org.gitlab4j.api.models;

import org.gitlab4j.api.GitLabApiForm;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by the ProtectedBranchesAPi to set up the
 * allowed_to_push, allowed_to_merge, and allowed_to_unprotect values.
 */
public class AllowedTo {

	private List<AccessLevel> accessLevels;
	private List<Integer> userIds;
	private List<Integer> groupIds;

    private AccessLevel accessLevel;
    private Integer userId;
    private Integer groupId;

    public AllowedTo(List<AccessLevel> accessLevels,
					 List<Integer> userIds,
					 List<Integer> groupIds) {
		this.accessLevels = accessLevels;
		this.userIds = userIds;
		this.groupIds = groupIds;
	}

    public AllowedTo(AccessLevel accessLevel, Integer userId, Integer groupId) {
    	this();
		this.accessLevel = accessLevel;
		this.userId = userId;
		this.groupId = groupId;

		this.accessLevels.add(accessLevel);
		this.userIds.add(userId);
		this.groupIds.add(groupId);
    }

    private AllowedTo() {
    	accessLevels = new ArrayList<AccessLevel>();
    	userIds = new ArrayList<Integer>();
    	groupIds = new ArrayList<Integer>();
	}

    public AllowedTo withAccessLevel(AccessLevel accessLevel) {
	this.accessLevel = accessLevel;
	return (this);
    }

    public AllowedTo withUserId(Integer userId) {
	this.userId = userId;
	return (this);
    }

    public AllowedTo withGroupId(Integer groupId) {
	this.groupId = groupId;
	return (this);
    }

	public AllowedTo withAccessLevels(List<AccessLevel> accessLevels) {
		this.accessLevels = accessLevels;
		return (this);
	}

	public AllowedTo withUserIds(List<Integer> userIds) {
		this.userIds = userIds;
		return (this);
	}

	public AllowedTo withGroupIds(List<Integer> groupIds) {
		this.groupIds = groupIds;
		return (this);
	}

    public GitLabApiForm getForm(GitLabApiForm form, String allowedToName) {

		if (form == null) {
			form = new GitLabApiForm();
		}

		for (AccessLevel accessLevel : accessLevels) {
			form.withParam(allowedToName + "[][access_level]", accessLevel.toValue());
		}
		for (Integer userId : userIds) {
			form.withParam(allowedToName + "[][user_id]", userId);
		}
		for (Integer groupId : groupIds) {
			form.withParam(allowedToName + "[][group_id]", groupId);
		}
		return form;
	}
}
