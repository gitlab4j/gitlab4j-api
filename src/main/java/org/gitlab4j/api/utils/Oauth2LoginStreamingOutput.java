package org.gitlab4j.api.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

/**
 * This StreamingOutput implementation is utilized to send a OAuth2 token request
 * in a secure manner.  The password is never copied to a String, instead it is
 * contained in a SecretString that is cleared when an instance of this class is finalized.
 */
public class Oauth2LoginStreamingOutput implements StreamingOutput, AutoCloseable {

    private final String username;
    private final SecretString password;

    public Oauth2LoginStreamingOutput(String username, CharSequence password) {
        this.username = username;
        this.password = new SecretString(password);
    }

    public Oauth2LoginStreamingOutput(String username, char[] password) {
        this.username = username;
        this.password = new SecretString(password);
    }

    @Override
    public void write(OutputStream output) throws IOException, WebApplicationException {

        PrintWriter writer = new PrintWriter(output);
        writer.append("{ ");
        writer.append("\"grant_type\": \"password\", ");
        writer.append("\"username\": \"" + username + "\", ");
        writer.append("\"password\": ");

        // Output the quoted password
        writer.append('"');
        for (int i = 0, length = password.length(); i < length; i++) {
            writer.append(password.charAt(i));
        }     
        writer.append('"');

        writer.append(" }");
        writer.flush();
        writer.close();
    }

    /**
     * Clears the contained password's data.
     */
    public void clearPassword() {
        password.clear();
    }

    @Override
    public void close() {
        clearPassword();
    }

    @Override
    public void finalize() throws Throwable {
        clearPassword();
        super.finalize();
    }
}