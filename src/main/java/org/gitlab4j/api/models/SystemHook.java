package org.gitlab4j.api.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SystemHook {

    private Integer id;
    private String url;
    private Date createdAt;
    private Boolean pushEvents;
    private Boolean tagPushEvents;
    private Boolean enableSslVerification;
    private Boolean repositoryUpdateEvents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPushEvents() {
        return pushEvents;
    }

    public void setPushEvents(Boolean pushEvents) {
        this.pushEvents = pushEvents;
    }

    public Boolean getTagPushEvents() {
        return tagPushEvents;
    }

    public void setTagPushEvents(Boolean tagPushEvents) {
        this.tagPushEvents = tagPushEvents;
    }

    public Boolean getEnableSslVerification() {
        return enableSslVerification;
    }

    public void setEnableSslVerification(Boolean enableSslVerification) {
        this.enableSslVerification = enableSslVerification;
    }

    public void setRepositoryUpdateEvents(Boolean repositoryUpdateEvents) {
        this.repositoryUpdateEvents = repositoryUpdateEvents;
    }

    public Boolean getRepositoryUpdateEvents() {
        return repositoryUpdateEvents;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SystemHook{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", pushEvents=").append(pushEvents);
        sb.append(", tagPushEvents=").append(tagPushEvents);
        sb.append(", enableSslVerification=").append(enableSslVerification);
        sb.append(", repositoryUpdateEvents=").append(repositoryUpdateEvents);
        sb.append('}');
        return sb.toString();
    }

}
