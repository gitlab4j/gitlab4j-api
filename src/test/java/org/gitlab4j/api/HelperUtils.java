package org.gitlab4j.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HelperUtils {

    private static Properties testProperties;
    static {

        File propertiesFile = new File((String) System.getProperties().get("user.home"), "test-gitlab4j.properties");
        if (!propertiesFile.exists()) {

            // Get the maven basedir, we use it to locate the properties for the unit tests
            String basedir = (String) System.getProperties().get("basedir");

            // If we are performing a release in target/checkout, trim off the target/checkout directory from basedir
            if (basedir != null && (basedir.endsWith("target/checkout") || basedir.endsWith("target\\checkout"))) {
                basedir = basedir.substring(0, basedir.length() - 15);
            }

            propertiesFile = new File(basedir, "src/test/gitlab/test-gitlab4j.properties");
        }

        if (propertiesFile.exists()) {

            System.out.println("test-gitlab4j.properties location: " + propertiesFile.getAbsolutePath());

            testProperties = new Properties();
            try (InputStream input = new FileInputStream(propertiesFile)) {
                testProperties.load(input);
            } catch (IOException ioe) {
            }

        } else {
            System.out.println("No test-gitlab4j.properties file found");
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
