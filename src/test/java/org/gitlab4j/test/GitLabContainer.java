package org.gitlab4j.test;

import java.time.Duration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.utility.DockerImageName;

public class GitLabContainer extends GenericContainer<GitLabContainer> {

	
	public final String version;
	public final Version parsedVersion;
	
	public GitLabContainer(String version) {
		super(DockerImageName.parse("gitlab/gitlab-ce:" + version));
		this.version = version;
		this.parsedVersion = new Version(version);
		
		this.withExposedPorts(80)
			.withEnv("GITLAB_OMNIBUS_CONFIG", "gitlab_rails['initial_root_password']=\"password\";gitlab_rails['lfs_enabled']=false;")
			.waitingFor(
				Wait
				.forHttp("/")
				.forStatusCode(302)
				.forStatusCode(200)
				.withStartupTimeout(Duration.ofSeconds(300))
		);
	}
	
	public String url() {
		return "http://localhost:" + getMappedPort(80) + "";
	}

	@Override
	public String toString() {
		return "GitLabContainer [version=" + version + ", parsedVersion=" + parsedVersion + ", url()= " + url() + " ]";
	}
	
	
}
