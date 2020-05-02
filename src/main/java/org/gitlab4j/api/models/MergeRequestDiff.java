package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.Constants.MergeRequestState;
import org.gitlab4j.api.utils.JacksonJson;

public class MergeRequestDiff {

    private Integer id;
    private String headCommitSha;
    private String baseCommitSha;
    private String startCommitSha;
    private Date createdAt;
    private Integer mergeRequestId;
    private MergeRequestState state;
    private String realSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadCommitSha() {
        return headCommitSha;
    }

    public void setHeadCommitSha(String headCommitSha) {
        this.headCommitSha = headCommitSha;
    }

    public String getBaseCommitSha() {
        return baseCommitSha;
    }

    public void setBaseCommitSha(String baseCommitSha) {
        this.baseCommitSha = baseCommitSha;
    }

    public String getStartCommitSha() {
        return startCommitSha;
    }

    public void setStartCommitSha(String startCommitSha) {
        this.startCommitSha = startCommitSha;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getMergeRequestId() {
        return mergeRequestId;
    }

    public void setMergeRequestId(Integer mergeRequestId) {
        this.mergeRequestId = mergeRequestId;
    }

    public MergeRequestState getState() {
        return state;
    }

    public void setState(MergeRequestState state) {
        this.state = state;
    }

    public String getRealSize() {
        return realSize;
    }

    public void setRealSize(String realSize) {
        this.realSize = realSize;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
