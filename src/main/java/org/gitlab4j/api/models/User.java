package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class User extends AbstractUser<User> {

    private String externUid;

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    public String getExternUid() {
        return this.externUid;
    }

    public User withExternUid(String externUid) {
        setExternUid(externUid);
        return this;
    }

    /**
     * Fluent method to set the projects_limit setting.
     *
     * @param projectsLimit the value for the projects_limit setting
     * @deprecated Replaced by {@link #withProjectsLimit(Integer)}
     * @see #withProjectsLimit(Integer)
     * @return the value of this instance
     */
    @Deprecated
    public User withProjectLimit(Integer projectsLimit) {
        return withProjectsLimit(projectsLimit);
    }
 
    /**
     * Fluent method to set the shared_projects_minutes_limit setting.
     *
     * @param sharedRunnersMinuteLimit the value for the shared_projects_minutes_limit setting
     * @deprecated Replaced by {@link #withSharedRunnersMinutesLimit(Integer)}
     * @see #withSharedRunnersMinutesLimit(Integer)
     * @return the value of this instance
     */
    @Deprecated
    public User withSharedRunnersMinuteLimit(Integer sharedRunnersMinuteLimit) {
        return withSharedRunnersMinutesLimit(sharedRunnersMinuteLimit);
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
