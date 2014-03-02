
package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
	
	private Integer id;
	private String name;
	private Integer ownerId;
	private String path;

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

	public Integer getOwnerId () {
		return this.ownerId;
	}

	public void setOwnerId (Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getPath () {
		return this.path;
	}

	public void setPath (String path) {
		this.path = path;
	}
}
