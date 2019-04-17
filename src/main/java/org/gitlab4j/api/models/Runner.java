package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class Runner {

    private Integer id;
    private String description;
    private Boolean active;
    private Boolean isShared;
    private String name;
    private Boolean online;
    private RunnerStatus status;
    private String ipAddress;

    /**
     * Enum to use for RunnersApi filtering.
     */
    public enum RunnerStatus {
        SPECIFIC, SHARED, ACTIVE, ONLINE, PAUSED, OFFLINE;
        private static JacksonJsonEnumHelper<RunnerStatus> enumHelper =
                new JacksonJsonEnumHelper<>(RunnerStatus.class);

        @JsonCreator
        public static RunnerStatus forValue(String value) {
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIs_shared() {
        return isShared;
    }

    public void setIs_shared(Boolean is_shared) {
        this.isShared = is_shared;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return this.online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public RunnerStatus getStatus() {
        return this.status;
    }

    public void setStatus(RunnerStatus status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    public Runner withId(Integer id) {
        this.id = id;
        return this;
    }

    public Runner withDescription(String description) {
        this.description = description;
        return this;
    }

    public Runner withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Runner withIsShared(Boolean isShared) {
        this.isShared = isShared;
        return this;
    }

    public Runner withName(String name) {
        this.name = name;
        return this;
    }

    public Runner withOnline(Boolean online) {
        this.online = online;
        return this;
    }

    public Runner withStatus(RunnerStatus status) {
        this.status = status;
        return this;
    }

    public Runner withIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
