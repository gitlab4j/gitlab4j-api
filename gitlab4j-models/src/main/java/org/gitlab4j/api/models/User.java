package org.gitlab4j.api.models;

import java.util.Date;
import java.util.List;

import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User extends AbstractUser<User> {
    private static final long serialVersionUID = 1L;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("bot")
    private Boolean bot;

    @JsonProperty("can_create_group")
    private Boolean canCreateGroup;

    @JsonProperty("can_create_project")
    private Boolean canCreateProject;

    @JsonProperty("color_scheme_id")
    private Integer colorSchemeId;

    @JsonProperty("confirmed_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date confirmedAt;

    @JsonProperty("current_sign_in_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date currentSignInAt;

    @JsonProperty("custom_attributes")
    private List<CustomAttribute> customAttributes;

    @JsonProperty("external")
    private Boolean external;

    @JsonProperty("extern_uid")
    private String externUid;

    @JsonProperty("extra_shared_runners_minutes_limit")
    private Integer extraSharedRunnersMinutesLimit;

    @JsonProperty("identities")
    private List<Identity> identities;

    @JsonProperty("is_admin")
    private Boolean isAdmin;

    @JsonProperty("last_activity_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date lastActivityOn;

    @JsonProperty("last_sign_in_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date lastSignInAt;

    @JsonProperty("linkedin")
    private String linkedin;

    @JsonProperty("location")
    private String location;

    @JsonProperty("namespace_id")
    private Long namespaceId;

    @JsonProperty("organization")
    private String organization;

    @JsonProperty("private_profile")
    private Boolean privateProfile;

    @JsonProperty("projects_limit")
    private Integer projectsLimit;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("public_email")
    private String publicEmail;

    @JsonProperty("shared_runners_minutes_limit")
    private Integer sharedRunnersMinutesLimit;

    @JsonProperty("skype")
    private String skype;

    @JsonProperty("state")
    private String state;

    @JsonProperty("theme_id")
    private Integer themeId;

    @JsonProperty("twitter")
    private String twitter;

    @JsonProperty("two_factor_enabled")
    private Boolean twoFactorEnabled;

    @JsonProperty("website_url")
    private String websiteUrl;

    @JsonProperty("skip_confirmation")
    private Boolean skipConfirmation;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        this.bot = bot;
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

    public Date getCurrentSignInAt() {
        return currentSignInAt;
    }

    public void setCurrentSignInAt(Date currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }

    public String getExternUid() {
        return this.externUid;
    }

    public Integer getExtraSharedRunnersMinutesLimit() {
        return extraSharedRunnersMinutesLimit;
    }

    public void setExtraSharedRunnersMinutesLimit(Integer extraSharedRunnersMinutesLimit) {
        this.extraSharedRunnersMinutesLimit = extraSharedRunnersMinutesLimit;
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

    public Long getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(Long namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Boolean getPrivateProfile() {
        return privateProfile;
    }

    public void setPrivateProfile(Boolean privateProfile) {
        this.privateProfile = privateProfile;
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

    public String getPublicEmail() {
        return publicEmail;
    }

    public void setPublicEmail(String publicEmail) {
        this.publicEmail = publicEmail;
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

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
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

    public User withBio(String bio) {
        this.bio = bio;
        return this;
    }

    public User withCanCreateGroup(Boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
        return this;
    }

    public User withCanCreateProject(Boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
        return this;
    }

    public User withColorSchemeId(Integer colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
        return this;
    }

    public User withConfirmedAt(Date confirmedAt) {
        this.confirmedAt = confirmedAt;
        return this;
    }

    public User withCurrentSignInAt(Date currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
        return this;
    }

    public User withExternal(Boolean external) {
        this.external = external;
        return this;
    }

    public User withExternUid(String externUid) {
        this.externUid = externUid;
        return this;
    }

    public User withExtraSharedRunnersMinutesLimit(Integer extraSharedRunnersMinutesLimit) {
        this.extraSharedRunnersMinutesLimit = extraSharedRunnersMinutesLimit;
        return this;
    }

    public User withIdentities(List<Identity> identities) {
        this.identities = identities;
        return this;
    }

    public User withIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public User withLastActivityOn(Date lastActivityOn) {
        this.lastActivityOn = lastActivityOn;
        return this;
    }

    public User withLastSignInAt(Date lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
        return this;
    }

    public User withLinkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public User withLocation(String location) {
        this.location = location;
        return this;
    }

    public User withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public User withPrivateProfile(Boolean privateProfile) {
        this.privateProfile = privateProfile;
        return this;
    }

    public User withProjectsLimit(Integer projectsLimit) {
        this.projectsLimit = projectsLimit;
        return this;
    }

    public User withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public User withPublicEmail(String publicEmail) {
        this.publicEmail = publicEmail;
        return this;
    }

    public User withSharedRunnersMinutesLimit(Integer sharedRunnersMinutesLimit) {
        this.sharedRunnersMinutesLimit = sharedRunnersMinutesLimit;
        return this;
    }

    public User withSkype(String skype) {
        this.skype = skype;
        return this;
    }

    public User withState(String state) {
        this.state = state;
        return this;
    }

    public User withThemeId(Integer themeId) {
        this.themeId = themeId;
        return this;
    }

    public User withTwitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public User withTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
        return this;
    }

    public User withWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
        return this;
    }

    public User withSkipConfirmation(Boolean skipConfirmation) {
        this.skipConfirmation = skipConfirmation;
        return this;
    }

    public User withCustomAttributes(List<CustomAttribute> customAttributes) {
        this.customAttributes = customAttributes;
        return this;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
