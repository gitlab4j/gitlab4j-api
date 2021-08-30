package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

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
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestRepositoryFileApi extends AbstractIntegrationTest {

    private static final String TEST_CONTENT = "This is some content to test file content 1234567890 !@#$%^&().";
    private static final String TEST_ADDITIONAL_CONTENT = "\n\nThis is an additional line";

    private static final String TEST_BRANCH_NAME = "feature/test_branch_for_files";
    private static final String TEST_FILEPATH = "test-file.txt";
    private static GitLabApi gitLabApi;

    public TestRepositoryFileApi() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server
        gitLabApi = baseTestSetup();

        teardown();
    }

    @AfterAll
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

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testRawFileViaFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File file = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README.md", tempDir);
        assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    public void testRepositoryFileViaInputStream() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        InputStream in = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "README.md");

        Path target = Files.createTempFile(TEST_PROJECT_NAME + "-README", "md");
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);

        assertTrue(target.toFile().length() > 0);
        Files.delete(target);
    }

    @Test
    public void testRepositoryFileGetFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        RepositoryFile fileInfo = gitLabApi.getRepositoryFileApi().getFileInfo(project.getId(), "README.md", "master");
        assertNotNull(fileInfo);
    }

    @Test
    public void testRepositoryFileGetOptionalFile() throws GitLabApiException, IOException {

        Project project = gitLabApi.getProjectApi().getProject(TEST_NAMESPACE, TEST_PROJECT_NAME);
        assertNotNull(project);

        Optional<RepositoryFile> fileInfo = gitLabApi.getRepositoryFileApi().getOptionalFileInfo(project.getId(), "README.md", "master");
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
