package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class ChildEpic extends AbstractEpic<ChildEpic> {

    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
