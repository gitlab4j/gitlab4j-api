package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.api.models.Runner.RunnerType;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateRunnerParams implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("runner_type")
    private RunnerType runnerType;

    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("paused")
    private Boolean paused;

    @JsonProperty("locked")
    private Boolean locked;

    @JsonProperty("run_untagged")
    private Boolean runUntagged;

    @JsonProperty("tag_list")
    private List<String> tagList;

    @JsonProperty("access_level")
    private String accessLevel;

    @JsonProperty("maximum_timeout")
    private Integer maximumTimeout;

    @JsonProperty("maintenance_note")
    private String maintenanceNote;

    public GitLabForm getForm() {

        return new GitLabForm()
                .withParam("runner_type", runnerType, true)
                .withParam("group_id", groupId)
                .withParam("project_id", projectId)
                .withParam("description", description)
                .withParam("paused", paused)
                .withParam("locked", locked)
                .withParam("run_untagged", runUntagged)
                .withParam("tag_list", tagList)
                .withParam("access_level", accessLevel)
                .withParam("maximum_timeout", maximumTimeout)
                .withParam("maintenance_note", maintenanceNote);
    }

    public CreateRunnerParams withRunnerType(RunnerType runnerType) {
        this.runnerType = runnerType;
        return this;
    }

    public CreateRunnerParams withGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public CreateRunnerParams withProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public CreateRunnerParams withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateRunnerParams withPaused(Boolean paused) {
        this.paused = paused;
        return this;
    }

    public CreateRunnerParams withLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    public CreateRunnerParams withRunUntagged(Boolean runUntagged) {
        this.runUntagged = runUntagged;
        return this;
    }

    public CreateRunnerParams withTagList(List<String> tagList) {
        this.tagList = tagList;
        return this;
    }

    public CreateRunnerParams withAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    public CreateRunnerParams withMaximumTimeout(Integer maximumTimeout) {
        this.maximumTimeout = maximumTimeout;
        return this;
    }

    public CreateRunnerParams withMaintenanceNote(String maintenanceNote) {
        this.maintenanceNote = maintenanceNote;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
