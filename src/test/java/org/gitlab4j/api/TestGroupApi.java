package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.AccessRequest;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.GroupParams;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
@Category(IntegrationTest.class)
public class TestGroupApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);
    private static final String TEST_GROUP_MEMBER_USERNAME = HelperUtils.getProperty(GROUP_MEMBER_USERNAME_KEY);
    private static final String TEST_REQUEST_ACCESS_USERNAME = HelperUtils.getProperty(TEST_REQUEST_ACCESS_USERNAME_KEY);

    private static GitLabApi gitLabApi;
    private static Group testGroup;
    private static User testUser;

    public TestGroupApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        String problems = "";
        if (gitLabApi != null) {
            Optional<Group> group = gitLabApi.getGroupApi().getOptionalGroup(TEST_GROUP);
            if (group.isPresent()) {
        	testGroup = group.get();
            } else {
                problems += "Problem fetching test group\n";
            }

            if (TEST_GROUP_MEMBER_USERNAME != null && TEST_GROUP_MEMBER_USERNAME.length() > 0) {
                try {
                    testUser = gitLabApi.getUserApi().getUser(TEST_GROUP_MEMBER_USERNAME);
                } catch (GitLabApiException gle) {
                    problems += "Problem fetching test user, error=" + gle.getMessage() + "\n";
                }
            }
        }

        if (!problems.isEmpty()) {
            System.err.print(problems);
        }

        removeGroupMembers();
    }

    @AfterClass
    public static void teardown() {
        removeGroupMembers();
    }

    private static void removeGroupMembers() {

        if (gitLabApi != null && testGroup != null && testUser != null) {
            try {
                gitLabApi.getGroupApi().removeMember(testGroup.getId(), testUser.getId());
            } catch (GitLabApiException ignore) {
            }
        }

        if (TEST_REQUEST_ACCESS_USERNAME != null) {
            Optional<User> user = gitLabApi.getUserApi().getOptionalUser(TEST_REQUEST_ACCESS_USERNAME);
            if (user.isPresent()) {
                Integer userId = user.get().getId();
                try {
                    gitLabApi.getGroupApi().denyAccessRequest(testGroup, userId);
                } catch (Exception e) {
                    try {
                        gitLabApi.getGroupApi().removeMember(testGroup, userId);
                    } catch (Exception ignore) {}
                }
            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
        assumeNotNull(testGroup);
        assumeNotNull(testUser);
    }

    @Test
    public void testMemberOperations() throws GitLabApiException {

        // Arrange and Act
        Member member = gitLabApi.getGroupApi().addMember(testGroup.getId(), testUser.getId(), AccessLevel.DEVELOPER);

        // Assert
        assertNotNull(member);
        assertEquals(testUser.getId(), member.getId());
        assertEquals(AccessLevel.DEVELOPER, member.getAccessLevel());

        // Act
        Optional<Member> optionalMember = gitLabApi.getGroupApi().getOptionalMember(testGroup, testUser.getId());

        // Assert
        assertTrue(optionalMember.isPresent());

        // Act
        List<Member> members = gitLabApi.getGroupApi().getMembers(testGroup);

        // Assert
        assertNotNull(members);
        Boolean found = (members.stream().filter(m -> m.getId().equals(member.getId())).findAny().orElse(null) != null);
        assertTrue(found);

        // Act
        gitLabApi.getGroupApi().removeMember(testGroup.getId(), testUser.getId());

        // Act
        optionalMember = gitLabApi.getGroupApi().getOptionalMember(testGroup, testUser.getId());

        // Assert
        assertFalse(optionalMember.isPresent());
    }

    @Test
    public void testAllMemberOperations() throws GitLabApiException {

        // Arrange and Act
        Member member = gitLabApi.getGroupApi().addMember(testGroup.getId(), testUser.getId(), AccessLevel.DEVELOPER);

        // Assert
        assertNotNull(member);
        assertEquals(testUser.getId(), member.getId());
        assertEquals(AccessLevel.DEVELOPER, member.getAccessLevel());

        // Act
        List<Member> members = gitLabApi.getGroupApi().getAllMembers(testGroup);

        // Assert
        assertNotNull(members);
        Boolean found = (members.stream().filter(m -> m.getId().equals(member.getId())).findAny().orElse(null) != null);
        assertTrue(found);

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


    @Test
    public void testRequestAccess() throws GitLabApiException {

        assumeTrue(TEST_REQUEST_ACCESS_USERNAME != null && TEST_REQUEST_ACCESS_USERNAME.length() > 0);

        gitLabApi.sudo(TEST_REQUEST_ACCESS_USERNAME);
        User user = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(user);
        final Integer userId = user.getId();

        try {
            try {

                AccessRequest accessRequest = gitLabApi.getGroupApi().requestAccess(testGroup);
                assertNotNull(accessRequest);
                assertEquals(userId, accessRequest.getId());

            } finally {
                gitLabApi.unsudo();
            }

            Stream<AccessRequest> requests = gitLabApi.getGroupApi().getAccessRequestsStream(testGroup);
            assertTrue(requests.anyMatch(r -> r.getId() == userId));

            AccessRequest accessRequest = gitLabApi.getGroupApi().approveAccessRequest(testGroup, user.getId(), AccessLevel.DEVELOPER);
            assertNotNull(accessRequest);
            assertEquals(user.getId(), accessRequest.getId());
            assertEquals(AccessLevel.DEVELOPER, accessRequest.getAccessLevel());

            user = null;

            requests = gitLabApi.getGroupApi().getAccessRequestsStream(testGroup);
            assertFalse(requests.anyMatch(r -> r.getId() == userId));

        } finally {
            try {
                if (user == null) {
                    gitLabApi.getGroupApi().removeMember(testGroup, userId);
                } else {
                    gitLabApi.getGroupApi().denyAccessRequest(testGroup, userId);
                }
            } catch (Exception ignore) {}
        }
    }

    @Test
    public void testDenyRequestAccess() throws GitLabApiException {

        assumeTrue(TEST_REQUEST_ACCESS_USERNAME != null && TEST_REQUEST_ACCESS_USERNAME.length() > 0);

        gitLabApi.sudo(TEST_REQUEST_ACCESS_USERNAME);
        User user = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(user);
        final Integer userId = user.getId();

        try {
            try {

                AccessRequest accessRequest = gitLabApi.getGroupApi().requestAccess(testGroup);
                assertNotNull(accessRequest);
                assertEquals(userId, accessRequest.getId());

            } finally {
                gitLabApi.unsudo();
            }

            List<AccessRequest> requests = gitLabApi.getGroupApi().getAccessRequests(testGroup);
            assertTrue(requests.stream().anyMatch(r -> r.getId() == userId));

            gitLabApi.getGroupApi().denyAccessRequest(testGroup, userId);

            requests = gitLabApi.getGroupApi().getAccessRequests(testGroup);
            assertFalse(requests.stream().anyMatch(r -> r.getId() == userId));

            user = null;

        } finally {
            try {
                if (user != null) {
                    gitLabApi.getGroupApi().denyAccessRequest(testGroup, userId);
                }
            } catch (Exception ignore) {
            }
        }
    }

    @Test
    public void updateGroup() throws GitLabApiException {

        String description = "Test Group (" + HelperUtils.getRandomInt(1000) + ")";
        GroupParams params = new GroupParams().withDescription(description);

        Group updatedGroup = gitLabApi.getGroupApi().updateGroup(testGroup, params);
        assertEquals(description, updatedGroup.getDescription());

        Optional<Group> optional = gitLabApi.getGroupApi().getOptionalGroup(TEST_GROUP);
        assertTrue(optional.isPresent());
        assertEquals(testGroup.getId(), optional.get().getId());
        assertEquals(description, optional.get().getDescription());
    }
}
