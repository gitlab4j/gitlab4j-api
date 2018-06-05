/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import org.gitlab4j.api.models.WikiPage;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * @author shuklaalok7 (alok@clay.fish)
 * @since v4.8.21 2018-06-5 1:26 AM IST
 */
public class WikisApi extends AbstractApi {


    public WikisApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of pages in project wiki.  This only returns the first page of wiki-pages.
     *
     * GET /projects/:id/wikis
     *
     * @param projectId the project ID to get the wiki-pages for
     * @return a list of pages in the project's wiki
     * @throws GitLabApiException if any exception occurs
     */
    public List<WikiPage> getPages(Integer projectId) throws GitLabApiException {
        return getPages(projectId, 1, this.getDefaultPerPage());
    }

    /**
     * Get a list of project snippets.  This only returns the first page of snippets.
     *
     * GET /projects/:id/wikis
     *
     * @param projectId the project ID to get the wiki pages for
     * @param page the page to get
     * @param perPage the number of wiki-pages per page
     * @return a list of pages in project's wiki for the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<WikiPage> getPages(Integer projectId, int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "wikis");
        return response.readEntity(new GenericType<List<WikiPage>>() {});
    }

    /**
     * Get a Pager of project's wiki pages.
     *
     * GET /projects/:id/wikis
     *
     * @param projectId the project ID to get the wiki-pages for
     * @param itemsPerPage the number of wiki-pages per page
     * @return the Pager of wiki-pages
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<WikiPage> getPages(Integer projectId, int itemsPerPage) throws GitLabApiException {
        return (new Pager<>(this, WikiPage.class, itemsPerPage, null, "projects", projectId, "wikis"));
    }

    /**
     * Get a single page of project wiki.
     *
     * GET /projects/:id/wikis/:slug
     *
     * @param projectId the project ID to get the wiki page for
     * @param slug the slug of the project's wiki page
     * @return the specified project Snippet
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage getPage(Integer projectId, String slug) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "projects", projectId, "wikis", slug);
        return (response.readEntity(WikiPage.class));
    }

    /**
     * Get a single page of project wiki as an Optional instance.
     *
     * GET /projects/:id/wikis/:slug
     *
     * @param projectId the project ID to get the snippet for
     * @param slug the slug of the project's wiki page
     * @return the specified project Snippet as an Optional instance
     */
    public Optional<WikiPage> getOptionalPage(Integer projectId, String slug) {
        try {
            return (Optional.ofNullable(getPage(projectId, slug)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a new project wiki page. The user must have permission to create new wiki page.
     *
     * POST /projects/:id/wikis
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param title the title of a snippet, required
     * @param content the content of a wiki page, required
     * @return a WikiPage instance with info on the created page
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage createPage(Integer projectId, String title, String content) throws GitLabApiException {
        // one of title or content is required
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title)
                .withParam("content", content);

        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "wikis");
        return (response.readEntity(WikiPage.class));
    }

    /**
     * Updates an existing project wiki page. The user must have permission to change an existing wiki page.
     *
     * PUT /projects/:id/wikis/:slug
     *
     * @param projectId the ID of the project owned by the authenticated user, required
     * @param slug the slug of the project's wiki page, required
     * @param title the title of a snippet, optional
     * @param content the content of a page, optional. Either title or content must be supplied.
     * @return a WikiPage instance with info on the updated page
     * @throws GitLabApiException if any exception occurs
     */
    public WikiPage updatePage(Integer projectId, String slug, String title, String content) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("title", title)
                .withParam("slug", slug, true)
                .withParam("content", content);

        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "wikis", slug);
        return (response.readEntity(WikiPage.class));
    }

    /**
     * Deletes an existing project wiki page. This is an idempotent function and deleting a non-existent page does
     * not cause an error.
     *
     * DELETE /projects/:id/wikis/:slug
     *
     * @param projectId the project ID
     * @param slug the slug of the project's wiki page
     * @throws GitLabApiException if any exception occurs
     */
    public void deletePage(Integer projectId, String slug) throws GitLabApiException {
        delete(Response.Status.NO_CONTENT, null, "projects", projectId, "wikis", slug);
    }

}
