package org.gitlab4j.api;

import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MultivaluedHashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Variable;
import org.gitlab4j.api.utils.ISO8601;

/** This class extends the standard JAX-RS Form class to make it fluent. */
public class GitLabApiForm extends Form {

  public GitLabApiForm() {
    super();
  }

  public GitLabApiForm(final MultivaluedHashMap<String, String> map) {
    super(map);
  }

  /**
   * Create a GitLabApiForm instance with the "page", and "per_page" parameters preset.
   *
   * @param page the value for the "page" parameter
   * @param perPage the value for the "per_page" parameter
   */
  public GitLabApiForm(final int page, final int perPage) {
    super();
    withParam(AbstractApi.PAGE_PARAM, page);
    withParam(AbstractApi.PER_PAGE_PARAM, (Integer) perPage);
  }

  /**
   * Fluent method for adding query and form parameters to a get() or post() call.
   *
   * @param name the name of the field/attribute to add
   * @param value the value of the field/attribute to add
   * @return this GitLabAPiForm instance
   */
  public GitLabApiForm withParam(final String name, final Object value) throws IllegalArgumentException {
    return (withParam(name, value, false));
  }

  /**
   * Fluent method for adding Date query and form parameters to a get() or post() call.
   *
   * @param name the name of the field/attribute to add
   * @param date the value of the field/attribute to add
   * @return this GitLabAPiForm instance
   */
  public GitLabApiForm withParam(final String name, final Date date) throws IllegalArgumentException {
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
  public GitLabApiForm withParam(final String name, final Date date, final boolean required)
      throws IllegalArgumentException {
    return (withParam(name, (date == null ? null : ISO8601.toString(date)), required));
  }

  /**
   * Fluent method for adding AccessLevel query and form parameters to a get() or post() call.
   *
   * @param name the name of the field/attribute to add
   * @param level the value of the field/attribute to add
   * @return this GitLabAPiForm instance
   */
  public GitLabApiForm withParam(final String name, final AccessLevel level) throws IllegalArgumentException {
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
  public GitLabApiForm withParam(final String name, final AccessLevel level, final boolean required)
      throws IllegalArgumentException {
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
  public <T> GitLabApiForm withParam(final String name, final List<T> values) {
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
  public <T> GitLabApiForm withParam(final String name, final List<T> values, final boolean required)
      throws IllegalArgumentException {

    if (values == null || values.isEmpty()) {
      if (required) {
        throw new IllegalArgumentException(name + " cannot be empty or null");
      }

      return (this);
    }

    for (final T value : values) {
      if (value != null) {
        this.param(name + "[]", value.toString());
      }
    }

    return (this);
  }

  /**
   * Fluent method for adding an array of hash type query and form parameters to a get() or post()
   * call.
   *
   * @param name the name of the field/attribute to add
   * @param variables a Map containing array of hashes
   * @param required the field is required flag
   * @return this GitLabAPiForm instance
   * @throws IllegalArgumentException if a required parameter is null or empty
   */
  public GitLabApiForm withParam(final String name, final Map<String, ?> variables, final boolean required)
      throws IllegalArgumentException {

    if (variables == null || variables.isEmpty()) {
      if (required) {
        throw new IllegalArgumentException(name + " cannot be empty or null");
      }

      return (this);
    }

    for (final Entry<String, ?> variable : variables.entrySet()) {
      final Object value = variable.getValue();
      if (value != null) {
        this.param(name + "[][key]", variable.getKey());
        this.param(name + "[][value]", value.toString());
      }
    }

    return (this);
  }

  /**
   * Fluent method for adding query and form parameters to a get() or post() call. If required is
   * true and value is null, will throw an IllegalArgumentException.
   *
   * @param name the name of the field/attribute to add
   * @param value the value of the field/attribute to add
   * @param required the field is required flag
   * @return this GitLabAPiForm instance
   * @throws IllegalArgumentException if a required parameter is null or empty
   */
  public GitLabApiForm withParam(final String name, final Object value, final boolean required)
      throws IllegalArgumentException {

    if (value == null) {
      if (required) {
        throw new IllegalArgumentException(name + " cannot be empty or null");
      }

      return (this);
    }

    final String stringValue = value.toString();
    if (required && stringValue.trim().length() == 0) {
      throw new IllegalArgumentException(name + " cannot be empty or null");
    }

    this.param(name.trim(), stringValue);
    return (this);
  }

  /**
   * Fluent method for adding a List&lt;Variable&gt; type query and form parameters to a get(),
   * post(), or put() call.
   *
   * @param variables the List of Variable to add
   * @return this GitLabAPiForm instance
   */
  public GitLabApiForm withParam(final List<Variable> variables) {

    if (variables == null || variables.isEmpty()) {
      return (this);
    }

    variables.forEach(
        v -> {
          final String value = v.getValue();
          if (value != null) {
            this.param("variables[" + v.getKey() + "]", value);
          }
        });

    return (this);
  }
}
