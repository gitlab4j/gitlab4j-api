package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApiForm;
import org.gitlab4j.api.utils.ISO8601;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

public class IterationFilter {

    public enum IterationFilterState {
        OPENED, UPCOMING, CURRENT, CLOSED, ALL;

        private static JacksonJsonEnumHelper<IterationFilterState> enumHelper = new JacksonJsonEnumHelper<>(IterationFilterState.class, false, true);

        @JsonCreator
        public static IterationFilterState forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    public enum IterationFilterIn {
        TITLE, CADENCE_TITLE;

        private static JacksonJsonEnumHelper<IterationFilterIn> enumHelper = new JacksonJsonEnumHelper<>(IterationFilterIn.class, false, false, true);

        @JsonCreator
        public static IterationFilterIn forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /**
     * Return opened, upcoming, current, closed, or all iterations.
     */
    private IterationFilterState state;

    /**
     * Return only iterations with a title matching the provided string.
     */
    private String search;

    /**
     * Fields in which fuzzy search should be performed with the query given in the argument search.
     */
    private IterationFilterIn in;

    /**
     * Include iterations from parent group and its ancestors. Defaults to true.
     */
    private Boolean includeAncestors;

    /**
     * Return iterations updated after the specified date. Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    private Date updatedAfter;

    /**
     * Return iterations updated before the specified date. Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    private Date updatedBefore;

    public IterationFilterState getState() {
        return state;
    }

    public void setState(IterationFilterState state) {
        this.state = state;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public IterationFilterIn getIn() {
        return in;
    }

    public void setIn(IterationFilterIn in) {
        this.in = in;
    }

    public Boolean getIncludeAncestors() {
        return includeAncestors;
    }

    public void setIncludeAncestors(Boolean includeAncestors) {
        this.includeAncestors = includeAncestors;
    }

    public Date getUpdatedAfter() {
        return updatedAfter;
    }

    public void setUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public Date getUpdatedBefore() {
        return updatedBefore;
    }

    public void setUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
    }

    public IterationFilter withState(IterationFilterState state) {
        this.state = state;
        return (this);
    }

    public IterationFilter withSearch(String search) {
        this.search = search;
        return (this);
    }

    public IterationFilter withIn(IterationFilterIn in) {
        this.in = in;
        return (this);
    }

    public IterationFilter withIncludeAncestors(Boolean includeAncestors) {
        this.includeAncestors = includeAncestors;
        return (this);
    }

    public IterationFilter withUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
        return (this);
    }

    public IterationFilter withUpdatedBefore(Date updatedBefore) {
        this.updatedBefore = updatedBefore;
        return (this);
    }

    @JsonIgnore
    public GitLabApiForm getQueryParams(int page, int perPage) {
        return (getQueryParams()
                .withParam(Constants.PAGE_PARAM, page)
                .withParam(Constants.PER_PAGE_PARAM, perPage));
    }

    @JsonIgnore
    public GitLabApiForm getQueryParams() {
        return new GitLabApiForm()
                .withParam("state", state)
                .withParam("search", search)
                .withParam("in", in)
                .withParam("include_ancestors", includeAncestors)
                .withParam("updated_after", ISO8601.toString(updatedAfter, false))
                .withParam("updated_before", ISO8601.toString(updatedBefore, false));
    }
}
