package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.*;

import org.gitlab4j.api.models.*;
import org.gitlab4j.api.models.Package;
import org.gitlab4j.models.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@org.junit.jupiter.api.Disabled(
        "Integration tests are disabled, see https://github.com/gitlab4j/gitlab4j-api/issues/1165")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestPackageApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestPackageApi() {
        super();
    }

    @BeforeAll
    public static void setup() {
        gitLabApi = baseTestSetup();
        testProject = getTestProject();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
        assumeTrue(testProject != null);
    }

    @Disabled("need creation of a package through CI")
    @Test
    public void getPackagesStream() throws GitLabApiException {
        PackagesApi packagesApi = gitLabApi.getPackagesApi();
        PackageFilter filter = new PackageFilter()
                .withOrderBy(Constants.PackageOrderBy.CREATED_AT)
                .withSortOder(Constants.SortOrder.DESC);

        Optional<Package> lastPackage =
                packagesApi.getPackagesStream(testProject.getId(), filter).findAny();

        assertTrue(lastPackage.isPresent());
    }
}
