GitlLab API for Java (gitlab4j-api)
===================================

GitLab API for Java (gitlab4j-api) provides a full featured and easy to consume Java API for working with GitLab repositories via the GitLab REST API.  Additionally, full support for working with GitLab WebHooks is also provided.

---

To utilize the GitLab API for Java in your project, simply add the following dependency to your project's build file:

**Gradle: build.gradle**
```java
dependencies {
    ...
    compile group: 'org.gitlab4j', name: 'gitlab4j-api', version: '4.6.9'
}
```

**Maven: pom.xml**
```xml
<dependency>
    <groupId>org.gitlab4j</groupId>
    <artifactId>gitlab4j-api</artifactId>
    <version>4.6.9</version>
</dependency>
```

Javadocs are available here: <a href="http://www.messners.com/gitlab4j-api/javadocs/index.html?overview-summary.html"  target="_top">Javadocs</a>

---

GitLab4J-API is quite simple to use, all you need is the URL to your GitLab server and the Private Token from your GitLab Account Settings page.  Once you have that info it is as simple as:
```java
// Create a GitLabApi instance to communicate with your GitLab server
GitLabApi gitLabApi = new GitLabApi("http://your.gitlab.server.com", "YOUR_PRIVATE_TOKEN");

// Get the list of projects your account has access to
List<Project> projects = gitLabApi.getProjectApi().getProjects();
```

You can also login to your GitLab server with username, and password:
```java
// Log in to the GitLab server using a username and password
GitLabApi gitLabApi = GitLabApi.login("http://your.gitlab.server.com", "your-username", "your-password");
```

As of GitLab4J-API 4.6.6, all API requests support performing the API call as if you were another user, provided you are authenticated as an administrator:
```java
// Create a GitLabApi instance to communicate with your GitLab server (must be an administrator)
GitLabApi gitLabApi = new GitLabApi("http://your.gitlab.server.com", "YOUR_PRIVATE_TOKEN");

// sudo as as a different user, in this case the user named "johndoe", all future calls will be done as "johndoe"
gitLabApi.sudo("johndoe")

// To turn off sudo mode
gitLabApi.unsudo();
```

---

As of GitLab4J-API 4.2.0 support has been added for GitLab API V4. If your application requires GitLab API V3,
you can still use GitLab4J-API by creating your GitLabApi instance as follows:
```java
// Create a GitLabApi instance to communicate with your GitLab server using GitLab API V3
GitLabApi gitLabApi = new GitLabApi(ApiVersion.V3, "http://your.gitlab.server.com", "YOUR_PRIVATE_TOKEN");
```

---

GitLab4J-API provides an easy to use paging mechanism to page through lists of results from the GitLab API.
Here are a couple of examples on how to use the Pager:
```java
// Get a Pager instance that will page through the projects with 10 projects per page
Pager<Project> projectPager = gitlabApi.getProjectsApi().getProjects(10);

// Iterate through the pages and print out the name and description
while (projectsPager.hasNext())) {
    for (Project project : projectPager.next()) {
        System.out.println(project.getName() + " -: " + project.getDescription());
    }
}
```

```java
// Create a Pager instance that will be used to build a list containing all the commits for project ID 1234
Pager<Commit> commitPager = gitlabApi.getCommitsApi().getCommits(1234, 20);
List<Commit> allCommits = new ArrayList<>(commitPager.getTotalItems());

// Iterate through the pages and append each page of commits to the list
while (commitPager.hasNext())
    allCommits.addAll(commitPager.next());
```
---

The API has been broken up into sub APIs classes to make it easier to learn and to separate concerns.  Following is a list of the sub APIs along with a sample use of each API.  See the <a href="http://www.messners.com/gitlab4j-api/javadocs/index.html?org/gitlab4j/api/package-summary.html" target="_top">Javadocs</a> for a complete list of available methods for each sub API.

Available Sub APIs
------------------
&nbsp;&nbsp;[CommitsApi](#commitsapi)<br/>
&nbsp;&nbsp;[DeployKeysApi](#deploykeysapi)<br/>
&nbsp;&nbsp;[EventsApi](#eventsapi)<br/>
&nbsp;&nbsp;[GroupApi](#groupapi)<br/>
&nbsp;&nbsp;[IssuesApi](#issuesapi)<br/>
&nbsp;&nbsp;[JobApi](#jobapi)<br/>
&nbsp;&nbsp;[MergeRequestApi](#mergerequestapi)<br/>
&nbsp;&nbsp;[NamespaceApi](#namespaceapi)<br/>
&nbsp;&nbsp;[NotesApi](#notesapi)<br/>
&nbsp;&nbsp;[PipelineApi](#pipelineapi)<br/>
&nbsp;&nbsp;[ProjectApi](#projectapi)<br/>
&nbsp;&nbsp;[RepositoryApi](#repositoryapi)<br/>
&nbsp;&nbsp;[RepositoryFileApi](#repositoryfileapi)<br/>
&nbsp;&nbsp;[ServicesApi](#servicesapi)<br/>
&nbsp;&nbsp;[SessionApi](#sessionapi)<br/>
&nbsp;&nbsp;[UserApi](#userapi)


Sub API Examples
----------------

### CommitsApi
```java
// Get a list of commits associated with the specified branch that fall within the specified time window
// This uses the ISO8601 date utilities the in org.gitlab4j.api.utils.ISO8601 class
Date since = ISO8601.toDate("2017-01-01T00:00:00Z");
Date until = new Date(); // now
List<Commit> commits = gitLabApi.getCommitsApi().getCommits(1234, "new-feature", since, until);
```

### DeployKeysApi
```java
// Get a list of DeployKeys for the authenticated user
List<DeployKey> deployKeys = gitLabApi.getDeployKeysApi().getDeployKeys();
```

### EventsApi
```java
// Get a list of Events for the authenticated user
Date after = new Date(0); // After Eposc
Date before = new Date(); // Before now
List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, before, after, DESC);
```

### GroupApi
```java
// Get a list of groups that you have access to
List<Group> groups = gitLabApi.getGroupApi().getGroups();
```

### IssuesApi
```java
// Get a list of issues for the specified project ID
List<Issue> issues = gitLabApi.getIssuesApi().getIssues(1234);
```

### JobApi
```java
// Get a list of jobs for the specified project ID
List<Job> jobs = gitLabApi.getJobApi().getJobs(1234);
```

### MergeRequestApi
```java
// Get a list of the merge requests for the specified project
List<MergeRequest> mergeRequests = gitLabApi.getMergeRequestApi().getMergeRequests(1234);
```
 
### NamespaceApi
```java
// Get all namespaces that match "foobar" in their name or path
List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces("foobar");
```

### NotesApi
```java
// Get a list of the issues's notes for project ID 1234, issue IID 1
List<Note> notes = gitLabApi.getNotesApi().getNotes(Integer 1234, Integer 1);
```

### PipelineApi
```java
// Get all pipelines for the specified project ID
List<Pipeline> pipelines = gitLabApi.getPipelineApi().getPipelines(1234);
```

### ProjectApi
```java
// Get a list of accessible projects 
public List<Project> projects = gitLabApi.getProjectApi().getProjects();
```
```java
// Create a new project
Project projectSpec = new Project()
    .withName("my-project")
    .withDescription("My project for demonstration.")
    .withIssuesEnabled(true)
    .withMergeRequestsEnabled(true)
    .withWikiEnabled(true)
    .withSnippetsEnabled(true)
    .withPublic(true);

Project newProject = gitLabApi.getProjectApi().createProject(projectSpec);
```

### RepositoryApi
```java
// Get a list of repository branches from a project, sorted by name alphabetically
List<Branch> branches = gitLabApi.getRepositoryApi().getBranches();
```

### RepositoryFileApi
```java
// Get info (name, size, ...) and the content from a file in repository
RepositoryFile file = gitLabApi.getRepositoryFileApi().getFile("file-path", 1234, "ref");   
```

### ServicesApi
```java
// Activates the gitlab-ci service.
getLabApi.getServicesApi().setGitLabCI("project-name", "auth-token", "project-ci-url");
```

### SessionApi
```java
// Log in to the GitLab server and get the session info
getLabApi.getSessionApi().login("your-username", "your-email", "your-password");
```

### UserApi
```java
// Get the User info for user_id 1
User user = gitLabApi.getUserApi().getUser(1);
```
