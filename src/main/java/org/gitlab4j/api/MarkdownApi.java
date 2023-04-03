package org.gitlab4j.api;

import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Markdown;
import org.gitlab4j.api.models.MarkdownRequest;

/**
 * This class provides an entry point to all the GitLab API markdown calls.
 */
public class MarkdownApi extends AbstractApi {

    public MarkdownApi(final GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Render an arbitrary Markdown document.
     *
     * <pre><code>GitLab Endpoint: POST /api/v4/markdown</code></pre>
     *
     * @param text text to be transformed
     * @return a Markdown instance with transformed info
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 11.0
     */
    public Markdown getMarkdown(final String text) throws GitLabApiException {

        if (!isApiVersion(ApiVersion.V4)) {
            throw new GitLabApiException("Api version must be v4");
        }

        return getMarkdown(new MarkdownRequest(text, true));
    }

    /**
     * Render an arbitrary Markdown document.
     *
     * <pre><code>GitLab Endpoint: POST /api/v4/markdown</code></pre>
     *
     * @param markdownRequest a request of markdown transformation
     * @return a Markdown instance with transformed info
     * @throws GitLabApiException if any exception occurs
     * @since GitLab 11.0
     */
    public Markdown getMarkdown(final MarkdownRequest markdownRequest) throws GitLabApiException {

        if (!isApiVersion(ApiVersion.V4)) {
            throw new GitLabApiException("Api version must be v4");
        }

        final Response response = post(Response.Status.OK, markdownRequest, "markdown");
        return (response.readEntity(Markdown.class));
    }
}
