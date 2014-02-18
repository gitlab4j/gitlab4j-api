package com.messners.gitlab.api;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * This class utilizes the Jersey client package to communicate with a GitLab API endpoint.
 * 
 * @author gmessner
 */
public class GitLabApiClient {
	
	protected static final String PRIVATE_TOKEN_HEADER = "PRIVATE-TOKEN";
	protected static final String API_NAMESPACE = "/api/v3";
	
	private ClientConfig clientConfig;
	private Client apiClient;
	private String hostUrl;
	private String privateToken;

	public GitLabApiClient (String hostUrl, String privateToken) {	
		
		// Remove the trailing "/" from the hostUrl if present
		this.hostUrl = (hostUrl.endsWith("/") ? hostUrl.replaceAll("/$", "") : hostUrl) + API_NAMESPACE;
		this.privateToken = privateToken;
		
		clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonConfig.class);
		clientConfig.getFeatures().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, Boolean.TRUE);
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	}
	
	
	protected boolean ignoreCertificateErrors () {

		// Create a TrustManager that trusts all certificates
		TrustManager[ ] certs = new TrustManager[ ] {
            new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
			}
		};
    
		// Now set the default SSLSocketFactory to use the just created TrustManager
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, certs, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		} catch (GeneralSecurityException ex) {
			return (false);
		}
    
		// Set up a HostnameVerifier for Jersey to verify all hostnames
		try {
			
			clientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(					
			     new HostnameVerifier() {
			         @Override
			         public boolean verify(String s, SSLSession sslSession) {
			             return (true);
			         }
			     }
			 ));
			
		} catch (NoSuchAlgorithmException e) {
			return (false);
		}
		
		return (true);
	}
    
	
	
	protected URL getApiUrl (Object ... pathArgs) throws IOException {
		
		StringBuilder url = new StringBuilder();
		url.append(hostUrl);
		for (Object pathArg : pathArgs) {
			url.append("/");
			url.append(pathArg.toString());
		}
		
		return (new URL(url.toString()));
    }
	
	
	protected  ClientResponse get (MultivaluedMap<String, String> queryParams, Object ... pathArgs) 
			throws UniformInterfaceException, ClientHandlerException, IOException {		
		URL url = getApiUrl(pathArgs);
		return (get(queryParams, url));	
	}	
	
		
	protected ClientResponse get (MultivaluedMap<String, String> queryParams, URL url) 
			throws UniformInterfaceException, ClientHandlerException {
		
		if (apiClient == null) {
			apiClient = Client.create(clientConfig);
		}
		
		WebResource resource = apiClient.resource(url.toString());
		if (queryParams != null) {
			resource.queryParams(queryParams);
		}
		
		return (resource.header(PRIVATE_TOKEN_HEADER, privateToken)
			.accept(MediaType.APPLICATION_JSON)
			.get(ClientResponse.class));
	}		
	
	
	protected ClientResponse post (Form formData, Object ... pathArgs) 
			throws UniformInterfaceException, ClientHandlerException, IOException {		
		URL url = getApiUrl(pathArgs);
		return (post(formData, url));		
	}
	
		
	protected ClientResponse post (Form formData, URL url) throws UniformInterfaceException, ClientHandlerException {
		
		if (apiClient == null) {
			apiClient = Client.create(clientConfig);
		}
		
		return (apiClient.resource(url.toString())
			.header(PRIVATE_TOKEN_HEADER, privateToken)
			.accept(MediaType.APPLICATION_JSON)
			.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			.post(ClientResponse.class, formData));		
	}
	
	
	protected  ClientResponse delete (MultivaluedMap<String, String> queryParams, Object ... pathArgs) 
			throws UniformInterfaceException, ClientHandlerException, IOException {		
		URL url = getApiUrl(pathArgs);
		return (delete(queryParams, url));	
	}	
	
		
	protected ClientResponse delete (MultivaluedMap<String, String> queryParams, URL url) 
			throws UniformInterfaceException, ClientHandlerException {
		
		if (apiClient == null) {
			apiClient = Client.create(clientConfig);
		}
		
		WebResource resource = apiClient.resource(url.toString());
		if (queryParams != null) {
			resource.queryParams(queryParams);
		}
		
		return (resource.header(PRIVATE_TOKEN_HEADER, privateToken)
			.accept(MediaType.APPLICATION_JSON)
			.delete(ClientResponse.class));
	}	
}
