package org.gitlab4j.api.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalWikiService extends NotificationService {

    public static final String WIKIURL_KEY_PROP = "external_wiki_url";

    public String getExternalWikiUrl() {
        return this.getProperty(WIKIURL_KEY_PROP);
    }

    public void setExternalWikiUrl(String endpoint) {
        this.setProperty(WIKIURL_KEY_PROP, endpoint);
    }


    public ExternalWikiService withExternalWikiUrl(String endpoint) {
        setExternalWikiUrl(endpoint);
        return this;
    }
}
