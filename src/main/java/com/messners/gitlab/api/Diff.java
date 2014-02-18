
package com.messners.gitlab.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Diff {

	@JsonProperty("a_mode")
	private String aMode;
	
	@JsonProperty("b_mode")
	private String bMode;	
	
	private boolean deletedFile;
	private String diff;
	private boolean newFile;
	private String newPath;
	private String oldPath;
	private boolean renamedFile;

	public String getAMode () {
		return this.aMode;
	}

	public void setAMode (String aMode) {
		this.aMode = aMode;
	}

	public String getBMode () {
		return this.bMode;
	}

	public void setBMode (String bMode) {
		this.bMode = bMode;
	}

	public boolean getDeletedFile () {
		return this.deletedFile;
	}

	public void setDeletedFile (boolean deletedFile) {
		this.deletedFile = deletedFile;
	}

	public String getDiff () {
		return this.diff;
	}

	public void setDiff (String diff) {
		this.diff = diff;
	}

	public boolean getNewFile () {
		return this.newFile;
	}

	public void setNewFile (boolean newFile) {
		this.newFile = newFile;
	}

	public String getNewPath () {
		return this.newPath;
	}

	public void setNewPath (String newPath) {
		this.newPath = newPath;
	}

	public String getOldPath () {
		return this.oldPath;
	}

	public void setOldPath (String oldPath) {
		this.oldPath = oldPath;
	}

	public boolean getRenamedFile () {
		return this.renamedFile;
	}

	public void setRenamedFile (boolean renamedFile) {
		this.renamedFile = renamedFile;
	}
}
