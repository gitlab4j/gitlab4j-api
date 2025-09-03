package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Identity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The provider associated with the SAML configuration.
     */
    @JsonProperty("provider")
    private String provider;

    /**
     * The external user ID associated with the SAML provider.
     */
    @JsonProperty("extern_uid")
    private String externUid;

    /**
     * The ID of the SAML provider.
     */
    @JsonProperty("saml_provider_id")
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
