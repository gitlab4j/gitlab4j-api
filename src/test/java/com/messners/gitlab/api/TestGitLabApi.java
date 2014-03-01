package com.messners.gitlab.api;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestGitLabApi {
	
	// The following needs to be set to your test repository
	private static final String TEST_HOST_URL = "https://your.gitlab.host.url";
	private static final String TEST_PRIVATE_TOKEN = "YOUR PRIVATE TOKEN";
	
	private static GitLabApi gitLabApi;
	
	public TestGitLabApi () {
		super();
	}
	
	@BeforeClass
	public static void setup () {
		gitLabApi = new GitLabApi(TEST_HOST_URL, TEST_PRIVATE_TOKEN);
	}
	
	@Test
	public void testProjects () {
			
		 try {
			 List<Project> projects = gitLabApi.getProjects();
			 assertTrue(projects != null);
		} catch (Exception e) {
			System.err.println("Check TEST_HOST_URL and TEST_PRIVATE_TOKEN, they are probably not set.");
		}
	}
	
	@Test
	public void testOwnedProjects () {
			
		 try {
			 List<Project> projects = gitLabApi.getOwnedProjects();
			 assertTrue(projects != null);
			 
		} catch (Exception e) {
			System.err.println("Check TEST_HOST_URL and TEST_PRIVATE_TOKEN, they are probably not set.");		
		}
	}
}
