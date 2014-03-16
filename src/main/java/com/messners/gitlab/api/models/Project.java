
package com.messners.gitlab.api.models;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
	
	private Date createdAt;
	private String defaultBranch;
	private String description;
	private String httpUrlToRepo;
	private Integer id;
	private Boolean issuesEnabled;
	private Date lastActivityAt;
	private Boolean mergeRequestsEnabled;
	private String name;
	private String nameWithNamespace;
	private Namespace namespace;
	private Owner owner;
	private String path;
	private String pathWithNamespace;
	private Boolean isPublic;
	private Boolean snippetsEnabled;
	private String sshUrlToRepo;
	private Integer visibilityLevel;
	private Boolean wallEnabled;
	private String webUrl;
	private Boolean wikiEnabled;

	public Date getCreatedAt () {
		return this.createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDefaultBranch () {
		return this.defaultBranch;
	}

	public void setDefaultBranch (String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}

	public String getDescription () {
		return this.description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public String getHttpUrlToRepo () {
		return this.httpUrlToRepo;
	}

	public void setHttpUrlToRepo (String httpUrlToRepo) {
		this.httpUrlToRepo = httpUrlToRepo;
	}

	public Integer getId () {
		return this.id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public Boolean getIssuesEnabled () {
		return this.issuesEnabled;
	}

	public void setIssuesEnabled (Boolean issuesEnabled) {
		this.issuesEnabled = issuesEnabled;
	}

	public Date getLastActivityAt () {
		return this.lastActivityAt;
	}

	public void setLastActivityAt (Date lastActivityAt) {
		this.lastActivityAt = lastActivityAt;
	}

	public Boolean getMergeRequestsEnabled () {
		return this.mergeRequestsEnabled;
	}

	public void setMergeRequestsEnabled (Boolean mergeRequestsEnabled) {
		this.mergeRequestsEnabled = mergeRequestsEnabled;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getNameWithNamespace () {
		return this.nameWithNamespace;
	}

	public void setNameWithNamespace (String nameWithNamespace) {
		this.nameWithNamespace = nameWithNamespace;
	}

	public Namespace getNamespace () {
		return this.namespace;
	}

	public void setNamespace (Namespace namespace) {
		this.namespace = namespace;
	}

	public Owner getOwner () {
		return this.owner;
	}

	public void setOwner (Owner owner) {
		this.owner = owner;
	}

	public String getPath () {
		return this.path;
	}

	public void setPath (String path) {
		this.path = path;
	}

	public String getPathWithNamespace () {
		return this.pathWithNamespace;
	}

	public void setPathWithNamespace (String pathWithNamespace) {
		this.pathWithNamespace = pathWithNamespace;
	}

	public Boolean getPublic () {
		return this.isPublic;
	}

	public void setPublic (Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getSnippetsEnabled () {
		return this.snippetsEnabled;
	}

	public void setSnippetsEnabled (Boolean snippetsEnabled) {
		this.snippetsEnabled = snippetsEnabled;
	}

	public String getSshUrlToRepo () {
		return this.sshUrlToRepo;
	}

	public void setSshUrlToRepo (String sshUrlToRepo) {
		this.sshUrlToRepo = sshUrlToRepo;
	}

	public Integer getVisibilityLevel () {
		return this.visibilityLevel;
	}

	public void setVisibilityLevel (Integer visibilityLevel) {
		this.visibilityLevel = visibilityLevel;
	}

	public Boolean getWallEnabled () {
		return this.wallEnabled;
	}

	public void setWallEnabled (Boolean wallEnabled) {
		this.wallEnabled = wallEnabled;
	}

	public String getWebUrl () {
		return this.webUrl;
	}

	public void setWebUrl (String webUrl) {
		this.webUrl = webUrl;
	}

	public Boolean getWikiEnabled () {
		return this.wikiEnabled;
	}

	public void setWikiEnabled (Boolean wikiEnabled) {
		this.wikiEnabled = wikiEnabled;
	}
	
	public static final boolean isValid (Project project) {
		return (project != null && project.getId() != null);
	}
}
