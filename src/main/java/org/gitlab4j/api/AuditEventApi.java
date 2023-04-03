package org.gitlab4j.api;

import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import org.gitlab4j.api.models.AuditEvent;
import org.gitlab4j.api.utils.ISO8601;

/**
 * This class implements the client side API for the GitLab Instance Audit Event API.
 * See <a href="https://docs.gitlab.com/ee/api/audit_events.html">Audit Event API at GitLab</a> for more information.
 */
public class AuditEventApi extends AbstractApi {

    public AuditEventApi(final GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a List of the group audit events viewable by Maintainer or an Owner of the group.
     *
     * <pre><code>GET /audit_events/</code></pre>
     *
     * @param created_after Return group audit events created on or after the given time.
     * @param created_before Return group audit events created on or before the given time.
     * @param entityType Return audit events for the given entity type. Valid values are: User, Group, or Project.
     * @param entityId Return audit events for the given entity ID. Requires entityType attribute to be present.
     * @return a List of group Audit events
     * @throws GitLabApiException if any exception occurs
     */
    public List<AuditEvent> getAuditEvents(final Date created_after, final Date created_before, final String entityType, final Long entityId) throws GitLabApiException {
        return (getAuditEvents(created_after, created_before, entityType, entityId, getDefaultPerPage()).all());
    }

    /**
     * Get a Pager of the group audit events viewable by Maintainer or an Owner of the group.
     *
     * <pre><code>GET /audit_events</code></pre>
     *
     * @param created_after Return group audit events created on or after the given time.
     * @param created_before Return group audit events created on or before the given time.
     * @param entityType Return audit events for the given entity type. Valid values are: User, Group, or Project.
     * @param entityId Return audit events for the given entity ID. Requires entityType attribute to be present.
     * @param itemsPerPage the number of Audit Event instances that will be fetched per page
     * @return a Pager of group Audit events
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<AuditEvent> getAuditEvents(final Date created_after, final Date created_before, final String entityType, final Long entityId, final int itemsPerPage) throws GitLabApiException {
        final Form form = new GitLabApiForm()
                .withParam("created_before", ISO8601.toString(created_before, false))
                .withParam("created_after", ISO8601.toString(created_after, false))
                .withParam("entity_type", entityType)
                .withParam("entity_id", entityId);
        return (new Pager<AuditEvent>(this, AuditEvent.class, itemsPerPage, form.asMap(), "audit_events"));
    }

    /**
     * Get a Stream of the group audit events viewable by Maintainer or an Owner of the group.
     *
     * <pre><code>GET /audit_events</code></pre>
     *
     * @param created_after Return group audit events created on or after the given time.
     * @param created_before Return group audit events created on or before the given time.
     * @param entityType Return audit events for the given entity type. Valid values are: User, Group, or Project.
     * @param entityId Return audit events for the given entity ID. Requires entityType attribute to be present.
     * @return a Stream of group Audit events
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<AuditEvent> getAuditEventsStream(final Date created_after, final Date created_before, final String entityType, final Long entityId) throws GitLabApiException {
        return (getAuditEvents(created_after, created_before, entityType, entityId, getDefaultPerPage()).stream());
    }

    /**
     * Get a specific instance audit event.
     *
     * <pre><code>GitLab Endpoint: GET /audit_events/:id</code></pre>
     *
     * @param auditEventId the auditEventId, required
     * @return the group Audit event
     * @throws GitLabApiException if any exception occurs
     */
    public AuditEvent getAuditEvent(final Long auditEventId) throws GitLabApiException {
        final Response response = get(Response.Status.OK, null, "audit_events", auditEventId);
        return (response.readEntity(AuditEvent.class));
    }
}
