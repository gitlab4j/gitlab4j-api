package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContainerExpirationPolicyAttributes implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("container_expiration_policy_attributes")
    private ContainerExpirationPolicy containerExpirationPolicyAttributes;

    public ContainerExpirationPolicy getContainerExpirationPolicyAttributes() {
        return containerExpirationPolicyAttributes;
    }

    public void setContainerExpirationPolicyAttributes(ContainerExpirationPolicy containerExpirationPolicyAttributes) {
        this.containerExpirationPolicyAttributes = containerExpirationPolicyAttributes;
    }

    public ContainerExpirationPolicyAttributes withContainerExpirationPolicyAttributes(
            ContainerExpirationPolicy containerExpirationPolicyAttributes) {
        this.containerExpirationPolicyAttributes = containerExpirationPolicyAttributes;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
