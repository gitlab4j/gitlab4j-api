GitlLab API for Java (gitlab4j-api)
===================================

GitLab API for Java (gitlab4j-api) provides a full featured and easy to consume Java API for working with GitLab repositories via the GitLab REST API.  Additionally, full support for working with GitLab webhooks and system hooks is also provided.

---

To utilize the GitLab API for Java in your project, simply add the following dependency to your project's build file:

**Gradle: build.gradle**
```java
dependencies {
    ...
    compile group: 'org.gitlab4j', name: 'gitlab4j-api', version: '4.8.29'
}
```

**Maven: pom.xml**
```xml
<dependency>
    <groupId>org.gitlab4j</groupId>
    <artifactId>gitlab4j-api</artifactId>
    <version>4.8.29</version>
</dependency>
```

**Ivy and SBT**<br/>
There have been reports of problems resolving some dependencies when using Ivy or SBT, for help resolving those issues see:<br/>
<a href="https://github.com/eclipse-ee4j/jaxrs-api/issues/571">JAX-RS API Issue #571</a><br/>
<a href="https://github.com/eclipse-ee4j/jaxrs-api/issues/572">JAX-RS API Issue #572</a>

---

## Javadocs
Javadocs are available here: <a href="http://www.messners.com/gitlab4j-api/javadocs/index.html?overview-summary.html"  target="_top">Javadocs</a>

---

## Java 8 Requirement
As of GitLab4J-API 4.8.0, Java 8+ is now required to use GitLab4J-API.

---

## Using GitLab4J

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
## Connecting Through a Proxy Server
As of GitLab4J-API 4.8.2 support has been added for connecting to the GitLab server using an HTTP proxy server:
```java
// Log in to the GitLab server using a proxy server (with basic auth on proxy)
Map<String, Object> proxyConfig = ProxyClientConfig.createProxyClientConfig(
        "http://your-proxy-server", "proxy-username", "proxy-password");
GitLabApi gitLabApi = new GitLabApi("http://your.gitlab.com", "YOUR_PRIVATE_TOKEN", null, proxyConfig);

// Log in to the GitLab server using a proxy server (no auth on proxy)
Map<String, Object> proxyConfig = ProxyClientConfig.createProxyClientConfig("http://your-proxy-server");
GitLabApi gitLabApi = new GitLabApi("http://your.gitlab.com", "YOUR_PRIVATE_TOKEN", null, proxyConfig);
```
See the Javadoc on the GitLabApi class for a complete list of methods accepting the proxy configuration (clientConfiguration parameter)

---
## GitLab API V3 and V4 Support
As of GitLab4J-API 4.2.0 support has been added for GitLab API V4. If your application requires GitLab API V3,
you can still use GitLab4J-API by creating your GitLabApi instance as follows:
```java
// Create a GitLabApi instance to communicate with your GitLab server using GitLab API V3
GitLabApi gitLabApi = new GitLabApi(ApiVersion.V3, "http://your.gitlab.server.com", "YOUR_PRIVATE_TOKEN");
```

---
## Results Paging
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
---
## Java 8 Optional&lt;T&gt; Support
GitLab4J-API supports Java 8 Optional&lt;T&gt; for API calls that result in the return of a single item. Here is an example on how to use the Java 8 Optional&lt;T&gt; API calls:
```java
Optional<Group> optionalGroup =  gitlabApi.getGroupApi().getGroup("my-group-path");
if (optionalGroup.isPresent())
    return optionalGroup.get();

return gitlabApi.getGroupApi().addGroup("my-group-name", "my-group-path");
```
---
## Issue Time Estimates
GitLab issues allow for time tracking. The following time units are currently available:

* months (mo)
* weeks (w)
* days (d)
* hours (h)
* minutes (m)

Conversion rates are 1mo = 4w, 1w = 5d and 1d = 8h. 

---
## Making API Calls
The API has been broken up into sub APIs classes to make it easier to learn and to separate concerns.  Following is a list of the sub APIs along with a sample use of each API.  See the <a href="http://www.messners.com/gitlab4j-api/javadocs/index.html?org/gitlab4j/api/package-summary.html" target="_top">Javadocs</a> for a complete list of available methods for each sub API.

### Available Sub APIs
------------------
&nbsp;&nbsp;[CommitsApi](#commitsapi)<br/>
&nbsp;&nbsp;[DeployKeysApi](#deploykeysapi)<br/>
&nbsp;&nbsp;[EventsApi](#eventsapi)<br/>
&nbsp;&nbsp;[GroupApi](#groupapi)<br/>
&nbsp;&nbsp;[HealthCheckApi](#healthcheckapi)<br/>
&nbsp;&nbsp;[IssuesApi](#issuesapi)<br/>
&nbsp;&nbsp;[JobApi](#jobapi)<br/>
&nbsp;&nbsp;[LabelsApi](#labelsapi)<br/>
&nbsp;&nbsp;[MergeRequestApi](#mergerequestapi)<br/>
&nbsp;&nbsp;[MilestonesApi](#milestonesapi)<br/>
&nbsp;&nbsp;[NamespaceApi](#namespaceapi)<br/>
&nbsp;&nbsp;[NotesApi](#notesapi)<br/>
&nbsp;&nbsp;[NotificationSettingsApi](#notificationsettingsapi)<br/>
&nbsp;&nbsp;[PipelineApi](#pipelineapi)<br/>
&nbsp;&nbsp;[ProjectApi](#projectapi)<br/>
&nbsp;&nbsp;[ProtectedBranchesApi](#protectedbranchesapi) <br/>
&nbsp;&nbsp;[RepositoryApi](#repositoryapi)<br/>
&nbsp;&nbsp;[RepositoryFileApi](#repositoryfileapi)<br/>
&nbsp;&nbsp;[RunnersApi](#runnersapi) <br/>
&nbsp;&nbsp;[ServicesApi](#servicesapi)<br/>
&nbsp;&nbsp;[SessionApi](#sessionapi)<br/>
&nbsp;&nbsp;[SnippetsApi](#snippetsapi)<br/>
&nbsp;&nbsp;[SystemHooksApi](#systemhooksapi)<br/>
&nbsp;&nbsp;[UserApi](#userapi)


### Sub API Examples
----------------

#### CommitsApi
```java
// Get a list of commits associated with the specified branch that fall within the specified time window
// This uses the ISO8601 date utilities the in org.gitlab4j.api.utils.ISO8601 class
Date since = ISO8601.toDate("2017-01-01T00:00:00Z");
Date until = new Date(); // now
List<Commit> commits = gitLabApi.getCommitsApi().getCommits(1234, "new-feature", since, until);
```

#### DeployKeysApi
```java
// Get a list of DeployKeys for the authenticated user
List<DeployKey> deployKeys = gitLabApi.getDeployKeysApi().getDeployKeys();
```

#### EventsApi
```java
// Get a list of Events for the authenticated user
Date after = new Date(0); // After Eposc
Date before = new Date(); // Before now
List<Event> events = gitLabApi.getEventsApi().getAuthenticatedUserEvents(null, null, before, after, DESC);
```

#### GroupApi
```java
// Get a list of groups that you have access to
List<Group> groups = gitLabApi.getGroupApi().getGroups();
```

#### HealthCheckApi
```java
// Get the liveness endpoint health check results. Assumes ip_whitelisted per:
// https://docs.gitlab.com/ee/administration/monitoring/ip_whitelist.html
HealthCheckInfo healthCheck = gitLabApi.getHealthCheckApi().getLiveness();
```

#### IssuesApi
```java
// Get a list of issues for the specified project ID
List<Issue> issues = gitLabApi.getIssuesApi().getIssues(1234);
```

#### JobApi
```java
// Get a list of jobs for the specified project ID
List<Job> jobs = gitLabApi.getJobApi().getJobs(1234);
```

#### LabelsApi
```java
// Get a list of labels for the specified project ID
List<Label> labels = gitLabApi.getLabelsApi().getLabels(1234);
```

#### MergeRequestApi
```java
// Get a list of the merge requests for the specified project
List<MergeRequest> mergeRequests = gitLabApi.getMergeRequestApi().getMergeRequests(1234);
```

#### MilestonesApi
```java
// Get a list of the milestones for the specified project
List<Milestone> milestones = gitLabApi.getMilestonesApi().getMilestones(1234);
```
 
#### NamespaceApi
```java
// Get all namespaces that match "foobar" in their name or path
List<Namespace> namespaces = gitLabApi.getNamespaceApi().findNamespaces("foobar");
```

#### NotesApi
```java
// Get a list of the issues's notes for project ID 1234, issue IID 1
List<Note> notes = gitLabApi.getNotesApi().getNotes(1234, 1);
```
#### NotificationSettingsApi
```java
// Get the current global notification settings
NotificationSettings settings = gitLabApi.getNotificationSettingsApi().getGlobalNotificationSettings();
```

#### PipelineApi
```java
// Get all pipelines for the specified project ID
List<Pipeline> pipelines = gitLabApi.getPipelineApi().getPipelines(1234);
```

#### ProjectApi
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

#### ProtectedBranchesApi
```java
List<ProtectedBranch> branches = gitLabApi.getProtectedBranchesApi().getProtectedBranches(project.getId());
```

#### RepositoryApi
```java
// Get a list of repository branches from a project, sorted by name alphabetically
List<Branch> branches = gitLabApi.getRepositoryApi().getBranches();
```

#### RepositoryFileApi
```java
// Get info (name, size, ...) and the content from a file in repository
RepositoryFile file = gitLabApi.getRepositoryFileApi().getFile("file-path", 1234, "ref");   
```

#### RunnersApi
```java
// Get All Runners.
List<Runner> runners = gitLabApi.getRunnersApi().getAllRunners();
```

#### ServicesApi
```java
// Activate/Update the Slack Notifications service
SlackService slackService =  new SlackService()
        .withMergeRequestsEvents(true)
        .withWebhook("https://hooks.slack.com/services/ABCDEFGHI/KJLMNOPQR/wetrewq7897HKLH8998wfjjj")
        .withUsername("GitLab4J");
gitLabApi.getServicesApi().updateSlackService("project-path", slackService);
```

#### SessionApi
```java
// Log in to the GitLab server and get the session info
gitLabApi.getSessionApi().login("your-username", "your-email", "your-password");
```

#### SnippetsApi
```java
// Get a list of the authenticated user's snippets
List<Snippet> snippets = gitLabApi.getSnippetsApi().getSnippets();
```

#### SystemHooksApi
```java
// Get a list of installed system hooks
List<SystemHook> hooks = gitLabApi.getSystemHooksApi().getSystemHooks();
```

#### UserApi
```java
// Get the User info for user_id 1
User user = gitLabApi.getUserApi().getUser(1);

// Create a new user with no password who will recieve a reset password email
User userConfig = new User()
    .withEmail("jdoe@example.com")
    .withName("Jane Doe")
    .withUsername("jdoe");
String password = null;
boolean sendResetPasswordEmail = true;
gitLabApi.getUserApi().createUser(userConfig, password, sendResetPasswordEmail);
```
