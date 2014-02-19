package com.messners.gitlab.api.webhook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventObject {
	
	public enum ObjectKind {
		MERGE_REQUEST,
		ISSUE;
		
		public String toString () {
			return (name().toLowerCase());
		}
	}
	
	private EventObjectAttributes objectAttributes;
	private ObjectKind objectKind;

	public EventObjectAttributes getObjectAttributes () {
		return this.objectAttributes;
	}

	public void setObjectAttributes (EventObjectAttributes objectAttributes) {
		this.objectAttributes = objectAttributes;
	}

	public ObjectKind getObjectKind () {
		return this.objectKind;
	}

	public void setObjectKind (ObjectKind objectKind) {
		this.objectKind = objectKind;
	}
}
