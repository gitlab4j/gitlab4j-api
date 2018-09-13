
package org.gitlab4j.api.systemhooks;

import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.HookManager;
import org.gitlab4j.api.utils.HttpRequestUtils;
import org.gitlab4j.api.utils.JacksonJson;

/**
 * This class provides a handler for processing GitLab System Hook callouts.
 */
public class SystemHookManager extends HookManager {

    public static final String SYSTEM_HOOK_EVENT = "System Hook";

    private final static Logger LOGGER = GitLabApi.getLogger();
    private final JacksonJson jacksonJson = new JacksonJson();

    // Collection of objects listening for System Hook events.
    private final List<SystemHookListener> systemHookListeners = new CopyOnWriteArrayList<SystemHookListener>();

    /**
     * Create a HookManager to handle GitLab system hook events.
     */
    public SystemHookManager() {
        super();
    }

    /**
     * Create a HookManager to handle GitLab system hook events which will be verified
     * against the specified secretToken.
     * 
     * @param secretToken the secret token to verify against
     */
    public SystemHookManager(String secretToken) {
        super(secretToken);
    }

    /**
     * Parses and verifies an SystemHookEvent instance from the HTTP request and
     * fires it off to the registered listeners.
     * 
     * @param request the HttpServletRequest to read the Event instance from
     * @throws GitLabApiException if the parsed event is not supported
     */
    public void handleEvent(HttpServletRequest request) throws GitLabApiException {

        String eventName = request.getHeader("X-Gitlab-Event");
        if (eventName == null || eventName.trim().isEmpty()) {
            String message = "X-Gitlab-Event header is missing!";
            LOGGER.warning(message);
            return;
        }

        if (!isValidSecretToken(request)) {
            String message = "X-Gitlab-Token mismatch!";
            LOGGER.warning(message);
            throw new GitLabApiException(message);
        }

        LOGGER.info("handleEvent: X-Gitlab-Event=" + eventName);
        if (!SYSTEM_HOOK_EVENT.equals(eventName)) {
            String message = "Unsupported X-Gitlab-Event, event Name=" + eventName;
            LOGGER.warning(message);
            throw new GitLabApiException(message);
        }

        try {

            SystemHookEvent event;
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.fine(HttpRequestUtils.getShortRequestDump("System Hook", true, request));
                String postData = HttpRequestUtils.getPostDataAsString(request);
                LOGGER.fine("Raw POST data:\n" + postData);
                event = jacksonJson.unmarshal(SystemHookEvent.class, postData);
                LOGGER.fine(event.getEventName() + "\n" + jacksonJson.marshal(event) + "\n");
            } else {
                InputStreamReader reader = new InputStreamReader(request.getInputStream());
                event = jacksonJson.unmarshal(SystemHookEvent.class, reader);
            }

            event.setRequestUrl(request.getRequestURL().toString());
            event.setRequestQueryString(request.getQueryString());
            fireEvent(event);

        } catch (Exception e) {
            LOGGER.warning("Error processing JSON data, exception=" +
                    e.getClass().getSimpleName() + ", error=" + e.getMessage());
            throw new GitLabApiException(e);
        }
    }

    /**
     * Verifies the provided Event and fires it off to the registered listeners.
     * 
     * @param event the Event instance to handle
     * @throws GitLabApiException if the event is not supported
     */
    public void handleEvent(SystemHookEvent event) throws GitLabApiException {
        if (event != null) {
            LOGGER.info("handleEvent:" + event.getClass().getSimpleName() + ", eventName=" + event.getEventName());
            fireEvent(event);
        } else {
            LOGGER.warning("handleEvent: provided event cannot be null!");
        }
    }

    /**
     * Adds a System Hook event listener.
     *
     * @param listener the SystemHookListener to add
     */
    public void addListener(SystemHookListener listener) {

        if (!systemHookListeners.contains(listener)) {
            systemHookListeners.add(listener);
        }
    }

    /**
     * Removes a System Hook event listener.
     *
     * @param listener the SystemHookListener to remove
     */
    public void removeListener(SystemHookListener listener) {
        systemHookListeners.remove(listener);
    }

    /**
     * Fire the event to the registered listeners.
     * 
     * @param event the SystemHookEvent instance to fire to the registered event listeners
     * @throws GitLabApiException if the event is not supported
     */
    public void fireEvent(SystemHookEvent event) throws GitLabApiException {

        if (event instanceof ProjectSystemHookEvent) {
            fireProjectEvent((ProjectSystemHookEvent) event);
        } else if (event instanceof TeamMemberSystemHookEvent) {
            fireTeamMemberEvent((TeamMemberSystemHookEvent) event);
        } else if (event instanceof UserSystemHookEvent) {
            fireUserEvent((UserSystemHookEvent) event);
        } else if (event instanceof KeySystemHookEvent) {
            fireKeyEvent((KeySystemHookEvent) event);
        } else if (event instanceof GroupSystemHookEvent) {
            fireGroupEvent((GroupSystemHookEvent) event);
        } else if (event instanceof GroupMemberSystemHookEvent) {
            fireGroupMemberEvent((GroupMemberSystemHookEvent) event);
        } else if (event instanceof PushSystemHookEvent) {
            firePushEvent((PushSystemHookEvent) event);
        } else if (event instanceof TagPushSystemHookEvent) {
            fireTagPushEvent((TagPushSystemHookEvent) event);
        } else if (event instanceof RepositorySystemHookEvent) {
            fireRepositoryEvent((RepositorySystemHookEvent) event);
        } else {
            String message = "Unsupported event, event_named=" + event.getEventName();
            LOGGER.warning(message);
            throw new GitLabApiException(message);
        }
    }

    protected void fireProjectEvent(ProjectSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onProjectEvent(event);
        }
    }

    protected void fireTeamMemberEvent(TeamMemberSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onTeamMemberEvent(event);
        }
    }

    protected void fireUserEvent(UserSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onUserEvent(event);
        }
    }

    protected void fireKeyEvent(KeySystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onKeyEvent(event);
        }
    }

    protected void fireGroupEvent(GroupSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onGroupEvent(event);
        }
    }

    protected void fireGroupMemberEvent(GroupMemberSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onGroupMemberEvent(event);
        }
    }

    protected void firePushEvent(PushSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onPushEvent(event);
        }
    }

    protected void fireTagPushEvent(TagPushSystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onTagPushEvent(event);
        }
    }

    protected void fireRepositoryEvent(RepositorySystemHookEvent event) {
        for (SystemHookListener listener : systemHookListeners) {
            listener.onRepositoryEvent(event);
        }
    }
}
