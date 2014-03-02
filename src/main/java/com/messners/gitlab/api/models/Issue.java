
package com.messners.gitlab.api.models;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Issue {
	
	private Assignee assignee;
	private Author author;
	private Date createdAt;
	private String description;
	private Integer id;
	private Integer iid;
	private List<String> labels;
	private Milestone milestone;
	private Integer project_id;
	private String state;
	private String title;
	private Date updatedAt;

	public Assignee getAssignee () {
		return this.assignee;
	}

	public void setAssignee (Assignee assignee) {
		this.assignee = assignee;
	}

	public Author getAuthor () {
		return this.author;
	}

	public void setAuthor (Author author) {
		this.author = author;
	}

	public Date getCreatedAt () {
		return this.createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription () {
		return this.description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public Integer getId () {
		return this.id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public Integer getIid () {
		return this.iid;
	}

	public void setIid (Integer iid) {
		this.iid = iid;
	}

	public List<String> getLabels () {
		return this.labels;
	}

	public void setLabels (List<String> labels) {
		this.labels = labels;
	}

	public Milestone getMilestone () {
		return this.milestone;
	}

	public void setMilestone (Milestone milestone) {
		this.milestone = milestone;
	}

	public Integer getProject_id () {
		return this.project_id;
	}

	public void setProject_id (Integer project_id) {
		this.project_id = project_id;
	}

	public String getState () {
		return this.state;
	}

	public void setState (String state) {
		this.state = state;
	}

	public String getTitle () {
		return this.title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public Date getUpdatedAt () {
		return this.updatedAt;
	}

	public void setUpdatedAt (Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
