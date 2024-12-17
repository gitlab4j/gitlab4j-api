package org.gitlab4j.api.models;

import java.io.Serializable;
import java.util.List;

import org.gitlab4j.models.Constants.ArchiveFormat;
import org.gitlab4j.models.utils.JacksonJson;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is part of the Release class model.
 */
public class Assets implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class Source implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * The format of the archive.
         */
        @JsonProperty("format")
        private ArchiveFormat format;

        /**
         * The URL of the source.
         */
        @JsonProperty("url")
        private String url;

        public ArchiveFormat getFormat() {
            return format;
        }

        public void setFormat(ArchiveFormat format) {
            this.format = format;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    public static class Link implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * The unique identifier of the link.
         */
        @JsonProperty("id")
        private Long id;

        /**
         * The name of the link.
         */
        @JsonProperty("name")
        private String name;

        /**
         * The URL associated with the link.
         */
        @JsonProperty("url")
        private String url;

        /**
         * Indicates if the link is external.
         */
        @JsonProperty("external")
        private Boolean external;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getExternal() {
            return external;
        }

        public void setExternal(Boolean external) {
            this.external = external;
        }

        @Override
        public String toString() {
            return (JacksonJson.toJsonString(this));
        }
    }

    /**
     * The count of assets.
     */
    @JsonProperty("count")
    private Integer count;

    /**
     * The list of sources associated with the assets.
     */
    @JsonProperty("sources")
    private List<Source> sources;

    /**
     * The list of links associated with the assets.
     */
    @JsonProperty("links")
    private List<Link> links;

    /**
     * The file path of the evidence associated with the assets.
     */
    @JsonProperty("evidence_file_path")
    private String evidenceFilePath;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getEvidenceFilePath() {
        return evidenceFilePath;
    }

    public void setEvidenceFilePath(String evidenceFilePath) {
        this.evidenceFilePath = evidenceFilePath;
    }

    @Override
    public String toString() {
        return (JacksonJson.toJsonString(this));
    }
}
