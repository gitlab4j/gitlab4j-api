package org.gitlab4j.api;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.gitlab4j.api.models.Metadata;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@Disabled("Required Gitlab version not less then 15.6")
public class TestMetadataApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;
    private static User currentUser;

    public TestMetadataApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
        currentUser = getCurrentUser();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testGetMetadata() throws GitLabApiException {
        Metadata metadata = gitLabApi.getMetadataApi().getMetadata();
        System.out.println("METADATA +\n" + metadata);
    }


}
