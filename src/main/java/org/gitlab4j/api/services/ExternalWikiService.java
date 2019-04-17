package org.gitlab4j.api.services;

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
