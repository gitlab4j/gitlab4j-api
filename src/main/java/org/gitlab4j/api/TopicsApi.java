package org.gitlab4j.api;

import org.gitlab4j.api.models.Topic;
import org.gitlab4j.api.models.TopicParams;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TopicsApi extends AbstractApi{

    public TopicsApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * <p>Get a list of Topics. </p>
     *
     * <strong>WARNING:</strong> Do not use this method to fetch Topics from https://gitlab.com,
     * gitlab.com has many 1,000's of public topics and it will a long time to fetch all of them.
     * Instead use {@link #getTopics(int itemsPerPage)} which will return a Pager of Topic instances.
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @return the list of topics viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public List<Topic> getTopics() throws GitLabApiException {
        return (getTopics(getDefaultPerPage()).all());
    }

    /**
     * Get a list of topics in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @param page the page to get
     * @param perPage the number of Topic instances per page
     * @return the list of topics
     * @throws GitLabApiException if any exception occurs
     */
    public List<Topic> getTopics(int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "topics");
        return (response.readEntity(new GenericType<List<Topic>>() {}));
    }

    /**
     * Get a Pager of topics.
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @param itemsPerPage the number of Topic instances that will be fetched per page
     * @return the pager of topics
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Topic> getTopics(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Topic>(this, Topic.class, itemsPerPage, null, "topics"));
    }

    /**
     * Get a Stream of topics.
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @return the stream of topics
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Topic> getTopicsStream() throws GitLabApiException {
        return (getTopics(getDefaultPerPage()).stream());
    }

    /**
     * Get all details of a topic.
     *
     * <pre><code>GitLab Endpoint: GET /topics/:id</code></pre>
     *
     * @param id the topic ID
     * @return the topic for the specified topic id
     * @throws GitLabApiException if any exception occurs
     */
    public Topic getTopic(Integer id) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "topics", id);
        return (response.readEntity(Topic.class));
    }

    /**
     * Get all details of a topic as an Optional instance.
     *
     * <pre><code>GitLab Endpoint: GET /topics/:id</code></pre>
     *
     * @param id the topic ID
     * @return the Topic for the specified topic id as an Optional instance
     */
    public Optional<Topic> getOptionalTopic(Integer id) {
        try {
            return (Optional.ofNullable(getTopic(id)));
        } catch (GitLabApiException glae) {
            return (GitLabApi.createOptionalFromException(glae));
        }
    }

    /**
     * Creates a new Topic. Available only for users who can create topics.
     *
     * <pre><code>GitLab Endpoint: POST /topics</code></pre>
     *
     * @param params a TopicParams instance holding the parameters for the topic creation
     * @return the created Topic instance
     * @throws GitLabApiException if any exception occurs
     */
    public Topic createTopic(TopicParams params) throws GitLabApiException {
        Response response = post(Response.Status.CREATED, params.getForm(true), "topics");
        return (response.readEntity(Topic.class));
    }

    /**
     * Update a project topic.
     *
     * <pre><code>GitLab Endpoint: PUT /topics/:id</code></pre>
     *
     * @param id the topic id
     * @param params a TopicParams instance holding the properties to Update
     * @return the updated Topic instance
     * @throws GitLabApiException at any exception
     */
    public Topic updateTopic(Integer id, TopicParams params) throws GitLabApiException {
        Response response = putWithFormData(Response.Status.OK,
            params.getForm(false), "topics",  id);
        return (response.readEntity(Topic.class));
    }

    /**
     * Uploads and sets the topic's avatar for the specified topic.
     *
     * <pre><code>GitLab Endpoint: PUT /topics/:id</code></pre>
     *
     * @param id the topic in the form of an Integer
     * @param avatarFile the File instance of the avatar file to upload
     * @return the updated Topic instance
     * @throws GitLabApiException if any exception occurs
     */
    public Topic updateTopicAvatar(final Integer id, File avatarFile) throws GitLabApiException {
        Response response = putUpload(Response.Status.OK, "avatar", avatarFile,  "topics", id);
        return (response.readEntity(Topic.class));
    }

    /**
     * Delete the topic's avatar for the specified topic.
     *
     * <pre><code>GitLab Endpoint: PUT /topics/:id</code></pre>
     *
     * @param id the topic in the form of an Integer
     * @return the updated Topic instance
     * @throws GitLabApiException if any exception occurs
     */
    public Topic deleteTopicAvatar(final Integer id) throws GitLabApiException {
        Response response = putUpload(Response.Status.OK, "avatar", null,  "topics", id);
        return (response.readEntity(Topic.class));
    }

    /**
     * Delete a topic. You must be an administrator to delete a project topic. When you delete a project topic, you also delete the topic assignment for projects.
     *
     * <pre><code>GitLab Endpoint: DELETE /topics/:id</code></pre>
     *
     * @param id the topic to deleted in the form of an Integer
     * @throws GitLabApiException if any exception occurs
     */
    public void deleteTopic(Integer id) throws GitLabApiException {
        if(isApiVersion(GitLabApi.ApiVersion.V3)){
            throw new GitLabApiException("Topics need api v4+");
        }
        delete(Response.Status.NO_CONTENT,null, "topics", id);
    }

    /**
     * Merge two topics together. You must be an administrator to merge a source topic into a target topic. When you merge topics, you delete the source topic and move all assigned projects to the target topic.
     *
     * <pre><code>GitLab Endpoint: POST /topics/merge</code></pre>
     *
     * @param sourceTopicId ID of source project topic
     * @param targetTopicId ID of target project topic
     * @return the merged Topic instance
     * @throws GitLabApiException if any exception occurs
     */
    public Topic mergeTopics(Integer sourceTopicId, Integer targetTopicId) throws GitLabApiException {
        Response response = post(Response.Status.OK,new GitLabApiForm().withParam("source_topic_id",sourceTopicId).withParam("target_topic_id",targetTopicId),"topics/merge");
        return (response.readEntity(Topic.class));
    }
}
