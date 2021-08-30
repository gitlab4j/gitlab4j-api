package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Label;
import org.gitlab4j.api.models.Project;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestLabelsApi extends AbstractIntegrationTest {

    private static final String TEST_GROUP = HelperUtils.getProperty(GROUP_KEY);
    private static final String TEST_PROJECT_LABEL = "test-project-label";
    private static final String TEST_GROUP_LABEL = "test-group-label";
    private static final String TEST_PROJECT_LABEL_1 = "test-project-label-1";
    private static final String TEST_GROUP_LABEL_1 = "test-group-label-1";

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static Group testGroup;

    public TestLabelsApi() {
        super();
    }

    @BeforeAll
    public static void testSetup() {

        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        String problems = "";
        if (gitLabApi != null) {
            Optional<Group> group = gitLabApi.getGroupApi().getOptionalGroup(TEST_GROUP);
            if (group.isPresent()) {
                testGroup = group.get();
            } else {
                problems += "Problem fetching test group\n";
            }
        }

        if (!problems.isEmpty()) {
            System.err.print(problems);
        }

        deleteTestLabels();
    }

    @AfterAll
    public static void tearDown() {
        deleteTestLabels();
    }

    private static final void deleteTestLabels() {

        if (testProject != null) {
            try {
                gitLabApi.getLabelsApi().deleteProjectLabel(testProject, TEST_PROJECT_LABEL);
            } catch (Exception ignore) {}

            try {
                gitLabApi.getLabelsApi().deleteGroupLabel(testGroup, TEST_GROUP_LABEL);
            } catch (Exception ignore) {}

            try {
                gitLabApi.getLabelsApi().deleteProjectLabel(testProject, TEST_PROJECT_LABEL_1);
            } catch (Exception ignore) {}

            try {
                gitLabApi.getLabelsApi().deleteGroupLabel(testGroup, TEST_GROUP_LABEL_1);
            } catch (Exception ignore) {}
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateAndDeleteProjectLabel() throws GitLabApiException {

	Label labelConfig = new Label().withName(TEST_PROJECT_LABEL).withColor("#FF0000");
	Label testLabel = gitLabApi.getLabelsApi().createProjectLabel(testProject, labelConfig);
        assertNotNull(testLabel);
        assertEquals(TEST_PROJECT_LABEL, testLabel.getName());

        Optional<Label> optionalLabel = gitLabApi.getLabelsApi().getOptionalProjectLabel(testProject, TEST_PROJECT_LABEL);
        assertTrue(optionalLabel.isPresent());
        assertEquals(TEST_PROJECT_LABEL, optionalLabel.get().getName());
        assertEquals("#FF0000", optionalLabel.get().getColor());

        Stream<Label> labelsStream = gitLabApi.getLabelsApi().getProjectLabelsStream(testProject);
        assertTrue(labelsStream.map(Label::getName).anyMatch(s -> TEST_PROJECT_LABEL.equals(s)));

        gitLabApi.getLabelsApi().deleteProjectLabel(testProject, testLabel.getId());
        optionalLabel = gitLabApi.getLabelsApi().getOptionalProjectLabel(testProject, TEST_PROJECT_LABEL);
        assertFalse(optionalLabel.isPresent());

        List<Label> labels = gitLabApi.getLabelsApi().getProjectLabels(testProject);
        assertFalse(labels.stream().map(Label::getName).anyMatch(s -> TEST_PROJECT_LABEL.equals(s)));
    }

    @Test
    public void testCreateAndUpdateProjectLabel() throws GitLabApiException {

	Label labelConfig = new Label().withName(TEST_PROJECT_LABEL_1).withColor("#FF0000");
	Label testLabel = gitLabApi.getLabelsApi().createProjectLabel(testProject, labelConfig);
        assertNotNull(testLabel);
        assertEquals(TEST_PROJECT_LABEL_1, testLabel.getName());
        assertEquals("#FF0000", testLabel.getColor());

        try {
            labelConfig = new Label().withName(TEST_PROJECT_LABEL_1).withColor("#000000");
            gitLabApi.getLabelsApi().updateProjectLabel(testProject, testLabel, labelConfig);
            Optional<Label> optionalLabel = gitLabApi.getLabelsApi().getOptionalProjectLabel(testProject, TEST_PROJECT_LABEL_1);
            assertTrue(optionalLabel.isPresent());
            assertEquals("#000000", optionalLabel.get().getColor());
        } finally {
            try {
                gitLabApi.getLabelsApi().deleteProjectLabel(testProject, testLabel.getId());
            } catch (Exception ignore) {
            }
        }
    }

    @Test
    public void testCreateAndDeleteGroupLabel() throws GitLabApiException {

	Label labelConfig = new Label().withName(TEST_GROUP_LABEL).withColor("#FF0000");
	Label testLabel = gitLabApi.getLabelsApi().createGroupLabel(testGroup, labelConfig);
        assertNotNull(testLabel);
        assertEquals(TEST_GROUP_LABEL, testLabel.getName());

        Optional<Label> optionalLabel = gitLabApi.getLabelsApi().getOptionalGroupLabel(testGroup, TEST_GROUP_LABEL);
        assertTrue(optionalLabel.isPresent());
        assertEquals(TEST_GROUP_LABEL, optionalLabel.get().getName());
        assertEquals("#FF0000", optionalLabel.get().getColor());

        Stream<Label> labelsStream = gitLabApi.getLabelsApi().getGroupLabelsStream(testGroup);
        assertTrue(labelsStream.map(Label::getName).anyMatch(s -> TEST_GROUP_LABEL.equals(s)));

        gitLabApi.getLabelsApi().deleteGroupLabel(testGroup, testLabel.getId());
        optionalLabel = gitLabApi.getLabelsApi().getOptionalGroupLabel(testGroup, TEST_GROUP_LABEL);
        assertFalse(optionalLabel.isPresent());

        List<Label> labels = gitLabApi.getLabelsApi().getGroupLabels(testGroup);
        assertFalse(labels.stream().map(Label::getName).anyMatch(s -> TEST_GROUP_LABEL.equals(s)));
    }

    @Test
    public void testCreateAndUpdateGroupLabel() throws GitLabApiException {

	Label labelConfig = new Label().withName(TEST_GROUP_LABEL_1).withColor("#FF0000");
	Label testLabel = gitLabApi.getLabelsApi().createGroupLabel(testGroup, labelConfig);
        assertNotNull(testLabel);
        assertEquals(TEST_GROUP_LABEL_1, testLabel.getName());
        assertEquals("#FF0000", testLabel.getColor());

        try {
            labelConfig = new Label().withName(TEST_GROUP_LABEL_1).withColor("#000000");
            gitLabApi.getLabelsApi().updateGroupLabel(testGroup, testLabel, labelConfig);
            Optional<Label> optionalLabel = gitLabApi.getLabelsApi().getOptionalGroupLabel(testGroup, TEST_GROUP_LABEL_1);
            assertTrue(optionalLabel.isPresent());
            assertEquals("#000000", optionalLabel.get().getColor());
        } finally {
            try {
                gitLabApi.getLabelsApi().deleteGroupLabel(testGroup, testLabel.getId());
            } catch (Exception ignore) {
            }
        }
    }
}
