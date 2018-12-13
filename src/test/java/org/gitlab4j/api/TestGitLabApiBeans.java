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
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.models.ArtifactsFile;
import org.gitlab4j.api.models.AwardEmoji;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Comment;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitPayload;
import org.gitlab4j.api.models.CommitStatus;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.DeployKey;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Discussion;
import org.gitlab4j.api.models.Epic;
import org.gitlab4j.api.models.EpicIssue;
import org.gitlab4j.api.models.Event;
import org.gitlab4j.api.models.FileUpload;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.HealthCheckInfo;
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
import org.gitlab4j.api.models.ProtectedBranch;
import org.gitlab4j.api.models.PushRules;
import org.gitlab4j.api.models.Runner;
import org.gitlab4j.api.models.RunnerDetail;
import org.gitlab4j.api.models.Session;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.SshKey;
import org.gitlab4j.api.models.SystemHook;
import org.gitlab4j.api.models.Tag;
import org.gitlab4j.api.models.TreeItem;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.services.JiraService;
import org.gitlab4j.api.services.SlackService;
import org.gitlab4j.api.systemhooks.ProjectSystemHookEvent;
import org.gitlab4j.api.systemhooks.PushSystemHookEvent;
import org.gitlab4j.api.systemhooks.SystemHookEvent;
import org.gitlab4j.api.systemhooks.TeamMemberSystemHookEvent;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TestGitLabApiBeans {

    private <T> T unmarshal(Class<T> returnType, String file) throws JsonParseException, JsonMappingException, IOException {
        return (JsonUtils.unmarshal(returnType, file + ".json"));
    }

    private <T> List<T> unmarshalList(Class<T> returnType,  String file) throws JsonParseException, JsonMappingException, IOException {
        return (JsonUtils.unmarshalList(returnType, file + ".json"));
    }

    private <T> boolean compareJson(T apiObject, String file) throws IOException {
        return (JsonUtils.compareJson(apiObject, file + ".json"));
    }


    @Test
    public void testAwardEmoji() {

        try {
            AwardEmoji awardEmoji = unmarshal(AwardEmoji.class, "award-emoji");
            assertTrue(compareJson(awardEmoji, "award-emoji"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBranch() {

        try {
            Branch branch = unmarshal(Branch.class, "branch");
            assertTrue(compareJson(branch, "branch"));

            branch = unmarshal(Branch.class, "bad-branch");
            assertTrue(!Branch.isValid(branch));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCommit() {

        try {
            Commit commit = unmarshal(Commit.class, "commit");
            assertTrue(compareJson(commit, "commit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommitPayload() {

        try {
            CommitPayload commitPayload = unmarshal(CommitPayload.class, "commit-payload");
            assertTrue(compareJson(commitPayload, "commit-payload"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommitStatus() {

        try {
            CommitStatus commitStatus = unmarshal(CommitStatus.class, "commit-status");
            assertTrue(compareJson(commitStatus, "commit-status"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCompareResults() {

        try {
            CompareResults compareResults = unmarshal(CompareResults.class, "compare-results");
            assertTrue(compareJson(compareResults, "compare-results"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDiff() {

        try {
            Diff diff = unmarshal(Diff.class, "diff");
            assertTrue(compareJson(diff, "diff"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testComment() {

        try {
            Comment comment = unmarshal(Comment.class, "comment");
            assertTrue(compareJson(comment, "comment"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEpic() {

        try {
            Epic epic = unmarshal(Epic.class, "epic");
            assertTrue(compareJson(epic, "epic"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEpicIssue() {

        try {
            EpicIssue epicIssue = unmarshal(EpicIssue.class, "epic-issue");
            assertTrue(compareJson(epicIssue, "epic-issue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEvent() {

        try {
            Event event = unmarshal(Event.class, "event");
            assertTrue(compareJson(event, "event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileUpload() {

        try {
            FileUpload fileUpload = unmarshal(FileUpload.class, "file-upload");
            assertTrue(compareJson(fileUpload, "file-upload"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroup() {

        try {
            Group group = unmarshal(Group.class, "group");
            assertTrue(compareJson(group, "group"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHealthCheckInfo() {

        try {
            HealthCheckInfo healthCheck = unmarshal(HealthCheckInfo.class, "health-check");
            assertTrue(compareJson(healthCheck, "health-check"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIssue() {

        try {
            Issue issue = unmarshal(Issue.class, "issue");
            assertTrue(compareJson(issue, "issue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommitDiscussions() {

        try {
            List<Discussion> discussions = unmarshalList(Discussion.class, "commit-discussions");
            assertTrue(compareJson(discussions, "commit-discussions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEpicDiscussions() {

        try {
            List<Discussion> discussions = unmarshalList(Discussion.class, "epic-discussions");
            assertTrue(compareJson(discussions, "epic-discussions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIssueDiscussions() {

        try {
            List<Discussion> discussions = unmarshalList(Discussion.class, "issue-discussions");
            assertTrue(compareJson(discussions, "issue-discussions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestDiscussions() {

        try {
            List<Discussion> discussions = unmarshalList(Discussion.class, "merge-request-discussions");
            assertTrue(compareJson(discussions, "merge-request-discussions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSnippetDiscussions() {

        try {
            List<Discussion> discussions = unmarshalList(Discussion.class, "snippet-discussions");
            assertTrue(compareJson(discussions, "snippet-discussions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPipeline() {

        try {
            Pipeline pipeline = unmarshal(Pipeline.class, "pipeline");
            assertTrue(compareJson(pipeline, "pipeline"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJob() {

        try {
            Job job = unmarshal(Job.class, "job");
            assertTrue(compareJson(job, "job"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeployKeys() {

        try {
            List<DeployKey> deployKeys = unmarshalList(DeployKey.class, "deploy-keys");
            assertTrue(compareJson(deployKeys, "deploy-keys"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArtifactsFile() {

        try {
            ArtifactsFile artifactFile = unmarshal(ArtifactsFile.class, "artifacts-file");
            assertTrue(compareJson(artifactFile, "artifacts-file"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectLanguages() {

        try {
            Map<String, Float> projectLanguages = JsonUtils.unmarshalMap(Float.class, "project-languages.json");
            assertTrue(compareJson(projectLanguages, "project-languages"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectUsers() {

        try {
            List<ProjectUser> projectUsers = unmarshalList(ProjectUser.class, "project-users");
            assertTrue(compareJson(projectUsers, "project-users"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectHook() {

        try {
            ProjectHook hook = unmarshal(ProjectHook.class, "hook");
            assertTrue(compareJson(hook, "hook"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectEvents() {

        try {
            List<Event> events = unmarshalList(Event.class, "project-events");
            assertTrue(compareJson(events, "project-events"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProtectedBranch() {

        try {
            ProtectedBranch protectedBranch = unmarshal(ProtectedBranch.class, "protected-branch");
            assertTrue(compareJson(protectedBranch, "protected-branch"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPushRule() {

        try {
            PushRules pushRule = unmarshal(PushRules.class, "push-rule");
            assertTrue(compareJson(pushRule, "push-rule"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRunnerDetail() {

        try {
            RunnerDetail runnerDetail = unmarshal(RunnerDetail.class, "runner-detail");
            assertTrue(compareJson(runnerDetail, "runner-detail"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAllRunners() {

        try {
            List<Runner> allRunners = unmarshalList(Runner.class, "all-runners");
            assertTrue(compareJson(allRunners, "all-runners"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJiraService() {

        try {
            JiraService jira = unmarshal(JiraService.class, "jira");
            assertTrue(compareJson(jira, "jira"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKey() {

        try {
            Key key = unmarshal(Key.class, "key");
            assertTrue(compareJson(key, "key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMember() {

        try {
            Member member = unmarshal(Member.class, "member");
            assertTrue(compareJson(member, "member"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestApprovals() {

        try {
            MergeRequest mergeRequestApprovals = unmarshal(MergeRequest.class, "approvals");
            assertTrue(compareJson(mergeRequestApprovals, "approvals"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequest() {

        try {
            MergeRequest mergeRequest = unmarshal(MergeRequest.class, "merge-request");
            assertTrue(compareJson(mergeRequest, "merge-request"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMilestone() {

        try {
            Milestone milestone = unmarshal(Milestone.class, "milestone");
            assertTrue(compareJson(milestone, "milestone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNote() {

        try {
            Note note = unmarshal(Note.class, "note");
            assertTrue(compareJson(note, "note"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMergeRequestNote() {

        try {
            Note note = unmarshal(Note.class, "merge-request-note");
            assertTrue(compareJson(note, "merge-request-note"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNotificationSettings() {

        try {
            NotificationSettings settings = unmarshal(NotificationSettings.class, "notification-settings");
            assertTrue(compareJson(settings, "notification-settings"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProject() {

        try {
            Project project = unmarshal(Project.class, "project");
            assertTrue(compareJson(project, "project"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectSnippet() {

        try {
            Snippet snippet = unmarshal(Snippet.class, "snippet");
            assertTrue(compareJson(snippet, "snippet"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSession() {

        try {
            Session session = unmarshal(Session.class, "session");
            assertTrue(compareJson(session, "session"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSlackService() {

        try {
            SlackService slackNotifications = unmarshal(SlackService.class, "slack-notifications");
            assertTrue(compareJson(slackNotifications, "slack-notifications"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSystemHook() {

        try {
            SystemHook systemHook = unmarshal(SystemHook.class, "system-hook");
            assertTrue(compareJson(systemHook, "system-hook"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTag() {

        try {
            Tag tag = unmarshal(Tag.class, "tag");
            assertTrue(compareJson(tag, "tag"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSshKey() {

        try {
            SshKey sshKey = unmarshal(SshKey.class, "sshkey");
            assertTrue(compareJson(sshKey, "sshkey"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTree() {

        try {
            List<TreeItem> tree = unmarshalList(TreeItem.class, "tree");
            assertTrue(compareJson(tree, "tree"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUser() {

        try {
            User user = unmarshal(User.class, "user");
            assertTrue(compareJson(user, "user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImpersonationToken() {

        try {
            ImpersonationToken token = unmarshal(ImpersonationToken.class, "impersonation-token");
            assertTrue(compareJson(token, "impersonation-token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOauthToken() {

        try {
            OauthTokenResponse token = unmarshal(OauthTokenResponse.class, "oauth-token");
            assertTrue(compareJson(token, "oauth-token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProjectSystemHookEvent() {

        try {
            ProjectSystemHookEvent event = unmarshal(ProjectSystemHookEvent.class, "project-system-hook-event");
            assertTrue(compareJson(event, "project-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTeamMemberSystemHookEvent() {

        try {
            TeamMemberSystemHookEvent event = unmarshal(TeamMemberSystemHookEvent.class, "team-member-system-hook-event");
            assertTrue(compareJson(event, "team-member-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPushSystemHookEvent() {

        try {
            PushSystemHookEvent event = unmarshal(PushSystemHookEvent.class, "push-system-hook-event");
            assertTrue(compareJson(event, "push-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserSystemHookEvent() {

        try {
            SystemHookEvent event = unmarshal(SystemHookEvent.class, "user-system-hook-event");
            assertTrue(compareJson(event, "user-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroupSystemHookEvent() {

        try {
            SystemHookEvent event = unmarshal(SystemHookEvent.class, "group-system-hook-event");
            assertTrue(compareJson(event, "group-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroupMemberSystemHookEvent() {

        try {
            SystemHookEvent event = unmarshal(SystemHookEvent.class, "group-member-system-hook-event");
            assertTrue(compareJson(event, "group-member-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTagPushSystemHookEvent() {

        try {
            SystemHookEvent event = unmarshal(SystemHookEvent.class, "tag-push-system-hook-event");
            assertTrue(compareJson(event, "tag-push-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRepositorySystemHookEvent() {

        try {
            SystemHookEvent event = unmarshal(SystemHookEvent.class, "repository-system-hook-event");
            assertTrue(compareJson(event, "repository-system-hook-event"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLabels() {

        try {
            List<Label> labels = unmarshalList(Label.class, "labels");
            assertTrue(compareJson(labels, "labels"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
