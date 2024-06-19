package org.gitlab4j.api.models;

import org.gitlab4j.api.GitLabApiForm;
import org.gitlab4j.api.TopicsApi;

import java.io.File;
import java.io.Serializable;

/**
 * This class is utilized by the {@link TopicsApi#createTopic(TopicParams)}
 * and {@link TopicsApi#updateTopic(Integer, TopicParams)} methods to set
 * the parameters for the call to the GitLab API.
 *
 * Avatar Upload has its own Upload in {@link TopicsApi#updateTopicAvatar(Integer,File)}
 */
public class TopicParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String title;
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
    public GitLabApiForm getForm(boolean isCreate) {

        GitLabApiForm form = new GitLabApiForm()
            .withParam("name", name, isCreate)
            .withParam("title", title, isCreate)
            .withParam("description", description);

        return (form);
    }
}
