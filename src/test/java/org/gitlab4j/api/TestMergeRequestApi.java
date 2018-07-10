package org.gitlab4j.api;

import org.gitlab4j.api.models.MergeRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TestMergeRequestApi {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Mock private Response response;

    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;

    @SuppressWarnings("deprecation")
    @Before
    public void setUp() throws Exception {
        initMocks(this);

        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);

        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.put(attributeCaptor.capture(), Mockito.<Object>anyVararg()))
                .thenReturn(response);

        when(response.getStatus()).thenReturn(200);
        when(response.getEntity()).thenReturn(new MergeRequest());
    }

    @Test
    public void whenAllArgumentsNull_thenNoAttributesSent() throws Exception {
        new MergeRequestApi(gitLabApi).updateMergeRequest(1, 2, null, null, null, null, null, null,
                null, null, null, null, null);
        assertEquals(0, attributeCaptor.getValue().size());
    }

    @Test
    public void falseBooleansAreSerializedCorrectly() throws Exception {
        new MergeRequestApi(gitLabApi).updateMergeRequest(1, 2, null, null, null, null, null, null,
                null, null, null, null, false);
        assertThat(attributeCaptor.getValue(),
                hasEntry("allow_collaboration", Collections.singletonList("false")));
    }

    @Test
    public void trueBooleansAreSerializedCorrectly() throws Exception {
        new MergeRequestApi(gitLabApi).updateMergeRequest(1, 2, null, null, null, null, null, null,
                null, null, null, null, true);
        assertThat(attributeCaptor.getValue(),
                hasEntry("allow_collaboration", Collections.singletonList("true")));
    }
}
