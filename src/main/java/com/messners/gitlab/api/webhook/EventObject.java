package com.messners.gitlab.api.webhook;

import javax.xml.bind.annotation.XmlAccessType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY,
    property = "object_kind",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = IssueEvent.class, name = IssueEvent.OBJECT_KIND),
    @JsonSubTypes.Type(value = PushEvent.class, name = PushEvent.OBJECT_KIND),    
    @JsonSubTypes.Type(value = MergeRequestEvent.class, name = MergeRequestEvent.OBJECT_KIND)
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventObject {
    
    public static enum ObjectKind {
        ISSUE, MERGE_REQUEST, PUSH;
        
        @Override
        public String toString() {
            return (name().toLowerCase());
        }   
    }

    private ObjectKind objectKind;

    public ObjectKind getObjectKind() {
        return this.objectKind;
    }

    public void setObjectKind(ObjectKind objectKind) {
        this.objectKind = objectKind;
    }
}
