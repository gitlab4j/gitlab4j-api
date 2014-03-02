
package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

	private String actionName;
	private Integer authorId;
	
	private EventData data;
	
	private Integer projectId;
	private String targetId;
	private String targetTitle;
	private String targetType;
	private String title;

	public String getActionName () {
		return this.actionName;
	}

	public void setActionName (String actionName) {
		this.actionName = actionName;
	}

	public Integer getAuthorId () {
		return this.authorId;
	}

	public void setAuthorId (Integer authorId) {
		this.authorId = authorId;
	}

	public EventData getData () {
		return this.data;
	}

	public void setData (EventData data) {
		this.data = data;
	}

	public Integer getProjectId () {
		return this.projectId;
	}

	public void setProjectId (Integer projectId) {
		this.projectId = projectId;
	}

	public String getTargetId () {
		return this.targetId;
	}

	public void setTargetId (String targetId) {
		this.targetId = targetId;
	}

	public String getTargetTitle () {
		return this.targetTitle;
	}

	public void setTargetTitle (String targetTitle) {
		this.targetTitle = targetTitle;
	}

	public String getTargetType () {
		return this.targetType;
	}

	public void setTargetType (String targetType) {
		this.targetType = targetType;
	}

	public String getTitle () {
		return this.title;
	}

	public void setTitle (String title) {
		this.title = title;
	}
}
