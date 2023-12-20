package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class EpicInLink extends AbstractEpic<EpicInLink> {
    private static final long serialVersionUID = 3050569265982322906L;

    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
