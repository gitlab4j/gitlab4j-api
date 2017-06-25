package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.util.List;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.gitlab4j.api.models.Namespace;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
  * In order for these tests to run you must set the following properties in test-gitlab4j.properties
 * 
 * TEST_NAMESPACE
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * 
 * If any of the above are NULL, all tests in this class will be skipped.
 *
 */
public class TestNamespaceApi {

    // The following needs to be set to your test repository

    private static final String TEST_NAMESPACE;
    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    static {
        TEST_NAMESPACE = TestUtils.getProperty("TEST_NAMESPACE");
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static GitLabApi gitLabApi;

    public TestNamespaceApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        String problems = "";
        if (TEST_NAMESPACE == null || TEST_NAMESPACE.trim().length() == 0) {
            problems += "TEST_NAMESPACE cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().length() == 0) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().length() == 0) {
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
    public void testGetNamespaces() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().getNamespaces();
        assertNotNull(namespaces);
        assertEquals(TEST_NAMESPACE, namespaces.get(0).getName());
    }

    @Test
    public void testGetNamespacesViaPager() throws GitLabApiException {
        Pager<Namespace> pager = gitLabApi.getNamespaceApi().getNamespaces(10);
        assertNotNull(pager);
        assertEquals(TEST_NAMESPACE, pager.next().get(0).getName());
    }

    @Test
    public void testGetNamespacesByPage() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().getNamespaces(1, 10);
        assertNotNull(namespaces);
        assertEquals(TEST_NAMESPACE, namespaces.get(0).getName());
    }

    @Test
    public void testFindNamespaces() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces(TEST_NAMESPACE);
        assertNotNull(namespaces);
        assertEquals(TEST_NAMESPACE, namespaces.get(0).getName());
    }

    @Test
    public void testFindNamespacesByPage() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces(TEST_NAMESPACE, 1, 10);
        assertNotNull(namespaces);
        assertEquals(TEST_NAMESPACE, namespaces.get(0).getName());
    }

    @Test
    public void testFindNamespacesViaPager() throws GitLabApiException {
        Pager<Namespace> pager = gitLabApi.getNamespaceApi().findNamespaces(TEST_NAMESPACE, 10);
        assertNotNull(pager);
        assertEquals(TEST_NAMESPACE, pager.next().get(0).getName());
    }
}
