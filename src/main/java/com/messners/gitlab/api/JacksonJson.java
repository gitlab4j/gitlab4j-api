
package com.messners.gitlab.api;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
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
     * @param returnType an instance of this type class will be returned
     * @param reader the Reader instance that contains the JSON data
     * @return an instance of the provided class containing the parsed data from the Reader
     * @throws JsonParseException when an error occurs paresing the provided JSON
     * @throws JsonMappingException
     * @throws IOException
     */
    public <T> T unmarshal(Class<T> returnType, Reader reader) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = getContext(returnType);
        return (objectMapper.readValue(reader, returnType));
    }

    /**
     * Unmarshal the JSON data contained by the string and populate an instance of the provided returnType class.
     * 
     * @param returnType an instance of this type class will be returned
     * @param postData
     * @return an instance of the provided class containing the parsed data from the string
     * @throws JsonParseException when an error occurs paresing the provided JSON
     * @throws JsonMappingException
     * @throws IOException
     */
    public <T> T unmarshal(Class<T> returnType, String postData) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = getContext(returnType);
        return (objectMapper.readValue(postData, returnType));
    }

    /**
     * Marshals the supplied object out as a formatted JSON string.
     * 
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
}