package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.util.Map;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.FileUpload;
import org.gitlab4j.api.models.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFileUpload {

    // The following needs to be set to your test repository
    private static final String TEST_PROJECT_NAME;
    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    private static final String TEST_PROXY_URI;
    private static final String TEST_PROXY_USERNAME;
    private static final String TEST_PROXY_PASSWORD;
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_PROJECT_NAME = TestUtils.getProperty("TEST_PROJECT_NAME");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
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

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().isEmpty()) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_PROJECT_NAME == null || TEST_PROJECT_NAME.trim().isEmpty()) {
            problems += "TEST_PROJECT_NAME cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }
    }

    @Before
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
    }
}
