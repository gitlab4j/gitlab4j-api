
package org.gitlab4j.api.models;

import org.gitlab4j.api.utils.JacksonJson;

public class LdapGroupLink {

    // The GitLab REST API documentation (https://docs.gitlab.com/ee/api/groups.html#list-ldap-group-links)
    // does not specify the attributes which are returned for a LDAP group link :-/.
    // This are the attributes returned by our GitLab instance (15.10.2-ee).
    // These seem fine because they match with the attributes which you can pass when adding a LDAP group link :-).

    private String cn;

    private AccessLevel groupAccess;

    private String provider;

    private String filter;

    public String getCn() {
        return cn;
    }

    public void setCn(String aCn) {
        cn = aCn;
    }

    public AccessLevel getGroupAccess() {
       return groupAccess;
    }

    public void setGroupAccess(AccessLevel aGroupAccess) {
        groupAccess = aGroupAccess;
    }

    public String getProvider() {
        return provider;
     }

    public void setProvider(String aProvider) {
        provider = aProvider;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String aFilter) {
        filter = aFilter;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
