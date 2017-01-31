package com.messners.gitlab.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.messners.gitlab.api.webhook.EventObject;
import com.messners.gitlab.api.webhook.PushEvent;

public class TestGitLabApiEvents {

    private static JacksonJson jacksonJson;

    public TestGitLabApiEvents() {
        super();
    }

    @BeforeClass
    public static void setup() {
        jacksonJson = new JacksonJson();
    }

    @Test
    public void testIssueEvent() {

        try {
            EventObject issueEvent = makeFakeApiCall(EventObject.class, "issue-event");
            assertTrue(compareJson(issueEvent, "issue-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestEvent() {

        try {
            EventObject mergeRequestEvent = makeFakeApiCall(EventObject.class, "merge-request-event");
            assertTrue(compareJson(mergeRequestEvent, "merge-request-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPushEvent() {

        try {
            PushEvent pushEvent = makeFakeApiCall(PushEvent.class, "push-event");
            assertTrue(compareJson(pushEvent, "push-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> T makeFakeApiCall(Class<T> returnType, String file) throws JsonParseException, JsonMappingException, IOException {

        InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(file + ".json"));
        ObjectMapper objectMapper = jacksonJson.getContext(returnType);
        return (objectMapper.readValue(reader, returnType));
    }

    private <T> boolean compareJson(T apiObject, String file) throws IOException {

        InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(file + ".json"));
        String objectJson = jacksonJson.marshal(apiObject);
        JsonNode tree1 = jacksonJson.getObjectMapper().readTree(objectJson.getBytes());
        JsonNode tree2 = jacksonJson.getObjectMapper().readTree(reader);
        boolean sameJson = tree1.equals(tree2);
        return (sameJson);
    }
}
