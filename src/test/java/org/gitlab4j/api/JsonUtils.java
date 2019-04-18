package org.gitlab4j.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {
    
    private static JacksonJson jacksonJson;
    static {
        jacksonJson = new JacksonJson();
        jacksonJson.getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        jacksonJson.getObjectMapper().configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
    }

    static JsonNode readTreeFromResource(String filename) throws JsonParseException, JsonMappingException, IOException {
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (jacksonJson.readTree(reader));
    }

    static <T> T unmarshalResource(Class<T> returnType, String filename) throws JsonParseException, JsonMappingException, IOException {
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (jacksonJson.unmarshal(returnType, reader));
    }

    static <T> List<T> unmarshalResourceList(Class<T> returnType,  String filename) throws JsonParseException, JsonMappingException, IOException {
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (JsonUtils.unmarshalList(returnType, reader));
    }

    static <T> Map<String, T> unmarshalResourceMap(Class<T> returnType, String filename) throws JsonParseException, JsonMappingException, IOException {
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (jacksonJson.unmarshalMap(returnType, reader));
    }

    static <T> T unmarshal(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshal(returnType, reader));
    }

    static <T> T unmarshal(Class<T> returnType, JsonNode tree) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshal(returnType, tree));
    }

    static <T> T unmarshal(Class<T> returnType, String json) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshal(returnType, json));
    }

    static <T> List<T> unmarshalList(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshalList(returnType, reader));
    }

    static <T> List<T> unmarshalList(Class<T> returnType, String json) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshalList(returnType, json));
    }

    static <T> Map<String, T> unmarshalMap(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshalMap(returnType, reader));
    }

    static <T> Map<String, T> unmarshalMap(Class<T> returnType, String json) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.unmarshalMap(returnType, json));
    }


    static <T> boolean compareJson(T apiObject, String filename) throws IOException {
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (compareJson(apiObject, reader));
    }

    static <T> boolean compareJson(T apiObject, InputStreamReader reader) throws IOException {

        String objectJson = jacksonJson.marshal(apiObject);
        JsonNode tree1 = jacksonJson.getObjectMapper().readTree(objectJson.getBytes());
        JsonNode tree2 = jacksonJson.getObjectMapper().readTree(reader);

        boolean sameJson = tree1.equals(tree2);
        if (!sameJson) {
            System.err.println("JSON did not match:");
            sortedDump(tree1);
            sortedDump(tree2);
        }

        return (sameJson);
    }

    static <T> boolean compareJson(T apiObject, T apiObject1) throws IOException {

        String objectJson = jacksonJson.marshal(apiObject);
        String object1Json = jacksonJson.marshal(apiObject1);
        JsonNode tree1 = jacksonJson.getObjectMapper().readTree(objectJson.getBytes());
        JsonNode tree2 = jacksonJson.getObjectMapper().readTree(object1Json.getBytes());

        boolean sameJson = tree1.equals(tree2);
        if (!sameJson) {
            System.err.println("JSON did not match:");
            sortedDump(tree1);
            sortedDump(tree2);
        }

        return (sameJson);
    }

    static void sortedDump(final JsonNode node) throws JsonProcessingException {
        final Object obj = jacksonJson.getObjectMapper().treeToValue(node, Object.class);
        System.err.println(jacksonJson.getObjectMapper().writeValueAsString(obj));
        System.err.flush();
    }

    static String readResource(String filename) throws IOException {

        InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(filename));
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }

        return (stringBuilder.toString());
    }
}
