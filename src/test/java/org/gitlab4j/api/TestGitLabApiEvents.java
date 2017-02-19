package org.gitlab4j.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.JacksonJson;
import org.gitlab4j.api.webhook.Event;
import org.gitlab4j.api.webhook.IssueEvent;
import org.gitlab4j.api.webhook.MergeRequestEvent;
import org.gitlab4j.api.webhook.PushEvent;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestGitLabApiEvents {

    private static JacksonJson jacksonJson;

    public TestGitLabApiEvents() {
        super();
    }

    @BeforeClass
    public static void setup() {
        jacksonJson = new JacksonJson();
        jacksonJson.getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    @Test
    public void testIssueEvent() {

        try {
            Event issueEvent = makeFakeApiCall(IssueEvent.class, "issue-event");
            assertTrue(compareJson(issueEvent, "issue-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestEvent() {

        try {
            Event mergeRequestEvent = makeFakeApiCall(MergeRequestEvent.class, "merge-request-event");
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
        if (!sameJson) {
            System.out.println("JSON did not match:");
            sortedDump(tree1);
            sortedDump(tree2);
        }
        return (sameJson);
    }
  
    private void sortedDump(final JsonNode node) throws JsonProcessingException {        
        final Object obj = jacksonJson.getObjectMapper().treeToValue(node, Object.class);
        System.out.println(jacksonJson.getObjectMapper().writeValueAsString(obj));
    }
}
