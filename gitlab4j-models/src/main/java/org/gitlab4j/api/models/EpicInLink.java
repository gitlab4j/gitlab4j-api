package org.gitlab4j.api.models;

import org.gitlab4j.models.utils.JacksonJson;

public class EpicInLink extends AbstractEpic<EpicInLink> {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
