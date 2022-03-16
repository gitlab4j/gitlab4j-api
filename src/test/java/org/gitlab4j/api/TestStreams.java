package org.gitlab4j.api;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.gitlab4j.api.JsonUtils.compareJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.MultivaluedMap;

import org.gitlab4j.api.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestStreams implements Constants {

    @Mock private GitLabApi gitLabApi;
    @Mock private GitLabApiClient gitLabApiClient;
    @Captor private ArgumentCaptor<MultivaluedMap<String, String>> attributeCaptor;
    private MockResponse response;

    static private List<User> sortedUsers;

    @BeforeAll
    public static void setupClass() throws Exception {

        // Get a list of users sorted by username, we use this as thye source of truth for the asserts
        sortedUsers = JsonUtils.unmarshalResourceList(User.class, "user-list.json");
        sortedUsers.sort(comparing(User::getUsername));
    }

    @BeforeEach
    public void setup() throws Exception {
    	openMocks(this);
        response = new MockResponse(User.class, null, "user-list.json");
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

    @Test
    public void testLazyStream() throws Exception {

        // Arrange
        Pager<User> pager = new UserApi(gitLabApi).getUsers(10);
        Stream<User> stream = pager.lazyStream();

        // Assert
        assertNotNull(stream);
        List<String> usernames = stream.map(User::getUsername).collect(toList());
        assertNotNull(usernames);

        assertEquals(usernames.size(), sortedUsers.size());
        for (int i = 0; i < sortedUsers.size(); i++) {
            assertTrue(usernames.contains(sortedUsers.get(i).getUsername()));
        }
    }

    @Test
    public void testStreamLazyLimit() throws Exception {

        // Arrange and only continue if there are more than 3 users
        Pager<User> pager = new UserApi(gitLabApi).getUsers(2);
        assumeTrue(pager != null && pager.getTotalItems() > 3);
        Stream<User> stream = pager.lazyStream();

        // Assert
        List<User> users = stream.limit(3).collect(toList());
        assertNotNull(users);
        assertEquals(3, users.size());
    }
}
