package org.gitlab4j.api;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.models.Label;

public class LabelsApi extends AbstractApi {

    public LabelsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get all labels of the specified project. Only returns the first page
     *
     * @param projectId the project ID to get the labels for
     * @return a list of project's labels
     * @throws GitLabApiException if any exception occurs
     */
    public List<Label> getLabels(Integer projectId) throws GitLabApiException {
        return (getLabels(projectId, 1, getDefaultPerPage()));
    }

    /**
     * Get all labels of the specified project to using the specified page and per page setting
     *
     * @param projectId the project ID to get the labels for
     * @param page      the page to get
     * @param perPage   the number of issues per page
     * @return a list of project's labels in the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Label> getLabels(Integer projectId, int page, int perPage) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        Response response = get(javax.ws.rs.core.Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "labels");
        return (response.readEntity(new GenericType<List<Label>>() {}));
    }

    /**
     * Create a label
     *
     * @param projectId   the project ID to create a label for
     * @param name        the name for the label
     * @param color       the color for the label
     * @param description the description for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Integer projectId, String name, String color, String description) throws GitLabApiException {
        return (createLabel(projectId, name, color, description, null));
    }

    /**
     * Create a label
     *
     * @param projectId the project ID to create a label for
     * @param name      the name for the label
     * @param color     the color for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Integer projectId, String name, String color) throws GitLabApiException {
        return (createLabel(projectId, name, color, null, null));
    }

    /**
     * Create a label
     *
     * @param projectId the project ID to create a label for
     * @param name      the name for the label
     * @param color     the color for the label
     * @param priority  the priority for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Integer projectId, String name, String color, Integer priority) throws GitLabApiException {
        return (createLabel(projectId, name, color, null, priority));
    }

    /**
     *  Create a label
     *
     * @param projectId the project ID to create a label for
     * @param name the name for the label
     * @param color the color for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the created Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label createLabel(Integer projectId, String name, String color, String description, Integer priority) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("color", color, true)
                .withParam("description", description)
                .withParam("priority", priority);
        Response response = post(Response.Status.CREATED, formData, "projects", projectId, "labels");
        return (response.readEntity(Label.class));
    }


    /**
     * Update the specified label
     *
     * @param projectId the project ID to update a label for
     * @param name the name for the label
     * @param newName the new name for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the modified Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label updateLabelName(Integer projectId, String name, String newName, String description, Integer priority) throws GitLabApiException {
        return (updateLabel(projectId, name, newName, null, description, priority));
    }


    /**
     * Update the specified label
     *
     * @param projectId the project ID to update a label for
     * @param name the name for the label
     * @param color the color for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the modified Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label updateLabelColor(Integer projectId, String name, String color, String description, Integer priority) throws GitLabApiException {
        return (updateLabel(projectId, name, null, color, description, priority));
    }

    /**
     * Update the specified label
     *
     * @param projectId the project ID to update a label for
     * @param name the name for the label
     * @param newName the new name for the label
     * @param color the color for the label
     * @param description the description for the label
     * @param priority the priority for the label
     * @return the modified Label instance
     * @throws GitLabApiException if any exception occurs
     */
    public Label updateLabel(Integer projectId, String name, String newName, String color, String description, Integer priority) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("new_name", newName)
                .withParam("color", color)
                .withParam("description", description)
                .withParam("priority", priority);
        Response response = put(Response.Status.OK, formData.asMap(), "projects", projectId, "labels");
        return (response.readEntity(Label.class));
    }

    /**
     * Delete the specified label
     *
     * @param projectId the project ID to delete a label for
     * @param name the name for the label
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteLabel(Integer projectId, String name) throws GitLabApiException {

        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }

        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true);
        Response.Status expectedStatus = (isApiVersion(GitLabApi.ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, formData.asMap(), "projects", projectId, "labels");
    }

    /**
     * Subscribe a specified label
     *
     * @param projectId the project ID to subscribe a label for
     * @param labelId the lable ID
     * @return HttpStatusCode 503
     * @throws GitLabApiException if any exception occurs
     */
    public Label subscribeLabel(Integer projectId, Integer labelId) throws GitLabApiException {
        Response response = post(Response.Status.NOT_MODIFIED, getDefaultPerPageParam(), "projects", projectId, "labels", labelId, "subscribe");
        return (response.readEntity(Label.class));
    }


    /**
     * Unsubscribe a specified label
     *
     * @param projectId the project ID to unsubscribe a label for
     * @param labelId the lable ID
     * @return HttpStatusCode 503
     * @throws GitLabApiException if any exception occurs
     */
    public Label unsubscribeLabel(Integer projectId, Integer labelId) throws GitLabApiException {
        Response response = post(Response.Status.NOT_MODIFIED, getDefaultPerPageParam(), "projects", projectId, "labels", labelId, "unsubscribe");
        return (response.readEntity(Label.class));
    }
}
