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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;
import java.util.List;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Runner;
import org.gitlab4j.api.models.Runner.RunnerStatus;
import org.gitlab4j.api.models.Runner.RunnerType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_NAMESPACE
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 * <p>
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class) // FIXME check if it works properly
public class TestRunnersApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    public TestRunnersApi() {
        super();
    }

    @BeforeAll
    public static void setup() throws GitLabApiException {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();

        if (allRunners.size() > 0) {

            for (Runner runner : allRunners) {
                gitLabApi.getRunnersApi().removeRunner(runner.getId());
            }

            allRunners = gitLabApi.getRunnersApi().getAllRunners();
            assertEquals(0, allRunners.size());
        }
    }

    /**
     * creates a new runner for a random project
     */
    private static Runner createRunner() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProjects().get(0);
        project = gitLabApi.getProjectApi().getProject(project.getId());
        String runnersToken = project.getRunnersToken();

        return (gitLabApi.getRunnersApi().registerRunner(runnersToken,
                "Junit registered runner", true,
                Arrays.asList("wow"), false,
                false, null));
    }

    @BeforeEach
    public void beforeMethod() throws GitLabApiException {
        assumeTrue(gitLabApi != null);

        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();

        for (Runner runner : allRunners) {
            gitLabApi.getRunnersApi().removeRunner(runner.getId());
        }
    }

    @Test
    public void shouldHaveRunnerDetails() throws GitLabApiException {

        // Arrange
        Runner runner = createRunner();
        assertNotNull(runner, "Failed to create test runner.");

        List<Runner> runners = gitLabApi.getRunnersApi().getAllRunners();
        assertEquals(1, runners.size());
        assertNotNull("Description should not be null", runners.get(0).getDescription());
    }

    @Test
    public void shouldRemoveRunner() throws GitLabApiException {

        for (int i = 0; i < 3; i++) {
            Runner runner = createRunner();
            assertNotNull(runner, "Failed to create test runner.");
        }

        List<Runner> allRunners = gitLabApi.getRunnersApi().getAllRunners();
        assertEquals(3, allRunners.size());

        for (Runner r : allRunners) {
            gitLabApi.getRunnersApi().removeRunner(r.getId());
        }

        allRunners = gitLabApi.getRunnersApi().getAllRunners();
        assertEquals(0, allRunners.size());
    }

    @Test
    public void shouldHavePausedRunner() throws GitLabApiException {

        Runner runner = createRunner();
        assertNotNull(runner, "Failed to create test runner.");

        List<Runner> runners = gitLabApi.getRunnersApi().getAllRunners(RunnerType.GROUP_TYPE, RunnerStatus.PAUSED);
        assertTrue(runners.isEmpty());
    }
}
