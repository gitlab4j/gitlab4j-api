package com.messners.gitlab.api.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType (XmlAccessType.FIELD)
public class AbstractUser {

	private Date createdAt;
	
	private String email;
	private Integer id;
	private String name;
	private String state;
	private String username;
	private Boolean blocked;

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

	public Integer getId () {
		return this.id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getState () {
		return this.state;
	}

	public void setState (String state) {
		this.state = state;
	}

	public String getUsername () {
		return this.username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
}
