package org.gitlab4j.api;

import org.gitlab4j.test.GitLabContainer;
import org.gitlab4j.test.GitLabInvocationContextProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(GitLabInvocationContextProvider.class)
class UserApiIntegrationTest {

	
	@TestTemplate
	void testName(GitLabContainer container) throws Exception {
	
		System.out.println(container.version);
		
	}

}
