package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.GitLabForm;
import org.gitlab4j.models.utils.ISO8601;
import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ChangelogPayload implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The version associated with the changelog.
     */
    @JsonProperty("version")
    private String version;

    /**
     * The reference from which the changelog is generated.
     */
    @JsonProperty("from")
    private String from;

    /**
     * The reference to which the changelog is generated.
     */
    @JsonProperty("to")
    private String to;

    /**
     * The date associated with the changelog.
     */
    @JsonProperty("date")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date date;

    /**
     * The branch associated with the changelog.
     */
    @JsonProperty("branch")
    private String branch;

    /**
     * The trailer information associated with the changelog.
     */
    @JsonProperty("trailer")
    private String trailer;

    /**
     * The file associated with the changelog.
     */
    @JsonProperty("file")
    private String file;

    /**
     * The message associated with the changelog.
     */
    @JsonProperty("message")
    private String message;

    public ChangelogPayload(String version) {
        this.version = version;
    }

    @JsonIgnore
    public GitLabForm getFormData() {
        return new GitLabForm()
                .withParam("version", version, true)
                .withParam("from", from)
                .withParam("to", to)
                .withParam("date", ISO8601.dateOnly(date))
                .withParam("branch", branch)
                .withParam("trailer", trailer)
                .withParam("file", file)
                .withParam("message", message);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
