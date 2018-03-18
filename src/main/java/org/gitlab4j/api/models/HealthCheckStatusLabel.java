package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class HealthCheckStatusLabel {
    private String shard;

    public String getShard() {
        return this.shard;
    }

    public void setShard(String shard) {
        this.shard = shard;
    }
}
