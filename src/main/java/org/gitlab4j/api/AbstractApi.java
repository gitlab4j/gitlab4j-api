package org.gitlab4j.api;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URL;

/**
 * This class is the base class for all the sub API classes. It provides implementations of
 * delete(), get(), post() and put() that are re-used by all the sub-classes.
 */
public abstract class AbstractApi {

    private GitLabApi gitLabApi;

    public AbstractApi(GitLabApi gitLabApi) {
        this.gitLabApi = gitLabApi;
    }

    protected GitLabApiClient getApiClient() {
        return (gitLabApi.getApiClient());
    }

    /**
     * Perform an HTTP GET call with the specified query parameters and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response get(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object... pathArgs) throws GitLabApiException {
        try {
            return validate(getApiClient().get(queryParams, pathArgs), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP GET call with the specified query parameters and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response get(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, URL url) throws GitLabApiException {
        try {
            return validate(getApiClient().get(queryParams, url), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP POST call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param formData the Form containing the name/value pairs for the POST data
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response post(Response.Status expectedStatus, Form formData, Object... pathArgs) throws GitLabApiException {
        try {
            return validate(getApiClient().post(formData, pathArgs), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP POST call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response post(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object... pathArgs) throws GitLabApiException {
        try {
            return validate(getApiClient().post(queryParams, pathArgs), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP POST call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param formData the Form containing the name/value pairs for the POST data
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response post(Response.Status expectedStatus, Form formData, URL url) throws GitLabApiException {
        try {
            return validate(getApiClient().post(formData, url), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP PUT call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response put(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object... pathArgs) throws GitLabApiException {
        try {
            return validate(getApiClient().put(queryParams, pathArgs), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP PUT call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response put(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, URL url) throws GitLabApiException {
        try {
            return validate(getApiClient().put(queryParams, url), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP DELETE call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     *
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response delete(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object... pathArgs) throws GitLabApiException {
        try {
            return validate(getApiClient().delete(queryParams, pathArgs), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Perform an HTTP DELETE call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param expectedStatus the HTTP status that should be returned from the server
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException if any exception occurs during execution
     */
    protected Response delete(Response.Status expectedStatus, MultivaluedMap<String, String> queryParams, URL url) throws GitLabApiException {
        try {
            return validate(getApiClient().delete(queryParams, url), expectedStatus);
        } catch (Exception e) {
            throw handle(e);
        }
    }

    /**
     * Convenience method for adding query and form parameters to a get() or post() call.
     * 
     * @param formData the Form containing the name/value pairs
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     */
    protected void addFormParam(Form formData, String name, Object value) throws IllegalArgumentException {
        addFormParam(formData, name, value, false);
    }

    /**
     * Convenience method for adding query and form parameters to a get() or post() call.
     * If required is true and value is null, will throw an IllegalArgumentException.
     * 
     * @param formData the Form containing the name/value pairs
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     * @param required the field is required flag
     * @throws IllegalArgumentException if a required parameter is null or empty
     */
    protected void addFormParam(Form formData, String name, Object value, boolean required) throws IllegalArgumentException {

        if (value == null) {

            if (required) {
                throw new IllegalArgumentException(name + " cannot be empty or null");
            }

            return;
        }

        String stringValue = value.toString().trim();
        if (required && stringValue.length() == 0) {
            throw new IllegalArgumentException(name + " cannot be empty or null");
        }

        formData.param(name, stringValue);
    }

    /**
     * Validates response.
     * 
     * @param response response
     * @param expected expected respone status
     * @return original response if the response status is expected
     * @throws GitLabApiException if HTTP status is not as expected, or the secret token doesn't match
     */
    protected Response validate(Response response, Response.Status expected) throws GitLabApiException {
        if (response.getStatus() != expected.getStatusCode()) {
            throw new GitLabApiException(response);
        }

        if (!getApiClient().validateSecretToken(response)) {
            throw new GitLabApiException(new NotAuthorizedException("Invalid secret token in response."));
        }

        return (response);
    }

    /**
     * Wraps exception if needed
     * 
     * @param thrown the exception that should be wrapped 
     * @throws GitLabApiException containing the cause or GitLab API specific message
     */
    protected GitLabApiException handle(Exception thrown) throws GitLabApiException {

        if (thrown instanceof GitLabApiException) {
            throw (GitLabApiException) thrown;
        }

        throw new GitLabApiException(thrown);
    }
}
