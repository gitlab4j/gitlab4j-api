package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IssuesStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("statistics")
    private Statistics statistics;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @JsonIgnore
    public Counts getCounts() {
        return (statistics != null ? statistics.counts : null);
    }

    public static class Statistics implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonProperty("counts")
        private Counts counts;

        public Counts getCounts() {
            return counts;
        }

        public void setCounts(Counts counts) {
            this.counts = counts;
        }
    }

    public static class Counts implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * The total number of issues.
         */
        @JsonProperty("all")
        private Integer all;

        /**
         * The number of closed issues.
         */
        @JsonProperty("closed")
        private Integer closed;

        /**
         * The number of opened issues.
         */
        @JsonProperty("opened")
        private Integer opened;

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }

        public Integer getClosed() {
            return closed;
        }

        public void setClosed(Integer closed) {
            this.closed = closed;
        }

        public Integer getOpened() {
            return opened;
        }

        public void setOpened(Integer opened) {
            this.opened = opened;
        }
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
