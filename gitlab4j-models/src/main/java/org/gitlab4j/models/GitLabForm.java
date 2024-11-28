package org.gitlab4j.models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Variable;
import org.gitlab4j.models.utils.ISO8601;

public class GitLabForm {

    private Map<String, String> formValues = new LinkedHashMap<>();

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
        withParam(Constants.PER_PAGE_PARAM, (Integer) perPage);
    }

    /**
     * Fluent method for adding query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     * @return this GitLabAPiForm instance
     */
    public GitLabForm withParam(String name, Object value) throws IllegalArgumentException {
        return (withParam(name, value, false));
    }

    /**
     * Fluent method for adding Date query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param date the value of the field/attribute to add
     * @return this GitLabAPiForm instance
     */
    public GitLabForm withParam(String name, Date date) throws IllegalArgumentException {
        return (withParam(name, date, false));
    }

    /**
     * Fluent method for adding Date query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param date the value of the field/attribute to add
     * @param required the field is required flag
     * @return this GitLabAPiForm instance
     * @throws IllegalArgumentException if a required parameter is null or empty
     */
    public GitLabForm withParam(String name, Date date, boolean required) throws IllegalArgumentException {
        return (withParam(name, (date == null ? null : ISO8601.toString(date)), required));
    }

    /**
     * Fluent method for adding AccessLevel query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param level the value of the field/attribute to add
     * @return this GitLabAPiForm instance
     */
    public GitLabForm withParam(String name, AccessLevel level) throws IllegalArgumentException {
        return (withParam(name, level, false));
    }

    /**
     * Fluent method for adding AccessLevel query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param level the value of the field/attribute to add
     * @param required the field is required flag
     * @return this GitLabAPiForm instance
     * @throws IllegalArgumentException if a required parameter is null or empty
     */
    public GitLabForm withParam(String name, AccessLevel level, boolean required) throws IllegalArgumentException {
        return (withParam(name, (level == null ? null : level.toValue()), required));
    }

    /**
     * Fluent method for adding a List type query and form parameters to a get() or post() call.
     *
     * @param <T> the type contained by the List
     * @param name the name of the field/attribute to add
     * @param values a List containing the values of the field/attribute to add
     * @return this GitLabAPiForm instance
     */
    public <T> GitLabForm withParam(String name, List<T> values) {
        return (withParam(name, values, false));
    }

    /**
     * Fluent method for adding a List type query and form parameters to a get() or post() call.
     *
     * @param <T> the type contained by the List
     * @param name the name of the field/attribute to add
     * @param values a List containing the values of the field/attribute to add
     * @param required the field is required flag
     * @return this GitLabAPiForm instance
     * @throws IllegalArgumentException if a required parameter is null or empty
     */
    public <T> GitLabForm withParam(String name, List<T> values, boolean required) throws IllegalArgumentException {

        if (values == null || values.isEmpty()) {
            if (required) {
                throw new IllegalArgumentException(name + " cannot be empty or null");
            }

            return (this);
        }

        for (T value : values) {
            if (value != null) {
                formValues.put(name + "[]", value.toString());
            }
        }

        return (this);
    }

    /**
     * Fluent method for adding an array of hash type query and form parameters to a get() or post() call.
     *
     * @param name the name of the field/attribute to add
     * @param variables a Map containing array of hashes
     * @param required the field is required flag
     * @return this GitLabAPiForm instance
     * @throws IllegalArgumentException if a required parameter is null or empty
     */
    public GitLabForm withParam(String name, Map<String, ?> variables, boolean required)
            throws IllegalArgumentException {

        if (variables == null || variables.isEmpty()) {
            if (required) {
                throw new IllegalArgumentException(name + " cannot be empty or null");
            }

            return (this);
        }

        for (Entry<String, ?> variable : variables.entrySet()) {
            Object value = variable.getValue();
            if (value != null) {
                formValues.put(name + "[" + variable.getKey() + "]", value.toString());
            }
        }

        return (this);
    }

    /**
     * Fluent method for adding query and form parameters to a get() or post() call.
     * If required is true and value is null, will throw an IllegalArgumentException.
     *
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     * @param required the field is required flag
     * @return this GitLabAPiForm instance
     * @throws IllegalArgumentException if a required parameter is null or empty
     */
    public GitLabForm withParam(String name, Object value, boolean required) throws IllegalArgumentException {

        if (value == null) {
            if (required) {
                throw new IllegalArgumentException(name + " cannot be empty or null");
            }

            return (this);
        }

        String stringValue = value.toString();
        if (required && stringValue.trim().length() == 0) {
            throw new IllegalArgumentException(name + " cannot be empty or null");
        }

        formValues.put(name.trim(), stringValue);
        return (this);
    }

    /**
     * Fluent method for adding a List&lt;Variable&gt; type query and form parameters to a get(), post(), or put() call.
     *
     * @param variables the List of Variable to add
     * @return this GitLabAPiForm instance
     */
    public GitLabForm withParam(List<Variable> variables) {

        if (variables == null || variables.isEmpty()) {
            return (this);
        }

        variables.forEach(v -> {
            String value = v.getValue();
            if (value != null) {
                formValues.put("variables[" + v.getKey() + "]", value);
            }
        });

        return (this);
    }

    public Map<String, String> getFormValues() {
        return formValues;
    }
}
