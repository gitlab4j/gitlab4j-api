package org.gitlab4j.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import jakarta.ws.rs.core.MultivaluedMap;
import java.util.Collections;
import org.gitlab4j.api.models.MergeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestUnitMergeRequestApi {

  @Mock private GitLabApi mockGitLabApi;
  @Mock private GitLabApiClient mockedGitLabApiClient;
  @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;

  @BeforeEach
  void setUp() throws Exception {
    BDDMockito.given(mockGitLabApi.getApiClient()).willReturn(mockedGitLabApiClient);
    BDDMockito.given(mockedGitLabApiClient.validateSecretToken(any())).willReturn(true);
    final MockResponse mockedResponse =
        new MockResponse(MergeRequest.class, "merge-request.json", null);
    BDDMockito.given(
            mockedGitLabApiClient.put(attributeCaptor.capture(), Mockito.any(Object[].class)))
        .willReturn(mockedResponse);
  }

  @Test
  void whenAllArgumentsNull_thenNoAttributesSent() throws Exception {
    // Given
    final MergeRequestApi mergeRequestApi = new MergeRequestApi(this.mockGitLabApi);

    // When
    mergeRequestApi.updateMergeRequest(
        1L, 2L, null, null, null, null, null, null, null, null, null, null, null);
    // Then
    assertEquals(0, attributeCaptor.getValue().size());
  }

  @Test
  void falseBooleansAreSerializedCorrectly() throws Exception {
    // Given
    final MergeRequestApi mergeRequestApi = new MergeRequestApi(this.mockGitLabApi);

    // When
    mergeRequestApi.updateMergeRequest(
        1L, 2L, null, null, null, null, null, null, null, null, null, null, false);
    // Then
    assertThat(
        attributeCaptor.getValue(),
        hasEntry("allow_collaboration", Collections.singletonList("false")));
  }

  @Test
  void trueBooleansAreSerializedCorrectly() throws Exception {
    // Given
    final MergeRequestApi mergeRequestApi = new MergeRequestApi(this.mockGitLabApi);

    // When
    mergeRequestApi.updateMergeRequest(
        1L, 2L, null, null, null, null, null, null, null, null, null, null, true);
    // Then
    assertThat(
        attributeCaptor.getValue(),
        hasEntry("allow_collaboration", Collections.singletonList("true")));
  }
}
