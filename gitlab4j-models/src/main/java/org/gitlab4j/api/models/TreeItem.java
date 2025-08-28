package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TreeItem implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Type {
        TREE,
        BLOB,
        COMMIT;

        @Override
        public String toString() {
            return (name().toLowerCase());
        }
    }

    @JsonProperty("id")
    private String id;

    @JsonProperty("mode")
    private String mode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("path")
    private String path;

    @JsonProperty("type")
    private Type type;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
