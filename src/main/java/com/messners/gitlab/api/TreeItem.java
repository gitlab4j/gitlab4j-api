package com.messners.gitlab.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TreeItem {
	
	public enum Type {
		TREE,
		BLOB;
		
		public String toString () {
			return (name().toLowerCase());
		}
	}

	private String id;
	private String mode;
	private String name;
	private Type type;

	public String getId () {
		return this.id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getMode () {
		return this.mode;
	}

	public void setMode (String mode) {
		this.mode = mode;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Type getType () {
		return this.type;
	}

	public void setType (Type type) {
		this.type = type;
	}
}
