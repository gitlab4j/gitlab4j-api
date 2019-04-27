package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.ImpersonationToken;
import org.gitlab4j.api.models.ImpersonationToken.Scope;
import org.gitlab4j.api.models.SshKey;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.models.Version;
import org.gitlab4j.api.utils.ISO8601;
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
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 * TEST_SUDO_AS_USERNAME
 *
 * If this is null the sudo() tests will be skipped.
 *
 * TEST_SSH_KEY
 *
 * If this is null the SSH key tests will be skipped.
 *
 */
@Category(IntegrationTest.class)
public class TestUserApi extends AbstractIntegrationTest  {

    // The following needs to be set to your test repository
    private static final String TEST_USERNAME;
    private static final String TEST_BLOCK_USERNAME;
    private static final String TEST_SUDO_AS_USERNAME;
    private static final String TEST_SSH_KEY;
    static {
        TEST_USERNAME = TestUtils.getProperty("TEST_USERNAME");
        TEST_BLOCK_USERNAME = TestUtils.getProperty("TEST_BLOCK_USERNAME");
        TEST_SUDO_AS_USERNAME = TestUtils.getProperty("TEST_SUDO_AS_USERNAME");
        TEST_SSH_KEY = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCvbkmGRaANy2nmLrfYa9LkjMqjs9twYZXQKUPK18j" +
                "BWmNgnAm818IikxjfFit3Gqnnh9zdNzlzUYs2osmfdHwRLeFY3hKVR6WckGYVroQuV5ArUA4+oME+IIQ2soCv/" +
                "vNWfEmp2N1mpBTwi2mIYKurCKv6UpIpGK9D+ezNk5H0waVTK8EvZ/ey69Nu7C7RsbTYeyi5WY/jaUG5JbsEeKY" +
                "IW/2DIlUts7gcB2hzXtt7r7+6DLx82Vb+S2jPZu2JQaB4zfgS7LQgzHUy1aAAgUUpuAbvWzuGHKO0p551Ru4qi" +
                "tyXN2+OUVXcYAsuIIdGGB0wLvTDgiOOSZWnSE+sg6XX user@example.com";
    }

    private static final String TEST_IMPERSONATION_TOKEN_NAME = "token1";

    private static GitLabApi gitLabApi;
    private static User blockUser;

    public TestUserApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (TEST_USERNAME == null || TEST_USERNAME.trim().isEmpty()) {
            problems += "TEST_USER_NAME cannot be empty\n";
        }

        if (problems.isEmpty()) {

            // Must setup the connection to the GitLab test server
            gitLabApi = baseTestSetup();

            if (gitLabApi != null) {

                if (TEST_BLOCK_USERNAME != null) {
                    try {
                        blockUser = gitLabApi.getUserApi().getUser(TEST_BLOCK_USERNAME);
                        if (blockUser != null) {
                            gitLabApi.getUserApi().unblockUser(blockUser.getId());
                        }
                    } catch (Exception ignore) {}
                }

                if (TEST_SSH_KEY != null) {
                    try {
                        List<SshKey> sshKeys = gitLabApi.getUserApi().getSshKeys();
                        if (sshKeys != null) {
                            for (SshKey key : sshKeys) {
                                if (TEST_SSH_KEY.equals(key.getKey())) {
                                    gitLabApi.getUserApi().deleteSshKey(key.getId());
                                }
                            }
                        }
                    } catch (Exception ignore) {}
                }
            }

        } else {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testGetVersion() throws GitLabApiException {
        Version version = gitLabApi.getVersion();
        assertNotNull(version);
        System.out.format("version=%s, revision=%s%n", version.getVersion(), version.getRevision());
        assertNotNull(version.getVersion());
        assertNotNull(version.getRevision());
    }
    
    @Test
    public void testGetCurrentUser() throws GitLabApiException {
        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(currentUser);
        assertEquals(TEST_USERNAME, currentUser.getUsername());
    }

    @Test
    public void testLookupUser() throws GitLabApiException {
        User user = gitLabApi.getUserApi().getUser(TEST_USERNAME);
        assertNotNull(user);
        assertEquals(TEST_USERNAME, user.getUsername());
    }

    @Test
    public void testBlockUnblockUser() throws GitLabApiException {
        assumeNotNull(blockUser);

        assertNotEquals("blocked", blockUser.getState());
        gitLabApi.getUserApi().blockUser(blockUser.getId());
        User user = gitLabApi.getUserApi().getUser(blockUser.getId());
        assertEquals("blocked", user.getState());

        gitLabApi.getUserApi().unblockUser(blockUser.getId());
        user = gitLabApi.getUserApi().getUser(blockUser.getId());
        assertNotEquals("blocked", user.getState());
    }

    @Test
    public void testGetOptionalUser() throws GitLabApiException {

        Optional<User> optional = gitLabApi.getUserApi().getOptionalUser(TEST_USERNAME);
        assertNotNull(optional);
        assertTrue(optional.isPresent());
        assertEquals(TEST_USERNAME, optional.get().getUsername());

        optional = gitLabApi.getUserApi().getOptionalUser("this-username-does-not-exist");
        assertNotNull(optional);
        assertFalse(optional.isPresent());
    }

    @Test
    public void testSudoAsUser() throws GitLabApiException {

        assumeTrue(TEST_SUDO_AS_USERNAME != null && TEST_SUDO_AS_USERNAME.length() > 0);

        try {

            gitLabApi.sudo(TEST_SUDO_AS_USERNAME);
            User user = gitLabApi.getUserApi().getCurrentUser();
            assertNotNull(user);
            assertEquals(TEST_SUDO_AS_USERNAME, user.getUsername());
            Integer sudoAsId = user.getId();

            gitLabApi.sudo(null);
            user = gitLabApi.getUserApi().getCurrentUser();
            assertNotNull(user);
            assertEquals(TEST_USERNAME, user.getUsername());

            gitLabApi.unsudo();
            assertNull(gitLabApi.getSudoAsId());

            gitLabApi.setSudoAsId(sudoAsId);
            user = gitLabApi.getUserApi().getCurrentUser();
            assertNotNull(user);
            assertEquals(sudoAsId, user.getId());
            assertEquals(TEST_SUDO_AS_USERNAME, user.getUsername());

        } finally {
            gitLabApi.unsudo();
        }
    }

    @Test
    public void testCreateImpersonationToken() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");
        ImpersonationToken token = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);
        assertNotNull(token);
        assertNotNull(token.getId());
        assertEquals(TEST_IMPERSONATION_TOKEN_NAME, token.getName());
        assertEquals(2, token.getScopes().size());

        gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), token.getId());
    }

    @Test
    public void testGetOptionalImpersonationToken() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");
        ImpersonationToken token = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);
        assertNotNull(token);

        Optional<ImpersonationToken> optional = gitLabApi.getUserApi().getOptionalImpersonationToken(user.getId(), token.getId());
        assertTrue(optional.isPresent());
        assertEquals(token.getId(), optional.get().getId());
        gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), token.getId());

        optional = gitLabApi.getUserApi().getOptionalImpersonationToken(user.getId(), 123456);
        assertNotNull(optional);
        assertFalse(optional.isPresent());
    }

    @Test
    public void testGetImpersonationTokens() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");
        ImpersonationToken createdToken = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);
        assertNotNull(createdToken);

        ImpersonationToken token =  gitLabApi.getUserApi().getImpersonationToken(user.getId(), createdToken.getId());
        assertNotNull(token);
        assertEquals(createdToken.getId(), token.getId());
        assertEquals(TEST_IMPERSONATION_TOKEN_NAME, token.getName());
        assertEquals(createdToken.getExpiresAt(), token.getExpiresAt());

        List<ImpersonationToken> tokens = gitLabApi.getUserApi().getImpersonationTokens(user.getId());
        assertNotNull(tokens);
        assertTrue(tokens.size() > 0);

        gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), createdToken.getId());
    }

    @Test
    public void testDeleteImpersonationTokens() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");
        ImpersonationToken createdToken = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME + "a", expiresAt, scopes);
        assertNotNull(createdToken);

        ImpersonationToken token =  gitLabApi.getUserApi().getImpersonationToken(user.getId(), createdToken.getId());
        assertNotNull(token);
        assertEquals(createdToken.getId(), token.getId());

        gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), createdToken.getId());
        token =  gitLabApi.getUserApi().getImpersonationToken(user.getId(), createdToken.getId());
        assertFalse(token.getActive());
    }

    @Test
    public void testGetSshKeys() throws GitLabApiException {

        assumeTrue(TEST_SSH_KEY != null);
        SshKey sshKey = gitLabApi.getUserApi().addSshKey("Test-Key", TEST_SSH_KEY);
        assertNotNull(sshKey);
        assertEquals(TEST_SSH_KEY, sshKey.getKey());
        gitLabApi.getUserApi().deleteSshKey(sshKey.getId());

        User user = gitLabApi.getUserApi().getCurrentUser();
        sshKey = gitLabApi.getUserApi().addSshKey(user.getId(), "Test-Key1", TEST_SSH_KEY);
        assertNotNull(sshKey);
        assertEquals(TEST_SSH_KEY, sshKey.getKey());
        assertEquals(user.getId(), sshKey.getUserId());
        gitLabApi.getUserApi().deleteSshKey(sshKey.getId());
    }

    @Test
    public void testGetOptionalSshKey() throws GitLabApiException {

        assumeTrue(TEST_SSH_KEY != null);
        User user = gitLabApi.getUserApi().getCurrentUser();
        SshKey sshKey = gitLabApi.getUserApi().addSshKey(user.getId(), "Test-Key1", TEST_SSH_KEY);
        assertNotNull(sshKey);

        Optional<SshKey> optional = gitLabApi.getUserApi().getOptionalSshKey(sshKey.getId());
        assertNotNull(optional.isPresent());
        assertEquals(sshKey.getId(), optional.get().getId());
        gitLabApi.getUserApi().deleteSshKey(sshKey.getId());

        optional = gitLabApi.getUserApi().getOptionalSshKey(12345);
        assertNotNull(optional);
        assertFalse(optional.isPresent());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), GitLabApi.getOptionalException(optional).getHttpStatus());
    }
}
