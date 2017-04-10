
package org.gitlab4j.api.webhook;

import org.gitlab4j.api.webhook.IssueEvent;
import org.gitlab4j.api.webhook.MergeRequestEvent;
import org.gitlab4j.api.webhook.PushEvent;

/**
 * This class defines an event listener for the event fired when
 * a WebHook notification has been received from a GitLab server.
 */
public interface WebHookListener extends java.util.EventListener {

    /**
     * This method is called when a WebHook issue event has been received.
     *
     * @param event the EventObject instance containing info on the issue
     */
    public void onIssueEvent(IssueEvent event);

    /**
     * This method is called when a WebHook merge request event has been received
     *
     * @param event the EventObject instance containing info on the merge request
     */
    public void onMergeRequestEvent(MergeRequestEvent event);

    /**
     * This method is called when a WebHook push event has been received.
     *
     * @param pushEvent the PushEvent instance
     */
    public void onPushEvent(PushEvent pushEvent);

    /**
     * This method is called when a WebHook tag push event has been received.
     *
     * @param tagPushEvent the TagPushEvent instance
     */
    public void onTagPushEvent(TagPushEvent tagPushEvent);

    /**
     * This method is called when a WebHook note event has been received.
     *
     * @param noteEvent theNoteEvent instance
     */
    public void onNoteEvent(NoteEvent noteEvent);

    /**
     * This method is called when a WebHook tag push event has been received.
     *
     * @param pushEvent the PushEvent instance
     */
    public void onJobEvent(PushEvent pushEvent);

    /**
     * This method is called when a WebHook pipeline event has been received.
     *
     * @param pushEvent the PushEvent instance
     */
    public void onPipelineEvent(PushEvent pushEvent);

    /**
     * This method is called when a WebHook wiki event has been received.
     *
     * @param pushEvent the PushEvent instance
     */
    public void onWikiEvent(PushEvent pushEvent);
}
