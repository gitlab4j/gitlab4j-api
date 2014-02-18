
package com.messners.gitlab.api.event;

/**
 * This class defines an event listener for the event fired when
 * a WebHook notification has been received from a GitLab server.
 *
 * @author  Greg Messner <gmessner@messners.com>
 */
public interface WebHookListener extends java.util.EventListener {
	
	/**
	 * This method is called when a WebHook issue notification has been received.
	 *
	 * @param event the EventObject instance containing info on the issue
	 */
	public void onIssue (EventObject event);


	/**.
	 * This method is called when a WebHook merge request notification has been received
	 *
	 * @param event the EventObject instance containing info on the merge request
	 */
	public void onMergeRequest (EventObject event);

	
	/**
	 * This method is called when a WebHook push notification has been received.
	 *
	 * @param pushEvent the PushEvent instance
	 */
	public void onPush (PushEvent pushEvent);	
}


