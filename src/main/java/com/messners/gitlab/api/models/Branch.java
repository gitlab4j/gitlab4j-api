
package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Branch {

	private Commit commit;
	private String name;
	private Boolean isProtected;

	public Commit getCommit () {
		return this.commit;
	}

	public void setCommit (Commit commit) {
		this.commit = commit;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Boolean getProtected () {
		return this.isProtected;
	}

	public void setProtected (Boolean isProtected) {
		this.isProtected = isProtected;
	}
}
