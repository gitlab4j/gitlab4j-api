package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Random;

import org.gitlab4j.api.models.Issue;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Todo;
import org.gitlab4j.api.models.User;
import org.gitlab4j.models.Constants.TodoState;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@org.junit.jupiter.api.Disabled(
        "Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
public class TestTodosApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    private static final String ISSUE_TITLE = "Test Issue Title for Todo";
    private static final String ISSUE_DESCRIPTION = "This is a really nice todo description, not.";
    private static Random randomNumberGenerator = new Random();

    public TestTodosApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        deleteAllTestIssues();
    }

    @AfterAll
    public static void teardown() throws GitLabApiException {
        deleteAllTestIssues();
    }

    private static void deleteAllTestIssues() {

        if (gitLabApi != null) {
            try {
                List<Issue> issues = gitLabApi.getIssuesApi().getIssues(testProject.getId());
                if (issues != null) {

                    for (Issue issue : issues) {
                        if (issue.getTitle().startsWith(ISSUE_TITLE)) {
                            gitLabApi.getIssuesApi().deleteIssue(testProject.getId(), issue.getIid());
                        }
                    }
                }
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    private static String getUniqueTitle() {
        return (ISSUE_TITLE + " - " + (randomNumberGenerator.nextInt() + 1));
    }

    @Test
    public void testGetTodos() throws GitLabApiException {

        Issue issue = gitLabApi.getIssuesApi().createIssue(testProject, getUniqueTitle(), ISSUE_DESCRIPTION);
        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        gitLabApi.getIssuesApi().assignIssue(testProject, issue.getIid(), currentUser.getId());

        List<Todo> todos = gitLabApi.getTodosApi().getPendingTodos();
        assertTrue(todos.size() > 0);
        boolean found = false;
        for (Todo todo : todos) {
            if (todo.isIssueTodo() && ((Issue) todo.getTarget()).getIid().intValue() == issue.getIid()) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void testMarkAllAsDone() throws GitLabApiException {

        Issue issue = gitLabApi.getIssuesApi().createIssue(testProject, getUniqueTitle(), ISSUE_DESCRIPTION);
        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        gitLabApi.getIssuesApi().assignIssue(testProject, issue.getIid(), currentUser.getId());

        List<Todo> todos = gitLabApi.getTodosApi().getPendingTodos();
        assertTrue(todos.size() > 0);

        gitLabApi.getTodosApi().markAllAsDone();
        todos = gitLabApi.getTodosApi().getPendingTodos();
        assertNotNull(todos);
        assertEquals(0, todos.size());
    }

    @Test
    public void testMarkAsDone() throws GitLabApiException {

        Issue issue = gitLabApi.getIssuesApi().createIssue(testProject, getUniqueTitle(), ISSUE_DESCRIPTION);
        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        gitLabApi.getIssuesApi().assignIssue(testProject, issue.getIid(), currentUser.getId());

        List<Todo> todos = gitLabApi.getTodosApi().getPendingTodos();
        assertTrue(todos.size() > 0);

        Long foundId = null;
        for (Todo todo : todos) {
            if (todo.isIssueTodo() && ((Issue) todo.getTarget()).getIid().intValue() == issue.getIid()) {
                foundId = todo.getId();
                break;
            }
        }

        assertNotNull(foundId);

        gitLabApi.getTodosApi().markAsDone(foundId);
        todos = gitLabApi.getTodosApi().getDoneTodos();
        assertTrue(todos.size() > 0);
        foundId = null;
        for (Todo todo : todos) {
            if (todo.getState() == TodoState.DONE
                    && todo.isIssueTodo()
                    && ((Issue) todo.getTarget()).getIid().intValue() == issue.getIid()) {
                foundId = todo.getId();
                break;
            }
        }

        assertNotNull(foundId);
    }
}
