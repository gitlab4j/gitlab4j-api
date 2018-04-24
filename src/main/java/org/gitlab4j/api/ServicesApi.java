package org.gitlab4j.api;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.services.HipChatService;
import org.gitlab4j.api.services.JiraService;
import org.gitlab4j.api.services.SlackService;

/**
 * Access for the services API.  Currently only the gitlab-ci, HipChatService, Slack, and JIRA service are supported.
 * See <a href="https://github.com/gitlabhq/gitlabhq/blob/master/doc/api/services.md">GitLab documentation</a> for more info.
 */
public class ServicesApi extends AbstractApi {

    public ServicesApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Activates the gitlab-ci service for a project.
     *
     * PUT /projects/:id/services/gitlab-ci
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param token for authentication
     * @param projectCIUrl URL of the GitLab-CI project
     * @throws GitLabApiException if any exception occurs
     * @deprecated No longer supported
     */
    public void setGitLabCI(Object projectIdOrPath, String token, String projectCIUrl) throws GitLabApiException {
        final Form formData = new Form();
        formData.param("token", token);
        formData.param("project_url", projectCIUrl);
        put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "services", "gitlab-ci");
    }

    /**
     * Deletes the gitlab-ci service for a project.
     *
     * DELETE /projects/:id/services/gitlab-ci
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @throws GitLabApiException if any exception occurs
     * @deprecated No longer supported
     */
    public void deleteGitLabCI(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "gitlab-ci");
    }

    /**
     * Get the HipChatService notification configuration for a project.
     *
     * Get /projects/:id/services/hipchat
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a HipChatService instance holding the HipChatService notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public HipChatService getHipChatService(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "hipchat");
        return (response.readEntity(HipChatService.class)); 
    }
    
    /**
     * Updates the HipChatService notification settings for a project.
     *
     * PUT /projects/:id/services/hipchat
     * 
     * The following properties on the HipChatService instance are utilized in the update of the settings:
     *
     * pushEvents (optional) - Enable notifications for push events
     * issuesEvents (optional) - Enable notifications for issue events
     * confidentialIssuesEvents (optional) - Enable notifications for confidential issue events
     * MergeRequestsEvents (optional) - Enable notifications for merge request events
     * tagPushEvents (optional) - Enable notifications for tag push events
     * noteEvents (optional) - Enable notifications for note events
     * confidentialNoteEvents (optional) - Enable notifications for confidential note events
     * pipelineEvents (optional) - Enable notifications for pipeline events
     * token (required) - The room token
     * color (optional) - The room color
     * notify (optional) - Enable notifications
     * room (optional) - Room name or ID
     * apiVersion (optional) - Leave blank for default (v2)
     * server (false) - Leave blank for default. https://hipchat.example.com
     * notifyOnlyBrokenPipelines (optional) - Send notifications for broken pipelines
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param hipChat the HipChatService instance holding the settings
     * @return a HipChatService instance holding the newly updated settings
     * @throws GitLabApiException if any exception occurs
     */
    public HipChatService updateHipChatService(Object projectIdOrPath, HipChatService hipChat) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("push_events", hipChat.getPushEvents())
                .withParam("issues_events", hipChat.getIssuesEvents())
                .withParam("confidential_issues_events", hipChat.getConfidentialIssuesEvents())
                .withParam("merge_requests_events", hipChat.getMergeRequestsEvents())
                .withParam("tag_push_events", hipChat.getTagPushEvents())
                .withParam("note_events", hipChat.getNoteEvents())
                .withParam("confidential_note_events", hipChat.getConfidentialNoteEvents())
                .withParam("pipeline_events", hipChat.getPipelineEvents())
                .withParam("token", hipChat.getToken(), true)
                .withParam("color", hipChat.getColor())
                .withParam("notify", hipChat.getNotify())
                .withParam("room", hipChat.getRoom())
                .withParam("api_version", hipChat.getApiVersion())
                .withParam("server", hipChat.getServer())
                .withParam("notify_only_broken_pipelines", hipChat.getNotifyOnlyBrokenPipelines());
        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "services", "hipchat");
        return (response.readEntity(HipChatService.class));
    }

    /**
     * Activates HipChatService notifications.
     *
     * PUT /projects/:id/services/hipchat
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param token for authentication
     * @param room HipChatService Room
     * @param server HipChatService Server URL
     * @throws GitLabApiException if any exception occurs
     * @deprecated replaced with {@link #updateHipChatService(Object, HipChatService) updateHipChat} method
     */
    public void setHipChat(Object projectIdOrPath, String token, String room, String server) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("token", token)
                .withParam("room", room)
                .withParam("server", server);
        put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "services", "hipchat");
    }

    /**
     * Deletes the HipChatService service for a project.
     *
     * DELETE /projects/:id/services/hipchat
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @throws GitLabApiException if any exception occurs
     * @deprecated replaced with {@link #deleteHipChatService(Object) updateHipChat} method
     */
    public void deleteHipChat(Object projectIdOrPath) throws GitLabApiException {
        deleteHipChatService(projectIdOrPath);
    }

    /**
     * Deletes the HipChatService service for a project.
     *
     * DELETE /projects/:id/services/hipchat
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteHipChatService(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "hipchat");
    }

    /**
     * Get the Slack notification settings for a project.
     *
     * Get /projects/:id/services/slack
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a SlackService instance holding the Slack notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public SlackService getSlackService(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "slack");
        return (response.readEntity(SlackService.class)); 
    }

    /**
     * Updates the Slack notification settings for a project.
     *
     * PUT /projects/:id/services/slack
     *
     * The following properties on the SlackService instance are utilized in the update of the settings:
     * 
     * webhook (required) - https://hooks.slack.com/services/...
     * username (optional) - username
     * defaultChannel (optional) - Default channel to use if others are not configured
     * notifyOnlyBrokenPipelines (optional) - Send notifications for broken pipelines
     * notifyOnlyDefault_branch (optional) - Send notifications only for the default branch
     * pushEvents (optional) - Enable notifications for push events
     * issuesEvents (optional) - Enable notifications for issue events
     * confidentialIssuesEvents (optional) - Enable notifications for confidential issue events
     * mergeRequestsEvents (optional) - Enable notifications for merge request events
     * tagPushEvents (optional) - Enable notifications for tag push events
     * noteEvents (optional) - Enable notifications for note events
     * confidentialNoteEvents (optional) - Enable notifications for confidential note events
     * pipelineEvents (optional) - Enable notifications for pipeline events
     * wikiPageEvents (optional) - Enable notifications for wiki page events
     * pushChannel (optional) - The name of the channel to receive push events notifications
     * issueChannel (optional) - The name of the channel to receive issues events notifications
     * confidentialIssueChannel (optional) - The name of the channel to receive confidential issues events notifications
     * mergeRequestChannel (optional) - The name of the channel to receive merge request events notifications
     * noteChannel (optional) - The name of the channel to receive note events notifications
     * confidentialNoteChannel (optional) - The name of the channel to receive confidential note events notifications
     * tagPushChannel (optional) - The name of the channel to receive tag push events notifications
     * pipelineChannel (optional) - The name of the channel to receive pipeline events notifications
     * wikiPageChannel (optional) - The name of the channel to receive wiki page events notifications
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param slackNotifications the SlackService instance holding the settings
     * @return a SlackService instance holding the newly updated settings
     * @throws GitLabApiException if any exception occurs
     */
    public SlackService updateSlackService(Object projectIdOrPath, SlackService slackNotifications) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("webhook", slackNotifications.getWebhook(), true)
                .withParam("username", slackNotifications.getUsername())
                .withParam("channel",  slackNotifications.getDefaultChannel())
                .withParam("notify_only_broken_pipelines", slackNotifications.getNotifyOnlyBrokenPipelines())
                .withParam("notify_only_default_branch", slackNotifications.getNotifyOnlyDefaultBranch())
                .withParam("push_events", slackNotifications.getPushEvents())
                .withParam("issues_events", slackNotifications.getIssuesEvents())
                .withParam("confidential_issues_events", slackNotifications.getConfidentialIssuesEvents())
                .withParam("merge_requests_events", slackNotifications.getMergeRequestsEvents())
                .withParam("tag_push_events", slackNotifications.getTagPushEvents())
                .withParam("note_events", slackNotifications.getNoteEvents())
                .withParam("confidential_note_events", slackNotifications.getConfidentialNoteEvents())
                .withParam("pipeline_events", slackNotifications.getPipelineEvents())
                .withParam("wiki_page_events", slackNotifications.getWikiPageEvents())
                .withParam("push_channel", slackNotifications.getPushChannel())
                .withParam("issue_channel", slackNotifications.getIssueChannel())
                .withParam("confidential_issue_channel", slackNotifications.getConfidentialIssueChannel())
                .withParam("merge_request_channel", slackNotifications.getMergeRequestChannel())
                .withParam("note_channel", slackNotifications.getNoteChannel())
                .withParam("confidential_note_channel", slackNotifications.getConfidentialNoteChannel())
                .withParam("tag_push_channel", slackNotifications.getTagPushChannel())
                .withParam("pipeline_channel", slackNotifications.getPipelineChannel())
                .withParam("wiki_page_channel", slackNotifications.getWikiPageChannel());
        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "services", "slack");
        return (response.readEntity(SlackService.class));
    }

    /**
     * Deletes the Slack notifications service for a project.
     *
     * DELETE /projects/:id/services/slack
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteSlackService(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "slack");
    }
    
    /**
     * Get the JIRA service settings for a project.
     *
     * Get /projects/:id/services/jira
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @return a JiraService instance holding the JIRA service settings
     * @throws GitLabApiException if any exception occurs
     */
    public JiraService getJiraService(Object projectIdOrPath) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "jira");
        return (response.readEntity(JiraService.class)); 
    }

    /**
     * Updates the JIRA service settings for a project.
     *
     * PUT /projects/:id/services/jira
     * 
     * The following properties on the JiraService instance are utilized in the update of the settings:
     *
     * mergeRequestsEvents (optional) - Enable notifications for merge request events
     * commitEvents (optional) - Enable notifications for commit events
     * url (required) - The URL to the JIRA project which is being linked to this GitLab project, e.g., https://jira.example.com.
     * apiUrl (optional) - The JIRA API url if different than url
     * projectKey (optional) - The short identifier for your JIRA project, all uppercase, e.g., PROJ.
     * username (required) - The username of the user created to be used with GitLab/JIRA.
     * password (required) - The password of the user created to be used with GitLab/JIRA.
     * jiraIssueTransitionId (optional) - The ID of a transition that moves issues to a closed state.
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @param jira the JiraService instance holding the settings
     * @return a JiraService instance holding the newly updated settings
     * @throws GitLabApiException if any exception occurs
     */
    public JiraService updateJiraService(Object projectIdOrPath, JiraService jira) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("merge_requests_events", jira.getMergeRequestsEvents())
                .withParam("commit_events", jira.getCommitEvents())
                .withParam("url", jira.getUrl(), true)
                .withParam("api_url", jira.getApiUrl())
                .withParam("project_key", jira.getProjectKey())
                .withParam("username", jira.getUsername(), true)
                .withParam("password",  jira.getPassword(), true)
                .withParam("jira_issue_transition_id", jira.getJiraIssueTransitionId());
        Response response = put(Response.Status.OK, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "services", "jira");
        return (response.readEntity(JiraService.class));
    }

    /**
     * Deletes the JIRA service for a project.
     *
     * DELETE /projects/:id/services/jira
     *
     * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or path
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteJiraService(Object projectIdOrPath) throws GitLabApiException {
        Response.Status expectedStatus = (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "jira");
    }
}
