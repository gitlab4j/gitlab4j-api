package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Namespace {

    private Integer id;
    private String name;
    private String path;
    private String kind;
    private String fullPath;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Namespace withId(Integer id) {
        this.id = id;
        return this;
    }

    public Namespace withName(String name) {
        this.name = name;
        return this;
    }

    public Namespace withPath(String path) {
        this.path = path;
        return this;
    }

    public Namespace withKind(String kind) {
        this.kind = kind;
        return this;
    }

    public Namespace withFullPath(String fullPath) {
        this.fullPath = fullPath;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
