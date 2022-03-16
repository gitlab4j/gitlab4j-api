package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.gitlab4j.api.utils.Oauth2LoginStreamingOutput;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class TestOauth2LoginStreamingOutput {

    private static final String USERNAME = "test-user";

    @Test
    public void testPasswordsWithBackslashes() throws Exception {

        final String password = "Password with \\backslashes\\";
        try (Oauth2LoginStreamingOutput oauth2Stream = new Oauth2LoginStreamingOutput(USERNAME, password)) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            oauth2Stream.write(stream);

            String json = stream.toString(StandardCharsets.UTF_8.name());
            System.out.println(json);
            JsonNode tree = JsonUtils.readTreeFromString(json);
            assertEquals(password, tree.path("password").asText());
        }
    }

    @Test
    public void testPasswordsWithDoubleQuotes() throws Exception {

        final String password = "Password with \"double quotes\"";
        try (Oauth2LoginStreamingOutput oauth2Stream = new Oauth2LoginStreamingOutput(USERNAME, password)) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            oauth2Stream.write(stream);

            String json = stream.toString(StandardCharsets.UTF_8.name());
            System.out.println(json);
            JsonNode tree = JsonUtils.readTreeFromString(json);
            assertEquals(password, tree.path("password").asText());
        }
    }

    @Test
    public void testPasswordsWithSpecialLetters() throws Exception {

        final String password = "Password with special letters 'Ää - Öö - Üü - ẞ'";
        try (Oauth2LoginStreamingOutput oauth2Stream = new Oauth2LoginStreamingOutput(USERNAME, password)) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            oauth2Stream.write(stream);

            String json = stream.toString(StandardCharsets.UTF_8.name());
            System.out.println(json);
            JsonNode tree = JsonUtils.readTreeFromString(json);
            assertEquals(password, tree.path("password").asText());
        }
    }

    @Test
    public void testPasswordsWithManySpecialChars() throws Exception {

        final String password = "Password with many special chars '\\ - \" - [] - () - ~ - ! - ^ - ` - Ää - Öö - Üü - ẞ'";
        try (Oauth2LoginStreamingOutput oauth2Stream = new Oauth2LoginStreamingOutput(USERNAME, password)) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            oauth2Stream.write(stream);

            String json = stream.toString(StandardCharsets.UTF_8.name());
            System.out.println(json);
            JsonNode tree = JsonUtils.readTreeFromString(json);
            assertEquals(password, tree.path("password").asText());
        }
    }
}
