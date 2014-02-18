
package com.messners.gitlab.api;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class EventData {

	private String after;
	private String before;
	private List<Commit> commits;
	private String ref;
	private Repository repository;
	private Integer total_commits_count;
	private Integer user_id;
	private String user_name;

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

	public Integer getTotal_commits_count () {
		return this.total_commits_count;
	}

	public void setTotal_commits_count (Integer total_commits_count) {
		this.total_commits_count = total_commits_count;
	}

	public Integer getUser_id () {
		return this.user_id;
	}

	public void setUser_id (Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name () {
		return this.user_name;
	}

	public void setUser_name (String user_name) {
		this.user_name = user_name;
	}
}
