
package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

public class Member {

    private AccessLevel accessLevel;
    private Date createdAt;
    private Integer id;
    private String name;
    private String state;
    private String username;

    public AccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member withAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    public Member withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Member withId(Integer id) {
        this.id = id;
        return this;
    }

    public Member withState(String state) {
        this.state = state;
        return this;
    }

    public Member withUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
