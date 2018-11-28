package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.gitlab4j.api.utils.JacksonJson;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Markdown {

    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
