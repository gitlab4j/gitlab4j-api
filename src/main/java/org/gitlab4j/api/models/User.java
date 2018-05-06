package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User extends AbstractUser {
    private String externUid;

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    public String getExternUid() {
        return this.externUid;
    }
}
