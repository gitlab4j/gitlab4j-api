
package org.gitlab4j.api.utils;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.gitlab4j.api.models.User;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

/**
 * Jackson JSON Configuration and utility class.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJson extends JacksonJaxbJsonProvider implements ContextResolver<ObjectMapper> {

    private static final SimpleDateFormat iso8601UtcFormat;
    static {
        iso8601UtcFormat = new SimpleDateFormat(ISO8601.UTC_PATTERN);
        iso8601UtcFormat.setLenient(true);
        iso8601UtcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private final ObjectMapper objectMapper;

    public JacksonJson() {

        objectMapper = new ObjectMapper();

        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        SimpleModule module = new SimpleModule("GitLabApiJsonModule");
        module.addSerializer(Date.class, new JsonDateSerializer());
        module.addDeserializer(Date.class, new JsonDateDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public ObjectMapper getContext(Class<?> objectType) {
        return (objectMapper);
    }

    /**
     * Gets the ObjectMapper contained by this instance.
     * 
     * @return the ObjectMapper contained by this instance
     */
    public ObjectMapper getObjectMapper() {
        return (objectMapper);
    }

    /**
     * Unmarshal the JSON data on the specified Reader instance to an instance of the provided class.
     * 
     * @param <T> the generics type for the return value
     * @param returnType an instance of this type class will be returned
     * @param reader the Reader instance that contains the JSON data
     * @return an instance of the provided class containing the parsed data from the Reader
     * @throws JsonParseException when an error occurs paresing the provided JSON
     * @throws JsonMappingException if a JSON error occurs
     * @throws IOException if an error occurs reading the JSON data
     */
    public <T> T unmarshal(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = getContext(returnType);
        return (objectMapper.readValue(reader, returnType));
    }

    /**
     * Unmarshal the JSON data contained by the string and populate an instance of the provided returnType class.
     * 
     * @param <T> the generics type for the return value
     * @param returnType an instance of this type class will be returned
     * @param postData a String holding the POST data
     * @return an instance of the provided class containing the parsed data from the string
     * @throws JsonParseException when an error occurs paresing the provided JSON
     * @throws JsonMappingException if a JSON error occurs
     * @throws IOException if an error occurs reading the JSON data
     */
    public <T> T unmarshal(Class<T> returnType, String postData) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = getContext(returnType);
        return (objectMapper.readValue(postData, returnType));
    }

    /**
     * Marshals the supplied object out as a formatted JSON string.
     * 
     * @param <T> the generics type for the provided object
     * @param object the object to output as a JSON string
     * @return a String containing the JSON for the specified object
     */
    public <T> String marshal(final T object) {

        if (object == null) {
            throw new IllegalArgumentException("object parameter is null");
        }

        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        String results = null;
        try {
            results = writer.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            System.err.println("JsonGenerationException, message=" + e.getMessage());
        } catch (JsonMappingException e) {
            e.printStackTrace();
            System.err.println("JsonMappingException, message=" + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException, message=" + e.getMessage());
        }

        return (results);
    }

    /**
     * JsonSerializer for serializing ISO8601 formatted dates.
     */
    public static class JsonDateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
            String iso8601String = ISO8601.toString(date);
            gen.writeString(iso8601String);
        }
    }

    /**
     * JsonDeserializer for deserializing ISO8601 formatted dates.
     */
    public static class JsonDateDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException, JsonProcessingException {

            try {
                return (ISO8601.toDate(jsonparser.getText()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Serializer for the odd User instances in the "approved_by" array in the merge_request JSON.
     */
    public static class UserListSerializer extends JsonSerializer<List<User>> {

        @Override
        public void serialize(List<User> value, JsonGenerator jgen,
                SerializerProvider provider) throws IOException,
                JsonProcessingException {

            jgen.writeStartArray();
            for (User user : value) {
                jgen.writeStartObject();
                jgen.writeObjectField("user", user);
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
        }
    }

    /**
     * Deserializer for the odd User instances in the "approved_by" array in the merge_request JSON.
     */
    public static class UserListDeserializer extends JsonDeserializer<List<User>> {

        private static final ObjectMapper mapper = new JacksonJson().getObjectMapper();

        @Override
        public List<User> deserialize(JsonParser jsonParser, DeserializationContext context)
                throws IOException, JsonProcessingException {

            JsonNode tree = jsonParser.readValueAsTree();
            int numUsers = tree.size();
            List<User> users = new ArrayList<>(numUsers);
            for (int i = 0; i < numUsers; i++) {
                JsonNode node = tree.get(i);
                JsonNode userNode = node.get("user");
                User user = mapper.treeToValue(userNode,  User.class);
                users.add(user);
            }

            return (users);
        }
    }

    /**
     * This class is used to create a thread-safe singleton instance of JacksonJson customized
     * to be used by
     */
    private static class JacksonJsonSingletonHelper {
        private static final JacksonJson JACKSON_JSON = new JacksonJson();
        static {
            JACKSON_JSON.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
            JACKSON_JSON.objectMapper.setSerializationInclusion(Include.ALWAYS);
        }
    }

    /**
     * Gets a the supplied object output as a formatted JSON string.  Null properties will
     * result in the value of the property being null.  This is meant to be used for
     * toString() implementations of GitLab4J classes.
     *
     * @param <T> the generics type for the provided object
     * @param object the object to output as a JSON string
     * @return a String containing the JSON for the specified object
     */
    public static <T> String toJsonString(final T object) {
        return (JacksonJsonSingletonHelper.JACKSON_JSON.marshal(object));
    }

    /**
     * Parse the provided String into a JsonNode instance.
     *
     * @param jsonString a String containing JSON to parse
     * @return a JsonNode with the String parsed into a JSON tree
     * @throws IOException if any IO error occurs
     */
    public static JsonNode toJsonNode(String jsonString) throws IOException {
        return (JacksonJsonSingletonHelper.JACKSON_JSON.objectMapper.readTree(jsonString));
    }
}
