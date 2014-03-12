
package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectHook {
	
	private String createdAt;
	private Integer id;
	private Boolean issuesEvents;
	private Boolean mergeRequestEvents;
	private Integer projectId;
	private Boolean pushEvents;
	private String url;

	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIssuesEvents() {
		return this.issuesEvents;
	}

	public void setIssuesEvents(Boolean issuesEvents) {
		this.issuesEvents = issuesEvents;
	}

	public Boolean getMergeRequestEvents() {
		return this.mergeRequestEvents;
	}

	public void setMergeRequestEvents(Boolean mergeRequestEvents) {
		this.mergeRequestEvents = mergeRequestEvents;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Boolean getPushEvents() {
		return this.pushEvents;
	}

	public void setPushEvents(Boolean pushEvents) {
		this.pushEvents = pushEvents;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
