package org.gitlab4j.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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

import org.gitlab4j.api.models.Email;
import org.gitlab4j.api.models.PersonalAccessToken;
import org.gitlab4j.api.models.PersonalAccessToken.Scope;
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
public class TestUserApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_USERNAME = HelperUtils.getProperty(USERNAME_KEY);
    private static final String TEST_BLOCK_USERNAME = HelperUtils.getProperty(BLOCK_USERNAME_KEY);
    private static final String TEST_SUDO_AS_USERNAME = HelperUtils.getProperty(SUDO_AS_USERNAME_KEY);

    private static final String TEST_IMPERSONATION_TOKEN_NAME = "ipt_1";
    private static final String TEST_PERSONAL_ACCESS_TOKEN_NAME = "pat_1";
    private static final String TEST_SSH_KEY = 
	"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC3rWzl/oPAD+Em2iGTmR81HcYZsopvnKp7jelI4XS91fT1NjCRrGsxf5Mw/" +
        "KnmtBjhk+kQjkhIrnsBDcs6DZWtNcHJtyWJZrYsfxMTqWCaQv+OTRwVboqS2pmPcbK3gizUd5GCLFTKbg4OMpdywTwi6NAPwQ" +
	"rtn3xwiVnGGCfBSyRFppcYP81otALctrlAW57V5+bQwFIJteJ+NWe1UmPxrqQ0N/a+dEEoJHzwX8RtVSkULafrRw8avn6Zp2x" +
        "1OlD2aIEMQWvepNTRW6UDMSmWFc61ycy1pF5sCT5rij+b/fN4qCEvQs6R7GmCzaaZzbWuAqaxLRdITm/WUxdG6rjh";

    private static final String TEST_USER_EMAIL = "test-user-email123@gitlab4j.org";

    private static final String TEST_EXTERNAL_USERNAME = HelperUtils.getProperty(EXTERNAL_USERNAME_KEY);
    private static final String TEST_EXTERNAL_PROVIDER = HelperUtils.getProperty(EXTERNAL_PROVIDER_KEY);
    private static final String TEST_EXTERNAL_UID = HelperUtils.getProperty(EXTERNAL_UID_KEY);

    private static GitLabApi gitLabApi;
    private static User blockUser;

    public TestUserApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_USERNAME == null || TEST_USERNAME.trim().isEmpty()) {
            problems += "TEST_USER_NAME cannot be empty\n";
        }

        if (problems.isEmpty()) {

            // Must setup the connection to the GitLab test server
            gitLabApi = baseTestSetup();

            if (gitLabApi != null) {

                if (TEST_EXTERNAL_USERNAME != null) {
                    Optional<User> optionalUser = gitLabApi.getUserApi().getOptionalUser(TEST_EXTERNAL_USERNAME);
                    if (optionalUser.isPresent()) {
                        try {
                            gitLabApi.getUserApi().deleteUser(optionalUser.get());
                        } catch (Exception ignore) {}
                    }
                }

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
                                if (key.getKey().startsWith(TEST_SSH_KEY)) {
                                    gitLabApi.getUserApi().deleteSshKey(key.getId());
                                }
                            }
                        }
                    } catch (Exception ignore) {}
                }

                try {
                    List<Email> emails = gitLabApi.getUserApi().getEmails();
                    for (Email email : emails) {
                        if (TEST_USER_EMAIL.equals(email.getEmail())) {
                            gitLabApi.getUserApi().deleteEmail(email.getId());
                        }
                    }
                }  catch (Exception ignore) {}
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
    public void testExternalUid() throws GitLabApiException {

        assumeNotNull(TEST_EXTERNAL_USERNAME);
        assumeNotNull(TEST_EXTERNAL_PROVIDER);
        assumeNotNull(TEST_EXTERNAL_UID);

        User externalUser = null;
        try {

            User userSettings = new User()
                    .withUsername(TEST_EXTERNAL_USERNAME)
                    .withEmail(TEST_EXTERNAL_USERNAME + "@gitlab4j.org")
                    .withName("GitLab4J External User")
                    .withSkipConfirmation(true)
                    .withIsAdmin(false)
                    .withExternUid(TEST_EXTERNAL_UID)
                    .withProvider(TEST_EXTERNAL_PROVIDER);
            externalUser = gitLabApi.getUserApi().createUser(userSettings, TEST_LOGIN_PASSWORD, false);
            assertNotNull(externalUser);

            Optional<User> optionalUser = gitLabApi.getUserApi().getOptionalUserByExternalUid(TEST_EXTERNAL_PROVIDER, TEST_EXTERNAL_UID);
            assertNotNull(optionalUser);
            assertTrue(optionalUser.isPresent());
            assertEquals(externalUser.getId(), optionalUser.get().getId());

            optionalUser = gitLabApi.getUserApi().getOptionalUserByExternalUid("unknown-provider", "unknown-uid");
            assertNotNull(optionalUser);
            assertFalse(optionalUser.isPresent());

        } finally {
            if (externalUser != null) {
                try {
                    gitLabApi.getUserApi().deleteUser(externalUser);
                } catch (Exception ignore) {}
            }
        }
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

        // NOTE: READ_REGISTRY & WRITE_REGISTRY scopes are left out because the GitLab server docker instance does not
        // have the registry configured and the test would thus fail.
        Scope[] scopes = {Scope.API, Scope.READ_API, Scope.READ_USER, Scope.READ_REPOSITORY, Scope.WRITE_REPOSITORY, Scope.SUDO};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");

        PersonalAccessToken token = null;
        try {

            token = gitLabApi.getUserApi().createImpersonationToken(user, TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);

            assertNotNull(token);
            assertNotNull(token.getId());
            assertEquals(TEST_IMPERSONATION_TOKEN_NAME, token.getName());
            assertEquals(expiresAt.getTime(), token.getExpiresAt().getTime());
            assertEquals(scopes.length, token.getScopes().size());
            assertThat(token.getScopes(), contains(scopes));

        } finally {
            if (user != null && token != null) {
                gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), token.getId());
            }
        }
    }

    @Test
    public void testGetOptionalImpersonationToken() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");

        PersonalAccessToken token = null;
        try {

            token = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);
            assertNotNull(token);

            Optional<PersonalAccessToken> optional = gitLabApi.getUserApi().getOptionalImpersonationToken(user.getId(), token.getId());
            assertTrue(optional.isPresent());
            assertEquals(token.getId(), optional.get().getId());
            gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), token.getId());

            optional = gitLabApi.getUserApi().getOptionalImpersonationToken(user.getId(), 123456);
            assertNotNull(optional);
            assertFalse(optional.isPresent());

        } finally {
            if (user != null && token != null) {
                gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), token.getId());
            }
        }
    }

    @Test
    public void testGetImpersonationTokens() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");
        PersonalAccessToken createdToken = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);
        assertNotNull(createdToken);

        PersonalAccessToken token =  gitLabApi.getUserApi().getImpersonationToken(user.getId(), createdToken.getId());
        assertNotNull(token);
        assertEquals(createdToken.getId(), token.getId());
        assertEquals(TEST_IMPERSONATION_TOKEN_NAME, token.getName());
        assertEquals(createdToken.getExpiresAt(), token.getExpiresAt());

        List<PersonalAccessToken> tokens = gitLabApi.getUserApi().getImpersonationTokens(user.getId());
        assertNotNull(tokens);
        assertTrue(tokens.size() > 0);

        gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), createdToken.getId());
    }

    @Test
    public void testRevokeImpersonationToken() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();
        Scope[] scopes = {Scope.API, Scope.READ_USER};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");
        PersonalAccessToken createdToken = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME + "a", expiresAt, scopes);
        assertNotNull(createdToken);

        PersonalAccessToken token =  gitLabApi.getUserApi().getImpersonationToken(user.getId(), createdToken.getId());
        assertNotNull(token);
        assertEquals(createdToken.getId(), token.getId());

        gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), createdToken.getId());
        token =  gitLabApi.getUserApi().getImpersonationToken(user.getId(), createdToken.getId());
        assertFalse(token.getActive());
    }

    @Test
    public void testCreatePersonalAccessToken() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();

        // NOTE: READ_REGISTRY & WRITE_REGISTRY scopes are left out because the GitLab server docker instance does not
        // have the registry configured and the test would thus fail.
        Scope[] scopes = {Scope.API, Scope.READ_API, Scope.READ_USER, Scope.READ_REPOSITORY, Scope.WRITE_REPOSITORY, Scope.SUDO};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");

        PersonalAccessToken token = null;
        try {

            token = gitLabApi.getUserApi().createPersonalAccessToken(user, TEST_PERSONAL_ACCESS_TOKEN_NAME, expiresAt, scopes);

            assertNotNull(token);
            assertNotNull(token.getId());
            assertEquals(TEST_PERSONAL_ACCESS_TOKEN_NAME, token.getName());
            assertEquals(expiresAt.getTime(), token.getExpiresAt().getTime());
            assertEquals(scopes.length, token.getScopes().size());
            assertThat(token.getScopes(), contains(scopes));

        } finally {
            if (user != null && token != null) {
                // GitLab doesn't have this API method yet - not a big issue since multiple tokens with the same name
                // can be created. Note that you won't see a token in the UI unless the expiry date is in the future.
//                gitLabApi.getUserApi().revokePersonalAccessToken(user.getId(), token.getId());
            }
        }
    }

    @Test
    public void testGetSshKeys() throws GitLabApiException {

        assumeTrue(TEST_SSH_KEY != null);
        SshKey sshKey = gitLabApi.getUserApi().addSshKey("Test-Key", TEST_SSH_KEY);
        assertNotNull(sshKey);
        assertTrue(TEST_SSH_KEY, sshKey.getKey().startsWith(TEST_SSH_KEY));
        gitLabApi.getUserApi().deleteSshKey(sshKey.getId());

        User user = gitLabApi.getUserApi().getCurrentUser();
        sshKey = gitLabApi.getUserApi().addSshKey(user.getId(), "Test-Key1", TEST_SSH_KEY);
        assertNotNull(sshKey);
        assertTrue(TEST_SSH_KEY, sshKey.getKey().startsWith(TEST_SSH_KEY));
        assertEquals(user.getId(), sshKey.getUserId());

        Optional<SshKey> optional = gitLabApi.getUserApi().getOptionalSshKey(sshKey.getId());
        assertNotNull(optional.isPresent());
        assertTrue(TEST_SSH_KEY, sshKey.getKey().startsWith(TEST_SSH_KEY));

        gitLabApi.getUserApi().deleteSshKey(sshKey.getId());
    }

    @Test
    public void testGetOptionalSshKey() throws GitLabApiException {

        assumeTrue(TEST_SSH_KEY != null);
        User user = gitLabApi.getUserApi().getCurrentUser();
        SshKey sshKey = gitLabApi.getUserApi().addSshKey(user.getId(), "Test-Key2", TEST_SSH_KEY);
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

    @Test
    public void testCurrentUserEmails() throws GitLabApiException {

        List<Email> currentUserEmails = gitLabApi.getUserApi().getEmails();
        assertNotNull(currentUserEmails);
        int currentSize = currentUserEmails.size();

        Email email = gitLabApi.getUserApi().addEmail(TEST_USER_EMAIL);
        currentUserEmails = gitLabApi.getUserApi().getEmails();
        assertTrue(currentUserEmails.size() == currentSize + 1);

        Email found = currentUserEmails.stream().filter(e -> e.getEmail().equals(TEST_USER_EMAIL)).findAny().orElse(null);
        assertNotNull(found);

        Email email1 = gitLabApi.getUserApi().getEmail(email.getId());
        assertEquals(email.getEmail(), email1.getEmail());

        gitLabApi.getUserApi().deleteEmail(email.getId());
        currentUserEmails = gitLabApi.getUserApi().getEmails();
        assertEquals(currentSize, currentUserEmails.size());
        found = currentUserEmails.stream().filter(e -> e.getEmail().equals(TEST_USER_EMAIL)).findAny().orElse(null);
        assertNull(found);
    }

    @Test
    public void testEmails() throws GitLabApiException {

        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(currentUser);
        List<Email> emails = gitLabApi.getUserApi().getEmails(currentUser);
        assertNotNull(emails);
        int currentSize = emails.size();

        Email email = gitLabApi.getUserApi().addEmail(currentUser, TEST_USER_EMAIL, true);
        emails = gitLabApi.getUserApi().getEmails(currentUser);
        assertTrue(emails.size() == currentSize + 1);
        Email found = emails.stream().filter(e -> e.getEmail().equals(TEST_USER_EMAIL)).findAny().orElse(null);
        assertNotNull(found);
 
        gitLabApi.getUserApi().deleteEmail(currentUser, email.getId());
        emails = gitLabApi.getUserApi().getEmails(currentUser);
        assertEquals(currentSize, emails.size());
        found = emails.stream().filter(e -> e.getEmail().equals(TEST_USER_EMAIL)).findAny().orElse(null);
        assertNull(found);
    }
}
