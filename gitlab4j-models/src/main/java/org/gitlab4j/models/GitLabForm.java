package org.gitlab4j.models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.gitlab4j.api.models.AccessLevel;

public class GitLabForm {

    private Map<String, GitLabFormValue> formValues = new LinkedHashMap<>();

    public GitLabForm() {
        super();
    }

    /**
     * Create a GitLabApiForm instance with the "page", and "per_page" parameters preset.
     *
     * @param page the value for the "page" parameter
     * @param perPage the value for the "per_page" parameter
     */
    public GitLabForm(int page, int perPage) {
        super();
        withParam(Constants.PAGE_PARAM, page);
        withParam(Constants.PER_PAGE_PARAM, perPage);
    }

    /**
     * Fluent method for adding query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, Object value) {
        return withParam(name, value, false);
    }

    /**
     * Fluent method for adding Date query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param date the value of the field/attribute to add
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, Date date) {
        return withParam(name, date, false);
    }

    /**
     * Fluent method for adding Date query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param date the value of the field/attribute to add
     * @param required the field is required flag
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, Date date, boolean required) {
        formValues.put(name, new GitLabFormValue(date, GitLabFormValueType.DATE, required));
        return this;
    }

    /**
     * Fluent method for adding AccessLevel query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param level the value of the field/attribute to add
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, AccessLevel level) {
        return withParam(name, level, false);
    }

    /**
     * Fluent method for adding AccessLevel query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param level the value of the field/attribute to add
     * @param required the field is required flag
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, AccessLevel level, boolean required) {
        formValues.put(name, new GitLabFormValue(level, GitLabFormValueType.ACCESS_LEVEL, required));
        return this;
    }

    /**
     * Fluent method for adding a List type query and form parameters to a get() or post() call.
     *
     * @param <T> the type contained by the List
     * @param name the name of the field/attribute to add
     * @param values a List containing the values of the field/attribute to add
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, List<?> values) {
        return withParam(name, values, false);
    }

    /**
     * Fluent method for adding a List type query and form parameters to a get() or post() call.
     *
     * @param <T> the type contained by the List
     * @param name the name of the field/attribute to add
     * @param values a List containing the values of the field/attribute to add
     * @param required the field is required flag
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, List<?> values, boolean required) {
        formValues.put(name, new GitLabFormValue(values, GitLabFormValueType.LIST, required));
        return this;
    }

    /**
     * Fluent method for adding an array of hash type query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param variables a Map containing array of hashes
     * @param required the field is required flag
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, Map<String, ?> variables, boolean required) {
        formValues.put(name, new GitLabFormValue(variables, GitLabFormValueType.MAP, required));
        return this;
    }

    /**
     * Fluent method for adding query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     * @param required the field is required flag
     * @return this {@link GitLabForm} instance
     */
    public GitLabForm withParam(String name, Object value, boolean required) {
        formValues.put(name, new GitLabFormValue(value, GitLabFormValueType.OBJECT, required));
        return this;
    }

    public Map<String, GitLabFormValue> getFormValues() {
        return formValues;
    }
}
