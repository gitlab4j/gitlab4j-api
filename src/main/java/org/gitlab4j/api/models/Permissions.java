package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Permissions {   

    private ProjectAccess projectAccess;
    private ProjectAccess groupAccess;

    public ProjectAccess getProjectAccess() {
        return projectAccess;
    }

    public ProjectAccess getGroupAccess() {
        return groupAccess;
    }
}
