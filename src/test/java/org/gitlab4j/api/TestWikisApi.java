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

import java.io.File;
import java.util.List;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.WikiAttachment;
import org.gitlab4j.api.models.WikiPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestWikisApi extends AbstractIntegrationTest {

    private static final String TEST_WIKI_TITLE_PREFIX = "Test Wiki: ";
    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static String testContent;

    public TestWikisApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        testContent = "This is a test content and must be deleted after testing.";
        deleteAllTestWikiPages();
    }

    @AfterAll
    public static void teardown() throws GitLabApiException {
        deleteAllTestWikiPages();
    }

    private static void deleteAllTestWikiPages() {
        if (testProject != null) {
            try {
                List<WikiPage> wikiPages = gitLabApi.getWikisApi().getPages(testProject);
                wikiPages.stream().filter(wp -> wp.getTitle().startsWith(TEST_WIKI_TITLE_PREFIX)).map(WikiPage::getSlug).forEach(slug -> {
                    try {
                        gitLabApi.getWikisApi().deletePage(testProject, slug);
                    } catch (GitLabApiException ignored) {
                    }
                });
            } catch (GitLabApiException ignore) {

            }
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(testProject != null);
    }

    private WikiPage createWikiPage(String title, String content) throws GitLabApiException {
        return (gitLabApi.getWikisApi().createPage(testProject.getId(), title, content));
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
        wikiPage = gitLabApi.getWikisApi().updatePage(testProject, wikiPage.getSlug(), title, "some content");
        assertEquals(title, wikiPage.getTitle());
        assertEquals("some content", wikiPage.getContent());
    }

    @Test
    public void testListWikiPages() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test listWikiPages()";
        WikiPage newWikiPage = createWikiPage(title, testContent);
        assertNotNull(newWikiPage);

        String wikiPageSlug = newWikiPage.getSlug();
        List<WikiPage> wikiPages = gitLabApi.getWikisApi().getPages(testProject);
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
        gitLabApi.getWikisApi().deletePage(testProject, wikiPageSlug);
        List<WikiPage> wikiPages = gitLabApi.getWikisApi().getPages(testProject);
        if (wikiPages.stream().anyMatch(wp -> wp.getSlug().equals(wikiPageSlug))) {
            fail("WikiPage was not deleted.");
        }
    }

    @Test
    public void testAttachment() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test createWikiPage()";
        WikiPage wikiPage = createWikiPage(title, testContent);
        assertNotNull(wikiPage);

        File attachFile = new File("README.md");
        WikiAttachment attachment = gitLabApi.getWikisApi().uploadAttachment(testProject, attachFile);
        assertNotNull(attachment);
        assertEquals("README.md", attachment.getFileName());
    }

    @Test
    public void testAttachmentWithBranch() throws GitLabApiException {
        String title = TEST_WIKI_TITLE_PREFIX + "Test createWikiPage()";
        WikiPage wikiPage = createWikiPage(title, testContent);
        assertNotNull(wikiPage);

        File attachFile = new File("README.md");
        WikiAttachment attachment = gitLabApi.getWikisApi().uploadAttachment(testProject, attachFile, "master");
        assertNotNull(attachment);
        assertEquals("README.md", attachment.getFileName());
        assertEquals("master", attachment.getBranch());
    }
}
