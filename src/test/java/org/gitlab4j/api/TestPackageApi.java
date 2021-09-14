package org.gitlab4j.api;

import org.gitlab4j.api.models.*;
import org.gitlab4j.api.models.Package;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

@Category(IntegrationTest.class)
public class TestPackageApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestPackageApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void getPackagesStream() throws GitLabApiException {
        PackagesApi packagesApi = gitLabApi.getPackagesApi();

        assumeNotNull(packagesApi);

        Optional<Package> packageOptional = packagesApi.getPackagesStream(testProject.getId()).findAny();

        assertTrue(packageOptional.isPresent());
    }
}
