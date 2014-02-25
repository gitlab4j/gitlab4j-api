
package com.messners.gitlab.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/** 
 * Jackson JSON Configuration class
 *
 * @author Greg Messner <greg@messners.com>
 *
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JacksonJsonConfig implements ContextResolver<ObjectMapper> {
		
	private final ObjectMapper objectMapper;

    public JacksonJsonConfig () {

		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, Boolean.TRUE);
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, Boolean.TRUE);		
	}
	    
    @Override
    public ObjectMapper getContext (Class<?> objectType) {
        return (objectMapper);
    }
    
    
    public ObjectMapper getObjectMapper () {
    	return (objectMapper);
    }
}