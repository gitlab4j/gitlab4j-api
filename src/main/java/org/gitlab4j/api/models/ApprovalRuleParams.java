package org.gitlab4j.api.models;

import java.util.List;

import org.gitlab4j.api.GitLabApiForm;

public class ApprovalRuleParams {

    private String name;
    private Integer approvalsRequired;
    private List<Integer> userIds;
    private List<Integer> groupIds;

    public ApprovalRuleParams withName(String name) {
	this.name = name;
	return (this);
    }

    public ApprovalRuleParams withApprovalsRequired(Integer approvalsRequired) {
	this.approvalsRequired = approvalsRequired;
	return (this);
    }

    public ApprovalRuleParams withUserIds(List<Integer> userIds) {
	this.userIds = userIds;
	return (this);
    }

    public ApprovalRuleParams withGroupIds(List<Integer> groupIds) {
	this.groupIds = groupIds;
	return (this);
    }

    /**
     * Get the form params specified by this instance.
     *
     * @return a GitLabApiForm instance holding the form parameters for this ApprovalRuleParams instance
     */
    public GitLabApiForm getForm() {
	return new GitLabApiForm()
            .withParam("name", name)
            .withParam("approvals_required", approvalsRequired, true)
            .withParam("user_ids", userIds)
            .withParam("group_ids", groupIds);
    }
}
