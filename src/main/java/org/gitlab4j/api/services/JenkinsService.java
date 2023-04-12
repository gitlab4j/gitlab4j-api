package org.gitlab4j.api.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gitlab4j.api.GitLabApiForm;

public class JenkinsService extends NotificationService {

    public static final String URL_PROP = "jenkins_url";
    public static final String PROJECT_NAME_PROP = "project_name";
    public static final String USERNAME_PROP = "username";
    public static final String PASSWORD_PROP = "password";
    public static final String MERGE_REQUEST_EVENTS_PROP = "merge_request_events";
    public static final String TAG_PUSH_EVENTS_PROP = "tag_push_events";
    private CharSequence password;

    @Override
    public GitLabApiForm servicePropertiesForm() {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam(URL_PROP, getUrl(), true)
                .withParam(PROJECT_NAME_PROP, getProjectName(), true)
                .withParam(USERNAME_PROP, getUsername())
                .withParam(PASSWORD_PROP, getPassword())
                .withParam(PUSH_EVENTS_PROP, getPushEvents())
                .withParam(MERGE_REQUEST_EVENTS_PROP, getMergeRequestsEvents())
                .withParam(TAG_PUSH_EVENTS_PROP, getTagPushEvents());
        return formData;
    }

    @JsonIgnore
    public String getUrl() {
        return (getProperty(URL_PROP));
    }

    public void setUrl(String url) {
        setProperty(URL_PROP, url);
    }

    public JenkinsService withUrl(String url) {
        setUrl(url);
        return (this);
    }

    @JsonIgnore
    public String getProjectName() {
        return (getProperty(PROJECT_NAME_PROP));
    }

    public void setProjectName(String projectName) {
        setProperty(PROJECT_NAME_PROP, projectName);
    }

    public JenkinsService withProjectName(String projectName) {
        setProjectName(projectName);
        return (this);
    }

    @JsonIgnore
    public String getUsername() {
        return (getProperty(USERNAME_PROP));
    }

    public void setUsername(String username) {
        setProperty(USERNAME_PROP, username);
    }

    public JenkinsService withUsername(String username) {
        setUsername(username);
        return (this);
    }

    @JsonIgnore
    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    public JenkinsService withPassword(CharSequence password) {
        setPassword(password);
        return (this);
    }

}
