package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MergeRequest {
	
	private Assignee assignee;
	private Author author;
	private Integer downvotes;
	private Integer id;
	private Integer iid;
	private Integer projectId;
	private String sourceBranch;
	private String state;
	private String targetBranch;
	private String title;
	private String description;
	private Integer upvotes;

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

	public Integer getDownvotes () {
		return this.downvotes;
	}

	public void setDownvotes (Integer downvotes) {
		this.downvotes = downvotes;
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

	public Integer getProjectId () {
		return this.projectId;
	}

	public void setProjectId (Integer projectId) {
		this.projectId = projectId;
	}

	public String getSourceBranch () {
		return this.sourceBranch;
	}

	public void setSourceBranch (String sourceBranch) {
		this.sourceBranch = sourceBranch;
	}

	public String getState () {
		return this.state;
	}

	public void setState (String state) {
		this.state = state;
	}

	public String getTargetBranch () {
		return this.targetBranch;
	}

	public void setTargetBranch (String targetBranch) {
		this.targetBranch = targetBranch;
	}

	public String getTitle () {
		return this.title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUpvotes () {
		return this.upvotes;
	}

	public void setUpvotes (Integer upvotes) {
		this.upvotes = upvotes;
	}
	
	public static final boolean isValid (MergeRequest mergeRequest) {
		return (mergeRequest != null && mergeRequest.getId() != null);
	}
}
