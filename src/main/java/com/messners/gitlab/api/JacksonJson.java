
package com.messners.gitlab.api;

import java.io.IOException;
import java.io.Reader;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/** 
 * Jackson JSON Configuration and utility class.
 *
 * @author Greg Messner <greg@messners.com>
 *
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJson extends JacksonJaxbJsonProvider implements ContextResolver<ObjectMapper> {
		
	private final ObjectMapper objectMapper;

    public JacksonJson () {

		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, Boolean.TRUE);
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, Boolean.TRUE);		
	}
	   
    /**
     * 
     */
    @Override
    public ObjectMapper getContext (Class<?> objectType) {
        return (objectMapper);
    }
    
    
    /**
     * 
     * @return
     */
    public ObjectMapper getObjectMapper () {
    	return (objectMapper);
    }
    
    /**
     * 
     * @param returnType
     * @param reader
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
	public <T> T unmarshal (Class<T> returnType, Reader reader) 
			throws JsonParseException, JsonMappingException, IOException {		
		ObjectMapper objectMapper = getContext(returnType);
		return (objectMapper.readValue(reader,  returnType));	
	}
		
	/**
	 * 
	 * @param returnType
	 * @param postData
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> T unmarshal (Class<T> returnType, String postData) 
			throws JsonParseException, JsonMappingException, IOException {		
		ObjectMapper objectMapper = getContext(returnType);
		return (objectMapper.readValue(postData,  returnType));	
	}
	
	
	/**
	 * Marshals the supplied object out as a formatted JSON string.
	 * 
	 * @param object
	 * @return
	 */
	public <T> String marshal (final T object) {
		
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
}