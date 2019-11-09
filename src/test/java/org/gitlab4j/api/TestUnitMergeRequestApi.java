package org.gitlab4j.api;

import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Collections;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.MergeRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestUnitMergeRequestApi {

    @Mock private GitLabApi mockGitLabApi;
    @Mock private GitLabApiClient mockedGitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse mockedResponse;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockedResponse = new MockResponse(MergeRequest.class, "merge-request.json", null);
        when(mockGitLabApi.getApiClient()).thenReturn(mockedGitLabApiClient);

        when(mockedGitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(mockedGitLabApiClient.put(attributeCaptor.capture(), Mockito.<Object>any()))
                .thenReturn(mockedResponse);
    }

    @Test
    public void whenAllArgumentsNull_thenNoAttributesSent() throws Exception {
        new MergeRequestApi(mockGitLabApi).updateMergeRequest(1, 2, null, null, null, null, null, null,
                null, null, null, null, null);
        assertEquals(0, attributeCaptor.getValue().size());
    }

    @Test
    public void falseBooleansAreSerializedCorrectly() throws Exception {
        new MergeRequestApi(mockGitLabApi).updateMergeRequest(1, 2, null, null, null, null, null, null,
                null, null, null, null, false);
        assertThat(attributeCaptor.getValue(),
                hasEntry("allow_collaboration", Collections.singletonList("false")));
    }

    @Test
    public void trueBooleansAreSerializedCorrectly() throws Exception {
        new MergeRequestApi(mockGitLabApi).updateMergeRequest(1, 2, null, null, null, null, null, null,
                null, null, null, null, true);
        assertThat(attributeCaptor.getValue(),
                hasEntry("allow_collaboration", Collections.singletonList("true")));
    }
}
