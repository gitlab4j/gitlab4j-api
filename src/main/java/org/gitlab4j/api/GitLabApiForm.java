package org.gitlab4j.api;

import javax.ws.rs.core.Form;

/**
 * This class extends the standard JAX-RS Form class to make it fluent.
 */
public class GitLabApiForm extends Form {

    /**
     * Fluent method for adding query and form parameters to a get() or post() call.
     * 
     * @param name the name of the field/attribute to add
     * @param value the value of the field/attribute to add
     * @return this GitLabAPiForm instance
     */
    protected GitLabApiForm withParam(String name, Object value) throws IllegalArgumentException {
        return (withParam(name, value, false));
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
    protected GitLabApiForm withParam(String name, Object value, boolean required) throws IllegalArgumentException {

        if (value == null) {

            if (required) {
                throw new IllegalArgumentException(name + " cannot be empty or null");
            }

            return (this);
        }

        String stringValue = value.toString().trim();
        if (required && stringValue.length() == 0) {
            throw new IllegalArgumentException(name + " cannot be empty or null");
        }

        this.param(name, stringValue);
        return (this);
    }
}
