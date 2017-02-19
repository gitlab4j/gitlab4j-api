package org.gitlab4j.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.gitlab4j.api.models.ErrorMessage;

/**
 * This is the exception that will be thrown if any exception occurs while communicating
 * with a GitLab API endpoint.
 */
public class GitLabApiException extends Exception {

    private static final long serialVersionUID = 1L;
    private StatusType statusInfo;
    private int httpStatus;
    private String message;

    /**
     * Create a GitLabApiException instance based on the ClientResponse.
     * 
     * @param response
     */
    public GitLabApiException(Response response) {

        super();
        statusInfo = response.getStatusInfo();
        httpStatus = response.getStatus();

        if (response.hasEntity()) {
            try {

                ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
                message = errorMessage.getMessage();

            } catch (Exception ignore) {
            }
        }
    }

    /**
     * Create a GitLabApiException instance based on the exception.
     * 
     * @param e
     */
    public GitLabApiException(Exception e) {
        super(e);
        message = e.getMessage();
    }

    /**
     * Get the message associated with the exception.
     * 
     * @return the message associated with the exception
     */
    @Override
    public final String getMessage() {
        return (message != null ? message : getReason());
    }

    /**
     * Returns the HTTP status reason message, returns null if the
     * causing error was not an HTTP related exception.
     * 
     * @return the HTTP status reason message
     */
    public final String getReason() {
        return (statusInfo != null ? statusInfo.getReasonPhrase() : null);
    }

    /**
     * Returns the HTTP status code that was the cause of the exception. returns 0 if the
     * causing error was not an HTTP related exception.
     * 
     * @return the HTTP status code, returns 0 if the causing error was not an HTTP related exception
     */
    public final int getHttpStatus() {
        return (httpStatus);
    }
}
