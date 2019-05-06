package org.gitlab4j.api;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.List;
import java.util.stream.Stream;

import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.PipelineSchedule;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Trigger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class TestPipelineApi extends AbstractIntegrationTest {

    private static final String SCHEDULE_DESCRIPTION = "Test pipeline schedule - DELETE AFTER TEST";
    private static final String TRIGGER_DESCRIPTION = "Test pipeline trigger - DELETE AFTER TEST";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestPipelineApi() {
        super();
    }

    private static void deleteTestResources() {

        if (testProject == null) {
            return;
        }

        try {

            List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProject);
            for (PipelineSchedule schedule : pipelineSchedules) {
                if (schedule.getDescription().startsWith(SCHEDULE_DESCRIPTION)) {
                    gitLabApi.getPipelineApi().deletePipelineSchedule(testProject, schedule.getId());
                }
            }

        } catch (Exception ignore) {}

        try {

            List<Trigger> triggers = gitLabApi.getPipelineApi().getPipelineTriggers(testProject);
            for (Trigger trigger : triggers) {
                if (trigger.getDescription().startsWith(TRIGGER_DESCRIPTION)) {
                    gitLabApi.getPipelineApi().deletePipelineTrigger(testProject, trigger.getId());
                }
            }

        } catch (Exception ignore) {}
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @AfterClass
    public static void teardown() {
        deleteTestResources();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
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

    @Test
    public void testCreateAndUpdatePipelineTrigger() throws GitLabApiException {

        assertNotNull(testProject);

        String triggerDescription = TRIGGER_DESCRIPTION + " - test updatePipelineTrigger()";
        Trigger createdTrigger = gitLabApi.getPipelineApi().createPipelineTrigger(testProject, triggerDescription);
        assertNotNull(createdTrigger);

        // Make sure the created trigger is present before updating
        List<Trigger> pipelineTriggers = gitLabApi.getPipelineApi().getPipelineTriggers(testProject);
        assertNotNull(pipelineTriggers);
        assertTrue(pipelineTriggers.stream().map(Trigger::getDescription).collect(toList()).contains(triggerDescription));

        String newTriggerDescription = triggerDescription + " - updated";
        gitLabApi.getPipelineApi().updatePipelineTrigger(testProject, createdTrigger.getId(), newTriggerDescription);

        pipelineTriggers = gitLabApi.getPipelineApi().getPipelineTriggers(testProject);
        assertNotNull(pipelineTriggers);

        List<String> triggerDecriptions = pipelineTriggers.stream().map(Trigger::getDescription).collect(toList());
        assertFalse(triggerDecriptions.contains(triggerDescription));
        assertTrue(triggerDecriptions.contains(newTriggerDescription));
    }

    @Test
    public void testDeletePipelineTrigger() throws GitLabApiException {

        assertNotNull(testProject);

        String triggerDescription = TRIGGER_DESCRIPTION + " - test deletePipelineTrigger()";
        Trigger createdTrigger = gitLabApi.getPipelineApi().createPipelineTrigger(testProject, triggerDescription);
        assertNotNull(createdTrigger);

        // Make sure the created trigger is present before deleting
        List<Trigger> pipelineTriggers = gitLabApi.getPipelineApi().getPipelineTriggers(testProject);
        assertNotNull(pipelineTriggers);
        assertTrue(pipelineTriggers.stream().map(Trigger::getDescription).collect(toList()).contains(triggerDescription));

        gitLabApi.getPipelineApi().deletePipelineTrigger(testProject, createdTrigger.getId());
        pipelineTriggers = gitLabApi.getPipelineApi().getPipelineTriggers(testProject);
        assertNotNull(pipelineTriggers);
        assertFalse(pipelineTriggers.stream().map(Trigger::getDescription).collect(toList()).contains(triggerDescription));
    }

    @Test
    public void testTriggerAndCancelPipeline() throws GitLabApiException {

        assertNotNull(testProject);

        String triggerDescription = TRIGGER_DESCRIPTION + " - test triggerPipeline() - " + HelperUtils.getRandomInt(1000);
        Trigger createdTrigger = gitLabApi.getPipelineApi().createPipelineTrigger(testProject, triggerDescription);
        assertNotNull(createdTrigger);

        Pipeline pipeline = gitLabApi.getPipelineApi().triggerPipeline(testProject, createdTrigger, "master",  null);
        assertNotNull(pipeline);

        Pipeline canceledPipeline = gitLabApi.getPipelineApi().cancelPipelineJobs(testProject, pipeline.getId());
        assertNotNull(canceledPipeline);

        gitLabApi.getPipelineApi().deletePipelineTrigger(testProject, createdTrigger.getId());
        Stream<Trigger>pipelineTriggers = gitLabApi.getPipelineApi().getPipelineTriggersStream(testProject);
        assertNotNull(pipelineTriggers);
        assertFalse(pipelineTriggers.map(Trigger::getDescription).collect(toList()).contains(triggerDescription));
    }
}
