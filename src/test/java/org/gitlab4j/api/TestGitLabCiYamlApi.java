package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import org.gitlab4j.api.models.GitLabCiTemplate;
import org.gitlab4j.api.models.GitLabCiTemplateElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 *
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestGitLabCiYamlApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    @BeforeAll
    public static void setUp() {
        gitLabApi = baseTestSetup();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testGetAllCiYamlTemplates() throws GitLabApiException {
        List<GitLabCiTemplateElement> ciYamlTemplatesElements = gitLabApi.getGitLabCiYamlApi().getAllGitLabCiYamlTemplates();
        assertAll(
            () -> assertNotNull(ciYamlTemplatesElements),
            () -> assertTrue(ciYamlTemplatesElements.size() > 0)
        );
    }

    @Test
    public void testGetSingleCiYamlTemplate() throws GitLabApiException {
        List<GitLabCiTemplateElement> ciYamlTemplatesElements = gitLabApi.getGitLabCiYamlApi().getAllGitLabCiYamlTemplates();
        assumeTrue(ciYamlTemplatesElements != null);
        assumeTrue(ciYamlTemplatesElements.size() > 0);
        String templateKey = ciYamlTemplatesElements.get(0).getKey();
        GitLabCiTemplate ciYamlTemplate = gitLabApi.getGitLabCiYamlApi().getSingleGitLabCiYamlTemplate(templateKey);
        assertAll(
            () -> assertNotNull(ciYamlTemplate),
            () -> assertNotNull(ciYamlTemplate.getContent()),
            () -> assertEquals(templateKey, ciYamlTemplate.getName())
        );

    }

}
