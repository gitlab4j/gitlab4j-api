package org.gitlab4j.api;

import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.gitlab4j.api.models.Snippet;

/**
 * This class provides an entry point to all the GitLab Personal Snippet API project calls.
 */
public class PersonalSnippetApi extends AbstractApi {

	public PersonalSnippetApi(GitLabApi gitLabApi) {
		super(gitLabApi);
	}

    /**
     * Create a new Personal Snippet.
     *
     * @param title the title of the snippet
     * @param fileName the file name of the snippet
     * @param content the content of the snippet
     * @return the created Snippet
     * @throws GitLabApiException if any exception occurs
     */
	public Snippet createSnippet(String title, String filename, String content) throws GitLabApiException {
		GitLabApiForm formData = new GitLabApiForm()
				.withParam("title", title)
				.withParam("file_name", filename)
				.withParam("content", content);
		
		Response response = post(Response.Status.CREATED, formData, "snippets");
		return (response.readEntity(Snippet.class));
	}

    /**
     * Removes Personal Snippet
     *
     * DELETE /snippets/:id
     *
     * @param snippetId the snippet ID to remove
     * @throws GitLabApiException if any exception occurs
     */
	public void deleteSnippet(Integer snippetId) throws GitLabApiException {
		if (snippetId == null) {
			throw new RuntimeException("snippetId can't be null");
		}
		delete(Response.Status.NO_CONTENT, null, "snippets", snippetId);
	}

    /**
     * Get a list of Authenticated User's Personal Snippets.
     *
     * GET /snippets
     *
     * @return a list of authenticated user's personal snippets
     * @throws GitLabApiException if any exception occurs
     */
	public List<Snippet> getSnippets() throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "snippets");
        List<Snippet> snippets = (response.readEntity(new GenericType<List<Snippet>>() {}));
        
        for (Snippet snippet : snippets) {
			snippet.setContent(getSnippetContent(snippet.getId()));
		}

        return snippets;
	}
	
    /**
     * Get a the content of a Personal Snippet
     *
     * GET /snippets/id/raw
     *
     * @return the content of personal snippet
     * @throws GitLabApiException if any exception occurs
     */
	public String getSnippetContent(Integer snippetId) throws GitLabApiException {
		Response response = get(Response.Status.OK, null, "snippets", snippetId, "raw");
		return (response.readEntity(String.class));
	}
	
}
