package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class HealthCheckInfo {
    private HealthCheckItem dbCheck;
    private HealthCheckItem redisCheck;
    private HealthCheckItem cacheCheck;
    private HealthCheckItem queuesCheck;
    private HealthCheckItem sharedStateCheck;
    private HealthCheckItem fsShardsCheck;
    private HealthCheckItem gitalyCheck;

    public HealthCheckItem getDbCheck() {
        return this.dbCheck;
    }

    public void setDbCheck(HealthCheckItem dbCheck) {
        this.dbCheck = dbCheck;
    }

    public HealthCheckItem getRedisCheck() {
        return this.redisCheck;
    }

    public void setRedisCheck(HealthCheckItem redisCheck) {
        this.redisCheck = redisCheck;
    }

    public HealthCheckItem getCacheCheck() {
        return this.cacheCheck;
    }

    public void setCacheCheck(HealthCheckItem cacheCheck) {
        this.cacheCheck = cacheCheck;
    }

    public HealthCheckItem getQueuesCheck() {
        return this.queuesCheck;
    }

    public void setQueuesCheck(HealthCheckItem queuesCheck) {
        this.queuesCheck = queuesCheck;
    }

    public HealthCheckItem getSharedStateCheck() {
        return this.sharedStateCheck;
    }

    public void setSharedStateCheck(HealthCheckItem sharedStateCheck) {
        this.sharedStateCheck = sharedStateCheck;
    }

    public HealthCheckItem getFsShardsCheck() {
        return this.fsShardsCheck;
    }

    public void setFsShardsCheck(HealthCheckItem fsShardsCheck) {
        this.fsShardsCheck = fsShardsCheck;
    }

    public HealthCheckItem getGitalyCheck() {
        return this.gitalyCheck;
    }

    public void setGitalyCheck(HealthCheckItem gitalyCheck) {
        this.gitalyCheck = gitalyCheck;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
