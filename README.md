gitlab-api for Java
===================

This project provides a full featured Java API for working with GitLab repositories via the standard GitLab REST API.

It is quite simple to use, all you need is the URL to your GitLab server and the Private Token from your GitLab Account Settings page.  Once you have that info it is as simple as:
```java
// Create a GitLabApi instance to communicate with your GitLab server
GitLabApi gitLabApi = new GitLabApi("http://your.gitlab.server.com", "YOUR_PRIVATE_TOKEN");

// Get the list of projects your account has access to
List<Project> projects = gitLabApi.getProjectApi().getProjects();
```

The API has been broken up into sub APIs classes to make it easier to learn and to separate concerns.  Following is a list of the sub APIs along with a sample use of each API.  See the Javadocs for a complete list of available methods for each sub API.

Available Sub APIs
------------------
```
CommitsApi
GroupApi
MergeRequestApi
ProjectApi
RepositoryApi
SessionApi
UserApi
```

CommitsApi:
```java
// Get a list of commits associated with the specified branch
List<Commit> commits = gitLabApi.getgetCommitsApi().getCommits(1234, "new-feature");
```

GroupApi:
```java
// Get a list of groups that you have access to
List<Group> groups = gitLabApi.getGroupApi().getGroups();
```

MergeRequestApi:
```java
// Get a list of the merge requests for the specified project
 List<MergeRequest> mregeRequests = gitLabApi.getMergeRequestApi().getMergeRequests(1234);
```

ProjectApi:
```java
// Get a list of accessible projects 
public List<Project> projects = gitLabApi.getProjectApi().getProjects();
```

RepositoryApi:
```java
// Get a list of repository branches from a project, sorted by name alphabetically
List<Branch> branches = gitLabApi.getRepositoryApi().getBranches();
```

SessionApi:
```java
// Log in to the GitLab server and get the session info
getLabApi.getSessionApi().login("your-username", "your-email", "your-password");
```

UserApi:
```java
// Get the User info for user_id 1
User user = gitLabApi.getUserApi().getUser(1);
```
