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
import org.mockito.Spy;

public class TestSnippetDiscussionsApi implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Spy private FakeResponse response;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        response.init(Discussion.class,  null, "snippet-discussions.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }

    @Test
    public void testGetSnippetDiscussionsByList() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getSnippetDiscussions(1, 1);        
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "snippet-discussions.json"));
    }

    @Test
    public void testGetSnippetDiscussionsByListWithMaxItems() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getSnippetDiscussions(1, 1, 20);        
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "snippet-discussions.json"));
    }

    @Test
    public void testGetSnippetDiscussionsByPager() throws Exception {
        Pager<Discussion> discussions = new DiscussionsApi(gitLabApi).getSnippetDiscussionsPager(1, 1, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions.all(), "snippet-discussions.json"));
    }

    @Test
    public void testGetSnippetDiscussionsByStream() throws Exception {
        Stream<Discussion> stream = new DiscussionsApi(gitLabApi).getSnippetDiscussionsStream(1, 1);
        assertNotNull(stream);
        List<Discussion> discussions = stream.collect(Collectors.toList());
        assertTrue(compareJson(discussions, "snippet-discussions.json"));
    }
}
