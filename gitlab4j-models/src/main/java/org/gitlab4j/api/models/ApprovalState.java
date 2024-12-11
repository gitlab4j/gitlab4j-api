package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApprovalState implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Indicates whether the approval rules have been overwritten.
     */
    @JsonProperty("approval_rules_overwritten")
    private Boolean approvalRulesOverwritten;

    /**
     * The list of approval rules associated with the approval state.
     */
    @JsonProperty("rules")
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
