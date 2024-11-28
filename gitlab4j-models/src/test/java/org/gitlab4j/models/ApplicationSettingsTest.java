package org.gitlab4j.models;

import static org.junit.jupiter.api.Assertions.*;

import org.gitlab4j.api.models.ApplicationSettings;
import org.gitlab4j.api.models.Setting;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

class ApplicationSettingsTest {
    ApplicationSettings settings = new ApplicationSettings();

    @Test
    void addSettingsStringArray() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        Object value = settings.addSetting(Setting.REPOSITORY_STORAGES, arrayNode);

        assertInstanceOf(String[].class, value);
        assertArrayEquals(new String[] {}, (String[]) value);

        arrayNode.add("value1");
        arrayNode.add("value2");

        value = settings.addSetting(Setting.REPOSITORY_STORAGES, arrayNode);
        assertInstanceOf(String[].class, value);
        assertArrayEquals(new String[] {"value1", "value2"}, (String[]) value);
    }

    @Test
    void jsonArrayNodeToValueInteger() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        Object value = settings.addSetting(Setting.ELASTICSEARCH_NAMESPACE_IDS, arrayNode);

        assertInstanceOf(Integer[].class, value);
        assertArrayEquals(new Integer[] {}, (Integer[]) value);

        arrayNode.add(1);
        arrayNode.add(2);

        value = settings.addSetting(Setting.ELASTICSEARCH_NAMESPACE_IDS, arrayNode);
        assertInstanceOf(Integer[].class, value);
        assertArrayEquals(new Integer[] {1, 2}, (Integer[]) value);
    }

    @Test
    void jsonArrayNodeToValueTypes() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        Object value = settings.addSetting(Setting.ASSET_PROXY_ALLOWLIST, arrayNode);

        assertInstanceOf(String[].class, value);
        assertArrayEquals(new String[] {}, (String[]) value);

        arrayNode.add("host1");
        arrayNode.add("host2");

        value = settings.addSetting(Setting.ASSET_PROXY_ALLOWLIST, arrayNode);
        assertInstanceOf(String[].class, value);
        assertArrayEquals(new String[] {"host1", "host2"}, (String[]) value);
    }

    @Test
    void jsonNodeToValueTypes() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.createObjectNode().textNode("");

        Object value = settings.addSetting(Setting.ASSET_PROXY_ALLOWLIST, jsonNode);

        assertInstanceOf(String.class, value);
        assertEquals("", value);

        jsonNode = mapper.createObjectNode().textNode("host1");
        value = settings.addSetting(Setting.ASSET_PROXY_ALLOWLIST, jsonNode);
        assertInstanceOf(String.class, value);
        assertEquals("host1", value);
    }
}
