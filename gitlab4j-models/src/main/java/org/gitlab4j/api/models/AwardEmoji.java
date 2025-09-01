package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class AwardEmoji implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum AwardableType {
        ISSUE,
        MERGE_REQUEST,
        NOTE,
        SNIPPET;

        private static JacksonJsonEnumHelper<AwardableType> enumHelper =
                new JacksonJsonEnumHelper<>(AwardableType.class, true);

        @JsonCreator
        public static AwardableType forValue(String value) {
            return enumHelper.forValue(value);
        }

        @JsonValue
        public String toValue() {
            return (enumHelper.toString(this));
        }

        @Override
        public String toString() {
            return (enumHelper.toString(this));
        }
    }

    /**
     * The unique identifier of the award emoji.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the award emoji.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The user associated with the award emoji.
     */
    @JsonProperty("user")
    private User user;

    /**
     * The date when the award emoji was created.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The date when the award emoji was last updated.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedAt;

    /**
     * The ID of the awardable entity.
     */
    @JsonProperty("awardable_id")
    private Long awardableId;

    /**
     * The type of the awardable entity.
     */
    @JsonProperty("awardable_type")
    private AwardableType awardableType;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getAwardableId() {
        return awardableId;
    }

    public void setAwardableId(Long awardableId) {
        this.awardableId = awardableId;
    }

    public AwardableType getAwardableType() {
        return awardableType;
    }

    public void setAwardableType(AwardableType awardableType) {
        this.awardableType = awardableType;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
