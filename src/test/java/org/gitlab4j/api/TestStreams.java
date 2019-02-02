package org.gitlab4j.api;

import static java.util.Comparator.comparing;
import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

public class TestStreams implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Spy private FakeResponse response;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;

    static private List<User> sortedUsers;

    @BeforeClass
    public static void setupClass() throws Exception {

        // Get a list of users sorted by username, we use this as thye source of truth for the asserts
        sortedUsers = JsonUtils.unmarshalResourceList(User.class, "user-list.json");
        sortedUsers.sort(comparing(User::getUsername));
    }

    @Before
    public void setup() throws Exception {
        initMocks(this);
        response.init(User.class, null, "user-list.json");
        when(gitLabApi.getApiClient()).thenReturn(gitLabApiClient);
        when(gitLabApiClient.validateSecretToken(any())).thenReturn(true);
        when(gitLabApiClient.get(attributeCaptor.capture(), Mockito.<Object>any())).thenReturn(response);
    }

    @Test
    public void testStream() throws Exception {

        // Arrange
        Stream<User> stream = new UserApi(gitLabApi).getUsersStream();

        // Assert
        assertNotNull(stream);
        List<User> users = stream.sorted(comparing(User::getUsername)).collect(toList());
        assertNotNull(users);

        assertEquals(users.size(), sortedUsers.size());
        for (int i = 0; i < users.size(); i++) {
            assertTrue(compareJson(sortedUsers.get(i), users.get(i)));
        }
    }

    @Test
    public void testParallelStream() throws Exception {

        // Arrange
        Stream<User> stream = new UserApi(gitLabApi).getUsersStream();

        // Assert
        assertNotNull(stream);
        List<User> users = stream.parallel().sorted(comparing(User::getUsername)).collect(toList());
        assertNotNull(users);

        assertEquals(users.size(), sortedUsers.size());
        for (int i = 0; i < users.size(); i++) {
            assertTrue(compareJson(sortedUsers.get(i), users.get(i)));
        }
    }
}
