package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestPager extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    public TestPager() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testProjectPager() throws GitLabApiException {

        Pager<Project> pager = gitLabApi.getProjectApi().getProjects(10);
        assertNotNull(pager);
        assertEquals(10, pager.getItemsPerPage());
        assertTrue(0 < pager.getTotalPages());
        assertTrue(0 < pager.getTotalItems());

        int itemNumber = 0;
        int pageIndex = 0;
        while (pager.hasNext() && pageIndex < 4) {

            List<Project> projects = pager.next();

            pageIndex++;
            assertEquals(pageIndex, pager.getCurrentPage());

            if (pageIndex < pager.getTotalPages())
                assertEquals(10, projects.size());

            for (Project project : projects) {
                itemNumber++;
                System.out.format("page=%d, item=%d, projectId=%d, projectName=%s%n", pageIndex, itemNumber, project.getId(), project.getName());
            }
        }
    }

    @Test
    public void testMemberProjectPager() throws GitLabApiException {

        Pager<Project> pager = gitLabApi.getProjectApi().getMemberProjects(2);
        assertNotNull(pager);
        assertEquals(2, pager.getItemsPerPage());
        assertTrue(0 < pager.getTotalPages());
        assertTrue(0 < pager.getTotalItems());

        int itemNumber = 0;
        int pageIndex = 0;
        while (pager.hasNext() && pageIndex < 10) {

            List<Project> projects = pager.next();

            pageIndex++;
            assertEquals(pageIndex, pager.getCurrentPage());

            if (pageIndex < pager.getTotalPages())
                assertEquals(2, projects.size());

            for (Project project : projects) {
                itemNumber++;
                System.out.format("page=%d, item=%d, projectId=%d, projectName=%s%n", pageIndex, itemNumber, project.getId(), project.getName());
            }
        }
    }

    @Test
    public void testBranchesPager() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Pager<Branch> pager = gitLabApi.getRepositoryApi().getBranches(project.getId(), 2);
        assertNotNull(pager);
        assertEquals(2, pager.getItemsPerPage());
        assertTrue(0 < pager.getTotalPages());
        assertTrue(0 < pager.getTotalItems());

        int itemNumber = 0;
        int pageIndex = 0;
        while (pager.hasNext() && pageIndex < 10) {

            List<Branch> branches = pager.next();

            pageIndex++;
            assertEquals(pageIndex, pager.getCurrentPage());

            if (pageIndex < pager.getTotalPages())
                assertEquals(2, branches.size());

            for (Branch branch : branches) {
                itemNumber++;
                System.out.format("page=%d, item=%d, branchName=%s, isMerged=%b%n", pageIndex, itemNumber, branch.getName(), branch.getMerged());
            }
        }
    }

    @Test
    public void testMembersPager() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Pager<Member> pager = gitLabApi.getProjectApi().getMembers(project.getId(), 2);
        assertNotNull(pager);
        assertEquals(2, pager.getItemsPerPage());
        assertTrue(0 < pager.getTotalPages());
        assertTrue(0 < pager.getTotalItems());

        int itemNumber = 0;
        int pageIndex = 0;
        while (pager.hasNext() && pageIndex < 10) {

            List<Member> members = pager.next();

            pageIndex++;
            assertEquals(pageIndex, pager.getCurrentPage());

            if (pageIndex < pager.getTotalPages())
                assertEquals(2, members.size());

            for (Member member : members) {
                itemNumber++;
                System.out.format("page=%d, item=%d, name=%s, username=%s%n", pageIndex, itemNumber, member.getName(), member.getUsername());
            }
        }
    }

    @Test
    public void testAll() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Pager<Commit> pager = gitLabApi.getCommitsApi().getCommits(project, 1);
        assertNotNull(pager);
        assertEquals(1, pager.getItemsPerPage());
        assertTrue(0 < pager.getTotalPages());

        int numCommits = pager.getTotalItems();
        assertTrue(0 < numCommits);

        List<Commit> allCommits = pager.all();
        System.out.println("All commits:");
        allCommits.stream().map(Commit::getId).forEach(System.out::println);

        assertEquals(numCommits, allCommits.size());
    }

    @Test
    public void testStream() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Pager<Commit> pager = gitLabApi.getCommitsApi().getCommits(project, 1);
        assertNotNull(pager);
        assertEquals(1, pager.getItemsPerPage());
        assertTrue(0 < pager.getTotalPages());

        int numCommits = pager.getTotalItems();
        assertTrue(0 < numCommits);

        System.out.println("Streamed commits:");
       assertEquals(numCommits, pager.stream().map(Commit::getId).peek(System.out::println).count());
    }
}
