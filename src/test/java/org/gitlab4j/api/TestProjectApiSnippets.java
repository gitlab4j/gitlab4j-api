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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.Visibility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 *
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestProjectApiSnippets extends AbstractIntegrationTest {

    private static final String TEST_SNIPPET_TITLE_PREFIX = "Test Snippet: ";
    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestProjectApiSnippets() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        deleteAllTestSnippets();
    }

    @AfterAll
    public static void teardown() throws GitLabApiException {
        deleteAllTestSnippets();
    }

    private static void deleteAllTestSnippets() {
        if (gitLabApi != null) {
            try {
                List<Snippet> snippets = gitLabApi.getProjectApi().getSnippets(testProject);
                if (snippets != null) {
                    for (Snippet snippet : snippets) {
                        if (snippet.getTitle().startsWith(TEST_SNIPPET_TITLE_PREFIX)) {
                            gitLabApi.getProjectApi().deleteSnippet(testProject, snippet.getId());
                        }
                    }
                }
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    private Snippet createSnippet(String title, String filename, String description,
            String code, Visibility visibility) throws GitLabApiException {
        return (gitLabApi.getProjectApi().createSnippet(testProject, title, filename, description, code, visibility));
    }

    @Test
    public void testCreate() throws GitLabApiException {

        String title = TEST_SNIPPET_TITLE_PREFIX + "Test createSnippet()";
        String filename = "test-create-snippet.js";
        String description = null;
        String code = "window.open();";
        Visibility visibility = Visibility.PRIVATE;
        Snippet snippet = createSnippet(title, filename, description, code, visibility);
        assertNotNull(snippet);
        assertEquals(title, snippet.getTitle());
        assertEquals(filename, snippet.getFileName());
    }

    @Test
    public void testUpdate() throws GitLabApiException {

        assumeTrue(testProject != null);

        String title = TEST_SNIPPET_TITLE_PREFIX + "Test createSnippet()";
        String filename = "test-update-snippet.js";
        String description = null;
        String code = "window.open();";
        Visibility visibility = Visibility.INTERNAL;
        Snippet snippet = createSnippet(title, filename, description, code, visibility);
        assertNotNull(snippet);

        title = TEST_SNIPPET_TITLE_PREFIX + "Test updateSnippet()";
        snippet = gitLabApi.getProjectApi().updateSnippet(testProject, snippet.getId(), title, null, null, null, null);
        assertEquals(title, snippet.getTitle());
        assertEquals(filename, snippet.getFileName());
    }

    @Test
    public void testListSnippets() throws GitLabApiException {

        assumeTrue(testProject != null);

        String title = TEST_SNIPPET_TITLE_PREFIX + "Test listSnippets()";
        String filename = "test-list-snippets.js";
        String description = null;
        String code = "window.open();";
        Visibility visibility = Visibility.INTERNAL;
        Snippet newSnippet = createSnippet(title, filename, description, code, visibility);
        assertNotNull(newSnippet);

        long snippetId = newSnippet.getId();
        List<Snippet> snippets = gitLabApi.getProjectApi().getSnippets(testProject);
        assertNotNull(snippets);
        for (Snippet snippet : snippets) {
            if (snippet.getId() == snippetId) {
                assertEquals(title, snippet.getTitle());
                assertEquals(filename, snippet.getFileName());
                break;
            }
        }
    }

    @Test
    public void testDeleteSnippet() throws GitLabApiException {

        assumeTrue(testProject != null);

        String title = TEST_SNIPPET_TITLE_PREFIX + "Test listSnippets()";
        String filename = "test-delete-snippet.js";
        String description = null;
        String code = "window.open();";
        Visibility visibility = Visibility.INTERNAL;
        Snippet createdSnippet = createSnippet(title, filename, description, code, visibility);
        assertNotNull(createdSnippet);

        long snippetId = createdSnippet.getId();
        gitLabApi.getProjectApi().deleteSnippet(testProject, snippetId);
        List<Snippet> snippets = gitLabApi.getProjectApi().getSnippets(testProject);
        if (snippets != null) {
            for (Snippet snippet : snippets) {
                if (snippet.getId() == snippetId) {
                    fail("Snippet was not deleted.");
                }
            }
        }
    }

    @Test
    public void testSnippetContent() throws GitLabApiException {

        assumeTrue(testProject != null);

        String title = TEST_SNIPPET_TITLE_PREFIX + "Test getRawSnippetContent()";
        String filename = "test-raw-snippet.js";
        String description = null;
        String code = "window.open();";
        Visibility visibility = Visibility.PUBLIC;
        Snippet createdSnippet = createSnippet(title, filename, description, code, visibility);
        assertNotNull(createdSnippet);

        String rawContent = gitLabApi.getProjectApi().getRawSnippetContent(testProject.getId(), createdSnippet.getId());
        assertEquals(code, rawContent);
    }
}
