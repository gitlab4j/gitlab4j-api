package org.gitlab4j.api;

import static org.junit.Assert.*;

import org.gitlab4j.api.GitLabApi.ApiVersion;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPersonalSnippetApi {

    private static final String TEST_HOST_URL;
    private static final String TEST_PRIVATE_TOKEN;
    
    static {
        TEST_HOST_URL = TestUtils.getProperty("TEST_HOST_URL");
        TEST_PRIVATE_TOKEN = TestUtils.getProperty("TEST_PRIVATE_TOKEN");
    }

    private static final String TEST_SNIPPET_TITLE_PREFIX = "Test Snippet: ";
    private static GitLabApi gitLabApi;
	
    @BeforeClass
    public static void setup() {

        String problems = "";

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().isEmpty()) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (TEST_PRIVATE_TOKEN == null || TEST_PRIVATE_TOKEN.trim().isEmpty()) {
            problems += "TEST_PRIVATE_TOKEN cannot be empty\n";
        }

        if (problems.isEmpty()) {
            gitLabApi = new GitLabApi(ApiVersion.V4, TEST_HOST_URL, TEST_PRIVATE_TOKEN);
        } else {
            System.err.print(problems);
        }

        deleteAllPersonalTestSnippets();
    }
    
    public static void deleteAllPersonalTestSnippets() {
    	
    }
    
	@Test
	public void testCreate() {
		assertEquals(TEST_HOST_URL, "http://172.19.0.4/");
		assertNotNull(gitLabApi.getPersonalSnippetApi());
	}

}
