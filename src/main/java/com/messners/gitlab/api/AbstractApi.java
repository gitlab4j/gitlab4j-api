package com.messners.gitlab.api;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URL;

/**
 * This class is the base class for all the sub API classes. It provides implementations of
 * delete(), get(), post() and put() that are re-used by all the sub-classes.
 * 
 * @author Greg Messner <greg@messners.com>
 *
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
     * @param queryParams
     * @param pathArgs
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param queryParams
     * @param url
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param formData
     * @param pathArgs
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param queryParams
     * @param pathArgs
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param formData
     * @param url
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param queryParams
     * @param pathArgs
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param queryParams
     * @param url
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param queryParams
     * @param pathArgs
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param queryParams
     * @param url
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws GitLabApiException
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
     * @param formData
     * @param name
     * @param value
     */
    protected void addFormParam(Form formData, String name, Object value) throws IllegalArgumentException {
        addFormParam(formData, name, value, false);
    }

    /**
     * Convenience method for adding query and form parameters to a get() or post() call.
     * If required is true and value is null, will throw an IllegalArgumentException.
     * 
     * @param formData
     * @param name
     * @param value
     * @param required
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
     * @throws GitLabApiException in case of unexpected response status
     */
    protected Response validate(Response response, Response.Status expected) throws GitLabApiException {
        if (response.getStatus() != expected.getStatusCode()) {
            throw new GitLabApiException(response);
        }

        return (response);
    }

    /**
     * Wraps exception if needed
     * 
     * @param thrown exception
     * @return never returns
     * @throws GitLabApiException always
     */
    protected GitLabApiException handle(Exception thrown) throws GitLabApiException {

        if (thrown instanceof GitLabApiException) {
            throw (GitLabApiException) thrown;
        }

        throw new GitLabApiException(thrown);
    }
}
