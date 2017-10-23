package org.gitlab4j.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.gitlab4j.api.utils.DurationUtils;
import org.junit.Test;

public class TestDuration {

    @Test
    public void testParse() {

        int seconds = DurationUtils.parse("1w1d1h1m1s");
        assertEquals(60 * 60 * 24 * 7 + 60 * 60 * 24 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("1d1h1m1s");
        assertEquals(60 * 60 * 24 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("60m");
        assertEquals(60 * 60, seconds);
 
        seconds = DurationUtils.parse("1h");
        assertEquals(60 * 60, seconds);
    }

    @Test
    public void testBadParse() {
        
        try {
            DurationUtils.parse("1m1h");
            fail("Should have received an exception for the bad duration");
        } catch (IllegalArgumentException iae) {
            System.out.println("Recieved expected exception: " + iae.getMessage());
        }
        
        try {
            DurationUtils.parse("1z");
            fail("Should have received an exception for the bad duration");
        } catch (IllegalArgumentException iae) {
            System.out.println("Recieved expected exception: " + iae.getMessage());
        }
        
        try {
            DurationUtils.parse("2d2h2s2m");
            fail("Should have received an exception for the bad duration");
        } catch (IllegalArgumentException iae) {
            System.out.println("Recieved expected exception: " + iae.getMessage());
        }
        
        try {
            DurationUtils.parse("2d2h2m2m");
            fail("Should have received an exception for the bad duration");
        } catch (IllegalArgumentException iae) {
            System.out.println("Recieved expected exception: " + iae.getMessage());
        }

        try {
            DurationUtils.parse("1w2w2d2h2m");
            fail("Should have received an exception for the bad duration");
        } catch (IllegalArgumentException iae) {
            System.out.println("Recieved expected exception: " + iae.getMessage());
        }
    }
    
    @Test
    public void testToString() {

        String duration = DurationUtils.toString(60 + 1);
        assertEquals("1m1s", duration);

        duration = DurationUtils.toString(60 * 60 + 60 + 1);
        assertEquals("1h1m1s", duration);

        duration = DurationUtils.toString(60 * 60 * 24 + 60 * 60 * 2 + 60 * 3 + 4);
        assertEquals("1d2h3m4s", duration);

        duration = DurationUtils.toString(60 * 60 * 24 * 7 + 60 * 60 * 24 * 2 + 60 * 60 * 3 + 60 * 4 + 5);
        assertEquals("1w2d3h4m5s", duration);
    }
}
