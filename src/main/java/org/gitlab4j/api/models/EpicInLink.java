package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class EpicInLink extends AbstractEpic<EpicInLink> {

    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
