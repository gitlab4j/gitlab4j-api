package org.gitlab4j.api;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * This class can be used as a spy for Mockito to test the individual APIs getXxxxx() methods without the
 * need to have a GitLab server available.
 * 
 * Supports getXxxxx() methods that return a List of items, single items, and Optional items.
 */
public abstract class FakeResponse extends Response {

    private List<?> responseList;
    private ByteArrayInputStream responseInputStream;
    private Object responseItem;
    private int perPage = 20;

    public <T> void init(Class<T> type, String itemFilename, String listFilename) throws Exception {

        if (itemFilename != null) {
            InputStreamReader reader = new InputStreamReader(TestGitLabApiBeans.class.getResourceAsStream(itemFilename));
            responseItem = JsonUtils.unmarshal(type, reader);
        } else {
            responseItem = null;
        }

        if (listFilename != null) {
            String listJson = JsonUtils.readResource(listFilename);
            responseList = (List<?>) JsonUtils.unmarshalList(type, listJson);
            responseInputStream = new ByteArrayInputStream(listJson.getBytes());
        } else {
            responseList = null;
            responseInputStream = null;
        }
    }

    public void setPerPageHeaderValue(int perPage) {
        this.perPage = perPage;
    }

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
        if (responseInputStream == null) {
            return (null);
        }

        responseInputStream.reset();
        return (responseInputStream);
    }

    @Override
    public String getHeaderString(String name) {
        switch (name) {
        case Constants.PER_PAGE:
            return (responseList != null ? Integer.toString(perPage) : "0");

        case Constants.TOTAL_PAGES_HEADER:
            return (responseList != null ? Integer.toString((int)Math.ceil((double)responseList.size() / 20.0)) : "0");

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