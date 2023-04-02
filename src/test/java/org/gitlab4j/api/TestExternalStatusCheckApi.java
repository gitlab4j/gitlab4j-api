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

import org.gitlab4j.api.models.ExternalStatusCheck;
import org.gitlab4j.api.models.ExternalStatusCheckStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestExternalStatusCheckApi implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    @BeforeEach
    public void setUp() throws Exception {
        openMocks(this);
    }

    @Test
    public void testGetExternalStatusChecks() throws Exception {
        initGetExternalStatusChecks();
        List<ExternalStatusCheck> result = new ExternalStatusCheckApi(gitLabApi).getExternalStatusChecks(6L);
        assertNotNull(result);
        assertTrue(compareJson(result, "external-status-checks.json"));
    }

    @Test
    public void testGetExternalStatusChecksByPager() throws Exception {
        initGetExternalStatusChecks();
        Pager<ExternalStatusCheck> pager = new ExternalStatusCheckApi(gitLabApi).getExternalStatusChecks(6L, 20);
        assertNotNull(pager);
        assertTrue(compareJson(pager.all(), "external-status-checks.json"));
    }

    @Test
    public void testGetExternalStatusChecksByStream() throws Exception {
        initGetExternalStatusChecks();
        Stream<ExternalStatusCheck> stream = new ExternalStatusCheckApi(gitLabApi).getExternalStatusChecksStream(6L);
        assertNotNull(stream);
        List<ExternalStatusCheck> list = stream.collect(Collectors.toList());
        assertTrue(compareJson(list, "external-status-checks.json"));
    }

    private void initGetExternalStatusChecks() throws Exception, IOException {
        response = new MockResponse(ExternalStatusCheck.class, null, "external-status-checks.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }

    @Test
    public void testGetExternalStatusCheckStatuses() throws Exception {
        initGetExternalStatusCheckStatuses();
        List<ExternalStatusCheckStatus> result = new ExternalStatusCheckApi(gitLabApi).getExternalStatusCheckStatuses(6L, 23L);
        assertNotNull(result);
        assertTrue(compareJson(result, "external-status-check-statuses.json"));
    }

    @Test
    public void testGetExternalStatusCheckStatusesByPager() throws Exception {
        initGetExternalStatusCheckStatuses();
        Pager<ExternalStatusCheckStatus> pager = new ExternalStatusCheckApi(gitLabApi).getExternalStatusCheckStatuses(6L, 23L, 20);
        assertNotNull(pager);
        assertTrue(compareJson(pager.all(), "external-status-check-statuses.json"));
    }

    @Test
    public void testGetExternalStatusCheckStatusesByStream() throws Exception {
        initGetExternalStatusCheckStatuses();
        Stream<ExternalStatusCheckStatus> stream = new ExternalStatusCheckApi(gitLabApi).getExternalStatusCheckStatusesStream(6L, 23L);
        assertNotNull(stream);
        List<ExternalStatusCheckStatus> list = stream.collect(Collectors.toList());
        assertTrue(compareJson(list, "external-status-check-statuses.json"));
    }

    private void initGetExternalStatusCheckStatuses() throws Exception, IOException {
        response = new MockResponse(ExternalStatusCheckStatus.class, null, "external-status-check-statuses.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }
}
