package org.gitlab4j.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Email;
import org.gitlab4j.api.models.GpgKey;
import org.gitlab4j.api.models.ImpersonationToken;
import org.gitlab4j.api.models.ImpersonationToken.Scope;
import org.gitlab4j.api.models.Membership;
import org.gitlab4j.api.models.MembershipSourceType;
import org.gitlab4j.api.models.SshKey;
import org.gitlab4j.api.models.User;
import org.gitlab4j.api.models.Version;
import org.gitlab4j.api.utils.ISO8601;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
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

    // Key for fake@fake.com - set to never expire
    private static final String TEST_GPG_KEY = "-----BEGIN PGP PUBLIC KEY BLOCK-----\n\n" +
            "mQINBGBHltIBEADW8zSGAs/XEkwWI36xOusuOqSINhTDVuqq3n50Oazb+a9Ai/MM\n" +
            "8GTm900ZZghGBVUXGm8PkWuSmabcpbDbjYmJx8aIY71UipXxXrfBzr8S2yOx7IjU\n" +
            "m6AEw0HwNUF6ayBwsklUFFWMyCeCVSCeZNldFwKRQApP6YOlTlwmZFESv32J6AHz\n" +
            "goeEsIcoea484nVJKOl7unneb8TyuF6kmViyZtiDkjeiH5vNy8XjSWH9xl5tcBxy\n" +
            "70NxkZt9EKnMq8izy51OBdzA+oWByGIGRjRPrW+5niMCGltV0w12M0uMDa2pJU2B\n" +
            "Z0U7uL/Lj3srMnD54OjbjK++wtYbshhGKXhAzshk9RgZq5fEN7Jjn1CTvue5EcHz\n" +
            "D27RD4yy35MledJ0hrvcTVVxvFmTg3TfDFdQBVLHjRATdXo7xT1Wg35M3z3aVSRt\n" +
            "PoynOxGNSotKUGfW5bhCB9XjUNpNY7+IphLS4LuQ3vZdEs9MTTWagoOoDx5w2PRS\n" +
            "7VNccRsqgIbNkpPjy78wN9m1QV97ytFs57eE+FfNDkKYeeCQDeHbeBlOmoEP/vSc\n" +
            "plOb6K3mdJgs0d5klXTOrFRVCYHHQ84p1YyQDKZO2Qd6JtHo5FNeqvgj5JwnBdfH\n" +
            "NGUdnaSn6hQTd8UB0AfwB+CC7cJq/fhbgcNvfK0ErHd24tsCif8vP9AG4QARAQAB\n" +
            "tBtGYWtlIFBlcnNvbiA8ZmFrZUBmYWtlLmNvbT6JAk4EEwEIADgWIQSE5/Jy7XYO\n" +
            "8riBcF/RhpwhMYJMpwUCYEeW0gIbAwULCQgHAgYVCgkICwIEFgIDAQIeAQIXgAAK\n" +
            "CRDRhpwhMYJMp+ESD/99LNCF1bqg/jhQOC4UIdwzCCVkUP8imrL6NnBUso+FAwH6\n" +
            "AT+Pbg8JLpM0lfcjzD5PV+ekLtWTZZVsyObfdRo7GrtBt/wcLKfJU5uQmrJfRClN\n" +
            "mdiHbh8LyVwfhLp20JRqV6NiEWSdWwNBq8zMZGgZ6HONC7JPGokak0MKpU2Woyc3\n" +
            "BlAU+998mdoDPKWT8XEr8cnHFFuUpZb4oWhqNV36mFrkdBZovEbnfefA+JvcxwEs\n" +
            "khAeaNLmpZWZ95YSimJuL4sKUjPCXlkHs9nayTFeDdNcZPAuZwfBCLpdCSj4ErYV\n" +
            "MyMHs/8J7CibulJB/o8qpp07oa3Qlcd62XNpqDOEIxiWHefnaYgkyIHtmzhXH4Fa\n" +
            "O7Ir3zGcwARXpQfobRUmtFdzeJT3zVVdUWjkKr5rgwYZZraADXGvOo8xJ5cvdrzq\n" +
            "4/yvOaNNoIA4KkuZcXbnqsh31pT77PxsqK60+TpLzw/jyzTqmVTEG+6SUobW7o6D\n" +
            "qrpqR2RPH0GtyzKHuGKSCJClDmiLF+XSyjScGUAlQ9hcFquI6F1Vddy88yURaESK\n" +
            "qy2agvhSkgpRxeuglytl6ZbWp/AIXrkh3F6qJozMNMFzEokapRYsQe+QdXCDSGDQ\n" +
            "DNvXXfIvxrFv+vQLy4jjM+DwJfrw0eN2XZ+U3E9sP0uloiVgU1zg1wc2tyPv77kC\n" +
            "DQRgR5bSARAAwumSlVvzb3JORu2ezPsCh8C+VJe2nPo8m+vR1Dni58UB3xnixZnF\n" +
            "lPaEprnIO5TSDwELJJN3oNM+AVAPjUJYHotKny5iBSFPIbHYYHs/mGRqo4jHa4b6\n" +
            "riNRWJ1xoYdvzH7PKAcV36tl27Y4SuQVMYmnaSXbDkGOqd9cenqVHikhj9+SJxNr\n" +
            "yIHrw/SNbNbRl3cMVfke2vgRp9Eso5Ivpl6tjNoohAwDp3L6MGbHliEYQgk8pjzq\n" +
            "bIR4lakKNVdRQoW/ZaQM2GkDlbCIEuY/7Rr4ZA1L0tsALY+bnv+9SMtA1OnMvNQ7\n" +
            "7Pn2uTSHeIbSVxsRk9aWmK63l20OEcB/YPmTSeNvq0JVzJ2fLG2ZL6NUHBBF2DB4\n" +
            "x66FA8mu9cK3Y9Jnc/3KWdzGA74R4HSIcuDPGkZmPtDMXSgXArRuD0s71QgH5E3U\n" +
            "9/QJ8g4s9Mjb/8aBhbg+7lm8HzN3XANmbR+y/s71Askw/ewlbhfmwxK+/XI3xDr0\n" +
            "1jkf42cmoLq4/Y292mQjFkcq6cCFIxDOXM89Qopbtm6PnaQsKyz0GoiyHsP846yS\n" +
            "RdiHTVHrUdiLl+6TIK90cm8CzNoiF+UGvdD4HObWbySh8O8n1nno+lX9EwSoq20o\n" +
            "0WobXesDjNIrzJHow0WGGbx5gTxlZq0WwmgXgwYM0PbqlfjxFjct+98AEQEAAYkC\n" +
            "NgQYAQgAIBYhBITn8nLtdg7yuIFwX9GGnCExgkynBQJgR5bSAhsMAAoJENGGnCEx\n" +
            "gkynbOkQAI+N/wFxOTbewuTiy0P11saqqYr7Jwc7NLhqOVZ1tHKaTB6ZDbIrlWjN\n" +
            "u2tFk7PqsA4/zI6KO9JoKiQYbopZ+xjd1nCJUjkUKI/wi4rl0t7ELQKhlSlUC11f\n" +
            "Nz0C6Q+9cwRQCCT4sX/ZkzVQbGWx9fkAYVHzezDqh43xorXJ2ix5y63pr1NGuUgx\n" +
            "EujMlUbXFzXpUrCmdUVWujlp4gSEfd6tLW0WMw0tYJe6UY7xx4EmWsT+kAGj7QLH\n" +
            "L06yFigDQ0eUkGQ1T7Z0AjG4EXGETbX6lSLwzIBnmaZXQxdx4LiRy9TcaNtcowH4\n" +
            "U2yxCoG0o0kS7sS/rI77TV6WZ46DPCJmlNJ+MP8lt0j/nsDA3AECB1AA+8SNepbA\n" +
            "LSZY7MJmh4nsqJ+iy/XMosipluZx2u6ZwlXAHxAzHhs7FBsvdMtq/gLNAlZzVyeH\n" +
            "UqzRaMJps7xIbap5d5jZT5jaZwFeGi+63YVRx3Jm6dkiBCPFffLyWdrzkFTZdWqZ\n" +
            "PkiRbJ64wYPIWQgAN/RhmCcRBhxJE8f7kgo/nBkZ5dwmfXgnXpheEaaCSzvJ4nMh\n" +
            "TUdg6ZLna12QndjI5gy5aenrr5H/HmDKKSNkuWZv0+NS4GhwnL8NFs+MRk6znpLN\n" +
            "aEjPdfYxINCMz+uotKJV9NieDWIbEJLlfZUf2hJwuwwjQGAyVf7b\n" +
            "=ryCD\n" +
            "-----END PGP PUBLIC KEY BLOCK-----";

    private static final String TEST_EXTERNAL_USERNAME = HelperUtils.getProperty(EXTERNAL_USERNAME_KEY);
    private static final String TEST_EXTERNAL_PROVIDER = HelperUtils.getProperty(EXTERNAL_PROVIDER_KEY);
    private static final String TEST_EXTERNAL_UID = HelperUtils.getProperty(EXTERNAL_UID_KEY);

    private static GitLabApi gitLabApi;
    private static User blockUser;

    public TestUserApi() {
        super();
    }

    @BeforeAll
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

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
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
        assumeTrue(blockUser != null);

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

        assumeTrue(TEST_EXTERNAL_USERNAME != null);
        assumeTrue(TEST_EXTERNAL_PROVIDER != null);
        assumeTrue(TEST_EXTERNAL_UID != null);

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
            Long sudoAsId = user.getId();

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

        // NOTE: READ_API, READ_REGISTRY & WRITE_REGISTRY scopes are left out because the GitLab server docker instance does not
        // have the registry configured and the test would thus fail.
        Scope[] scopes = {Scope.API, Scope.READ_USER, Scope.READ_REPOSITORY, Scope.WRITE_REPOSITORY, Scope.SUDO};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");

        ImpersonationToken token = null;
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

        ImpersonationToken token = null;
        try {

            token = gitLabApi.getUserApi().createImpersonationToken(user.getId(), TEST_IMPERSONATION_TOKEN_NAME, expiresAt, scopes);
            assertNotNull(token);

            Optional<ImpersonationToken> optional = gitLabApi.getUserApi().getOptionalImpersonationToken(user.getId(), token.getId());
            assertTrue(optional.isPresent());
            assertEquals(token.getId(), optional.get().getId());
            gitLabApi.getUserApi().revokeImpersonationToken(user.getId(), token.getId());

            optional = gitLabApi.getUserApi().getOptionalImpersonationToken(user.getId(), 123456L);
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
    public void testRevokeImpersonationToken() throws GitLabApiException, ParseException {

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
    public void testCreatePersonalAccessToken() throws GitLabApiException, ParseException {

        User user = gitLabApi.getUserApi().getCurrentUser();

        // NOTE: READ_REGISTRY & WRITE_REGISTRY scopes are left out because the GitLab server docker instance does not
        // have the registry configured and the test would thus fail.
        Scope[] scopes = {Scope.API, Scope.READ_API, Scope.READ_USER, Scope.READ_REPOSITORY, Scope.WRITE_REPOSITORY, Scope.SUDO};
        Date expiresAt = ISO8601.toDate("2018-01-01T00:00:00Z");

// This does not work with the GitLab version we are using in the integration tests
//        ImpersonationToken token = null;
//        try {
//
//            token = gitLabApi.getUserApi().createPersonalAccessToken(user, TEST_PERSONAL_ACCESS_TOKEN_NAME, expiresAt, scopes);
//
//            assertNotNull(token);
//            assertNotNull(token.getId());
//            assertEquals(TEST_PERSONAL_ACCESS_TOKEN_NAME, token.getName());
//            assertEquals(expiresAt.getTime(), token.getExpiresAt().getTime());
//            assertEquals(scopes.length, token.getScopes().size());
//            assertThat(token.getScopes(), contains(scopes));
//
//        } finally {
//            if (user != null && token != null) {
//                // GitLab doesn't have this API method yet - not a big issue since multiple tokens with the same name
//                // can be created. Note that you won't see a token in the UI unless the expiry date is in the future.
//                // gitLabApi.getUserApi().revokePersonalAccessToken(user.getId(), token.getId());
//            }
//        }
    }

    @Test
    public void testGetSshKeys() throws GitLabApiException {

        assumeTrue(TEST_SSH_KEY != null);
        SshKey sshKey = gitLabApi.getUserApi().addSshKey("Test-Key", TEST_SSH_KEY);
        assertNotNull(sshKey);
        assertTrue(sshKey.getKey().startsWith(TEST_SSH_KEY), TEST_SSH_KEY);
        gitLabApi.getUserApi().deleteSshKey(sshKey.getId());

        User user = gitLabApi.getUserApi().getCurrentUser();
        sshKey = gitLabApi.getUserApi().addSshKey(user.getId(), "Test-Key1", TEST_SSH_KEY);
        assertNotNull(sshKey);
        assertTrue(sshKey.getKey().startsWith(TEST_SSH_KEY), TEST_SSH_KEY);
        assertEquals(user.getId(), sshKey.getUserId());

        Optional<SshKey> optional = gitLabApi.getUserApi().getOptionalSshKey(sshKey.getId());
        assertNotNull(optional.isPresent());
        assertTrue(sshKey.getKey().startsWith(TEST_SSH_KEY), TEST_SSH_KEY);

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

        optional = gitLabApi.getUserApi().getOptionalSshKey(12345L);
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

    @Test
    public void testGpgKeys() throws GitLabApiException {
        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(currentUser);
        List<GpgKey> keys = gitLabApi.getUserApi().listGpgKeys(currentUser.getId());
        assertNotNull(keys);
        int currentSize = keys.size();

        GpgKey key = gitLabApi.getUserApi().addGpgKey(currentUser.getId(), TEST_GPG_KEY);
        keys = gitLabApi.getUserApi().listGpgKeys(currentUser.getId());
        assertTrue(keys.size() == currentSize + 1);
        GpgKey found = keys.stream().filter(e -> e.getKey().equals(TEST_GPG_KEY)).findAny().orElse(null);
        assertNotNull(found);

        gitLabApi.getUserApi().deleteGpgKey(currentUser.getId(), key.getId());
        keys = gitLabApi.getUserApi().listGpgKeys(currentUser.getId());
        assertEquals(currentSize, keys.size());
        found = keys.stream().filter(e -> e.getKey().equals(TEST_GPG_KEY)).findAny().orElse(null);
        assertNull(found);
    }

    @Test
    public void testGpgKeysCurrentUser() throws GitLabApiException {
        List<GpgKey> keys = gitLabApi.getUserApi().listGpgKeys();
        assertNotNull(keys);
        int currentSize = keys.size();

        GpgKey key = gitLabApi.getUserApi().addGpgKey(TEST_GPG_KEY);
        keys = gitLabApi.getUserApi().listGpgKeys();
        assertTrue(keys.size() == currentSize + 1);
        GpgKey found = keys.stream().filter(e -> e.getKey().equals(TEST_GPG_KEY)).findAny().orElse(null);
        assertNotNull(found);

        gitLabApi.getUserApi().deleteGpgKey(key.getId());
        keys = gitLabApi.getUserApi().listGpgKeys();
        assertEquals(currentSize, keys.size());
        found = keys.stream().filter(e -> e.getKey().equals(TEST_GPG_KEY)).findAny().orElse(null);
        assertNull(found);
    }

    public void testGetMemberships() throws GitLabApiException {
        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        assertNotNull(currentUser);
        List<Membership> memberships = gitLabApi.getUserApi().getMemberships(currentUser.getId());
        assertNotNull(memberships);
        assertEquals(3, memberships.size());

        Membership membership1 = memberships.get(0);
        assertMembershipEquals(membership1, 1L, "test-project", MembershipSourceType.PROJECT, AccessLevel.MAINTAINER);

        Membership membership2 = memberships.get(1);
        assertMembershipEquals(membership2, 1L, "Test Group", MembershipSourceType.NAMESPACE, AccessLevel.OWNER);

        Membership membership3 = memberships.get(2);
        assertMembershipEquals(membership3, 1L, "subgroup", MembershipSourceType.NAMESPACE, AccessLevel.OWNER);
    }

    private void assertMembershipEquals(Membership actualMembership,
                                        long expectedSourceId,
                                        String expectedSourceName,
                                        MembershipSourceType expectedSourceType,
                                        AccessLevel expectedAccessLevel) {
        assertEquals(expectedSourceId, actualMembership.getSourceId());
        assertEquals(expectedSourceName, actualMembership.getSourceName());
        assertEquals(expectedSourceType, actualMembership.getSourceType());
        assertEquals(expectedAccessLevel, actualMembership.getAccessLevel());
    }
}
