package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ExportStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Enum representing the status of the export.
     */
    public enum Status {
        NONE,
        STARTED,
        FINISHED,

        /**
         * Represents that the export process has been completed successfully and the platform is
         * performing some actions on the resulted file. For example, sending an email notifying
         * the user to download the file, uploading the exported file to a web server, etc.
         */
        AFTER_EXPORT_ACTION;

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
     * The unique identifier of the export status.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The description of the export status.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The name of the export status.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The name of the export status with the namespace.
     */
    @JsonProperty("name_with_namespace")
    private String nameWithNamespace;

    /**
     * The path of the export status.
     */
    @JsonProperty("path")
    private String path;

    /**
     * The path of the export status with the namespace.
     */
    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;

    /**
     * The creation date of the export status.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The export status (e.g., in progress, completed, etc.).
     */
    @JsonProperty("export_status")
    private Status exportStatus;

    /**
     * The links related to the export status.
     */
    @JsonProperty("_links")
    private Map<String, String> links;

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

    public Status getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(Status exportStatus) {
        this.exportStatus = exportStatus;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    @JsonIgnore
    public String getLinkByName(String name) {
        if (links == null || links.isEmpty()) {
            return (null);
        }

        return (links.get(name));
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
