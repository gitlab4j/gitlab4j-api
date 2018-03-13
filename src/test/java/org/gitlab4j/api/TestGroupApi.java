package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * TEST_USERNAME
 * TEST_GROUP
 * TEST_GROUP_MEMBER_USERNAME
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
public class TestGroupApi {

    // The following needs to be set to your test repository
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    private static final String TEST_USERNAME;
    private static final String TEST_GROUP;
    private static final String TEST_GROUP_MEMBER_USERNAME;
    static {
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
        TEST_USERNAME = TestUtils.getProperty("TEST_USERNAME");
        TEST_GROUP = TestUtils.getProperty("TEST_GROUP");
        TEST_GROUP_MEMBER_USERNAME = TestUtils.getProperty("TEST_GROUP_MEMBER_USERNAME");
    }

    private static GitLabApi gitLabApi;
    private static Group testGroup;
    private static User testUser;

    public TestGroupApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (TEST_USERNAME == null || TEST_USERNAME.trim().isEmpty()) {
            problems += "TEST_USER_NAME cannot be empty\n";
        }

        if (problems.isEmpty()) {

            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
            try {
                List<Group> groups = gitLabApi.getGroupApi().getGroups(TEST_GROUP);
                testGroup = groups.get(0);
            } catch (GitLabApiException gle) {
                problems += "Problem fetching test group, error=" + gle.getMessage() + "\n";
            }

            if (TEST_GROUP_MEMBER_USERNAME != null && TEST_GROUP_MEMBER_USERNAME.length() > 0) {
                try {
                    testUser = gitLabApi.getUserApi().getUser(TEST_GROUP_MEMBER_USERNAME);
                } catch (GitLabApiException gle) {
                    problems += "Problem fetching test user, error=" + gle.getMessage() + "\n";
                }
            }
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }

        removeGroupMember();
    }

    @AfterClass
    public static void teardown() {
        removeGroupMember();
    }

    private static void removeGroupMember() {

        if (gitLabApi != null) {

            if (testGroup != null && testUser != null) {
                try {
                    gitLabApi.getGroupApi().removeMember(testGroup.getId(), testUser.getId());
                } catch (GitLabApiException ignore) {
                }
            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
        assumeTrue(testGroup != null && testUser != null);
    }

    @Test
    public void testMemberOperations() throws GitLabApiException {

        Member member = gitLabApi.getGroupApi().addMember(testGroup.getId(), testUser.getId(), AccessLevel.DEVELOPER);
        assertNotNull(member);
        assertEquals(testUser.getId(), member.getId());

        gitLabApi.getGroupApi().removeMember(testGroup.getId(), testUser.getId());
    }

    @Test
    public void getGroup() throws GitLabApiException {
        Group group = gitLabApi.getGroupApi().getGroup(TEST_GROUP);
        assertNotNull(group);
    }

    @Test
    public void getOptionalGroup() {
        Optional<Group> optional = gitLabApi.getGroupApi().getOptionalGroup(TEST_GROUP);
        assertTrue(optional.isPresent());
        assertEquals(testGroup.getId(), optional.get().getId());

        optional = gitLabApi.getGroupApi().getOptionalGroup(12345);
        assertNotNull(optional);
        assertFalse(optional.isPresent());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), GitLabApi.getOptionalException(optional).getHttpStatus());
    }
}
