package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReadinessHealthCheck {

    private HealthCheckStatusDetail dbCheck;
    private HealthCheckStatusDetail redisCheck;
    private HealthCheckStatusDetail cacheCheck;
    private HealthCheckStatusDetail queuesCheck;
    private HealthCheckStatusDetail sharedStateCheck;
    private HealthCheckStatusDetail fsShardsCheck;
    private HealthCheckStatusDetail gitalyCheck;

    public HealthCheckStatusDetail getDbCheck() {
        return this.dbCheck;
    }

    public void setDbCheck(HealthCheckStatusDetail dbCheck) {
        this.dbCheck = dbCheck;
    }

    public HealthCheckStatusDetail getRedisCheck() {
        return this.redisCheck;
    }

    public void setRedisCheck(HealthCheckStatusDetail redisCheck) {
        this.redisCheck = redisCheck;
    }

    public HealthCheckStatusDetail getCacheCheck() {
        return this.cacheCheck;
    }

    public void setCacheCheck(HealthCheckStatusDetail cacheCheck) {
        this.cacheCheck = cacheCheck;
    }

    public HealthCheckStatusDetail getQueuesCheck() {
        return this.queuesCheck;
    }

    public void setQueuesCheck(HealthCheckStatusDetail queuesCheck) {
        this.queuesCheck = queuesCheck;
    }

    public HealthCheckStatusDetail getSharedStateCheck() {
        return this.sharedStateCheck;
    }

    public void setSharedStateCheck(HealthCheckStatusDetail sharedStateCheck) {
        this.sharedStateCheck = sharedStateCheck;
    }

    public HealthCheckStatusDetail getFsShardsCheck() {
        return this.fsShardsCheck;
    }

    public void setFsShardsCheck(HealthCheckStatusDetail fsShardsCheck) {
        this.fsShardsCheck = fsShardsCheck;
    }

    public HealthCheckStatusDetail getGitalyCheck() {
        return this.gitalyCheck;
    }

    public void setGitalyCheck(HealthCheckStatusDetail gitalyCheck) {
        this.gitalyCheck = gitalyCheck;
    }
}
