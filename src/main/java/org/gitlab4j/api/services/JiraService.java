package org.gitlab4j.api.services;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JiraService extends NotificationService {

    public static final String URL_PROP = "url";
    public static final String API_URL_PROP = "api_url";
    public static final String PROJECT_KEY_PROP = "project_key";
    public static final String USERNAME_PROP = "username";
    public static final String JIRA_ISSUE_TRANSITION_ID_PROP = "jira_issue_transition_id";

    private CharSequence password;

    public JiraService withCommitEvents(Boolean commitEvents) {
        return withCommitEvents(commitEvents, this);
    }

    public JiraService withMergeRequestsEvents(Boolean mergeRequestsEvents) {
        return withMergeRequestsEvents(mergeRequestsEvents, this);
    }

    @JsonIgnore
    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    public JiraService withPassword(CharSequence password) {
        setPassword(password);
        return (this);
    }

    @JsonIgnore
    public String getUrl() {
        return (getProperty(URL_PROP));
    }

    public void setUrl(String url) {
        setProperty(URL_PROP, url);
    }

    public JiraService withUrl(String url) {
        setUrl(url);
        return (this);
    }

    @JsonIgnore
    public String getApiUrl() {
        return (getProperty(API_URL_PROP));
    }

    public void setApiUrl(String apiUrl) {
        setProperty(API_URL_PROP, apiUrl);
    }

    public JiraService withApiUrl(String apiUrl) {
        setApiUrl(apiUrl);
        return (this);
    }

    @JsonIgnore
    public String getProjectKey() {
        return (getProperty(PROJECT_KEY_PROP));
    }

    public void setProjectKey(String projectKey) {
        setProperty(PROJECT_KEY_PROP, projectKey);
    }

    public JiraService withProjectKey(String projectKey) {
        setProjectKey(projectKey);
        return (this);
    }

    @JsonIgnore
    public String getUsername() {
        return (getProperty(USERNAME_PROP));
    }

    public void setUsername(String username) {
        setProperty(USERNAME_PROP, username);
    }

    public JiraService withUsername(String username) {
        setUsername(username);
        return (this);
    }

    @JsonIgnore
    public Integer getJiraIssueTransitionId() {
        return (getProperty(JIRA_ISSUE_TRANSITION_ID_PROP, (Integer) null));
    }

    public void setJiraIssueTransitionId(Integer jiraIssueTransitionId) {
        setProperty(JIRA_ISSUE_TRANSITION_ID_PROP, jiraIssueTransitionId);
    }

    public JiraService withJiraIssueTransitionId(Integer jiraIssueTransitionId) {
        setJiraIssueTransitionId(jiraIssueTransitionId);
        return (this);
    }

    @Override
    public void setProperties(Map<String, Object> properties) {
        fixJiraIssueTransitionId(properties);
        super.setProperties(properties);
    }

    /**
     * Make sure jiraIssueTransitionId is an integer and not an empty string.
     * @param properties the Map holding the properties
     */
    private void fixJiraIssueTransitionId(Map<String, Object> properties) {

        if (properties != null) {
            Object jiraIssueTransitionId = properties.get(JIRA_ISSUE_TRANSITION_ID_PROP);
            if (jiraIssueTransitionId instanceof String) {
                if (((String)jiraIssueTransitionId).trim().isEmpty()) {
                    properties.put(JIRA_ISSUE_TRANSITION_ID_PROP, null);
                } else {
                    properties.put(JIRA_ISSUE_TRANSITION_ID_PROP, Integer.valueOf((String)jiraIssueTransitionId));
                }
            }
        }
    }
}
