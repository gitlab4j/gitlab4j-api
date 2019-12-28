package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.List;
import java.util.Optional;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Release;
import org.gitlab4j.api.models.ReleaseParams;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class TestReleasesApi extends AbstractIntegrationTest {

    private static final String TEST_TAG_NAME = "test-release-tag";
    private static final String TEST_RELEASE_NAME = "Test Release";
    private static final String TEST_RELEASE_DESCRIPTION = "Test releases API release.";
    private static final String TEST_UPDATED_RELEASE_DESCRIPTION = "UPDATED: Test releases API release.";

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestReleasesApi() {
        super();
    }

    @BeforeClass
    public static void testSetup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        deleteTestResources();
    }

    @AfterClass
    public static void tearDown() {
	deleteTestResources();
    }

    private static final void deleteTestResources() {

        if (testProject != null) {
            try {
                gitLabApi.getReleasesApi().deleteRelease(testProject, TEST_TAG_NAME);
            } catch (Exception ignore) {}

            try {
                gitLabApi.getTagsApi().deleteTag(testProject, TEST_TAG_NAME);
            } catch (Exception ignore) {}
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateAndDeleteRelease() throws GitLabApiException {

	ReleaseParams params = new ReleaseParams()
		.withName(TEST_RELEASE_NAME)
		.withTagName(TEST_TAG_NAME)
		.withDescription(TEST_RELEASE_DESCRIPTION)
		.withRef("master");
        Release testRelease = gitLabApi.getReleasesApi().createRelease(testProject, params);
        assertNotNull(testRelease);
        assertEquals(TEST_RELEASE_NAME, testRelease.getName());
        assertEquals(TEST_TAG_NAME, testRelease.getTagName());

        List<Release> releases = gitLabApi.getReleasesApi().getReleases(testProject);
        assertTrue(releases.stream().map(Release::getTagName).anyMatch(s -> TEST_TAG_NAME.equals(s)));

        gitLabApi.getReleasesApi().deleteRelease(testProject, TEST_TAG_NAME);
        Optional<Release> release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
        assertFalse(release.isPresent());

        releases = gitLabApi.getReleasesApi().getReleases(testProject);
        assertFalse(releases.stream().map(Release::getTagName).anyMatch(s -> TEST_TAG_NAME.equals(s)));
    }

    @Test
    public void testCreateAndUpdateRelease() throws GitLabApiException {

	ReleaseParams params = new ReleaseParams()
		.withName(TEST_RELEASE_NAME)
		.withTagName(TEST_TAG_NAME)
		.withDescription(TEST_RELEASE_DESCRIPTION)
		.withRef("master");
        Release testRelease = gitLabApi.getReleasesApi().createRelease(testProject, params);
        assertNotNull(testRelease);
        assertEquals(TEST_RELEASE_NAME, testRelease.getName());
        assertEquals(TEST_TAG_NAME, testRelease.getTagName());

        Optional<Release> release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
        assertTrue(release.isPresent());

        params = new ReleaseParams()
		.withTagName(TEST_TAG_NAME)
		.withDescription(TEST_UPDATED_RELEASE_DESCRIPTION);
        testRelease = gitLabApi.getReleasesApi().updateRelease(testProject, params);
        assertNotNull(testRelease);
        assertEquals(TEST_RELEASE_NAME, testRelease.getName());
        assertEquals(TEST_TAG_NAME, testRelease.getTagName());
        assertEquals(TEST_UPDATED_RELEASE_DESCRIPTION, testRelease.getDescription());

        release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
        assertTrue(release.isPresent());
        testRelease = release.get();
        assertEquals(TEST_RELEASE_NAME, testRelease.getName());
        assertEquals(TEST_TAG_NAME, testRelease.getTagName());
        assertEquals(TEST_UPDATED_RELEASE_DESCRIPTION, testRelease.getDescription());

        gitLabApi.getReleasesApi().deleteRelease(testProject, TEST_TAG_NAME);
        release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
        assertFalse(release.isPresent());
    }
}
