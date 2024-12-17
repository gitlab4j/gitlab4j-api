package org.gitlab4j.api.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.Constants.TodoAction;
import org.gitlab4j.models.Constants.TodoState;
import org.gitlab4j.models.Constants.TodoType;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("project")
    private Project project;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("action_name")
    private TodoAction actionName;

    @JsonProperty("target_type")
    private TodoType targetType;

    @JsonProperty("target")
    @JsonDeserialize(using = TargetDeserializer.class)
    private Object target;

    @JsonProperty("target_url")
    private String targetUrl;

    @JsonProperty("body")
    private String body;

    @JsonProperty("state")
    private TodoState state;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public TodoAction getActionName() {
        return actionName;
    }

    public void setActionName(TodoAction actionName) {
        this.actionName = actionName;
    }

    public TodoType getTargetType() {
        return targetType;
    }

    public void setTargetType(TodoType targetType) {
        this.targetType = targetType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TodoState getState() {
        return state;
    }

    public void setState(TodoState state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Issue getIssueTarget() {
        return (targetType == TodoType.ISSUE ? (Issue) target : null);
    }

    @JsonIgnore
    public MergeRequest getMergeRequestTarget() {
        return (targetType == TodoType.MERGE_REQUEST ? (MergeRequest) target : null);
    }

    @JsonIgnore
    public boolean isIssueTodo() {
        return (targetType == TodoType.ISSUE);
    }

    @JsonIgnore
    public boolean isMergeRequestTodo() {
        return (targetType == TodoType.MERGE_REQUEST);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }

    // This deserializer will determine the target type and deserialize to the correct class (either MergeRequest or
    // Issue).
    private static class TargetDeserializer extends JsonDeserializer<Object> {

        @Override
        public Object deserialize(JsonParser jp, DeserializationContext context)
                throws IOException, JsonProcessingException {

            ObjectMapper mapper = (ObjectMapper) jp.getCodec();
            ObjectNode root = (ObjectNode) mapper.readTree(jp);
            boolean isMergeRequestTarget = root.has("source_branch");
            if (isMergeRequestTarget) {
                return mapper.treeToValue(root, MergeRequest.class);
            } else {
                return mapper.treeToValue(root, Issue.class);
            }
        }
    }
}
