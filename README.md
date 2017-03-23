GitlLab API for Java (gitlab4j-api)
===================================

GitLab API for Java (gitlab4j-api) provides a full featured Java API for working with GitLab repositories via the GitLab REST API.

---

To utilize the GitLab API for Java in your project, simply add the following dependency to your project's build file:

**Gradle: build.gradle**
```java
dependencies {
    ...
    compile group: 'org.gitlab4j', name: 'gitlab4j-api', version: '4.0.6'
}
```

**Maven: pom.xml**
```xml
<dependency>
    <groupId>org.gitlab4j</groupId>
    <artifactId>gitlab4j-api</artifactId>
    <version>4.0.6</version>
</dependency>
```

If you are not using Gradle or Maven you can download the latest gitlab4j-api JAR file here: [gitlab4j-api-4.0.6.jar](https://oss.sonatype.org/service/local/repositories/releases/content/org/gitlab4j/gitlab4j-api/4.0.5/gitlab4j-api-4.0.6.jar "Download JAR")

Javadocs are available here: <a href="http://www.messners.com/gitlab4j-api/javadocs/index.html?org/gitlab4j/api/package-summary.html" target="_top">Javadocs</a>

---

GitLab4J-API is quite simple to use, all you need is the URL to your GitLab server and the Private Token from your GitLab Account Settings page.  Once you have that info it is as simple as:
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
RepositoryFileApi
ServicesApi
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

RepositoryFileApi:
```java
// Get info (name, size, ...) and the content from a file in repository
RepositoryFile file = gitLabApi.getRepositoryFileApi().getFile("file-path", 1234, "ref");   
```

ServicesApi:
```java
// Activates the gitlab-ci service.
getLabApi.getServicesApi().setGitLabCI("project-name", "auth-token", "project-ci-url");
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
