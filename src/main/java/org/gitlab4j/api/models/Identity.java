package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.gitlab4j.api.utils.JacksonJson;

@XmlAccessorType(XmlAccessType.FIELD)
public class Identity {

    private String provider;
    private String externUid;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getExternUid() {
        return externUid;
    }

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
