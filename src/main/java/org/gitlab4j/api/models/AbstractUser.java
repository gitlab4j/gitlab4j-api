package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractUser {

    private String avatarUrl;
    private String bio;
    private Boolean canCreateGroup;
    private Boolean canCreateProject;
    private Integer colorSchemeId;
    private Date confirmedAt;
    private Date createdAt;
    private Date currentSignInAt;
    private List<CustomAttribute> customAttributes;
    private String email;
    private Boolean external;
    private Integer id;
    private List<Identity> identities;
    private Boolean isAdmin;
    private Date lastActivityOn;
    private Date lastSignInAt;
    private String linkedin;
    private String location;
    private String name;
    private String organization;
    private Integer projectsLimit;
    private String provider;
    private Integer sharedRunnersMinutesLimit;
    private String skype;
    private String state;
    private Integer themeId;
    private String twitter;
    private Boolean twoFactorEnabled;
    private String username;
    private String websiteUrl;
    private String webUrl;
    private Boolean skipConfirmation;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean getCanCreateGroup() {
        return canCreateGroup;
    }

    public void setCanCreateGroup(Boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    public Boolean getCanCreateProject() {
        return canCreateProject;
    }

    public void setCanCreateProject(Boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
    }

    public Integer getColorSchemeId() {
        return colorSchemeId;
    }

    public void setColorSchemeId(Integer colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    public Date getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Date confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCurrentSignInAt() {
        return currentSignInAt;
    }

    public void setCurrentSignInAt(Date currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getLastActivityOn() {
        return lastActivityOn;
    }

    public void setLastActivityOn(Date lastActivityOn) {
        this.lastActivityOn = lastActivityOn;
    }

    public Date getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(Date lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getProjectsLimit() {
        return projectsLimit;
    }

    public void setProjectsLimit(Integer projectsLimit) {
        this.projectsLimit = projectsLimit;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getSharedRunnersMinutesLimit() {
        return sharedRunnersMinutesLimit;
    }

    public void setSharedRunnersMinutesLimit(Integer sharedRunnersMinutesLimit) {
        this.sharedRunnersMinutesLimit = sharedRunnersMinutesLimit;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Boolean getSkipConfirmation() {
        return skipConfirmation;
    }

    public void setSkipConfirmation(Boolean skipConfirmation) {
        this.skipConfirmation = skipConfirmation;
    }

    public List<CustomAttribute> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
    }


    public AbstractUser withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public AbstractUser withBio(String bio) {
        this.bio = bio;
        return this;
    }

    public AbstractUser withCanCreateGroup(Boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
        return this;
    }

    public AbstractUser withCanCreateProject(Boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
        return this;
    }

    public AbstractUser withColorSchemeId(Integer colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
        return this;
    }

    public AbstractUser withConfirmedAt(Date confirmedAt) {
        this.confirmedAt = confirmedAt;
        return this;
    }

    public AbstractUser withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AbstractUser withCurrentSignInAt(Date currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
        return this;
    }

    public AbstractUser withEmail(String email) {
        this.email = email;
        return this;
    }

    public AbstractUser withExternal(Boolean external) {
        this.external = external;
        return this;
    }

    public AbstractUser withId(Integer id) {
        this.id = id;
        return this;
    }

    public AbstractUser withIdentities(List<Identity> identities) {
        this.identities = identities;
        return this;
    }

    public AbstractUser withIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public AbstractUser withLastActivityOn(Date lastActivityOn) {
        this.lastActivityOn = lastActivityOn;
        return this;
    }

    public AbstractUser withLastSignInAt(Date lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
        return this;
    }

    public AbstractUser withLinkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public AbstractUser withLocation(String location) {
        this.location = location;
        return this;
    }

    public AbstractUser withName(String name) {
        this.name = name;
        return this;
    }

    public AbstractUser withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public AbstractUser withProjectsLimit(Integer projectsLimit) {
        this.projectsLimit = projectsLimit;
        return this;
    }

    public AbstractUser withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public AbstractUser withSharedRunnersMinutesLimit(Integer sharedRunnersMinutesLimit) {
        this.sharedRunnersMinutesLimit = sharedRunnersMinutesLimit;
        return this;
    }

    public AbstractUser withSkype(String skype) {
        this.skype = skype;
        return this;
    }

    public AbstractUser withState(String state) {
        this.state = state;
        return this;
    }

    public AbstractUser withThemeId(Integer themeId) {
        this.themeId = themeId;
        return this;
    }

    public AbstractUser withTwitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public AbstractUser withTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
        return this;
    }

    public AbstractUser withUsername(String username) {
        this.username = username;
        return this;
    }

    public AbstractUser withWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
        return this;
    }

    public AbstractUser withWebUrl(String webUrl) {
        this.webUrl = webUrl;
        return this;
    }

    public AbstractUser withSkipConfirmation(Boolean skipConfirmation) {
        this.skipConfirmation = skipConfirmation;
        return this;
    }

    public AbstractUser withCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
        return this;
    }
}
