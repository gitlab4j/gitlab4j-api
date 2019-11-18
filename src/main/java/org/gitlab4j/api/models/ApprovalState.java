package org.gitlab4j.api.models;

import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class ApprovalState {

    private Boolean approvalRulesOverwritten;
    private List<ApprovalRule> rules;

    public Boolean getApprovalRulesOverwritten() {
	return approvalRulesOverwritten;
    }

    public void setApprovalRulesOverwritten(Boolean approvalRulesOverwritten) {
	this.approvalRulesOverwritten = approvalRulesOverwritten;
    }

    public List<ApprovalRule> getRules() {
	return rules;
    }

    public void setRules(List<ApprovalRule> rules) {
	this.rules = rules;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
