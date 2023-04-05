package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Optional;

import org.gitlab4j.api.models.Namespace;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestNamespaceApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    public TestNamespaceApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testGetNamespaces() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().getNamespaces();
        assertNotNull(namespaces);
        Optional<Namespace> matchingNamespace = namespaces.stream().
                filter(n -> n.getPath().equals(TEST_NAMESPACE)).findFirst();
        assertTrue(matchingNamespace.isPresent(), TEST_NAMESPACE + " not found!");
    }

    @Test
    public void testGetNamespacesViaPager() throws GitLabApiException {
        Pager<Namespace> pager = gitLabApi.getNamespaceApi().getNamespaces(10);
        assertNotNull(pager);

        while (pager.hasNext()) {
            List<Namespace> namespaces = pager.next();
            for (Namespace namespace : namespaces) {
                if (TEST_NAMESPACE.equals(namespace.getPath()))
                    return;
            }
        }

        fail(TEST_NAMESPACE + " not found!");
    }

    @Test
    public void testGetNamespacesByPage() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().getNamespaces(1, 10);
        assertNotNull(namespaces);
        Optional<Namespace> matchingNamespace = namespaces.stream().
                filter(n -> n.getPath().equals(TEST_NAMESPACE)).findFirst();
        assertTrue(matchingNamespace.isPresent(), TEST_NAMESPACE + " not found!");
    }

    @Test
    public void testFindNamespaces() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces(TEST_NAMESPACE);
        assertNotNull(namespaces);
        assertEquals(TEST_NAMESPACE, namespaces.get(0).getPath());
    }

    @Test
    public void testFindSubgroupNamespaces() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces(TEST_SUB_GROUP);
        assertNotNull(namespaces);
        assertEquals(TEST_SUB_GROUP, namespaces.get(0).getPath());
    }

    @Test
    public void testFindNamespacesByPage() throws GitLabApiException {
        List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces(TEST_NAMESPACE, 1, 10);
        assertNotNull(namespaces);
        assertEquals(TEST_NAMESPACE, namespaces.get(0).getPath());
    }

    @Test
    public void testFindNamespacesViaPager() throws GitLabApiException {
        Pager<Namespace> pager = gitLabApi.getNamespaceApi().findNamespaces(TEST_NAMESPACE, 10);
        assertNotNull(pager);
        assertEquals(TEST_NAMESPACE, pager.next().get(0).getPath());
    }
}
