package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.gitlab4j.api.models.Namespace;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
@Category(IntegrationTest.class)
public class TestNamespaceApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    public TestNamespaceApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testGetNamespaces() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().getNamespaces();
        assertNotNull(namespaces);
        for (Namespace namespace : namespaces) {
            if (TEST_NAMESPACE.equals(namespace.getName()))
                 return;
        }

        fail(TEST_NAMESPACE + " not found!");
    }

    @Test
    public void testGetNamespacesViaPager() throws GitLabApiException {
        Pager<Namespace> pager = gitLabApi.getNamespaceApi().getNamespaces(10);
        assertNotNull(pager);

        while (pager.hasNext()) {
            List<Namespace> namespaces = pager.next();
            for (Namespace namespace : namespaces) {
                if (TEST_NAMESPACE.equals(namespace.getName()))
                    return;
            }
        }

        fail(TEST_NAMESPACE + " not found!");
    }

    @Test
    public void testGetNamespacesByPage() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().getNamespaces(1, 10);
        assertNotNull(namespaces);
        for (Namespace namespace : namespaces) {
            if (TEST_NAMESPACE.equals(namespace.getName()))
                 return;
        }

        fail(TEST_NAMESPACE + " not found!");
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
