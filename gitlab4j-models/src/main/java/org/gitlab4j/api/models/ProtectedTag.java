package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

public class ProtectedTag implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class CreateAccessLevel implements Serializable {
        private static final long serialVersionUID = 1L;

        private AccessLevel access_level;
        private String accessLevelDescription;

        public AccessLevel getAccess_level() {
            return access_level;
        }

        public void setAccess_level(AccessLevel access_level) {
            this.access_level = access_level;
        }

        public String getAccessLevelDescription() {
            return accessLevelDescription;
        }

        public void setAccessLevelDescription(String accessLevelDescription) {
            this.accessLevelDescription = accessLevelDescription;
        }
    }

    private String name;
    private List<CreateAccessLevel> createAccessLevels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateAccessLevel> getCreateAccessLevels() {
        return createAccessLevels;
    }

    public void setCreateAccessLevels(List<CreateAccessLevel> createAccessLevels) {
        this.createAccessLevels = createAccessLevels;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
