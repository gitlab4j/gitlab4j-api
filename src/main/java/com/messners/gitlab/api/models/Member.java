
package com.messners.gitlab.api.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Member {
	
	public static final int GUEST_LEVEL     = 10;
	public static final int REPORTER_LEVEL  = 20;
	public static final int	DEVELOPER_LEVEL = 30;
	public static final int	MASTER_LEVEL    = 40;
	public static final int	OWNER_LEVEL     = 50;

	private Integer accessLevel;
	private Date createdAt;
	private String email;
	private Integer id;
	private String name;
	private String state;
	private String username;

	public Integer getAccessLevel () {
		return this.accessLevel;
	}

	public void setAccessLevel (Integer accessLevel) {
		this.accessLevel = accessLevel;
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
}
