package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import jakarta.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.gitlab4j.api.models.Discussion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestIssueDiscussionsApi implements Constants {

  @Mock private GitLabApi gitLabApi;
  @Mock private GitLabApiClient gitLabApiClient;
  @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
  private MockResponse response;

  @BeforeEach
  public void setUp() throws Exception {
    openMocks(this);
    response = new MockResponse(Discussion.class, null, "issue-discussions.json");
    when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
    when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
    when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.any(Object[].class)))
        .thenReturn(response);
  }

  @Test
  public void testGetIssueDiscussionsByList() throws Exception {
    final List<Discussion> discussions = new DiscussionsApi(gitLabApi).getIssueDiscussions(1L, 1L);
    assertNotNull(discussions);
    assertTrue(compareJson(discussions, "issue-discussions.json"));
  }

  @Test
  public void testGetIssueDiscussionsByListWithMaxItems() throws Exception {
    final List<Discussion> discussions = new DiscussionsApi(gitLabApi).getIssueDiscussions(1L, 1L, 20);
    assertNotNull(discussions);
    assertTrue(compareJson(discussions, "issue-discussions.json"));
  }

  @Test
  public void testGetIssueDiscussionsByPager() throws Exception {
    final Pager<Discussion> discussions =
        new DiscussionsApi(gitLabApi).getIssueDiscussionsPager(1L, 1L, 20);
    assertNotNull(discussions);
    assertTrue(compareJson(discussions.all(), "issue-discussions.json"));
  }

  @Test
  public void testGetIssueDiscussionsByStream() throws Exception {
    final Stream<Discussion> stream = new DiscussionsApi(gitLabApi).getIssueDiscussionsStream(1L, 1L);
    assertNotNull(stream);
    final List<Discussion> discussions = stream.collect(Collectors.toList());
    assertTrue(compareJson(discussions, "issue-discussions.json"));
  }
}
