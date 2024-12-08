package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Discussion implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the discussion.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Indicates if the discussion is an individual note.
     */
    @JsonProperty("individual_note")
    private Boolean individualNote;

    /**
     * The list of notes associated with the discussion.
     */
    @JsonProperty("notes")
    private List<Note> notes;

    public String getId() {
        return id;
    }

    public Boolean getIndividualNote() {
        return individualNote;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIndividualNote(Boolean individualNote) {
        this.individualNote = individualNote;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
