/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

import java.util.Date;

public class Todo {

    public static class Project {

        private Integer id;
        private String name;
        private String nameWithNamespace;
        private String path;
        private String pathWithNamespace;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameWithNamespace() {
            return nameWithNamespace;
        }

        public void setNameWithNamespace(String nameWithNamespace) {
            this.nameWithNamespace = nameWithNamespace;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPathWithNamespace() {
            return pathWithNamespace;
        }

        public void setPathWithNamespace(String pathWithNamespace) {
            this.pathWithNamespace = pathWithNamespace;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    public class Target {
        private Integer id;
        private Integer iid;
        private Integer projectId;
        private String title;
        private String description;
        private String state;
        private Date createdAt;
        private Date updatedAt;
        private String targetBranch;
        private String sourceBranch;
        private Integer upvotes;
        private Integer downvotes;
        private Author author;
        private Author assignee;
        private Integer sourceProjectId;
        private Integer targetProjectId;
        private String[] labels;
        private Boolean workInProgress;
        private Milestone milestone;
        private Boolean mergeWhenPipelineSucceeds;
        private String mergeStatus;
        private Boolean subscribed;
        private Integer userNotesCount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getIid() {
            return iid;
        }

        public void setIid(Integer iid) {
            this.iid = iid;
        }

        public Integer getProjectId() {
            return projectId;
        }

        public void setProjectId(Integer projectId) {
            this.projectId = projectId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getTargetBranch() {
            return targetBranch;
        }

        public void setTargetBranch(String targetBranch) {
            this.targetBranch = targetBranch;
        }

        public String getSourceBranch() {
            return sourceBranch;
        }

        public void setSourceBranch(String sourceBranch) {
            this.sourceBranch = sourceBranch;
        }

        public Integer getUpvotes() {
            return upvotes;
        }

        public void setUpvotes(Integer upvotes) {
            this.upvotes = upvotes;
        }

        public Integer getDownvotes() {
            return downvotes;
        }

        public void setDownvotes(Integer downvotes) {
            this.downvotes = downvotes;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Author getAssignee() {
            return assignee;
        }

        public void setAssignee(Author assignee) {
            this.assignee = assignee;
        }

        public Integer getSourceProjectId() {
            return sourceProjectId;
        }

        public void setSourceProjectId(Integer sourceProjectId) {
            this.sourceProjectId = sourceProjectId;
        }

        public Integer getTargetProjectId() {
            return targetProjectId;
        }

        public void setTargetProjectId(Integer targetProjectId) {
            this.targetProjectId = targetProjectId;
        }

        public String[] getLabels() {
            return labels;
        }

        public void setLabels(String[] labels) {
            this.labels = labels;
        }

        public Boolean getWorkInProgress() {
            return workInProgress;
        }

        public void setWorkInProgress(Boolean workInProgress) {
            this.workInProgress = workInProgress;
        }

        public Milestone getMilestone() {
            return milestone;
        }

        public void setMilestone(Milestone milestone) {
            this.milestone = milestone;
        }

        public Boolean getMergeWhenPipelineSucceeds() {
            return mergeWhenPipelineSucceeds;
        }

        public void setMergeWhenPipelineSucceeds(Boolean mergeWhenPipelineSucceeds) {
            this.mergeWhenPipelineSucceeds = mergeWhenPipelineSucceeds;
        }

        public String getMergeStatus() {
            return mergeStatus;
        }

        public void setMergeStatus(String mergeStatus) {
            this.mergeStatus = mergeStatus;
        }

        public Boolean getSubscribed() {
            return subscribed;
        }

        public void setSubscribed(Boolean subscribed) {
            this.subscribed = subscribed;
        }

        public Integer getUserNotesCount() {
            return userNotesCount;
        }

        public void setUserNotesCount(Integer userNotesCount) {
            this.userNotesCount = userNotesCount;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    private Integer id;
    private Project project;
    private Author author;
    private String actionName;
    private String targetType;
    private String targetUrl;
    private Target target;
    private String body;
    private String state;
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
