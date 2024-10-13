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

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.Discussion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestIssueDiscussionsApi implements Constants {

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
        response = new MockResponse(Discussion.class, null, "issue-discussions.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any()))
                .thenReturn(response);
    }

    @Test
    public void testGetIssueDiscussionsByList() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getIssueDiscussions(1L, 1L);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "issue-discussions.json"));
    }

    @Test
    public void testGetIssueDiscussionsByListWithMaxItems() throws Exception {
        List<Discussion> discussions = new DiscussionsApi(gitLabApi).getIssueDiscussions(1L, 1L, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions, "issue-discussions.json"));
    }

    @Test
    public void testGetIssueDiscussionsByPager() throws Exception {
        Pager<Discussion> discussions = new DiscussionsApi(gitLabApi).getIssueDiscussionsPager(1L, 1L, 20);
        assertNotNull(discussions);
        assertTrue(compareJson(discussions.all(), "issue-discussions.json"));
    }

    @Test
    public void testGetIssueDiscussionsByStream() throws Exception {
        Stream<Discussion> stream = new DiscussionsApi(gitLabApi).getIssueDiscussionsStream(1L, 1L);
        assertNotNull(stream);
        List<Discussion> discussions = stream.collect(Collectors.toList());
        assertTrue(compareJson(discussions, "issue-discussions.json"));
    }
}
