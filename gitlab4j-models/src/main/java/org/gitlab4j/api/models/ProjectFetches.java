package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ProjectFetches implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class DateCount implements Serializable {

        private static final long serialVersionUID = 1L;

        @JsonProperty("count")
        private Integer count;

        @JsonProperty("date")
        @JsonSerialize(using = JacksonJson.DateOnlySerializer.class)
        private Date date;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public static class Fetches implements Serializable {
        private static final long serialVersionUID = 1L;

        @JsonProperty("total")
        private Integer total;

        @JsonProperty("days")
        private List<DateCount> days;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<DateCount> getDays() {
            return days;
        }

        public void setDays(List<DateCount> days) {
            this.days = days;
        }
    }

    @JsonProperty("fetches")
    private Fetches fetches;

    public Fetches getFetches() {
        return fetches;
    }

    public void setFetches(Fetches fetches) {
        this.fetches = fetches;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
