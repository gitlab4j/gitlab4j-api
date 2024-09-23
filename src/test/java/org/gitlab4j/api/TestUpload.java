package org.gitlab4j.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import org.gitlab4j.api.models.FileUpload;
import org.gitlab4j.api.models.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
* In order for these tests to run you must set the following properties in test-gitlab4j.properties
 *
 * TEST_NAMESPACE
 * TEST_PROJECT_NAME
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@org.junit.jupiter.api.Disabled("Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestUpload extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_PROXY_URI = HelperUtils.getProperty("TEST_PROXY_URI");
    private static final String TEST_PROXY_USERNAME = HelperUtils.getProperty("TEST_PROXY_USERNAME");
    private static final String TEST_PROXY_PASSWORD = HelperUtils.getProperty("TEST_PROXY_PASSWORD");

    private static GitLabApi gitLabApi;

    public TestUpload() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testFileUpload() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File fileToUpload = new File("README.md");
        FileUpload fileUpload = gitLabApi.getProjectApi().uploadFile(project.getId(), fileToUpload, null);
        assertNotNull(fileUpload);
    }

    @Test
    public void testFileUploadWithMediaType() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File fileToUpload = new File("README.md");
        FileUpload fileUpload = gitLabApi.getProjectApi().uploadFile(project.getId(), fileToUpload, "text/markdown");
        assertNotNull(fileUpload);
    }

    @Test
    public void testFileUploadWithProxy() throws GitLabApiException {

        assumeTrue(TEST_PROXY_URI != null && TEST_PROXY_USERNAME != null && TEST_PROXY_PASSWORD != null);
        assumeTrue(TEST_PROXY_URI.length() > 0 && TEST_PROXY_USERNAME.length() > 0 && TEST_PROXY_PASSWORD.length() > 0);

        // Setup a GitLabApi instance to use a proxy
        Map<String, Object> clientConfig = ProxyClientConfig.createProxyClientConfig(TEST_PROXY_URI, TEST_PROXY_USERNAME, TEST_PROXY_PASSWORD);
        GitLabApi gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN, null, clientConfig);

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File fileToUpload = new File("README.md");
        FileUpload fileUpload = gitLabApi.getProjectApi().uploadFile(project.getId(), fileToUpload, null);
        assertNotNull(fileUpload);

        gitLabApi.close();
    }

    @Test
    public void testInputStreamUpload() throws GitLabApiException, FileNotFoundException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        String filename = "README.md";
        File fileToUpload = new File(filename);
        FileUpload fileUpload = gitLabApi.getProjectApi().uploadFile(
            project.getId(), new FileInputStream(fileToUpload), filename, null);

        assertNotNull(fileUpload);
        assertThat(fileUpload.getUrl(), endsWith(filename));
        assertThat(fileUpload.getAlt(), equalTo(filename));
    }

}
