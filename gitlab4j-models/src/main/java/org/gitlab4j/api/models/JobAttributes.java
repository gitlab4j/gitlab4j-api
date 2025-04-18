package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobAttributes implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("job_variables_attributes")
    private List<JobAttribute> jobAttributes;

    public JobAttributes(List<JobAttribute> jobAttributes) {
        this.jobAttributes = jobAttributes;
    }

    public List<JobAttribute> getJobAttributes() {
        return jobAttributes;
    }

    public void setJobAttributes(List<JobAttribute> jobAttributes) {
        this.jobAttributes = jobAttributes;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
