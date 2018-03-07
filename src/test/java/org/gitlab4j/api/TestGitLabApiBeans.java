/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.gitlab4j.api.models.ArtifactsFile;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitPayload;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.DeployKey;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.ImpersonationToken;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Job;
import org.gitlab4j.api.models.Key;
import org.gitlab4j.api.models.Label;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Note;
import org.gitlab4j.api.models.NotificationSettings;
import org.gitlab4j.api.models.OauthTokenResponse;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.ProjectHook;
import org.gitlab4j.api.models.ProjectUser;
import org.gitlab4j.api.models.Session;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.SshKey;
import org.gitlab4j.api.models.SystemHook;
import org.gitlab4j.api.models.Tag;
import org.gitlab4j.api.models.TreeItem;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.systemhooks.ProjectSystemHookEvent;
import org.gitlab4j.api.systemhooks.PushSystemHookEvent;
import org.gitlab4j.api.systemhooks.SystemHookEvent;
import org.gitlab4j.api.systemhooks.TeamMemberSystemHookEvent;
import org.gitlab4j.api.utils.JacksonJson;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestGitLabApiBeans {

    private static JacksonJson jacksonJson;

    public TestGitLabApiBeans() {
        super();
    }

    @BeforeClass
    public static void setup() {
        jacksonJson = new JacksonJson();
        jacksonJson.getObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    @Test
    public void testBranch() {

        try {
            Branch branch = makeFakeApiCall(Branch.class, "branch");
            assertTrue(compareJson(branch, "branch"));

            branch = makeFakeApiCall(Branch.class, "bad-branch");
            assertTrue(!Branch.isValid(branch));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCommit() {

        try {
            Commit commit = makeFakeApiCall(Commit.class, "commit");
            assertTrue(compareJson(commit, "commit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommitPayload() {

        try {
            CommitPayload commitPayload = makeFakeApiCall(CommitPayload.class, "commit-payload");
            assertTrue(compareJson(commitPayload, "commit-payload"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCompareResults() {

        try {
            CompareResults compareResults = makeFakeApiCall(CompareResults.class, "compare-results");
            assertTrue(compareJson(compareResults, "compare-results"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDiff() {

        try {
            Diff diff = makeFakeApiCall(Diff.class, "diff");
            assertTrue(compareJson(diff, "diff"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testComment() {

        try {
            Comment comment = makeFakeApiCall(Comment.class, "comment");
            assertTrue(compareJson(comment, "comment"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEvent() {

        try {
            Event event = makeFakeApiCall(Event.class, "event");
            assertTrue(compareJson(event, "event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroup() {

        try {
            Group group = makeFakeApiCall(Group.class, "group");
            assertTrue(compareJson(group, "group"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIssue() {

        try {
            Issue issue = makeFakeApiCall(Issue.class, "issue");
            assertTrue(compareJson(issue, "issue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPipeline() {

        try {
            Pipeline pipeline = makeFakeApiCall(Pipeline.class, "pipeline");
            assertTrue(compareJson(pipeline, "pipeline"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJob() {

        try {
            Job job = makeFakeApiCall(Job.class, "job");
            assertTrue(compareJson(job, "job"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeployKeys() {

        try {

            InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream("deploy-keys.json"));
            ObjectMapper objectMapper = jacksonJson.getContext(null);
            List<DeployKey> deployKeys = objectMapper.readValue(reader, new TypeReference<List<DeployKey>>() {});
            assertTrue(compareJson(deployKeys, "deploy-keys"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArtifactsFile() {

        try {
            ArtifactsFile artifactFile = makeFakeApiCall(ArtifactsFile.class, "artifacts-file");
            assertTrue(compareJson(artifactFile, "artifacts-file"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectUsers() {

        try {
            InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream("project-users.json"));
            ObjectMapper objectMapper = jacksonJson.getContext(null);
            List<ProjectUser> projectUsres = objectMapper.readValue(reader, new TypeReference<List<ProjectUser>>() {});
            assertTrue(compareJson(projectUsres, "project-users"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectHook() {

        try {
            ProjectHook hook = makeFakeApiCall(ProjectHook.class, "hook");
            assertTrue(compareJson(hook, "hook"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKey() {

        try {
            Key key = makeFakeApiCall(Key.class, "key");
            assertTrue(compareJson(key, "key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMember() {

        try {
            Member member = makeFakeApiCall(Member.class, "member");
            assertTrue(compareJson(member, "member"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestApprovals() {

        try {
            MergeRequest mergeRequestApprovals = makeFakeApiCall(MergeRequest.class, "approvals");
            assertTrue(compareJson(mergeRequestApprovals, "approvals"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequest() {

        try {
            MergeRequest mergeRequest = makeFakeApiCall(MergeRequest.class, "merge-request");
            assertTrue(compareJson(mergeRequest, "merge-request"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMilestone() {

        try {
            Milestone milestone = makeFakeApiCall(Milestone.class, "milestone");
            assertTrue(compareJson(milestone, "milestone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNote() {

        try {
            Note note = makeFakeApiCall(Note.class, "note");
            assertTrue(compareJson(note, "note"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestNote() {

        try {
            Note note = makeFakeApiCall(Note.class, "merge-request-note");
            assertTrue(compareJson(note, "merge-request-note"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotificationSettings() {

        try {
            NotificationSettings settings = makeFakeApiCall(NotificationSettings.class, "notification-settings");
            assertTrue(compareJson(settings, "notification-settings"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProject() {

        try {
            Project project = makeFakeApiCall(Project.class, "project");
            assertTrue(compareJson(project, "project"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectSnippet() {

        try {
            Snippet snippet = makeFakeApiCall(Snippet.class, "snippet");
            assertTrue(compareJson(snippet, "snippet"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSession() {

        try {
            Session session = makeFakeApiCall(Session.class, "session");
            assertTrue(compareJson(session, "session"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSystemHook() {

        try {
            SystemHook systemHook = makeFakeApiCall(SystemHook.class, "system-hook");
            assertTrue(compareJson(systemHook, "system-hook"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTag() {

        try {
            Tag tag = makeFakeApiCall(Tag.class, "tag");
            assertTrue(compareJson(tag, "tag"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSshKey() {

        try {
            SshKey sshKey = makeFakeApiCall(SshKey.class, "sshkey");
            assertTrue(compareJson(sshKey, "sshkey"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTree() {

        try {

            InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream("tree.json"));
            ObjectMapper objectMapper = jacksonJson.getContext(null);
            List<TreeItem> tree = objectMapper.readValue(reader, new TypeReference<List<TreeItem>>() {});
            assertTrue(compareJson(tree, "tree"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUser() {

        try {
            User user = makeFakeApiCall(User.class, "user");
            assertTrue(compareJson(user, "user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImpersonationToken() {

        try {
            ImpersonationToken token = makeFakeApiCall(ImpersonationToken.class, "impersonation-token");
            assertTrue(compareJson(token, "impersonation-token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOauthToken() {

        try {
            OauthTokenResponse token = makeFakeApiCall(OauthTokenResponse.class, "oauth-token");
            assertTrue(compareJson(token, "oauth-token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectSystemHookEvent() {

        try {
            ProjectSystemHookEvent event = makeFakeApiCall(ProjectSystemHookEvent.class, "project-system-hook-event");
            assertTrue(compareJson(event, "project-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTeamMemberSystemHookEvent() {

        try {
            TeamMemberSystemHookEvent event = makeFakeApiCall(TeamMemberSystemHookEvent.class, "team-member-system-hook-event");
            assertTrue(compareJson(event, "team-member-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPushSystemHookEvent() {

        try {
            PushSystemHookEvent event = makeFakeApiCall(PushSystemHookEvent.class, "push-system-hook-event");
            assertTrue(compareJson(event, "push-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserSystemHookEvent() {

        try {
            SystemHookEvent event = makeFakeApiCall(SystemHookEvent.class, "user-system-hook-event");
            assertTrue(compareJson(event, "user-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroupSystemHookEvent() {

        try {
            SystemHookEvent event = makeFakeApiCall(SystemHookEvent.class, "group-system-hook-event");
            assertTrue(compareJson(event, "group-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroupMemberSystemHookEvent() {

        try {
            SystemHookEvent event = makeFakeApiCall(SystemHookEvent.class, "group-member-system-hook-event");
            assertTrue(compareJson(event, "group-member-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTagPushSystemHookEvent() {

        try {
            SystemHookEvent event = makeFakeApiCall(SystemHookEvent.class, "tag-push-system-hook-event");
            assertTrue(compareJson(event, "tag-push-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRepositorySystemHookEvent() {

        try {
            SystemHookEvent event = makeFakeApiCall(SystemHookEvent.class, "repository-system-hook-event");
            assertTrue(compareJson(event, "repository-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLabels() {

        try {

            InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream("labels.json"));
            ObjectMapper objectMapper = jacksonJson.getContext(null);
            List<Label> labels = objectMapper.readValue(reader, new TypeReference<List<Label>>() {});
            assertTrue(compareJson(labels, "labels"));

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
