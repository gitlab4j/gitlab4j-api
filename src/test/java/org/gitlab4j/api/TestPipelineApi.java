package org.gitlab4j.api;

import org.gitlab4j.api.models.PipelineSchedule;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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


    private static GitLabApi gitLabApi;

    public TestPipelineApi() {
        super();
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
            gitLabApi = new GitLabApi(GitLabApi.ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }
    }

    @AfterClass
    public static void teardown() {
    }


    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testCreateProjectPipeLineSchedule() throws GitLabApiException {
        assumeTrue(TEST_NAMESPACE != null && TEST_PROJECT_NAME != null);
        assumeTrue(TEST_NAMESPACE.trim().length() > 0 && TEST_PROJECT_NAME.trim().length() > 0);

        Integer testProjectId = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME).getId();
        PipelineSchedule newPipelineSchedule = new PipelineSchedule();
        newPipelineSchedule.setDescription("test pipeline schedule");
        newPipelineSchedule.setCron("0 4 * * *");
        newPipelineSchedule.setRef("master");
        PipelineSchedule createdPipelineSchedule = gitLabApi.getPipelineApi().createPipelineSchedule(testProjectId,newPipelineSchedule);
        assertNotNull(createdPipelineSchedule);
        List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProjectId);
        assertFalse(pipelineSchedules.isEmpty());
    }

    @Test
    public void testModifyProjectPipeLineSchedule() throws GitLabApiException {
        assumeTrue(TEST_NAMESPACE != null && TEST_PROJECT_NAME != null);
        assumeTrue(TEST_NAMESPACE.trim().length() > 0 && TEST_PROJECT_NAME.trim().length() > 0);

        Integer testProjectId = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME).getId();
        List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProjectId);
        assertTrue(pipelineSchedules.size()==1);
        PipelineSchedule existingPipelineSchedule = pipelineSchedules.get(0);
        assertTrue(existingPipelineSchedule.getDescription().equals("test pipeline schedule"));
        existingPipelineSchedule.setDescription("new name");
        gitLabApi.getPipelineApi().modifyPipelineSchedule(testProjectId,existingPipelineSchedule);
        pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProjectId);
        PipelineSchedule newPipelineSchedule = pipelineSchedules.get(0);
        assertTrue(pipelineSchedules.size()==1);
        assertTrue(newPipelineSchedule.equals("new name"));
    }


    @Test
    public void testDeleteProjectPipeLineSchedule() throws GitLabApiException {
        assumeTrue(TEST_NAMESPACE != null && TEST_PROJECT_NAME != null);
        assumeTrue(TEST_NAMESPACE.trim().length() > 0 && TEST_PROJECT_NAME.trim().length() > 0);

        Integer testProjectId = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME).getId();
        List<PipelineSchedule> pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProjectId);
        assertFalse(pipelineSchedules.isEmpty());
        gitLabApi.getPipelineApi().deletePipelineSchedule(testProjectId,pipelineSchedules.get(0).getId());
        pipelineSchedules = gitLabApi.getPipelineApi().getPipelineSchedules(testProjectId);
        assertTrue(pipelineSchedules.isEmpty());
    }
}
