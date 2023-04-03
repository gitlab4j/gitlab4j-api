package org.gitlab4j.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import org.gitlab4j.api.utils.JacksonJson;

public class JsonUtils {

  private static final JacksonJson jacksonJson;

  static {
    jacksonJson = new JacksonJson();
    jacksonJson.getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    jacksonJson.getObjectMapper().configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
  }

  static JsonNode readTreeFromMap(final Map<String, Object> map)
      throws JsonParseException, JsonMappingException, IOException {
    final String jsonString = jacksonJson.getObjectMapper().writeValueAsString(map);
    return (jacksonJson.readTree(jsonString));
  }

  static JsonNode readTreeFromString(final String jsonString)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.readTree(jsonString));
  }

  static JsonNode readTreeFromResource(final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final InputStreamReader reader =
        new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
    return (jacksonJson.readTree(reader));
  }

  static <T> T unmarshalResource(final Class<T> returnType, final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final InputStreamReader reader =
        new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
    return (jacksonJson.unmarshal(returnType, reader));
  }

  static <T> List<T> unmarshalResourceList(final Class<T> returnType, final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final InputStreamReader reader =
        new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
    return (JsonUtils.unmarshalList(returnType, reader));
  }

  static <T> Map<String, T> unmarshalResourceMap(final Class<T> returnType, final String filename)
      throws JsonParseException, JsonMappingException, IOException {
    final InputStreamReader reader =
        new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
    return (jacksonJson.unmarshalMap(returnType, reader));
  }

  static <T> T unmarshal(final Class<T> returnType, final Reader reader)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshal(returnType, reader));
  }

  static <T> T unmarshal(final Class<T> returnType, final JsonNode tree)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshal(returnType, tree));
  }

  public static <T> T unmarshal(final Class<T> returnType, final String json)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshal(returnType, json));
  }

  static <T> List<T> unmarshalList(final Class<T> returnType, final Reader reader)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshalList(returnType, reader));
  }

  static <T> List<T> unmarshalList(final Class<T> returnType, final String json)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshalList(returnType, json));
  }

  static <T> Map<String, T> unmarshalMap(final Class<T> returnType, final Reader reader)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshalMap(returnType, reader));
  }

  static <T> Map<String, T> unmarshalMap(final Class<T> returnType, final String json)
      throws JsonParseException, JsonMappingException, IOException {
    return (jacksonJson.unmarshalMap(returnType, json));
  }

  static <T> boolean compareJson(final T apiObject, final String filename) throws IOException {
    final InputStreamReader reader =
        new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
    return (compareJson(apiObject, reader));
  }

  static <T> boolean compareJson(final T apiObject, final InputStreamReader reader) throws IOException {

    final String objectJson = jacksonJson.marshal(apiObject);
    final JsonNode tree1 = jacksonJson.getObjectMapper().readTree(objectJson.getBytes());
    final JsonNode tree2 = jacksonJson.getObjectMapper().readTree(reader);

    final boolean sameJson = tree1.equals(tree2);
    if (!sameJson) {
      System.err.println("JSON did not match:");
      sortedDump(tree1);
      sortedDump(tree2);
    }

    return (sameJson);
  }

  static <T> boolean compareJson(final T apiObject, final T apiObject1) throws IOException {

    final String objectJson = jacksonJson.marshal(apiObject);
    final String object1Json = jacksonJson.marshal(apiObject1);
    final JsonNode tree1 = jacksonJson.getObjectMapper().readTree(objectJson.getBytes());
    final JsonNode tree2 = jacksonJson.getObjectMapper().readTree(object1Json.getBytes());

    final boolean sameJson = tree1.equals(tree2);
    if (!sameJson) {
      System.err.println("JSON did not match:");
      sortedDump(tree1);
      sortedDump(tree2);
    }

    return (sameJson);
  }

  static void sortedDump(final JsonNode node) throws JsonProcessingException {
    System.err.println(sortedJsonString(node));
    System.err.flush();
  }

  static String sortedJsonString(final JsonNode node) throws JsonProcessingException {
    final Object obj = jacksonJson.getObjectMapper().treeToValue(node, Object.class);
    return (jacksonJson.getObjectMapper().writeValueAsString(obj));
  }

  public static String readResource(final String filename) throws IOException {

    final InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(filename));
    final StringBuilder stringBuilder = new StringBuilder();

    try (final BufferedReader br = new BufferedReader(reader)) {
      String line;
      while ((line = br.readLine()) != null) {
        stringBuilder.append(line).append("\n");
      }
    }

    return (stringBuilder.toString());
  }
}
