package org.gitlab4j.api;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * This class can be used as a spy for Mockito to test the individual APIs
 * getXxxxx() methods without the need to have a GitLab server available.
 * 
 * Supports getXxxxx() methods that return a List of items, single items,
 * Optional items, and Pagers of items.
 */
public abstract class FakeResponse extends Response {

    private List<?> responseList;
    private Object responseItem;
    private int perPage = 20;

    private String itemJson;
    private String listJson;

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

    // The below methods allow this abstract class to act as a fake Resource
    // instance.

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
    public int getStatus() {
        return (200);
    }
}