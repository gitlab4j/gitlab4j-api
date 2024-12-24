package org.gitlab4j.models;

public class GitLabFormValue {

    private Object value;
    private GitLabFormValueType type;
    private boolean required;

    public GitLabFormValue(Object value, GitLabFormValueType type, boolean required) {
        super();
        this.value = value;
        this.type = type;
        this.required = required;
    }

    public Object getValue() {
        return value;
    }

    public GitLabFormValueType getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }

    @Override
    public String toString() {
        return "GitLabFormValue [value=" + value + ", type=" + type + ", required=" + required + "]";
    }
}
