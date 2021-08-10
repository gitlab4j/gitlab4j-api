package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.gitlab4j.api.models.SystemHook;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 *
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSystemHooksApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_HOOK_URL = "http://hook.example.com/hook/callback";

    private static final String TEST_SECRET_TOKEN = "123456abcd";

    private static GitLabApi gitLabApi;

    public TestSystemHooksApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        if (TEST_HOOK_URL == null || TEST_HOOK_URL.trim().isEmpty()) {
            System.err.println("TEST_HOOK_URL cannot be empty");
        } else {
            // Must setup the connection to the GitLab test server
            gitLabApi = baseTestSetup();
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testAddSystemHook() throws GitLabApiException {

        SystemHook hook = gitLabApi.getSystemHooksApi().addSystemHook(TEST_HOOK_URL, TEST_SECRET_TOKEN, true, false, true);
        try {
            assertNotNull(hook);
            assertEquals(TEST_HOOK_URL, hook.getUrl());
            assertTrue(hook.getPushEvents());
            assertFalse(hook.getTagPushEvents());
            assertFalse(hook.getMergeRequestsEvents());

            // TODO: Attribute "repository_update_events" is not being honored by GitLab API as per issue
            //       https://gitlab.com/gitlab-org/gitlab/-/issues/335129
            //       Revisit and update this test if/when the GitLab API/documentation is fixed
            assertTrue(hook.getRepositoryUpdateEvents());

            assertTrue(hook.getEnableSslVerification());
        } finally {
            // Ensure we remove the hook we added even if we had failures
            gitLabApi.getSystemHooksApi().deleteSystemHook(hook);
        }

        hook.withPushEvents(false)
            .withTagPushEvents(true)
            .withMergeRequestsEvents(true)
            .withRepositoryUpdateEvents(false)
            .withEnableSslVerification(false);

        SystemHook updatedHook = gitLabApi.getSystemHooksApi().addSystemHook(TEST_HOOK_URL, TEST_SECRET_TOKEN, hook);
        try {
            assertNotNull(updatedHook);
            assertEquals(TEST_HOOK_URL, updatedHook.getUrl());
            assertFalse(updatedHook.getPushEvents());
            assertTrue(updatedHook.getTagPushEvents());
            assertTrue(updatedHook.getMergeRequestsEvents());

            // TODO: Attribute "repository_update_events" is not being honored by GitLab API as per issue
            //       https://gitlab.com/gitlab-org/gitlab/-/issues/335129
            //       Revisit and update this test if/when the GitLab API/documentation is fixed
            assertTrue(updatedHook.getRepositoryUpdateEvents());

            assertFalse(updatedHook.getEnableSslVerification());
        } finally {
            // Ensure we remove the hook we added even if we had failures
            gitLabApi.getSystemHooksApi().deleteSystemHook(updatedHook);
        }

    }

    @Test
    public void testGetSystemHooks() throws GitLabApiException {

        SystemHook hook = gitLabApi.getSystemHooksApi().addSystemHook(TEST_HOOK_URL, TEST_SECRET_TOKEN, true, false, true);
        assertNotNull(hook);

        List<SystemHook> hooks = gitLabApi.getSystemHooksApi().getSystemHooks();
        assertNotNull(hooks);
        assertFalse(hooks.isEmpty());

        gitLabApi.getSystemHooksApi().deleteSystemHook(hook);
    }
}
