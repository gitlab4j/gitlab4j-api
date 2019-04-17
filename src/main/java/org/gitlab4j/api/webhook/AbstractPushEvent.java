package org.gitlab4j.api.webhook;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractPushEvent {

    private String eventName;

    private String after;
    private String before;
    private String ref;
    private String checkoutSha;

    private Integer userId;
    private String userName;
    private String userEmail;
    private String userAvatar;

    private Integer projectId;
    private EventProject project;
    private EventRepository repository;
    private List<EventCommit> commits;
    private Integer totalCommitsCount;

    private String requestUrl;
    private String requestQuesryString;

    public String getEventName() {
        return (eventName);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getAfter() {
        return this.after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return this.before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCheckoutSha() {
        return checkoutSha;
    }

    public void setCheckoutSha(String checkoutSha) {
        this.checkoutSha = checkoutSha;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public EventRepository getRepository() {
        return this.repository;
    }

    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    public List<EventCommit> getCommits() {
        return this.commits;
    }

    public void setCommits(List<EventCommit> commits) {
        this.commits = commits;
    }

    public Integer getTotalCommitsCount() {
        return this.totalCommitsCount;
    }

    public void setTotalCommitsCount(Integer totalCommitsCount) {
        this.totalCommitsCount = totalCommitsCount;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @JsonIgnore
    public String getRequestUrl() {
        return (requestUrl);
    }

    public void setRequestQueryString(String requestQuesryString) {
        this.requestQuesryString = requestQuesryString;
    }

    @JsonIgnore
    public String getRequestQueryString() {
        return (requestQuesryString);
    }

    /**
     * Gets the branch name from the ref. Will return null if the ref does not start with "refs/heads/".
     * 
     * @return the branch name from the ref
     */
    @JsonIgnore
    public String getBranch() {

        String ref = getRef();
        if (ref == null || ref.trim().length() == 0) {
            return (null);
        }

        ref = ref.trim();
        int refsHeadsIndex = ref.indexOf(REFS_HEADS);
        if (refsHeadsIndex != 0) {
            return (null);
        }

        return (ref.substring(REFS_HEADS.length()));
    }

    private static final String REFS_HEADS = "refs/heads/";
}