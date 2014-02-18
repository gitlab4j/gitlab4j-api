
package com.messners.gitlab.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Branch {

	private Commit commit;
	private String name;
	private boolean isProtected;

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

	public boolean getProtected () {
		return this.isProtected;
	}

	public void setProtected (boolean isProtected) {
		this.isProtected = isProtected;
	}
}
