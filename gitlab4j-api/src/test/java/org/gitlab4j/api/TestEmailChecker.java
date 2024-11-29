package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.gitlab4j.api.utils.EmailChecker;
import org.junit.jupiter.api.Test;

public class TestEmailChecker {

    @Test
    public void testValidEmail() {
        assertTrue(EmailChecker.isValidEmail("john@foobar.com"));
    }

    @Test
    public void testValidEmailWithPeriods() {
        assertTrue(EmailChecker.isValidEmail("john.doe@foobar.com"));
    }

    @Test
    public void testMissingTld() {
        assertFalse(EmailChecker.isValidEmail("john@foobar"));
    }

    @Test
    public void testMissingHostname() {
        assertFalse(EmailChecker.isValidEmail("sam@.com"));
    }

    @Test
    public void testNameOnly() {
        assertFalse(EmailChecker.isValidEmail("john"));
    }

    @Test
    public void testNullEmail() {
        assertFalse(EmailChecker.isValidEmail(null));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(EmailChecker.isValidEmail(""));
    }
}
