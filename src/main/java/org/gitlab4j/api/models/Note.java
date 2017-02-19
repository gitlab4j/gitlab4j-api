package org.gitlab4j.api.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Note {

    public static enum NotableType {
        ISSUE("Issue"), MERGE_REQUEST("MergeRequest"), SNIPPET("Snippet");

        private String name;

        NotableType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return (name);
        }
    }

    private String attachment;
    private Author author;
    private String body;
    private Date createdAt;
    private Boolean downvote;
    private Date expiresAt;
    private String fileName;
    private Integer id;
    private Integer noteableId;
    private NotableType noteableType;
    private Boolean system;
    private String title;
    private String updatedAt;
    private Boolean upvote;

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDownvote() {
        return downvote;
    }

    public void setDownvote(Boolean downvote) {
        this.downvote = downvote;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoteableId() {
        return noteableId;
    }

    public void setNoteableId(Integer noteableId) {
        this.noteableId = noteableId;
    }

    public NotableType getNoteableType() {
        return noteableType;
    }

    public void setNoteableType(NotableType noteableType) {
        this.noteableType = noteableType;
    }

    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }
}
