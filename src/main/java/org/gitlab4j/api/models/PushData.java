
package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.gitlab4j.api.Constants.ActionType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PushData {

    private Integer commit_count;
    private ActionType action;
    private String refType;
    private String commitFrom;
    private String commitTo;
    private String ref;

    public Integer getCommit_count() {
        return commit_count;
    }

    public void setCommit_count(Integer commit_count) {
        this.commit_count = commit_count;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getCommitFrom() {
        return commitFrom;
    }

    public void setCommitFrom(String commitFrom) {
        this.commitFrom = commitFrom;
    }

    public String getCommitTo() {
        return commitTo;
    }

    public void setCommitTo(String commitTo) {
        this.commitTo = commitTo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
