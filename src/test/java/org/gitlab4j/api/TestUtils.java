package org.gitlab4j.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class TestUtils {

    /**
     * Reads the content of a Reader instance and returns it as a String.
     * 
     * @param reader
     * @return the content of a Reader instance as a String
     * @throws IOException
     */
    public static String getReaderContentAsString(Reader reader) throws IOException {

        int count;
        final char[] buffer = new char[2048];
        final StringBuilder out = new StringBuilder();
        while ((count = reader.read(buffer, 0, buffer.length)) >= 0) {
            out.append(buffer, 0, count);
        }

        return (out.toString());
    }


    private static Properties testProperties;
    static {

        testProperties = new Properties();
        File path = new File(System.getProperty("user.home"), "test-gitlab4j.properties");
        try (InputStream input = new FileInputStream(path)) {
            testProperties.load(input);
        } catch (IOException ioe) {
        }
    }

    /**
     * Get a named property from the test-gitlab4j.properties file.
     *
     * @param key the key of the property to get
     * @return the named property from the test-gitlab4j.properties file
     */
    public static final String getProperty(String key) {
        return (testProperties.getProperty(key));
    }
}