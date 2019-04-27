package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.util.Map;

import org.gitlab4j.api.models.FileUpload;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

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
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFileUpload extends AbstractIntegrationTest {

    // The following needs to be set to your test repository
    private static final String TEST_PROXY_URI;
    private static final String TEST_PROXY_USERNAME;
    private static final String TEST_PROXY_PASSWORD;
    static {
        TEST_PROXY_URI = TestUtils.getProperty("TEST_PROXY_URI");
        TEST_PROXY_USERNAME = TestUtils.getProperty("TEST_PROXY_USERNAME");
        TEST_PROXY_PASSWORD = TestUtils.getProperty("TEST_PROXY_PASSWORD");
    }

    private static GitLabApi gitLabApi;

    public TestFileUpload() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
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
    }
}
