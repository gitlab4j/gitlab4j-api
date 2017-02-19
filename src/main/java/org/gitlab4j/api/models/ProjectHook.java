
package org.gitlab4j.api.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectHook {

    private Boolean build_events;
    private Date createdAt;
    private Boolean enable_ssl_verification;
    private Integer id;
    private Boolean issuesEvents;
    private Boolean mergeRequestsEvents;
    private Boolean note_events;
    private Boolean pipeline_events;
    private Integer projectId;
    private Boolean pushEvents;
    private Boolean tag_push_events;
    private String url;
    private Boolean wiki_page_events;

    public Boolean getBuild_events() {
        return build_events;
    }

    public void setBuild_events(Boolean build_events) {
        this.build_events = build_events;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getEnable_ssl_verification() {
        return enable_ssl_verification;
    }

    public void setEnable_ssl_verification(Boolean enable_ssl_verification) {
        this.enable_ssl_verification = enable_ssl_verification;
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

    public Boolean getNote_events() {
        return note_events;
    }

    public void setNote_events(Boolean note_events) {
        this.note_events = note_events;
    }

    public Boolean getPipeline_events() {
        return pipeline_events;
    }

    public void setPipeline_events(Boolean pipeline_events) {
        this.pipeline_events = pipeline_events;
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

    public Boolean getTag_push_events() {
        return tag_push_events;
    }

    public void setTag_push_events(Boolean tag_push_events) {
        this.tag_push_events = tag_push_events;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getWiki_page_events() {
        return wiki_page_events;
    }

    public void setWiki_page_events(Boolean wiki_page_events) {
        this.wiki_page_events = wiki_page_events;
    }
}