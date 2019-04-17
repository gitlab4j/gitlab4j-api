package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Variable {

    private String key;
    private String value;
    @JsonProperty("protected")
    private Boolean isProtected;
    private String environmentScope;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getProtected() {
        return isProtected;
    }

    public void setProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    public String getEnvironmentScope() {
        return environmentScope;
    }

    public void setEnvironmentScope(String environmentScope) {
        this.environmentScope = environmentScope;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
