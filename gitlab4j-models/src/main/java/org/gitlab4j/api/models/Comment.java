package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;

import org.gitlab4j.models.Constants.LineType;
import org.gitlab4j.models.utils.JacksonJson;
import org.gitlab4j.models.utils.MultiDateFormatDeserializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The author of the comment.
     */
    @JsonProperty("author")
    private Author author;

    /**
     * The creation date of the comment.
     */
    @JsonProperty("created_at")
    @JsonDeserialize(using = MultiDateFormatDeserializer.class)
    private Date createdAt;

    /**
     * The type of the line (e.g., new or old).
     */
    @JsonProperty("line_type")
    private LineType lineType;

    /**
     * The path to the file being commented on.
     */
    @JsonProperty("path")
    private String path;

    /**
     * The line number of the comment.
     */
    @JsonProperty("line")
    private Integer line;

    /**
     * The content of the comment.
     */
    @JsonProperty("note")
    private String note;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
