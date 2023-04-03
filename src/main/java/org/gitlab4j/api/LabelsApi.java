package org.gitlab4j.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.gitlab4j.api.models.Label;

/**
 * This class provides an entry point to all the GitLab API project and group label calls.
 *
 * @see <a href="https://docs.gitlab.com/ce/api/labels.html">Labels API at GitLab</a>
 * @see <a href="https://docs.gitlab.com/ce/api/group_labels.html">Group Labels API at GitLab</a>
 */
public class LabelsApi extends AbstractApi {

  public LabelsApi(final GitLabApi gitLabApi) {
    super(gitLabApi);
  }

  /**
   * Get all labels of the specified project.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @return a list of project's labels
   * @throws GitLabApiException if any exception occurs
   */
  public List<Label> getProjectLabels(final Object projectIdOrPath) throws GitLabApiException {
    return (getProjectLabels(projectIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Get a Pager of all labels of the specified project.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param itemsPerPage the number of items per page
   * @return a list of project's labels in the specified range
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Label> getProjectLabels(final Object projectIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Label>(
        this,
        Label.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "labels"));
  }

  /**
   * Get a Stream of all labels of the specified project.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @return a Stream of project's labels
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Label> getProjectLabelsStream(final Object projectIdOrPath)
      throws GitLabApiException {
    return (getProjectLabels(projectIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Get a single project label.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return a Label instance holding the information for the group label
   * @throws GitLabApiException if any exception occurs
   */
  public Label getProjectLabel(final Object projectIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName));
    return (response.readEntity(Label.class));
  }

  /**
   * Get a single project label as the value of an Optional.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return a Optional instance with a Label instance as its value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<Label> getOptionalProjectLabel(
      final Object projectIdOrPath, final Object labelIdOrName) throws GitLabApiException {
    try {
      return (Optional.ofNullable(getProjectLabel(projectIdOrPath, labelIdOrName)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }

  /**
   * Create a project label. A Label instance is used to set the label properties. withXXX() methods
   * are provided to set the properties of the label to create:
   *
   * <pre><code>
   *   // name and color properties are required
   *   Label labelProperties = new Label()
   *          .withName("a-pink-project-label")
   *          .withColor("pink")
   *          .withDescription("A new pink project label")
   *       .withPriority(10);
   *   gitLabApi.getLabelsApi().createProjectLabel(projectId, labelProperties);
   * </code></pre>
   *
   * <pre><code>GitLab Endpoint: POST /groups/:id/labels</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelProperties a Label instance holding the properties for the new group label
   * @return the created Label instance
   * @throws GitLabApiException if any exception occurs
   */
  public Label createProjectLabel(final Object projectIdOrPath, final Label labelProperties)
      throws GitLabApiException {
    final GitLabApiForm formData = labelProperties.getForm(true);
    final Response response =
        post(
            Response.Status.CREATED,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels");
    return (response.readEntity(Label.class));
  }

  /**
   * Update the specified project label. The name, color, and description can be updated. A Label
   * instance is used to set the properties of the label to update, withXXX() methods are provided
   * to set the properties to update:
   *
   * <pre><code>
   *   Label labelUpdates = new Label()
   *        .withName("a-new-name")
   *        .withColor("red")
   *        .withDescription("A red group label");
   *   gitLabApi.getLabelsApi().updateGroupLabel(projectId, labelId, labelUpdates);
   * </code></pre>
   *
   * <pre><code>GitLab Endpoint: PUT /projects/:id/labels/:label_id</code></pre>
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @param labelConfig a Label instance holding the label properties to update
   * @return the updated Label instance
   * @throws GitLabApiException if any exception occurs
   */
  public Label updateProjectLabel(
      final Object projectIdOrPath, final Object labelIdOrName, final Label labelConfig)
      throws GitLabApiException {
    final GitLabApiForm formData = labelConfig.getForm(false);
    final Response response =
        putWithFormData(
            Response.Status.OK,
            formData,
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName));
    return (response.readEntity(Label.class));
  }

  /**
   * Delete the specified project label.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteProjectLabel(final Object projectIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "labels",
        getLabelIdOrName(labelIdOrName));
  }

  /**
   * Subscribe a specified project label.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return HttpStatusCode 503
   * @throws GitLabApiException if any exception occurs
   */
  public Label subscribeProjectLabel(final Object projectIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    final Response response =
        post(
            Response.Status.NOT_MODIFIED,
            getDefaultPerPageParam(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName),
            "subscribe");
    return (response.readEntity(Label.class));
  }

  /**
   * Unsubscribe a specified project label.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return HttpStatusCode 503
   * @throws GitLabApiException if any exception occurs
   */
  public Label unsubscribeProjectLabel(final Object projectIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    final Response response =
        post(
            Response.Status.NOT_MODIFIED,
            getDefaultPerPageParam(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName),
            "unsubscribe");
    return (response.readEntity(Label.class));
  }

  /**
   * Get all labels of the specified group.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @return a list of group's labels
   * @throws org.gitlab4j.api.GitLabApiException if any exception occurs
   */
  public List<Label> getGroupLabels(final Object groupIdOrPath) throws GitLabApiException {
    return (getGroupLabels(groupIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Get a Pager of all labels of the specified group.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param itemsPerPage the number of items per page
   * @return a list of group's labels in the specified range
   * @throws GitLabApiException if any exception occurs
   */
  public Pager<Label> getGroupLabels(final Object groupIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Label>(
        this,
        Label.class,
        itemsPerPage,
        null,
        "groups",
        getGroupIdOrPath(groupIdOrPath),
        "labels"));
  }

  /**
   * Get a Stream of all labels of the specified group.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @return a Stream of group's labels
   * @throws GitLabApiException if any exception occurs
   */
  public Stream<Label> getGroupLabelsStream(final Object groupIdOrPath) throws GitLabApiException {
    return (getGroupLabels(groupIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Get a single group label.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return a Label instance holding the information for the group label
   * @throws GitLabApiException if any exception occurs
   */
  public Label getGroupLabel(final Object groupIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    final Response response =
        get(
            Response.Status.OK,
            null,
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName));
    return (response.readEntity(Label.class));
  }

  /**
   * Get a single group label as the value of an Optional.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return a Optional instance with a Label instance as its value
   * @throws GitLabApiException if any exception occurs
   */
  public Optional<Label> getOptionalGroupLabel(
      final Object groupIdOrPath, final Object labelIdOrName) throws GitLabApiException {
    try {
      return (Optional.ofNullable(getGroupLabel(groupIdOrPath, labelIdOrName)));
    } catch (final GitLabApiException glae) {
      return (GitLabApi.createOptionalFromException(glae));
    }
  }

  /**
   * Create a group label. A Label instance is used to set the label properties. withXXX() methods
   * are provided to set the properties of the label to create:
   *
   * <pre><code>
   *   Label labelProperties = new Label()
   *       .withName("a-name")
   *       .withColor("green")
   *       .withDescription("A new green group label");
   *   gitLabApi.getLabelsApi().createGroupLabel(projectId, labelProperties);
   * </code></pre>
   *
   * <pre><code>GitLab Endpoint: POST /groups/:id/labels</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelProperties a Label instance holding the properties for the new group label
   * @return the created Label instance
   * @throws GitLabApiException if any exception occurs
   */
  public Label createGroupLabel(final Object groupIdOrPath, final Label labelProperties)
      throws GitLabApiException {
    final GitLabApiForm formData = labelProperties.getForm(true);
    final Response response =
        post(
            Response.Status.CREATED, formData, "groups", getGroupIdOrPath(groupIdOrPath), "labels");
    return (response.readEntity(Label.class));
  }

  /**
   * Update the specified label. The name, color, and description can be updated. A Label instance
   * is used to set the properties of the label to update, withXXX() methods are provided to set the
   * properties to update:
   *
   * <pre><code>
   *   Label labelUpdates = new Label()
   *       .withName("a-new-name")
   *       .withColor("red")
   *       .withDescription("A red group label");
   *   gitLabApi.getLabelsApi().updateGroupLabel(projectId, labelId, labelUpdates);
   * </code></pre>
   *
   * <pre><code>GitLab Endpoint: PUT /groups/:id/labels/:label_id</code></pre>
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @param labelConfig a Label instance holding the label properties to update
   * @return the updated Label instance
   * @throws GitLabApiException if any exception occurs
   */
  public Label updateGroupLabel(
      final Object groupIdOrPath, final Object labelIdOrName, final Label labelConfig)
      throws GitLabApiException {
    final GitLabApiForm formData = labelConfig.getForm(false);
    final Response response =
        putWithFormData(
            Response.Status.OK,
            formData,
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName));
    return (response.readEntity(Label.class));
  }

  /**
   * Delete the specified label
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @throws GitLabApiException if any exception occurs
   */
  public void deleteGroupLabel(final Object groupIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    delete(
        Response.Status.OK,
        null,
        "groups",
        getGroupIdOrPath(groupIdOrPath),
        "labels",
        getLabelIdOrName(labelIdOrName));
  }

  /**
   * Subscribe a specified group label.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return HttpStatusCode 503
   * @throws GitLabApiException if any exception occurs
   */
  public Label subscribeGroupLabel(final Object groupIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    final Response response =
        post(
            Response.Status.NOT_MODIFIED,
            getDefaultPerPageParam(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName),
            "subscribe");
    return (response.readEntity(Label.class));
  }

  /**
   * Unsubscribe a specified group label.
   *
   * @param groupIdOrPath the group in the form of an Long(ID), String(path), or Group instance
   * @param labelIdOrName the label in the form of an Long(ID), String(name), or Label instance
   * @return HttpStatusCode 503
   * @throws GitLabApiException if any exception occurs
   */
  public Label unsubscribeGroupLabel(final Object groupIdOrPath, final Object labelIdOrName)
      throws GitLabApiException {
    final Response response =
        post(
            Response.Status.NOT_MODIFIED,
            getDefaultPerPageParam(),
            "groups",
            getGroupIdOrPath(groupIdOrPath),
            "labels",
            getLabelIdOrName(labelIdOrName),
            "unsubscribe");
    return (response.readEntity(Label.class));
  }

  /**
   * Get all labels of the specified project.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @return a list of project's labels
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #getProjectLabels(Object)} method.
   */
  @Deprecated
  public List<Label> getLabels(final Object projectIdOrPath) throws GitLabApiException {
    return (getLabels(projectIdOrPath, getDefaultPerPage()).all());
  }

  /**
   * Get all labels of the specified project to using the specified page and per page setting
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param page the page to get
   * @param perPage the number of items per page
   * @return a list of project's labels in the specified range
   * @throws GitLabApiException if any exception occurs
   * @deprecated Will be removed in the next major release (6.0.0)
   */
  @Deprecated
  public List<Label> getLabels(final Object projectIdOrPath, final int page, final int perPage)
      throws GitLabApiException {
    final Response response =
        get(
            jakarta.ws.rs.core.Response.Status.OK,
            getPageQueryParams(page, perPage),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels");
    return (response.readEntity(new GenericType<List<Label>>() {}));
  }

  /**
   * Get a Pager of all labels of the specified project.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param itemsPerPage the number of items per page
   * @return a list of project's labels in the specified range
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #getProjectLabels(Object, int)} method.
   */
  @Deprecated
  public Pager<Label> getLabels(final Object projectIdOrPath, final int itemsPerPage)
      throws GitLabApiException {
    return (new Pager<Label>(
        this,
        Label.class,
        itemsPerPage,
        null,
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "labels"));
  }

  /**
   * Get a Stream of all labels of the specified project.
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @return a Stream of project's labels
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #getProjectLabelsStream(Object)} method.
   */
  @Deprecated
  public Stream<Label> getLabelsStream(final Object projectIdOrPath) throws GitLabApiException {
    return (getLabels(projectIdOrPath, getDefaultPerPage()).stream());
  }

  /**
   * Create a label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param color the color for the label
   * @param description the description for the label
   * @return the created Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #createProjectLabel(Object, Label)} method.
   */
  @Deprecated
  public Label createLabel(
      final Object projectIdOrPath, final String name, final String color, final String description)
      throws GitLabApiException {
    return (createLabel(projectIdOrPath, name, color, description, null));
  }

  /**
   * Create a label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param color the color for the label
   * @return the created Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #createProjectLabel(Object, Label)} method.
   */
  @Deprecated
  public Label createLabel(final Object projectIdOrPath, final String name, final String color)
      throws GitLabApiException {
    return (createLabel(projectIdOrPath, name, color, null, null));
  }

  /**
   * Create a label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param color the color for the label
   * @param priority the priority for the label
   * @return the created Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #createProjectLabel(Object, Label)} method.
   */
  @Deprecated
  public Label createLabel(
      final Object projectIdOrPath, final String name, final String color, final Integer priority)
      throws GitLabApiException {
    return (createLabel(projectIdOrPath, name, color, null, priority));
  }

  /**
   * Create a label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param color the color for the label
   * @param description the description for the label
   * @param priority the priority for the label
   * @return the created Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #createProjectLabel(Object, Label)} method.
   */
  @Deprecated
  public Label createLabel(
      final Object projectIdOrPath,
      final String name,
      final String color,
      final String description,
      final Integer priority)
      throws GitLabApiException {
    final Label labelProperties =
        new Label()
            .withName(name)
            .withColor(color)
            .withDescription(description)
            .withPriority(priority);
    return (createProjectLabel(projectIdOrPath, labelProperties));
  }

  /**
   * Update the specified label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param newName the new name for the label
   * @param description the description for the label
   * @param priority the priority for the label
   * @return the modified Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated @deprecated Replaced by the {@link #updateProjectLabel(Object, Object, Label)}
   *     method.
   */
  @Deprecated
  public Label updateLabelName(
      final Object projectIdOrPath,
      final String name,
      final String newName,
      final String description,
      final Integer priority)
      throws GitLabApiException {
    return (updateLabel(projectIdOrPath, name, newName, null, description, priority));
  }

  /**
   * Update the specified label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param color the color for the label
   * @param description the description for the label
   * @param priority the priority for the label
   * @return the modified Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated @deprecated Replaced by the {@link #updateProjectLabel(Object, Object, Label)}
   *     method.
   */
  @Deprecated
  public Label updateLabelColor(
      final Object projectIdOrPath,
      final String name,
      final String color,
      final String description,
      final Integer priority)
      throws GitLabApiException {
    return (updateLabel(projectIdOrPath, name, null, color, description, priority));
  }

  /**
   * Update the specified label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @param newName the new name for the label
   * @param color the color for the label
   * @param description the description for the label
   * @param priority the priority for the label
   * @return the modified Label instance
   * @throws GitLabApiException if any exception occurs
   * @deprecated @deprecated Replaced by the {@link #updateProjectLabel(Object, Object, Label)}
   *     method.
   */
  @Deprecated
  public Label updateLabel(
      final Object projectIdOrPath,
      final String name,
      final String newName,
      final String color,
      final String description,
      final Integer priority)
      throws GitLabApiException {
    final GitLabApiForm formData =
        new GitLabApiForm()
            .withParam("name", name, true)
            .withParam("new_name", newName)
            .withParam("color", color)
            .withParam("description", description)
            .withParam("priority", priority);
    final Response response =
        put(
            Response.Status.OK,
            formData.asMap(),
            "projects",
            getProjectIdOrPath(projectIdOrPath),
            "labels");
    return (response.readEntity(Label.class));
  }

  /**
   * Delete the specified label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param name the name for the label
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #deleteProjectLabel(Object, Object)} method.
   */
  @Deprecated
  public void deleteLabel(final Object projectIdOrPath, final String name)
      throws GitLabApiException {
    final GitLabApiForm formData = new GitLabApiForm().withParam("name", name, true);
    delete(
        Response.Status.OK,
        formData.asMap(),
        "projects",
        getProjectIdOrPath(projectIdOrPath),
        "labels");
  }

  /**
   * Subscribe a specified label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelId the label ID
   * @return HttpStatusCode 503
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #subscribeProjectLabel(Object, Object)} method.
   */
  @Deprecated
  public Label subscribeLabel(final Object projectIdOrPath, final Long labelId)
      throws GitLabApiException {
    return (subscribeProjectLabel(projectIdOrPath, labelId));
  }

  /**
   * Unsubscribe a specified label
   *
   * @param projectIdOrPath the project in the form of an Long(ID), String(path), or Project
   *     instance
   * @param labelId the label ID
   * @return HttpStatusCode 503
   * @throws GitLabApiException if any exception occurs
   * @deprecated Replaced by the {@link #unsubscribeProjectLabel(Object, Object)} method.
   */
  @Deprecated
  public Label unsubscribeLabel(final Object projectIdOrPath, final Long labelId)
      throws GitLabApiException {
    return (unsubscribeProjectLabel(projectIdOrPath, labelId));
  }
}
