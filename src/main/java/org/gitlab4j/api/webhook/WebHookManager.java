
package org.gitlab4j.api.webhook;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.utils.HttpRequestUtils;
import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * This class provides a handler for processing GitLab WebHook callouts.
 */
public class WebHookManager {

    private final static Logger LOG = Logger.getLogger(WebHookManager.class.getName());
    private final JacksonJson jacksonJson = new JacksonJson();

    private String secretToken;

    // Collection of objects listening for WebHook events.
    private final List<WebHookListener> webhookListeners = new CopyOnWriteArrayList<WebHookListener>();

    /**
     * Create a WebHookManager to handle GitLab webhook events.
     */
    public WebHookManager() {
        this.secretToken = null;
    }

    /**
     * Create a WebHookManager to handle GitLab webhook events which will be verified
     * against the specified secretToken.
     * 
     * @param secretToken the secret token to verify against
     */
    public WebHookManager(String secretToken) {
        this.secretToken = secretToken;
    }

    /**
     * Set the secret token that received webhook events should be validated against.
     *
     * @param secretToken the secret token to verify against
     */
    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    /**
     * Validate the provided secret token against the reference secret token. Returns true if
     * the secret token is valid or there is no reference secret token to validate against,
     * otherwise returns false.
     * 
     * @param secretToken the token to validate
     * @return true if the secret token is valid or there is no reference secret token to validate against
     */
    public boolean isValidSecretToken(String secretToken) {
        return (this.secretToken == null || this.secretToken.equals(secretToken) ? true : false);
    }

    /**
     * Validate the provided secret token found in the HTTP header against the reference secret token.
     * Returns true if the secret token is valid or there is no reference secret token to validate
     * against, otherwise returns false.
     * 
     * @param request the HTTP request to verify the secret token
     * @return true if the secret token is valid or there is no reference secret token to validate against
     */
    public boolean isValidSecretToken(HttpServletRequest request) {

        if (this.secretToken != null) {
            String secretToken = request.getHeader("X-Gitlab-Token");
            return (isValidSecretToken(secretToken));
        }

        return (true);
    }

    /**
     * Parses and verifies an Event instance from the HTTP request and
     * fires it off to the registered listeners.
     * 
     * @param request the HttpServletRequest to read the Event instance from
     * @throws GitLabApiException if the parsed event is not supported
     */
    public void handleEvent(HttpServletRequest request) throws GitLabApiException {

        if (!isValidSecretToken(request)) {
            String message = "X-Gitlab-Token mismatch!";
            LOG.warning(message);
            throw new GitLabApiException(message);
        }

        String eventName = request.getHeader("X-Gitlab-Event");
        LOG.info("handleEvent: X-Gitlab-Event=" + eventName);
        switch (eventName) {

        case BuildEvent.X_GITLAB_EVENT:
        case IssueEvent.X_GITLAB_EVENT:
        case MergeRequestEvent.X_GITLAB_EVENT:
        case NoteEvent.X_GITLAB_EVENT:
        case PipelineEvent.X_GITLAB_EVENT:
        case PushEvent.X_GITLAB_EVENT:
        case TagPushEvent.X_GITLAB_EVENT:
        case WikiPageEvent.X_GITLAB_EVENT:
            break;

        default:
            String message = "Unsupported X-Gitlab-Event, event Name=" + eventName;
            LOG.warning(message);
            throw new GitLabApiException(message);
        }

        String errorMessage = null;
        try {

            Event event;
            if (LOG.isLoggable(Level.FINE)) {
                LOG.fine(HttpRequestUtils.getShortRequestDump(eventName + " webhook", true, request));
                String postData = HttpRequestUtils.getPostDataAsString(request);
                LOG.fine("Raw POST data:\n" + postData);
                event = jacksonJson.unmarshal(Event.class, postData);
                LOG.fine(event.getObjectKind() + " event:\n" + jacksonJson.marshal(event) + "\n");
            } else {
                InputStreamReader reader = new InputStreamReader(request.getInputStream());
                event = jacksonJson.unmarshal(Event.class, reader);
            }

            fireEvent(event);

        } catch (JsonParseException jpe) {
            errorMessage = jpe.getMessage();
            LOG.warning("Error parsing JSON data, error=" + errorMessage);
        } catch (JsonMappingException jme) {
            errorMessage = jme.getMessage();
            LOG.warning("Error mapping JSON data, error=" + errorMessage);
        } catch (IOException ioe) {
            errorMessage = ioe.getMessage();
            LOG.warning("Error reading JSON data, error=" + errorMessage);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            LOG.warning("Unexpected error reading JSON data, error=" + errorMessage);
        }

        if (errorMessage != null)
            throw new GitLabApiException(errorMessage);
    }

    /**
     * Verifies the provided Event and fires it off to the registered listeners.
     * 
     * @param event the Event instance to handle
     * @throws GitLabApiException if the event is not supported
     */
    public void handleEvent(Event event) throws GitLabApiException {

        LOG.info("handleEvent: object_kind=" + event.getObjectKind());

        switch (event.getObjectKind()) {
        case BuildEvent.OBJECT_KIND:
        case IssueEvent.OBJECT_KIND:
        case MergeRequestEvent.OBJECT_KIND:
        case NoteEvent.OBJECT_KIND:
        case PipelineEvent.OBJECT_KIND:
        case PushEvent.OBJECT_KIND:
        case TagPushEvent.OBJECT_KIND:
        case WikiPageEvent.OBJECT_KIND:
            break;

        default:
            String message = "Unsupported event object_kind, object_kind=" + event.getObjectKind();
            LOG.warning(message);
            throw new GitLabApiException(message);
        }

        fireEvent(event);
    }

    /**
     * Adds a WebHook event listener.
     *
     * @param listener the WebHookListener to add
     */
    public void addListener(WebHookListener listener) {

        if (!webhookListeners.contains(listener)) {
            webhookListeners.add(listener);
        }
    }

    /**
     * Removes a WebHook event listener.
     *
     * @param listener the WebHookListener to remove
     */
    public void removeListener(WebHookListener listener) {
        webhookListeners.remove(listener);
    }

    /**
     * Fire the event to the registered listeners.
     * 
     * @param event the Event instance to fire to the registered event listeners
     * @throws GitLabApiException if the event is not supported
     */
    public void fireEvent(Event event) throws GitLabApiException {

        switch (event.getObjectKind()) {
        case BuildEvent.OBJECT_KIND:
            fireBuildEvent((BuildEvent) event);
            break;

        case IssueEvent.OBJECT_KIND:
            fireIssueEvent((IssueEvent) event);
            break;

        case MergeRequestEvent.OBJECT_KIND:
            fireMergeRequestEvent((MergeRequestEvent) event);
            break;

        case NoteEvent.OBJECT_KIND:
            fireNoteEvent((NoteEvent) event);
            break;

        case PipelineEvent.OBJECT_KIND:
            firePipelineEvent((PipelineEvent) event);
            break;

        case PushEvent.OBJECT_KIND:
            firePushEvent((PushEvent) event);
            break;

        case TagPushEvent.OBJECT_KIND:
            fireTagPushEvent((TagPushEvent) event);
            break;

        case WikiPageEvent.OBJECT_KIND:
            fireWikiPageEvent((WikiPageEvent) event);
            break;

        default:
            String message = "Unsupported event object_kind, object_kind=" + event.getObjectKind();
            LOG.warning(message);
            throw new GitLabApiException(message);
        }
    }

    protected void fireBuildEvent(BuildEvent buildEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onBuildEvent(buildEvent);
        }
    }

    protected void fireIssueEvent(IssueEvent issueEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onIssueEvent(issueEvent);
        }
    }

    protected void fireMergeRequestEvent(MergeRequestEvent mergeRequestEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onMergeRequestEvent(mergeRequestEvent);
        }
    }

    protected void fireNoteEvent(NoteEvent noteEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onNoteEvent(noteEvent);
        }
    }

    protected void firePipelineEvent(PipelineEvent pipelineEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onPipelineEvent(pipelineEvent);
        }
    }

    protected void firePushEvent(PushEvent pushEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onPushEvent(pushEvent);
        }
    }

    protected void fireTagPushEvent(TagPushEvent tagPushEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onTagPushEvent(tagPushEvent);
        }
    }

    protected void fireWikiPageEvent(WikiPageEvent wikiPageEvent) {

        for (WebHookListener listener : webhookListeners) {
            listener.onWikiPageEvent(wikiPageEvent);
        }
    }
}
