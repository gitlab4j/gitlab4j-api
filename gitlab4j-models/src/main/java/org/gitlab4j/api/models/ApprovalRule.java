package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApprovalRule implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the approval rule.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the approval rule.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The type of the approval rule.
     */
    @JsonProperty("rule_type")
    private String ruleType;
    @JsonProperty("report_type")
    private String reportType;

    /**
     * The list of eligible approvers for the rule.
     */
    @JsonProperty("eligible_approvers")
    private List<User> eligibleApprovers;

    /**
     * The number of approvals required for the rule.
     */
    @JsonProperty("approvals_required")
    private Integer approvalsRequired;

    /**
     * The source rule from which this approval rule is derived.
     */
    @JsonProperty("source_rule")
    private ApprovalRule sourceRule;

    /**
     * The list of users associated with the approval rule.
     */
    @JsonProperty("users")
    private List<User> users;

    /**
     * The list of groups associated with the approval rule.
     */
    @JsonProperty("groups")
    private List<Group> groups;

    /**
     * Indicates whether the rule contains hidden groups.
     */
    @JsonProperty("contains_hidden_groups")
    private Boolean containsHiddenGroups;

    /**
     * The list of users who have approved the rule.
     */
    @JsonProperty("approved_by")
    private List<User> approvedBy;

    /**
     * Indicates whether the rule has been approved.
     */
    @JsonProperty("approved")
    private Boolean approved;

    /**
     * If true, applies the rule to all protected branches
     * and ignores the protected_branch_ids attribute.
     */
    @JsonProperty("applies_to_all_protected_branches")
    private Boolean appliesToAllProtectedBranches;

    @JsonProperty("protected_branches")
    private List<ProtectedBranch> protectedBranches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<User> getEligibleApprovers() {
        return eligibleApprovers;
    }

    public void setEligibleApprovers(List<User> eligibleApprovers) {
        this.eligibleApprovers = eligibleApprovers;
    }

    public Integer getApprovalsRequired() {
        return approvalsRequired;
    }

    public void setApprovalsRequired(Integer approvalsRequired) {
        this.approvalsRequired = approvalsRequired;
    }

    public ApprovalRule getSourceRule() {
        return sourceRule;
    }

    public void setSourceRule(ApprovalRule sourceRule) {
        this.sourceRule = sourceRule;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Boolean getContainsHiddenGroups() {
        return containsHiddenGroups;
    }

    public void setContainsHiddenGroups(Boolean containsHiddenGroups) {
        this.containsHiddenGroups = containsHiddenGroups;
    }

    public List<User> getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(List<User> approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getAppliesToAllProtectedBranches() {
        return appliesToAllProtectedBranches;
    }

    public void setAppliesToAllProtectedBranches(Boolean appliesToAllProtectedBranches) {
        this.appliesToAllProtectedBranches = appliesToAllProtectedBranches;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public List<ProtectedBranch> getProtectedBranches() {
        return protectedBranches;
    }

    public void setProtectedBranches(List<ProtectedBranch> protectedBranches) {
        this.protectedBranches = protectedBranches;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
