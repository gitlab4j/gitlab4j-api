
package org.gitlab4j.api.systemhooks;

/**
 * This class defines an event listener for the event fired when
 * a System Hook notification has been received from a GitLab server.
 */
public interface SystemHookListener extends java.util.EventListener {

    /**
     * This method is called when a System Hook prject event has been received.
     *
     * @param event the ProjectSystemHookEvent instance
     */
    public void onProjectEvent(ProjectSystemHookEvent event);

    /**
     * This method is called when a System Hook team member event has been received.
     *
     * @param event the TeamMemberSystemHookEvent instance containing info on the team member event
     */
    public void onTeamMemberEvent(TeamMemberSystemHookEvent event);
}
