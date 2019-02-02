package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that the tests are in the correct order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPager {

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

    public TestPager() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().isEmpty()) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
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
