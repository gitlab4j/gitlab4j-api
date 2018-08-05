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

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Runner;
import org.gitlab4j.api.models.RunnerDetail;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;
import static org.assertj.core.api.Assertions.*;


/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * TEST_GROUP_PROJECT
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 * <p>
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@FixMethodOrder(MethodSorters.JVM)
public class TestRequestResponseLogging {

    @Rule
    public final SystemErrRule systemErrorRule = new SystemErrRule().enableLog();


    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    private static GitLabApi gitLabApiWithoutLogging;

    static {
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApi;

    public TestRequestResponseLogging() {
        super();
    }

    @BeforeClass
    public static void setup() throws GitLabApiException {

        String problems = "";

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN, true);
            gitLabApiWithoutLogging = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN, false);
        } else {
            System.err.print(problems);
        }


    }


    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
        assumeTrue(gitLabApiWithoutLogging != null);
    }

    @Test
    public void shouldLogRequests() throws GitLabApiException {

        systemErrorRule.clearLog();
        gitLabApi.getRunnersApi().getAllRunners();
        String log = systemErrorRule.getLog();

        assertThat(log).contains("/api/v4/runners");

    }

    @Test
    public void shouldNotLogRequests() throws GitLabApiException {

        systemErrorRule.clearLog();
        gitLabApiWithoutLogging.getRunnersApi().getAllRunners();
        String log = systemErrorRule.getLog();

        assertThat(log).doesNotContain("/api/v4/runners");

    }


}
