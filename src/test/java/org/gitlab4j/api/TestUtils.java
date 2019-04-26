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

        // Get the maven basedir, we use it to locate the properties for the unit tests
        String basedir = (String) System.getProperties().get("basedir");

        // If we are performing a release in target/checkout, trim off the target/checkout directory from basedir
        if (basedir != null && (basedir.endsWith("target/checkout") || basedir.endsWith("target\\checkout"))) {
            basedir = basedir.substring(0, basedir.length() - 15);
        }

        File propertiesFile = new File(basedir, "test-gitlab4j.properties");
        if (!propertiesFile.exists()) {
            propertiesFile = new File((String) System.getProperties().get("user.home"), "test-gitlab4j.properties");
        }

        System.out.println("test-gitlab4j.properties location: " + propertiesFile.getAbsolutePath());

        testProperties = new Properties();
        try (InputStream input = new FileInputStream(propertiesFile)) {
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

    /**
     * Set a named property, this will amend and overwrite properties read from the test-gitlab4j.properties file.
     *
     * @param key the key of the property to get
     * @return the named property from the test-gitlab4j.properties file
     */
    public static final void setProperty(String key, String value) {
        if (value == null) {
            testProperties.remove(key);
        } else {
            testProperties.setProperty(key, value);
        }
    }

    /**
     * Get a random integer between 1 and the specified value (inclusive).
     *
     * @param maxValue the maximum value to return
     * @return a random integer between 1 and the specified value (inclusive)
     */
    public static final int getRandomInt(int maxValue) {
        return ((int)(Math.random() * maxValue + 1));
    }

    /**
     * Get a random integer between 1 and Integer.MAX_VALUE (inclusive).
     *
     * @return a random integer between 1 and Integer.MAX_VALUE (inclusive)
     */
    public static final int getRandomInt() {
        return (getRandomInt(Integer.MAX_VALUE));
    }
}
