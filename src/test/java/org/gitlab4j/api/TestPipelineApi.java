package org.gitlab4j.api;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.PipelineSchedule;
import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPipelineApi {
    // The following needs to be set to your test repository
    private static final String TEST_NAMESPACE;
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;

    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static final String SCHEDULE_DESCRIPTION = "Test pipeline schedule - DELETE AFTER TEST";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestPipelineApi() {
        super();
    }

    private static void deleteTestSchedules() {

        if (testProject == null) {
            return;
        }

        try {

            List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProject);
            if (pipelineSchedules == null || pipelineSchedules.isEmpty()) {
                return;
            }

            for (PipelineSchedule schedule : pipelineSchedules) {
                if (schedule.getDescription().startsWith(SCHEDULE_DESCRIPTION)) {
                    gitLabApi.getPipelineApi().deletePipelineSchedule(testProject, schedule.getId());
                }
            }

        } catch (Exception ignore) {
        }
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

            try {
                testProject = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
            } catch (GitLabApiException gle) {
            }

            deleteTestSchedules();

        } else {
            System.err.print(problems);
        }
    }

    @AfterClass
    public static void teardown() {
        deleteTestSchedules();
    }


    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testCreateAndUpdateProjectPipeLineSchedule() throws GitLabApiException {

        assertNotNull(testProject);

        String scheduleDescription = SCHEDULE_DESCRIPTION + " - test updatePipelineSchedule()";
        PipelineSchedule newPipelineSchedule = new PipelineSchedule();
        newPipelineSchedule.setDescription(scheduleDescription);
        newPipelineSchedule.setCron("2 4 * * *");
        newPipelineSchedule.setRef("master");
        PipelineSchedule createdPipelineSchedule = gitLabApi.getPipelineApi().createPipelineSchedule(testProject, newPipelineSchedule);
        assertNotNull(createdPipelineSchedule);

        // Make sure the created schedule is present before updating
        List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProject);
        assertNotNull(pipelineSchedules);
        assertTrue(pipelineSchedules.stream().map(PipelineSchedule::getDescription).collect(toList()).contains(scheduleDescription));

        String newScheduleDescription = scheduleDescription + " - updated";
        createdPipelineSchedule.setDescription(newScheduleDescription);
        gitLabApi.getPipelineApi().updatePipelineSchedule(testProject, createdPipelineSchedule);

        pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProject);
        assertNotNull(pipelineSchedules);

        List<String> scheduleDecriptions = pipelineSchedules.stream().map(PipelineSchedule::getDescription).collect(toList());
        assertFalse(scheduleDecriptions.contains(scheduleDescription));
        assertTrue(scheduleDecriptions.contains(newScheduleDescription));
    }

    @Test
    public void testDeleteProjectPipeLineSchedule() throws GitLabApiException {

        assertNotNull(testProject);

        String scheduleDescription = SCHEDULE_DESCRIPTION + " - test deletePipelineSchedule()";
        PipelineSchedule newPipelineSchedule = new PipelineSchedule();
        newPipelineSchedule.setDescription(scheduleDescription);
        newPipelineSchedule.setCron("1 4 * * *");
        newPipelineSchedule.setRef("master");
        PipelineSchedule createdPipelineSchedule = gitLabApi.getPipelineApi().createPipelineSchedule(testProject, newPipelineSchedule);
        assertNotNull(createdPipelineSchedule);

        // Make sure the created schedule is present before deleting
        List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProject);
        assertNotNull(pipelineSchedules);
        assertTrue(pipelineSchedules.stream().map(PipelineSchedule::getDescription).collect(toList()).contains(scheduleDescription));

        gitLabApi.getPipelineApi().deletePipelineSchedule(testProject, createdPipelineSchedule.getId());
        pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProject);
        assertNotNull(pipelineSchedules);
        assertFalse(pipelineSchedules.stream().map(PipelineSchedule::getDescription).collect(toList()).contains(scheduleDescription));
    }
}
