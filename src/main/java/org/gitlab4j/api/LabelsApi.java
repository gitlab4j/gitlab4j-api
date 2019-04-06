package org.gitlab4j.api;

import java.util.List;
import java.util.stream.Stream;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Label;

public class LabelsApi extends AbstractApi {

    public LabelsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all labels of the specified project.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a list of project's labels
     * @throws GitLabApiException if any exception occurs
     */
    public List<Label> getLabels(Object projectIdOrPath) throws GitLabApiException {
        return (getLabels(projectIdOrPath, getDefaultPerPage()).all());
    }

    /**
     * Get all labels of the specified project to using the specified page and per page setting
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param page the page to get
     * @param perPage the number of items per page
     * @return a list of project's labels in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Label> getLabels(Object projectIdOrPath, int page, int perPage) throws GitLabApiException {
        Response response = get(javax.ws.rs.core.Response.Status.OK, getPageQueryParams(page, perPage),
                "projects", getProjectIdOrPath(projectIdOrPath), "labels");
        return (response.readEntity(new GenericType<List<Label>>() {}));
    }

    /**
     * Get a Pager of all labels of the specified project.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param itemsPerPage the number of items per page
     * @return a list of project's labels in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Label> getLabels(Object projectIdOrPath, int itemsPerPage) throws GitLabApiException {
        return (new Pager<Label>(this, Label.class, itemsPerPage, null,
                "projects", getProjectIdOrPath(projectIdOrPath), "labels"));
    }

    /**
     * Get a Stream of all labels of the specified project.
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @return a Stream of project's labels
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Label> getLabelsStream(Object projectIdOrPath) throws GitLabApiException {
        return (getLabels(projectIdOrPath, getDefaultPerPage()).stream());
    }

    /**
     * Create a label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name        the name for the label
     * @param color       the color for the label
     * @param description the description for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Object projectIdOrPath, String name, String color, String description) throws GitLabApiException {
        return (createLabel(projectIdOrPath, name, color, description, null));
    }

    /**
     * Create a label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @param color the color for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Object projectIdOrPath, String name, String color) throws GitLabApiException {
        return (createLabel(projectIdOrPath, name, color, null, null));
    }

    /**
     * Create a label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @param color the color for the label
     * @param priority the priority for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Object projectIdOrPath, String name, String color, Integer priority) throws GitLabApiException {
        return (createLabel(projectIdOrPath, name, color, null, priority));
    }

    /**
     *  Create a label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @param color the color for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Object projectIdOrPath, String name, String color, String description, Integer priority) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("color", color, true)
                .withParam("description", description)
                .withParam("priority", priority);
        Response response = post(Response.Status.CREATED, formData, "projects", getProjectIdOrPath(projectIdOrPath), "labels");
        return (response.readEntity(Label.class));
    }


    /**
     * Update the specified label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @param newName the new name for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the modified Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label updateLabelName(Object projectIdOrPath, String name, String newName, String description, Integer priority) throws GitLabApiException {
        return (updateLabel(projectIdOrPath, name, newName, null, description, priority));
    }


    /**
     * Update the specified label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @param color the color for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the modified Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label updateLabelColor(Object projectIdOrPath, String name, String color, String description, Integer priority) throws GitLabApiException {
        return (updateLabel(projectIdOrPath, name, null, color, description, priority));
    }

    /**
     * Update the specified label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @param newName the new name for the label
     * @param color the color for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the modified Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label updateLabel(Object projectIdOrPath, String name, String newName, String color, String description, Integer priority) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("new_name", newName)
                .withParam("color", color)
                .withParam("description", description)
                .withParam("priority", priority);
        Response response = put(Response.Status.OK, formData.asMap(),
                "projects", getProjectIdOrPath(projectIdOrPath), "labels");
        return (response.readEntity(Label.class));
    }

    /**
     * Delete the specified label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param name the name for the label
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteLabel(Object projectIdOrPath, String name) throws GitLabApiException {

        GitLabApiForm formData = new GitLabApiForm().withParam("name", name, true);
        Response.Status expectedStatus = (isApiVersion(GitLabApi.ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, formData.asMap(), "projects", getProjectIdOrPath(projectIdOrPath), "labels");
    }

    /**
     * Subscribe a specified label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param labelId the label ID
     * @return HttpStatusCode 503
     * @throws GitLabApiException if any exception occurs
     */
    public Label subscribeLabel(Object projectIdOrPath, Integer labelId) throws GitLabApiException {
        Response response = post(Response.Status.NOT_MODIFIED, getDefaultPerPageParam(),
                "projects", getProjectIdOrPath(projectIdOrPath), "labels", labelId, "subscribe");
        return (response.readEntity(Label.class));
    }

    /**
     * Unsubscribe a specified label
     *
     * @param projectIdOrPath the project in the form of an Integer(ID), String(path), or Project instance
     * @param labelId the label ID
     * @return HttpStatusCode 503
     * @throws GitLabApiException if any exception occurs
     */
    public Label unsubscribeLabel(Object projectIdOrPath, Integer labelId) throws GitLabApiException {
        Response response = post(Response.Status.NOT_MODIFIED, getDefaultPerPageParam(),
                "projects", getProjectIdOrPath(projectIdOrPath), "labels", labelId, "unsubscribe");
        return (response.readEntity(Label.class));
    }
}
