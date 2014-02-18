package com.messners.gitlab.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {

	private String bio;
	private boolean blocked;
	private boolean canCreateGroup;
	private boolean canCreateProject;
	private boolean canCreateTeam;
	private Date createdAt;
	private boolean darkScheme;
	private String email;
	private Integer id;
	private boolean isAdmin;
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

	public boolean getBlocked () {
		return this.blocked;
	}

	public void setBlocked (boolean blocked) {
		this.blocked = blocked;
	}

	public boolean getCanCreateGroup () {
		return this.canCreateGroup;
	}

	public void setCanCreateGroup (boolean canCreateGroup) {
		this.canCreateGroup = canCreateGroup;
	}

	public boolean getCanCreateProject () {
		return this.canCreateProject;
	}

	public void setCanCreateProject (boolean canCreateProject) {
		this.canCreateProject = canCreateProject;
	}

	public boolean getCanCreateTeam () {
		return this.canCreateTeam;
	}

	public void setCanCreateTeam (boolean canCreateTeam) {
		this.canCreateTeam = canCreateTeam;
	}

	public Date getCreatedAt () {
		return this.createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getDarkScheme () {
		return this.darkScheme;
	}

	public void setDarkScheme (boolean darkScheme) {
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

	public boolean getIsAdmin () {
		return this.isAdmin;
	}

	public void setIsAdmin (boolean isAdmin) {
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
