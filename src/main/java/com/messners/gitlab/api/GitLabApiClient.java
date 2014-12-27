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
import javax.net.ssl.SSLSocketFactory;
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
 * @author Greg Messner <greg@messners.com>
 */
public class GitLabApiClient {
	
	protected static final String PRIVATE_TOKEN_HEADER = "PRIVATE-TOKEN";
	protected static final String API_NAMESPACE = "/api/v3";
	
	private ClientConfig clientConfig;
	private Client apiClient;
	private String hostUrl;
	private String privateToken;
	private static boolean ignoreCertificateErrors;
	private static SSLSocketFactory defaultSocketFactory;
	private static HTTPSProperties defaultHttpsProperties;


	/**
	 * Construct an instance to communicate with a GitLab API server using the specified
	 * server URL and private token.
	 * 
	 * @param hostUrl the URL to the GitLab API server
	 * @param privateToken the private token to authenticate with
	 */
	public GitLabApiClient (String hostUrl, String privateToken) {	
		
		// Remove the trailing "/" from the hostUrl if present
		this.hostUrl = (hostUrl.endsWith("/") ? hostUrl.replaceAll("/$", "") : hostUrl) + API_NAMESPACE;
		this.privateToken = privateToken;
		
		clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJson.class);
		clientConfig.getFeatures().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, Boolean.TRUE);
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	}
	
	
	/**
	 * Returns true if the API is setup to ignore SSL certificate errors, otherwise returns false.
	 * 
	 * @return true if the API is setup to ignore SSL certificate errors, otherwise returns false
	 */
	public boolean getIgnoreCertificateErrors () {
		return (GitLabApiClient.ignoreCertificateErrors);
	}
	
	
	/**
	 * Sets up the Jersey system ignore SSL certificate errors or not.
	 * 
	 * <p><strong>WARNING: Setting this to true will affect ALL uses of HttpsURLConnection and Jersey.<strong><p>
	 * 
	 * @param ignoreCertificateErrors
	 */
	public void setIgnoreCerificateErrors (boolean ignoreCertificateErrors) {
		
		if (GitLabApiClient.ignoreCertificateErrors == ignoreCertificateErrors) {
			return;
		}
		
		if (ignoreCertificateErrors == false) {	
		
			GitLabApiClient.ignoreCertificateErrors = false;
			HttpsURLConnection.setDefaultSSLSocketFactory(GitLabApiClient.defaultSocketFactory);
			clientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, GitLabApiClient.defaultHttpsProperties);
			return;
		}
		
		SSLSocketFactory defaultSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
		HTTPSProperties defaultHttpsProperties = (HTTPSProperties)clientConfig.getProperties().get(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES);		
		
		if (ignoreCertificateErrors() == true) {
			GitLabApiClient.ignoreCertificateErrors = true;
			GitLabApiClient.defaultSocketFactory = defaultSocketFactory;
			GitLabApiClient.defaultHttpsProperties = defaultHttpsProperties;
		} else {
			throw new RuntimeException("Unable to ignore certificate errors.");
		}
	}
	
	
	/**
	 * Sets up Jersey client to ignore certificate errors.  
	 *
	 * @return true if successful at setting up to ignore certificate errors, otherwise returns false.
	 */
	private boolean ignoreCertificateErrors () {

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
		SSLSocketFactory defaultSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, certs, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			HttpsURLConnection.getDefaultSSLSocketFactory();
		} catch (GeneralSecurityException ex) {
			HttpsURLConnection.setDefaultSSLSocketFactory(defaultSocketFactory);
			return (false);
		}
    
		// Set up a HostnameVerifier for Jersey to verify all hostnames as valid
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
			HttpsURLConnection.setDefaultSSLSocketFactory(defaultSocketFactory);
			return (false);
		}
		
		return (true);
	}
    
	
	/**
	 * Construct a REST URL with the specified path arguments.
	 * 
	 * @param pathArgs
	 * @return a REST URL with the specified path arguments
	 * @throws IOException
	 */
	protected URL getApiUrl (Object ... pathArgs) throws IOException {
		
		StringBuilder url = new StringBuilder();
		url.append(hostUrl);
		for (Object pathArg : pathArgs) {
			url.append("/");
			url.append(pathArg.toString());
		}
		
		return (new URL(url.toString()));
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
		URL url = getApiUrl(pathArgs);
		return (get(queryParams, url));	
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
		URL url = getApiUrl(pathArgs);
		return (post(formData, url));		
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
		
		if (apiClient == null) {
			apiClient = Client.create(clientConfig);
		}
		
		return (apiClient.resource(url.toString())
			.header(PRIVATE_TOKEN_HEADER, privateToken)
			.accept(MediaType.APPLICATION_JSON)
			.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
			.post(ClientResponse.class, formData));		
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
		URL url = getApiUrl(pathArgs);
		return (put(queryParams, url));	
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
		
		if (apiClient == null) {
			apiClient = Client.create(clientConfig);
		}
		
		WebResource resource = apiClient.resource(url.toString());
		if (queryParams != null) {
			resource.queryParams(queryParams);
		}
		
		return (resource.header(PRIVATE_TOKEN_HEADER, privateToken)
			.accept(MediaType.APPLICATION_JSON)
			.put(ClientResponse.class));
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
		URL url = getApiUrl(pathArgs);
		return (delete(queryParams, url));	
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
