package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.IOException;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestTopicsApi implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks(this);
    }

    @Test
    public void testGetTopic() throws Exception {
        initGetTopic();
        Topic result = new TopicsApi(gitLabApi).getTopic(1);
        assertNotNull(result);
        assertTrue(compareJson(result, "topic.json"));
    }

    private void initGetTopic() throws Exception, IOException {
        response = new MockResponse(Topic.class, "topic.json", null);
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }
}
