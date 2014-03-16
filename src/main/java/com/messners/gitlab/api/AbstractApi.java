package com.messners.gitlab.api;

import java.net.URL;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.representation.Form;

public abstract class AbstractApi {

	private GitLabApi gitLabApi;
	
	public AbstractApi (GitLabApi gitLabApi) {
		this.gitLabApi = gitLabApi;
	}

	protected GitLabApiClient getApiClient () {
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
	protected  ClientResponse get (ClientResponse.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object ... pathArgs) 
			throws GitLabApiException {	
		
		try {
			
			ClientResponse response = getApiClient().get(queryParams, pathArgs);
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected ClientResponse get (ClientResponse.Status expectedStatus, MultivaluedMap<String, String> queryParams, URL url) 
			throws GitLabApiException {		
		
		try {
			
			ClientResponse response = getApiClient().get(queryParams, url);	
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected ClientResponse post (ClientResponse.Status expectedStatus, Form formData, Object ... pathArgs) throws GitLabApiException {
		
		try {
			
			ClientResponse response = getApiClient().post(formData, pathArgs);
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected ClientResponse post (ClientResponse.Status expectedStatus, Form formData, URL url) throws GitLabApiException {
		
		try {
			
			ClientResponse response = getApiClient().post(formData, url);
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected  ClientResponse put (ClientResponse.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object ... pathArgs) throws GitLabApiException {
		
		try {
			
			ClientResponse response = getApiClient().put(queryParams, pathArgs);	
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected ClientResponse put (ClientResponse.Status expectedStatus, MultivaluedMap<String, String> queryParams, URL url) throws GitLabApiException {	
		
		try {
			
			ClientResponse response = getApiClient().put(queryParams, url);
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected  ClientResponse delete (ClientResponse.Status expectedStatus, MultivaluedMap<String, String> queryParams, Object ... pathArgs)
			throws GitLabApiException {
		
		try {
			
			ClientResponse response = getApiClient().delete(queryParams, pathArgs);
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
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
	protected ClientResponse delete (ClientResponse.Status expectedStatus, MultivaluedMap<String, String> queryParams, URL url) throws GitLabApiException {
		
		try {
			
			ClientResponse response = getApiClient().delete(queryParams, url);
			if (response.getStatus() != expectedStatus.getStatusCode()) {
				throw (new GitLabApiException(response));			
			}
			
			return (response);
			
		} catch (Exception e) {
			throw (new GitLabApiException(e));
		}
	}
	
	
	/**
	 * 
	 * @param formData
	 * @param string
	 * @param email
	 */
	protected void addFormParam(Form formData, String name, Object value) throws IllegalArgumentException {
		addFormParam(formData, name, value, false);
	}
	
	
	/**
	 * 
	 * @param formData
	 * @param string
	 * @param email
	 * @param required
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
			
			formData.add(name, stringValue);		
	}
}