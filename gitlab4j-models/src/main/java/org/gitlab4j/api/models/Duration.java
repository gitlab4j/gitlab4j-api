package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.DurationUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This class represents a duration in time.
 */
public class Duration implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The duration in seconds.
     */
    @JsonProperty("seconds")
    private int seconds;

    /**
     * The string representation of the duration.
     */
    @JsonProperty("duration_string")
    private String durationString;

    /**
     * Create a Duration instance from a human readable string. e.g: 3h30m
     *
     * @param durationString a duration in human readable format
     */
    public Duration(String durationString) {
        seconds = DurationUtils.parse(durationString);
        this.durationString = (seconds == 0 ? "0m" : DurationUtils.toString(seconds));
    }

    /**
     * Create a Duration instance from a number of seconds.
     *
     * @param seconds the number of seconds for this Duration instance to represent
     */
    public Duration(int seconds) {
        this.seconds = seconds;
        durationString = (seconds == 0 ? "0m" : DurationUtils.toString(seconds));
    }

    /**
     * Get the number of seconds this duration represents.
     *
     * @return the number of seconds this duration represents
     */
    public int getSeconds() {
        return (seconds);
    }

    /**
     * Set the number of seconds this duration represents.
     *
     * @param seconds the number of seconds this duration represents
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @JsonValue
    @Override
    public String toString() {
        return (durationString);
    }

    @JsonCreator
    public static Duration forValue(String value) {
        return new Duration(value);
    }
}
