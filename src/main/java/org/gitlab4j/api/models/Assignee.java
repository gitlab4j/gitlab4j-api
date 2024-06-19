
package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Assignee extends AbstractUser<Assignee> {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
