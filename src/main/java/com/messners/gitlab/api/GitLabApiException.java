package com.messners.gitlab.api;

import javax.ws.rs.core.Response.StatusType;

import com.messners.gitlab.api.models.ErrorMessage;
import com.sun.jersey.api.client.ClientResponse;

public class GitLabApiException extends Exception {

	private static final long serialVersionUID = 1L;
	private StatusType statusInfo;
	private String message;
	
	/**
	 * Create a GitLabApiException based on the ClientResponse.
	 * 
	 * @param response
	 */
	public GitLabApiException (ClientResponse response) {
		
		super();
		statusInfo = response.getStatusInfo();

		if (response.hasEntity()) {
			try {
			
				ErrorMessage errorMessage = response.getEntity(ErrorMessage.class);
				message = errorMessage.getMessage();
				
			} catch (Exception ignore) {}
		}		
	}

	
	public GitLabApiException (Exception e) {
		super(e);
		message = e.getMessage();
	}
	
	
	/**
	 * Get the message associated with the exception.
	 * 
	 * @return the message associated with the exception
	 */
	@Override
	public final String getMessage () {		
		return (message != null ? message : getReason());
	}	

	
	/**
	 * Returns the HTTP status reason message, returns null if the
	 * causing error was not an HTTP related exception.
	 * 
	 * @return
	 */
	public final String getReason () {		
		return (statusInfo != null ? statusInfo.getReasonPhrase() : null);
	}
	
	
	/**
	 * Returns the HTTP status code.  returns 0 if the
	 * causing error was not an HTTP related exception.
	 *  
	 * @return
	 */
	public final int getHttpStatus () {		
		return (statusInfo != null ? statusInfo.getStatusCode() : 0);
	}
}
