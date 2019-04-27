package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.junit.AfterClass;
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
 *
 * NOTE: &amp;FixMethodOrder(MethodSorters.NAME_ASCENDING) is very important to insure that testCreate() is executed first.
 */
@Category(IntegrationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRepositoryFileApi extends AbstractIntegrationTest {

    private static final String TEST_CONTENT = "This is some content to test file content 1234567890 !@#$%^&().";
    private static final String TEST_ADDITIONAL_CONTENT = "\n\nThis is an additional line";

    private static final String TEST_BRANCH_NAME = "feature/test_branch_for_files";
    private static final String TEST_FILEPATH = "test-file.txt";
    private static GitLabApi gitLabApi;

    public TestRepositoryFileApi() {
        super();
    }

    @BeforeClass
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        teardown();
    }

    @AfterClass
    public static void teardown() {
        if (gitLabApi != null) {

            try {
                Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);

                try {
                    gitLabApi.getRepositoryFileApi().deleteFile(project.getId(), TEST_FILEPATH, TEST_BRANCH_NAME, "Cleanup test files.");
                } catch (GitLabApiException ignore) {
                }

                try {
                    gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
                } catch (GitLabApiException ignore) {
                }

            } catch (GitLabApiException ignore) {
            }
        }
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }

    @Test
    public void testRawFileViaFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File file = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README", null);
        assertTrue(file.length() > 0);
        file.delete();

        file = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README", new File("."));
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    public void testRepositoryFileViaInputStream() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        InputStream in = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README");

        Path target = Files.createTempFile(TEST_PROJECT_NAME + "-README", "");
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);

        assertTrue(target.toFile().length() > 0);
        Files.delete(target);
    }

    @Test
    public void testRepositoryFileGetFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        RepositoryFile fileInfo = gitLabApi.getRepositoryFileApi().getFileInfo(project.getId(), "README", "master");
        assertNotNull(fileInfo);
    }

    @Test
    public void testRepositoryFileGetOptionalFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Optional<RepositoryFile> fileInfo = gitLabApi.getRepositoryFileApi().getOptionalFileInfo(project.getId(), "README", "master");
        assertNotNull(fileInfo.get());

        fileInfo = gitLabApi.getRepositoryFileApi().getOptionalFileInfo(project.getId(), "I-DONT-EXIST", "master");
        assertFalse(fileInfo.isPresent());

        GitLabApiException e = GitLabApi.getOptionalException(fileInfo);
        assertNotNull(e);
        assertEquals(404, e.getHttpStatus());
    }

    @Test
    public void testCreateFileAndDeleteFile() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Branch branch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        RepositoryFile file = new RepositoryFile();
        file.setFilePath(TEST_FILEPATH);
        file.setContent(TEST_CONTENT);
        RepositoryFile createdFile = gitLabApi.getRepositoryFileApi().createFile(project.getId(), file, TEST_BRANCH_NAME, "Testing createFile().");
        assertNotNull(createdFile);

        gitLabApi.getRepositoryFileApi().deleteFile(project.getId(), TEST_FILEPATH, TEST_BRANCH_NAME, "Testing deleteFile().");
        gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
    }

    @Test
    public void testCreateFileWithEmptyContent() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Branch branch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        RepositoryFile file = new RepositoryFile();
        file.setFilePath(TEST_FILEPATH);
        file.setContent("");
        RepositoryFile createdFile = gitLabApi.getRepositoryFileApi().createFile(project.getId(), file, TEST_BRANCH_NAME, "Testing createFile().");
        assertNotNull(createdFile);

        gitLabApi.getRepositoryFileApi().deleteFile(project.getId(), TEST_FILEPATH, TEST_BRANCH_NAME, "Testing deleteFile().");
        gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
    }

    @Test
    public void testUpdateFile() throws GitLabApiException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Branch branch = gitLabApi.getRepositoryApi().createBranch(project.getId(), TEST_BRANCH_NAME, "master");
        assertNotNull(branch);

        RepositoryFile file = new RepositoryFile();
        file.setFilePath(TEST_FILEPATH);
        file.setContent(TEST_CONTENT);
        RepositoryFile createdFile = gitLabApi.getRepositoryFileApi().createFile(project.getId(), file, TEST_BRANCH_NAME, "Testing createFile().");
        assertNotNull(createdFile);

        Optional<RepositoryFile> optionalFile = gitLabApi.getRepositoryFileApi().getOptionalFile(project, TEST_FILEPATH, TEST_BRANCH_NAME);
        assertTrue(optionalFile.isPresent());
        RepositoryFile newFile = optionalFile.get();
        assertEquals(TEST_CONTENT, newFile.getDecodedContentAsString());

        String newContent = TEST_CONTENT + TEST_ADDITIONAL_CONTENT;
        file = new RepositoryFile();
        file.setFilePath(TEST_FILEPATH);
        file.encodeAndSetContent(newContent);
        gitLabApi.getRepositoryFileApi().updateFile(project.getId(), file, TEST_BRANCH_NAME, "Testing updateFile().");

        optionalFile = gitLabApi.getRepositoryFileApi().getOptionalFile(project, TEST_FILEPATH, TEST_BRANCH_NAME);
        assertTrue(optionalFile.isPresent());
        RepositoryFile updatedFile = optionalFile.get();
        assertEquals(newContent, updatedFile.getDecodedContentAsString());

        gitLabApi.getRepositoryFileApi().deleteFile(project.getId(), TEST_FILEPATH, TEST_BRANCH_NAME, "Testing deleteFile().");
        gitLabApi.getRepositoryApi().deleteBranch(project.getId(), TEST_BRANCH_NAME);
    }
}
