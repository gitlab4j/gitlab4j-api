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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Variable;
import org.gitlab4j.api.models.Visibility;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * TEST_GROUP_PROJECT
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_GROUP;
    private static final String TEST_GROUP_PROJECT;
    private static final String TEST_XFER_NAMESPACE;
    static {
        TEST_GROUP = TestUtils.getProperty("TEST_GROUP");
        TEST_GROUP_PROJECT = TestUtils.getProperty("TEST_GROUP_PROJECT");
        TEST_XFER_NAMESPACE = TestUtils.getProperty("TEST_XFER_NAMESPACE");
    }

    private static final String TEST_PROJECT_NAME_1 = "test-gitlab4j-create-project";
    private static final String TEST_PROJECT_NAME_2 = "test-gitlab4j-create-project-2";
    private static final String TEST_PROJECT_NAME_UPDATE = "test-gitlab4j-create-project-update";
    private static final String TEST_XFER_PROJECT_NAME = "test-gitlab4j-xfer-project";
    private static final String TEST_VARIABLE_KEY_PREFIX = "TEST_VARIABLE_KEY_";
    private static GitLabApi gitLabApi;

    public TestProjectApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        deleteAllTestProjects();
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        deleteAllTestProjects();
    }

    private static void deleteAllTestProjects() {
        if (gitLabApi == null) {
            return;
        }

        try {
            Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME_1);
            gitLabApi.getProjectApi().deleteProject(project);
        } catch (GitLabApiException ignore) {}

        try {
            Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME_2);
            gitLabApi.getProjectApi().deleteProject(project);
        } catch (GitLabApiException ignore) {}

        try {
            Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME_UPDATE);
            gitLabApi.getProjectApi().deleteProject(project);
        } catch (GitLabApiException ignore) {}

        try {
            Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_XFER_PROJECT_NAME);
            gitLabApi.getProjectApi().deleteProject(project);
        } catch (GitLabApiException ignore) {}

        if (TEST_GROUP != null && TEST_PROJECT_NAME != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
                List<Group> groups = gitLabApi.getGroupApi().getGroups(TEST_GROUP);
                gitLabApi.getProjectApi().unshareProject(project.getId(), groups.get(0).getId());

                List<Variable> variables = gitLabApi.getProjectApi().getVariables(project);
                if (variables != null) {

                    for (Variable variable : variables) {
                        if (variable.getKey().startsWith(TEST_VARIABLE_KEY_PREFIX)) {
                            gitLabApi.getProjectApi().deleteVariable(project, variable.getKey());
                        }
                    }
                }
            } catch (GitLabApiException ignore) {
            }
        }

        if (TEST_GROUP != null && TEST_GROUP_PROJECT != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_GROUP_PROJECT);
                gitLabApi.getProjectApi().deleteProject(project);
            } catch (GitLabApiException ignore) {}
        }

        if (TEST_XFER_NAMESPACE != null) {
            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_XFER_NAMESPACE, TEST_XFER_PROJECT_NAME);
                gitLabApi.getProjectApi().deleteProject(project);
            } catch (GitLabApiException ignore) {}
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testCreate() throws GitLabApiException {

        Project project = new Project()
                .withName(TEST_PROJECT_NAME_1)
                .withDescription("GitLab4J test project.")
                .withIssuesEnabled(true)
                .withMergeRequestsEnabled(true)
                .withWikiEnabled(true)
                .withSnippetsEnabled(true)
                .withVisibility(Visibility.PUBLIC)
                .withTagList(Arrays.asList("tag1", "tag2"));

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);
        assertEquals(TEST_PROJECT_NAME_1, newProject.getName());
        assertEquals(project.getDescription(), newProject.getDescription());
        assertEquals(project.getIssuesEnabled(), newProject.getIssuesEnabled());
        assertEquals(project.getMergeRequestsEnabled(), newProject.getMergeRequestsEnabled());
        assertEquals(project.getWikiEnabled(), newProject.getWikiEnabled());
        assertEquals(project.getSnippetsEnabled(), newProject.getSnippetsEnabled());
        assertEquals(project.getTagList(), newProject.getTagList());
        assertTrue(Visibility.PUBLIC == newProject.getVisibility() || Boolean.TRUE == newProject.getPublic());
    }

    @Test
    public void testUpdate() throws GitLabApiException {

        Project project = new Project()
                .withName(TEST_PROJECT_NAME_UPDATE)
                .withDescription("GitLab4J test project.")
                .withIssuesEnabled(true)
                .withMergeRequestsEnabled(true)
                .withWikiEnabled(true)
                .withSnippetsEnabled(true)
                .withVisibility(Visibility.PUBLIC)
                .withTagList(Arrays.asList("tag1", "tag2"));

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);
        assertEquals(project.getName(), newProject.getName());
        assertEquals(project.getDescription(), newProject.getDescription());
        assertEquals(project.getIssuesEnabled(), newProject.getIssuesEnabled());
        assertEquals(project.getMergeRequestsEnabled(), newProject.getMergeRequestsEnabled());
        assertEquals(project.getWikiEnabled(), newProject.getWikiEnabled());
        assertEquals(project.getSnippetsEnabled(), newProject.getSnippetsEnabled());
        assertEquals(project.getTagList(), newProject.getTagList());
        assertTrue(Visibility.PUBLIC == newProject.getVisibility() || Boolean.TRUE == newProject.getPublic());

        project = new Project()
            .withId(newProject.getId())
            .withName(newProject.getName())
            .withDescription("GitLab4J test updateProject()")
            .withIssuesEnabled(false)
            .withMergeRequestsEnabled(false)
            .withWikiEnabled(false)
            .withSnippetsEnabled(false)
            .withVisibility(Visibility.PRIVATE);

        Project updatedProject = gitLabApi.getProjectApi().updateProject(project);
        assertNotNull(updatedProject);
        assertEquals(project.getName(), newProject.getName());
        assertEquals(project.getDescription(), updatedProject.getDescription());
        assertEquals(project.getIssuesEnabled(), updatedProject.getIssuesEnabled());
        assertEquals(project.getMergeRequestsEnabled(), updatedProject.getMergeRequestsEnabled());
        assertEquals(project.getWikiEnabled(), updatedProject.getWikiEnabled());
        assertEquals(project.getSnippetsEnabled(), updatedProject.getSnippetsEnabled());
        assertTrue(Visibility.PRIVATE == updatedProject.getVisibility() || Boolean.FALSE == updatedProject.getPublic());
    }

    @Test
    public void testListProjects() throws GitLabApiException {

        List<Project> projects = gitLabApi.getProjectApi().getProjects();
        assertNotNull(projects);
        assertTrue(projects.size() >= 2);

        int matchCount = 0;
        for (Project project : projects) {
            if (TEST_PROJECT_NAME_1.equals(project.getName()))
                matchCount++;
            else if (TEST_PROJECT_NAME_2.equals(project.getName()))
                matchCount++;
        }

        assertEquals(2, matchCount);

        projects = gitLabApi.getProjectApi().getProjects(TEST_PROJECT_NAME_1);
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals(TEST_PROJECT_NAME_2, projects.get(0).getName());
        assertEquals(TEST_PROJECT_NAME_1, projects.get(1).getName());
    }

    @Test
    public void testListProjectsWithParams() throws GitLabApiException {

        List<Project> projects = gitLabApi.getProjectApi().getProjects(false, Visibility.PUBLIC,
                Constants.ProjectOrderBy.NAME, Constants.SortOrder.DESC, null, true, true, true, false, true);
        assertNotNull(projects);
        assertTrue(projects.size() >= 2);

        int matchCount = 0;
        for (Project project : projects) {
            if (TEST_PROJECT_NAME_1.equals(project.getName()))
                matchCount++;
            else if (TEST_PROJECT_NAME_2.equals(project.getName()))
                matchCount++;
        }

        assertEquals(2, matchCount);

        projects = gitLabApi.getProjectApi().getProjects(TEST_PROJECT_NAME_1);
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals(TEST_PROJECT_NAME_2, projects.get(0).getName());
        assertEquals(TEST_PROJECT_NAME_1, projects.get(1).getName());
    }


    @Test
    public void testListProjectsWithStatistics() throws GitLabApiException {

        List<Project> projects = gitLabApi.getProjectApi().getProjects(false, null,
                Constants.ProjectOrderBy.NAME, Constants.SortOrder.DESC, null, false, false, false, false, true);
        assertNotNull(projects);
        assertTrue(projects.size() >= 2);

        assertNotNull(projects.get(0).getStatistics());
        assertNotNull(projects.get(0).getStatistics().getLfsObjectSize());
        assertNotNull(projects.get(0).getStatistics().getCommitCount());
        assertNotNull(projects.get(0).getStatistics().getJobArtifactsSize());
        assertNotNull(projects.get(0).getStatistics().getStorageSize());

    }

    @Test
    public void testListProjectsWithParamsViaPager() throws GitLabApiException {

        Pager<Project> pager = gitLabApi.getProjectApi().getProjects(false, Visibility.PUBLIC,
                Constants.ProjectOrderBy.NAME, Constants.SortOrder.DESC, null, true, true, true, false, true, 10);
        assertNotNull(pager);
        assertTrue(pager.getTotalItems() >= 2);

        List<Project> projects = pager.next();
        int matchCount = 0;
        for (Project project : projects) {
            if (TEST_PROJECT_NAME_1.equals(project.getName()))
                matchCount++;
            else if (TEST_PROJECT_NAME_2.equals(project.getName()))
                matchCount++;
        }

        assertEquals(2, matchCount);

        projects = gitLabApi.getProjectApi().getProjects(TEST_PROJECT_NAME_1);
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals(TEST_PROJECT_NAME_2, projects.get(0).getName());
        assertEquals(TEST_PROJECT_NAME_1, projects.get(1).getName());
    }

    @Test
    public void testListProjectsWithParamByPage() throws GitLabApiException {

        List<Project> projects = gitLabApi.getProjectApi().getProjects(false, Visibility.PUBLIC,
                Constants.ProjectOrderBy.NAME, Constants.SortOrder.DESC, null, true, true, true, false, true, 1, 10);
        assertNotNull(projects);
        assertTrue(projects.size() >= 2);

        int matchCount = 0;
        for (Project project : projects) {
            if (TEST_PROJECT_NAME_1.equals(project.getName()))
                matchCount++;
            else if (TEST_PROJECT_NAME_2.equals(project.getName()))
                matchCount++;
        }

        assertEquals(2, matchCount);

        projects = gitLabApi.getProjectApi().getProjects(TEST_PROJECT_NAME_1);
        assertNotNull(projects);
        assertEquals(2, projects.size());
        assertEquals(TEST_PROJECT_NAME_2, projects.get(0).getName());
        assertEquals(TEST_PROJECT_NAME_1, projects.get(1).getName());
    }

    @Test
    public void testListStarredProjects() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        try {
            gitLabApi.getProjectApi().starProject(project);
        } catch (Exception ignore) {
        }

        List<Project> projects = gitLabApi.getProjectApi().getStarredProjects();

        try {
            gitLabApi.getProjectApi().unstarProject(project);
        } catch (Exception ignore) {
        }

        assertNotNull(projects);
        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals(TEST_PROJECT_NAME, projects.get(0).getName());
    }

    @Test
    public void testListStarredProjectsWithParams() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        try {
            gitLabApi.getProjectApi().starProject(project);
        } catch (Exception ignore) {
        }

        List<Project> projects = gitLabApi.getProjectApi().getProjects(false, Visibility.PUBLIC,
                Constants.ProjectOrderBy.NAME, Constants.SortOrder.DESC, TEST_PROJECT_NAME, true, true, true, true, true);

        try {
            gitLabApi.getProjectApi().unstarProject(project);
        } catch (Exception ignore) {
        }

        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals(TEST_PROJECT_NAME, projects.get(0).getName());
    }

    @Test
    public void testRemoveByDelete() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME_1);
        gitLabApi.getProjectApi().deleteProject(project);
    }

    @Test
    public void testCreateParameterBased() throws GitLabApiException {

        Project newProject = gitLabApi.getProjectApi().createProject(
                TEST_PROJECT_NAME_2, null, "GitLab4J test project.", true, true, true, true, Visibility.PUBLIC, null, null);
        assertNotNull(newProject);
        assertEquals(TEST_PROJECT_NAME_2, newProject.getName());
        assertEquals("GitLab4J test project.", newProject.getDescription());
        assertEquals(true, newProject.getIssuesEnabled());
        assertEquals(true, newProject.getMergeRequestsEnabled());
        assertEquals(true, newProject.getWikiEnabled());
        assertEquals(true, newProject.getSnippetsEnabled());
        assertTrue(Visibility.PUBLIC == newProject.getVisibility() || Boolean.TRUE == newProject.getPublic());
    }

    @Test
    public void testRemoveByDeleteParameterBased() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME_2);
        gitLabApi.getProjectApi().deleteProject(project);
    }

    @Test
    public void testProjects() throws GitLabApiException {
        List<Project> projects = gitLabApi.getProjectApi().getProjects();
        assertTrue(projects != null);
    }

    @Test
    public void testProjectPerPage() throws GitLabApiException {
        List<Project> projects = gitLabApi.getProjectApi().getProjects(1, 10);
        assertNotNull(projects);
        assertTrue(projects.size() > 0);
    }

    @Test
    public void testOwnedProjects() throws GitLabApiException {
        List<Project> projects = gitLabApi.getProjectApi().getOwnedProjects();
        assertTrue(projects != null);
    }

    @Test
    public void testOwnedProjectsPerPage() throws GitLabApiException {
        List<Project> projects = gitLabApi.getProjectApi().getOwnedProjects(1, 10);
        assertTrue(projects != null);
        assertTrue(projects.size() > 0);
    }

    @Test
    public void testMemberProjects() throws GitLabApiException {
        List<Project> projects = gitLabApi.getProjectApi().getMemberProjects();
        assertTrue(projects != null);
    }

    @Test
    public void testMemberProjectsPerPage() throws GitLabApiException {
        List<Project> projects = gitLabApi.getProjectApi().getMemberProjects(1, 10);
        assertTrue(projects != null);
        assertTrue(projects.size() > 0);
    }

    @Test
    public void testProjectLanguages() throws GitLabApiException {

        assumeTrue(TEST_GROUP != null && TEST_GROUP_PROJECT != null);
        assumeTrue(TEST_GROUP.trim().length() > 0 && TEST_GROUP_PROJECT.trim().length() > 0);

        Project project = gitLabApi.getProjectApi().getProject(TEST_GROUP, TEST_GROUP_PROJECT);
        assertNotNull(project);
        Map<String, Float> projectLanguages = gitLabApi.getProjectApi().getProjectLanguages(project.getId());
        assertNotNull(projectLanguages);
    }

    @Test
    public void testForkProject() throws GitLabApiException {

        assumeTrue(TEST_GROUP != null && TEST_GROUP_PROJECT != null);
        assumeTrue(TEST_GROUP.trim().length() > 0 && TEST_GROUP_PROJECT.trim().length() > 0);

        Project project = gitLabApi.getProjectApi().getProject(TEST_GROUP, TEST_GROUP_PROJECT);
        assertNotNull(project);
        Project forkedProject = gitLabApi.getProjectApi().forkProject(project.getId(), TEST_NAMESPACE);
        assertNotNull(forkedProject);
    }

    @Test
    public void testShareProject() throws GitLabApiException {

        assumeTrue(TEST_GROUP != null && TEST_GROUP_PROJECT != null);
        assumeTrue(TEST_GROUP.trim().length() > 0 && TEST_GROUP_PROJECT.trim().length() > 0);

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        List<Group> groups = gitLabApi.getGroupApi().getGroups(TEST_GROUP);
        assertNotNull(groups);

        Group shareGroup = groups.get(0);
        gitLabApi.getProjectApi().shareProject(project.getId(), shareGroup.getId(), AccessLevel.DEVELOPER, null);
        gitLabApi.getProjectApi().unshareProject(project.getId(), shareGroup.getId());
    }

    @Test
    public void testArchiveProject() throws GitLabApiException {
        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        assertEquals(true, gitLabApi.getProjectApi().archiveProject(project.getId()).getArchived());
        assertEquals(false, gitLabApi.getProjectApi().unarchiveProject(project.getId()).getArchived());
    }

    @Test
    public void testGetOptionalProject() throws GitLabApiException {

        Optional<Project> optional = gitLabApi.getProjectApi().getOptionalProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(optional);
        assertTrue(optional.isPresent());
        assertEquals(TEST_PROJECT_NAME, optional.get().getName());

        Integer projectId = optional.get().getId();
        optional = gitLabApi.getProjectApi().getOptionalProject(projectId);
        assertNotNull(optional);
        assertTrue(optional.isPresent());
        assertEquals(projectId, optional.get().getId());

        optional = gitLabApi.getProjectApi().getOptionalProject(TEST_NAMESPACE, "this-project-does-not-exist");
        assertNotNull(optional);
        assertFalse(optional.isPresent());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), GitLabApi.getOptionalException(optional).getHttpStatus());

        optional = gitLabApi.getProjectApi().getOptionalProject(1234567);
        assertNotNull(optional);
        assertFalse(optional.isPresent());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), GitLabApi.getOptionalException(optional).getHttpStatus());
    }

    @Test
    public void testStarAndUnstarProject() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        try {
            gitLabApi.getProjectApi().unstarProject(project);
        } catch (Exception ignore) {
        }

        Project starredProject = gitLabApi.getProjectApi().starProject(project);
        assertNotNull(starredProject);
        assertEquals(1, (int)starredProject.getStarCount());

        Project unstarredProject = gitLabApi.getProjectApi().unstarProject(project);
        assertNotNull(unstarredProject);
        assertEquals(0, (int)unstarredProject.getStarCount());
    }

    @Test
    public void testTransferProject() throws GitLabApiException {

        assumeTrue(TEST_XFER_NAMESPACE != null && TEST_XFER_NAMESPACE.trim().length() > 0);

        Project project = new Project()
                .withName(TEST_XFER_PROJECT_NAME)
                .withDescription("GitLab4J test project - transfer.")
                .withVisibility(Visibility.PUBLIC);

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);

        Project projectToTransfer = gitLabApi.getProjectApi().getProject(newProject);
        assertNotNull(projectToTransfer);

        Project transferedProject = gitLabApi.getProjectApi().transferProject(projectToTransfer, TEST_XFER_NAMESPACE);
        assertNotNull(transferedProject);
    }

    @Test
    public void testVariables() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        String key = TEST_VARIABLE_KEY_PREFIX + TestUtils.getRandomInt() + "_" +  TestUtils.getRandomInt();
        String value = "TEST_VARIABLE_VALUE_" + TestUtils.getRandomInt() + "_" +  TestUtils.getRandomInt();
        Variable variable = gitLabApi.getProjectApi().createVariable(project, key, value, null, null);

        assertNotNull(variable);
        assertEquals(key, variable.getKey());
        assertEquals(value, variable.getValue());

        Stream<Variable> variables = gitLabApi.getProjectApi().getVariablesStream(project);
        assertNotNull(variables);

        Variable matchingVariable = variables.filter(v -> v.getKey().equals(key)).findAny().orElse(null);
        assertNotNull(matchingVariable);
        assertEquals(key, matchingVariable.getKey());
        assertEquals(value, matchingVariable.getValue());
        assertFalse(matchingVariable.getProtected());
        assertNull(matchingVariable.getEnvironmentScope());

        gitLabApi.getProjectApi().updateVariable(project, key, "NONE", true, "DEV");
        variable = gitLabApi.getProjectApi().getVariable(project, key);

        assertNotNull(variable);
        assertEquals(key, variable.getKey());
        assertEquals("NONE", variable.getValue());
        assertTrue(variable.getProtected());

        gitLabApi.getProjectApi().deleteVariable(project, key);
        variables = gitLabApi.getProjectApi().getVariablesStream(project);
        assertNotNull(variables);

        matchingVariable = variables.filter(v -> v.getKey().equals(key)).findAny().orElse(null);
        assertNull(matchingVariable);
    }
}
