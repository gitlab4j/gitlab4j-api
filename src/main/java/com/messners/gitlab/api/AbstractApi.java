package com.messners.gitlab.api;

import java.io.IOException;
import java.net.URL;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
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
	 * @param queryParams
	 * @param pathArgs
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 * @throws IOException
	 */
	protected  ClientResponse get (MultivaluedMap<String, String> queryParams, Object ... pathArgs) 
			throws UniformInterfaceException, ClientHandlerException, IOException {		
		return (getApiClient().get(queryParams, pathArgs));	
	}	
	
	
	/**
	 * Perform an HTTP GET call with the specified query parameters and URL, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 * 
	 * @param queryParams
	 * @param url
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 */
	protected ClientResponse get (MultivaluedMap<String, String> queryParams, URL url) 
			throws UniformInterfaceException, ClientHandlerException {		
		return (getApiClient().get(queryParams, url));	
	}		
	
	
	/**
	 * Perform an HTTP POST call with the specified form data and path objects, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 * 
	 * @param formData
	 * @param pathArgs
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 * @throws IOException
	 */
	protected ClientResponse post (Form formData, Object ... pathArgs) 
			throws UniformInterfaceException, ClientHandlerException, IOException {		
		return (getApiClient().post(formData, pathArgs));		
	}
	
	
	/**
	 * Perform an HTTP POST call with the specified form data and URL, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 * 
	 * @param formData
	 * @param url
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 */
	protected ClientResponse post (Form formData, URL url) throws UniformInterfaceException, ClientHandlerException {
		return (getApiClient().post(formData, url));		
	}
	
	
	
	/**
	 * Perform an HTTP PUT call with the specified form data and path objects, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 * 
	 * @param queryParams
	 * @param pathArgs
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 * @throws IOException
	 */
	protected  ClientResponse put (MultivaluedMap<String, String> queryParams, Object ... pathArgs)
			throws UniformInterfaceException, ClientHandlerException, IOException {
		return (getApiClient().put(queryParams, pathArgs));	
	}	
	
	
	/**
	 * Perform an HTTP PUT call with the specified form data and URL, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 *  
	 * @param queryParams
	 * @param url
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 */
	protected ClientResponse put (MultivaluedMap<String, String> queryParams, URL url) 
			throws UniformInterfaceException, ClientHandlerException {		
		return (getApiClient().put(queryParams, url));
	}
	
	
	
	/**
	 * Perform an HTTP DELETE call with the specified form data and path objects, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 * 
	 * @param queryParams
	 * @param pathArgs
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 * @throws IOException
	 */
	protected  ClientResponse delete (MultivaluedMap<String, String> queryParams, Object ... pathArgs)
			throws UniformInterfaceException, ClientHandlerException, IOException {
		return (getApiClient().delete(queryParams, pathArgs));	
	}	
	
	
	/**
	 * Perform an HTTP DELETE call with the specified form data and URL, returning 
	 * a ClientResponse instance with the data returned from the endpoint.
	 *  
	 * @param queryParams
	 * @param url
	 * @return a ClientResponse instance with the data returned from the endpoint
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 */
	protected ClientResponse delete (MultivaluedMap<String, String> queryParams, URL url) 
			throws UniformInterfaceException, ClientHandlerException {		
		return (getApiClient().delete(queryParams, url));
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