package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Environment implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum EnvironmentState {
        AVAILABLE,
        STOPPED;

        private static JacksonJsonEnumHelper<EnvironmentState> enumHelper =
                new JacksonJsonEnumHelper<>(EnvironmentState.class);

        @JsonCreator
        public static EnvironmentState forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    private Long id;
    private String name;
    private String slug;
    private String externalUrl;
    private String tier;
    private EnvironmentState state;
    private Deployment lastDeployment;
    private Date autoStopAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public EnvironmentState getState() {
        return state;
    }

    public void setState(EnvironmentState state) {
        this.state = state;
    }

    public Deployment getLastDeployment() {
        return lastDeployment;
    }

    public void setLastDeployment(Deployment lastDeployment) {
        this.lastDeployment = lastDeployment;
    }

    public Date getAutoStopAt() {
        return autoStopAt;
    }

    public void setAutoStopAt(Date autoStopAt) {
        this.autoStopAt = autoStopAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
