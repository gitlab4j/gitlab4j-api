package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectApi {

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

    private static final String TEST_PROJECT_NAME_1 = "test-gitlab4j-create-project";
    private static final String TEST_PROJECT_NAME_2 = "test-gitlab4j-create-project-2";
    private static final String TEST_PROJECT_NAME_UPDATE = "test-gitlab4j-create-project-update";
    private static GitLabApi gitLabApi;

    public TestProjectApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().length() == 0) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().length() == 0) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().length() == 0) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }

        deleteAllTestProjects();
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        deleteAllTestProjects();
    }

    private static void deleteAllTestProjects() {
        if (gitLabApi != null) {
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
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
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
                .withVisibility(Visibility.PUBLIC);

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);
        assertEquals(TEST_PROJECT_NAME_1, newProject.getName());
        assertEquals(project.getDescription(), newProject.getDescription());
        assertEquals(project.getIssuesEnabled(), newProject.getIssuesEnabled());
        assertEquals(project.getMergeRequestsEnabled(), newProject.getMergeRequestsEnabled());
        assertEquals(project.getWikiEnabled(), newProject.getWikiEnabled());
        assertEquals(project.getSnippetsEnabled(), newProject.getSnippetsEnabled());
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
                .withVisibility(Visibility.PUBLIC);

        Project newProject = gitLabApi.getProjectApi().createProject(project);
        assertNotNull(newProject);
        assertEquals(project.getName(), newProject.getName());
        assertEquals(project.getDescription(), newProject.getDescription());
        assertEquals(project.getIssuesEnabled(), newProject.getIssuesEnabled());
        assertEquals(project.getMergeRequestsEnabled(), newProject.getMergeRequestsEnabled());
        assertEquals(project.getWikiEnabled(), newProject.getWikiEnabled());
        assertEquals(project.getSnippetsEnabled(), newProject.getSnippetsEnabled());
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

        List<Project> projects = gitLabApi.getProjectApi().getStarredProjects();
        assertNotNull(projects);
        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals(TEST_PROJECT_NAME, projects.get(0).getName());
    }

    @Test
    public void testListStarredProjectsWithParams() throws GitLabApiException {

        List<Project> projects = gitLabApi.getProjectApi().getProjects(false, Visibility.PUBLIC,
                Constants.ProjectOrderBy.NAME, Constants.SortOrder.DESC, TEST_PROJECT_NAME, true, true, true, true, true);
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
        assertEquals(10, projects.size());
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
}
