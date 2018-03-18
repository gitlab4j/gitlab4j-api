package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LivenessHealthCheck {
    private HealthCheckStatus dbCheck;
    private HealthCheckStatus redisCheck;
    private HealthCheckStatus cacheCheck;
    private HealthCheckStatus queuesCheck;
    private HealthCheckStatus sharedStateCheck;
    private HealthCheckStatus fsShardsCheck;
    private HealthCheckStatus gitalyCheck;

    public HealthCheckStatus getDbCheck() {
        return this.dbCheck;
    }

    public void setDbCheck(HealthCheckStatus dbCheck) {
        this.dbCheck = dbCheck;
    }

    public HealthCheckStatus getRedisCheck() {
        return this.redisCheck;
    }

    public void setRedisCheck(HealthCheckStatus redisCheck) {
        this.redisCheck = redisCheck;
    }

    public HealthCheckStatus getCacheCheck() {
        return this.cacheCheck;
    }

    public void setCacheCheck(HealthCheckStatus cacheCheck) {
        this.cacheCheck = cacheCheck;
    }

    public HealthCheckStatus getQueuesCheck() {
        return this.queuesCheck;
    }

    public void setQueuesCheck(HealthCheckStatus queuesCheck) {
        this.queuesCheck = queuesCheck;
    }

    public HealthCheckStatus getSharedStateCheck() {
        return this.sharedStateCheck;
    }

    public void setSharedStateCheck(HealthCheckStatus sharedStateCheck) {
        this.sharedStateCheck = sharedStateCheck;
    }

    public HealthCheckStatus getFsShardsCheck() {
        return this.fsShardsCheck;
    }

    public void setFsShardsCheck(HealthCheckStatus fsShardsCheck) {
        this.fsShardsCheck = fsShardsCheck;
    }

    public HealthCheckStatus getGitalyCheck() {
        return this.gitalyCheck;
    }

    public void setGitalyCheck(HealthCheckStatus gitalyCheck) {
        this.gitalyCheck = gitalyCheck;
    }
}
