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

public class TestMergeRequestDiscussionsApi implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        response = new MockResponse(Discussion.class,  null,  "merge-request-discussions.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }

    @Test
    public void testGetMergeRequestDiscussionsByList() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getMergeRequestDiscussions(1, 1);        
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }

    @Test
    public void testGetMergeRequestDiscussionsByListMaxItems() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getMergeRequestDiscussions(1, 1, 20);        
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }

    @Test
    public void testGetMergeRequestDiscussionsByPager() throws Exception {
        Pager<Discussion> discussions = new DiscussionsApi(gitLabApi).getMergeRequestDiscussionsPager(1, 1, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions.all(), "merge-request-discussions.json"));
    }

    @Test
    public void testGetMergeRequestDiscussionsByStream() throws Exception {
        Stream<Discussion> stream = new DiscussionsApi(gitLabApi).getMergeRequestDiscussionsStream(1, 1);
        assertNotNull(stream);
        List<Discussion> discussions = stream.collect(Collectors.toList());
        assertTrue(compareJson(discussions, "merge-request-discussions.json"));
    }
}
