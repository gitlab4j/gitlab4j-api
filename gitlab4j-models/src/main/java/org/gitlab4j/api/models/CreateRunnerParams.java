package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.api.models.Runner.RunnerType;
import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.JacksonJson;

public class CreateRunnerParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private RunnerType runnerType;
    private Long groupId;
    private Long projectId;
    private String description;
    private Boolean paused;
    private Boolean locked;
    private Boolean runUntagged;
    private List<String> tagList;
    private String accessLevel;
    private Integer maximumTimeout;
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
