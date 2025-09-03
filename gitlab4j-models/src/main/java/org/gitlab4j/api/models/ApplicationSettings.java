package org.gitlab4j.api.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.*;

public class ApplicationSettings implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the application settings.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The date when the application settings were created.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * The date when the application settings were last updated.
     */
    @JsonProperty("updated_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date updatedAt;

    /**
     * The settings for the application stored as key-value pairs.
     */
    @JsonProperty("settings")
    private Map<String, Object> settings = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Object> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, Object> settings) {
        this.settings = settings;
    }

    @JsonIgnore
    public Object getSetting(Setting setting) {

        if (setting == null) {
            return (null);
        }

        String name = setting.toString();
        return (settings.get(name));
    }

    @JsonIgnore
    public Object getSetting(String setting) {

        if (setting == null) {
            return (null);
        }

        return (settings.get(setting));
    }

    public Object addSetting(String setting, Object value) {

        Setting appSetting = Setting.forValue(setting);
        if (appSetting != null) {
            return (addSetting(appSetting, value));
        }

        settings.put(setting, value);
        return (value);
    }

    public Object addSetting(Setting setting, Object value) {

        if (value instanceof JsonNode) {
            value = jsonNodeToValue((JsonNode) value, setting);
        }

        setting.validate(value);
        settings.put(setting.toString(), value);
        return (value);
    }

    public Object removeSetting(Setting setting) {
        return settings.remove(setting.toString());
    }

    public Object removeSetting(String setting) {
        return settings.remove(setting);
    }

    public void clearSettings() {
        settings.clear();
    }

    private Object jsonNodeToValue(JsonNode node, Setting setting) {

        Object value = node;
        if (node instanceof NullNode) {
            value = null;
        } else if (node instanceof TextNode) {
            value = node.asText();
        } else if (node instanceof BooleanNode) {
            value = node.asBoolean();
        } else if (node instanceof IntNode) {
            value = node.asInt();
        } else if (node instanceof FloatNode) {
            value = (float) ((FloatNode) node).asDouble();
        } else if (node instanceof DoubleNode) {
            value = (float) ((DoubleNode) node).asDouble();
        } else if (node instanceof ArrayNode) {
            if (node.isEmpty()) {
                value = setting.emptyArrayValue();
            } else {
                List<Object> values = new ArrayList<>(node.size());
                node.forEach(element -> values.add(jsonNodeToValue(element, setting)));
                Class<?> type = values.get(0).getClass();
                value = Array.newInstance(type, values.size());
                for (int i = 0; i < values.size(); i++) {
                    Array.set(value, i, type.cast(values.get(i)));
                }
            }
        } else if (node instanceof ObjectNode) {
            ObjectMapper mapper = new ObjectMapper();
            value = mapper.convertValue(node, HashMap.class);
        }

        return (value);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
