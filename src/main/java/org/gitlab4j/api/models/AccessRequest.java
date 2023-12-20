package org.gitlab4j.api.models;

import java.util.Date;

public class AccessRequest extends AbstractUser<AccessRequest> {
    private static final long serialVersionUID = 1L;

    private Date requestedAt;
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
