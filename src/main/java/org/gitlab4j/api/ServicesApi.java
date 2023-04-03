package org.gitlab4j.api;

import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.services.BugzillaService;
import org.gitlab4j.api.services.CustomIssueTrackerService;
import org.gitlab4j.api.services.EmailOnPushService;
import org.gitlab4j.api.services.ExternalWikiService;
import org.gitlab4j.api.services.HipChatService;
import org.gitlab4j.api.services.JiraService;
import org.gitlab4j.api.services.MattermostService;
import org.gitlab4j.api.services.SlackService;

/**
 * Access for the services API. Currently only the gitlab-ci, HipChatService, Slack, and JIRA
 * service are supported. See <a
 * href="https://github.com/gitlabhq/gitlabhq/blob/master/doc/api/services.md">GitLab
 * documentation</a> for more info.
 */
public class ServicesApi extends AbstractApi {

  public ServicesApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Activates the gitlab-ci service for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/gitlab-ci</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param token for authentication
   * @param projectCIUrl URL of the GitLab-CI project
   * @throws GitLabApiException if any exception occurs
   * @deprecated No longer supported
   */
  public void setGitLabCI(
      final Object projectIdOrPath, final String token, final String projectCIUrl)
      throws GitLabApiException {
    final Form formData = new Form();
    formData.param("token", token);
    formData.param("project_url", projectCIUrl);
    put(
        Response.Status.OK,
        formData.asMap(),
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "gitlab-ci");
  }

  /**
   * Deletes the gitlab-ci service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/gitlab-ci</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   * @deprecated No longer supported
   */
  public void deleteGitLabCI(final Object projectIdOrPath) throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "gitlab-ci");
  }

  /**
   * Get the HipChatService notification configuration for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/hipchat</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a HipChatService instance holding the HipChatService notification settings
   * @throws GitLabApiException if any exception occurs
   */
  public HipChatService getHipChatService(final Object projectIdOrPath) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "hipchat");
    return (response.readEntity(HipChatService.class));
  }

  /**
   * Updates the HipChatService notification settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/hipchat</code></pre>
   *
   * The following properties on the HipChatService instance are utilized in the update of the
   * settings:
   *
   * <p>pushEvents (optional) - Enable notifications for push events issuesEvents (optional) -
   * Enable notifications for issue events confidentialIssuesEvents (optional) - Enable
   * notifications for confidential issue events MergeRequestsEvents (optional) - Enable
   * notifications for merge request events tagPushEvents (optional) - Enable notifications for tag
   * push events noteEvents (optional) - Enable notifications for note events confidentialNoteEvents
   * (optional) - Enable notifications for confidential note events pipelineEvents (optional) -
   * Enable notifications for pipeline events token (required) - The room token color (optional) -
   * The room color notify (optional) - Enable notifications room (optional) - Room name or ID
   * apiVersion (optional) - Leave blank for default (v2) server (false) - Leave blank for default.
   * https://hipchat.example.com notifyOnlyBrokenPipelines (optional) - Send notifications for
   * broken pipelines
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param hipChat the HipChatService instance holding the settings
   * @return a HipChatService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public HipChatService updateHipChatService(
      final Object projectIdOrPath, final HipChatService hipChat) throws GitLabApiException {
    final GitLabApiForm formData = hipChat.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "hipchat");
    return (response.readEntity(HipChatService.class));
  }

  /**
   * Activates HipChatService notifications.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/hipchat</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param token for authentication
   * @param room HipChatService Room
   * @param server HipChatService Server URL
   * @throws GitLabApiException if any exception occurs
   * @deprecated replaced with {@link #updateHipChatService(Object, HipChatService) updateHipChat}
   *     method
   */
  public void setHipChat(
      final Object projectIdOrPath, final String token, final String room, final String server)
      throws GitLabApiException {
    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("token", token)
            .withParam("room", room)
            .withParam("server", server);
    put(
        Response.Status.OK,
        formData.asMap(),
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "hipchat");
  }

  /**
   * Deletes the HipChatService service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/hipchat</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   * @deprecated replaced with {@link #deleteHipChatService(Object) updateHipChat} method
   */
  public void deleteHipChat(final Object projectIdOrPath) throws GitLabApiException {
    deleteHipChatService(projectIdOrPath);
  }

  /**
   * Deletes the HipChatService service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/hipchat</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteHipChatService(final Object projectIdOrPath) throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "hipchat");
  }

  /**
   * Get the Slack notification settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/slack</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a SlackService instance holding the Slack notification settings
   * @throws GitLabApiException if any exception occurs
   */
  public SlackService getSlackService(final Object projectIdOrPath) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "slack");
    return (response.readEntity(SlackService.class));
  }

  /**
   * Updates the Slack notification settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/slack</code></pre>
   *
   * The following properties on the SlackService instance are utilized in the update of the
   * settings:
   *
   * <p>webhook (required) - https://hooks.slack.com/services/... username (optional) - username
   * defaultChannel (optional) - Default channel to use if others are not configured
   * notifyOnlyBrokenPipelines (optional) - Send notifications for broken pipelines
   * notifyOnlyDefault_branch (optional) - Send notifications only for the default branch pushEvents
   * (optional) - Enable notifications for push events issuesEvents (optional) - Enable
   * notifications for issue events confidentialIssuesEvents (optional) - Enable notifications for
   * confidential issue events mergeRequestsEvents (optional) - Enable notifications for merge
   * request events tagPushEvents (optional) - Enable notifications for tag push events noteEvents
   * (optional) - Enable notifications for note events confidentialNoteEvents (optional) - Enable
   * notifications for confidential note events pipelineEvents (optional) - Enable notifications for
   * pipeline events wikiPageEvents (optional) - Enable notifications for wiki page events
   * pushChannel (optional) - The name of the channel to receive push events notifications
   * issueChannel (optional) - The name of the channel to receive issues events notifications
   * confidentialIssueChannel (optional) - The name of the channel to receive confidential issues
   * events notifications mergeRequestChannel (optional) - The name of the channel to receive merge
   * request events notifications noteChannel (optional) - The name of the channel to receive note
   * events notifications confidentialNoteChannel (optional) - The name of the channel to receive
   * confidential note events notifications tagPushChannel (optional) - The name of the channel to
   * receive tag push events notifications pipelineChannel (optional) - The name of the channel to
   * receive pipeline events notifications wikiPageChannel (optional) - The name of the channel to
   * receive wiki page events notifications
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param slackNotifications the SlackService instance holding the settings
   * @return a SlackService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public SlackService updateSlackService(
      final Object projectIdOrPath, final SlackService slackNotifications)
      throws GitLabApiException {
    final GitLabApiForm formData = slackNotifications.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "slack");
    return (response.readEntity(SlackService.class));
  }

  /**
   * Deletes the Slack notifications service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/slack</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteSlackService(final Object projectIdOrPath) throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "slack");
  }

  /**
   * Get the JIRA service settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/jira</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a JiraService instance holding the JIRA service settings
   * @throws GitLabApiException if any exception occurs
   */
  public JiraService getJiraService(final Object projectIdOrPath) throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "jira");
    return (response.readEntity(JiraService.class));
  }

  /**
   * Updates the JIRA service settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/jira</code></pre>
   *
   * The following properties on the JiraService instance are utilized in the update of the
   * settings:
   *
   * <p>mergeRequestsEvents (optional) - Enable notifications for merge request events commitEvents
   * (optional) - Enable notifications for commit events url (required) - The URL to the JIRA
   * project which is being linked to this GitLab project, e.g., https://jira.example.com. apiUrl
   * (optional) - The JIRA API url if different than url projectKey (optional) - The short
   * identifier for your JIRA project, all uppercase, e.g., PROJ. username (required) - The username
   * of the user created to be used with GitLab/JIRA. password (required) - The password of the user
   * created to be used with GitLab/JIRA. jiraIssueTransitionId (optional) - The ID of a transition
   * that moves issues to a closed state.
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param jira the JiraService instance holding the settings
   * @return a JiraService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public JiraService updateJiraService(final Object projectIdOrPath, final JiraService jira)
      throws GitLabApiException {
    final GitLabApiForm formData = jira.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "jira");
    return (response.readEntity(JiraService.class));
  }

  /**
   * Deletes the JIRA service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/jira</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteJiraService(final Object projectIdOrPath) throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus, null, "projects", getProjectIdOrPath(projectIdOrPath), "services", "jira");
  }

  /**
   * Get the ExternalWiki service settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/external-wiki</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a ExternalWikiService instance holding the External Wiki service settings
   * @throws GitLabApiException if any exception occurs
   */
  public ExternalWikiService getExternalWikiService(final Object projectIdOrPath)
      throws GitLabApiException {
    final Response response =
        this.get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "external-wiki");
    return (response.readEntity(ExternalWikiService.class));
  }

  /**
   * Updates the ExternalWikiService service settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/external-wiki</code></pre>
   *
   * The following properties on the JiraService instance are utilized in the update of the
   * settings:
   *
   * <p>external_wiki_url (required) - The URL to the External Wiki project which is being linked to
   * this GitLab project, e.g., http://www.wikidot.com/
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param externalWiki the ExternalWikiService instance holding the settings
   * @return a ExternalWikiService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public ExternalWikiService updateExternalWikiService(
      final Object projectIdOrPath, final ExternalWikiService externalWiki)
      throws GitLabApiException {
    final GitLabApiForm formData = externalWiki.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "external-wiki");
    return (response.readEntity(ExternalWikiService.class));
  }

  /**
   * Deletes the ExternalWiki service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/external-wiki</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteExternalWikiService(final Object projectIdOrPath) throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "external-wiki");
  }

  /**
   * Get the Mattermost service settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/mattermost</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a MattermostService instance holding the Mattermost service settings
   * @throws GitLabApiException if any exception occurs
   */
  public MattermostService getMattermostService(final Object projectIdOrPath)
      throws GitLabApiException {
    final Response response =
        this.get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "mattermost");
    return (response.readEntity(MattermostService.class));
  }

  /**
   * Updates the Mattermost service settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/mattermost</code></pre>
   *
   * The following properties on the MattermostService instance are utilized in the update of the
   * settings:
   *
   * <p>webhook (required) - https://hooks.slack.com/services/... username (optional) - username
   * defaultChannel (optional) - Default channel to use if others are not configured
   * notifyOnlyBrokenPipelines (optional) - Send notifications for broken pipelines
   * notifyOnlyDefault_branch (optional) - Send notifications only for the default branch pushEvents
   * (optional) - Enable notifications for push events issuesEvents (optional) - Enable
   * notifications for issue events confidentialIssuesEvents (optional) - Enable notifications for
   * confidential issue events mergeRequestsEvents (optional) - Enable notifications for merge
   * request events tagPushEvents (optional) - Enable notifications for tag push events noteEvents
   * (optional) - Enable notifications for note events confidentialNoteEvents (optional) - Enable
   * notifications for confidential note events pipelineEvents (optional) - Enable notifications for
   * pipeline events wikiPageEvents (optional) - Enable notifications for wiki page events
   * pushChannel (optional) - The name of the channel to receive push events notifications
   * issueChannel (optional) - The name of the channel to receive issues events notifications
   * confidentialIssueChannel (optional) - The name of the channel to receive confidential issues
   * events notifications mergeRequestChannel (optional) - The name of the channel to receive merge
   * request events notifications noteChannel (optional) - The name of the channel to receive note
   * events notifications confidentialNoteChannel (optional) - The name of the channel to receive
   * confidential note events notifications tagPushChannel (optional) - The name of the channel to
   * receive tag push events notifications pipelineChannel (optional) - The name of the channel to
   * receive pipeline events notifications wikiPageChannel (optional) - The name of the channel to
   * receive wiki page events notifications
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param mattermostNotifications the MattermostService instance holding the settings
   * @return a MattermostService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public MattermostService updateMattermostService(
      final Object projectIdOrPath, final MattermostService mattermostNotifications)
      throws GitLabApiException {
    final GitLabApiForm formData = mattermostNotifications.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "mattermost");
    return (response.readEntity(MattermostService.class));
  }

  /**
   * Deletes the Mattermost service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/external-wiki</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteMattermostService(final Object projectIdOrPath) throws GitLabApiException {
    final Response.Status expectedStatus =
        (isApiVersion(ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
    delete(
        expectedStatus,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "mattermost");
  }

  /**
   * Get the Bugzilla service settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/bugzilla</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a BugzillaService instance holding the External Wiki service settings
   * @throws GitLabApiException if any exception occurs
   */
  public BugzillaService getBugzillaService(final Object projectIdOrPath)
      throws GitLabApiException {
    final Response response =
        this.get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "bugzilla");
    return (response.readEntity(BugzillaService.class));
  }

  /**
   * Updates the Bugzilla service settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/bugzilla</code></pre>
   *
   * The following properties on the BugzillaService instance are utilized in the update of the
   * settings:
   *
   * <p>description (optional), description issuesUrl (required), issue url newIssueUrl (required),
   * new Issue url projectUrl (required), project url pushEvents (optional) - Enable notifications
   * for push events title (optional), the title for the custom issue tracker
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param bugzillaService the BugzillaService instance holding the settings
   * @return a BugzillaService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public BugzillaService updateBugzillaService(
      final Object projectIdOrPath, final BugzillaService bugzillaService)
      throws GitLabApiException {
    final GitLabApiForm formData = bugzillaService.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "bugzilla");
    return (response.readEntity(BugzillaService.class));
  }

  /**
   * Deletes the Bugzilla service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/bugzilla</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteBugzillaService(final Object projectIdOrPath) throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "bugzilla");
  }

  /**
   * Get the Custom Issue Tracker service settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/custom_issue_tracker</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a ExternalWikiService instance holding the External Wiki service settings
   * @throws GitLabApiException if any exception occurs
   */
  public CustomIssueTrackerService getCustomIssueTrackerService(final Object projectIdOrPath)
      throws GitLabApiException {
    final Response response =
        this.get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "custom-issue-tracker");
    return (response.readEntity(CustomIssueTrackerService.class));
  }

  /**
   * Updates the Custom Issue Tracker service settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/custom_issue_tracker</code></pre>
   *
   * The following properties on the CustomIssueTrackerService instance are utilized in the update
   * of the settings:
   *
   * <p>description (optional), description issuesUrl (required), issue url newIssueUrl (required),
   * new Issue url projectUrl (required), project url pushEvents (optional) - Enable notifications
   * for push events title (optional), the title for the custom issue tracker
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param customIssueTracker the CustomIssueTrackerService instance holding the settings
   * @return a CustomIssueTrackerService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public CustomIssueTrackerService updateCustomIssueTrackerService(
      final Object projectIdOrPath, final CustomIssueTrackerService customIssueTracker)
      throws GitLabApiException {
    final GitLabApiForm formData = customIssueTracker.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "custom-issue-tracker");
    return (response.readEntity(CustomIssueTrackerService.class));
  }

  /**
   * Deletes the Custom Issue Tracker service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/custom_issue_tracker</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteCustomIssueTrackerService(final Object projectIdOrPath)
      throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "custom-issue-tracker");
  }

  /**
   * Get Emails on push service settings for a project.
   *
   * <pre><code>GitLab Endpoint: GET /projects/:id/services/emails-on-push</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @return a EmailOnPushService instance holding the Email on push settings
   * @throws GitLabApiException if any exception occurs
   */
  public EmailOnPushService getEmailOnPushService(final Object projectIdOrPath)
      throws GitLabApiException {
    final Response response =
        this.get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "emails-on-push");
    return (response.readEntity(EmailOnPushService.class));
  }

  /**
   * Updates the EmailsOnPush service settings for a project.
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/services/emails-on-push</code></pre>
   *
   * The following properties on the EmailOnPushService instance are utilized in the update of the
   * settings:
   *
   * <p>recipients (required), Emails separated by whitespace disable_diffs (optional), Disable code
   * diffs send_from_committer_email (optional), Send from committer push_events (optional), Enable
   * notifications for push events tag_push_events(optional), Enable notifications for tag push
   * events branches_to_be_notified (optional), Branches to send notifications for. Valid options
   * are "all", "default", "protected", and "default_and_protected". Notifications are always fired
   * for tag pushes. The default value is "all"
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @param emailsOnPush the EmailOnPushService instance holding the settings
   * @return a EmailOnPushService instance holding the newly updated settings
   * @throws GitLabApiException if any exception occurs
   */
  public EmailOnPushService updateEmailOnPushService(
      final Object projectIdOrPath, final EmailOnPushService emailsOnPush)
      throws GitLabApiException {
    final GitLabApiForm formData = emailsOnPush.servicePropertiesForm();
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "services",
            "emails-on-push");
    return (response.readEntity(EmailOnPushService.class));
  }

  /**
   * Deletes the Emails on push service for a project.
   *
   * <pre><code>GitLab Endpoint: DELETE /projects/:id/services/emails-on-push</code></pre>
   *
   * @param projectIdOrPath id, path of the project, or a Project instance holding the project ID or
   *     path
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteEmailonPushService(final Object projectIdOrPath) throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "services",
        "emails-on-push");
  }
}
