package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.gitlab4j.api.models.Duration;
import org.gitlab4j.api.utils.DurationUtils;
import org.junit.jupiter.api.Test;

public class TestDuration {

    @Test
    public void testParse() {

        int seconds = DurationUtils.parse("7mo1w1d1h1m1s");
        assertEquals(60 * 60 * 8 * 5 * 4 * 7 + 60 * 60 * 8 * 5 + 60 * 60 * 8 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("1w1d1h1m1s");
        assertEquals(60 * 60 * 8 * 5 + 60 * 60 * 8 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("1d1h1m1s");
        assertEquals(60 * 60 * 8 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("60m");
        assertEquals(60 * 60, seconds);

        seconds = DurationUtils.parse("1h");
        assertEquals(60 * 60, seconds);
    }

    @Test
    public void testRoundTrip() {

        Duration duration = new Duration("2mo1d");
        String durationString = DurationUtils.toString(duration.getSeconds(), false);
        assertEquals("8w1d", durationString);
        duration = new Duration(durationString);
        assertEquals("2mo1d", duration.toString());

        duration = new Duration("9w2h5m");
        durationString = DurationUtils.toString(duration.getSeconds(), false);
        assertEquals("9w0d2h5m", durationString);
        duration = new Duration(durationString);
        assertEquals("2mo1w0d2h5m", duration.toString());

        duration = new Duration("2mo1w1d2h5m");
        durationString = DurationUtils.toString(duration.getSeconds(), false);
        assertEquals("9w1d2h5m", durationString);
        duration = new Duration(durationString);
        assertEquals("2mo1w1d2h5m", duration.toString());
    }


    @Test
    public void testParseWithSpaces() {

        int seconds = DurationUtils.parse("5w 1d 1h 1m 1s");
        assertEquals(60 * 60 * 8 * 5 * 5 + 60 * 60 * 8 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("1w 1d 1h 1m 1s");
        assertEquals(60 * 60 * 8 * 5 + 60 * 60 * 8 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("1d 1h 1m 1s");
        assertEquals(60 * 60 * 8 + 60 * 60 + 60 + 1, seconds);

        seconds = DurationUtils.parse("60m");
        assertEquals(60 * 60, seconds);

        seconds = DurationUtils.parse("2h");
        assertEquals(60 * 60 * 2, seconds);
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

        duration = DurationUtils.toString(60 * 60 * 8 + 60 * 60 * 2 + 60 * 3 + 4);
        assertEquals("1d2h3m4s", duration);

        duration = DurationUtils.toString(60 * 60 * 8 * 5 + 60 * 60 * 8 * 2 + 60 * 60 * 3 + 60 * 4 + 5);
        assertEquals("1w2d3h4m5s", duration);

        duration = DurationUtils.toString(60 * 60 * 8 * 5 * 4 * 3 + 60 * 60 * 8 * 2 + 60 * 60 * 3 + 60 * 6 + 8);
        assertEquals("3mo2d3h6m8s", duration);

        duration = DurationUtils.toString(60 * 60 * 8 * 5 * 5 + 60 * 60 * 8 * 2 + 60 * 60 * 3 + 60 * 4 + 5, false);
        assertEquals("5w2d3h4m5s", duration);

        duration = DurationUtils.toString(60 * 60 * 8 * 5 * 4 * 3 + 60 * 60 * 8 * 2 + 60 * 60 * 3 + 60 * 6 + 8, false);
        assertEquals("12w2d3h6m8s", duration);

        duration = DurationUtils.toString(60 * 60 * 8 * 5 * 5 + 60 * 60 * 8 * 2 + 60 * 60 * 3 + 60 * 4 + 5, false);
        assertEquals("5w2d3h4m5s", duration);
    }
}
