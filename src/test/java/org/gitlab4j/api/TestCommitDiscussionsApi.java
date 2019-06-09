package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.Discussion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestCommitDiscussionsApi implements Constants {

    private static final String COMMIT_SHA = "abcdef1234567890";
    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        response = new MockResponse(Discussion.class,  null,  "commit-discussions.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }

    @Test
    public void testGetCommitDiscussionsByList() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getCommitDiscussions(1, COMMIT_SHA);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "commit-discussions.json"));
    }

    @Test
    public void testGetCommitDiscussionsByListWithMaxItems() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getCommitDiscussions(1, COMMIT_SHA, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "commit-discussions.json"));
    }

    @Test
    public void testGetCommitDiscussionsByPager() throws Exception {
        Pager<Discussion> discussions = new DiscussionsApi(gitLabApi).getCommitDiscussionsPager(1, COMMIT_SHA, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions.all(), "commit-discussions.json"));
    }

    @Test
    public void testGetCommitDiscussionsByStream() throws Exception {
        Stream<Discussion> stream = new DiscussionsApi(gitLabApi).getCommitDiscussionsStream(1, COMMIT_SHA);
        assertNotNull(stream);
        List<Discussion> discussions = stream.collect(Collectors.toList());
        assertTrue(compareJson(discussions, "commit-discussions.json"));
    }
}
