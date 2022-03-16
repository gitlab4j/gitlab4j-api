package org.gitlab4j.api;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.gitlab4j.api.models.Pipeline;
import org.gitlab4j.api.models.PipelineSchedule;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.Trigger;
import org.gitlab4j.api.models.Variable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestPipelineApi extends AbstractIntegrationTest {

    private static final String SCHEDULE_DESCRIPTION = "Test pipeline schedule - DELETE AFTER TEST";
    private static final String TRIGGER_DESCRIPTION = "Test pipeline trigger - DELETE AFTER TEST";
    private static final String TEST_GITLAB_CI_YML_CONTENT = "build:\n" +
        "  stage: build\n  script:\n    - echo 'Empty build for testing variables with GitLab4J-API'";


    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static RepositoryFile createdGitlabCiYml;
    private static RepositoryFile gitlabCiYml;

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

        if (createdGitlabCiYml != null) {
            try {
                gitLabApi.getRepositoryFileApi().deleteFile(
                    testProject, ".gitlab-ci.yml", "master", "No longer needed.");
            } catch (Exception ignore) {}
        }
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        Optional<RepositoryFile> fileInfo =
                gitLabApi.getRepositoryFileApi().getOptionalFileInfo(testProject, ".gitlab-ci.yml", "master");
        if (fileInfo.isPresent()) {
            gitlabCiYml = fileInfo.get();
        } else {

            try {
                RepositoryFile file = new RepositoryFile();
                file.setFilePath(".gitlab-ci.yml");
                file.setContent(TEST_GITLAB_CI_YML_CONTENT);
                createdGitlabCiYml = gitLabApi.getRepositoryFileApi().createFile(
                        testProject, file, "master", "Need for testing pipelines.");
                gitlabCiYml = createdGitlabCiYml;
                System.out.println("Created .gitlab-ci.yml file for testing purposes.");
            } catch (Exception ignore) {}
        }
    }

    @AfterAll
    public static void teardown() {
        deleteTestResources();
    }

    @BeforeEach
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

        // Skip this test if no .gitlab-ci.yml file is in the test project
        assumeTrue(gitlabCiYml != null);

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

    @Test
    public void testCreatePipelineNoVariables() throws GitLabApiException {

        // Skip this test if no .gitlab-ci.yml file is in the test project
        assumeTrue(gitlabCiYml != null);

        // Act
        Pipeline pipeline = gitLabApi.getPipelineApi().createPipeline(testProject, "master");

        // Assert
        assertNotNull(pipeline);

        gitLabApi.getPipelineApi().deletePipeline(testProject, pipeline.getId());
    }

    @Test
    public void testCreatePipelineWithVariables() throws GitLabApiException {

        // Skip this test if no .gitlab-ci.yml file is in the test project
        assumeTrue(gitlabCiYml != null);

        // Arrange
        List<Variable> variableList = new ArrayList<>();
        variableList.add(new Variable("VAR1", "value1"));
        variableList.add(new Variable("VAR2", "value2"));

        // Act
        Pipeline pipeline = gitLabApi.getPipelineApi().createPipeline(testProject, "master", variableList);

        // Assert
        assertNotNull(pipeline);

        gitLabApi.getPipelineApi().deletePipeline(testProject, pipeline.getId());
    }

    @Test
    public void testCreatePipelineWithMapVariables() throws GitLabApiException {

        // Skip this test if no .gitlab-ci.yml file is in the test project
        assumeTrue(gitlabCiYml != null);

        // Arrange
        Map<String, String> variableMap = new HashMap<>();
        variableMap.put("VAR1", "value1");
        variableMap.put("VAR2", "value2");

        // Act
        Pipeline pipeline = gitLabApi.getPipelineApi().createPipeline(testProject, "master", variableMap);

        // Assert
        assertNotNull(pipeline);

        gitLabApi.getPipelineApi().deletePipeline(testProject, pipeline.getId());
    }

    @Test
    public void testPipelineVariables() throws GitLabApiException {

        // Skip this test if no .gitlab-ci.yml file is in the test project
        assumeTrue(gitlabCiYml != null);

        // Arrange
        Map<String, String> variableMap = new HashMap<>();
        variableMap.put("VAR1", "value1");
        variableMap.put("VAR2", "value2");

        // Act
        Pipeline pipeline = gitLabApi.getPipelineApi().createPipeline(testProject, "master", variableMap);

        // Assert
        assertNotNull(pipeline);

        try {

            Stream<Variable> stream = gitLabApi.getPipelineApi().getPipelineVariablesStream(testProject, pipeline.getId());
            stream.forEach(v -> {
                String value = variableMap.get(v.getKey());
                assertEquals(value, v.getValue());
            });

            List<Variable> variables = gitLabApi.getPipelineApi().getPipelineVariables(testProject, pipeline.getId());
            assertEquals(variableMap.size(), variables.size());
            variables.forEach(v -> {
                String value = variableMap.get(v.getKey());
                assertEquals(value, v.getValue());
            });

        } finally {
            gitLabApi.getPipelineApi().deletePipeline(testProject, pipeline.getId());
        }
    }
}
