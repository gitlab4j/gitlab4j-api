package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Topic {

    private Integer id;

    private String name;

    private String title;

    private String description;

    private int total_projects_count;

    private String avatar_url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal_projects_count() {
        return total_projects_count;
    }

    public void setTotal_projects_count(int total_projects_count) {
        this.total_projects_count = total_projects_count;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
