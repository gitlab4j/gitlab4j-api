package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.gitlab4j.api.Constants.GroupSearchScope;
import org.gitlab4j.api.Constants.ProjectSearchScope;
import org.gitlab4j.api.Constants.SearchScope;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Milestone;
import org.gitlab4j.api.models.Note;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.SearchBlob;
import org.gitlab4j.api.models.Snippet;
import org.gitlab4j.api.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestSearchApi extends AbstractIntegrationTest {

    private static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static Group testGroup;

    public TestSearchApi() {
        super();
    }

    @BeforeAll
    public static void testSetup() {

        // Must setup the connection to the GitLab test server and get the test Project
        // instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        if (gitLabApi != null) {
            try {
                List<Group> groups = gitLabApi.getGroupApi().getGroups(TEST_GROUP);
                testGroup = groups.get(0);
            } catch (GitLabApiException gle) {
                System.err.println("Problem fetching test group, error=" + gle.getMessage());
            }
        }
    }

    @Test
    public void testGlobalProjectSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.PROJECTS, TEST_PROJECT_NAME);
        assertNotNull(results);
        assertTrue(results.get(0).getClass() == Project.class);
    }

    @Test
    public void testGlobalIssuesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.ISSUES, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Issue.class);
        }
    }

    @Test
    public void testGlobalMergeRequestsSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.MERGE_REQUESTS, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == MergeRequest.class);
        }
    }

    @Test
    public void testGlobalMilestonesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.MILESTONES, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Milestone.class);
        }
    }

    @Test
    public void testGlobalSnippetTitlesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.SNIPPET_TITLES, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Snippet.class);
        }
    }

    @Disabled
    public void testGlobalSnippetBlobsSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.SNIPPET_BLOBS, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Snippet.class);
        }
    }

    @Test
    public void testGlobalUsersSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().globalSearch(SearchScope.USERS, TEST_LOGIN_USERNAME);
        assertNotNull(results);
        assertTrue(results.get(0).getClass() == User.class);
    }

    @Test
    public void testGroupProjectSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().groupSearch(testGroup, GroupSearchScope.PROJECTS, TEST_GROUP_PROJECT_NAME);
        assertNotNull(results);
        assertTrue(results.get(0).getClass() == Project.class);
    }

    @Test
    public void testGroupIssuesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().groupSearch(testGroup, GroupSearchScope.ISSUES, TEST_GROUP_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Issue.class);
        }
    }

    @Test
    public void testGrouplMergeRequestsSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().groupSearch(testGroup, GroupSearchScope.MERGE_REQUESTS, TEST_GROUP_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == MergeRequest.class);
        }
    }

    @Test
    public void testGroupMilestonesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().groupSearch(testGroup, GroupSearchScope.MILESTONES, TEST_GROUP_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Milestone.class);
        }
    }

    @Test
    public void testGrouplUsersSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().groupSearch(testGroup, GroupSearchScope.USERS, TEST_LOGIN_USERNAME);
        assertNotNull(results);
        assertTrue(results.get(0).getClass() == User.class);
    }

    @Test
    public void testProjectIssuesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().projectSearch(
                testProject, ProjectSearchScope.ISSUES, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Issue.class);
        }
    }

    @Test
    public void testProjectlMergeRequestsSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().projectSearch(
                testProject, ProjectSearchScope.MERGE_REQUESTS, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == MergeRequest.class);
        }
    }

    @Test
    public void testProjectMilestonesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().projectSearch(
                testProject, ProjectSearchScope.MILESTONES, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Milestone.class);
        }
    }

    @Test
    public void testProjectNotesSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().projectSearch(
                testProject, ProjectSearchScope.NOTES, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == Note.class);
        }
    }

    @Test
    public void testProjectWikiBlobsSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().projectSearch(
                testProject, ProjectSearchScope.WIKI_BLOBS, TEST_PROJECT_NAME);
        assertNotNull(results);
        if (results.size() > 0) {
            assertTrue(results.get(0).getClass() == SearchBlob.class);
        }
    }

    @Test
    public void testProjectlUsersSearch() throws GitLabApiException {
        List<?> results = gitLabApi.getSearchApi().projectSearch(
                testProject, ProjectSearchScope.USERS, TEST_LOGIN_USERNAME);
        assertNotNull(results);
        assertTrue(results.get(0).getClass() == User.class);
    }
}
