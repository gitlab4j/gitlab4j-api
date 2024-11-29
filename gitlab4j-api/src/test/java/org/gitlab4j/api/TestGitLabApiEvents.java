package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.gitlab4j.api.JsonUtils.readTreeFromResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.logging.Level;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;

import org.gitlab4j.api.systemhooks.MergeRequestSystemHookEvent;
import org.gitlab4j.api.systemhooks.SystemHookEvent;
import org.gitlab4j.api.systemhooks.SystemHookListener;
import org.gitlab4j.api.utils.JacksonJson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestGitLabApiEvents {

    private static JacksonJson jacksonJson;
    private static Level savedLevel;

    public TestGitLabApiEvents() {
        super();
    }

    @BeforeAll
    public static void setup() throws Exception {
        jacksonJson = new JacksonJson();
        jacksonJson.getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        savedLevel = GitLabApi.getLogger().getLevel();
    }

    @AfterAll
    public static void teardown() {
        GitLabApi.getLogger().setLevel(savedLevel);
    }

    @Test
    public void testSystemHookManagerHandleEvent() throws Exception {

        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        given(request.getHeader("X-Gitlab-Event")).willReturn(SystemHookManager.SYSTEM_HOOK_EVENT);

        JsonNode tree = readTreeFromResource("merge-request-system-hook-event.json");
        ((ObjectNode) tree).remove("event_name");
        String json = jacksonJson.getObjectMapper().writeValueAsString(tree);
        ServletInputStream servletInputStream = new MockServletInputStream(json);
        given(request.getInputStream()).willReturn(servletInputStream);

        SystemHookManager systemHookMgr = new SystemHookManager();
        final SystemHookEvent receivedEvents[] = new SystemHookEvent[1];
        systemHookMgr.addListener(new SystemHookListener() {
            public void onMergeRequestEvent(MergeRequestSystemHookEvent event) {
                receivedEvents[0] = event;
            }
        });

        // Act
        systemHookMgr.handleEvent(request);

        // Assert
        assertNotNull(receivedEvents[0]);
        assertEquals(MergeRequestSystemHookEvent.class, receivedEvents[0].getClass());
        assertTrue(compareJson(receivedEvents[0], "merge-request-system-hook-event.json"));
    }
}
