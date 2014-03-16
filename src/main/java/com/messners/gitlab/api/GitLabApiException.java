package com.messners.gitlab.api;

import javax.ws.rs.core.Response.StatusType;

import com.messners.gitlab.api.models.ErrorMessage;
import com.sun.jersey.api.client.ClientResponse;

public class GitLabApiException extends Exception {

	private static final long serialVersionUID = 1L;
	private StatusType statusInfo;
	private String message;
	
	/**
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
	 * 
	 */
	@Override
	public final String getMessage () {		
		return (message != null ? message : statusInfo.getReasonPhrase());
	}	

	
	/**
	 * 
	 * @return
	 */
	public final String getReason () {		
		return (statusInfo.getReasonPhrase());
	}
	
	
	/**
	 * 
	 * @return
	 */
	public final int getHttpStatus () {		
		return (statusInfo.getStatusCode());
	}
}
