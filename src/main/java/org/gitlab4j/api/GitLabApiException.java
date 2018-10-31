package org.gitlab4j.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * This is the exception that will be thrown if any exception occurs while communicating
 * with a GitLab API endpoint.
 */
public class GitLabApiException extends Exception {

    private static final long serialVersionUID = 1L;
    private StatusType statusInfo;
    private int httpStatus;
    private String message;
    private Map<String, List<String>> validationErrors;
    
    /**
     * Create a GitLabApiException instance with the specified message.
     *
     * @param message the message for the exception
     */
    public GitLabApiException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Create a GitLabApiException instance based on the ClientResponse.
     *
     * @param response the JAX-RS response that caused the exception
     */
    public GitLabApiException(Response response) {

        super();
        statusInfo = response.getStatusInfo();
        httpStatus = response.getStatus();

        if (response.hasEntity()) {

            try {

                String message = response.readEntity(String.class);
                this.message = message;

                // Determine what is in the content of the response and process it accordingly
                MediaType mediaType = response.getMediaType();
                if (mediaType != null && "json".equals(mediaType.getSubtype())) {

                    JsonNode json = JacksonJson.toJsonNode(message);

                    // First see if it is a "message", if so it is either a simple message,
                    // or a Map<String, List<String>> of validation errors
                    JsonNode jsonMessage = json.get("message");
                    if (jsonMessage != null) {

                        // If the node is an object, then it is validation errors
                        if (jsonMessage.isObject()) {

                            StringBuilder buf = new StringBuilder();
                            validationErrors = new HashMap<>();
                            Iterator<Entry<String, JsonNode>> fields = jsonMessage.fields();
                            while(fields.hasNext()) {

                                Entry<String, JsonNode> field = fields.next();
                                String fieldName = field.getKey();                                
                                List<String> values = new ArrayList<>();
                                validationErrors.put(fieldName, values);
                                for (JsonNode value : field.getValue()) {
                                    values.add(value.asText());
                                }

                                if (values.size() > 0) {
                                    buf.append((buf.length() > 0 ? ", " : "")).append(fieldName);
                                }
                            }

                            if (buf.length() > 0) {
                                this.message = "The following fields have validation errors: " + buf.toString();
                            }    

                        } else {
                            this.message = jsonMessage.asText();
                        }

                    } else {

                        JsonNode jsonError = json.get("error");
                        if (jsonError != null) {
                            this.message = jsonError.asText();
                        }
                    }
                }

            } catch (Exception ignore) {
            }
        }
    }

    /**
     * Create a GitLabApiException instance based on the exception.
     *
     * @param e the Exception to wrap
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
     * causing error was not an HTTP related exception
     *
     * @return the HTTP status code, returns 0 if the causing error was not an HTTP related exception
     */
    public final int getHttpStatus() {
        return (httpStatus);
    }

    /**
     * Returns true if this GitLabApiException was caused by validation errors on the GitLab server,
     * otherwise returns false.
     *
     * @return true if this GitLabApiException was caused by validation errors on the GitLab server,
     * otherwise returns false
     */
    public boolean hasValidationErrors() {
        return (validationErrors != null);
    }

    /**
     * Returns a Map&lt;String, List&lt;String&gt;&gt; instance containing validation errors if this GitLabApiException 
     * was caused by validation errors on the GitLab server, otherwise returns null.
     *
     * @return a Map&lt;String, List&lt;String&gt;&gt; instance containing validation errors if this GitLabApiException 
     * was caused by validation errors on the GitLab server, otherwise returns null
     */
    public Map<String, List<String>> getValidationErrors() {
        return (validationErrors);
    }
}
