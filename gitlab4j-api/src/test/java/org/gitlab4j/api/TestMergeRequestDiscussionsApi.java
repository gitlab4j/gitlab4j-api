package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.Discussion;
import org.gitlab4j.models.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestMergeRequestDiscussionsApi implements Constants {

    @Mock
    private GitLabApi gitLabApi;

    @Mock
    private GitLabApiClient gitLabApiClient;

    @Captor
    private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;

    private MockResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks(this);
        response = new MockResponse(Discussion.class, null, "merge-request-discussions.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.any(Object[].class)))
                .thenReturn(response);
    }

    @Test
    public void testGetMergeRequestDiscussionsByList() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getMergeRequestDiscussions(1L, 1L);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }

    @Test
    public void testGetMergeRequestDiscussionsByListMaxItems() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getMergeRequestDiscussions(1L, 1L, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }

    @Test
    public void testGetMergeRequestDiscussionsByPager() throws Exception {
        Pager<Discussion> discussions = new DiscussionsApi(gitLabApi).getMergeRequestDiscussionsPager(1L, 1L, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions.all(), "merge-request-discussions.json"));
    }

    @Test
    public void testGetMergeRequestDiscussionsByStream() throws Exception {
        Stream<Discussion> stream = new DiscussionsApi(gitLabApi).getMergeRequestDiscussionsStream(1L, 1L);
        assertNotNull(stream);
        List<Discussion> discussions = stream.collect(Collectors.toList());
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }
}
