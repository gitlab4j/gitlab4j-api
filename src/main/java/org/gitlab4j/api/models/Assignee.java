
package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class Assignee extends AbstractUser<Assignee> {

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
