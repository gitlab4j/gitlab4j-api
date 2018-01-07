
package org.gitlab4j.api;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides a base class handler for processing GitLab Web Hook and System Hook callouts.
 */
public abstract class HookManager {

    private String secretToken;

    /**
     * Create a HookManager to handle GitLab hook events.
     */
    public HookManager() {
        this.secretToken = null;
    }

    /**
     * Create a HookManager to handle GitLab hook events which will be verified
     * against the specified secretToken.
     * 
     * @param secretToken the secret token to verify against
     */
    public HookManager(String secretToken) {
        this.secretToken = secretToken;
    }

    /**
     * Set the secret token that received hook events should be validated against.
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
    public abstract void handleEvent(HttpServletRequest request) throws GitLabApiException;
}