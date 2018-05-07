package org.gitlab4j.api.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User extends AbstractUser {
    private String password;
    private Boolean resetPassword;
    private String externUid;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setResetPassword(Boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public Boolean getResetPassword() {
        return this.resetPassword;
    }

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    public String getExternUid() {
        return this.externUid;
    }

    public User withEmail(String email) {
        setEmail(email);
        return this;
    }

    public User withPassword(String password) {
        setPassword(password);
        return this;
    }

    public User withResetPassword(boolean resetPassword) {
        setResetPassword(resetPassword);
        return this;
    }

    public User withName(String name) {
        setName(name);
        return this;
    }

    public User withUsername(String username) {
        setUsername(username);
        return this;
    }

    public User withSkype(String skype) {
        setSkype(skype);
        return this;
    }

    public User withLinkedin(String linkedIn) {
        setLinkedin(linkedIn);
        return this;
    }

    public User withTwitter(String twitter) {
        setTwitter(twitter);
        return this;
    }

    public User withWebsiteUrl(String websiteUrl) {
        setWebsiteUrl(websiteUrl);
        return this;
    }

    public User withOrganization(String organization) {
        setOrganization(organization);
        return this;
    }

    public User withProjectLimit(Integer projectsLimit) {
        setProjectsLimit(projectsLimit);
        return this;
    }

    public User withExternUid(String externUid) {
        setExternUid(externUid);
        return this;
    }

    public User withProvider(String provider) {
        setProvider(provider);
        return this;
    }

    public User withBio(String bio) {
        setBio(bio);
        return this;
    }

    public User withLocation(String location) {
        setLocation(location);
        return this;
    }

    public User withIsAdmin(Boolean isAdmin) {
        setIsAdmin(isAdmin);
        return this;
    }

    public User withCanCreateGroup(Boolean canCreateGroup) {
        setCanCreateGroup(canCreateGroup);
        return this;
    }

    public User withSkipConfirmation(Boolean skipConfirmation) {
        setSkipConfirmation(skipConfirmation);
        return this;
    }

    public User withExternal(Boolean external) {
        setExternal(external);
        return this;
    }

    public User withSharedRunnersMinuteLimit(Integer sharedRunnersMinuteLimit) {
        setSharedRunnersMinutesLimit(sharedRunnersMinuteLimit);
        return this;
    }
}
