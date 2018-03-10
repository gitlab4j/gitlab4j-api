
package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProtectedBranch {
    private String name;
    private List<BranchAccessLevelDetails> pushAccessLevels;
    private List<BranchAccessLevelDetails> mergeAccessLevels;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BranchAccessLevelDetails> getPushAccessLevels() {
        return this.pushAccessLevels;
    }

    public void setPushAccessLevels(List<BranchAccessLevelDetails> pushAccessLevels) {
        this.pushAccessLevels = pushAccessLevels;
    }

    public List<BranchAccessLevelDetails> getMergeAccessLevels() {
        return this.mergeAccessLevels;
    }

    public void setMergeAccessLevels(List<BranchAccessLevelDetails> mergeAccessLevels) {
        this.mergeAccessLevels = mergeAccessLevels;
    }

    public static final boolean isValid(ProtectedBranch branch) {
        return (branch != null && branch.getName() != null);
    }
}
