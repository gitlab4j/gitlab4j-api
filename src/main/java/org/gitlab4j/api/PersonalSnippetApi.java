package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Snippet;

public class PersonalSnippetApi extends AbstractApi {

	public PersonalSnippetApi(GitLabApi gitLabApi) {
		super(gitLabApi);
	}

	public Snippet createSnippet(String title, String filename, String content) throws GitLabApiException {
		GitLabApiForm formData = new GitLabApiForm()
				.withParam("title", title)
				.withParam("file_name", filename)
				.withParam("content", content);
		
		Response response = post(Response.Status.CREATED, formData, "snippets");
		return (response.readEntity(Snippet.class));
	}

	public void deleteSnippet(Integer snippetId) throws GitLabApiException {
		if (snippetId == null) {
			throw new RuntimeException("snippetId can't be null");
		}
		delete(Response.Status.NO_CONTENT, null, "snippets", snippetId);
	}

	public List<Snippet> getSnippets() throws GitLabApiException {
        Response response = get(Response.Status.OK, getDefaultPerPageParam(), "snippets");
        List<Snippet> snippets = (response.readEntity(new GenericType<List<Snippet>>() {}));
        
        for (Snippet snippet : snippets) {
			snippet.setContent(getSnippetContent(snippet.getId()));
		}

        return snippets;
	}
	
	public String getSnippetContent(Integer snippetId) throws GitLabApiException {
		Response response = get(Response.Status.OK, null, "snippets", snippetId, "raw");
		return (response.readEntity(String.class));
	}
	
}
