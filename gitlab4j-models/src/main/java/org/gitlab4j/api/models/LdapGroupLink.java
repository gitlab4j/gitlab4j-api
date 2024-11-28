package org.gitlab4j.api.models;

import java.io.Serializable;

import org.gitlab4j.models.utils.JacksonJson;

public class LdapGroupLink implements Serializable {
    private static final long serialVersionUID = 1L;

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
