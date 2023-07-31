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
     * Get a list of topics (As user: my , as admin: all groups) and in the specified page range.
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @param page the page to get
     * @param perPage the number of Group instances per page
     * @return the list of groups viewable by the authenticated userin the specified page range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Topic> getTopics(int page, int perPage) throws GitLabApiException {
        Response response = get(Response.Status.OK, getPageQueryParams(page, perPage), "topics");
        return (response.readEntity(new GenericType<List<Topic>>() {}));
    }

    /**
     * Get a Pager of groups. (As user: my groups, as admin: all groups)
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @param itemsPerPage the number of Group instances that will be fetched per page
     * @return the list of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Topic> getTopics(int itemsPerPage) throws GitLabApiException {
        return (new Pager<Topic>(this, Topic.class, itemsPerPage, null, "topics"));
    }

    /**
     * Get a Stream of groups. (As user: my groups, as admin: all groups)
     *
     * <pre><code>GitLab Endpoint: GET /topics</code></pre>
     *
     * @return a Stream of groups viewable by the authenticated user
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Topic> getGroupsStream() throws GitLabApiException {
        return (getTopics(getDefaultPerPage()).stream());
    }

    /**
     * Get all details of a group.
     *
     * <pre><code>GitLab Endpoint: GET /topics/:id</code></pre>
     *
     * @param id the group ID, path of the group, or a Group instance holding the group ID or path
     * @return the Group instance for the specified group path
     * @throws GitLabApiException if any exception occurs
     */
    public Topic getTopic(Integer id) throws GitLabApiException {
        Response response = get(Response.Status.OK, null, "topics", id);
        return (response.readEntity(Topic.class));
    }

    /**
     * Get all details of a group as an Optional instance.
     *
     * <pre><code>GitLab Endpoint: GET /topics/:id</code></pre>
     *
     * @param id the topic ID
     * @return the Topic for the specified topic id as an Optional instance
     */
    public Optional<Topic> getOptionalGroup(Integer id) {
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
     * @return the created Group instance
     * @throws GitLabApiException if any exception occurs
     */
    public Topic createTopic(TopicParams params) throws GitLabApiException {
        Response response = post(Response.Status.CREATED, params.getForm(true), "topics");
        return (response.readEntity(Topic.class));
    }

    /**
     * Updates the project group. Only available to group owners and administrators.
     *
     * <pre><code>GitLab Endpoint: PUT /groups</code></pre>
     *
     * @param id the topic I
     * @param params a TopicParams instance holding the properties to Update
     * @return updated Group instance
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
     * <pre><code>PUT /topics/:id</code></pre>
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

    public void deleteTopic(Integer id) throws GitLabApiException {
        if(isApiVersion(GitLabApi.ApiVersion.V3)){
            throw new GitLabApiException("Topics need api v4+");
        }
        delete(Response.Status.NO_CONTENT,null, "topics", id);
    }



    public Topic mergeTopics(Integer sourceTopicId, Integer targetTopicId) throws GitLabApiException {
        Response response = post(Response.Status.OK,new GitLabApiForm().withParam("source_topic_id",sourceTopicId).withParam("target_topic_id",targetTopicId),"topics/merge");
        return (response.readEntity(Topic.class));
    }


}
