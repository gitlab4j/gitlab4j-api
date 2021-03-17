package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Discussion;
import org.gitlab4j.api.models.Note;
import org.gitlab4j.api.models.Position;
import org.gitlab4j.api.models.Position.PositionType;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//@Category(IntegrationTest.class)
public class TestDiscussionsApi extends AbstractIntegrationTest {

	private static GitLabApi gitLabApi;
	private static Project testProject;

	public TestDiscussionsApi() {
		super();
	}

	@BeforeClass
	public static void setup() {
		// Must setup the connection to the GitLab test server
		gitLabApi = baseTestSetup();
		testProject = getTestProject();

		if (!gitLabApi.getRepositoryFileApi().getOptionalFile(testProject, TEST_PROJECT_SUBDIRECTORY_PATH, "master")
			.isPresent()) {
			try {
				RepositoryFile repoFile = new RepositoryFile();
				repoFile.setFilePath(TEST_PROJECT_SUBDIRECTORY_PATH);
				repoFile.setContent("This is a test project used to test GitLab4J-API.");
				gitLabApi.getRepositoryFileApi().createFile(testProject, repoFile, "master", "Initial commit.");
			} catch (GitLabApiException ignore) {
			}
		}
	}

	@Before
	public void beforeMethod() {
		assumeNotNull(gitLabApi);
	}

	@Test
	public void testDeleteCommitDiscussion() throws GitLabApiException {

		List<Commit> commits = gitLabApi.getCommitsApi().getCommits(testProject.getId());

		Commit oneCommit = commits.get(commits.size() - 5);

		List<Discussion> commitDiscussions = gitLabApi.getDiscussionsApi().getCommitDiscussions(testProject.getId(),
			oneCommit.getId());

		assertNotNull(commitDiscussions);
		assertTrue(commitDiscussions.size() == 1);

		assertNotNull(commitDiscussions.get(0).getNotes());
		assertTrue(commitDiscussions.get(0).getNotes().size() == 1);

		gitLabApi.getDiscussionsApi().deleteCommitDiscussionNote(testProject.getId(),
			oneCommit.getId(), commitDiscussions.get(0).getId(), commitDiscussions.get(0).getNotes().get(0).getId());

		commitDiscussions = gitLabApi.getDiscussionsApi().getCommitDiscussions(testProject.getId(),
			oneCommit.getId());

		assertNotNull(commitDiscussions);
		assertTrue(commitDiscussions.size() == 0);

//		Commit lastCommit = commits.get(commits.size() - 1);
//
//		List<Diff> lastCommitDiffs = gitLabApi.getCommitsApi().getDiff(testProject.getId(), lastCommit.getId());
//		Diff lastCommitDiff = lastCommitDiffs.get(0);
//
//		Integer oldLine = null;
//		Integer newLine = null;
//
//		if (lastCommitDiff.getDeletedFile()) {
//			oldLine = 1;
//		} else {
//			newLine = 1;
//		}
//
//		Commit previousCommit = commits.get(commits.size() - 1);
//
//		Discussion discussion = gitLabApi.getDiscussionsApi().createCommitDiscussion(testProject.getId(),
//			lastCommit.getId(), "Test", null, null, new Position()
//				.withBaseSha(previousCommit.getId())
//				.withHeadSha(lastCommit.getId())
//				.withStartSha(previousCommit.getId())
//				.withPositionType(PositionType.TEXT)
//				.withOldPath(lastCommitDiff.getOldPath())
//				.withNewPath(lastCommitDiff.getNewPath())
//				.withOldLine(oldLine)
//				.withNewLine(newLine));
//
//		Discussion commitDiscussion = gitLabApi.getDiscussionsApi().getCommitDiscussion(testProject.getId(),
//			lastCommit.getId(), discussion.getId());
//
//		assertNotNull(commitDiscussion.getNotes());
//		assertTrue(commitDiscussion.getNotes().size() == 1);
//
//		Note note = gitLabApi.getDiscussionsApi().addCommitDiscussionNote(testProject.getId(),
//			lastCommit.getId(), discussion.getId(), "New note", new Date());
//
//
//		commitDiscussion = gitLabApi.getDiscussionsApi().getCommitDiscussion(testProject.getId(),
//			lastCommit.getId(), discussion.getId());
//
//		assertNotNull(commitDiscussion.getNotes());
//		assertTrue(commitDiscussion.getNotes().size() == 2);
//
//
//		gitLabApi.getDiscussionsApi().deleteCommitDiscussionNote(testProject.getId(),
//			lastCommit.getId(), discussion.getId(), note.getId());
//
//		commitDiscussion = gitLabApi.getDiscussionsApi().getCommitDiscussion(testProject.getId(),
//			lastCommit.getId(), discussion.getId());
//
//		assertNotNull(commitDiscussion.getNotes());
//		assertTrue(commitDiscussion.getNotes().size() == 1);

	}
}
