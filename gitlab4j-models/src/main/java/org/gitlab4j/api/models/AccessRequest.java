package org.gitlab4j.api.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessRequest extends AbstractUser<AccessRequest> {
    private static final long serialVersionUID = 1L;

    /**
     * The date when the access request was made.
     * Expected in ISO 8601 format (2019-03-15T08:00:00Z).
     */
    @JsonProperty("requested_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
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
