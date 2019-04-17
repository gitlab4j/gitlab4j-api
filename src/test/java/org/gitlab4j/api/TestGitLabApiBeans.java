/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software.json"), to deal in
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

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.gitlab4j.api.JsonUtils.unmarshalResource;
import static org.gitlab4j.api.JsonUtils.unmarshalResourceList;
import static org.gitlab4j.api.JsonUtils.unmarshalResourceMap;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.gitlab4j.api.models.ArtifactsFile;
import org.gitlab4j.api.models.AwardEmoji;
import org.gitlab4j.api.models.Board;
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
import org.gitlab4j.api.models.Package;
import org.gitlab4j.api.models.PackageFile;
import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.PipelineSchedule;
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
import org.gitlab4j.api.models.Variable;
import org.gitlab4j.api.services.JiraService;
import org.gitlab4j.api.services.SlackService;
import org.junit.Test;

public class TestGitLabApiBeans {

    @Test
    public void testAwardEmoji() throws Exception {
        AwardEmoji awardEmoji = unmarshalResource(AwardEmoji.class, "award-emoji.json");
        assertTrue(compareJson(awardEmoji, "award-emoji.json"));
    }

    @Test
    public void testBoard() throws Exception {
        List<Board> boards = unmarshalResourceList(Board.class, "project-board.json");
        assertTrue(compareJson(boards, "project-board.json"));
    }

    @Test
    public void testBranch() throws Exception {

        Branch branch = unmarshalResource(Branch.class, "branch.json");
        assertTrue(compareJson(branch, "branch.json"));

        branch = unmarshalResource(Branch.class, "bad-branch.json");
        assertTrue(!Branch.isValid(branch));
    }

    @Test
    public void testCommit() throws Exception {
        Commit commit = unmarshalResource(Commit.class, "commit.json");
        assertTrue(compareJson(commit, "commit.json"));
    }

    @Test
    public void testCommitPayload() throws Exception {
        CommitPayload commitPayload = unmarshalResource(CommitPayload.class, "commit-payload.json");
        assertTrue(compareJson(commitPayload, "commit-payload.json"));
    }

    @Test
    public void testCommitStatus() throws Exception {
        CommitStatus commitStatus = unmarshalResource(CommitStatus.class, "commit-status.json");
        assertTrue(compareJson(commitStatus, "commit-status.json"));
    }

    @Test
    public void testCompareResults() throws Exception {
        CompareResults compareResults = unmarshalResource(CompareResults.class, "compare-results.json");
        assertTrue(compareJson(compareResults, "compare-results.json"));
    }

    @Test
    public void testDiff() throws Exception {
        List<Diff> diffs = unmarshalResourceList(Diff.class, "diff.json");
        assertTrue(compareJson(diffs, "diff.json"));
    }

    @Test
    public void testComment() throws Exception {
        Comment comment = unmarshalResource(Comment.class, "comment.json");
        assertTrue(compareJson(comment, "comment.json"));
    }

    @Test
    public void testEpic() throws Exception {
        Epic epic = unmarshalResource(Epic.class, "epic.json");
        assertTrue(compareJson(epic, "epic.json"));
    }

    @Test
    public void testEpicIssue() throws Exception {
        EpicIssue epicIssue = unmarshalResource(EpicIssue.class, "epic-issue.json");
        assertTrue(compareJson(epicIssue, "epic-issue.json"));
    }

    @Test
    public void testEvent() throws Exception {
        Event event = unmarshalResource(Event.class, "event.json");
        assertTrue(compareJson(event, "event.json"));
    }

    @Test
    public void testFileUpload() throws Exception {
        FileUpload fileUpload = unmarshalResource(FileUpload.class, "file-upload.json");
        assertTrue(compareJson(fileUpload, "file-upload.json"));
    }

    @Test
    public void testGroup() throws Exception {
        Group group = unmarshalResource(Group.class, "group.json");
        assertTrue(compareJson(group, "group.json"));
    }

    @Test
    public void testHealthCheckInfo() throws Exception {
        HealthCheckInfo healthCheck = unmarshalResource(HealthCheckInfo.class, "health-check.json");
        assertTrue(compareJson(healthCheck, "health-check.json"));
    }

    @Test
    public void testIssue() throws Exception {
        Issue issue = unmarshalResource(Issue.class, "issue.json");
        assertTrue(compareJson(issue, "issue.json"));
    }

    @Test
    public void testCommitDiscussions() throws Exception {
        List<Discussion> discussions = unmarshalResourceList(Discussion.class, "commit-discussions.json");
        assertTrue(compareJson(discussions, "commit-discussions.json"));
    }

    @Test
    public void testEpicDiscussions() throws Exception {
        List<Discussion> discussions = unmarshalResourceList(Discussion.class, "epic-discussions.json");
        assertTrue(compareJson(discussions, "epic-discussions.json"));
    }

    @Test
    public void testIssueDiscussions() throws Exception {
        List<Discussion> discussions = unmarshalResourceList(Discussion.class, "issue-discussions.json");
        assertTrue(compareJson(discussions, "issue-discussions.json"));
    }

    @Test
    public void testMergeRequestDiscussions() throws Exception {
        List<Discussion> discussions = unmarshalResourceList(Discussion.class, "merge-request-discussions.json");
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }

    @Test
    public void testPackages() throws Exception {
        List<Package> packages = unmarshalResourceList(Package.class, "packages.json");
        assertTrue(compareJson(packages, "packages.json"));
    }

    @Test
    public void testPackageFiles() throws Exception {
        List<PackageFile> packageFiles = unmarshalResourceList(PackageFile.class, "package-files.json");
        assertTrue(compareJson(packageFiles, "package-files.json"));
    }

    @Test
    public void testSnippetDiscussions() throws Exception {
        List<Discussion> discussions = unmarshalResourceList(Discussion.class, "snippet-discussions.json");
        assertTrue(compareJson(discussions, "snippet-discussions.json"));
    }

    @Test
    public void testPipeline() throws Exception {
        Pipeline pipeline = unmarshalResource(Pipeline.class, "pipeline.json");
        assertTrue(compareJson(pipeline, "pipeline.json"));
    }

    @Test
    public void testPipelineSchedule() throws Exception {
        PipelineSchedule pipelineSchedule = unmarshalResource(PipelineSchedule.class, "pipeline-schedule.json");
        assertTrue(compareJson(pipelineSchedule, "pipeline-schedule.json"));
    }

    @Test
    public void testProjectVariables() throws Exception {
        List<Variable> variables = unmarshalResourceList(Variable.class, "project-variables.json");
        assertTrue(compareJson(variables, "project-variables.json"));
    }

    @Test
    public void testJob() throws Exception {
        Job job = unmarshalResource(Job.class, "job.json");
        assertTrue(compareJson(job, "job.json"));
    }

    @Test
    public void testDeployKeys() throws Exception {
        List<DeployKey> deployKeys = unmarshalResourceList(DeployKey.class, "deploy-keys.json");
        assertTrue(compareJson(deployKeys, "deploy-keys.json"));
    }

    @Test
    public void testArtifactsFile() throws Exception {
        ArtifactsFile artifactFile = unmarshalResource(ArtifactsFile.class, "artifacts-file.json");
        assertTrue(compareJson(artifactFile, "artifacts-file.json"));
    }

    @Test
    public void testProjectLanguages() throws Exception {
        Map<String, Float> projectLanguages = unmarshalResourceMap(Float.class, "project-languages.json");
        assertTrue(compareJson(projectLanguages, "project-languages.json"));
    }

    @Test
    public void testProjectUsers() throws Exception {
        List<ProjectUser> projectUsers = unmarshalResourceList(ProjectUser.class, "project-users.json");
        assertTrue(compareJson(projectUsers, "project-users.json"));
    }

    @Test
    public void testProjectHook() throws Exception {
        ProjectHook hook = unmarshalResource(ProjectHook.class, "hook.json");
        assertTrue(compareJson(hook, "hook.json"));
    }

    @Test
    public void testProjectEvents() throws Exception {
        List<Event> events = unmarshalResourceList(Event.class, "project-events.json");
        assertTrue(compareJson(events, "project-events.json"));
    }

    @Test
    public void testProtectedBranch() throws Exception {
        ProtectedBranch protectedBranch = unmarshalResource(ProtectedBranch.class, "protected-branch.json");
        assertTrue(compareJson(protectedBranch, "protected-branch.json"));
    }

    @Test
    public void testPushRule() throws Exception {
        PushRules pushRule = unmarshalResource(PushRules.class, "push-rule.json");
        assertTrue(compareJson(pushRule, "push-rule.json"));
    }

    @Test
    public void testRunnerDetail() throws Exception {
        RunnerDetail runnerDetail = unmarshalResource(RunnerDetail.class, "runner-detail.json");
        assertTrue(compareJson(runnerDetail, "runner-detail.json"));
    }

    @Test
    public void testAllRunners() throws Exception {
        List<Runner> allRunners = unmarshalResourceList(Runner.class, "all-runners.json");
        assertTrue(compareJson(allRunners, "all-runners.json"));
    }

    @Test
    public void testJiraService() throws Exception {
        JiraService jira = unmarshalResource(JiraService.class, "jira.json");
        assertTrue(compareJson(jira, "jira.json"));
    }

    @Test
    public void testKey() throws Exception {
        Key key = unmarshalResource(Key.class, "key.json");
        assertTrue(compareJson(key, "key.json"));
    }

    @Test
    public void testMember() throws Exception {
        Member member = unmarshalResource(Member.class, "member.json");
        assertTrue(compareJson(member, "member.json"));
    }

    @Test
    public void testMergeRequestApprovals() throws Exception {
        MergeRequest mergeRequestApprovals = unmarshalResource(MergeRequest.class, "approvals.json");
        assertTrue(compareJson(mergeRequestApprovals, "approvals.json"));
    }

    @Test
    public void testMergeRequest() throws Exception {
        MergeRequest mergeRequest = unmarshalResource(MergeRequest.class, "merge-request.json");
        assertTrue(compareJson(mergeRequest, "merge-request.json"));
    }

    @Test
    public void testMilestone() throws Exception {
        Milestone milestone = unmarshalResource(Milestone.class, "milestone.json");
        assertTrue(compareJson(milestone, "milestone.json"));
    }

    @Test
    public void testGroupMilestone() throws Exception {
        Milestone milestone = unmarshalResource(Milestone.class, "milestone-group.json");
        assertTrue(compareJson(milestone, "milestone-group.json"));
    }

    @Test
    public void testNote() throws Exception {
        Note note = unmarshalResource(Note.class, "note.json");
        assertTrue(compareJson(note, "note.json"));
    }

    @Test
    public void testMergeRequestNote() throws Exception {
        Note note = unmarshalResource(Note.class, "merge-request-note.json");
        assertTrue(compareJson(note, "merge-request-note.json"));
    }

    @Test
    public void testNotificationSettings() throws Exception {
        NotificationSettings settings = unmarshalResource(NotificationSettings.class, "notification-settings.json");
        assertTrue(compareJson(settings, "notification-settings.json"));
    }

    @Test
    public void testProject() throws Exception {
        Project project = unmarshalResource(Project.class, "project.json");
        assertTrue(compareJson(project, "project.json"));
    }

    @Test
    public void testProjectSnippet() throws Exception {
        Snippet snippet = unmarshalResource(Snippet.class, "snippet.json");
        assertTrue(compareJson(snippet, "snippet.json"));
    }

    @Test
    public void testSession() throws Exception {
        Session session = unmarshalResource(Session.class, "session.json");
        assertTrue(compareJson(session, "session.json"));
    }

    @Test
    public void testSlackService() throws Exception {
        SlackService slackNotifications = unmarshalResource(SlackService.class, "slack-notifications.json");
        assertTrue(compareJson(slackNotifications, "slack-notifications.json"));
    }

    @Test
    public void testSystemHook() throws Exception {
        SystemHook systemHook = unmarshalResource(SystemHook.class, "system-hook.json");
        assertTrue(compareJson(systemHook, "system-hook.json"));
    }

    @Test
    public void testTag() throws Exception {
        Tag tag = unmarshalResource(Tag.class, "tag.json");
        assertTrue(compareJson(tag, "tag.json"));
    }

    @Test
    public void testSshKey() throws Exception {
        SshKey sshKey = unmarshalResource(SshKey.class, "sshkey.json");
        assertTrue(compareJson(sshKey, "sshkey.json"));
    }

    @Test
    public void testTree() throws Exception {
        List<TreeItem> tree = unmarshalResourceList(TreeItem.class, "tree.json");
        assertTrue(compareJson(tree, "tree.json"));
    }

    @Test
    public void testUser() throws Exception {
        User user = unmarshalResource(User.class, "user.json");
        assertTrue(compareJson(user, "user.json"));
    }

    @Test
    public void testImpersonationToken() throws Exception {
        ImpersonationToken token = unmarshalResource(ImpersonationToken.class, "impersonation-token.json");
        assertTrue(compareJson(token, "impersonation-token.json"));
    }

    @Test
    public void testOauthToken() throws Exception {
        OauthTokenResponse token = unmarshalResource(OauthTokenResponse.class, "oauth-token.json");
        assertTrue(compareJson(token, "oauth-token.json"));
    }

    @Test
    public void testLabels() throws Exception {
        List<Label> labels = unmarshalResourceList(Label.class, "labels.json");
        assertTrue(compareJson(labels, "labels.json"));
    }
}
