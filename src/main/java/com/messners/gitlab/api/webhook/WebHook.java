
package com.messners.gitlab.api.webhook;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.messners.gitlab.api.JacksonJsonConfig;
import com.messners.gitlab.api.event.EventObject;
import com.messners.gitlab.api.event.PushEvent;
import com.messners.gitlab.api.event.WebHookListener;

/**
 * 	WebHookCallback
 * 
 *  This class provides endpoint for GitLab WebHook callouts.
 * 
 * @author Greg Messner <greg@messners.com>
 */
@Path("/gitlab/webhook")
public class WebHook {
		
	@Context HttpServletRequest request;
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/issue")
	public void issue () {		
		
		try {
			
			EventObject issueEvent = WebHook.unmarshall(EventObject.class, request.getReader());
			WebHook.fireIssueEvent(issueEvent);
			
		} catch (JsonParseException jpe) {
			System.err.println("Error parsing JSON data, error=" + jpe.getMessage());
		} catch (JsonMappingException jme) {
			System.err.println("Error mapping JSON data, error=" + jme.getMessage());
		} catch (IOException ioe) {
			System.err.println("Error reading JSON data, error=" + ioe.getMessage());
		}		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/merge-request")
	public void megeRequest () {
		
		try {
			
			EventObject mergeRequest = WebHook.unmarshall(EventObject.class, request.getReader());;
			WebHook.fireMergeRequestEvent(mergeRequest);
		
		} catch (JsonParseException jpe) {
			System.err.println("Error parsing JSON data, error=" + jpe.getMessage());
		} catch (JsonMappingException jme) {
			System.err.println("Error mapping JSON data, error=" + jme.getMessage());
		} catch (IOException ioe) {
			System.err.println("Error reading JSON data, error=" + ioe.getMessage());
		}		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/push")
	public void push () {
		
		try {
			
			PushEvent pushEvent = WebHook.unmarshall(PushEvent.class, request.getReader());
			WebHook.firePushEvent(pushEvent);
		
		} catch (JsonParseException jpe) {
			System.err.println("Error parsing JSON data, error=" + jpe.getMessage());
		} catch (JsonMappingException jme) {
			System.err.println("Error mapping JSON data, error=" + jme.getMessage());
		} catch (IOException ioe) {
			System.err.println("Error reading JSON data, error=" + ioe.getMessage());
		}		
	}
	
	private static final JacksonJsonConfig jacksonJsonConfig = new JacksonJsonConfig();	
	private static <T> T unmarshall (Class<T> returnType, Reader reader) 
			throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper objectMapper = jacksonJsonConfig.getContext(returnType);
		return (objectMapper.readValue(reader,  returnType));	
	}
	
	
	/**
	 * Collection of objects listening for WebHook events.
	 */
	private static ArrayList<WebHookListener> webhookListeners = new ArrayList<WebHookListener>();

	/**
	 * Adds a WebHook event listener.
	 *
	 * @param  l  the SMTPStatusListener to add
	 */
	public static synchronized void addListener (WebHookListener listener) {

		if (!webhookListeners.contains(listener)) {
			webhookListeners.add(listener);
		}
	}


	/**
	 * Removes a SMTP event listener.
	 *
	 * @param  l  the SMTPStatusListener to remove
	 */
	public static synchronized void removeListener (WebHookListener listener) {
	   	webhookListeners.remove(listener);
	}
	
	
	/**
	 * Make a copy of the listener object ArrayList so that it cannot
	 * be changed while we are firing events
	 * 
	 * @return a copy of the WebHookListener list, or null if no listeners have been added
	 */
	protected static synchronized List<WebHookListener> getListenerListCopy () {
		
		synchronized (WebHook.class) {
			
			if (webhookListeners.size() < 1) {
				return (null);
			}

			List<WebHookListener> listeners = new ArrayList<WebHookListener>(webhookListeners.size());
			for (WebHookListener listener : webhookListeners) {
				listeners.add(listener);
			}
			
			return (listeners);
	  	}
	}

	
	protected static void fireIssueEvent (EventObject issueEvent) {

		List<WebHookListener> listeners = getListenerListCopy();
		if (listeners == null) {
			return;
		}

	  	/*
		 * Fire the event to all listeners
		 */
		for (WebHookListener listener : listeners) {
			listener.onIssue(issueEvent);
		}
	}
	
	
	protected static void fireMergeRequestEvent (EventObject mergeRequestEvent) {

		List<WebHookListener> listeners = getListenerListCopy();
		if (listeners == null) {
			return;
		}

	  	/*
		 * Fire the event to all listeners
		 */
		for (WebHookListener listener : listeners) {
			listener.onMergeRequest(mergeRequestEvent);
		}
	}
	
	
	protected static void firePushEvent (PushEvent pushEvent) {

		List<WebHookListener> listeners = getListenerListCopy();
		if (listeners == null) {
			return;
		}

	  	/*
		 * Fire the event to all listeners
		 */
		for (WebHookListener listener : listeners) {
			listener.onPush(pushEvent);
		}
	}	
}
