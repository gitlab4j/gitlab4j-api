package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class HealthCheckStatusDetail extends HealthCheckStatus {
    private HealthCheckStatusLabel labels;

    public HealthCheckStatusLabel getLabels() {
        return this.labels;
    }

    public void setLabels(HealthCheckStatusLabel labels) {
        this.labels = labels;
    }
}
