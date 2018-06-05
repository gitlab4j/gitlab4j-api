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

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.WikiPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
public class TestWikisApi {

    // The following needs to be set to your test repository
    private static final String TEST_NAMESPACE;
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    private static final String TEST_WIKI_TITLE_PREFIX = "Test Wiki: ";
    private static GitLabApi gitLabApi;
    private static Integer testProjectId;
    private static String testContent;

    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    public TestWikisApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }

        if (gitLabApi != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
                testProjectId = project.getId();
            } catch (Exception e) {
                System.err.print(e.getMessage());
                gitLabApi = null;
            }
        }

        testContent = "This is a test content and must be deleted after testing.";

        deleteAllTestWikiPages();
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        deleteAllTestWikiPages();
    }

    private static void deleteAllTestWikiPages() {
        if (gitLabApi != null) {
            try {
                List<WikiPage> wikiPages = gitLabApi.getWikisApi().getPages(testProjectId);
                wikiPages.stream().filter(wp -> wp.getTitle().startsWith(TEST_WIKI_TITLE_PREFIX)).map(WikiPage::getSlug).forEach(slug -> {
                    try {
                        gitLabApi.getWikisApi().deletePage(testProjectId, slug);
                    } catch (GitLabApiException ignored) {
                    }
                });
            } catch (GitLabApiException ignore) {

            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    private WikiPage createWikiPage(String title, String content) throws GitLabApiException {
        return (gitLabApi.getWikisApi().createPage(testProjectId, title, content));
    }

    @Test
    public void testCreate() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test createWikiPage()";
        WikiPage wikiPage = createWikiPage(title, testContent);
        assertNotNull(wikiPage);
        assertEquals(title, wikiPage.getTitle());
        assertEquals(testContent, wikiPage.getContent());
    }

    @Test
    public void testUpdate() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test createWikiPage()";
        WikiPage wikiPage = createWikiPage(title, testContent);
        assertNotNull(wikiPage);

        title = TEST_WIKI_TITLE_PREFIX + "Test updateWikiPage()";
        wikiPage = gitLabApi.getWikisApi().updatePage(testProjectId, wikiPage.getSlug(), title, "some content");
        assertEquals(title, wikiPage.getTitle());
        assertEquals("some content", wikiPage.getContent());
    }

    @Test
    public void testListWikiPages() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test listWikiPages()";
        WikiPage newWikiPage = createWikiPage(title, testContent);
        assertNotNull(newWikiPage);

        String wikiPageSlug = newWikiPage.getSlug();
        List<WikiPage> wikiPages = gitLabApi.getWikisApi().getPages(testProjectId);
        assertNotNull(wikiPages);

        wikiPages.stream().filter(wp -> wp.getSlug().equals(wikiPageSlug)).forEach(wp -> {
            assertEquals(title, wp.getTitle());
            assertEquals(wikiPageSlug, wp.getSlug());
        });
    }

    @Test
    public void testDeleteWikiPage() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test listWikiPages()";
        WikiPage createdWikiPage = createWikiPage(title, testContent);
        assertNotNull(createdWikiPage);

        String wikiPageSlug = createdWikiPage.getSlug();
        gitLabApi.getWikisApi().deletePage(testProjectId, wikiPageSlug);
        List<WikiPage> wikiPages = gitLabApi.getWikisApi().getPages(testProjectId);
        if (wikiPages.stream().anyMatch(wp -> wp.getSlug().equals(wikiPageSlug))) {
            fail("WikiPage was not deleted.");
        }
    }

}
