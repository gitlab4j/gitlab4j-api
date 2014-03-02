
package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MergeRequestComment {

	private Author author;
	private String note;

	public Author getAuthor () {
		return this.author;
	}

	public void setAuthor (Author author) {
		this.author = author;
	}

	public String getNote () {
		return this.note;
	}

	public void setNote (String note) {
		this.note = note;
	}
}
