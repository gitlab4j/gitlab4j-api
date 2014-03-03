
package com.messners.gitlab.api.models;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Commit {

	private Author author;
	private Date authoredDate;
	private Date committedDate;

	private Committer committer;
	private String id;
	private String message;
	
	private List<Parent> parents;
    
	private String shortId;
	private Date timestamp;
	
	private String title;
	private String tree;
	private String url;

	public Author getAuthor () {
		return this.author;
	}

	public void setAuthor (Author author) {
		this.author = author;
	}

	public Date getAuthored_date () {
		return this.authoredDate;
	}

	public void setAuthored_date (Date authoredDate) {
		this.authoredDate = authoredDate;
	}

	public Date getCommitted_date () {
		return this.committedDate;
	}

	public void setCommitted_date (Date committedDate) {
		this.committedDate = committedDate;
	}

	public Committer getCommitter () {
		return this.committer;
	}

	public void setCommitter (Committer committer) {
		this.committer = committer;
	}

	public String getId () {
		return this.id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getShortId () {
		return this.shortId;
	}

	public String getMessage () {
		return this.message;
	}

	public void setMessage (String message) {
		this.message = message;
	}

	public List<Parent> getParents () {
		return this.parents;
	}

	public void setParents (List<Parent> parents) {
		this.parents = parents;
	}

	public void setShort_id (String short_id) {
		this.shortId = short_id;
	}

	public Date getTimestamp () {
		return this.timestamp;
	}

	public void setTimestamp (Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle () {
		return this.title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public String getTree () {
		return this.tree;
	}

	public void setTree (String tree) {
		this.tree = tree;
	}

	public String getUrl () {
		return this.url;
	}

	public void setUrl (String url) {
		this.url = url;
	}
}
