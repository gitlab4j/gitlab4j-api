package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtifactsFile {

    private String filename;
    private Integer size;

    public String getFilename() {
    return filename;
  }

    public void setFilename(String filename) {
    this.filename = filename;
  }

    public Integer getSize() {
    return size;
  }

    public void setSize(Integer size) {
    this.size = size;
  }
}
