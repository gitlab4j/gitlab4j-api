package org.gitlab4j.api.models;

import org.gitlab4j.models.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectTemplateType {
    DOCKERFILES,
    GITIGNORES,
    GITLAB_CI_YMLS,
    LICENSES,
    ISSUES,
    MERGE_REQUESTS;

    private static final JacksonJsonEnumHelper<ProjectTemplateType> enumHelper =
            new JacksonJsonEnumHelper<>(ProjectTemplateType.class);

    @JsonCreator
    public static ProjectTemplateType forValue(String value) {
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
