package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.Base64;

import org.gitlab4j.models.Constants.Encoding;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is used as input when creating a file in a repository.
 *
 * @see <a href="https://docs.gitlab.com/api/repository_files/#create-a-file-in-a-repository">Create a file in a repository</a>
 */
public class RepositoryFileCreate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String filePath;
    private String startBranch;
    private Encoding encoding;
    private String authorEmail;
    private String authorName;
    private String content;
    private Boolean executeFilemode;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public RepositoryFileCreate withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getStartBranch() {
        return startBranch;
    }

    public void setStartBranch(String startBranch) {
        this.startBranch = startBranch;
    }

    public RepositoryFileCreate withStartBranch(String startBranch) {
        this.startBranch = startBranch;
        return this;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public RepositoryFileCreate withEncoding(Encoding encoding) {
        this.encoding = encoding;
        return this;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public RepositoryFileCreate withAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public RepositoryFileCreate withAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RepositoryFileCreate withContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getExecuteFilemode() {
        return executeFilemode;
    }

    public void setExecuteFilemode(Boolean executeFilemode) {
        this.executeFilemode = executeFilemode;
    }

    public RepositoryFileCreate withExecuteFilemode(Boolean executeFilemode) {
        this.executeFilemode = executeFilemode;
        return this;
    }

    /**
     * Encodes the provided String using Base64 and sets it as the content.
     * The encoding property of this instance will be set to base64.
     *
     * @param content the String content to encode and set as the base64 encoded String content
     */
    @JsonIgnore
    public void encodeAndSetContent(String content) {
        encodeAndSetContent(content != null ? content.getBytes() : null);
    }

    /**
     * Encodes the provided byte array using Base64 and sets it as the content.
     * The encoding property of this instance will be set to base64.
     *
     * @param byteContent the byte[] content to encode and set as the base64 encoded String content
     */
    @JsonIgnore
    public void encodeAndSetContent(byte[] byteContent) {
        if (byteContent == null) {
            this.content = null;
            return;
        }
        this.content = Base64.getEncoder().encodeToString(byteContent);
        this.encoding = Encoding.BASE64;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
