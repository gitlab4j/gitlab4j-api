package org.gitlab4j.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.gitlab4j.api.utils.JacksonJson;

public class Metadata {

    private String version;
    private String revision;
    private Kas kas;
    private Boolean enterprise;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public Kas getKas() {
        return kas;
    }

    public void setKas(Kas kas) {
        this.kas = kas;
    }

    public Boolean getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Boolean enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }

    private static class Kas {

        private Boolean enabled;
        @JsonProperty("externalUrl")
        private String externalUrl;
        private String version;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getExternalUrl() {
            return externalUrl;
        }

        public void setExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }



}
