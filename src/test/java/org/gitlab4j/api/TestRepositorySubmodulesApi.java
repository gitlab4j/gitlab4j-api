package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.IOException;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.Commit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestRepositorySubmodulesApi implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks(this);
    }

    @Test
    public void testUpdateExistingSubmoduleReference() throws Exception {
        init();
        Commit result = new RepositorySubmodulesApi(gitLabApi).updateExistingSubmoduleReference(6L, "my-sub", "patch-1", "33e2ee8579fda5bc36accc9c6fbd0b4fefda9e30", "message");
        assertNotNull(result);
        assertTrue(compareJson(result, "commit.json"));
    }

    private void init() throws Exception, IOException {
        response = new MockResponse(Commit.class, "commit.json", null);
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.put(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }
}
