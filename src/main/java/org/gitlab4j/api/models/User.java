package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User extends AbstractUser<User> {
    private String externUid;

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    public String getExternUid() {
        return this.externUid;
    }

    /**
     * @deprecated Replaced by {@link #withProjectsLimit(Integer)}
     * @see #withProjectsLimit(Integer) 
     */
    @Deprecated
    public User withProjectLimit(Integer projectsLimit) {
        return withProjectsLimit(projectsLimit);
    }

    public User withExternUid(String externUid) {
        setExternUid(externUid);
        return this;
    }

    /**
     * @deprecated Replaced by {@link #withSharedRunnersMinutesLimit(Integer)}
     * @see #withSharedRunnersMinutesLimit(Integer) 
     */
    @Deprecated
    public User withSharedRunnersMinuteLimit(Integer sharedRunnersMinuteLimit) {
        return withSharedRunnersMinutesLimit(sharedRunnersMinuteLimit);
    }

}
