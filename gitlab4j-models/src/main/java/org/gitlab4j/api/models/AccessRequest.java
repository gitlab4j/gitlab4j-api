package org.gitlab4j.api.models;

import java.util.Date;

import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class AccessRequest extends AbstractUser<AccessRequest> {
    private static final long serialVersionUID = 1L;

    /**
     * The date when the access request was made.
     */
    @JsonProperty("requested_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date requestedAt;

    /**
     * The access level requested.
     */
    @JsonProperty("access_level")
    private AccessLevel accessLevel;

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
