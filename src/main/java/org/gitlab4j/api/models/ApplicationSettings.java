package org.gitlab4j.api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.FloatNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class ApplicationSettings implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date createdAt;
    private Date updatedAt;
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

    public Object addSetting(String setting, Object value) throws GitLabApiException {

        Setting appSetting = Setting.forValue(setting);
        if (appSetting != null) {
            return (addSetting(appSetting, value));
        }

        settings.put(setting, value);
        return (value);
    }

    public Object addSetting(Setting setting, Object value) throws GitLabApiException {

        if (value instanceof JsonNode) {
            value = jsonNodeToValue((JsonNode)value, setting);
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
            value = (float)((FloatNode)node).asDouble();
        } else if (node instanceof DoubleNode) {
            value = (float)((DoubleNode)node).asDouble();
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
