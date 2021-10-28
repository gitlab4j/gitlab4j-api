package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

public class ResourceGroup {

    public enum ProcessMode {
        UNORDERED, OLDEST_FIRST, NEWEST_FIRST;
        private static JacksonJsonEnumHelper<ProcessMode> enumHelper = new JacksonJsonEnumHelper<>(ProcessMode.class);

        @JsonCreator
        public static ProcessMode forValue(String value) {
            return enumHelper.forValue((value != null ? value.toLowerCase() : null));
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

    private Integer id;
    private String key;
    private ProcessMode processMode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ProcessMode getProcessMode() {
        return processMode;
    }

    public void setProcessMode(ProcessMode processMode) {
        this.processMode = processMode;
    }

    @Override
    public String toString() {
        return "ResourceGroup{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", processMode=" + processMode +
                '}';
    }
}
