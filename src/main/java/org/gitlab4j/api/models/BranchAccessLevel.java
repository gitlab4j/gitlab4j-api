package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BranchAccessLevel {

    private AccessLevel accessLevel;
    private String accessLevelDescription;

    public AccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevelDescription() {
        return this.accessLevelDescription;
    }

    public void setAccessLevelDescription(String accessLevelDescription) {
        this.accessLevelDescription = accessLevelDescription;
    }
}
