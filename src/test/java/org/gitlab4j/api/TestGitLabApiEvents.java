package org.gitlab4j.api;


import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.gitlab4j.api.JsonUtils.readTreeFromResource;
import static org.gitlab4j.api.JsonUtils.unmarshalResource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.logging.Level;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.gitlab4j.api.systemhooks.MergeRequestSystemHookEvent;
import org.gitlab4j.api.systemhooks.ProjectSystemHookEvent;
import org.gitlab4j.api.systemhooks.PushSystemHookEvent;
import org.gitlab4j.api.systemhooks.SystemHookEvent;
import org.gitlab4j.api.systemhooks.SystemHookListener;
import org.gitlab4j.api.systemhooks.SystemHookManager;
import org.gitlab4j.api.systemhooks.TeamMemberSystemHookEvent;
import org.gitlab4j.api.utils.JacksonJson;
import org.gitlab4j.api.webhook.BuildEvent;
import org.gitlab4j.api.webhook.ChangeContainer;
import org.gitlab4j.api.webhook.Event;
import org.gitlab4j.api.webhook.IssueEvent;
import org.gitlab4j.api.webhook.JobEvent;
import org.gitlab4j.api.webhook.MergeRequestEvent;
import org.gitlab4j.api.webhook.NoteEvent;
import org.gitlab4j.api.webhook.PipelineEvent;
import org.gitlab4j.api.webhook.PushEvent;
import org.gitlab4j.api.webhook.TagPushEvent;
import org.gitlab4j.api.webhook.WikiPageEvent;
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
    public void testIssueEvent() throws Exception {

        IssueEvent issueEvent = unmarshalResource(IssueEvent.class, "issue-event.json");
        assertTrue(compareJson(issueEvent, "issue-event.json"));

        ChangeContainer<Integer> idChange = issueEvent.getChanges().get("id");
        assertNotNull(idChange);
        assertEquals(123, (int)idChange.getPrevious());
        assertEquals(456, (int)idChange.getCurrent());
    }

    @Test
    public void testIssueChanges() throws Exception {

        IssueEvent issueEvent = unmarshalResource(IssueEvent.class, "issue-event.json");
        assertNotNull(issueEvent);

        ChangeContainer<Integer> idChange = issueEvent.getChanges().get("id");
        assertNotNull(idChange);
        assertEquals(123, (int)idChange.getPrevious());
        assertEquals(456, (int)idChange.getCurrent());

        ChangeContainer<Boolean> confidentialChange = issueEvent.getChanges().getConfidential();
        assertNotNull(confidentialChange);
        assertFalse(confidentialChange.getPrevious());
        assertTrue(confidentialChange.getCurrent());
    }

    @Test
    public void testMergeRequestEvent() throws Exception {

        MergeRequestEvent mergeRequestEvent = unmarshalResource(MergeRequestEvent.class, "merge-request-event.json");
        assertTrue(compareJson(mergeRequestEvent, "merge-request-event.json"));
    }

    @Test
    public void testMergeRequestEventChanges() throws Exception {

        MergeRequestEvent mergeRequestEvent = unmarshalResource(MergeRequestEvent.class, "merge-request-event.json");
        assertNotNull(mergeRequestEvent);

        ChangeContainer<Integer> iidChange = mergeRequestEvent.getChanges().get("iid");
        assertNotNull(iidChange);
        assertEquals(12, (int)iidChange.getPrevious());
        assertEquals(34, (int)iidChange.getCurrent());

        ChangeContainer<String> mergeStatusChangeChange = mergeRequestEvent.getChanges().getMergeStatus();
        assertNotNull(mergeStatusChangeChange);
        assertNull(mergeStatusChangeChange.getPrevious());
        assertEquals("unchecked", mergeStatusChangeChange.getCurrent());
    }

    @Test
    public void testPipelineEvent() throws Exception {
        Event event = unmarshalResource(PipelineEvent.class, "pipeline-event.json");
        assertTrue(compareJson(event, "pipeline-event.json"));
    }

    @Test
    public void testPushEvent() throws Exception {

        Event pushEvent = unmarshalResource(PushEvent.class, "push-event.json");
        assertTrue(compareJson(pushEvent, "push-event.json"));
    }

    @Test
    public void testTagPushEvent() throws Exception {

        Event pushEvent = unmarshalResource(TagPushEvent.class, "tag-push-event.json");
        assertTrue(compareJson(pushEvent, "tag-push-event.json"));
    }

    @Test
    public void testNoteCommitEvent() throws Exception {

        Event noteEvent = unmarshalResource(NoteEvent.class, "note-commit-event.json");
        assertTrue(compareJson(noteEvent, "note-commit-event.json"));
    }

    @Test
    public void testNoteMergeRequestEvent() throws Exception {

        Event noteEvent = unmarshalResource(NoteEvent.class, "note-merge-request-event.json");
        assertTrue(compareJson(noteEvent, "note-merge-request-event.json"));
    }

    @Test
    public void testNoteIssueEvent() throws Exception {

        Event noteEvent = unmarshalResource(NoteEvent.class, "note-issue-event.json");
        assertTrue(compareJson(noteEvent, "note-issue-event.json"));
    }

    @Test
    public void testNoteSnippetEvent() throws Exception {

        Event noteEvent = unmarshalResource(NoteEvent.class, "note-snippet-event.json");
        assertTrue(compareJson(noteEvent, "note-snippet-event.json"));
    }

    @Test
    public void testBuildEvent() throws Exception {

        Event event = unmarshalResource(BuildEvent.class, "build-event.json");
        assertTrue(compareJson(event, "build-event.json"));
    }

    @Test
    public void testJobEvent() throws Exception {

        Event event = unmarshalResource(JobEvent.class, "job-event.json");
        assertTrue(compareJson(event, "job-event.json"));
    }

    @Test
    public void testWikiPageEvent() throws Exception {

        Event event = unmarshalResource(WikiPageEvent.class, "wiki-page-event.json");
        assertTrue(compareJson(event, "wiki-page-event.json"));
    }

    @Test
    public void testPolymorphicEvent() throws Exception {

        Event event = unmarshalResource(Event.class, "build-event.json");
        assertTrue(compareJson(event, "build-event.json"));

        event = unmarshalResource(Event.class, "issue-event.json");
        assertTrue(compareJson(event, "issue-event.json"));

        event = unmarshalResource(Event.class, "job-event.json");
        assertTrue(compareJson(event, "job-event.json"));

        event = unmarshalResource(Event.class, "merge-request-event.json");
        assertTrue(compareJson(event, "merge-request-event.json"));

        event = unmarshalResource(Event.class, "note-commit-event.json");
        assertTrue(compareJson(event, "note-commit-event.json"));

        event = unmarshalResource(Event.class, "note-issue-event.json");
        assertTrue(compareJson(event, "note-issue-event.json"));

        event = unmarshalResource(Event.class, "note-merge-request-event.json");
        assertTrue(compareJson(event, "note-merge-request-event.json"));

        event = unmarshalResource(Event.class, "note-snippet-event.json");
        assertTrue(compareJson(event, "note-snippet-event.json"));

        event = unmarshalResource(Event.class, "pipeline-event.json");
        assertTrue(compareJson(event, "pipeline-event.json"));

        event = unmarshalResource(Event.class, "push-event.json");
        assertTrue(compareJson(event, "push-event.json"));

        event = unmarshalResource(Event.class, "tag-push-event.json");
        assertTrue(compareJson(event, "tag-push-event.json"));

        event = unmarshalResource(Event.class, "wiki-page-event.json");
        assertTrue(compareJson(event, "wiki-page-event.json"));
    }

    @Test
    public void testProjectSystemHookEvent() throws Exception {
        ProjectSystemHookEvent event = unmarshalResource(ProjectSystemHookEvent.class,
                "project-system-hook-event.json");
        assertTrue(compareJson(event, "project-system-hook-event.json"));
    }

    @Test
    public void testTeamMemberSystemHookEvent() throws Exception {
        TeamMemberSystemHookEvent event = unmarshalResource(TeamMemberSystemHookEvent.class,
                "team-member-system-hook-event.json");
        assertTrue(compareJson(event, "team-member-system-hook-event.json"));
    }

    @Test
    public void testPushSystemHookEvent() throws Exception {
        PushSystemHookEvent event = unmarshalResource(PushSystemHookEvent.class, "push-system-hook-event.json");
        assertTrue(compareJson(event, "push-system-hook-event.json"));
    }

    @Test
    public void testUserSystemHookEvent() throws Exception {
        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "user-system-hook-event.json");
        assertTrue(compareJson(event, "user-system-hook-event.json"));
    }

    @Test
    public void testUserFailedLoginSystemHookEvent() throws Exception {

        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "user-failed-login-system-hook-event.json");
        assertTrue(compareJson(event, "user-failed-login-system-hook-event.json"));
    }

    @Test
    public void testGroupSystemHookEvent() throws Exception {
        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "group-system-hook-event.json");
        assertTrue(compareJson(event, "group-system-hook-event.json"));
    }

    @Test
    public void testGroupMemberSystemHookEvent() throws Exception {
        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "group-member-system-hook-event.json");
        assertTrue(compareJson(event, "group-member-system-hook-event.json"));
    }

    @Test
    public void testTagPushSystemHookEvent() throws Exception {
        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "tag-push-system-hook-event.json");
        assertTrue(compareJson(event, "tag-push-system-hook-event.json"));
    }

    @Test
    public void testRepositorySystemHookEvent() throws Exception {
        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "repository-system-hook-event.json");
        assertTrue(compareJson(event, "repository-system-hook-event.json"));
    }

    @Test
    public void testMergeRequestSystemHookEvent() throws Exception {
        MergeRequestSystemHookEvent mergeRequestEvent = unmarshalResource(MergeRequestSystemHookEvent.class, "merge-request-system-hook-event.json");
        assertTrue(compareJson(mergeRequestEvent, "merge-request-system-hook-event.json"));

        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "merge-request-system-hook-event.json");
        assertTrue(compareJson(event, "merge-request-system-hook-event.json"));
    }

    @Test
    public void testPolymorphicSystemHookEvent() throws Exception {

        SystemHookEvent event = unmarshalResource(SystemHookEvent.class, "project-system-hook-event.json");
        assertTrue(compareJson(event, "project-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "team-member-system-hook-event.json");
        assertTrue(compareJson(event, "team-member-system-hook-event.json"));

        event = unmarshalResource(PushSystemHookEvent.class, "push-system-hook-event.json");
        assertTrue(compareJson(event, "push-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "user-system-hook-event.json");
        assertTrue(compareJson(event, "user-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "user-failed-login-system-hook-event.json");
        assertTrue(compareJson(event, "user-failed-login-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "group-system-hook-event.json");
        assertTrue(compareJson(event, "group-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "group-member-system-hook-event.json");
        assertTrue(compareJson(event, "group-member-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "tag-push-system-hook-event.json");
        assertTrue(compareJson(event, "tag-push-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "repository-system-hook-event.json");
        assertTrue(compareJson(event, "repository-system-hook-event.json"));

        event = unmarshalResource(SystemHookEvent.class, "merge-request-system-hook-event.json");
        assertTrue(compareJson(event, "merge-request-system-hook-event.json"));
    }

    @Test
    public void testSystemHookManagerHandleEvent() throws Exception {

        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        given(request.getHeader("X-Gitlab-Event")).willReturn(SystemHookManager.SYSTEM_HOOK_EVENT);

        JsonNode tree = readTreeFromResource("merge-request-system-hook-event.json");
        ((ObjectNode)tree).remove("event_name");
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
