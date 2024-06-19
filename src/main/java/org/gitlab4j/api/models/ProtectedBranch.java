
package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.api.utils.JacksonJson;

public class ProtectedBranch implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<BranchAccessLevel> pushAccessLevels;
    private List<BranchAccessLevel> mergeAccessLevels;
    private List<BranchAccessLevel> unprotectAccessLevels;
    private Boolean codeOwnerApprovalRequired;
    private Boolean allowForcePush;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BranchAccessLevel> getPushAccessLevels() {
        return this.pushAccessLevels;
    }

    public void setPushAccessLevels(List<BranchAccessLevel> pushAccessLevels) {
        this.pushAccessLevels = pushAccessLevels;
    }

    public List<BranchAccessLevel> getMergeAccessLevels() {
        return this.mergeAccessLevels;
    }

    public void setMergeAccessLevels(List<BranchAccessLevel> mergeAccessLevels) {
        this.mergeAccessLevels = mergeAccessLevels;
    }

    public List<BranchAccessLevel> getUnprotectAccessLevels() {
        return unprotectAccessLevels;
    }

    public void setUnprotectAccessLevels(List<BranchAccessLevel> unprotectAccessLevels) {
        this.unprotectAccessLevels = unprotectAccessLevels;
    }

    public static final boolean isValid(ProtectedBranch branch) {
        return (branch != null && branch.getName() != null);
    }

    public ProtectedBranch withName(String name) {
        this.name = name;
        return this;
    }

    public ProtectedBranch withPushAccessLevels(List<BranchAccessLevel> pushAccessLevels) {
        this.pushAccessLevels = pushAccessLevels;
        return this;
    }

    public ProtectedBranch withMergeAccessLevels(List<BranchAccessLevel> mergeAccessLevels) {
        this.mergeAccessLevels = mergeAccessLevels;
        return this;
    }

    public Boolean getCodeOwnerApprovalRequired() {
        return codeOwnerApprovalRequired;
    }

    public void setCodeOwnerApprovalRequired(Boolean codeOwnerApprovalRequired) {
        this.codeOwnerApprovalRequired = codeOwnerApprovalRequired;
    }

    public ProtectedBranch withCodeOwnerApprovalRequired(Boolean codeOwnerApprovalRequired) {
        this.codeOwnerApprovalRequired = codeOwnerApprovalRequired;
        return this;
    }

    public Boolean getAllowForcePush() {
        return allowForcePush;
    }

    public void setAllowForcePush(Boolean allowForcePush) {
        this.allowForcePush = allowForcePush;
    }

    public ProtectedBranch withAllowForcePush(Boolean allowForcePush) {
        this.allowForcePush = allowForcePush;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
