package org.gitlab4j.api;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.gitlab4j.api.models.Application;

/**
 * This class implements the client side API for the GitLab Applications API. See <a
 * href="https://docs.gitlab.com/ce/api/applications.html">Applications API at GitLab</a> for more
 * information.
 */
public class ApplicationsApi extends AbstractApi {

  public ApplicationsApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Get all OATH applications.
   *
   * <pre><code>GitLab Endpoint: GET /api/v4/applications</code></pre>
   *
   * @return a List of OAUTH Application instances
   * @throws GitLabApiException if any exception occurs
   */
  public List<Application> getApplications() throws GitLabApiException {
    return (getApplications(getDefaultPerPage()).all());
  }

  /**
   * Get all OAUTH applications using the specified page and per page setting
   *
   * <pre><code>GitLab Endpoint: GET /api/v4/applications</code></pre>
   *
   * @param page the page to get
   * @param perPage the number of items per page
   * @return a list of OAUTH Applications in the specified range
   * @throws GitLabApiException if any exception occurs
   */
  public List<Application> getApplications(final int page, final int perPage)
      throws GitLabApiException {
    final Response response =
        get(
            jakarta.ws.rs.core.Response.Status.OK,
            getPageQueryParams(page, perPage),
            "applications");
    return (response.readEntity(new GenericType<List<Application>>() {}));
  }

  /**
   * Get a Pager of all OAUTH applications.
   *
   * <pre><code>GitLab Endpoint: GET /api/v4/applications</code></pre>
   *
   * @param itemsPerPage the number of items per page
   * @return a Pager of Application instances in the specified range
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Application> getApplications(final int itemsPerPage) throws GitLabApiException {
    return (new Pager<Application>(this, Application.class, itemsPerPage, null, "applications"));
  }

  /**
   * Get a Stream of all OAUTH Application instances.
   *
   * <pre><code>GitLab Endpoint: GET /api/v4/applications</code></pre>
   *
   * @return a Stream of OAUTH Application instances
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Application> getApplicationsStream() throws GitLabApiException {
    return (getApplications(getDefaultPerPage()).stream());
  }

  /**
   * Create an OAUTH Application.
   *
   * <pre><code>GitLab Endpoint: POST /api/v4/applications</code></pre>
   *
   * @param name the name for the OAUTH Application
   * @param redirectUri the redirect URI for the OAUTH Application
   * @param scopes the scopes of the application (api, read_user, sudo, read_repository, openid,
   *     profile, email)
   * @return the created Application instance
   * @throws GitLabApiException if any exception occurs
   */
  public Application createApplication(
      final String name, final String redirectUri, final ApplicationScope[] scopes)
      throws GitLabApiException {

    if (scopes == null || scopes.length == 0) {
      throw new GitLabApiException("scopes cannot be null or empty");
    }

    return (createApplication(name, redirectUri, Arrays.asList(scopes)));
  }

  /**
   * Create an OAUTH Application.
   *
   * <pre><code>GitLab Endpoint: POST /api/v4/applications</code></pre>
   *
   * @param name the name for the OAUTH Application
   * @param redirectUri the redirect URI for the OAUTH Application
   * @param scopes the scopes of the application (api, read_user, sudo, read_repository, openid,
   *     profile, email)
   * @return the created Application instance
   * @throws GitLabApiException if any exception occurs
   */
  public Application createApplication(
      final String name, final String redirectUri, final List<ApplicationScope> scopes)
      throws GitLabApiException {

    if (scopes == null || scopes.isEmpty()) {
      throw new GitLabApiException("scopes cannot be null or empty");
    }

    final String scopesString =
        scopes.stream().map(ApplicationScope::toString).collect(Collectors.joining(","));
    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("name", name, true)
            .withParam("redirect_uri", redirectUri, true)
            .withParam("scopes", scopesString, true);
    final Response response = post(Response.Status.CREATED, formData, "applications");
    return (response.readEntity(Application.class));
  }

  /**
   * Delete the specified OAUTH Application.
   *
   * <pre><code>GitLab Endpoint: DELETE /api/v4/applications/:applicationId</code></pre>
   *
   * @param applicationId the ID of the OUAUTH Application to delete
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteApplication(final Long applicationId) throws GitLabApiException {
    delete(Response.Status.NO_CONTENT, null, "applications", applicationId);
  }
}
