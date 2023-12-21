package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gitlab4j.api.utils.JacksonJson;
import org.junit.jupiter.api.Assertions;

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

    static JsonNode readTreeFromMap(Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException {
        String jsonString = jacksonJson.getObjectMapper().writeValueAsString(map);
        return (jacksonJson.readTree(jsonString));
    }
    
    static JsonNode readTreeFromString(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        return (jacksonJson.readTree(jsonString));
    }

    static JsonNode readTreeFromResource(String filename) throws JsonParseException, JsonMappingException, IOException {
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (jacksonJson.readTree(reader));
    }

    static <T> T unmarshalResource(Class<T> returnType, String filename) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (jacksonJson.unmarshal(returnType, reader));
    }

    static <T> List<T> unmarshalResourceList(Class<T> returnType, String filename) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (JsonUtils.unmarshalList(returnType, reader));
    }

    static <T> Map<String, T> unmarshalResourceMap(Class<T> returnType, String filename) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(filename));
        return (jacksonJson.unmarshalMap(returnType, reader));
    }

    static <T> T unmarshal(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshal(returnType, reader));
    }

    static <T> T unmarshal(Class<T> returnType, JsonNode tree) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshal(returnType, tree));
    }

    static <T> T unmarshal(Class<T> returnType, String json) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshal(returnType, json));
    }

    static <T> List<T> unmarshalList(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshalList(returnType, reader));
    }

    static <T> List<T> unmarshalList(Class<T> returnType, String json) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshalList(returnType, json));
    }

    static <T> Map<String, T> unmarshalMap(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshalMap(returnType, reader));
    }

    static <T> Map<String, T> unmarshalMap(Class<T> returnType, String json) throws JsonParseException, JsonMappingException, IOException {
        checkSerializable(returnType);
        return (jacksonJson.unmarshalMap(returnType, json));
    }

    static <T> void checkSerializable(Class<T> cls) {
        if(!isSerializable(cls, new HashSet<>())) {
            fail("Class " + cls.getCanonicalName() + " or one of its member does not implement Serializable");
        }
    }
    static <T> boolean isSerializable(Class<T> cls, Set<Class<?>> checkedTypes) {
        if (checkedTypes.contains(cls)) {
            return true;
        }
        checkedTypes.add(cls);

        if (!Serializable.class.isAssignableFrom(cls)) {
            return false;
        }

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())) {
                Class<?> fieldClass = field.getType();
                if (!isSimpleType(fieldClass) && !isSerializable(fieldClass, checkedTypes) && !isCollectionSerializable(field, checkedTypes)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSimpleType(Class<?> type) {
        return type.isPrimitive() ||
               type.equals(String.class) ||
               type.equals(Integer.class) ||
               type.equals(Long.class) ||
               type.equals(Double.class) ||
               type.equals(Float.class) ||
               type.equals(Boolean.class) ||
               type.equals(Character.class) ||
               type.equals(Byte.class) ||
               type.equals(Short.class);
    }
    
    private static boolean isCollectionSerializable(Field field, Set<Class<?>> checkedTypes) {
        Class<?> fieldType = field.getType();
        if (Collection.class.isAssignableFrom(fieldType) || Map.class.isAssignableFrom(fieldType)) {
            Type genericType = field.getGenericType();

            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();

                for (Type typeArg : typeArguments) {
                    if (typeArg instanceof Class) {
                        Class<?> typeClass = (Class<?>) typeArg;
                        if (!isSimpleType(typeClass) && !isSerializable(typeClass, checkedTypes)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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
        System.err.println(sortedJsonString(node));
        System.err.flush();
    }

    static String sortedJsonString(final JsonNode node) throws JsonProcessingException {
        final Object obj = jacksonJson.getObjectMapper().treeToValue(node, Object.class);
        return (jacksonJson.getObjectMapper().writeValueAsString(obj));
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
