package org.gitlab4j.api.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
     * Require administrators to enable Admin Mode by re-authenticating for administrative tasks.
     */
    ADMIN_MODE(Boolean.class),

    /**
     * If set, abuse reports will be sent to this
     * address if it is set. Abuse reports are always available in the admin area.
     * @deprecated Use abuse_notification_email instead.
     */
    @Deprecated
    ADMIN_NOTIFICATION_EMAIL(String.class),

    /**
     * If set, abuse reports are sent to this address. Abuse reports are always available in the
     * Admin area.
     */
    ABUSE_NOTIFICATION_EMAIL(String.class),

    /**
     * Enable sending notification if sign in from unknown IP address happens.
     */
    NOTIFY_ON_UNKNOWN_SIGN_IN(Boolean.class),

    /**
     * Where to redirect users after logout.
     */

    AFTER_SIGN_OUT_PATH(String.class),

    /**
     * Text shown to the user after signing up
     */
    AFTER_SIGN_UP_TEXT(String.class),

    /**
     * required by: {@link #AKISMET_ENABLED} API key for Akismet spam protection.
     */
    AKISMET_API_KEY(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #AKISMET_API_KEY}) Enable or disable Akismet spam
     * protection.
     */
    AKISMET_ENABLED(Boolean.class),

    /**
     * (PREMIUM | SILVER) Set to true to allow group owners to manage LDAP
     */
    ALLOW_GROUP_OWNERS_TO_MANAGE_LDAP(Boolean.class),

    /**
     * Allow requests to the local network from hooks and services.
     * @deprecated Use allow_local_requests_from_web_hooks_and_services instead
     */
    @Deprecated
    ALLOW_LOCAL_REQUESTS_FROM_HOOKS_AND_SERVICES(Boolean.class),

    /**
     * Allow requests to the local network from system hooks.
     */
    ALLOW_LOCAL_REQUESTS_FROM_SYSTEM_HOOKS(Boolean.class),

    /**
     * Allow requests to the local network from web hooks and services.
     */
    ALLOW_LOCAL_REQUESTS_FROM_WEB_HOOKS_AND_SERVICES(Boolean.class),

    /**
     * Indicates whether users assigned up to the Guest role can create groups and personal
     * projects. Defaults to true.
     */
    ALLOW_PROJECT_CREATION_FOR_GUEST_AND_BELOW(Boolean.class),

    /**
     * Allow using a registration token to create a runner. Defaults to true.
     */
    ALLOW_RUNNER_REGISTRATION_TOKEN(Boolean.class),

    /**
     * Set the duration for which the jobs will be considered as old and expired.
     * Once that time passes, the jobs will be archived and no longer able to be  retried.
     * Make it empty to never expire jobs. It has to be no less than 1 day,
     * for example: 15 days, 1 month, 2 years.
     */
    ARCHIVE_BUILDS_IN_HUMAN_READABLE(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #ASSET_PROXY_URL})
     * Enable proxying of assets. GitLab restart is required to apply changes.
     */
    ASSET_PROXY_ENABLED(Boolean.class),

    /**
     * Shared secret with the asset proxy server. GitLab restart is required to apply changes.
     */
    ASSET_PROXY_SECRET_KEY(String.class),

    /**
     * URL of the asset proxy server. GitLab restart is required to apply changes.
     */
    ASSET_PROXY_URL(String.class),

    /**
     * Assets that match these domain(s) will NOT be proxied. Wildcards allowed.
     * Your GitLab installation URL is automatically whitelisted. GitLab restart
     * is required to apply changes.
     * @deprecated Use asset_proxy_allowlist instead
     */
    @Deprecated
    ASSET_PROXY_WHITELIST(new Class<?>[]{String.class, String[].class}),

    /**
     * Assets that match these domains are not proxied. Wildcards allowed. Your
     * GitLab installation URL is automatically allowlisted. GitLab restart is
     * required to apply changes.
     */
    ASSET_PROXY_ALLOWLIST(new Class<?>[]{String.class, String[].class}),

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
     * (PREMIUM | SILVER) Enabling this will make only licensed EE features
     * available to projects if the project namespace’s plan includes the feature
     * or if the project is public.
     */
    CHECK_NAMESPACE_PLAN(Boolean.class),

    /**
     * required by: {@link #CLIENTSIDE_SENTRY_DSN} Clientside Sentry Data Source Name.
     * removed by the following commit https://gitlab.com/gitlab-org/gitlab/commit/31c8ca6defd36bd08209ecc8c5913631c316ce37
     * @deprecated Will be removed in a future version of gitlab4j-api
     */
    @Deprecated
    CLIENTSIDE_SENTRY_DSN(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #CLIENTSIDE_SENTRY_DSN}) Enable Sentry error reporting for the client side.
     * @deprecated Will be removed in a future version of gitlab4j-api
     */
    @Deprecated
    CLIENTSIDE_SENTRY_ENABLED(Boolean.class),

    /**
     * Custom hostname (for private commit emails).
     */
    COMMIT_EMAIL_HOSTNAME(String.class),

    /**
     * Container Registry token duration in minutes.
     */
    CONTAINER_REGISTRY_TOKEN_EXPIRE_DELAY(Integer.class),

    /**
     * Set the default expiration time for each job’s artifacts.
     */
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

    DEFAULT_CI_CONFIG_PATH(String.class),


    /**
     * What visibility level new groups receive. Can take private, internal and
     * public as a parameter. Default is private.
     */
    DEFAULT_GROUP_VISIBILITY(String.class),

    /**
     * Default project creation protection. Can take: 0 (No one), 1 (Maintainers)
     * or 2 (Developers + Maintainers)
     */
    DEFAULT_PROJECT_CREATION(Integer.class),

    /**
     * What visibility level new projects receive. Can take private, internal and
     * public as a parameter. Default is private.
     */
    DEFAULT_PROJECT_VISIBILITY(String.class),

    /**
     * Project limit per user. Default is 100000.
     */
    DEFAULT_PROJECTS_LIMIT(Integer.class),

    /**
     * What visibility level new snippets receive. Can take private, internal and
     * public as a parameter. Default is private.
     */
    DEFAULT_SNIPPET_VISIBILITY(String.class),

    /**
     * Maximum diff patch size (Bytes).
     */
    DIFF_MAX_PATCH_BYTES(Integer.class),

    /**
     * Disabled OAuth sign-in sources.
     */
    DISABLED_OAUTH_SIGN_IN_SOURCES(String[].class),

    /**
     * Enforce DNS rebinding attack protection.
     */
    DNS_REBINDING_PROTECTION_ENABLED(Boolean.class),

    /**
     * required by: {@link #DOMAIN_BLACKLIST_ENABLED} Users with e-mail addresses that match
     * these domain(s) will NOT be able to sign-up. Wildcards allowed. Use separate
     * lines for multiple entries. Ex: domain.com, *.domain.com.
     */
    DOMAIN_BLACKLIST(String[].class),

    /**
     * (<strong>If enabled, requires:</strong>  {@link #DOMAIN_BLACKLIST}) Allows
     * blocking sign-ups from emails from specific domains.
     */
    DOMAIN_BLACKLIST_ENABLED(Boolean.class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * @deprecated Use {@link Setting#DOMAIN_BLACKLIST} instead. Will be removed in API v5
     * see https://gitlab.com/gitlab-org/gitlab/commit/85776fa3ffba6f641cf981cb0107f0e4ba882f3e#40f8529fa8ed874d8e312edb04db18420bf06d31_185_185
     */
    @Deprecated
    DOMAIN_BLACKLIST_RAW(String.class),

    /**
     * Force people to use only corporate emails for sign-up. Default is null,
     * meaning there is no restriction.
     */
    DOMAIN_WHITELIST(String[].class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * @deprecated Use {@link #DOMAIN_WHITELIST} instead. Will be removed in API v5
     * see https://gitlab.com/gitlab-org/gitlab/commit/85776fa3ffba6f641cf981cb0107f0e4ba882f3e#40f8529fa8ed874d8e312edb04db18420bf06d31_185_185
     */
    @Deprecated
    DOMAIN_WHITELIST_RAW(String.class),

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

    /**
     * (PREMIUM | SILVER) Enable the use of AWS hosted Elasticsearch
     */
    ELASTICSEARCH_AWS(Boolean.class),

    /**
     * (PREMIUM | SILVER) AWS IAM access key
     */
    ELASTICSEARCH_AWS_ACCESS_KEY(String.class),

    /**
     * (PREMIUM | SILVER) The AWS region the Elasticsearch domain is configured
     */
    ELASTICSEARCH_AWS_REGION(String.class),

    /**
     * (PREMIUM | SILVER) AWS IAM secret access key
     */
    ELASTICSEARCH_AWS_SECRET_ACCESS_KEY(String.class),

    /**
     * Amazon Access Key.
     */
    EKS_ACCESS_KEY_ID(String.class),

    /**
     * Amazon account ID
     */
    EKS_ACCOUNT_ID(String.class),

    /**
     * Enable integration with Amazon EKS.
     */
    EKS_INTEGRATION_ENABLED(Boolean.class),

    /**
     * AWS IAM secret access key
     */
    EKS_SECRET_ACCESS_KEY(String.class),

    /**
     * (PREMIUM | SILVER) Use the experimental elasticsearch indexer. More info:
     * https://gitlab.com/gitlab-org/gitlab-elasticsearch-indexer
     * Ruby indexer was removed and go indexer is no more experimental.
     * @deprecated removed in Gitlab 12.3. see https://gitlab.com/gitlab-org/gitlab/commit/82ba4a6a5c78501413012a9f2a918aa7353917a0?view=parallel#fbf64e6b8170f05f1b940fb05902d29f9eba3633_223_223
     */
    @Deprecated
    ELASTICSEARCH_EXPERIMENTAL_INDEXER(Boolean.class),

    /**
     * (PREMIUM | SILVER) Enable Elasticsearch indexing
     */
    ELASTICSEARCH_INDEXING(Boolean.class),

    /**
     * (PREMIUM | SILVER) Limit Elasticsearch to index certain namespaces and
     * projects
     */
    ELASTICSEARCH_LIMIT_INDEXING(Boolean.class),

    /**
     * (PREMIUM | SILVER) The namespaces to index via Elasticsearch if
     * {@link #ELASTICSEARCH_LIMIT_INDEXING} is enabled.
     */
    ELASTICSEARCH_NAMESPACE_IDS(Integer[].class),

    /**
     * (PREMIUM | SILVER) The projects to index via Elasticsearch if
     * {@link #ELASTICSEARCH_LIMIT_INDEXING} is enabled.
     */
    ELASTICSEARCH_PROJECT_IDS(Integer[].class),

    /**
     * (PREMIUM | SILVER) Enable Elasticsearch search
     */
    ELASTICSEARCH_SEARCH(Boolean.class),

    /**
     * (PREMIUM | SILVER) The url to use for connecting to Elasticsearch.
     * Use a comma-separated list to support cluster (e.g., http://localhost:9200,
     * http://localhost:9201"). If your Elasticsearch instance is password
     * protected, pass the username:password in the URL (e.g., http://username:password@elastic_host:9200/).
     */
    ELASTICSEARCH_URL(String[].class),

    /**
     * (PREMIUM | SILVER) Additional text added to the bottom of every email for
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

    /**
     * (<strong>If enabled, requires:</strong> {@link #TERMS}) Enforce application
     * ToS to all users.
     */
    ENFORCE_TERMS(Boolean.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #EXTERNAL_AUTH_CLIENT_KEY}) The
     * certificate to use to authenticate with the external authorization service
     */
    EXTERNAL_AUTH_CLIENT_CERT(String.class),

    /**
     * required by: {@link #EXTERNAL_AUTH_CLIENT_CERT} Private key for the certificate
     * when authentication is required for the external authorization service, this is
     * encrypted when stored
     */
    EXTERNAL_AUTH_CLIENT_KEY(String.class),

    /**
     * Passphrase to use for the private key when authenticating with the
     * external service this is encrypted when stored
     */
    EXTERNAL_AUTH_CLIENT_KEY_PASS(String.class),

    /**
     * required by: {@link #EXTERNAL_AUTHORIZATION_SERVICE_ENABLED} The default
     * classification label to use when requesting authorization and no
     * classification label has been specified on the project
     */
    EXTERNAL_AUTHORIZATION_SERVICE_DEFAULT_LABEL(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #EXTERNAL_AUTHORIZATION_SERVICE_DEFAULT_LABEL},
     * {@link #EXTERNAL_AUTHORIZATION_SERVICE_TIMEOUT} and {@link #EXTERNAL_AUTHORIZATION_SERVICE_URL})
     * Enable using an external authorization service for accessing projects
     */
    EXTERNAL_AUTHORIZATION_SERVICE_ENABLED(Boolean.class),

    /**
     * required by: {@link #EXTERNAL_AUTHORIZATION_SERVICE_ENABLED} The timeout after which an
     * authorization request is aborted, in seconds. When a request times out, access is denied
     * to the user. (min: 0.001, max: 10, step: 0.001)
     */
    EXTERNAL_AUTHORIZATION_SERVICE_TIMEOUT(Float.class),

    /**
     * required by: {@link #EXTERNAL_AUTHORIZATION_SERVICE_ENABLED} URL to which authorization
     * requests will be directed
     */
    EXTERNAL_AUTHORIZATION_SERVICE_URL(String.class),

    /**
     * (PREMIUM | SILVER) The ID of a project to load custom file templates from
     */
    FILE_TEMPLATE_PROJECT_ID(Integer.class),

    /**
     * Start day of the week for calendar views and date pickers. Valid values are 0
     * (default) for Sunday, 1 for Monday, and 6 for Saturday.
     */
    FIRST_DAY_OF_WEEK(Integer.class),

    /**
     * (PREMIUM | SILVER) Comma-separated list of IPs and CIDRs of allowed secondary nodes.
     * For example, 1.1.1.1, 2.2.2.0/24.
     */
    GEO_NODE_ALLOWED_IPS(String.class),

    /**
     * (PREMIUM | SILVER) The amount of seconds after which a request to get a secondary node
     * status will time out.
     */
    GEO_STATUS_TIMEOUT(Integer.class),

    /**
     * Default Gitaly timeout, in seconds. This timeout is not enforced for Git
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

    /**
     * Enable Grafana.
     */
    GRAFANA_ENABLED(Boolean.class),

    /**
     * Grafana URL.
     */
    GRAFANA_URL(String.class),

    /**
     * Enable Gravatar.
     */
    GRAVATAR_ENABLED(Boolean.class),

    /**
     * Create new projects using hashed storage paths: Enable immutable, hash-based paths
     * and repository names to store repositories on disk. This prevents repositories from
     * having to be moved or renamed when the Project URL changes and may improve disk I/O
     * performance. (EXPERIMENTAL)
     */
    HASHED_STORAGE_ENABLED(Boolean.class),

    /**
     * Hide marketing-related entries from help.
     */
    HELP_PAGE_HIDE_COMMERCIAL_CONTENT(Boolean.class),

    /**
     * Alternate support URL for help page and help dropdown.
     */
    HELP_PAGE_SUPPORT_URL(String.class),

    /**
     * Custom text displayed on the help page.
     */
    HELP_PAGE_TEXT(String.class),

    /**
     * (PREMIUM | SILVER) GitLab server administrator information
     */
    HELP_TEXT(String.class),

    /**
     * Do not display offers from third parties within GitLab.
     */
    HIDE_THIRD_PARTY_OFFERS(Boolean.class),

    /**
     * Redirect to this URL when not logged in.
     */
    HOME_PAGE_URL(String.class),

    /**
     * required by: {@link #HOUSEKEEPING_ENABLED} Enable Git pack file bitmap creation.
     */
    HOUSEKEEPING_BITMAPS_ENABLED(Boolean.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #HOUSEKEEPING_BITMAPS_ENABLED},
     * {@link #HOUSEKEEPING_FULL_REPACK_PERIOD}, {@link #HOUSEKEEPING_GC_PERIOD}, and
     * {@link #HOUSEKEEPING_INCREMENTAL_REPACK_PERIOD}) Enable or disable Git housekeeping.
     */
    HOUSEKEEPING_ENABLED(Boolean.class),

    /**
     * required by: {@link #HOUSEKEEPING_ENABLED} Number of Git pushes after which an
     * incremental git repack is run.
     */
    HOUSEKEEPING_FULL_REPACK_PERIOD(Integer.class),

    /**
     * required by: {@link #HOUSEKEEPING_ENABLED} Number of Git pushes after which git
     * gc is run.
     */
    HOUSEKEEPING_GC_PERIOD(Integer.class),

    /**
     * required by: {@link #HOUSEKEEPING_ENABLED} Number of Git pushes after which an
     * incremental git repack is run.
     */
    HOUSEKEEPING_INCREMENTAL_REPACK_PERIOD(Integer.class),

    /**
     * Enable HTML emails.
     */
    HTML_EMAILS_ENABLED(Boolean.class),

    /**
     * Sources to allow project import from, possible values: github, bitbucket,
     * bitbucket_server, gitlab, google_code, fogbugz, git, gitlab_project, gitea,
     * manifest, and phabricator.
     */
    IMPORT_SOURCES(String[].class),

    /**
     * When set to true Instance statistics will only be available to admins.
     */
    INSTANCE_STATISTICS_VISIBILITY_PRIVATE(Boolean.class),

    /**
     * Increase this value when any cached markdown should be invalidated.
     */
    LOCAL_MARKDOWN_VERSION(Integer.class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * Was added with this commit https://gitlab.com/gitlab-org/gitlab/commit/30e7f01877fd436e21efdf0974d42d8fc83f4883
     * @since 2019-07-18
     */
    LOGIN_RECAPTCHA_PROTECTION_ENABLED(Boolean.class),

    /**
     * Maximum artifacts size in MB
     */
    MAX_ARTIFACTS_SIZE(Integer.class),

    /**
     * Limit attachment size in MB
     */
    MAX_ATTACHMENT_SIZE(Integer.class),

    /**
     * Maximum import size in MB. 0 for unlimited. Default = 50
     */
    MAX_IMPORT_SIZE(Integer.class),

    /**
     * Maximum size of pages repositories in MB
     */
    MAX_PAGES_SIZE(Integer.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #METRICS_HOST},
     * {@link #METRICS_METHOD_CALL_THRESHOLD}, {@link #METRICS_PACKET_SIZE},
     * {@link #METRICS_POOL_SIZE}, {@link #METRICS_PORT}, {@link #METRICS_SAMPLE_INTERVAL} and
     * {@link #METRICS_TIMEOUT}) Enable influxDB metrics.
     */
    METRICS_ENABLED(Boolean.class),

    /**
     * required by: {@link #METRICS_ENABLED} InfluxDB host.
     */
    METRICS_HOST(String.class),

    /**
     * required by: {@link #METRICS_ENABLED} A method call is only tracked when it takes
     * longer than the given amount of milliseconds.
     */
    METRICS_METHOD_CALL_THRESHOLD(Integer.class),

    /**
     * required by: {@link #METRICS_ENABLED} The amount of datapoints to send in a single UDP
     * packet.
     */
    METRICS_PACKET_SIZE(Integer.class),

    /**
     * required by: {@link #METRICS_ENABLED} The amount of InfluxDB connections to keep open.
     */
    METRICS_POOL_SIZE(Integer.class),

    /**
     * required by: {@link #METRICS_ENABLED} The UDP port to use for connecting to InfluxDB.
     */
    METRICS_PORT(Integer.class),

    /**
     * required by: {@link #METRICS_ENABLED} The sampling interval in seconds.
     */
    METRICS_SAMPLE_INTERVAL(Integer.class),

    /**
     * required by: {@link #METRICS_ENABLED} The amount of seconds after which InfluxDB will
     * time out.
     */
    METRICS_TIMEOUT(Integer.class),

    /**
     * Allow repository mirroring to configured by project Maintainers. If disabled, only
     * Admins will be able to configure repository mirroring.
     */
    MIRROR_AVAILABLE(Boolean.class),

    /**
     * (PREMIUM | SILVER) Minimum capacity to be available before scheduling more mirrors
     * preemptively
     */
    MIRROR_CAPACITY_THRESHOLD(Integer.class),

    /**
     * (PREMIUM | SILVER) Maximum number of mirrors that can be synchronizing at the same time.
     */
    MIRROR_MAX_CAPACITY(Integer.class),

    /**
     * (PREMIUM | SILVER) Maximum time (in minutes) between updates that a mirror can have
     * when scheduled to synchronize.
     */
    MIRROR_MAX_DELAY(Integer.class),

    /**
     * Define a list of trusted domains or ip addresses to which local requests are allowed when
     * local requests for hooks and services are disabled.
     */
    OUTBOUND_LOCAL_REQUESTS_WHITELIST(String[].class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * Added with this commit https://gitlab.com/gitlab-org/gitlab/commit/336046254cfe69d795bc8ea454daaf5a35b60eac
     */
    OUTBOUND_LOCAL_REQUESTS_WHITELIST_RAW(String.class),

    /**
     * Require users to prove ownership of custom domains. Domain verification is an
     * essential security measure for public GitLab sites. Users are required to
     * demonstrate they control a domain before it is enabled.
     */
    PAGES_DOMAIN_VERIFICATION_ENABLED(Boolean.class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * Present for retro-compatibility purpose. See https://gitlab.com/gitlab-org/gitlab/commit/63b2082979efe182daf78e8269b252ccc73f93fc#958cb0573403da359fda7dac60baf49147a5c538_166_181
     * @deprecated Use {@link #PASSWORD_AUTHENTICATION_ENABLED_FOR_WEB} instead.
     */
    @Deprecated
    PASSWORD_AUTHENTICATION_ENABLED(Boolean.class),

    /**
     * Enable authentication for Git over HTTP(S) via a GitLab account password. Default is true.
     */
    PASSWORD_AUTHENTICATION_ENABLED_FOR_GIT(Boolean.class),

    /**
     * Enable authentication for the web interface via a GitLab account password. Default is true.
     */
    PASSWORD_AUTHENTICATION_ENABLED_FOR_WEB(Boolean.class),

    /**
     * ID of the group that is allowed to toggle the performance bar.
     * @deprecated Use {@link #PERFORMANCE_BAR_ALLOWED_GROUP_PATH} instead.
     */
    @Deprecated
    PERFORMANCE_BAR_ALLOWED_GROUP_ID(Integer.class),

    /**
     * Path of the group that is allowed to toggle the performance bar.
     */
    PERFORMANCE_BAR_ALLOWED_GROUP_PATH(String.class),

    /**
     * Allow enabling the performance bar.
     * @deprecated Pass performance_bar_allowed_group_path: nil instead
     */
    @Deprecated
    PERFORMANCE_BAR_ENABLED(Boolean.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #PLANTUML_URL}) Enable PlantUML integration.
     * Default is false.
     */
    PLANTUML_ENABLED(Boolean.class),

    /**
     * required by: {@link #PLANTUML_ENABLED} The PlantUML instance URL for integration.
     */
    PLANTUML_URL(String.class),

    /**
     * Interval multiplier used by endpoints that perform polling. Set to 0 to disable polling.
     * The documentation lists this as a decimal, but it is a String in the JSON.
     */
    POLLING_INTERVAL_MULTIPLIER(String.class),

    /**
     * Enable project export.
     */
    PROJECT_EXPORT_ENABLED(Boolean.class),

    /**
     * Maximum authenticated requests to /project/:id/jobs per minute. Introduced in GitLab 16.5.
     * Default: 600.
     */
    PROJECT_JOBS_API_RATE_LIMIT(Integer.class),

    /**
     * Enable Prometheus metrics.
     */
    PROMETHEUS_METRICS_ENABLED(Boolean.class),

    /**
     * Environment variables are protected by default.
     */
    PROTECTED_CI_VARIABLES(Boolean.class),

    /**
     * (PREMIUM | SILVER) When enabled, GitLab will run a background job that will produce
     * pseudonymized CSVs of the GitLab database that will be uploaded to your configured
     * object storage directory.
     */
    PSEUDONYMIZER_ENABLED(Boolean.class),

    /**
     * Number of changes (branches or tags) in a single push to determine whether webhooks
     * and services will be fired or not. Webhooks and services won’t be submitted if it
     * surpasses that value.
     */
    PUSH_EVENT_HOOKS_LIMIT(Integer.class),

    /**
     * Number of changes (branches or tags) in a single push to determine whether individual
     * push events or bulk push events will be created.
     * <a href="https://docs.gitlab.com/ee/user/admin_area/settings/push_event_activities_limit.html">
     * Bulk push events will be created</a> if it surpasses that value.
     */
    PUSH_EVENT_ACTIVITIES_LIMIT(Integer.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #RECAPTCHA_PRIVATE_KEY} and
     * {@link #RECAPTCHA_SITE_KEY}) Enable reCAPTCHA.
     */
    RECAPTCHA_ENABLED(Boolean.class),

    /**
     * required by: {@link #RECAPTCHA_ENABLED} Private key for reCAPTCHA.
     */
    RECAPTCHA_PRIVATE_KEY(String.class),

    /**
     * required by: {@link #RECAPTCHA_ENABLED} Site key for reCAPTCHA.
     */
    RECAPTCHA_SITE_KEY(String.class),

    /**
     * Maximum push size (MB).
     */
    RECEIVE_MAX_INPUT_SIZE(Integer.class),

    /**
     * GitLab will periodically run git fsck in all project and wiki repositories to
     * look for silent disk corruption issues.
     */
    REPOSITORY_CHECKS_ENABLED(Boolean.class),

    /**
     * (PREMIUM | SILVER) Size limit per repository (MB)
     */
    REPOSITORY_SIZE_LIMIT(Integer.class),

    /**
     * A list of names of enabled storage paths, taken from gitlab.yml. New projects
     * will be created in one of these stores, chosen at random.
     */
    REPOSITORY_STORAGES(String[].class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #TWO_FACTOR_GRACE_PERIOD}) Require all
     * users to set up Two-factor authentication.
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

    /**
     * Send confirmation email on sign-up.
     */
    SEND_USER_CONFIRMATION_EMAIL(Boolean.class),

    /**
     * Session duration in minutes. GitLab restart is required to apply changes
     */
    SESSION_EXPIRE_DELAY(Integer.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #SHARED_RUNNERS_TEXT} and
     * {@link #SHARED_RUNNERS_MINUTES}) Enable shared runners for new projects.
     */
    SHARED_RUNNERS_ENABLED(Boolean.class),

    /**
     * (PREMIUM | SILVER) required by: {@link #SHARED_RUNNERS_ENABLED} Set the maximum number
     * of pipeline minutes that a group can use on shared Runners per month.
     */
    SHARED_RUNNERS_MINUTES(Integer.class),

    /**
     * required by: {@link #SHARED_RUNNERS_ENABLED} Shared runners text.
     */
    SHARED_RUNNERS_TEXT(String.class),

    /**
     * Text on the login page.
     */
    SIGN_IN_TEXT(String.class),

    /**
     * Flag indicating if password authentication is enabled for the web interface.
     * Documentation lists this as a String, but it s a Boolean.
     * @deprecated Use {@link #PASSWORD_AUTHENTICATION_ENABLED_FOR_WEB} instead
     */
    @Deprecated
    SIGNIN_ENABLED(Boolean.class),

    /**
     * Enable registration. Default is true.
     */
    SIGNUP_ENABLED(Boolean.class),

    /**
     * (PREMIUM | SILVER) (<strong>If enabled, requires:</strong> {@link #SLACK_APP_ID},
     * {@link #SLACK_APP_SECRET} and {@link #SLACK_APP_VERIFICATION_TOKEN}) Enable Slack app.
     */
    SLACK_APP_ENABLED(Boolean.class),

    /**
     * (PREMIUM | SILVER) required by: {@link #SLACK_APP_ENABLED} The app id of the Slack-app.
     */
    SLACK_APP_ID(String.class),

    /**
     * (PREMIUM | SILVER) required by: {@link #SLACK_APP_ENABLED} The app secret of the
     * Slack-app.
     */
    SLACK_APP_SECRET(String.class),

    /**
     * (PREMIUM | SILVER) required by: {@link #SLACK_APP_ENABLED}  The verification token of
     * the Slack-app.
     */
    SLACK_APP_VERIFICATION_TOKEN(String.class),

    /**
     * The Snowplow site name / application id. (e.g. gitlab)
     */
    SNOWPLOW_APP_ID(String.class),

    /**
     * required by: {@link #SNOWPLOW_ENABLED} The Snowplow collector hostname.
     * (e.g. snowplow.trx.gitlab.net)
     */
    SNOWPLOW_COLLECTOR_HOSTNAME(String.class),

    /**
     * The Snowplow cookie domain. (e.g. .gitlab.com)
     */
    SNOWPLOW_COOKIE_DOMAIN(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #SNOWPLOW_COLLECTOR_HOSTNAME})
     * Enable snowplow tracking.
     */
    SNOWPLOW_ENABLED(Boolean.class),

    /**
     * The Snowplow base Iglu Schema Registry URL to use for custom context and self describing events.
     */
    SNOWPLOW_IGLU_REGISTRY_URL(String.class),

    /**
     * The Snowplow site name / application id. (e.g. gitlab)
     */
    SNOWPLOW_SITE_ID(String.class),

    /**
     * Enables Sourcegraph integration. Default is false. If enabled, requires sourcegraph_url.
     */
    SOURCEGRAPH_ENABLED(Boolean.class),

    /**
     * Blocks Sourcegraph from being loaded on private and internal projects. Defaul is true.
     */
    SOURCEGRAPH_PUBLIC_ONLY(Boolean.class),

    /**
     * The Sourcegraph instance URL for integration.
     */
    SOURCEGRAPH_URL(String.class),

    /**
     * Enables Spam Check via external API endpoint. Default is false.
     */
    SPAM_CHECK_ENDPOINT_ENABLED(Boolean.class),

    /**
     * URL of the external Spam Check service endpoint.
     */
    SPAM_CHECK_ENDPOINT_URL(String.class),

    /**
     * required by: {@link #PENDO_ENABLED} The Pendo endpoint url with js snippet.
     * (e.g. https://cdn.pendo.io/agent/static/your-api-key/pendo.js)
     */
    PENDO_URL(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #PENDO_URL}) Enable pendo tracking.
     */
    PENDO_ENABLED(Boolean.class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * https://gitlab.com/gitlab-org/gitlab/commit/85975447a2b70d1654f2f8163f55d369e130ef2b
     */
    STATIC_OBJECTS_EXTERNAL_STORAGE_AUTH_TOKEN(String.class),

    /**
     * NOT DOCUMENTED: but it's returned by a call to /api/v4/application/settings
     * https://gitlab.com/gitlab-org/gitlab/commit/85975447a2b70d1654f2f8163f55d369e130ef2b
     */
    STATIC_OBJECTS_EXTERNAL_STORAGE_URL(String.class),

    /**
     * Maximum time for web terminal websocket connection (in seconds). Set to 0 for
     * unlimited time.
     */
    TERMINAL_MAX_SESSION_TIME(Integer.class),

    /**
     * required by: {@link #ENFORCE_TERMS} Markdown content for the ToS.
     */
    TERMS(String.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #THROTTLE_AUTHENTICATED_API_PERIOD_IN_SECONDS}
     * and {@link #THROTTLE_AUTHENTICATED_API_REQUESTS_PER_PERIOD}) Enable authenticated API
     * request rate limit. Helps reduce request volume (e.g. from crawlers or abusive bots).
     */
    THROTTLE_AUTHENTICATED_API_ENABLED(Boolean.class),

    /**
     * required by: {@link #THROTTLE_AUTHENTICATED_API_ENABLED} Rate limit period in seconds.
     */
    THROTTLE_AUTHENTICATED_API_PERIOD_IN_SECONDS(Integer.class),

    /**
     * required by: {@link #THROTTLE_AUTHENTICATED_API_ENABLED} Max requests per period per user.
     */
    THROTTLE_AUTHENTICATED_API_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #THROTTLE_AUTHENTICATED_WEB_PERIOD_IN_SECONDS}
     * and {@link #THROTTLE_AUTHENTICATED_WEB_REQUESTS_PER_PERIOD}) Enable authenticated web
     * request rate limit. Helps reduce request volume (e.g. from crawlers or abusive bots).
     */
    THROTTLE_AUTHENTICATED_WEB_ENABLED(Boolean.class),

    /**
     * required by: {@link #THROTTLE_AUTHENTICATED_WEB_ENABLED}	Rate limit period in seconds.
     */
    THROTTLE_AUTHENTICATED_WEB_PERIOD_IN_SECONDS(Integer.class),

    /**
     * required by: {@link #THROTTLE_AUTHENTICATED_WEB_ENABLED} Max requests per period per user.
     */
    THROTTLE_AUTHENTICATED_WEB_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #THROTTLE_UNAUTHENTICATED_PERIOD_IN_SECONDS}
     * and {@link #THROTTLE_UNAUTHENTICATED_REQUESTS_PER_PERIOD}) Enable unauthenticated request
     * rate limit. Helps reduce request volume (e.g. from crawlers or abusive bots).
     */
    THROTTLE_UNAUTHENTICATED_ENABLED(Boolean.class),

    /**
     * required by: {@link #THROTTLE_UNAUTHENTICATED_ENABLED} Rate limit period in seconds.
     */
    THROTTLE_UNAUTHENTICATED_PERIOD_IN_SECONDS(Integer.class),

    /**
     * required by: {@link #THROTTLE_UNAUTHENTICATED_ENABLED} Max requests per period per IP.
     */
    THROTTLE_UNAUTHENTICATED_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * Limit display of time tracking units to hours. Default is false.
     */
    TIME_TRACKING_LIMIT_TO_HOURS(Boolean.class),

    /**
     * required by: {@link #REQUIRE_TWO_FACTOR_AUTHENTICATION} Amount of time (in hours) that
     * users are allowed to skip forced configuration of two-factor authentication.
     */
    TWO_FACTOR_GRACE_PERIOD(Integer.class),

    /**
     * (<strong>If enabled, requires:</strong> {@link #UNIQUE_IPS_LIMIT_PER_USER} and
     * {@link #UNIQUE_IPS_LIMIT_TIME_WINDOW}) Limit sign in from multiple ips.
     */
    UNIQUE_IPS_LIMIT_ENABLED(Boolean.class),

    /**
     * required by: {@link #UNIQUE_IPS_LIMIT_ENABLED} Maximum number of ips per user.
     */
    UNIQUE_IPS_LIMIT_PER_USER(Integer.class),

    /**
     * required by: {@link #UNIQUE_IPS_LIMIT_ENABLED} How many seconds an IP will be
     * counted towards the limit.
     */
    UNIQUE_IPS_LIMIT_TIME_WINDOW(Integer.class),

    /**
     * Every week GitLab will report license usage back to GitLab, Inc.
     */
    USAGE_PING_ENABLED(Boolean.class),

    /**
     * Newly registered users will be external by default.
     */
    USER_DEFAULT_EXTERNAL(Boolean.class),

    /**
     * Specify an e-mail address regex pattern to identify default internal users.
     */
    USER_DEFAULT_INTERNAL_REGEX(String.class),

    /**
     * Allow users to register any application to use GitLab as an OAuth provider.
     */
    USER_OAUTH_APPLICATIONS(Boolean.class),

    /**
     * When set to false disable the “You won’t be able to pull or push project code
     * via SSH” warning shown to users with no uploaded SSH key.
     */
    USER_SHOW_ADD_SSH_KEY_MESSAGE(Boolean.class),

    /**
     * Let GitLab inform you when an update is available.
     */
    VERSION_CHECK_ENABLED(Boolean.class),

    /**
     * Client side evaluation (allow live previews of JavaScript projects in the Web IDE
     * using CodeSandbox client side evaluation).
     */
    WEB_IDE_CLIENTSIDE_PREVIEW_ENABLED(Boolean.class),

    /**
     * Maximum number of simultaneous import jobs for the GitHub importer. Default is 1000.
     * Introduced in GitLab 16.11.
     */
    CONCURRENT_GITHUB_IMPORT_JOBS_LIMIT(Integer.class),

    /**
     * Maximum number of simultaneous import jobs for the Bitbucket Cloud importer. Default is 100.
     * Introduced in GitLab 16.11.
     */
    CONCURRENT_BITBUCKET_IMPORT_JOBS_LIMIT(Integer.class),

    /**
     * Maximum number of simultaneous import jobs for the Bitbucket Server importer. Default is 100.
     * Introduced in GitLab 16.11.
     */
    CONCURRENT_BITBUCKET_SERVER_IMPORT_JOBS_LIMIT(Integer.class),

    /**
     * Caching during the execution of cleanup policies.
     */
    CONTAINER_REGISTRY_EXPIRATION_POLICIES_CACHING(Boolean.class),

    /**
     * Default timeout for decompressing archived files, in seconds. Set to 0 to disable timeouts.
     * Introduced in GitLab 16.4.
     */
    DECOMPRESS_ARCHIVE_FILE_TIMEOUT(Integer.class),

    /**
     * Instance-level custom initial branch name.
     */
    DEFAULT_BRANCH_NAME(String.class),

    /**
     * Introduced in GitLab 17.0. For available options,
     * see Options for default_branch_protection_defaults.
     */
    DEFAULT_BRANCH_PROTECTION_DEFAULTS(HashMap.class),

    /**
     * Default preferred language for users who are not logged in.
     */
    DEFAULT_PREFERRED_LANGUAGE(String.class),

    /**
     * Default syntax highlighting theme for users who are new or not signed in.
     * See IDs of available themes.
     */
    DEFAULT_SYNTAX_HIGHLIGHTING_THEME(Integer.class),

    /**
     * Enable inactive project deletion. Default is false.
     * Became operational without feature flag in GitLab 15.4.
     */
    DELETE_INACTIVE_PROJECTS(Boolean.class),

    /**
     * Stops administrators from connecting their GitLab accounts to non-trusted OAuth 2.0
     * applications that have the api, read_api, read_repository, write_repository, read_registry,
     * write_registry, or sudo scopes. Introduced in GitLab 15.6.
     */
    DISABLE_ADMIN_OAUTH_SCOPES(Boolean.class),

    /**
     * Disable display of RSS/Atom and calendar feed tokens.
     */
    DISABLE_FEED_TOKEN(Boolean.class),

    /**
     * Users with email addresses that match these domains cannot sign up. Wildcards allowed.
     * Enter multiple entries on separate lines. For example: domain.com, *.domain.com.
     */
    DOMAIN_DENYLIST(String[].class),

    /**
     * (If enabled, requires: domain_denylist) Allows blocking sign-ups from emails from specific
     * domains.
     */
    DOMAIN_DENYLIST_ENABLED(Boolean.class),

    /**
     * Force people to use only corporate emails for sign-up. Default is null, meaning there is no
     * restriction.
     */
    DOMAIN_ALLOWLIST(String[].class),

    /**
     * The minimum allowed curve size (in bits) of an uploaded ECDSA_SK key.
     * Default is 0 (no restriction). -1 disables ECDSA_SK keys.
     */
    ECDSA_SK_KEY_RESTRICTION(Integer.class),

    /**
     * The minimum allowed curve size (in bits) of an uploaded ED25519_SK key.
     * Default is 0 (no restriction). -1 disables ED25519_SK keys.
     */
    ED25519_SK_KEY_RESTRICTION(Integer.class),

    /**
     * Specifies whether users must confirm their email before sign in. Possible values are off,
     * soft, and hard.
     */
    EMAIL_CONFIRMATION_SETTING(String.class),

    /**
     * How long to wait for a response from the pipeline validation service.
     * Assumes OK if it times out.
     */
    EXTERNAL_PIPELINE_VALIDATION_SERVICE_TIMEOUT(Integer.class),

    /**
     * Optional. Token to include as the X-Gitlab-Token header in requests to the URL in
     * external_pipeline_validation_service_url.
     */
    EXTERNAL_PIPELINE_VALIDATION_SERVICE_TOKEN(String.class),

    /**
     * URL to use for pipeline validation requests.
     */
    EXTERNAL_PIPELINE_VALIDATION_SERVICE_URL(String.class),

    /**
     * Time period in minutes after which the user is unlocked when maximum number of failed
     * sign-in attempts reached.
     */
    FAILED_LOGIN_ATTEMPTS_UNLOCK_PERIOD_IN_MINUTES(Integer.class),

    /**
     * (If enabled, requires: gitpod_url) Enable Gitpod integration. Default is false.
     */
    GITPOD_ENABLED(Boolean.class),

    /**
     * The Gitpod instance URL for integration.
     */
    GITPOD_URL(String.class),

    /**
     * Number of Git pushes after which an incremental git repack is run.
     */
    HOUSEKEEPING_OPTIMIZE_REPOSITORY_PERIOD(Integer.class),

    /**
     * If delete_inactive_projects is true, the time (in months) to wait before deleting inactive
     * projects. Default is 2. Became operational in GitLab 15.0.
     */
    INACTIVE_PROJECTS_DELETE_AFTER_MONTHS(Integer.class),

    /**
     * If delete_inactive_projects is true, the minimum repository size for projects to be checked
     * for inactivity. Default is 0. Became operational in GitLab 15.0.
     */
    INACTIVE_PROJECTS_MIN_SIZE_MB(Integer.class),

    /**
     * If delete_inactive_projects is true, sets the time (in months) to wait before emailing
     * maintainers that the project is scheduled be deleted because it is inactive. Default is 1.
     * Became operational in GitLab 15.0.
     */
    INACTIVE_PROJECTS_SEND_WARNING_EMAIL_AFTER_MONTHS(Integer.class),

    /**
     * Whether or not optional metrics are enabled in Service Ping. Introduced in GitLab 16.10.
     */
    INCLUDE_OPTIONAL_METRICS_IN_SERVICE_PING(Boolean.class),

    /**
     * Enable Invisible CAPTCHA spam detection during sign-up. Disabled by default.
     */
    INVISIBLE_CAPTCHA_ENABLED(Boolean.class),

    /**
     * ID of the OAuth application used to authenticate with the GitLab for Jira Cloud app.
     */
    JIRA_CONNECT_APPLICATION_KEY(String.class),

    /**
     * Enable public key storage for the GitLab for Jira Cloud app.
     */
    JIRA_CONNECT_PUBLIC_KEY_STORAGE_ENABLED(Boolean.class),

    /**
     * URL of the GitLab instance used as a proxy for the GitLab for Jira Cloud app.
     */
    JIRA_CONNECT_PROXY_URL(String.class),

    /**
     * Maximum decompressed file size for imported archives in MB. Set to 0 for unlimited.
     * Default is 25600.
     */
    MAX_DECOMPRESSED_ARCHIVE_SIZE(Integer.class),

    /**
     * Maximum export size in MB. 0 for unlimited. Default = 0 (unlimited).
     */
    MAX_EXPORT_SIZE(Integer.class),

    /**
     * Maximum remote file size for imports from external object storages.
     * Introduced in GitLab 16.3.
     */
    MAX_IMPORT_REMOTE_FILE_SIZE(Integer.class),

    /**
     * Maximum number of sign-in attempts before locking out the user.
     */
    MAX_LOGIN_ATTEMPTS(Integer.class),

    /**
     * Maximum size in bytes of the Terraform state files. Set this to 0 for unlimited file size.
     */
    MAX_TERRAFORM_STATE_SIZE_BYTES(Integer.class),

    /**
     * 	The maximum size in bytes of a single CI/CD configuration file. Default: 2097152.
     */
    MAX_YAML_SIZE_BYTES(Integer.class),

    /**
     * The maximum depth of nested CI/CD configuration added with the include keyword. Default: 100.
     */
    MAX_YAML_DEPTH(Integer.class),

    /**
     * personal_access_token_prefix
     */
    PERSONAL_ACCESS_TOKEN_PREFIX(String.class),

    /**
     * (If enabled, requires: kroki_url) Enable Kroki integration. Default is false.
     */
    KROKI_ENABLED(Boolean.class),

    /**
     * The Kroki instance URL for integration.
     */
    KROKI_URL(String.class),

    /**
     * 	Additional formats supported by the Kroki instance. Possible values are true or false for
     * 	formats bpmn, blockdiag, and excalidraw in the format &lt;format$gt;: true or &lt;format&gt;: false.
     */
    KROKI_FORMATS(HashMap.class),

    /**
     * 	(If enabled, requires diagramsnet_url) Enable Diagrams.net integration. Default is true.
     */
    DIAGRAMSNET_ENABLED(Boolean.class),

    /**
     * The Diagrams.net instance URL for integration.
     */
    DIAGRAMSNET_URL(String.class),

    /**
     * When enabled, any user that signs up for an account using the registration form is placed
     * under a Pending approval state and has to be explicitly approved by an administrator.
     */
    REQUIRE_ADMIN_APPROVAL_AFTER_USER_SIGNUP(Boolean.class),

    /**
     * Allow administrators to require 2FA for all administrators on the instance.
     */
    REQUIRE_ADMIN_TWO_FACTOR_AUTHENTICATION(Boolean.class),

    /**
     * Enable Remember me setting. Introduced in GitLab 16.0.
     */
    REMEMBER_ME_ENABLED(Boolean.class),

    /**
     * 	Enable Silent mode. Default is false.
     */
    SILENT_MODE_ENABLED(Boolean.class),

    /**
     * The signing secret of the GitLab for Slack app. Used for authenticating API requests from
     * the app.
     */
    SLACK_APP_SIGNING_SECRET(String.class),

    /**
     * API key used by GitLab for accessing the Spam Check service endpoint.
     */
    SPAM_CHECK_API_KEY(String.class),

    /**
     * (If enabled, requires: throttle_authenticated_packages_api_period_in_seconds and
     * throttle_authenticated_packages_api_requests_per_period) Enable authenticated API request
     * rate limit. Helps reduce request volume (for example, from crawlers or abusive bots).
     * View Package Registry rate limits for more details.
     */
    THROTTLE_AUTHENTICATED_PACKAGES_API_ENABLED(Boolean.class),

    /**
     * Rate limit period (in seconds). View Package Registry rate limits for more details.
     */
    THROTTLE_AUTHENTICATED_PACKAGES_API_PERIOD_IN_SECONDS(Integer.class),

    /**
     * Maximum requests per period per user. View Package Registry rate limits for more details.
     */
    THROTTLE_AUTHENTICATED_PACKAGES_API_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * (If enabled, requires: throttle_unauthenticated_api_period_in_seconds and
     * throttle_unauthenticated_api_requests_per_period) Enable unauthenticated API request rate
     * limit. Helps reduce request volume (for example, from crawlers or abusive bots).
     */
    THROTTLE_UNAUTHENTICATED_API_ENABLED(Boolean.class),

    /**
     * Rate limit period in seconds.
     */
    THROTTLE_UNAUTHENTICATED_API_PERIOD_IN_SECONDS(Integer.class),

    /**
     * Max requests per period per IP.
     */
    THROTTLE_UNAUTHENTICATED_API_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * (If enabled, requires: throttle_unauthenticated_packages_api_period_in_seconds and
     * throttle_unauthenticated_packages_api_requests_per_period) Enable authenticated API request
     * rate limit. Helps reduce request volume (for example, from crawlers or abusive bots). View
     * Package Registry rate limits for more details.
     */
    THROTTLE_UNAUTHENTICATED_PACKAGES_API_ENABLED(Boolean.class),

    /**
     * Rate limit period (in seconds). View Package Registry rate limits for more details.
     */
    THROTTLE_UNAUTHENTICATED_PACKAGES_API_PERIOD_IN_SECONDS(Integer.class),

    /**
     * Maximum requests per period per user. View Package Registry rate limits for more details.
     */
    THROTTLE_UNAUTHENTICATED_PACKAGES_API_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * 	Fetch GitLab Runner release version data from GitLab.com. For more information, see how to
     * 	determine which runners need to be upgraded.
     */
    UPDATE_RUNNER_VERSIONS_ENABLED(Boolean.class),

    /**
     * Maximum files in a diff.
     */
    DIFF_MAX_FILES(Integer.class),

    /**
     * Maximum lines in a diff.
     */
    DIFF_MAX_LINES(Integer.class),

    /**
     * The Mailgun HTTP webhook signing key for receiving events from webhook.
     */
    MAILGUN_SIGNING_KEY(String.class),

    /**
     * Enable Mailgun event receiver.
     */
    MAILGUN_EVENTS_ENABLED(Boolean.class),

    /**
     * The Snowplow collector for database events hostname.
     * (for example, db-snowplow.trx.gitlab.net)
     */
    SNOWPLOW_DATABASE_COLLECTOR_HOSTNAME(String.class),

    /**
     * Maximum wiki page content size in bytes. Default: 52428800 Bytes (50 MB). The minimum value
     * is 1024 bytes.
     */
    WIKI_PAGE_MAX_CONTENT_BYTES(Integer.class),

    /**
     * The maximum time, in seconds, that the cleanup process can take to delete a batch of tags
     * for cleanup policies.
     */
    CONTAINER_REGISTRY_DELETE_TAGS_SERVICE_TIMEOUT(Integer.class),

    /**
     * When rate limiting is enabled via the throttle_* settings, send this plain text response
     * when a rate limit is exceeded. ‘Retry later’ is sent if this is blank.
     */
    RATE_LIMITING_RESPONSE_TEXT(String.class),

    /**
     * Enable to allow anyone to pull from Package Registry visible and changeable.
     */
    PACKAGE_REGISTRY_ALLOW_ANYONE_TO_PULL_OPTION(Boolean.class),

    /**
     * Number of workers assigned to the packages cleanup policies.
     */
    PACKAGE_REGISTRY_CLEANUP_POLICIES_WORKER_CAPACITY(Integer.class),

    /**
     * Number of workers for cleanup policies.
     */
    CONTAINER_REGISTRY_EXPIRATION_POLICIES_WORKER_CAPACITY(Integer.class),

    /**
     * The maximum number of tags that can be deleted in a single execution of cleanup policies.
     */
    CONTAINER_REGISTRY_CLEANUP_TAGS_SERVICE_MAX_LIST_SIZE(Integer.class),

    /**
     * Prevent the deletion of the artifacts from the most recent successful jobs, regardless of
     * the expiry time. Enabled by default.
     */
    KEEP_LATEST_ARTIFACT(Boolean.class),

    /**
     * What’s new variant, possible values: all_tiers, current_tier, and disabled.
     */
    WHATS_NEW_VARIANT(String.class),

    /**
     * Send an email to users upon account deactivation.
     */
    USER_DEACTIVATION_EMAILS_ENABLED(Boolean.class),

    /**
     * track or compress. Sets the behavior for Sidekiq job size limits. Default: ‘compress’.
     */
    SIDEKIQ_JOB_LIMITER_MODE(String.class),

    /**
     * The threshold in bytes at which Sidekiq jobs are compressed before being stored in Redis.
     * Default: 100,000 bytes (100 KB).
     */
    SIDEKIQ_JOB_LIMITER_COMPRESSION_THRESHOLD_BYTES(Integer.class),

    /**
     * The threshold in bytes at which Sidekiq jobs are rejected. Default: 0 bytes (doesn’t reject any job).
     */
    SIDEKIQ_JOB_LIMITER_LIMIT_BYTES(Integer.class),

    /**
     * Enable pipeline suggestion banner.
     */
    SUGGEST_PIPELINE_ENABLED(Boolean.class),

    /**
     * Show the external redirect page that warns you about user-generated content in GitLab Pages.
     */
    ENABLE_ARTIFACT_EXTERNAL_REDIRECT_WARNING_PAGE(Boolean.class),

    /**
     * Max number of requests per minute for performing a search while authenticated. Default: 30.
     * To disable throttling set to 0.
     */
    SEARCH_RATE_LIMIT(Integer.class),

    /**
     * Max number of requests per minute for performing a search while unauthenticated.
     * Default: 10. To disable throttling set to 0.
     */
    SEARCH_RATE_LIMIT_UNAUTHENTICATED(Integer.class),

    /**
     *Set the expiration time (in seconds) of authentication tokens of newly registered instance
     * runners. Minimum value is 7200 seconds. For more information, see Automatically rotate
     * authentication tokens.
     */
    RUNNER_TOKEN_EXPIRATION_INTERVAL(Integer.class),

    /**
     * Set the expiration time (in seconds) of authentication tokens of newly registered group
     * runners. Minimum value is 7200 seconds. For more information, see Automatically rotate
     * authentication tokens.
     */
    GROUP_RUNNER_TOKEN_EXPIRATION_INTERVAL(Integer.class),

    /**
     * Set the expiration time (in seconds) of authentication tokens of newly registered project
     * runners. Minimum value is 7200 seconds. For more information, see Automatically rotate
     * authentication tokens.
     */
    PROJECT_RUNNER_TOKEN_EXPIRATION_INTERVAL(Integer.class),

    /**
     * Maximum number of pipeline creation requests per minute per user and commit.
     * Disabled by default.
     */
    PIPELINE_LIMIT_PER_PROJECT_USER_SHA(Integer.class),

    /**
     * Indicates whether users can create top-level groups. Introduced in GitLab 15.5.
     * Defaults to true.
     */
    CAN_CREATE_GROUP(Boolean.class),

    /**
     * Maximum simultaneous Direct Transfer batches to process.
     */
    BULK_IMPORT_CONCURRENT_PIPELINE_BATCH_LIMIT(Integer.class),

    /**
     * Enable migrating GitLab groups by direct transfer. Introduced in GitLab 15.8. Setting also
     * available in the Admin area.
     */
    BULK_IMPORT_ENABLED(Boolean.class),

    /**
     * Maximum download file size when importing from source GitLab instances by direct transfer.
     * Introduced in GitLab 16.3.
     */
    BULK_IMPORT_MAX_DOWNLOAD_FILE_SIZE(Integer.class),

    /**
     * Enable Silent admin exports. Default is false.
     */
    SILENT_ADMIN_EXPORTS_ENABLED(Boolean.class),

    /**
     * Newly created users have private profile by default. Introduced in GitLab 15.8.
     * Defaults to false.
     */
    USER_DEFAULTS_TO_PRIVATE_PROFILE(Boolean.class),

    /**
     * Introduced in GitLab 15.10. Max number of requests per 10 minutes per IP address for
     * unauthenticated requests to the list all projects API. Default: 400. To disable throttling
     * set to 0.
     */
    PROJECTS_API_RATE_LIMIT_UNAUTHENTICATED(Integer.class),

    /**
     * Indicates whether the instance was provisioned for GitLab Dedicated.
     */
    GITLAB_DEDICATED_INSTANCE(Boolean.class),

    /**
     * Indicates whether the instance was provisioned with the GitLab Environment Toolkit for
     * Service Ping reporting.
     */
    GITLAB_ENVIRONMENT_TOOLKIT_INSTANCE(Boolean.class),

    /**
     * The maximum number of includes per pipeline. Default is 150.
     */
    CI_MAX_INCLUDES(Integer.class),

    /**
     * Enable users to delete their accounts.
     */
    ALLOW_ACCOUNT_DELETION(Boolean.class),

    /**
     * Maximum number of Git operations per minute a user can perform. Default: 600.
     * Introduced in GitLab 16.2.
     */
    GITLAB_SHELL_OPERATION_LIMIT(Integer.class),

    /**
     * The maximum amount of memory, in bytes, that can be allocated for the pipeline configuration,
     * with all included YAML configuration files.
     */
    CI_MAX_TOTAL_YAML_SIZE_BYTES(Integer.class),

    /**
     * Public security contact information. Introduced in GitLab 16.7.
     */
    SECURITY_TXT_CONTENT(String.class),

    /**
     * Maximum downstream pipeline trigger rate. Default: 0 (no restriction).
     * Introduced in GitLab 16.10.
     */
    DOWNSTREAM_PIPELINE_TRIGGER_LIMIT_PER_PROJECT_USER_SHA(Integer.class),

    /**
     * Maximum limit of AsciiDoc include directives being processed in any one document.
     * Default: 32. Maximum: 64.
     */
    ASCIIDOC_MAX_INCLUDES(Integer.class),

    /**
     * When enabled, users must set an expiration date when creating a group or project access
     * token, or a personal access token owned by a non-service account.
     */
    REQUIRE_PERSONAL_ACCESS_TOKEN_EXPIRY(Boolean.class),

    /**
     * Enable automatic deactivation of dormant users.
     */
    DEACTIVATE_DORMANT_USERS(Boolean.class),

    /**
     * Length of time (in days) after which a user is considered dormant. Introduced in GitLab 15.3.
     */
    DEACTIVATE_DORMANT_USERS_PERIOD(Integer.class),

    /**
     * Indicates whether to skip metadata URL validation for the NuGet package.
     * Introduced in GitLab 17.0.
     */
    NUGET_SKIP_METADATA_URL_VALIDATION(Boolean.class),

    /**
     * Enabling this permits automatic allocation of purchased storage in a namespace.
     * Relevant only to EE distributions.
     */
    AUTOMATIC_PURCHASED_STORAGE_ALLOCATION(Boolean.class),

    /**
     * Maximum size of text fields to index by Elasticsearch. 0 value means no limit.
     * This does not apply to repository and wiki indexing. Premium and Ultimate only.
     */
    ELASTICSEARCH_INDEXED_FIELD_LENGTH_LIMIT(Integer.class),

    /**
     * Maximum size of repository and wiki files that are indexed by Elasticsearch.
     * Premium and Ultimate only.
     */
    ELASTICSEARCH_INDEXED_FILE_SIZE_LIMIT_KB(Integer.class),

    /**
     * Enable automatic requeuing of indexing workers. This improves non-code indexing throughput
     * by enqueuing Sidekiq jobs until all documents are processed. Premium and Ultimate only.
     */
    ELASTICSEARCH_REQUEUE_WORKERS(Boolean.class),

    /**
     * Number of indexing worker shards. This improves non-code indexing throughput by enqueuing
     * more parallel Sidekiq jobs. Default is 2. Premium and Ultimate only.
     */
    ELASTICSEARCH_WORKER_NUMBER_OF_SHARDS(Integer.class),

    /**
     * Maximum concurrency of Elasticsearch bulk requests per indexing operation. This only applies
     * to repository indexing operations. Premium and Ultimate only.
     */
    ELASTICSEARCH_MAX_BULK_CONCURRENCY(Integer.class),

    /**
     * Maximum size of Elasticsearch bulk indexing requests in MB. This only applies to repository
     * indexing operations. Premium and Ultimate only.
     */
    ELASTICSEARCH_MAX_BULK_SIZE_MB(Integer.class),

    /**
     * Maximum concurrency of Elasticsearch code indexing background jobs. This only applies to
     * repository indexing operations. Premium and Ultimate only.
     */
    ELASTICSEARCH_MAX_CODE_INDEXING_CONCURRENCY(Integer.class),

    /**
     * The username of your Elasticsearch instance. Premium and Ultimate only.
     */
    ELASTICSEARCH_USERNAME(String.class),

    /**
     * The password of your Elasticsearch instance. Premium and Ultimate only.
     */
    ELASTICSEARCH_PASSWORD(String.class),

    /**
     * Enabling this permits enforcement of namespace storage limits.
     */
    ENFORCE_NAMESPACE_STORAGE_LIMIT(Boolean.class),

    /**
     * Maximum allowable lifetime for access tokens in days. When left blank, default value of 365
     * is applied. When set, value must be 365 or less. When changed, existing access tokens with
     * an expiration date beyond the maximum allowable lifetime are revoked.
     * Self-managed, Ultimate only.
     */
    MAX_PERSONAL_ACCESS_TOKEN_LIFETIME(Integer.class),

    /**
     * Maximum allowable lifetime for SSH keys in days. Self-managed, Ultimate only.
     */
    MAX_SSH_KEY_LIFETIME(Integer.class),

    /**
     * List of package registry metadata to sync. See the list of the available values.
     * Self-managed, Ultimate only.
     */
    PACKAGE_METADATA_PURL_TYPES(Integer[].class),

    /**
     * Whether to look up merge request approval policy approval groups globally or within project
     * hierarchies.
     */
    SECURITY_POLICY_GLOBAL_GROUP_APPROVERS_ENABLED(Boolean.class),

    /**
     * Maximum number of active merge request approval policies per security policy project.
     * Default: 5. Maximum: 20
     */
    SECURITY_APPROVAL_POLICIES_LIMIT(Integer.class),

    /**
     * Enables ClickHouse as a data source for analytics reports. ClickHouse must be configured
     * for this setting to take effect. Available on Premium and Ultimate only.
     */
    USE_CLICKHOUSE_FOR_ANALYTICS(Boolean.class),

    /**
     * Indicates whether GitLab Duo features are enabled for this instance. Introduced in GitLab
     * 16.10. Self-managed, Premium and Ultimate only.
     */
    DUO_FEATURES_ENABLED(Boolean.class),

    /**
     * Indicates whether the GitLab Duo features enabled setting is enforced for all subgroups.
     * Introduced in GitLab 16.10. Self-managed, Premium and Ultimate only.
     */
    LOCK_DUO_FEATURES_ENABLED(Boolean.class),

    /**
     * List of types which are allowed to register a GitLab Runner. Can be [], ['group'],
     * ['project'] or ['group', 'project'].
     */
    VALID_RUNNER_REGISTRARS(String[].class),

    /**
     * (If enabled, requires: throttle_unauthenticated_web_period_in_seconds and
     * throttle_unauthenticated_web_requests_per_period) Enable unauthenticated web request rate
     * limit. Helps reduce request volume (for example, from crawlers or abusive bots).
     */
    THROTTLE_UNAUTHENTICATED_WEB_ENABLED(Boolean.class),

    /**
     * Rate limit period in seconds.
     */
    THROTTLE_UNAUTHENTICATED_WEB_PERIOD_IN_SECONDS(Integer.class),

    /**
     * Max requests per period per IP.
     */
    THROTTLE_UNAUTHENTICATED_WEB_REQUESTS_PER_PERIOD(Integer.class),

    /**
     * Hash of names of taken from gitlab.yml to weights. New projects are created in one of
     * these stores, chosen by a weighted random selection.
     */
    REPOSITORY_STORAGES_WEIGHTED(HashMap.class),

    /**
     * Prevent editing approval rules in projects and merge requests.
     */
    DISABLE_OVERRIDING_APPROVERS_PER_MERGE_REQUEST(Boolean.class),

    /**
     * Prevent approval by author
     */
    PREVENT_MERGE_REQUESTS_AUTHOR_APPROVAL(Boolean.class),

    /**
     * Prevent approval by committers to merge requests
     */
    PREVENT_MERGE_REQUESTS_COMMITTERS_APPROVAL(Boolean.class),

    /**
     * Indicates whether passwords require at least one number. Introduced in GitLab 15.1.
     * Premium and Ultimate only.
     */
    PASSWORD_NUMBER_REQUIRED(Boolean.class),

    /**
     * Indicates whether passwords require at least one symbol character. Introduced in GitLab 15.1.
     * Premium and Ultimate only.
     */
    PASSWORD_SYMBOL_REQUIRED(Boolean.class),

    /**
     * Indicates whether passwords require at least one uppercase letter. Introduced in GitLab 15.1.
     * Premium and Ultimate only.
     */
    PASSWORD_UPPERCASE_REQUIRED(Boolean.class),

    /**
     * Indicates whether passwords require at least one lowercase letter. Introduced in GitLab 15.1.
     * Premium and Ultimate only.
     */
    PASSWORD_LOWERCASE_REQUIRED(Boolean.class),

    /**
     * Enable default project deletion protection so only administrators can delete projects.
     * Default is false. Self-managed, Premium and Ultimate only.
     */
    DEFAULT_PROJECT_DELETION_PROTECTION(Boolean.class),

    /**
     * Number of days to wait before deleting a project or group that is marked for deletion.
     * Value must be between 1 and 90. Defaults to 7. Self-managed, Premium and Ultimate only.
     */
    DELETION_ADJOURNED_PERIOD(Integer.class),

    /**
     * Disable personal access tokens. Introduced in GitLab 15.7. Self-managed, Premium and
     * Ultimate only. There is no method available to enable a personal access token that’s been
     * disabled through the API. This is a known issue. For more information about available
     * workarounds, see Workaround.
     */
    DISABLE_PERSONAL_ACCESS_TOKENS(Boolean.class),

    /**
     * Disable user profile name changes.
     */
    UPDATING_NAME_DISABLED_FOR_USERS(Boolean.class),

    /**
     * Use repo.maven.apache.org as a default remote repository when the package is not found in
     * the GitLab Package Registry for Maven. Premium and Ultimate only.
     */
    MAVEN_PACKAGE_REQUESTS_FORWARDING(Boolean.class),

    /**
     * Use npmjs.org as a default remote repository when the package is not found in the GitLab
     * Package Registry for npm. Premium and Ultimate only.
     */
    NPM_PACKAGE_REQUESTS_FORWARDING(Boolean.class),

    /**
     * Use pypi.org as a default remote repository when the package is not found in the GitLab
     * Package Registry for PyPI. Premium and Ultimate only.
     */
    PYPI_PACKAGE_REQUESTS_FORWARDING(Boolean.class),

    /**
     * Prevent overrides of default branch protection. Self-managed, Premium and Ultimate only.
     */
    GROUP_OWNERS_CAN_MANAGE_DEFAULT_BRANCH_PROTECTION(Boolean.class),

    /**
     * When instance is in maintenance mode, non-administrative users can sign in with read-only
     * access and make read-only API requests. Premium and Ultimate only.
     */
    MAINTENANCE_MODE(Boolean.class),

    /**
     * 	Message displayed when instance is in maintenance mode. Premium and Ultimate only.
     */
    MAINTENANCE_MODE_MESSAGE(String.class),

    /**
     * Flag to indicate if token expiry date can be optional for service account users
     */
    SERVICE_ACCESS_TOKENS_EXPIRATION_ENFORCED(Boolean.class),

    /**
     * Specifies whether users who have not confirmed their email should be deleted. Default is
     * false. When set to true, unconfirmed users are deleted after
     * unconfirmed_users_delete_after_days days. Introduced in GitLab 16.1.
     * Self-managed, Premium and Ultimate only.
     */
    DELETE_UNCONFIRMED_USERS(Boolean.class),

    /**
     * Specifies how many days after sign-up to delete users who have not confirmed their email.
     * Only applicable if delete_unconfirmed_users is set to true. Must be 1 or greater.
     * Default is 7. Introduced in GitLab 16.1. Self-managed, Premium and Ultimate only.
     */
    UNCONFIRMED_USERS_DELETE_AFTER_DAYS(Integer.class),

    /*
     * Undocumented settings as of GitLab 12.4
     * These are reported but not documented.
     */
    CUSTOM_HTTP_CLONE_URL_ROOT(String.class),
    PROTECTED_PATHS_RAW(String.class),
    THROTTLE_PROTECTED_PATHS_ENABLED(Boolean.class),
    THROTTLE_PROTECTED_PATHS_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_PROTECTED_PATHS_REQUESTS_PER_PERIOD(Integer.class),

    /*
     * Undocumented settings as of GitLab 12.8
     * These are reported but not documented.
     */
    FORCE_PAGES_ACCESS_CONTROL(Boolean.class),
    MINIMUM_PASSWORD_LENGTH(Integer.class),
    SNIPPET_SIZE_LIMIT(Integer.class),

    /*
     * Undocumented settings as of GitLab 12.9
     * These are reported but not documented.
     */
    EMAIL_RESTRICTIONS_ENABLED(Boolean.class),
    EMAIL_RESTRICTIONS(String.class),

    /*
     * Undocumented settings as of GitLab 13.0
     * These are reported but not documented.
     */
    CONTAINER_EXPIRATION_POLICIES_ENABLE_HISTORIC_ENTRIES(Boolean.class),
    ISSUES_CREATE_LIMIT(Integer.class),
    RAW_BLOB_REQUEST_LIMIT(Integer.class),

    /*
     * Undocumented settings as of GitLab 17.3
     * These are reported but not documented.
     */
    ALLOW_POSSIBLE_SPAM(Boolean.class),
    DENY_ALL_REQUESTS_EXCEPT_ALLOWED(Boolean.class),
    DOMAIN_DENYLIST_RAW(String.class),
    DOMAIN_ALLOWLIST_RAW(String.class),
    OUTBOUND_LOCAL_REQUESTS_ALLOWLIST_RAW(String.class),
    ERROR_TRACKING_ENABLED(Boolean.class),
    ERROR_TRACKING_API_URL(String.class),
    FLOC_ENABLED(Boolean.class),
    HELP_PAGE_DOCUMENTATION_BASE_URL(String.class),
    MATH_RENDERING_LIMITS_ENABLED(Boolean.class),
    MAX_ARTIFACTS_CONTENT_INCLUDE_SIZE(Integer.class),
    MAX_PAGES_CUSTOM_DOMAINS_PER_PROJECT(Integer.class),
    THROTTLE_AUTHENTICATED_GIT_LFS_ENABLED(Boolean.class),
    THROTTLE_AUTHENTICATED_GIT_LFS_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_AUTHENTICATED_GIT_LFS_REQUESTS_PER_PERIOD(Integer.class),
    THROTTLE_AUTHENTICATED_FILES_API_ENABLED(Boolean.class),
    THROTTLE_AUTHENTICATED_FILES_API_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_AUTHENTICATED_FILES_API_REQUESTS_PER_PERIOD(Integer.class),
    THROTTLE_AUTHENTICATED_DEPRECATED_API_ENABLED(Boolean.class),
    THROTTLE_AUTHENTICATED_DEPRECATED_API_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_AUTHENTICATED_DEPRECATED_API_REQUESTS_PER_PERIOD(Integer.class),
    THROTTLE_UNAUTHENTICATED_FILES_API_ENABLED(Boolean.class),
    THROTTLE_UNAUTHENTICATED_FILES_API_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_UNAUTHENTICATED_FILES_API_REQUESTS_PER_PERIOD(Integer.class),
    THROTTLE_UNAUTHENTICATED_GIT_HTTP_ENABLED(Boolean.class),
    THROTTLE_UNAUTHENTICATED_GIT_HTTP_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_UNAUTHENTICATED_GIT_HTTP_REQUESTS_PER_PERIOD(Integer.class),
    THROTTLE_UNAUTHENTICATED_DEPRECATED_API_ENABLED(Boolean.class),
    THROTTLE_UNAUTHENTICATED_DEPRECATED_API_PERIOD_IN_SECONDS(Integer.class),
    THROTTLE_UNAUTHENTICATED_DEPRECATED_API_REQUESTS_PER_PERIOD(Integer.class),
    PROTECTED_PATHS_FOR_GET_REQUEST_RAW(String.class),
    USAGE_PING_FEATURES_ENABLED(Boolean.class),
    NOTES_CREATE_LIMIT(Integer.class),
    NOTES_CREATE_LIMIT_ALLOWLIST_RAW(String.class),
    MEMBERS_DELETE_LIMIT(Integer.class),
    PROJECT_IMPORT_LIMIT(Integer.class),
    PROJECT_EXPORT_LIMIT(Integer.class),
    PROJECT_DOWNLOAD_EXPORT_LIMIT(Integer.class),
    GROUP_IMPORT_LIMIT(Integer.class),
    GROUP_EXPORT_LIMIT(Integer.class),
    GROUP_DOWNLOAD_EXPORT_LIMIT(Integer.class),
    WIKI_ASCIIDOC_ALLOW_URI_INCLUDES(Boolean.class),
    SENTRY_ENABLED(Boolean.class),
    SENTRY_DSN(String.class),
    SENTRY_CLIENTSIDE_DSN(String.class),
    SENTRY_ENVIRONMENT(String.class),
    SENTRY_CLIENTSIDE_TRACES_SAMPLE_RATE(Float.class),
    SEARCH_RATE_LIMIT_ALLOWLIST_RAW(String.class),
    USERS_GET_BY_ID_LIMIT(Integer.class),
    USERS_GET_BY_ID_LIMIT_ALLOWLIST_RAW(String.class),
    INVITATION_FLOW_ENFORCEMENT(Boolean.class),
    DEACTIVATION_EMAIL_ADDITIONAL_TEXT(String.class),
    GROUP_API_LIMIT(Integer.class),
    GROUP_INVITED_GROUPS_API_LIMIT(Integer.class),
    GROUP_SHARED_GROUPS_API_LIMIT(Integer.class),
    GROUP_PROJECTS_API_LIMIT(Integer.class),
    GROUPS_API_LIMIT(Integer.class),
    PROJECT_API_LIMIT(Integer.class),
    PROJECTS_API_LIMIT(Integer.class),
    USER_CONTRIBUTED_PROJECTS_API_LIMIT(Integer.class),
    USER_PROJECTS_API_LIMIT(Integer.class),
    USER_STARRED_PROJECTS_API_LIMIT(Integer.class),
    NAMESPACE_AGGREGATION_SCHEDULE_LEASE_DURATION_IN_SECONDS(Integer.class),
    AI_ACTION_API_RATE_LIMIT(Integer.class),
    CODE_SUGGESTIONS_API_RATE_LIMIT(Integer.class),
    ELASTICSEARCH_CLIENT_REQUEST_TIMEOUT(Integer.class),
    ELASTICSEARCH_PAUSE_INDEXING(Boolean.class),
    ELASTICSEARCH_REPLICAS(Integer.class),
    ELASTICSEARCH_SHARDS(Integer.class),
    ELASTICSEARCH_ANALYZERS_SMARTCN_ENABLED(Boolean.class),
    ELASTICSEARCH_ANALYZERS_SMARTCN_SEARCH(Boolean.class),
    ELASTICSEARCH_ANALYZERS_KUROMOJI_ENABLED(Boolean.class),
    ELASTICSEARCH_ANALYZERS_KUROMOJI_SEARCH(Boolean.class),
    INSTANCE_LEVEL_AI_BETA_FEATURES_ENABLED(Boolean.class),
    LOCK_MEMBERSHIPS_TO_LDAP(Boolean.class),
    LOCK_MEMBERSHIPS_TO_SAML(Boolean.class),
    SEARCH_MAX_SHARD_SIZE_GB(Integer.class),
    SEARCH_MAX_DOCS_DENOMINATOR(Integer.class),
    SEARCH_MIN_DOCS_BEFORE_ROLLOVER(Integer.class),
    SECRET_DETECTION_TOKEN_REVOCATION_ENABLED(Boolean.class),
    SECRET_DETECTION_TOKEN_REVOCATION_URL(String.class),
    SECRET_DETECTION_TOKEN_REVOCATION_TOKEN(String.class),
    SECRET_DETECTION_REVOCATION_TOKEN_TYPES_URL(String.class),
    SECURITY_POLICY_SCHEDULED_SCANS_MAX_CONCURRENCY(Integer.class),
    THROTTLE_INCIDENT_MANAGEMENT_NOTIFICATION_ENABLED(Boolean.class),
    THROTTLE_INCIDENT_MANAGEMENT_NOTIFICATION_PER_PERIOD(Integer.class),
    THROTTLE_INCIDENT_MANAGEMENT_NOTIFICATION_PERIOD_IN_SECONDS(Integer.class),
    PRODUCT_ANALYTICS_ENABLED(Boolean.class),
    PRODUCT_ANALYTICS_DATA_COLLECTOR_HOST(String.class),
    PRODUCT_ANALYTICS_CONFIGURATOR_CONNECTION_STRING(String.class),
    CUBE_API_BASE_URL(String.class),
    CUBE_API_KEY(String.class),
    DUO_AVAILABILITY(String.class),
    ZOEKT_AUTO_INDEX_ROOT_NAMESPACE(Boolean.class),
    ZOEKT_INDEXING_ENABLED(Boolean.class),
    ZOEKT_INDEXING_PAUSED(Boolean.class),
    ZOEKT_SEARCH_ENABLED(Boolean.class),
    DUO_WORKFLOW_OAUTH_APPLICATION_ID(String.class),
    ALLOW_DEPLOY_TOKENS_AND_KEYS_WITH_EXTERNAL_AUTHN(Boolean.class),
    CONTAINER_REGISTRY_IMPORT_MAX_TAGS_COUNT(Integer.class),
    CONTAINER_REGISTRY_IMPORT_MAX_RETRIES(Integer.class),
    CONTAINER_REGISTRY_IMPORT_START_MAX_RETRIES(Integer.class),
    CONTAINER_REGISTRY_IMPORT_MAX_STEP_DURATION(Integer.class),
    CONTAINER_REGISTRY_PRE_IMPORT_TAGS_RATE(Integer.class),
    CONTAINER_REGISTRY_PRE_IMPORT_TIMEOUT(Integer.class),
    CONTAINER_REGISTRY_IMPORT_TIMEOUT(Integer.class),
    CONTAINER_REGISTRY_IMPORT_TARGET_PLAN(String.class),
    CONTAINER_REGISTRY_IMPORT_CREATED_BEFORE(String.class),
    ;

    private static JacksonJsonEnumHelper<Setting> enumHelper = new JacksonJsonEnumHelper<>(Setting.class);

    private Class<?> type;
    private Class<?>[] types;
    private Setting(Class<?> type) {
        this.type = type;
    }

    private Setting(Class<?>[] types) {
        this.types = types;
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
    public final boolean isValid(Object value) {

	if (value == null) {
	    return (true);
	}

	Class<?> valueType = value.getClass();
	if (type != null) {
	    return (valueType == type);
	}

	for (Class<?> type : types) {
	    if (valueType == type) {
		return (true);
	    }
	}

	return (false);
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

        StringBuilder shouldBe;
        if (type != null) {
            shouldBe = new StringBuilder(type.getSimpleName());
        } else {
            shouldBe = new StringBuilder(types[0].getSimpleName());
            for (int i = 1; i < types.length; i++) {
                shouldBe.append(" | ").append(types[i].getSimpleName());
            }
        }

        String errorMsg = String.format("'%s' value is of incorrect type, is %s, should be %s",
                toValue(), value.getClass().getSimpleName(), shouldBe.toString());
        throw new GitLabApiException(errorMsg);
    }

    public Object emptyArrayValue() {
        if (type != null) {
            if (type.isArray()) {
                return Array.newInstance(type.getComponentType(), 0);
            }
        } else {
            for(Class<?> possibleType: types) {
                if (possibleType.isArray()) {
                    return Array.newInstance(possibleType.getComponentType(), 0);
                }
            }
        }
        return null;
    }
}
