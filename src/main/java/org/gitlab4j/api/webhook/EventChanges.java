package org.gitlab4j.api.webhook;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.gitlab4j.api.utils.JacksonJson;

@XmlAccessorType(XmlAccessType.FIELD)
public class EventChanges {

    private List<Integer> updatedById;
    private List<Date> updatedAt;
    private Map<String, List<EventLabel>> labels;

    public List<Integer> getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(List<Integer> updatedById) {
        this.updatedById = updatedById;
    }

    public List<Date> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(List<Date> updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, List<EventLabel>> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, List<EventLabel>> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
