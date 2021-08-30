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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemErr;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@ExtendWith(SystemStubsExtension.class)
public class TestRequestResponseLogging implements PropertyConstants {

	@SystemStub
	private SystemErr systemErr;

	@TempDir
	static Path tempDir;

    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL = HelperUtils.getProperty(HOST_URL_KEY);
    private static final String TEST_PRIVATE_TOKEN = HelperUtils.getProperty(PRIVATE_TOKEN_KEY);

    private static GitLabApi gitLabApiWithEntityLogging;
    private static GitLabApi gitLabApiNoEntityLogging;
    private static GitLabApi gitLabApiNoMaskingLogging;
    private static GitLabApi gitLabApiWithoutLogging;

    private static Logger logger;
    private static StreamHandler loggingHandler;
    private static File tempLoggingFile;

    @BeforeAll
    public static void setup() throws Exception {

        String problems = "";

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {

            tempLoggingFile = Files.createFile(tempDir.resolve("test-loging.log")).toFile();

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

        assertTrue(log.contains("PRIVATE-TOKEN:"), "Request/response log information was missing.");
        assertTrue(log.contains("PRIVATE-TOKEN: ********"), "Request/response PRIVATE-TOKEN value was incorrectly present.");
        assertTrue(log.contains("/api/v4/projects"), "Request/response log information was missing.");
        assertTrue(log.contains("...more..."), "Request/response entity was missing.");
    }

    @Test
    public void shouldLogRequestsWithoutEntities() throws Exception {

        assumeTrue(gitLabApiNoEntityLogging != null);
        clearLogFile();
        gitLabApiNoEntityLogging.getProjectApi().getProjects(1, 1);
        String log = readLogFile();
        System.out.println(log);

        assertTrue(log.contains("PRIVATE-TOKEN:"), "Request/response log information was missing.");
        assertTrue(log.contains("PRIVATE-TOKEN: ********"), "Request/response PRIVATE-TOKEN value was incorrectly present.");
        assertTrue(log.contains("/api/v4/projects"), "Request/response log information was missing.");
        assertFalse(log.contains("...more..."), "Request/response entity was incorrectly present.");
    }

    @Test
    public void shouldLogPrivateToken() throws Exception {

        assumeTrue(gitLabApiNoMaskingLogging != null);
        clearLogFile();
        gitLabApiNoMaskingLogging.getProjectApi().getProjects(1, 1);
        String log = readLogFile();
        System.out.println(log);

        assertTrue(log.contains("PRIVATE-TOKEN:"), "Request/response log information was missing.");
        assertFalse(log.contains("PRIVATE-TOKEN: ********"), "Request/response PRIVATE-TOKEN value was missing.");
        assertTrue(log.contains("/api/v4/projects"), "Request/response log information was missing.");
        assertTrue(log.contains("...more..."), "Request/response entity was incorrectly present.");
    }

    @Test
    public void shouldNotLogRequests() throws GitLabApiException {

        assumeTrue(gitLabApiWithoutLogging != null);
        systemErr.clear();
        gitLabApiWithoutLogging.getProjectApi().getProjects(1, 1);
        String log = systemErr.getText();

        assertFalse(log.contains("PRIVATE-TOKEN:"), "Request/response log information was incorrectly present.");
        assertFalse(log.contains("/api/v4/projects"), "Request/response log information was incorrectly present.");
        assertFalse(log.contains("...more..."), "Request/response entity was incorrectly present.");
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
