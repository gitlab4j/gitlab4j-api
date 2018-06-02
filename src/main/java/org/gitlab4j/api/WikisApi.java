package org.gitlab4j.api;

import org.gitlab4j.api.models.WikiPage;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * This class provides an entry point to all the GitLab Wikis API project calls.
 *
 * @author shuklaalok7
 * @since v4.8.21 2018-06-02 7:08 AM IST
 */
public class WikisApi extends AbstractApi {

    public WikisApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a Pager of the authenticated user's wiki pages.
     *
     * GET /wikis
     *
     * @param itemsPerPage the number of wiki-pages per page
     * @return the Pager of wiki-pages
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<WikiPage> getPages(int itemsPerPage) throws GitLabApiException {
        return (new Pager<>(this, WikiPage.class, itemsPerPage, null, "wikis"));
    }

    /**
     * Get a list of the authenticated user's wikis.
     *
     * GET /wikis
     *
     * @param downloadContent indicating whether to download the page content
     * @return a list of authenticated user's wiki pages
     * @throws GitLabApiException if any exception occurs
     */
    public List<WikiPage> getPages(boolean downloadContent) throws GitLabApiException {

        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "wikis");
        List<WikiPage> pages = (response.readEntity(new GenericType<List<WikiPage>>(){}));

        if (downloadContent) {
            for (WikiPage page : pages) {
                page.setContent(getPageContent(page.getSlug()));
            }
        }

        return pages;
    }

    /**
     * Get a list of the authenticated user's wikis.
     *
     * GET /wikis
     *
     * @return a list of authenticated user's wikis
     * @throws GitLabApiException if any exception occurs
     */
    public List<WikiPage> getPages() throws GitLabApiException {
        return getPages(false);
    }

    /**
     * Get the content of a Wiki page.
     *
     * GET /wikis/:slug
     *
     * @param slug the slug to fetch
     * @return the content of wiki page
     * @throws GitLabApiException if any exception occurs
     */
    public String getPageContent(String slug) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "wikis", slug);
        return (response.readEntity(String.class));
    }

    /**
     * Get a specific Wiki page.
     *
     * @param slug the slug to get
     * @param downloadContent indicating whether to download the page content
     * @return the Wiki page with the given slug
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage getPage(String slug, boolean downloadContent) throws GitLabApiException {
        if (slug == null) {
            throw new RuntimeException("slug can't be null");
        }

        Response response = get(Response.Status.OK, null, "wikis", slug);
        WikiPage page = response.readEntity(WikiPage.class);

        if (downloadContent) {
            page.setContent(getPageContent(slug));
        }

        return page;
    }

    /**
     * Get a specific Page.
     *
     * @param slug the slug to get
     * @return the Wiki page with the given slug
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage getPage(String slug) throws GitLabApiException {
        return getPage(slug, false);
    }

    /**
     * Get a specific wiki page as an Optional instance.
     *
     * GET /wikis/:slug
     *
     * @param slug the slug of the wiki page to get the Optional instance for
     * @return the specified WikiPage as an Optional instance
     */
    public Optional<WikiPage> getOptionalPage( String slug) {
        return (getOptionalPage(slug, false));
    }

    /**
     * Get a specific wiki page as an Optional instance.
     *
     * GET /wikis/:slug
     *
     * @param slug the slug of the wiki page to get the Optional instance for
     * @param downloadContent indicating whether to download the page content
     * @return the specified WikiPage as an Optional instance
     */
    public Optional<WikiPage> getOptionalPage(String slug, boolean downloadContent) {
        try {
            return (Optional.ofNullable(getPage(slug, downloadContent)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Create a new WikiPage.
     *
     * @param title the title of the page
     * @param slug the slug of the page
     * @param content the content of the page
     * @return the created Wiki page
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage createPage(String title, String slug, String content) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title)
                .withParam("slug", slug, true)
                .withParam("content", content);
        Response response = post(Response.Status.CREATED, formData, "wikis");
        return (response.readEntity(WikiPage.class));
    }

    /**
     * Create a new Wiki page.
     *
     * @param title the title of the wiki page
     * @param slug slug of the page
     * @param content the content of the page
     * @param format {@code markdown}, {@code rdoc}, or {@code ansidoc}
     * @return the created WikiPage
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage createPage(String title, String slug, String content, String format) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title)
                .withParam("slug", slug, true)
                .withParam("content", content)
                .withParam("format", format);
        Response response = post(Response.Status.CREATED, formData, "wikis");
        return (response.readEntity(WikiPage.class));
    }

    /**
     * Removes Wiki page.
     *
     * DELETE /wikis/:slug
     *
     * @param slug the slug of the page to remove
     * @throws GitLabApiException if any exception occurs
     */
    public void deletePage(String slug) throws GitLabApiException {
        if (slug == null) {
            throw new RuntimeException("slug can't be null");
        }

        delete(Response.Status.NO_CONTENT, null, "wikis", slug);
    }
}
