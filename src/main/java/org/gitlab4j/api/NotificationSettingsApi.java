package org.gitlab4j.api;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.NotificationSettings;
import org.gitlab4j.api.models.NotificationSettings.Events;

public class NotificationSettingsApi extends AbstractApi {

    public NotificationSettingsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get the global notification settings.
     * 
     * GET /notification_settings
     *
     * @return a NotificationSettings instance containing the global notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public NotificationSettings getGlobalNotificationSettings() throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "notification_settings");
        return (response.readEntity(NotificationSettings.class));
    }

    /**
     * Update the global notification settings.
     * 
     * PUT /notification_settings
     *
     * @param settings a NotificationSettings instance with the new settings
     * @return a NotificationSettings instance containing the updated global notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public NotificationSettings updateGlobalNotificationSettings(NotificationSettings settings) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("level",  settings.getLevel())
                .withParam("email",  settings.getEmail());        

        Events events = settings.getEvents();
        if (events != null) {
                formData.withParam("new_note", events.getNewNote())
                .withParam("new_issuee", events.getNewIssue())
                .withParam("reopen_issuee", events.getReopenIssue())
                .withParam("close_issuee", events.getCloseIssue())
                .withParam("reassign_issuee", events.getReassignIssue())
                .withParam("new_merge_requeste", events.getNewMergeRequest())
                .withParam("reopen_merge_requeste", events.getReopenMergeRequest())
                .withParam("close_merge_requeste", events.getCloseMergeRequest())
                .withParam("reassign_merge_requeste", events.getReassignMergeRequest())
                .withParam("merge_merge_requeste", events.getMergeMergeRequest())
                .withParam("failed_pipelinee", events.getFailedPipeline())
                .withParam("success_pipelinee", events.getSuccessPipeline());
        }

        Response response = put(Response.Status.OK, formData.asMap(), "notification_settings");
        return (response.readEntity(NotificationSettings.class));
    }

    /**
     * Get the notification settings for a group.
     * 
     * GET /groups/:id/notification_settings
     *
     * @param groupId the group ID to get the notification settings for
     * @return a NotificationSettings instance containing the specified group's notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public NotificationSettings getGroupNotificationSettings(int groupId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "groups", groupId, "notification_settings");
        return (response.readEntity(NotificationSettings.class));
    }

    /**
     * Update the notification settings for a group
     * 
     * PUT /groups/:id/notification_settings
     *
     * @param groupId the group ID to update the notification settings for
     * @param settings a NotificationSettings instance with the new settings
     * @return a NotificationSettings instance containing the updated group notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public NotificationSettings updateGroupNotificationSettings(int groupId, NotificationSettings settings) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("level",  settings.getLevel())
                .withParam("email",  settings.getEmail());        

        Events events = settings.getEvents();
        if (events != null) {
                formData.withParam("new_note", events.getNewNote())
                .withParam("new_issuee", events.getNewIssue())
                .withParam("reopen_issuee", events.getReopenIssue())
                .withParam("close_issuee", events.getCloseIssue())
                .withParam("reassign_issuee", events.getReassignIssue())
                .withParam("new_merge_requeste", events.getNewMergeRequest())
                .withParam("reopen_merge_requeste", events.getReopenMergeRequest())
                .withParam("close_merge_requeste", events.getCloseMergeRequest())
                .withParam("reassign_merge_requeste", events.getReassignMergeRequest())
                .withParam("merge_merge_requeste", events.getMergeMergeRequest())
                .withParam("failed_pipelinee", events.getFailedPipeline())
                .withParam("success_pipelinee", events.getSuccessPipeline());
        }

        Response response = put(Response.Status.OK, formData.asMap(), "groups", groupId, "notification_settings");
        return (response.readEntity(NotificationSettings.class));
    }

    /**
     * Get the notification settings for a project.
     * 
     * GET /projects/:id/notification_settings
     *
     * @param projectId the project ID to get the notification settings for
     * @return a NotificationSettings instance containing the specified project's notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public NotificationSettings getProjectNotificationSettings(int projectId) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "notification_settings");
        return (response.readEntity(NotificationSettings.class));
    }

    /**
     * Update the notification settings for a project
     * 
     * PUT /projects/:id/notification_settings
     *
     * @param projectId the project ID to update the notification settings for
     * @param settings a NotificationSettings instance with the new settings
     * @return a NotificationSettings instance containing the updated project notification settings
     * @throws GitLabApiException if any exception occurs
     */
    public NotificationSettings updateProjectNotificationSettings(int projectId, NotificationSettings settings) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("level",  settings.getLevel())
                .withParam("email",  settings.getEmail());        

        Events events = settings.getEvents();
        if (events != null) {
                formData.withParam("new_note", events.getNewNote())
                .withParam("new_issuee", events.getNewIssue())
                .withParam("reopen_issuee", events.getReopenIssue())
                .withParam("close_issuee", events.getCloseIssue())
                .withParam("reassign_issuee", events.getReassignIssue())
                .withParam("new_merge_requeste", events.getNewMergeRequest())
                .withParam("reopen_merge_requeste", events.getReopenMergeRequest())
                .withParam("close_merge_requeste", events.getCloseMergeRequest())
                .withParam("reassign_merge_requeste", events.getReassignMergeRequest())
                .withParam("merge_merge_requeste", events.getMergeMergeRequest())
                .withParam("failed_pipelinee", events.getFailedPipeline())
                .withParam("success_pipelinee", events.getSuccessPipeline());
        }

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "notification_settings");
        return (response.readEntity(NotificationSettings.class));
    }
}
