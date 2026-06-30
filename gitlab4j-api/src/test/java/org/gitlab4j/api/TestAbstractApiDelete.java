package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Verifies that {@link AbstractApi#delete} releases its connection back to the pool.
 *
 * <p>Most delete callers are {@code void} and discard the returned {@link Response} without closing
 * it; with a pooling/Apache connector that leaks the connection and eventually exhausts the pool
 * (requests then block in {@code getPoolEntryBlocking}). The fix buffers the delete body,
 * which releases the connection while keeping the response readable for callers that consume it.
 */
@ExtendWith(MockitoExtension.class)
public class TestAbstractApiDelete {

    private static class TestApi extends AbstractApi {
        TestApi(GitLabApi gitLabApi) {
            super(gitLabApi);
        }
    }

    private TestApi setupApi(Response response) throws Exception {
        GitLabApi gitLabApi = mock(GitLabApi.class);
        GitLabApiClient apiClient = mock(GitLabApiClient.class);

        when(gitLabApi.getApiClient()).thenReturn(apiClient);
        when(apiClient.delete(nullable(MultivaluedMap.class), any(Object[].class))).thenReturn(response);
        when(apiClient.validateSecretToken(response)).thenReturn(true);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());

        return new TestApi(gitLabApi);
    }

    @Test
    public void shouldBufferEntityToReleaseConnectionWhenBodyPresent() throws Exception {
        Response response = mock(Response.class);
        when(response.hasEntity()).thenReturn(true);

        TestApi api = setupApi(response);
        Response result = api.delete(Response.Status.OK, null, "groups", 1L);

        assertSame(response, result);

        // The connection is released via bufferEntity() even though void callers discard the Response,
        // and the Response stays readable (not closed) for callers that read the deleted entity.
        verify(response).bufferEntity();
        verify(response, never()).close();
    }

    @Test
    public void shouldNotBufferWhenNoEntity() throws Exception {
        Response response = mock(Response.class);
        when(response.hasEntity()).thenReturn(false);

        TestApi api = setupApi(response);
        api.delete(Response.Status.OK, null, "groups", 1L);

        verify(response, never()).bufferEntity();
    }
}
