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
import org.gitlab4j.api.models.*;
import org.glassfish.jersey.internal.guava.Lists;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.*;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

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
public class TestRunnersApi {

    // The following needs to be set to your test repository
    private static final String TEST_NAMESPACE;
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    private static final String TEST_GROUP;
    private static final String TEST_GROUP_PROJECT;

    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
        TEST_GROUP = TestUtils.getProperty("TEST_GROUP");
        TEST_GROUP_PROJECT = TestUtils.getProperty("TEST_GROUP_PROJECT");
    }

    private static final String TEST_PROJECT_NAME_1 = "test-gitlab4j-create-project";
    private static final String TEST_PROJECT_NAME_2 = "test-gitlab4j-create-project-2";
    private static final String TEST_PROJECT_NAME_UPDATE = "test-gitlab4j-create-project-update";
    private static GitLabApi gitLabApi;

    public TestRunnersApi() {
        super();
    }

    @BeforeClass
    public static void setup() throws GitLabApiException {

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
            gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN, true);
        } else {
            System.err.print(problems);
        }

        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();

        if (allRunners.size() > 0) {

            for (Runner runner : allRunners) {
                RunnerDetail runnerDetail = gitLabApi.getRunnersApi().getRunnerDetail(runner.getId());
                gitLabApi.getRunnersApi().deleteRunner(runnerDetail.getToken());
            }

            allRunners = gitLabApi.getRunnersApi().getAllRunners();

            assertEquals(0, allRunners.size());
        }

    }

    /**
     * creates a new runner for a random project
     */
    private static void createRunner() throws GitLabApiException {

        // WHEN
        Project project = gitLabApi.getProjectApi().getProjects().get(0);
        project = gitLabApi.getProjectApi().getProject(project.getId());
        String runnersToken = project.getRunnersToken();

        // THEN
        gitLabApi.getRunnersApi().registerRunner(runnersToken,
                "Junit registered runner", true,
                Arrays.asList("wow"), false,
                false, null);

        // ASSERT
        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();

    }


    @Before
    public void beforeMethod() throws GitLabApiException {
        assumeTrue(gitLabApi != null);

        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();

        for (Runner runner : allRunners) {
            RunnerDetail runnerDetail = gitLabApi.getRunnersApi().getRunnerDetail(runner.getId());
            gitLabApi.getRunnersApi().deleteRunner(runnerDetail.getToken());
        }

    }

    @Test
    public void shouldHaveRunnerDetails() throws GitLabApiException {

        createRunner();

        List<Runner> runners = gitLabApi.getRunnersApi().getAllRunners();

        assertEquals(1, runners.size());
        assertNotNull("Description should not be null", runners.get(0).getDescription());

    }

    @Test
    public void shouldDeleteRunner() throws GitLabApiException {

        createRunner();
        createRunner();
        createRunner();

        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();
        assertEquals(3, allRunners.size());


        for (Runner runner : allRunners) {
            RunnerDetail runnerDetail = gitLabApi.getRunnersApi().getRunnerDetail(runner.getId());
            gitLabApi.getRunnersApi().deleteRunner(runnerDetail.getToken());
        }

        allRunners = gitLabApi.getRunnersApi().getAllRunners();
        assertEquals(0, allRunners.size());


    }


}
