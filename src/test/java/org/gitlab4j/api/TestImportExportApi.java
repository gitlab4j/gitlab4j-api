/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import java.io.File;
import java.util.Optional;

import org.gitlab4j.api.models.ExportStatus;
import org.gitlab4j.api.models.ImportStatus;
import org.gitlab4j.api.models.Project;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
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
public class TestImportExportApi extends AbstractIntegrationTest {

    private static final String TEST_IMPORT_PROJECT_NAME = "test-import-project";
    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestImportExportApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        deleteAllTestProjects();
    }

    @AfterClass
    public static void teardown() throws GitLabApiException {
        deleteAllTestProjects();
     }

     private static void deleteAllTestProjects() {
         if (gitLabApi == null) {
             return;
         }

         try {
             Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_IMPORT_PROJECT_NAME);
             gitLabApi.getProjectApi().deleteProject(project);
         } catch (GitLabApiException ignore) {}
     }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }


    @Test
    public void testScheduleExport() throws GitLabApiException {

        // Arrange
        assumeNotNull(testProject);

        // Act
        gitLabApi.getImportExportApi().scheduleExport(testProject);
        ExportStatus exportStatus = gitLabApi.getImportExportApi().getExportStatus(testProject);

        // Assert
        assertNotNull(exportStatus);
        ExportStatus.Status status = exportStatus.getExportStatus();
        assertNotEquals(ExportStatus.Status.NONE, status);
    }

    @Test
    public void testExportDownloadAndImport() throws GitLabApiException {

        // Arrange
        assumeNotNull(testProject);

        // Act
        gitLabApi.getImportExportApi().scheduleExport(testProject);

        // Wait up to 40 seconds for the export to complete
        System.out.print("Waiting for export to complete");
        int retries = 0;
        while (true) {

            System.out.print(".");
            ExportStatus exportStatus = gitLabApi.getImportExportApi().getExportStatus(testProject);
            if (exportStatus.getExportStatus() == ExportStatus.Status.FINISHED) {
                System.out.println("done");
                break;
            }

            if (retries >= 40) {
                System.out.println("aborting!");
                fail("Project export is taking too long, failing test.");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            retries++;
        }

        File exportDownload = null;
        try {

            System.out.println("Downloading exported project");
            exportDownload = gitLabApi.getImportExportApi().downloadExport(testProject, null);
            assertNotNull(exportDownload);
            assertTrue(exportDownload.length() > 10000);

            ImportStatus importStatus = gitLabApi.getImportExportApi().startImport(null, exportDownload,
                    TEST_IMPORT_PROJECT_NAME, true, null);
            assertNotNull(importStatus);
            Integer newProjectId = importStatus.getId();

            // Wait up to 40 seconds for the import to complete
            System.out.print("Waiting for import to complete");
            retries = 0;
            while (true) {

                System.out.print(".");
                importStatus = gitLabApi.getImportExportApi().getImportStatus(newProjectId);
                if (importStatus.getImportStatus() == ImportStatus.Status.FINISHED) {
                    System.out.println("done");
                    break;
                }

                if (retries >= 40) {
                    System.out.println("aborting!");
                    fail("Project import is taking too long, failing test.");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                retries++;
            }

            Optional<Project> newProject = gitLabApi.getProjectApi().getOptionalProject(newProjectId);
            assertTrue(newProject.isPresent());
            assertEquals(newProjectId, newProject.get().getId());
            assertEquals(TEST_IMPORT_PROJECT_NAME, newProject.get().getName());

        } finally {
            if (exportDownload != null) {
                exportDownload.delete();
            }
        }
    }
}
