package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ImportStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Enum representing the status of the import.
     */
    public enum Status {
        NONE,
        SCHEDULED,
        FAILED,
        STARTED,
        FINISHED;

        private static JacksonJsonEnumHelper<Status> enumHelper = new JacksonJsonEnumHelper<>(Status.class);

        @JsonCreator
        public static Status forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /**
     * The unique identifier of the import.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The description of the import.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The name of the import.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The name of the import with the associated namespace.
     */
    @JsonProperty("name_with_namespace")
    private String nameWithNamespace;

    /**
     * The path of the import.
     */
    @JsonProperty("path")
    private String path;

    /**
     * The path of the import with the associated namespace.
     */
    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;

    /**
     * The creation date of the import.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * The import status.
     */
    @JsonProperty("import_status")
    private Status importStatus;

    /**
     * Any errors associated with the import.
     */
    @JsonProperty("import_error")
    private String importError;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameWithNamespace() {
        return nameWithNamespace;
    }

    public void setNameWithNamespace(String nameWithNamespace) {
        this.nameWithNamespace = nameWithNamespace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Status getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(Status importStatus) {
        this.importStatus = importStatus;
    }

    public String getImportError() {
        return importError;
    }

    public void setImportError(String importError) {
        this.importError = importError;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
