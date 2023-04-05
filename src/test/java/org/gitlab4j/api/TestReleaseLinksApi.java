package org.gitlab4j.api;

import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestReleaseLinksApi implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks(this);
    }

    @Test
    public void testGetLinks() throws Exception {
        initGetLinks();
        List<Link> result = new ReleaseLinksApi(gitLabApi).getLinks(6L, "v1.0");
        assertNotNull(result);
        assertTrue(compareJson(result, "links.json"));
    }

    @Test
    public void testGetLinksByPager() throws Exception {
        initGetLinks();
        Pager<Link> pager = new ReleaseLinksApi(gitLabApi).getLinks(6L, "v1.0", 20);
        assertNotNull(pager);
        assertTrue(compareJson(pager.all(), "links.json"));
    }

    @Test
    public void testGetLinksByStream() throws Exception {
        initGetLinks();
        Stream<Link> stream = new ReleaseLinksApi(gitLabApi).getLinksStream(6L, "v1.0");
        assertNotNull(stream);
        List<Link> list = stream.collect(Collectors.toList());
        assertTrue(compareJson(list, "links.json"));
    }

    private void initGetLinks() throws Exception, IOException {
        response = new MockResponse(Link.class, null, "links.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }
}
