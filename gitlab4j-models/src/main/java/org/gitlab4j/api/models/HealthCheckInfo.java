package org.gitlab4j.api.models;

import java.io.IOException;
import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class HealthCheckInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The health check for the database.
     */
    @JsonProperty("db_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
    private HealthCheckItem dbCheck;

    /**
     * The health check for Redis.
     */
    @JsonProperty("redis_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
    private HealthCheckItem redisCheck;

    /**
     * The health check for the cache.
     */
    @JsonProperty("cache_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
    private HealthCheckItem cacheCheck;

    /**
     * The health check for queues.
     */
    @JsonProperty("queues_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
    private HealthCheckItem queuesCheck;

    /**
     * The health check for shared state.
     */
    @JsonProperty("shared_state_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
    private HealthCheckItem sharedStateCheck;

    /**
     * The health check for file system shards.
     */
    @JsonProperty("fs_shards_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
    private HealthCheckItem fsShardsCheck;

    /**
     * The health check for Gitaly.
     */
    @JsonProperty("gitaly_check")
    @JsonDeserialize(using = HealthCheckItemDeserializer.class)
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

    /**
     * This desrializer can deserialize on object containing a HealthCheckItem or an
     * array containing a single HealthCheckItem.
     */
    private static class HealthCheckItemDeserializer extends JsonDeserializer<HealthCheckItem> {

        private static final ObjectMapper mapper = new JacksonJson().getObjectMapper();

        @Override
        public HealthCheckItem deserialize(JsonParser jsonParser, DeserializationContext ctx)
                throws IOException, JsonProcessingException {

            HealthCheckItem healthCheckItem = null;
            JsonNode tree = jsonParser.readValueAsTree();
            if (tree.isArray()) {
                JsonNode node = tree.get(0);
                healthCheckItem = mapper.treeToValue(node, HealthCheckItem.class);
            } else if (tree.isObject()) {
                healthCheckItem = mapper.treeToValue(tree, HealthCheckItem.class);
            }

            return (healthCheckItem);
        }
    }
}
