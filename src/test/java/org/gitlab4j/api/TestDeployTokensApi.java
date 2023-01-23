package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.gitlab4j.api.Constants.DeployTokenScope;
import org.gitlab4j.api.models.DeployToken;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;
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
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestDeployTokensApi extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_USERNAME = HelperUtils.getProperty(USERNAME_KEY);

    private static GitLabApi gitLabApi;
    private static Group testGroup;
    private static Project testProject;

    public TestDeployTokensApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server
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

        if (TEST_USERNAME == null || TEST_USERNAME.trim().isEmpty()) {
            System.err.println("TEST_USER_NAME cannot be empty");
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
        assumeTrue(testGroup != null);
        assumeTrue(testProject != null);
    }

    @Test
    public void testCreateProject() throws GitLabApiException {

        assertNotNull(testProject);

        String name = "token-test-" + HelperUtils.getRandomInt(1000);

        int initialSize = gitLabApi.getDeployTokensApi().getProjectDeployTokens(testProject).size();

        DeployToken test = gitLabApi.getDeployTokensApi().addProjectDeployToken(
                testProject,
                name,
                Date.from(Instant.now().plus(1, ChronoUnit.DAYS)),
                "test-user-name", // Currently ignored by the API but correction is on the way
                                           // See: https://gitlab.com/gitlab-org/gitlab/-/issues/211963
                Collections.singletonList(DeployTokenScope.READ_REGISTRY));
        assertNotNull(test);
        assertEquals(test.getName(), name);
        assertNotNull(test.getToken());
        assertNotEquals(test.getToken(), "");
        gitLabApi.getDeployTokensApi().deleteProjectDeployToken(testProject, test.getId());

        assertEquals(initialSize, gitLabApi.getDeployTokensApi().getProjectDeployTokens(testProject).size());

    }

    @Test
    public void testCreateGroup() throws GitLabApiException {

        assertNotNull(testGroup);

        String name = "token-test-" + HelperUtils.getRandomInt(1000);

        int initialSize = gitLabApi.getDeployTokensApi().getGroupDeployTokens(testGroup).size();

        DeployToken test = gitLabApi.getDeployTokensApi().addGroupDeployToken(
                testGroup,
                name,
                Date.from(Instant.now().plus(1, ChronoUnit.DAYS)),
                "test-user-name", // Currently ignored by the API but correction is on the way
                // See: https://gitlab.com/gitlab-org/gitlab/-/issues/211963
                Arrays.asList(DeployTokenScope.READ_REPOSITORY, DeployTokenScope.READ_REGISTRY));
        assertNotNull(test);
        assertEquals(test.getName(), name);
        assertNotNull(test.getToken());
        assertNotEquals(test.getToken(), "");
        gitLabApi.getDeployTokensApi().deleteGroupDeployToken(testGroup, test.getId());

        List<DeployToken> deployTokens = gitLabApi.getDeployTokensApi().getGroupDeployTokens(testGroup);
        assertEquals(initialSize, deployTokens.size());

    }
}
