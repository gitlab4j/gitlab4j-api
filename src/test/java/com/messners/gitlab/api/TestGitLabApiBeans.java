package com.messners.gitlab.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.BeforeClass;
import org.junit.Test;

import com.messners.gitlab.api.models.Branch;
import com.messners.gitlab.api.models.Diff;
import com.messners.gitlab.api.models.Event;
import com.messners.gitlab.api.models.Group;
import com.messners.gitlab.api.models.Issue;
import com.messners.gitlab.api.models.Key;
import com.messners.gitlab.api.models.Member;
import com.messners.gitlab.api.models.MergeRequest;
import com.messners.gitlab.api.models.MergeRequestComment;
import com.messners.gitlab.api.models.Milestone;
import com.messners.gitlab.api.models.Note;
import com.messners.gitlab.api.models.Project;
import com.messners.gitlab.api.models.ProjectHook;
import com.messners.gitlab.api.models.ProjectSnippet;
import com.messners.gitlab.api.models.Session;
import com.messners.gitlab.api.models.SystemHook;
import com.messners.gitlab.api.models.Tag;
import com.messners.gitlab.api.models.TreeItem;
import com.messners.gitlab.api.models.User;

public class TestGitLabApiBeans {
	
	private static JacksonJson jacksonJson;
	
	public TestGitLabApiBeans () {
		super();
	}
	
	@BeforeClass
	public static void setup () {
		jacksonJson = new JacksonJson();
	}
	
	@Test
	public void testBranch () {

		 try {
			 Branch branch = makeFakeApiCall(Branch.class, "branch");
			 assertTrue(compareJson(branch, "branch"));
			 
			 branch = makeFakeApiCall(Branch.class, "bad-branch");
			 assertTrue(!Branch.isValid(branch));
			 
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testDiff () {
		
		try {			
			 Diff diff = makeFakeApiCall(Diff.class, "diff");
			 assertTrue(compareJson(diff, "diff"));			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testEvent () {
		
		try {			
			 Event event = makeFakeApiCall(Event.class, "event");
			 assertTrue(compareJson(event, "event"));			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testGroup () {
		
		try {			
			 Group group = makeFakeApiCall(Group.class, "group");
			 assertTrue(compareJson(group, "group"));			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testIssue () {
			
		 try {
			 Issue issue = makeFakeApiCall(Issue.class, "issue");
			 assertTrue(compareJson(issue, "issue"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testProjectHook () {
			
		 try {
			 ProjectHook hook = makeFakeApiCall(ProjectHook.class, "hook");
			 assertTrue(compareJson(hook, "hook"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testKey () {
			
		 try {
			 Key key = makeFakeApiCall(Key.class, "key");
			 assertTrue(compareJson(key, "key"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMember () {
			
		 try {
			 Member member = makeFakeApiCall(Member.class, "member");
			 assertTrue(compareJson(member, "member"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMergeRequestComment () {
			
		 try {
			 MergeRequestComment mergeRequestComment = makeFakeApiCall(MergeRequestComment.class, "merge-request-comment");
			 assertTrue(compareJson(mergeRequestComment, "merge-request-comment"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}	

	@Test
	public void testMergeRequest () {
			
		 try {
			 MergeRequest mergeRequest = makeFakeApiCall(MergeRequest.class, "merge-request");
			 assertTrue(compareJson(mergeRequest, "merge-request"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMilestone () {
			
		 try {
			 Milestone milestone = makeFakeApiCall(Milestone.class, "milestone");
			 assertTrue(compareJson(milestone, "milestone"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testNote () {
			
		 try {
			 Note note = makeFakeApiCall(Note.class, "note");
			 assertTrue(compareJson(note, "note"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testProject () {
			
		 try {
			 Project project = makeFakeApiCall(Project.class, "project");
			 assertTrue(compareJson(project, "project"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testProjectSnippet () {
			
		 try {
			 ProjectSnippet projectSnippet = makeFakeApiCall(ProjectSnippet.class, "project-snippet");
			 assertTrue(compareJson(projectSnippet, "project-snippet"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testSession () {
			
		 try {
			 Session session = makeFakeApiCall(Session.class, "session");
			 assertTrue(compareJson(session, "session"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testSystemHook () {
			
		 try {
			 SystemHook systemHook = makeFakeApiCall(SystemHook.class, "system-hook");
			 assertTrue(compareJson(systemHook, "system-hook"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testTag () {
			
		 try {
			 Tag tag = makeFakeApiCall(Tag.class, "tag");
			 assertTrue(compareJson(tag, "tag"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testTree () {
			
		 try {		

			InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream("tree.json"));
			ObjectMapper objectMapper = jacksonJson.getContext(null);	 
			List<TreeItem> tree = objectMapper.readValue(reader, new TypeReference<List<TreeItem>>(){});
			assertTrue(compareJson(tree, "tree"));
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	

	@Test
	public void testUser () {
			
		 try {
			 User user = makeFakeApiCall(User.class, "user");
			 assertTrue(compareJson(user, "user"));
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	private <T> T makeFakeApiCall (Class<T> returnType, String file) throws JsonParseException, JsonMappingException, IOException {
		
		InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(file + ".json"));
		ObjectMapper objectMapper = jacksonJson.getContext(returnType);
		return (objectMapper.readValue(reader,  returnType));	
	}
	
	private <T> boolean compareJson (T apiObject, String file) throws IOException {
		
		InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(file + ".json"));
		String objectJson = jacksonJson.marshal(apiObject);
		JsonNode tree1 = jacksonJson.getObjectMapper().readTree(objectJson.getBytes());
		JsonNode tree2 = jacksonJson.getObjectMapper().readTree(reader);
		boolean sameJson = tree1.equals(tree2);
		return (sameJson);
	}
}
