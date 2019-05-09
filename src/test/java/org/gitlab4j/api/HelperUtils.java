package org.gitlab4j.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class HelperUtils {

    private static final Properties testProperties = new Properties();

    static {

        boolean propertiesLoaded = false;

        // Get the maven basedir, we use it to locate the default properties for the unit tests
        String basedir = (String) System.getProperties().get("basedir");

        // If we are performing a release in target/checkout, trim off the target/checkout directory from basedir
        if (basedir != null && (basedir.endsWith("target/checkout") || basedir.endsWith("target\\checkout"))) {
            basedir = basedir.substring(0, basedir.length() - 15);
        }

        // Load the base test properties from "src/test/resources/test-gitlab4j.properties"
        File propertiesFile = new File(basedir, "src/test/resources/test-gitlab4j.properties");
        if (propertiesFile.exists()) {
            try (InputStreamReader input = new InputStreamReader(new FileInputStream(propertiesFile))) {
                testProperties.load(input);
                System.out.format("Loaded base test properties from: %n%s%n", propertiesFile.getAbsolutePath());
                propertiesLoaded = true;
            } catch (IOException ioe) {
                System.err.println("Error loading base test properties, error=" + ioe.getMessage());
            }
        }

        // Now load the overriding test properties if found in the user's home directory
        propertiesFile = new File((String) System.getProperties().get("user.home"), "test-gitlab4j.properties");
        if (propertiesFile.exists()) {
            try (InputStreamReader input = new InputStreamReader(new FileInputStream(propertiesFile))) {
                testProperties.load(input);
                System.out.format("Loaded overriding test properties from: %n%s%n", propertiesFile.getAbsolutePath());
                propertiesLoaded = true;
            } catch (IOException ioe) {
                System.err.println("Error loading overriding test properties, error=" + ioe.getMessage());
            }
        }

        if (!propertiesLoaded) {
            System.out.println("No test properties have been loaded!");
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
     * Get a named property from the test-gitlab4j.properties file,
     * will return the defaultValue if null or empty.
     *
     * @param key the key of the property to get
     * @param defaultValue the value to return if property is null or empty
     * @return the named property from the test-gitlab4j.properties file
     */
    public static final String getProperty(String key, String defaultValue) {

        String value = getProperty(key);
        if (value != null && value.trim().length() > 0) {
            return (value);
        }

        if (defaultValue != null) {
            testProperties.setProperty(key, defaultValue);
        }

        return (defaultValue);
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
