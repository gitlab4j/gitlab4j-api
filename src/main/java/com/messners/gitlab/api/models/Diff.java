
package com.messners.gitlab.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Diff {

	@JsonSerialize(include=Inclusion.ALWAYS)
	@JsonProperty("a_mode")
	private String a_mode;
	
	@JsonSerialize(include=Inclusion.ALWAYS)
	@JsonProperty("b_mode")
	private String b_mode;	
	
	private Boolean deletedFile;
	private String diff;
	private Boolean newFile;
	private String newPath;
	private String oldPath;
	private Boolean renamedFile;

	@JsonProperty("a_mode")
	public String getAMode () {
		return this.a_mode;
	}

	public void setAMode (String aMode) {
		this.a_mode = aMode;
	}

	@JsonProperty("b_mode")
	public String getBMode () {
		return this.b_mode;
	}

	public void setBMode (String bMode) {
		this.b_mode = bMode;
	}

	public Boolean getDeletedFile () {
		return this.deletedFile;
	}

	public void setDeletedFile (Boolean deletedFile) {
		this.deletedFile = deletedFile;
	}

	public String getDiff () {
		return this.diff;
	}

	public void setDiff (String diff) {
		this.diff = diff;
	}

	public Boolean getNewFile () {
		return this.newFile;
	}

	public void setNewFile (Boolean newFile) {
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

	public Boolean getRenamedFile () {
		return this.renamedFile;
	}

	public void setRenamedFile (Boolean renamedFile) {
		this.renamedFile = renamedFile;
	}
}
