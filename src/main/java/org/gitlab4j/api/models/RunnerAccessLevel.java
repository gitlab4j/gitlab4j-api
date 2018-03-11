package org.gitlab4j.api.models;

public enum RunnerAccessLevel {
    NOT_PROTECTED("not_protected"),
    REF_PROTECTED("ref_protected");

    private String value;

    RunnerAccessLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
