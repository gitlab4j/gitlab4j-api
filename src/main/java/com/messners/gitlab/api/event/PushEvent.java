
package com.messners.gitlab.api.event;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.messners.gitlab.api.Commit;
import com.messners.gitlab.api.Repository;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PushEvent {
	
	private String after;
	private String before;
	private List<Commit> commits;
	private Number projectId;
	private String ref;
	private Repository repository;
	private Number totalCommitsCount;
	private Number userId;
	private String userName;

	public String getAfter () {
		return this.after;
	}

	public void setAfter (String after) {
		this.after = after;
	}

	public String getBefore () {
		return this.before;
	}

	public void setBefore (String before) {
		this.before = before;
	}

	public List<Commit> getCommits () {
		return this.commits;
	}

	public void setCommits (List<Commit> commits) {
		this.commits = commits;
	}

	public Number getProjectId () {
		return this.projectId;
	}

	public void setProjectId (Number projectId) {
		this.projectId = projectId;
	}

	public String getRef () {
		return this.ref;
	}

	public void setRef (String ref) {
		this.ref = ref;
	}

	public Repository getRepository () {
		return this.repository;
	}

	public void setRepository (Repository repository) {
		this.repository = repository;
	}

	public Number getTotalCommitsCount () {
		return this.totalCommitsCount;
	}

	public void setTotalCommitsCount (Number totalCommitsCount) {
		this.totalCommitsCount = totalCommitsCount;
	}

	public Number getUserId () {
		return this.userId;
	}

	public void setUserId (Number userId) {
		this.userId = userId;
	}

	public String getUserName () {
		return this.userName;
	}

	public void setUserName (String userName) {
		this.userName = userName;
	}
}
