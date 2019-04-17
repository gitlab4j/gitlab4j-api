
package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectHook {

    private Boolean buildEvents;
    private Date createdAt;
    private Boolean enableSslVerification;
    private Integer id;
    private Boolean issuesEvents;
    private Boolean mergeRequestsEvents;
    private Boolean noteEvents;
    private Boolean jobEvents;
    private Boolean pipelineEvents;
    private Integer projectId;
    private Boolean pushEvents;
    private Boolean tagPushEvents;
    private String url;
    private Boolean wikiPageEvents;
    private String token;

    
    public Boolean getBuildEvents() {
        return buildEvents;
    }

    public void setBuildEvents(Boolean buildEvents) {
        this.buildEvents = buildEvents;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getEnableSslVerification() {
        return enableSslVerification;
    }

    public void setEnableSslVerification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIssuesEvents() {
        return issuesEvents;
    }

    public void setIssuesEvents(Boolean issuesEvents) {
        this.issuesEvents = issuesEvents;
    }

    public Boolean getMergeRequestsEvents() {
        return mergeRequestsEvents;
    }

    public void setMergeRequestsEvents(Boolean mergeRequestsEvents) {
        this.mergeRequestsEvents = mergeRequestsEvents;
    }

    public Boolean getNoteEvents() {
        return noteEvents;
    }

    public void setNoteEvents(Boolean noteEvents) {
        this.noteEvents = noteEvents;
    }
    
    public Boolean getJobEvents() {
        return jobEvents;
    }

    public void setJobEvents(Boolean jobEvents) {
        this.jobEvents = jobEvents;
    }

    public Boolean getPipelineEvents() {
        return pipelineEvents;
    }

    public void setPipelineEvents(Boolean pipelineEvents) {
        this.pipelineEvents = pipelineEvents;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Boolean getPushEvents() {
        return pushEvents;
    }

    public void setPushEvents(Boolean pushEvents) {
        this.pushEvents = pushEvents;
    }

    public Boolean getTagPushEvents() {
        return tagPushEvents;
    }

    public void setTagPushEvents(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getWikiPageEvents() {
        return wikiPageEvents;
    }

    public void setWikiPageEvents(Boolean wikiPageEvents) {
        this.wikiPageEvents = wikiPageEvents;
    }

    /**
     * @return the do build events flag
     * @deprecated As of release 4.1.0, replaced by {@link #getBuildEvents()}
     */
    @Deprecated
    @JsonIgnore
    public Boolean getBuild_events() {
        return buildEvents;
    }

    /**
     * @param buildEvents the do build events flag
     * @deprecated As of release 4.1.0, replaced by {@link #setBuildEvents(Boolean)}
     */
    @Deprecated
    @JsonIgnore
    public void setBuild_events(Boolean buildEvents) {
        this.buildEvents = buildEvents;
    }

    /**
     * @return the enable SSL verification flag
     * @deprecated As of release 4.1.0, replaced by {@link #getEnableSslVerification()}
     */
    @Deprecated
    @JsonIgnore
    public Boolean getEnable_ssl_verification() {
        return enableSslVerification;
    }

    /**
     * @param enableSslVerification the enable SSL verification flag
     * @deprecated As of release 4.1.0, replaced by {@link #setEnableSslVerification(Boolean)}
     */
    @Deprecated
    @JsonIgnore
    public void setEnable_ssl_verification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
    }

    /**
     * @return the do note events flag
     * @deprecated As of release 4.1.0, replaced by {@link #getNoteEvents()}
     */
    @Deprecated
    @JsonIgnore
    public Boolean getNote_events() {
        return noteEvents;
    }

    /**
     * @param noteEvents the do note events flag
     * @deprecated As of release 4.1.0, replaced by {@link #setNoteEvents(Boolean)}
     */
    @Deprecated
    @JsonIgnore
    public void setNote_events(Boolean noteEvents) {
        this.noteEvents = noteEvents;
    }

    /**
     * @return the do pipeline events flag
     * @deprecated As of release 4.1.0, replaced by {@link #getPipelineEvents()}
     */
    @Deprecated
    @JsonIgnore
    public Boolean getPipeline_events() {
        return pipelineEvents;
    }

    /**
     * @param pipelineEvents the do pipeline events flag
     * @deprecated As of release 4.1.0, replaced by {@link #setPipelineEvents(Boolean)}
     */
    @Deprecated
    @JsonIgnore
    public void setPipeline_events(Boolean pipelineEvents) {
        this.pipelineEvents = pipelineEvents;
    }

    /**
     * @return the do tag push events flag
     * @deprecated As of release 4.1.0, replaced by {@link #getTagPushEvents()}
     */
    @Deprecated
    @JsonIgnore
    public Boolean getTag_push_events() {
        return tagPushEvents;
    }

    /**
     * @param tagPushEvents the do tag push events flag
     * @deprecated As of release 4.1.0, replaced by {@link #setTagPushEvents(Boolean)}
     */
    @Deprecated
    @JsonIgnore
    public void setTag_push_events(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
    }

    /**
     * @return the do wiki page events flag
     * @deprecated As of release 4.1.0, replaced by {@link #getWikiPageEvents()}
     */
    @Deprecated
    @JsonIgnore
    public Boolean getWiki_page_events() {
        return wikiPageEvents;
    }

    /**
     * @param wikiPageEvents the do wiki page events flag
     * @deprecated As of release 4.1.0, replaced by {@link #setWikiPageEvents(Boolean)}
     */
    @Deprecated
    @JsonIgnore
    public void setWiki_page_events(Boolean wikiPageEvents) {
        this.wikiPageEvents = wikiPageEvents;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}