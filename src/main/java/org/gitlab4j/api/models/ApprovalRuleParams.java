package org.gitlab4j.api.models;

import java.util.List;

import org.gitlab4j.api.GitLabApiForm;

public class ApprovalRuleParams {

	private Integer approvalsRequired;
    private String name;
    private Boolean appliesToAllProtectedBranches;
    private List<Long> groupIds;
    private List<Long> protectedBranchIds;
    private String reportType;
    private String ruleType;
    private List<Long> userIds;
    private List<String> usernames;

    /**
     * @param approvalsRequired The number of required approvals for this rule.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withApprovalsRequired(Integer approvalsRequired) {
        this.approvalsRequired = approvalsRequired;
        return (this);
    }

    /**
     * @param name The name of the approval rule.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withName(String name) {
        this.name = name;
        return (this);
    }

    /**
     * @param appliesToAllProtectedBranches Whether the rule is applied to all protected branches. If set to true, the value of protected_branch_ids is ignored. Default is false. Introduced in GitLab 15.3.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withAppliesToAllProtectedBranches(Boolean appliesToAllProtectedBranches) {
        this.appliesToAllProtectedBranches = appliesToAllProtectedBranches;
        return (this);
    }

    /**
     * @param groupIds The IDs of groups as approvers.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
        return (this);
    }

    /**
     * @param protectedBranchIds The IDs of protected branches to scope the rule by. To identify the ID, use the API.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withProtectedBranchIds(List<Long> protectedBranchIds) {
        this.protectedBranchIds = protectedBranchIds;
        return (this);
    }

    /**
     * @param reportType The report type required when the rule type is report_approver. The supported report types are license_scanning (Deprecated in GitLab 15.9) and code_coverage.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withReportType(String reportType) {
        this.reportType = reportType;
        return (this);
    }

    /**
     * @param ruleType The type of rule. any_approver is a pre-configured default rule with approvals_required at 0. Other rules are regular and report_approver.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withRuleType(String ruleType) {
        this.ruleType = ruleType;
        return (this);
    }

    /**
     * @param userIds The IDs of users as approvers. If you provide both user_ids and usernames, both lists of users are added.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withUserIds(List<Long> userIds) {
        this.userIds = userIds;
        return (this);
    }

    /**
     * @param usernames The usernames of approvers for this rule (same as user_ids but requires a list of usernames). If you provide both user_ids and usernames, both lists of users are added.
     * @return this ApprovalRuleParams instance
     */
    public ApprovalRuleParams withUsernames(List<String> usernames) {
        this.usernames = usernames;
        return (this);
    }

    /**
     * Get the form params specified by this instance.
     *
     * @return a GitLabApiForm instance holding the form parameters for this ApprovalRuleParams instance
     */
    public GitLabApiForm getForm() {
        return new GitLabApiForm()
            .withParam("approvals_required", approvalsRequired, true)
            .withParam("name", name, true)
            .withParam("applies_to_all_protected_branches", appliesToAllProtectedBranches)
            .withParam("group_ids", groupIds)
            .withParam("protected_branch_ids", protectedBranchIds)
            .withParam("report_type", reportType)
            .withParam("rule_type", ruleType)
            .withParam("user_ids", userIds)
            .withParam("usernames", usernames);
    }
}
