package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class IssuesStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

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

        private Integer all;
        private Integer closed;
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
