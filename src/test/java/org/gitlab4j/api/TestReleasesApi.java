package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.gitlab4j.api.models.Milestone;
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

    private static final String TEST_TAG_NAME = "test-release/1.0.0";
    private static final String TEST_RELEASE_NAME = "Test Release";
    private static final String TEST_RELEASE_DESCRIPTION = "Test releases API release.";
    private static final String TEST_UPDATED_RELEASE_DESCRIPTION = "UPDATED: Test releases API release.";
    private static final String TEST_MILESTONE_TITLE = "Test Release 1.0.0 Milestone";

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

            try {
                List<Milestone> milestones = gitLabApi.getMilestonesApi().getMilestones(testProject);
                Optional<Milestone> testMilestone = milestones.stream().
                        filter(m -> m.getTitle().equals(TEST_MILESTONE_TITLE)).findFirst();
                if (testMilestone.isPresent()) {
                    gitLabApi.getMilestonesApi().deleteMilestone(testProject, testMilestone.get().getId());
                }
            } catch (Exception ignore) {}
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateAndDeleteRelease() throws GitLabApiException {

        Milestone testMilestone = gitLabApi.getMilestonesApi().createMilestone(testProject, TEST_MILESTONE_TITLE, null, null, null);
        assertNotNull(testMilestone);

        try {

            ReleaseParams params = new ReleaseParams()
                    .withName(TEST_RELEASE_NAME)
                    .withTagName(TEST_TAG_NAME)
                    .withDescription(TEST_RELEASE_DESCRIPTION)
                    .withRef("master")
                    .withMilestones(Collections.singletonList(TEST_MILESTONE_TITLE));
            Release testRelease = gitLabApi.getReleasesApi().createRelease(testProject, params);
            assertNotNull(testRelease);
            assertEquals(TEST_RELEASE_NAME, testRelease.getName());
            assertEquals(TEST_TAG_NAME, testRelease.getTagName());
            assertTrue(testRelease.getMilestones().stream().map(Milestone::getId)
                    .anyMatch(id -> id.equals(testMilestone.getId())));

            List<Release> releases = gitLabApi.getReleasesApi().getReleases(testProject);
            assertTrue(releases.stream().map(Release::getTagName).anyMatch(s -> TEST_TAG_NAME.equals(s)));

            gitLabApi.getReleasesApi().deleteRelease(testProject, TEST_TAG_NAME);
            Optional<Release> release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
            assertFalse(release.isPresent());

            releases = gitLabApi.getReleasesApi().getReleases(testProject);
            assertFalse(releases.stream().map(Release::getTagName).anyMatch(s -> TEST_TAG_NAME.equals(s)));

        } finally {
            try {
                gitLabApi.getMilestonesApi().deleteMilestone(testProject, testMilestone.getId());
            } catch (Exception ignore) {}
        }
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
        assertTrue(testRelease.getMilestones() == null || testRelease.getMilestones().isEmpty());

        Optional<Release> release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
        assertTrue(release.isPresent());

        Milestone testMilestone = gitLabApi.getMilestonesApi().createMilestone(testProject, TEST_MILESTONE_TITLE, null, null, null);
        assertNotNull(testMilestone);

        try {

            Date releasedAt = new Date();
            params = new ReleaseParams()
                    .withTagName(TEST_TAG_NAME)
                    .withDescription(TEST_UPDATED_RELEASE_DESCRIPTION)
                    .withReleasedAt(releasedAt)
                    .withMilestones(Collections.singletonList(TEST_MILESTONE_TITLE));
            testRelease = gitLabApi.getReleasesApi().updateRelease(testProject, params);
            assertNotNull(testRelease);
            assertEquals(TEST_RELEASE_NAME, testRelease.getName());
            assertEquals(TEST_TAG_NAME, testRelease.getTagName());
            assertEquals(TEST_UPDATED_RELEASE_DESCRIPTION, testRelease.getDescription());
            assertEquals(releasedAt, testRelease.getReleasedAt());
            assertTrue(testRelease.getMilestones().stream().map(Milestone::getId)
                    .anyMatch(id -> id.equals(testMilestone.getId())));

            release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
            assertTrue(release.isPresent());
            testRelease = release.get();
            assertEquals(TEST_RELEASE_NAME, testRelease.getName());
            assertEquals(TEST_TAG_NAME, testRelease.getTagName());
            assertEquals(TEST_UPDATED_RELEASE_DESCRIPTION, testRelease.getDescription());
            assertEquals(releasedAt, testRelease.getReleasedAt());
            assertTrue(testRelease.getMilestones().stream().map(Milestone::getId)
                    .anyMatch(id -> id.equals(testMilestone.getId())));

            gitLabApi.getReleasesApi().deleteRelease(testProject, TEST_TAG_NAME);
            release = gitLabApi.getReleasesApi().getOptionalRelease(testProject, TEST_TAG_NAME);
            assertFalse(release.isPresent());

        } finally {
            try {
                gitLabApi.getMilestonesApi().deleteMilestone(testProject, testMilestone.getId());
            } catch (Exception ignore) {}
        }
    }
}
