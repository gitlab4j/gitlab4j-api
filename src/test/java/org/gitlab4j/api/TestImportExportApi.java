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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.util.Optional;

import org.gitlab4j.api.models.ExportStatus;
import org.gitlab4j.api.models.ImportStatus;
import org.gitlab4j.api.models.Project;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestImportExportApi extends AbstractIntegrationTest {

    private static final String TEST_IMPORT_PROJECT_NAME = "test-import-project";
    private static GitLabApi gitLabApi;
    private static Project testProject;

    public TestImportExportApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();
        testProject = getTestProject();

        deleteAllTestProjects();
    }

    @AfterAll
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

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testExportDownloadAndImport() throws GitLabApiException {

        // Arrange
        assumeTrue(testProject != null);

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

            if (retries >= 6) {
                System.out.println("aborting!");
                fail("Project export is taking too long, failing test.");
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }

            retries++;
        }

        File exportDownload = null;
        try {

            System.out.println("Downloading exported project");
            exportDownload = gitLabApi.getImportExportApi().downloadExport(testProject, null);
            assertNotNull(exportDownload);
            assertTrue(exportDownload.length() > 2000, "length is not as expected. Current value: " + exportDownload.length());

            ImportStatus importStatus = gitLabApi.getImportExportApi().startImport(null, exportDownload,
                    TEST_IMPORT_PROJECT_NAME, true, null);
            assertNotNull(importStatus);
            Long newProjectId = importStatus.getId();

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

                if (retries >= 6) {
                    System.out.println("aborting!");
                    fail("Project import is taking too long, failing test.");
                }

                try {
                    Thread.sleep(10000);
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
