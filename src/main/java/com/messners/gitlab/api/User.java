package com.messners.gitlab.api;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	private String bio;
	private boolean canCreateGroup;
	private Integer colorSchemeId;
	private Date createdAt;
	private String email;
	private String externUid;
	private Integer id;
	private boolean isAdmin;
	private String linkedin;
	private String name;
	private String provider;
	private String skype;
	private String state;
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

	public boolean getCanCreateGroup () {
		return this.canCreateGroup;
	}

	public void setCanCreateGroup (boolean canCreateGroup) {
		this.canCreateGroup = canCreateGroup;
	}

	public Integer getColorSchemeId () {
		return this.colorSchemeId;
	}

	public void setColorSchemeId (Integer colorSchemeId) {
		this.colorSchemeId = colorSchemeId;
	}

	public Date getCreatedAt () {
		return this.createdAt;
	}

	public void setCreatedAt (Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail () {
		return this.email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getExternUid () {
		return this.externUid;
	}

	public void setExternUid (String externUid) {
		this.externUid = externUid;
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

	public String getProvider () {
		return this.provider;
	}

	public void setProvider (String provider) {
		this.provider = provider;
	}

	public String getSkype () {
		return this.skype;
	}

	public void setSkype (String skype) {
		this.skype = skype;
	}

	public String getState () {
		return this.state;
	}

	public void setState (String state) {
		this.state = state;
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
