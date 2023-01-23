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

import static org.gitlab4j.api.models.Setting.LOCAL_MARKDOWN_VERSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.gitlab4j.api.models.ApplicationSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * <p>
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestApplicationSettingsApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Object savedLocalMarkdownVersion;
    private static boolean fetchedApplicationSettings;

    public TestApplicationSettingsApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        if (gitLabApi != null) {
            try {
                ApplicationSettings appSettings = gitLabApi.getApplicationSettingsApi().getApplicationSettings();
                savedLocalMarkdownVersion = appSettings.getSetting(LOCAL_MARKDOWN_VERSION);
                fetchedApplicationSettings = true;
            } catch (Exception ignore) {}
        }
    }

    @AfterAll
    public static void teardown() {

        if (fetchedApplicationSettings) {
            try {
                gitLabApi.getApplicationSettingsApi().updateApplicationSetting(
                        LOCAL_MARKDOWN_VERSION, savedLocalMarkdownVersion);
            } catch (Exception ignore) {}
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(fetchedApplicationSettings);
    }

    @Test
    public void testGetApplicationSettings() throws GitLabApiException {
        ApplicationSettings appSettings = gitLabApi.getApplicationSettingsApi().getApplicationSettings();
        assertNotNull(appSettings);
    }

    @Test
    public void testUpdateApplicationSetting() throws GitLabApiException {

        int newValue = (savedLocalMarkdownVersion != null ? ((Integer)savedLocalMarkdownVersion).intValue() + 1234 : 1234);
        ApplicationSettings appSettings = gitLabApi.getApplicationSettingsApi().updateApplicationSetting(LOCAL_MARKDOWN_VERSION, newValue);
        assertNotNull(appSettings);

        Object updatedLocalMarkdownVersion = appSettings.getSetting(LOCAL_MARKDOWN_VERSION);
        assertEquals(newValue, updatedLocalMarkdownVersion);

        appSettings = gitLabApi.getApplicationSettingsApi().updateApplicationSetting(LOCAL_MARKDOWN_VERSION, savedLocalMarkdownVersion);
        updatedLocalMarkdownVersion = appSettings.getSetting(LOCAL_MARKDOWN_VERSION);
        assertEquals(savedLocalMarkdownVersion, updatedLocalMarkdownVersion);
    }

    @Test
    public void testUpdateApplicationSettings() throws GitLabApiException {

        // Arrange
        int newValue = (savedLocalMarkdownVersion != null ? ((Integer)savedLocalMarkdownVersion).intValue() + 123 : 123);
        ApplicationSettings appSettings = new ApplicationSettings();
        appSettings.addSetting(LOCAL_MARKDOWN_VERSION, newValue);

        // Act
        ApplicationSettings updatedAppSettings = gitLabApi.getApplicationSettingsApi().updateApplicationSettings(appSettings);

        // Assert
        assertNotNull(updatedAppSettings);
        Object updatedLocalMarkdownVersion = updatedAppSettings.getSetting(LOCAL_MARKDOWN_VERSION);
        assertEquals(newValue, updatedLocalMarkdownVersion);

        // Arrange
        appSettings = new ApplicationSettings();
        appSettings.addSetting(LOCAL_MARKDOWN_VERSION, savedLocalMarkdownVersion);

        // Act
        updatedAppSettings = gitLabApi.getApplicationSettingsApi().updateApplicationSettings(appSettings);

        // Assert
        updatedLocalMarkdownVersion = updatedAppSettings.getSetting(LOCAL_MARKDOWN_VERSION);
        assertEquals(savedLocalMarkdownVersion, updatedLocalMarkdownVersion);
    }
}
