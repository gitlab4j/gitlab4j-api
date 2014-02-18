package com.messners.gitlab.api;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestCreateProjectAndMergeRequest {
	
	// The following needs to be set to your test repository
	private static final String TEST_HOST_URL = "https://your.gitlab.host.url";
	private static final String TEST_PRIVATE_TOKEN = "YOUR PRIVATE TOKEN";
	
	private static GitLabApi gitLabApi;
	
	public TestCreateProjectAndMergeRequest () {
		super();
	}
	
	@BeforeClass
	public static void setup () {
		gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);
	}
	
	
	@Test
	public void testCreateProject () {
			
		 try {
			 
			 Project newProject = new Project();
			 newProject.setName("test-gitlab-api-project");
			 Project project = gitLabApi.createProject(newProject);
			 assertTrue(project != null);
			 assertTrue(project.getName().equals(newProject.getName()));
			 
			 gitLabApi.deleteProject(project);
			 
		} catch (Exception e) {
			System.err.println("Check TEST_HOST_URL and TEST_PRIVATE_TOKEN, they are probably not set.");		
		}
	}
}
