package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.Visibility;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class TestSnippetsApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static final String TEST_SNIPPET_TITLE_1 = "test-snippet-title-1";
    private static final String TEST_SNIPPET_FILE_NAME_1 = "test-snippet-file-name-1";
    private static final String TEST_SNIPPET_CONTENT_1 = "test-snippet-content-1";
    private static final String TEST_SNIPPET_CONTENT_2 = "test-snippet-content-2";
    private static final String TEST_SNIPPET_DESCRIPTION_1 = "test-snippet-description-1";

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testCreate() throws GitLabApiException {
        Snippet snippet = createSnippet(
                new Snippet(TEST_SNIPPET_TITLE_1, TEST_SNIPPET_FILE_NAME_1, TEST_SNIPPET_CONTENT_1));
        assertEquals(TEST_SNIPPET_TITLE_1, snippet.getTitle());
        assertEquals(TEST_SNIPPET_FILE_NAME_1, snippet.getFileName());
        assertNull(snippet.getContent());

        deleteSnippet(snippet);
    }

    @Test
    public void testDelete() throws GitLabApiException {
        Snippet snippet = createSnippet(
                new Snippet(TEST_SNIPPET_TITLE_1, TEST_SNIPPET_FILE_NAME_1, TEST_SNIPPET_CONTENT_1));
        deleteSnippet(snippet);

        SnippetsApi api = gitLabApi.getSnippetApi();
        List<Snippet> snippets = api.getSnippets();
        boolean found = snippets.stream().anyMatch(s -> s.getId().equals(snippet.getId()));
        assertFalse(found);
    }

    @Test
    public void testList() throws GitLabApiException {
        Snippet snippet1 = createSnippet(new Snippet(TEST_SNIPPET_TITLE_1, TEST_SNIPPET_FILE_NAME_1, TEST_SNIPPET_CONTENT_1));
        Snippet snippet2 = createSnippet(new Snippet(TEST_SNIPPET_TITLE_1, TEST_SNIPPET_FILE_NAME_1, TEST_SNIPPET_CONTENT_2));

        SnippetsApi api = gitLabApi.getSnippetApi();
        List<Snippet> snippets = api.getSnippets(true);

        assertTrue(snippets.size() >= 2);
        assertTrue(snippets.stream().anyMatch(s -> s.getContent().equals(TEST_SNIPPET_CONTENT_1)));
        assertTrue(snippets.stream().anyMatch(s -> s.getContent().equals(TEST_SNIPPET_CONTENT_2)));

        deleteSnippet(snippet1);
        deleteSnippet(snippet2);
    }

    @Test
    public void testSnippetContent() throws GitLabApiException {
        Snippet snippet = createSnippet(
                new Snippet(TEST_SNIPPET_TITLE_1, TEST_SNIPPET_FILE_NAME_1, TEST_SNIPPET_CONTENT_1));
        SnippetsApi api = gitLabApi.getSnippetApi();
        String snippetContent = api.getSnippetContent(snippet.getId());
        assertEquals(TEST_SNIPPET_CONTENT_1, snippetContent);
        deleteSnippet(snippet);
    }

    @Test
    public void testRetrieveSnippet() throws GitLabApiException {
        Snippet snippet = createSnippet(new Snippet(TEST_SNIPPET_TITLE_1, TEST_SNIPPET_FILE_NAME_1,
                TEST_SNIPPET_CONTENT_1, Visibility.INTERNAL, TEST_SNIPPET_DESCRIPTION_1));

        SnippetsApi api = gitLabApi.getSnippetApi();
        Snippet savedSnippet = api.getSnippet(snippet.getId(), true);

        assertEquals(TEST_SNIPPET_TITLE_1, savedSnippet.getTitle());
        assertEquals(TEST_SNIPPET_FILE_NAME_1, savedSnippet.getFileName());
        assertEquals(TEST_SNIPPET_CONTENT_1, savedSnippet.getContent());
        assertEquals(TEST_SNIPPET_DESCRIPTION_1, savedSnippet.getDescription());

        deleteSnippet(savedSnippet);
    }

    public void deleteSnippet(Snippet snippet) throws GitLabApiException {
        SnippetsApi api = gitLabApi.getSnippetApi();
        api.deleteSnippet(snippet.getId());
    }

    public Snippet createSnippet(Snippet snippet) throws GitLabApiException {
        SnippetsApi api = gitLabApi.getSnippetApi();
        return api.createSnippet(snippet.getTitle(), snippet.getFileName(), snippet.getContent(),
                snippet.getVisibility(), snippet.getDescription());
    }

}
