package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.GitLabForm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is utilized by the <code>org.gitlab4j.api.TopicsApi#createTopic(TopicParams)</code>
 * and <code>org.gitlab4j.api.TopicsApi#updateTopic(Integer, TopicParams)</code> methods to set
 * the parameters for the call to the GitLab API.
 *
 * Avatar Upload has its own Upload in <code>org.gitlab4j.api.TopicsApi#updateTopicAvatar(Integer,File)</code>
 */
public class TopicParams implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    public TopicParams withName(String name) {
        this.name = name;
        return (this);
    }

    public TopicParams withTitle(String title) {
        this.title = title;
        return (this);
    }

    public TopicParams withDescription(String description) {
        this.description = description;
        return (this);
    }

    /**
     * Get the form params for a group create oir update call.
     *
     * @param isCreate set to true for a create group call, false for update
     * @return a GitLabApiForm instance holding the parameters for the group create or update operation
     * @throws RuntimeException if required parameters are missing
     */
    public GitLabForm getForm(boolean isCreate) {

        GitLabForm form = new GitLabForm()
                .withParam("name", name, isCreate)
                .withParam("title", title, isCreate)
                .withParam("description", description);

        return (form);
    }
}
