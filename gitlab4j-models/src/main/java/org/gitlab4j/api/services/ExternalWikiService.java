package org.gitlab4j.api.services;

import org.gitlab4j.models.GitLabForm;

public class ExternalWikiService extends NotificationService {

    public static final String WIKIURL_KEY_PROP = "external_wiki_url";

    /**
     * Get the form data for this service based on it's properties.
     *
     * @return the form data for this service based on it's properties
     */
    @Override
    public GitLabForm servicePropertiesForm() {
        GitLabForm formData = new GitLabForm().withParam("external_wiki_url", getExternalWikiUrl(), true);
        return formData;
    }

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
