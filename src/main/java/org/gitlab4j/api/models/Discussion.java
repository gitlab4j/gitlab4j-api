package org.gitlab4j.api.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.gitlab4j.api.utils.JacksonJson;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Discussion {

  private String id;
  private String individualNote;
  private List<Note> notes;

  public String getId() {
    return id;
  }

  public String getIndividualNote() {
    return individualNote;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setIndividualNote(String individualNote) {
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
