package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommitRef {

    private RefType type;
    private String name;

   public enum RefType {
        branch, tag, all
    }

    public RefType getType() {
        return type;
    }

    public void setType(RefType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
