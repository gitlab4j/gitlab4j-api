package com.messners.gitlab.api.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {

	private String bio;
	private Boolean blocked;
	private Boolean canCreateGroup;
	private Boolean canCreateProject;
	private Boolean canCreateTeam;
	private Date createdAt;
	private Boolean darkScheme;
	private String email;
	private Integer id;
	private Boolean isAdmin;
	private String linkedin;
	private String name;
	private String privateToken;
	private String skype;
	private Integer themeId;
	private String twitter;
	private String username;
	private String websiteUrl;

	public String getBio () {
		return this.bio;
	}

	public void setBio (String bio) {
		this.bio = bio;
	}

	public Boolean getBlocked () {
		return this.blocked;
	}

	public void setBlocked (Boolean blocked) {
		this.blocked = blocked;
	}

	public Boolean getCanCreateGroup () {
		return this.canCreateGroup;
	}

	public void setCanCreateGroup (Boolean canCreateGroup) {
		this.canCreateGroup = canCreateGroup;
	}

	public Boolean getCanCreateProject () {
		return this.canCreateProject;
	}

	public void setCanCreateProject (Boolean canCreateProject) {
		this.canCreateProject = canCreateProject;
	}

	public Boolean getCanCreateTeam () {
		return this.canCreateTeam;
	}

	public void setCanCreateTeam (Boolean canCreateTeam) {
		this.canCreateTeam = canCreateTeam;
	}

	public Date getCreatedAt () {
		return this.createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getDarkScheme () {
		return this.darkScheme;
	}

	public void setDarkScheme (Boolean darkScheme) {
		this.darkScheme = darkScheme;
	}

	public String getEmail () {
		return this.email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public Integer getId () {
		return this.id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public Boolean getIsAdmin () {
		return this.isAdmin;
	}

	public void setIsAdmin (Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLinkedin () {
		return this.linkedin;
	}

	public void setLinkedin (String linkedin) {
		this.linkedin = linkedin;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getPrivateToken () {
		return this.privateToken;
	}

	public void setPrivateToken (String privateToken) {
		this.privateToken = privateToken;
	}

	public String getSkype () {
		return this.skype;
	}

	public void setSkype (String skype) {
		this.skype = skype;
	}

	public Integer getThemeId () {
		return this.themeId;
	}

	public void setThemeId (Integer themeId) {
		this.themeId = themeId;
	}

	public String getTwitter () {
		return this.twitter;
	}

	public void setTwitter (String twitter) {
		this.twitter = twitter;
	}

	public String getUsername () {
		return this.username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public String getWebsiteUrl () {
		return this.websiteUrl;
	}

	public void setWebsiteUrl (String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
}
