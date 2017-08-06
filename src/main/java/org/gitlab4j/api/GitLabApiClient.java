package org.gitlab4j.api;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.utils.JacksonJson;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

/**
 * This class utilizes the Jersey client package to communicate with a GitLab API endpoint.
 */
public class GitLabApiClient {

    protected static final String PRIVATE_TOKEN_HEADER = "PRIVATE-TOKEN";
    protected static final String X_GITLAB_TOKEN_HEADER = "X-Gitlab-Token";

    private ClientConfig clientConfig;
    private Client apiClient;
    private String hostUrl;
    private String privateToken;
    private String secretToken;
    private static boolean ignoreCertificateErrors;
    private static SSLSocketFactory defaultSocketFactory;

    /**
     * Construct an instance to communicate with a GitLab API server using the specified GitLab API version,
     * server URL, private token, and secret token.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL to the GitLab API server
     * @param privateToken the private token to authenticate with
     */
    public GitLabApiClient(ApiVersion apiVersion, String hostUrl, String privateToken) {
        this(apiVersion, hostUrl, privateToken, null);
    }

    /**
     * Construct an instance to communicate with a GitLab API server using GitLab API version 4, and the specified
     * server URL, private token, and secret token.
     * 
     * @param hostUrl the URL to the GitLab API server
     * @param privateToken the private token to authenticate with
     */
    public GitLabApiClient(String hostUrl, String privateToken) {
        this(ApiVersion.V4, hostUrl, privateToken, null);
    }

    /**
     * Construct an instance to communicate with a GitLab API server using the specified GitLab API version,
     * server URL, private token, and secret token.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL to the GitLab API server
     * @param privateToken the private token to authenticate with
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApiClient(ApiVersion apiVersion, String hostUrl, String privateToken, String secretToken) {
        this(apiVersion, hostUrl, privateToken, secretToken, null);
    }

    /**
     * Construct an instance to communicate with a GitLab API server using GitLab API version 4, and the specified
     * server URL, private token, and secret token.
     * 
     * @param hostUrl the URL to the GitLab API server
     * @param privateToken the private token to authenticate with
     * @param secretToken use this token to validate received payloads
     */
    public GitLabApiClient(String hostUrl, String privateToken, String secretToken) {
        this(ApiVersion.V4, hostUrl, privateToken, secretToken, null);
    }

    /**
     * Construct an instance to communicate with a GitLab API server using the specified GitLab API version, 
     * server URL and private token.
     *
     * @param apiVersion the ApiVersion specifying which version of the API to use
     * @param hostUrl the URL to the GitLab API server
     * @param privateToken the private token to authenticate with
     * @param secretToken use this token to validate received payloads
     * @param clientConfigProperties the properties given to Jersey's clientconfig
     */
    public GitLabApiClient(ApiVersion apiVersion, String hostUrl, String privateToken, String secretToken, Map<String, Object> clientConfigProperties) {

        // Remove the trailing "/" from the hostUrl if present
        this.hostUrl = (hostUrl.endsWith("/") ? hostUrl.replaceAll("/$", "") : hostUrl) + apiVersion.getApiNamespace();
        this.privateToken = privateToken;

        if (secretToken != null) {
            secretToken = secretToken.trim();
            secretToken = (secretToken.length() > 0 ? secretToken : null);
        }

        this.secretToken = secretToken;

        clientConfig = new ClientConfig();
        if (clientConfigProperties != null) {
            clientConfig.getProperties().putAll(clientConfigProperties);
        }

        clientConfig.register(JacksonJson.class);
    }

    /**
     * Construct an instance to communicate with a GitLab API server using GitLab API version 4, and the specified
     * server URL and private token.
     * 
     * @param hostUrl the URL to the GitLab API server
     * @param privateToken the private token to authenticate with
     * @param secretToken use this token to validate received payloads
     * @param clientConfigProperties the properties given to Jersey's clientconfig
     */
    public GitLabApiClient(String hostUrl, String privateToken, String secretToken, Map<String, Object> clientConfigProperties) {
        this(ApiVersion.V4, hostUrl, privateToken, secretToken, clientConfigProperties);
    }

    /**
     * Returns true if the API is setup to ignore SSL certificate errors, otherwise returns false.
     * 
     * @return true if the API is setup to ignore SSL certificate errors, otherwise returns false
     */
    public boolean getIgnoreCertificateErrors() {
        return (GitLabApiClient.ignoreCertificateErrors);
    }

    /**
     * Sets up the Jersey system ignore SSL certificate errors or not.
     * 
     * WARNING: Setting this to true will affect ALL uses of HttpsURLConnection and Jersey.
     * 
     * @param ignoreCertificateErrors if true will set up the Jersey system ignore SSL certificate errors
     */
    public void setIgnoreCerificateErrors(boolean ignoreCertificateErrors) {

        if (GitLabApiClient.ignoreCertificateErrors == ignoreCertificateErrors) {
            return;
        }

        if (!ignoreCertificateErrors) {

            GitLabApiClient.ignoreCertificateErrors = false;
            HttpsURLConnection.setDefaultSSLSocketFactory(GitLabApiClient.defaultSocketFactory);
 
        } else {

            SSLSocketFactory defaultSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();

            if (ignoreCertificateErrors()) {
                GitLabApiClient.ignoreCertificateErrors = true;
                GitLabApiClient.defaultSocketFactory = defaultSocketFactory;
            } else {
                throw new RuntimeException("Unable to ignore certificate errors.");
            }
        }
    }

    /**
     * Sets up Jersey client to ignore certificate errors.
     *
     * @return true if successful at setting up to ignore certificate errors, otherwise returns false.
     */
    private boolean ignoreCertificateErrors() {

        // Create a TrustManager that trusts all certificates
        TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        } };

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

        return (true);
    }

    /**
     * Construct a REST URL with the specified path arguments.
     * 
     * @param pathArgs ariable list of arguments used to build the URI
     * @return a REST URL with the specified path arguments
     * @throws IOException if an error occurs while constructing the URL
     */
    protected URL getApiUrl(Object... pathArgs) throws IOException {

        StringBuilder url = new StringBuilder();
        url.append(hostUrl);
        for (Object pathArg : pathArgs) {
            if (pathArg != null) {
                url.append("/");
                url.append(pathArg.toString());
            }
        }

        return (new URL(url.toString()));
    }

    /**
     * Validates the secret token (X-GitLab-Token) header against the expected secret token, returns true if valid,
     * otherwise returns false.
     * 
     * @param response the Response instance sent from the GitLab server
     * @return true if the response's secret token is valid, otherwise returns false
     */
    protected boolean validateSecretToken(Response response) {

        if (this.secretToken == null)
            return (true);

        String secretToken = response.getHeaderString(X_GITLAB_TOKEN_HEADER);
        if (secretToken == null)
            return (false);

        return (this.secretToken.equals(secretToken));
    }

    /**
     * Perform an HTTP GET call with the specified query parameters and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response get(MultivaluedMap<String, String> queryParams, Object... pathArgs) throws IOException {
        URL url = getApiUrl(pathArgs);
        return (get(queryParams, url));
    }

    /**
     * Perform an HTTP GET call with the specified query parameters and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     */
    protected Response get(MultivaluedMap<String, String> queryParams, URL url) {
        return (invocation(url, queryParams).get());
    }

    /**
     * Perform an HTTP GET call with the specified query parameters and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param accepts if non-empty will set the Accepts header to this value
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response getWithAccepts(MultivaluedMap<String, String> queryParams, String accepts, Object... pathArgs) throws IOException {
        URL url = getApiUrl(pathArgs);
        return (getWithAccepts(queryParams, url, accepts));
    }

    /**
     * Perform an HTTP GET call with the specified query parameters and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @param accepts if non-empty will set the Accepts header to this value
     * @return a ClientResponse instance with the data returned from the endpoint
     */
    protected Response getWithAccepts(MultivaluedMap<String, String> queryParams, URL url, String accepts) {
        return (invocation(url, queryParams, accepts).get());
    }

    /**
     * Perform an HTTP POST call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param formData the Form containing the name/value pairs
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response post(Form formData, Object... pathArgs) throws IOException {
        URL url = getApiUrl(pathArgs);
        return post(formData, url);
    }

    /**
     * Perform an HTTP POST call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a Response instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response post(MultivaluedMap<String, String> queryParams, Object... pathArgs) throws IOException {
        URL url = getApiUrl(pathArgs);
        return post(queryParams, url);
    }

    /**
     * Perform an HTTP POST call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param formData the Form containing the name/value pairs
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     */
    protected Response post(Form formData, URL url) {
        if (formData instanceof GitLabApiForm)
            return (invocation(url, null).post(Entity.entity(formData.asMap(), MediaType.APPLICATION_FORM_URLENCODED_TYPE)));
        else
            return (invocation(url, null).post(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED_TYPE)));
    }

    /**
     * Perform an HTTP POST call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     *
     * @param queryParams multivalue map of request parametersformData the Form containing the name/value pairs
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     */
    protected Response post(MultivaluedMap<String, String> queryParams, URL url) {
        return (invocation(url, queryParams).post(null));
    }

    /**
     * Perform an HTTP PUT call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response put(MultivaluedMap<String, String> queryParams, Object... pathArgs) throws IOException {
        URL url = getApiUrl(pathArgs);
        return (put(queryParams, url));
    }

    /**
     * Perform an HTTP PUT call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     *
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     */
    protected Response put(MultivaluedMap<String, String> queryParams, URL url) {
        return (invocation(url, null).put(Entity.entity(queryParams, MediaType.APPLICATION_FORM_URLENCODED_TYPE)));
    }

    /**
     * Perform an HTTP PUT call with the specified form data and path objects, returning
     * a ClientResponse instance with the data returned from the endpoint.
     *
     * @param formData the Form containing the name/value pairs
     * @param pathArgs variable list of arguments used to build the URI
     * @return a ClientResponse instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response put(Form formData, Object... pathArgs) throws IOException {
        URL url = getApiUrl(pathArgs);
        return put(formData, url);
    }

    /**
     * Perform an HTTP PUT call with the specified form data and URL, returning
     * a ClientResponse instance with the data returned from the endpoint.
     *
     * @param formData the Form containing the name/value pairs
     * @param url the fully formed path to the GitLab API endpoint
     * @return a ClientResponse instance with the data returned from the endpoint
     */
    protected Response put(Form formData, URL url) {
        if (formData instanceof GitLabApiForm)
            return (invocation(url, null).put(Entity.entity(formData.asMap(), MediaType.APPLICATION_FORM_URLENCODED_TYPE)));
        else
            return (invocation(url, null).put(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED_TYPE)));
    }

    /**
     * Perform an HTTP DELETE call with the specified form data and path objects, returning
     * a Response instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param pathArgs variable list of arguments used to build the URI
     * @return a Response instance with the data returned from the endpoint
     * @throws IOException if an error occurs while constructing the URL
     */
    protected Response delete(MultivaluedMap<String, String> queryParams, Object... pathArgs) throws IOException {
        return (delete(queryParams, getApiUrl(pathArgs)));
    }

    /**
     * Perform an HTTP DELETE call with the specified form data and URL, returning
     * a Response instance with the data returned from the endpoint.
     * 
     * @param queryParams multivalue map of request parameters
     * @param url the fully formed path to the GitLab API endpoint
     * @return a Response instance with the data returned from the endpoint
     */
    protected Response delete(MultivaluedMap<String, String> queryParams, URL url) {
        return (invocation(url, queryParams).delete());
    }

    protected Invocation.Builder invocation(URL url, MultivaluedMap<String, String> queryParams) {
        return (invocation(url, queryParams, MediaType.APPLICATION_JSON));
    }

    protected Invocation.Builder invocation(URL url, MultivaluedMap<String, String> queryParams, String accept) {

        if (apiClient == null) {
            apiClient = ClientBuilder.newBuilder().withConfig(clientConfig).sslContext(getSslContext()).hostnameVerifier(new AcceptAllHostnameVerifier()).build();
        }

        WebTarget target = apiClient.target(url.toExternalForm()).property(ClientProperties.FOLLOW_REDIRECTS, true);
        if (queryParams != null) {
            for (Map.Entry<String, List<String>> param : queryParams.entrySet()) {
                target = target.queryParam(param.getKey(), param.getValue().toArray());
            }
        }

        if (accept == null || accept.trim().length() == 0)
            return (target.request().header(PRIVATE_TOKEN_HEADER, privateToken));
        else
            return (target.request().header(PRIVATE_TOKEN_HEADER, privateToken).accept(accept));
    }

    protected class AcceptAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return (true);
        }
    }

    private SSLContext getSslContext() {
        try {
            return SSLContext.getDefault();
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
