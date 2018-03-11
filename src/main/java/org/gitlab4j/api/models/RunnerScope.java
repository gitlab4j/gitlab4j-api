package org.gitlab4j.api.models;

public enum RunnerScope {
    SPECIFIC("specific"),
    SHARED("shared"),
    ACTIVE("active"),
    ONLINE("online"),
    PAUSED("paused");

    private String value;

    RunnerScope(String value) {
        this.value = value;
    }

    public String toValue() {
        return this.value;
    }
}
