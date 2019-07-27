package org.gitlab4j.api.models;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.utils.JacksonJsonEnumHelper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This enum provides constants and value validation for the available GitLab application settings.
 * See <a href="https://docs.gitlab.com/ce/api/settings.html#list-of-settings-that-can-be-accessed-via-api-calls">
 * List of settings that can be accessed via API calls</a> for more information.
 */
public enum Setting {

    /**
     * Abuse reports will be sent to this address if it is set. Abuse reports are
     * always available in the admin area.
     */
    ADMIN_NOTIFICATION_EMAIL(String.class),

    /** Where to redirect users after logout. */
    AFTER_SIGN_OUT_PATH(String.class),

    /** Text shown to the user after signing up */
    AFTER_SIGN_UP_TEXT(String.class),

    /** required by: akismet_enabled API key for akismet spam protection. */
    AKISMET_API_KEY(String.class),

    /**
     * (If enabled, requires: akismet_api_key) Enable or disable akismet spam
     * protection.
     */
    AKISMET_ENABLED(Boolean.class),

    /** (Premium) Set to true to allow group owners to manage LDAP */
    ALLOW_GROUP_OWNERS_TO_MANAGE_LDAP(Boolean.class),

    /** Allow requests to the local network from hooks and services. */
    ALLOW_LOCAL_REQUESTS_FROM_HOOKS_AND_SERVICES(Boolean.class),

    /**
     * By default, we write to the authorized_keys file to support Git over SSH
     * without additional configuration. GitLab can be optimized to authenticate SSH
     * keys via the database file. Only disable this if you have configured your
     * OpenSSH server to use the AuthorizedKeysCommand.
     */
    AUTHORIZED_KEYS_ENABLED(Boolean.class),

    /**
     * Specify a domain to use by default for every project’s Auto Review Apps and
     * Auto Deploy stages.
     */
    AUTO_DEVOPS_DOMAIN(String.class),

    /**
     * Enable Auto DevOps for projects by default. It will automatically build,
     * test, and deploy applications based on a predefined CI/CD configuration.
     */
    AUTO_DEVOPS_ENABLED(Boolean.class),

    /**
     * (Premium) Enabling this will make only licensed EE features available to
     * projects if the project namespace’s plan includes the feature or if the
     * project is public.
     */
    CHECK_NAMESPACE_PLAN(Boolean.class),

    /**
     * required by: clientside_sentry_enabled Clientside Sentry Data Source Name.
     */
    CLIENTSIDE_SENTRY_DSN(String.class),

    /**
     * (If enabled, requires: clientside_sentry_dsn) Enable Sentry error reporting
     * for the client side.
     */
    CLIENTSIDE_SENTRY_ENABLED(Boolean.class),

    /** Container Registry token duration in minutes. */
    CONTAINER_REGISTRY_TOKEN_EXPIRE_DELAY(Integer.class),

    /** Set the default expiration time for each job’s artifacts. */
    DEFAULT_ARTIFACTS_EXPIRE_IN(String.class),

    /**
     * Determine if developers can push to master. Can take: 0 (not protected, both
     * developers and maintainers can push new commits, force push, or delete the
     * branch), 1 (partially protected, developers and maintainers can push new
     * commits, but cannot force push or delete the branch) or 2 (fully protected,
     * developers cannot push new commits, but maintainers can; no-one can force
     * push or delete the branch) as a parameter. Default is 2.
     */
    DEFAULT_BRANCH_PROTECTION(Integer.class),

    /**
     * What visibility level new groups receive. Can take private, internal and
     * public as a parameter. Default is private.
     */
    DEFAULT_GROUP_VISIBILITY(String.class),

    /**
     * What visibility level new projects receive. Can take private, internal and
     * public as a parameter. Default is private.
     */
    DEFAULT_PROJECT_VISIBILITY(String.class),

    /** Project limit per user. Default is 100000. */
    DEFAULT_PROJECTS_LIMIT(Integer.class),

    /**
     * What visibility level new snippets receive. Can take private, internal and
     * public as a parameter. Default is private.
     */
    DEFAULT_SNIPPET_VISIBILITY(String.class),

    /** Disabled OAuth sign-in sources. */
    DISABLED_OAUTH_SIGN_IN_SOURCES(String[].class),

    /** New as of 11.11.0 */
    DNS_REBINDING_PROTECTION_ENABLED(Boolean.class),

    /**
     * required by: domain_blacklist_enabled Users with e-mail addresses that match
     * these domain(s) will NOT be able to sign-up. Wildcards allowed. Use separate
     * lines for multiple entries. Ex: domain.com, *.domain.com.
     */
    DOMAIN_BLACKLIST(String[].class),

    /**
     * (If enabled, requires: domain_blacklist) Allows blocking sign-ups from emails
     * from specific domains.
     */
    DOMAIN_BLACKLIST_ENABLED(Boolean.class),

    /**
     * Force people to use only corporate emails for sign-up. Default is null,
     * meaning there is no restriction.
     */
    DOMAIN_WHITELIST(String[].class),

    /**
     * The minimum allowed bit length of an uploaded DSA key. Default is 0 (no
     * restriction). -1 disables DSA keys.
     */
    DSA_KEY_RESTRICTION(Integer.class),

    /**
     * The minimum allowed curve size (in bits) of an uploaded ECDSA key. Default is
     * 0 (no restriction). -1 disables ECDSA keys.
     */
    ECDSA_KEY_RESTRICTION(Integer.class),

    /**
     * The minimum allowed curve size (in bits) of an uploaded ED25519 key. Default
     * is 0 (no restriction). -1 disables ED25519 keys.
     */
    ED25519_KEY_RESTRICTION(Integer.class),

    /** (Premium) Enable the use of AWS hosted Elasticsearch */
    ELASTICSEARCH_AWS(Boolean.class),

    /** (Premium) AWS IAM access key */
    ELASTICSEARCH_AWS_ACCESS_KEY(String.class),

    /** (Premium) The AWS region the elasticsearch domain is configured */
    ELASTICSEARCH_AWS_REGION(String.class),

    /** (Premium) AWS IAM secret access key */
    ELASTICSEARCH_AWS_SECRET_ACCESS_KEY(String.class),

    /**
     * (Premium) Use the experimental elasticsearch indexer. More info:
     * https://gitlab.com/gitlab-org/gitlab-elasticsearch-indexer
     */
    ELASTICSEARCH_EXPERIMENTAL_INDEXER(Boolean.class),

    /** (Premium) Enable Elasticsearch indexing */
    ELASTICSEARCH_INDEXING(Boolean.class),

    /** (Premium) Enable Elasticsearch search */
    ELASTICSEARCH_SEARCH(Boolean.class),

    /**
     * (Premium) The url to use for connecting to Elasticsearch. Use a
     * comma-separated list to support cluster (e.g., “http://localhost:9200,
     * http://localhost:9201"). If your Elasticsearch instance is password
     * protected, pass the username:password in the URL,
     */
    ELASTICSEARCH_URL(String.class),

    /**
     * (Premium) Additional text added to the bottom of every email for
     * legal/auditing/compliance reasons
     */
    EMAIL_ADDITIONAL_TEXT(String.class),

    /**
     * Some email servers do not support overriding the email sender name. Enable
     * this option to include the name of the author of the issue, merge request or
     * comment in the email body instead.
     */
    EMAIL_AUTHOR_IN_BODY(Boolean.class),

    /**
     * Enabled protocols for Git access. Allowed values are: ssh, http, and nil to
     * allow both protocols.
     */
    ENABLED_GIT_ACCESS_PROTOCOL(String.class),

    /** (If enabled, requires: terms) Enforce application ToS to all users. */
    ENFORCE_TERMS(Boolean.class),

    /**
     * (Premium) (If enabled, requires: external_auth_client_key) The certificate to
     * use to authenticate with the external authorization service
     */
    EXTERNAL_AUTH_CLIENT_CERT(String.class),

    /**
     * required by: external_auth_client_cert (Premium) Private key for the
     * certificate when authentication is required for the external authorization
     * service, this is encrypted when stored
     */
    EXTERNAL_AUTH_CLIENT_KEY(String.class),

    /**
     * (Premium) Passphrase to use for the private key when authenticating with the
     * external service this is encrypted when stored
     */
    EXTERNAL_AUTH_CLIENT_KEY_PASS(String.class),

    /**
     * required by: external_authorization_service_enabled (Premium) The default
     * classification label to use when requesting authorization and no
     * classification label has been specified on the project
     */
    EXTERNAL_AUTHORIZATION_SERVICE_DEFAULT_LABEL(String.class),

    /**
     * (Premium) (If enabled, requires:
     * external_authorization_service_default_label,
     * external_authorization_service_timeout and external_authorization_service_url
     * ) Enable using an external authorization service for accessing projects
     */
    EXTERNAL_AUTHORIZATION_SERVICE_ENABLED(Boolean.class),

    /**
     * required by: external_authorization_service_enabled (Premium) The timeout
     * after which an authorization request is aborted, in seconds. When a request
     * times out, access is denied to the user. (min: 0.001, max: 10, step: 0.001)
     */
    EXTERNAL_AUTHORIZATION_SERVICE_TIMEOUT(Float.class),

    /**
     * required by: external_authorization_service_enabled (Premium) URL to which
     * authorization requests will be directed
     */
    EXTERNAL_AUTHORIZATION_SERVICE_URL(String.class),

    /** (Premium) The ID of a project to load custom file templates from */
    FILE_TEMPLATE_PROJECT_ID(Integer.class),

    /**
     * Start day of the week for calendar views and date pickers. Valid values are 0
     * (default) for Sunday, 1 for Monday, and 6 for Saturday.
     */
    FIRST_DAY_OF_WEEK(Integer.class),

    /**
     * (Premium) Comma-separated list of IPs and CIDRs of allowed secondary nodes.
     * For example, 1.1.1.1, 2.2.2.0/24.
     */
    GEO_NODE_ALLOWED_IPS(String.class),

    /**
     * (Premium) The amount of seconds after which a request to get a secondary node
     * status will time out.
     */
    GEO_STATUS_TIMEOUT(Integer.class),

    /**
     * Default Gitaly timeout, in seconds. This timeout is not enforced for git
     * fetch/push operations or Sidekiq jobs. Set to 0 to disable timeouts.
     */
    GITALY_TIMEOUT_DEFAULT(Integer.class),

    /**
     * Gitaly fast operation timeout, in seconds. Some Gitaly operations are
     * expected to be fast. If they exceed this threshold, there may be a problem
     * with a storage shard and ‘failing fast’ can help maintain the stability of
     * the GitLab instance. Set to 0 to disable timeouts.
     */
    GITALY_TIMEOUT_FAST(Integer.class),

    /**
     * Medium Gitaly timeout, in seconds. This should be a value between the Fast
     * and the Default timeout. Set to 0 to disable timeouts.
     */
    GITALY_TIMEOUT_MEDIUM(Integer.class),

    /** Undocumented setting. */
    GRAFANA_ENABLED(Boolean.class),

    /** Undocumented setting. */
    GRAFANA_URL(String.class),

    /** Enable Gravatar. */
    GRAVATAR_ENABLED(Boolean.class),

    /**
     * Create new projects using hashed storage paths: Enable immutable, hash-based
     * paths and repository names to store repositories on disk. This prevents
     * repositories from having to be moved or renamed when the Project URL changes
     * and may improve disk I/O performance. (EXPERIMENTAL)
     */
    HASHED_STORAGE_ENABLED(Boolean.class),

    /** Hide marketing-related entries from help. */
    HELP_PAGE_HIDE_COMMERCIAL_CONTENT(Boolean.class),

    /** Alternate support URL for help page. */
    HELP_PAGE_SUPPORT_URL(String.class),

    /** Custom text displayed on the help page. */
    HELP_PAGE_TEXT(String.class),

    /** (Premium) GitLab server administrator information */
    HELP_TEXT(String.class),

    /** Do not display offers from third parties within GitLab. */
    HIDE_THIRD_PARTY_OFFERS(Boolean.class),

    /** Redirect to this URL when not logged in. */
    HOME_PAGE_URL(String.class),

    /** required by: housekeeping_enabled Enable Git pack file bitmap creation. */
    HOUSEKEEPING_BITMAPS_ENABLED(Boolean.class),

    /**
     * (If enabled, requires: housekeeping_bitmaps_enabled,
     * housekeeping_full_repack_period, housekeeping_gc_period, and
     * housekeeping_incremental_repack_period) Enable or disable git housekeeping.
     */
    HOUSEKEEPING_ENABLED(Boolean.class),

    /**
     * required by: housekeeping_enabled Number of Git pushes after which an
     * incremental git repack is run.
     */
    HOUSEKEEPING_FULL_REPACK_PERIOD(Integer.class),

    /**
     * required by: housekeeping_enabled Number of Git pushes after which git gc is
     * run.
     */
    HOUSEKEEPING_GC_PERIOD(Integer.class),

    /**
     * required by: housekeeping_enabled Number of Git pushes after which an
     * incremental git repack is run.
     */
    HOUSEKEEPING_INCREMENTAL_REPACK_PERIOD(Integer.class),

    /** Enable HTML emails. */
    HTML_EMAILS_ENABLED(Boolean.class),

    /**
     * Sources to allow project import from, possible values: github, bitbucket,
     * gitlab, google_code, fogbugz, git, and gitlab_project.
     */
    IMPORT_SOURCES(String[].class),

    /** When set to true Instance statistics will only be available to admins. */
    INSTANCE_STATISTICS_VISIBILITY_PRIVATE(Boolean.class),

    /** Increase this value when any cached markdown should be invalidated. */
    LOCAL_MARKDOWN_VERSION(Integer.class),

    /** Maximum artifacts size in MB */
    MAX_ARTIFACTS_SIZE(Integer.class),

    /** Limit attachment size in MB */
    MAX_ATTACHMENT_SIZE(Integer.class),

    /** Maximum size of pages repositories in MB */
    MAX_PAGES_SIZE(Integer.class),

    /**
     * (If enabled, requires: metrics_host, metrics_method_call_threshold,
     * metrics_packet_size, metrics_pool_size, metrics_port, metrics_sample_interval
     * and metrics_timeout) Enable influxDB metrics.
     */
    METRICS_ENABLED(Boolean.class),

    /** required by: metrics_enabled InfluxDB host. */
    METRICS_HOST(String.class),

    /**
     * required by: metrics_enabled A method call is only tracked when it takes
     * longer than the given amount of milliseconds.
     */
    METRICS_METHOD_CALL_THRESHOLD(Integer.class),

    /**
     * required by: metrics_enabled The amount of datapoints to send in a single UDP
     * packet.
     */
    METRICS_PACKET_SIZE(Integer.class),

    /**
     * required by: metrics_enabled The amount of InfluxDB connections to keep open.
     */
    METRICS_POOL_SIZE(Integer.class),

    /**
     * required by: metrics_enabled The UDP port to use for connecting to InfluxDB.
     */
    METRICS_PORT(Integer.class),

    /** required by: metrics_enabled The sampling interval in seconds. */
    METRICS_SAMPLE_INTERVAL(Integer.class),

    /**
     * required by: metrics_enabled The amount of seconds after which InfluxDB will
     * time out.
     */
    METRICS_TIMEOUT(Integer.class),

    /**
     * Allow mirrors to be set up for projects. If disabled, only admins will be
     * able to set up mirrors in projects.
     */
    MIRROR_AVAILABLE(Boolean.class),

    /**
     * (Premium) Minimum capacity to be available before scheduling more mirrors
     * preemptively
     */
    MIRROR_CAPACITY_THRESHOLD(Integer.class),

    /**
     * (Premium) Maximum number of mirrors that can be synchronizing at the same
     * time.
     */
    MIRROR_MAX_CAPACITY(Integer.class),

    /**
     * (Premium) Maximum time (in minutes) between updates that a mirror can have
     * when scheduled to synchronize.
     */
    MIRROR_MAX_DELAY(Integer.class),

    /**
     * Require users to prove ownership of custom domains. Domain verification is an
     * essential security measure for public GitLab sites. Users are required to
     * demonstrate they control a domain before it is enabled.
     */
    PAGES_DOMAIN_VERIFICATION_ENABLED(Boolean.class),

    /**
     * Enable authentication for Git over HTTP(S) via a GitLab account password.
     * Default is true.
     */
    PASSWORD_AUTHENTICATION_ENABLED_FOR_GIT(Boolean.class),

    /**
     * Enable authentication for the web interface via a GitLab account password.
     * Default is true.
     */
    PASSWORD_AUTHENTICATION_ENABLED_FOR_WEB(Boolean.class),

    /**
     * (Deprecated: Use performance_bar_allowed_group_path instead) Path of the
     * group that is allowed to toggle the performance bar.
     */
    PERFORMANCE_BAR_ALLOWED_GROUP_ID(String.class),

    /** Path of the group that is allowed to toggle the performance bar. */
    PERFORMANCE_BAR_ALLOWED_GROUP_PATH(String.class),

    /**
     * (Deprecated: Pass performance_bar_allowed_group_path: nil instead) Allow
     * enabling the performance bar.
     */
    PERFORMANCE_BAR_ENABLED(Boolean.class),

    /**
     * (If enabled, requires: plantuml_url) Enable PlantUML integration. Default is
     * false.
     */
    PLANTUML_ENABLED(Boolean.class), // Decimal

    /** required by: plantuml_enabled The PlantUML instance URL for integration. */
    PLANTUML_URL(String.class),

    /**
     * Interval multiplier used by endpoints that perform polling. Set to 0 to disable polling.
     * The documentation liusts this as a decimal, but it is a String in the JSON.
     */
    POLLING_INTERVAL_MULTIPLIER(String.class),

    /** Enable project export. */
    PROJECT_EXPORT_ENABLED(Boolean.class),

    /** Enable prometheus metrics. */
    PROMETHEUS_METRICS_ENABLED(Boolean.class),

    /**
     * (Premium) When enabled, GitLab will run a background job that will produce
     * pseudonymized CSVs of the GitLab database that will be uploaded to your
     * configured object storage directory.
     */
    PSEUDONYMIZER_ENABLED(Boolean.class),

    /**
     * (If enabled, requires: recaptcha_private_key and recaptcha_site_key) Enable
     * recaptcha.
     */
    RECAPTCHA_ENABLED(Boolean.class),

    /** required by: recaptcha_enabled Private key for recaptcha. */
    RECAPTCHA_PRIVATE_KEY(String.class),

    /** required by: recaptcha_enabled Site key for recaptcha. */
    RECAPTCHA_SITE_KEY(String.class),

    /**
     * GitLab will periodically run git fsck in all project and wiki repositories to
     * look for silent disk corruption issues.
     */
    REPOSITORY_CHECKS_ENABLED(Boolean.class),

    /** (Premium) Size limit per repository (MB) */
    REPOSITORY_SIZE_LIMIT(Integer.class),

    /**
     * A list of names of enabled storage paths, taken from gitlab.yml. New projects
     * will be created in one of these stores, chosen at random.
     */
    REPOSITORY_STORAGES(String[].class),

    /**
     * (If enabled, requires: two_factor_grace_period) Require all users to set up
     * Two-factor authentication.
     */
    REQUIRE_TWO_FACTOR_AUTHENTICATION(Boolean.class),

    /**
     * Selected levels cannot be used by non-admin users for groups, projects or
     * snippets. Can take private, internal and public as a parameter. Default is
     * null which means there is no restriction.
     */
    RESTRICTED_VISIBILITY_LEVELS(String[].class),

    /**
     * The minimum allowed bit length of an uploaded RSA key. Default is 0 (no
     * restriction). -1 disables RSA keys.
     */
    RSA_KEY_RESTRICTION(Integer.class),

    /** Send confirmation email on sign-up. */
    SEND_USER_CONFIRMATION_EMAIL(Boolean.class),

    /** required by: sentry_enabled Sentry Data Source Name. */
    SENTRY_DSN(String.class),

    /**
     * (If enabled, requires: sentry_dsn) Sentry is an error reporting and logging
     * tool which is currently not shipped with GitLab, available at
     * https://sentry.io.
     */
    SENTRY_ENABLED(Boolean.class),

    /** Session duration in minutes. GitLab restart is required to apply changes */
    SESSION_EXPIRE_DELAY(Integer.class),

	/**
     * (If enabled, requires: shared_runners_text and shared_runners_minutes)
     * Enable shared runners for new projects.
     */
    SHARED_RUNNERS_ENABLED(Boolean.class),

    /**
     * required by: shared_runners_enabled (Premium) Set the maximum number of
     * pipeline minutes that a group can use on shared Runners per month.
     */
    SHARED_RUNNERS_MINUTES(Integer.class),

    /** required by: shared_runners_enabled Shared runners text. */
    SHARED_RUNNERS_TEXT(String.class),

    /** Text on the login page. */
    SIGN_IN_TEXT(String.class),

    /**
     * (Deprecated: Use password_authentication_enabled_for_web instead) Flag
     * indicating if password authentication is enabled for the web interface.
     * Documentation lists this as a String, but it s a Boolean
     */
    SIGNIN_ENABLED(Boolean.class),

    /** Enable registration. Default is true. */
    SIGNUP_ENABLED(Boolean.class),

    /**
     * (Premium) (If enabled, requires: slack_app_id, slack_app_secret and
     * slack_app_secret) Enable Slack app.
     */
    SLACK_APP_ENABLED(Boolean.class),

    /**
     * string required by: slack_app_enabled (Premium) The app id of the Slack-app.
     */
    SLACK_APP_ID(String.class),

    /** required by: slack_app_enabled (Premium) The app secret of the Slack-app. */
    SLACK_APP_SECRET(String.class),

    /**
     * required by: slack_app_enabled (Premium) The verification token of the
     * Slack-app.
     */
    SLACK_APP_VERIFICATION_TOKEN(String.class),

    /**
     * Maximum time for web terminal websocket connection (in seconds). Set to 0 for
     * unlimited time.
     */
    TERMINAL_MAX_SESSION_TIME(Integer.class),

    /**
     * required by: enforce_terms (Required by: enforce_terms) Markdown content for
     * the ToS.
     */
    TERMS(String.class),

    /**
     * (If enabled, requires: throttle_authenticated_api_period_in_seconds and
     * throttle_authenticated_api_requests_per_period) Enable authenticated API
     * request rate limit. Helps reduce request volume (e.g. from crawlers or
     * abusive bots).
     */
    THROTTLE_AUTHENTICATED_API_ENABLED(Boolean.class),

    /**
     * required by: throttle_authenticated_api_enabled Rate limit period in seconds.
     */
    THROTTLE_AUTHENTICATED_API_PERIOD_IN_SECONDS(Integer.class),

    /**
     * required by: throttle_authenticated_api_enabled Max requests per period per
     * user.
     */
    THROTTLE_AUTHENTICATED_API_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * (If enabled, requires: throttle_authenticated_web_period_in_seconds and
     * throttle_authenticated_web_requests_per_period) Enable authenticated web
     * request rate limit. Helps reduce request volume (e.g. from crawlers or
     * abusive bots).
     */
    THROTTLE_AUTHENTICATED_WEB_ENABLED(Boolean.class),

    /**
     * required by: throttle_authenticated_web_enabled Rate limit period in seconds.
     */
    THROTTLE_AUTHENTICATED_WEB_PERIOD_IN_SECONDS(Integer.class),

    /**
     * required by: throttle_authenticated_web_enabled Max requests per period per
     * user.
     */
    THROTTLE_AUTHENTICATED_WEB_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * (If enabled, requires: throttle_unauthenticated_period_in_seconds and
     * throttle_unauthenticated_requests_per_period) Enable unauthenticated request
     * rate limit. Helps reduce request volume (e.g. from crawlers or abusive bots).
     */
    THROTTLE_UNAUTHENTICATED_ENABLED(Boolean.class),

    /**
     * required by: throttle_unauthenticated_enabled Rate limit period in seconds.
     */
    THROTTLE_UNAUTHENTICATED_PERIOD_IN_SECONDS(Integer.class),

    /**
     * required by: throttle_unauthenticated_enabled Max requests per period per IP.
     */
    THROTTLE_UNAUTHENTICATED_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * Limit display of time tracking units to hours. Default is false.
     */
    TIME_TRACKING_LIMIT_TO_HOURS(Boolean.class),

    /**
     * required by: require_two_factor_authentication Amount of time (in hours) that
     * users are allowed to skip forced configuration of two-factor authentication.
     */
    TWO_FACTOR_GRACE_PERIOD(Integer.class),

    /**
     * (If enabled, requires: unique_ips_limit_per_user and
     * unique_ips_limit_time_window) Limit sign in from multiple ips.
     */
    UNIQUE_IPS_LIMIT_ENABLED(Boolean.class),

    /**
     * integer required by: unique_ips_limit_enabled Maximum number of ips per user.
     */
    UNIQUE_IPS_LIMIT_PER_USER(Integer.class),

    /**
     * required by: unique_ips_limit_enabled How many seconds an IP will be counted
     * towards the limit.
     */
    UNIQUE_IPS_LIMIT_TIME_WINDOW(Integer.class),

    /** Every week GitLab will report license usage back to GitLab, Inc. */
    USAGE_PING_ENABLED(Boolean.class),

    /** Newly registered users will be external by default. */
    USER_DEFAULT_EXTERNAL(Boolean.class),

    /**
     * Allow users to register any application to use GitLab as an OAuth provider.
     */
    USER_OAUTH_APPLICATIONS(Boolean.class),

    /**
     * When set to false disable the “You won’t be able to pull or push project code
     * via SSH” warning shown to users with no uploaded SSH key.
     */
    USER_SHOW_ADD_SSH_KEY_MESSAGE(Boolean.class),

    /** Let GitLab inform you when an update is available. */
    VERSION_CHECK_ENABLED(Boolean.class),

	/* The following settings are undocumented, but are returned from the server */
    ARCHIVE_BUILDS_IN_HUMAN_READABLE(Boolean.class),
    DEFAULT_PROJECT_CREATION(Integer.class),
    DOMAIN_BLACKLIST_RAW(String.class),
    DOMAIN_WHITELIST_RAW(String.class),
    RECEIVE_MAX_INPUT_SIZE(Integer.class),
    USER_DEFAULT_INTERNAL_REGEX(String.class),
    WEB_IDE_CLIENTSIDE_PREVIEW_ENABLED(Boolean.class),
    DIFF_MAX_PATCH_BYTES(Integer.class),
    COMMIT_EMAIL_HOSTNAME(String.class),
    PROTECTED_CI_VARIABLES(Boolean.class),
    PASSWORD_AUTHENTICATION_ENABLED(Boolean.class);

    private static JacksonJsonEnumHelper<Setting> enumHelper = new JacksonJsonEnumHelper<>(Setting.class);

    private Class<?> type;
    private Setting(Class<?> type) {
        this.type = type;
    }

    public final Class<?> getType() {
        return (type);
    }

    @JsonCreator
    public static Setting forValue(String value) {
        return enumHelper.forValue(value);
    }

    @JsonValue
    public String toValue() {
        return (enumHelper.toString(this));
    }

    @Override
    public String toString() {
        return (enumHelper.toString(this));
    }

    /**
     * Returns true if the provided value is of the correct type specified by this ApplicationSetting enum,
     * otherwise returns false.
     *
     * @param value the value to validate
     * @return true if the value is of the correct type or null
     */
    public boolean isValid(Object value) {
        return (value == null || value.getClass() == type);
    }

    /**
     * Validates the provided value against the data type of this ApplicationSetting enum.
     * Will throw a GitLabApiException if the value is not of the correct type.
     *
     * @param value the value to validate
     * @throws GitLabApiException if the provided value is not a valid type for the ApplicationSetting
     */
    public final void validate(Object value) throws GitLabApiException {

        if (isValid(value)) {
            return;
        }

        String errorMsg = String.format("'%s' value is of incorrect type, is %s, should be %s",
                toValue(), value.getClass().getSimpleName(), getType().getSimpleName());
        throw new GitLabApiException(errorMsg);
    }
}
