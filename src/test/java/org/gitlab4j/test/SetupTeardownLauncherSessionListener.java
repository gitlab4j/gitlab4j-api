package org.gitlab4j.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

public class SetupTeardownLauncherSessionListener implements LauncherSessionListener {

	private GitLabContainers gitLabContainers;

	@Override
	public void launcherSessionOpened(LauncherSession session) {
		session.getLauncher().registerTestExecutionListeners(new TestExecutionListener() {
			@Override
			public void testPlanExecutionStarted(TestPlan testPlan) {
				if (gitLabContainers == null) {
					gitLabContainers = new GitLabContainers();
					gitLabContainers.setUp();
				}
			}
		});
	}

	@Override
	public void launcherSessionClosed(LauncherSession session) {
		if (gitLabContainers != null) {
			gitLabContainers.tearDown();
			gitLabContainers = null;
		}
	}

	static class GitLabContainers {

		private static final String[] GITLAB_VERSIONS = { 
				"15.4.2-ce.0",
				"14.10.5-ce.0", 
		};

		private static final Map<String, GitLabContainer> containers = new HashMap<>();

		public GitLabContainers() {
			String propertyVersion = System.getProperty("gitlab4j.gitlab.version");

			if (propertyVersion == null) {
				for (String version : GITLAB_VERSIONS) {
					containers.put(version, new GitLabContainer(version));
				}
			} else {
				containers.put(propertyVersion, new GitLabContainer(propertyVersion));
			}
		}
		
		public static Collection<GitLabContainer> containers() {
			return Collections.unmodifiableCollection(containers.values());
		}

		void setUp() {			
//			containers.forEach((version, container) -> container.start());
		}

		void tearDown() {
			containers.forEach((version, container) -> {
				if (container.isRunning()) {
					container.stop();
				}
			});
		}
	}
}