package org.gitlab4j.api;

import java.io.ByteArrayInputStream;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Link.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * This class can be used as a concrete mock to test the individual APIs
 * getXxxxx() methods without the need to have a GitLab server available.
 * 
 * Supports getXxxxx() methods that return a List of items, single items,
 * Optional items, and Pagers of items.
 */
public class MockResponse extends Response {

    private List<?> responseList;
    private Object responseItem;
    private int perPage = 20;

    private String itemJson;
    private String listJson;

    private int status = 200;
    private Status statusInfo = Status.OK;
    private MediaType mediaType = MediaType.APPLICATION_JSON_TYPE;

    public MockResponse() {
    }

    public <T> MockResponse(Class<T> type, String itemFilename, String listFilename) throws Exception {
        init(type, itemFilename, listFilename);
    }

    public <T> MockResponse(Status statusInfo, String jsonString) {
        this.statusInfo = statusInfo;
        this.status = statusInfo.getStatusCode();
        this.itemJson = jsonString;
        this.responseItem = jsonString;
    }

    public <T> void init(Class<T> type, String itemFilename, String listFilename) throws Exception {

        if (itemFilename != null) {
            itemJson = JsonUtils.readResource(itemFilename);
            responseItem = JsonUtils.unmarshal(type, itemJson);
        } else {
            responseItem = null;
        }

        if (listFilename != null) {
            listJson = JsonUtils.readResource(listFilename);
            responseList = (List<?>) JsonUtils.unmarshalList(type, listJson);
        } else {
            responseList = null;
        }
    }

    public String getItemJson() {
        return (itemJson);
    }

    public String getListJson() {
        return (listJson);
    }

    public void setPerPageHeaderValue(int perPage) {
        this.perPage = perPage;
    }


    /******************************************************************************
     * The following methods allow this class to act as a fake Resource instance.
     ******************************************************************************/

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readEntity(GenericType<T> entityType) {
        return ((T) responseList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readEntity(Class<T> classType) {
        return ((T) responseItem);
    }

    @Override
    public Object getEntity() {
        return (listJson != null ? new ByteArrayInputStream(listJson.getBytes()) : null);
    }

    @Override
    public String getHeaderString(String name) {
        switch (name) {
        case Constants.PER_PAGE:
            return (responseList != null ? Integer.toString(perPage) : "0");

        case Constants.TOTAL_PAGES_HEADER:
            return (responseList != null ? Integer.toString((int) Math.ceil((double) responseList.size() / 20.0))
                    : "0");

        case Constants.TOTAL_HEADER:
            return (responseList != null ? Integer.toString(responseList.size()) : "0");

        default:
            return (null);
        }
    }

    @Override
    public boolean hasEntity() {
        return (itemJson != null || listJson != null);
    }

    @Override
    public int getStatus() {
        return (status);
    }

    @Override
    public StatusType getStatusInfo() {
        return (statusInfo);
    }

    @Override
    public MediaType getMediaType() {
        return (mediaType);
    }

    /**************************************************************************************************
     * The remaining methods are stubbed so we can create an instance of this class. They are simply
     * stubs, but needed to do this because the Mockito Spy annotation does not work without JAXB
     * on Java 11+ and did not wish to pull in the JAXB module even for testing. 
     **************************************************************************************************/

    @Override
    public <T> T readEntity(Class<T> entityType, Annotation[] annotations) {
        return null;
    }

    @Override
    public <T> T readEntity(GenericType<T> entityType, Annotation[] annotations) {
        return null;
    }

    @Override
    public boolean bufferEntity() {
        return false;
    }

    @Override
    public void close() {
    }

    @Override
    public Locale getLanguage() {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public Set<String> getAllowedMethods() {
        return null;
    }

    @Override
    public Map<String, NewCookie> getCookies() {
        return null;
    }

    @Override
    public EntityTag getEntityTag() {
        return null;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public Date getLastModified() {
        return null;
    }

    @Override
    public URI getLocation() {
        return null;
    }

    @Override
    public Set<Link> getLinks() {
        return null;
    }

    @Override
    public boolean hasLink(String relation) {
        return false;
    }

    @Override
    public Link getLink(String relation) {
        return null;
    }

    @Override
    public Builder getLinkBuilder(String relation) {
        return null;
    }

    @Override
    public MultivaluedMap<String, Object> getMetadata() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getStringHeaders() {
        return null;
    }
}