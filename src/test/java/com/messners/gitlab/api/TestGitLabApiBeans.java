package com.messners.gitlab.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGitLabApiBeans {
	
	private static JacksonJsonConfig jacksonJsonConfig;
	
	public TestGitLabApiBeans () {
		super();
	}
	
	@BeforeClass
	public static void setup () {
		jacksonJsonConfig = new JacksonJsonConfig();
	}
	
	@Test
	public void testBranch () {

		 try {
			 Branch branch = makeFakeApiCall(Branch.class, "branch");
			 assertTrue(branch != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testDiff () {
		
		try {			
			 Diff diff = makeFakeApiCall(Diff.class, "diff");
			 assertTrue(diff != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testEvent () {
		
		try {			
			 Event event = makeFakeApiCall(Event.class, "event");
			 assertTrue(event != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testGroup () {
		
		try {			
			 Group group = makeFakeApiCall(Group.class, "group");
			 assertTrue(group != null);			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testIssue () {
			
		 try {
			 Issue issue = makeFakeApiCall(Issue.class, "issue");
			 assertTrue(issue != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testKey () {
			
		 try {
			 Key key = makeFakeApiCall(Key.class, "key");
			 assertTrue(key != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMember () {
			
		 try {
			 Member member = makeFakeApiCall(Member.class, "member");
			 assertTrue(member != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMergeRequestComment () {
			
		 try {
			 MergeRequestComment mergeRequestComment = makeFakeApiCall(MergeRequestComment.class, "merge-request-comment");
			 assertTrue(mergeRequestComment != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}	

	@Test
	public void testMergeRequest () {
			
		 try {
			 MergeRequest mergeRequest = makeFakeApiCall(MergeRequest.class, "merge-request");
			 assertTrue(mergeRequest != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testMilestone () {
			
		 try {
			 Milestone milestone = makeFakeApiCall(Milestone.class, "milestone");
			 assertTrue(milestone != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testNote () {
			
		 try {
			 Note note = makeFakeApiCall(Note.class, "note");
			 assertTrue(note != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testProject () {
			
		 try {
			 Project project = makeFakeApiCall(Project.class, "project");
			 assertTrue(project != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testProjectSnippet () {
			
		 try {
			 ProjectSnippet projectSnippet = makeFakeApiCall(ProjectSnippet.class, "project-snippet");
			 assertTrue(projectSnippet != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testSession () {
			
		 try {
			 Session session = makeFakeApiCall(Session.class, "session");
			 assertTrue(session != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testSystemHook () {
			
		 try {
			 SystemHook systemHook = makeFakeApiCall(SystemHook.class, "SystemHook");
			 assertTrue(systemHook != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testTag () {
			
		 try {
			 Tag tag = makeFakeApiCall(Tag.class, "tag");
			 assertTrue(tag != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Test
	public void testTree () {
			
		 try {		

			InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream("tree.json"));
			ObjectMapper objectMapper = jacksonJsonConfig.getContext(null);	 
			List<TreeItem> tree = objectMapper.readValue(reader, new TypeReference<List<TreeItem>>(){});
			assertTrue(tree != null);
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	

	@Test
	public void testUser () {
			
		 try {
			 User user = makeFakeApiCall(User.class, "user");
			 assertTrue(user != null);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	private <T> T makeFakeApiCall (Class<T> returnType, String file) throws JsonParseException, JsonMappingException, IOException {
		
		InputStreamReader reader = new InputStreamReader(GitLabApi.class.getResourceAsStream(file + ".json"));
		ObjectMapper objectMapper = jacksonJsonConfig.getContext(returnType);
		return (objectMapper.readValue(reader,  returnType));	
	}
}
