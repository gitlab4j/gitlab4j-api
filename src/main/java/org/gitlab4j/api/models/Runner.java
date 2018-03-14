package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Runner {

    private Integer id;
    private String description;
    private Boolean active;
    private Boolean isShared;
    private String name;
    private Boolean online;
    private RunnerStatus status;

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
}
