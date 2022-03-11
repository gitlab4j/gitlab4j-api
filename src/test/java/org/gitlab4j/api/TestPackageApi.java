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
        assumeNotNull(testProject);
    }

    @Test
    public void getPackagesStream() throws GitLabApiException {
        PackagesApi packagesApi = gitLabApi.getPackagesApi();
        PackageFilter filter = new PackageFilter()
            .withOrderBy(Constants.PackageOrderBy.CREATED_AT)
            .withSortOder(Constants.SortOrder.DESC);



        Optional<Package> lastPackage = packagesApi.getPackagesStream(testProject.getId(),filter).findAny();

        assertTrue(lastPackage.isPresent());
    }
}
