package org.gitlab4j.api;

import org.gitlab4j.api.Constants.SortOrder;
import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Note;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestNotesApi {

    // The following needs to be set to your test repository
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApi;

    public TestNotesApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().length() == 0) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().length() == 0) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
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
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
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
