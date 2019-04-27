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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
public class TestRequestResponseLogging {

    @ClassRule
    public final static SystemErrRule systemErrorRule = new SystemErrRule().enableLog();

    @ClassRule
    public final static TemporaryFolder tempFolder = new TemporaryFolder();


    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApiWithEntityLogging;
    private static GitLabApi gitLabApiNoEntityLogging;
    private static GitLabApi gitLabApiNoMaskingLogging;
    private static GitLabApi gitLabApiWithoutLogging;

    private static Logger logger;
    private static StreamHandler loggingHandler;
    private static File tempLoggingFile;

    @BeforeClass
    public static void setup() throws Exception {

        String problems = "";

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {

            tempLoggingFile = tempFolder.newFile("test-loging.log");

            loggingHandler = new FileHandler(tempLoggingFile.getAbsolutePath());
            loggingHandler.setFormatter(new SimpleFormatter());
            logger = Logger.getLogger(TestRequestResponseLogging.class.getName());
            logger.setUseParentHandlers(false);
            logger.addHandler(loggingHandler);
            loggingHandler.setLevel(Level.ALL);
            logger.setLevel(Level.ALL);

            gitLabApiWithEntityLogging = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);
            gitLabApiWithEntityLogging.enableRequestResponseLogging(logger, Level.INFO, 100);
            gitLabApiNoEntityLogging = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);
            gitLabApiNoEntityLogging.enableRequestResponseLogging(logger, Level.INFO);
            gitLabApiNoMaskingLogging = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);
            gitLabApiNoMaskingLogging.enableRequestResponseLogging(logger, Level.INFO, 100, new ArrayList<String>());

            gitLabApiWithoutLogging = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);

        } else {
            System.err.print(problems);
        }
    }

    @Test
    public void shouldLogRequestsWithEntities() throws Exception {

        assumeTrue(gitLabApiWithEntityLogging != null);
        clearLogFile();
        gitLabApiWithEntityLogging.getProjectApi().getProjects(1, 1);
        String log = readLogFile();
        System.out.println(log);

        assertTrue("Request/response log information was missing.", log.contains("PRIVATE-TOKEN:"));
        assertTrue("Request/response PRIVATE-TOKEN value was incorrectly present.", log.contains("PRIVATE-TOKEN: ********"));
        assertTrue("Request/response log information was missing.", log.contains("/api/v4/projects"));
        assertTrue("Request/response entity was missing.", log.contains("...more..."));
    }

    @Test
    public void shouldLogRequestsWithoutEntities() throws Exception {

        assumeTrue(gitLabApiNoEntityLogging != null);
        clearLogFile();
        gitLabApiNoEntityLogging.getProjectApi().getProjects(1, 1);
        String log = readLogFile();
        System.out.println(log);

        assertTrue("Request/response log information was missing.", log.contains("PRIVATE-TOKEN:"));
        assertTrue("Request/response PRIVATE-TOKEN value was incorrectly present.", log.contains("PRIVATE-TOKEN: ********"));
        assertTrue("Request/response log information was missing.", log.contains("/api/v4/projects"));
        assertFalse("Request/response entity was incorrectly present.", log.contains("...more..."));
    }

    @Test
    public void shouldLogPrivateToken() throws Exception {

        assumeTrue(gitLabApiNoMaskingLogging != null);
        clearLogFile();
        gitLabApiNoMaskingLogging.getProjectApi().getProjects(1, 1);
        String log = readLogFile();
        System.out.println(log);

        assertTrue("Request/response log information was missing.", log.contains("PRIVATE-TOKEN:"));
        assertFalse("Request/response PRIVATE-TOKEN value was missing.", log.contains("PRIVATE-TOKEN: ********"));
        assertTrue("Request/response log information was missing.", log.contains("/api/v4/projects"));
        assertTrue("Request/response entity was incorrectly present.", log.contains("...more..."));
    }

    @Test
    public void shouldNotLogRequests() throws GitLabApiException {

        assumeTrue(gitLabApiWithoutLogging != null);
        systemErrorRule.clearLog();
        gitLabApiWithoutLogging.getProjectApi().getProjects(1, 1);
        String log = systemErrorRule.getLog();

        assertFalse("Request/response log information was incorrectly present.", log.contains("PRIVATE-TOKEN:"));
        assertFalse("Request/response log information was incorrectly present.", log.contains("/api/v4/projects"));
        assertFalse("Request/response entity was incorrectly present.", log.contains("...more..."));
    }

    private static String readLogFile() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        Files.lines(Paths.get(tempLoggingFile.getAbsolutePath()), StandardCharsets.UTF_8)
            .forEach(s -> contentBuilder.append(s).append("\n"));
        return contentBuilder.toString();
    }

    private static void clearLogFile() throws IOException {
        new PrintWriter(tempLoggingFile.getAbsolutePath()).close();
    }
}
