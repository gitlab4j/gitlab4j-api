
package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.api.utils.JacksonJson;

public class DeployKey {

    private Integer id;
    private String title;
    private String key;
    private Boolean canPush;
    private Date createdAt;
 
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCanPush() {
        return canPush;
    }

    public void setCanPush(Boolean canPush) {
        this.canPush = canPush;
    }
    
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
