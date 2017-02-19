package org.gitlab4j.api.webhook;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.gitlab4j.api.models.Assignee;
import org.gitlab4j.api.models.User;

@XmlAccessorType(XmlAccessType.FIELD)
public class IssueEvent implements Event {

    public static final String X_GITLAB_EVENT = "Issue Hook";
    public static final String OBJECT_KIND = "issue";

    private User user;
    private EventProject project;
    private EventRepository repository;
    private ObjectAttributes objectAttributes;
    private Assignee assignee;
    
    public String getObjectKind() {
        return (OBJECT_KIND);
    }

    public void setObjectKind(String objectKind) {    
        if (!OBJECT_KIND.equals(objectKind))
            throw new RuntimeException("Invalid object_kind (" + objectKind + "), must be '" + OBJECT_KIND + "'");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventProject getProject() {
        return project;
    }

    public void setProject(EventProject project) {
        this.project = project;
    }

    public EventRepository getRepository() {
        return repository;
    }

    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public ObjectAttributes getObjectAttributes() {
        return this.objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ObjectAttributes {

        private Integer assigneeId;
        private Integer authorId;
        private String branchName;
        private Date createdAt;
        private String description;
        private Integer id;
        private Integer iid;

        private String milestoneId;
        private Integer position;
        private Integer projectId;

        private String state;

        private String title;
        private Date updatedAt;

        private String url;
        private String action;

        public Integer getAssigneeId() {
            return this.assigneeId;
        }

        public void setAssigneeId(Integer assigneeId) {
            this.assigneeId = assigneeId;
        }

        public Integer getAuthorId() {
            return this.authorId;
        }

        public void setAuthorId(Integer authorId) {
            this.authorId = authorId;
        }

        public String getBranchName() {
            return this.branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public Date getCreatedAt() {
            return this.createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getId() {
            return this.id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getIid() {
            return this.iid;
        }

        public void setIid(Integer iid) {
            this.iid = iid;
        }

        public String getMilestoneId() {
            return this.milestoneId;
        }

        public void setMilestoneId(String milestoneId) {
            this.milestoneId = milestoneId;
        }

        public Integer getPosition() {
            return this.position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public Integer getProjectId() {
            return this.projectId;
        }

        public void setProjectId(Integer projectId) {
            this.projectId = projectId;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Date getUpdatedAt() {
            return this.updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }
}
