package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Parent {
	
	private String id;
	
	public String getId () {
		return this.id;
	}

	public void setId (String id) {
		this.id = id;
	}		
}