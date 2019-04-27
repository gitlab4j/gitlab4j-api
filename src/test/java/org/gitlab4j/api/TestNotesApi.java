package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.gitlab4j.api.Constants.SortOrder;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Note;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestNotesApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    public TestNotesApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testIssueNotes() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        for (Issue issue : gitLabApi.getIssuesApi().getIssues(project.getId())) {
            List<Note> notes = gitLabApi.getNotesApi().getIssueNotes(project.getId(), issue.getIid());
            assertNotNull(notes);
        }
    }

    @Test
    public void testIssueNotesPager() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        for (Issue issue : gitLabApi.getIssuesApi().getIssues(project.getId())) {
            Pager<Note> pager = gitLabApi.getNotesApi().getIssueNotes(project.getId(), issue.getIid(), 10);
            assertNotNull(pager);
        }
    }

    @Test
    public void testMergeRequestNotes() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        for (MergeRequest mr : gitLabApi.getMergeRequestApi().getMergeRequests(project.getId())) {
            List<Note> notes = gitLabApi.getNotesApi().getMergeRequestNotes(project.getId(), mr.getIid());
            assertNotNull(notes);
        }
    }

    @Test
    public void testMergeRequestNotesPager() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        for (MergeRequest mr : gitLabApi.getMergeRequestApi().getMergeRequests(project.getId())) {
            Pager<Note> pager = gitLabApi.getNotesApi().getMergeRequestNotes(project.getId(), mr.getIid(), SortOrder.DESC, Note.OrderBy.CREATED_AT, 10);
            assertNotNull(pager);
        }
    }
}
