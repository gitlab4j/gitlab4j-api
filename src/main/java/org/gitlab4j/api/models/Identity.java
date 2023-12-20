package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.io.Serializable;

public class Identity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String provider;
    private String externUid;
    private Integer samlProviderId;

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

    public Integer getSamlProviderId() {
	return samlProviderId;
    }

    public void setSamlProviderId(Integer samlProviderId) {
	this.samlProviderId = samlProviderId;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
