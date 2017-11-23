package org.gitlab4j.api;

import org.gitlab4j.api.models.Label;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

public class LabelsApi extends AbstractApi {
    public LabelsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    public List<Label> getLabels(Integer projectId) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(javax.ws.rs.core.Response.Status.OK, getDefaultPerPageParam(), "projects", projectId, "labels");
        return (response.readEntity(new GenericType<List<Label>>() {
        }));
    }

    public List<Label> getLabels(Integer projectId, int page, int perPage) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        Response response = get(javax.ws.rs.core.Response.Status.OK, getPageQueryParams(page, perPage), "projects", projectId, "labels");
        return (response.readEntity(new GenericType<List<Label>>() {
        }));
    }

    public Label createLabel(Integer projectId, String name, String color, String description) throws GitLabApiException {
        return (createLabel(projectId, name, color, description, null));
    }

    public Label createLabel(Integer projectId, String name, String color) throws GitLabApiException {
        return (createLabel(projectId, name, color, null, null));
    }

    public Label createLabel(Integer projectId, String name, String color, Integer priority) throws GitLabApiException {
        return (createLabel(projectId, name, color, null, priority));
    }

    public Label createLabel(Integer projectId, String name, String color, String description, Integer priority) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true)
                .withParam("color", color, true)
                .withParam("description", description)
                .withParam("priority", priority);
        Response response = post(Response.Status.OK, formData, "projects", projectId, "labels");
        return (response.readEntity(Label.class));
    }

    public Label updateLabelName(Integer projectId, String name, String newName, String description, Integer priority) throws GitLabApiException {
        return (updateLabel(projectId, name, newName, null, description, priority));
    }

    public Label updateLabelColor(Integer projectId, String name, String color, String description, Integer priority) throws GitLabApiException {
        return (updateLabel(projectId, name, null, color, description, priority));
    }

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

    public void deleteLabel(Integer projectId, String name) throws GitLabApiException {
        if (projectId == null) {
            throw new RuntimeException("projectId cannot be null");
        }
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("name", name, true);
        Response.Status expectedStatus = (isApiVersion(GitLabApi.ApiVersion.V3) ? Response.Status.OK : Response.Status.NO_CONTENT);
        delete(expectedStatus, formData.asMap(), "projects", projectId, "labels");
    }

    public Label subscribeLabel(Integer projectId, Integer labelId) throws GitLabApiException {
        Response response = post(Response.Status.NOT_MODIFIED, getDefaultPerPageParam(), "projects", projectId, "labels", labelId, "subscribe");
        return (response.readEntity(Label.class));
    }

    public Label unsubscribeLabel(Integer projectId, Integer labelId) throws GitLabApiException {
        Response response = post(Response.Status.NOT_MODIFIED, getDefaultPerPageParam(), "projects", projectId, "labels", labelId, "unsubscribe");
        return (response.readEntity(Label.class));
    }
}
