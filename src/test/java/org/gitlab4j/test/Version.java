package org.gitlab4j.test;

public class Version {
	final String version;
	final int major;
	final int minor;
	final int fix;

	Version(String version) {
		this.version = version;
		String[] split = version.substring(0, version.indexOf('-')).split("\\.");
		major = Integer.valueOf(split[0]).intValue();
		minor = Integer.valueOf(split[1]).intValue();
		fix = Integer.valueOf(split[2]).intValue();
	}

	
	public String version() {
		return version;
	}

	public boolean isBefore(Version minimalVersion) {
		return major < minimalVersion.major || minor < minimalVersion.minor || minor < minimalVersion.fix;
	}
	
	@Override
	public String toString() {
		return "Version [major=" + major + ", minor=" + minor + ", fix=" + fix + "]";
	}
}