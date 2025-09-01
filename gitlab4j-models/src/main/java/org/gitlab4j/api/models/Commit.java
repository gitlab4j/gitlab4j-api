package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Commit implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The author of the commit.
     */
    @JsonProperty("author")
    private Author author;

    /**
     * The date when the commit was authored.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("authored_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date authoredDate;

    /**
     * The email of the author.
     */
    @JsonProperty("author_email")
    private String authorEmail;

    /**
     * The name of the author.
     */
    @JsonProperty("author_name")
    private String authorName;

    /**
     * The date when the commit was committed.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("committed_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date committedDate;

    /**
     * The email of the committer.
     */
    @JsonProperty("committer_email")
    private String committerEmail;

    /**
     * The name of the committer.
     */
    @JsonProperty("committer_name")
    private String committerName;

    /**
     * The creation date of the commit.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    /**
     * The unique identifier of the commit.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The commit message.
     */
    @JsonProperty("message")
    private String message;

    /**
     * The list of parent commit IDs.
     */
    @JsonProperty("parent_ids")
    private List<String> parentIds;

    /**
     * The shortened commit ID.
     */
    @JsonProperty("short_id")
    private String shortId;

    /**
     * The statistics associated with the commit.
     */
    @JsonProperty("stats")
    private CommitStats stats;

    /**
     * The status of the commit.
     */
    @JsonProperty("status")
    private String status;

    /**
     * The timestamp of the commit.
     * Expected in format "2019-03-15T08:00:00.000Z".
     */
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date timestamp;

    /**
     * The title of the commit.
     */
    @JsonProperty("title")
    private String title;

    /**
     * The URL of the commit.
     */
    @JsonProperty("url")
    private String url;

    /**
     * The web URL of the commit.
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * The project ID associated with the commit.
     */
    @JsonProperty("project_id")
    private Long projectId;

    /**
     * The pipeline associated with the commit.
     */
    @JsonProperty("last_pipeline")
    private Pipeline lastPipeline;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getAuthoredDate() {
        return authoredDate;
    }

    public void setAuthoredDate(Date authoredDate) {
        this.authoredDate = authoredDate;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCommittedDate() {
        return committedDate;
    }

    public void setCommittedDate(Date committedDate) {
        this.committedDate = committedDate;
    }

    public String getCommitterEmail() {
        return committerEmail;
    }

    public void setCommitterEmail(String committerEmail) {
        this.committerEmail = committerEmail;
    }

    public String getCommitterName() {
        return committerName;
    }

    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<String> parentIds) {
        this.parentIds = parentIds;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public CommitStats getStats() {
        return stats;
    }

    public void setStats(CommitStats stats) {
        this.stats = stats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Pipeline getLastPipeline() {
        return lastPipeline;
    }

    public void setLastPipeline(Pipeline lastPipeline) {
        this.lastPipeline = lastPipeline;
    }

    public Commit withAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Commit withAuthoredDate(Date authoredDate) {
        this.authoredDate = authoredDate;
        return this;
    }

    public Commit withAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return this;
    }

    public Commit withAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Commit withCommittedDate(Date committedDate) {
        this.committedDate = committedDate;
        return this;
    }

    public Commit withCommitterEmail(String committerEmail) {
        this.committerEmail = committerEmail;
        return this;
    }

    public Commit withCommitterName(String committerName) {
        this.committerName = committerName;
        return this;
    }

    public Commit withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Commit withId(String id) {
        this.id = id;
        return this;
    }

    public Commit withMessage(String message) {
        this.message = message;
        return this;
    }

    public Commit withParentIds(List<String> parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    /**
     * @deprecated use {@link #withShortId(String)} instead
     */
    @Deprecated
    public Commit withShorwId(String shortId) {
        return withShortId(shortId);
    }

    public Commit withShortId(String shortId) {
        this.shortId = shortId;
        return this;
    }

    public Commit withStats(CommitStats stats) {
        this.stats = stats;
        return this;
    }

    public Commit withStatus(String status) {
        this.status = status;
        return this;
    }

    public Commit withTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Commit withTitle(String title) {
        this.title = title;
        return this;
    }

    public Commit withUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
