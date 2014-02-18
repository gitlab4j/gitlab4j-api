package com.messners.gitlab.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import com.messners.gitlab.api.event.EventObject;
import com.messners.gitlab.api.event.PushEvent;

public class TestGitLabApiEvents {
	
	private static JacksonJsonConfig jacksonJsonConfig;
	
	public TestGitLabApiEvents () {
		super();
	}
	
	@BeforeClass
	public static void setup () {
		jacksonJsonConfig = new JacksonJsonConfig();
	}
	
	@Test
	public void testIssueEvent () {

		 try {
			 EventObject issueEvent = makeFakeApiCall(EventObject.class, "issue-event");
			 assertTrue(issueEvent != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMergeRequestEvent () {

		 try {
			 EventObject mergeRequestEvent = makeFakeApiCall(EventObject.class, "merge-request-event");
			 assertTrue(mergeRequestEvent != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testPushEvent () {

		 try {
			 PushEvent pushEvent = makeFakeApiCall(PushEvent.class, "push-event");
			 assertTrue(pushEvent != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}	
	
	private <T> T makeFakeApiCall (Class<T> returnType, String file) throws JsonParseException, JsonMappingException, IOException {
			
		InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(file + ".json"));
		ObjectMapper objectMapper = jacksonJsonConfig.getContext(returnType);
		return (objectMapper.readValue(reader,  returnType));	
	}
}
