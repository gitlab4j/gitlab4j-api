package org.gitlab4j.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.gitlab4j.api.utils.JacksonJson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class defines an Iterator implementation that is used as a paging iterator for all API methods that 
 * return a List of objects.  It hides the details of interacting with the GitLab API when paging is involved
 * simplifying accessing large lists of objects.
 * 
 * <p>Example usage:</p>
 *  
 * <pre>
 *   // Get a Pager instance that will page through the projects with 10 projects per page
 *   Pager&lt;Project&gt; projectPager = gitlabApi.getProjectsApi().getProjectsPager(10);
 *
 *   // Iterate through the pages and print out the name and description
 *   while (projectsPager.hasNext())) {
 *       List&lt;Project&gt; projects = projectsPager.next();
 *       for (Project project : projects) {
 *           System.out.println(project.getName() + " -: " + project.getDescription());
 *       }
 *   }
 * </pre> 
 * 
 * @param <T> the GitLab4J type contained in the List.
 */
public class Pager<T> implements Iterator<List<T>>, Constants {

    private int itemsPerPage;
    private int totalPages;
    private int totalItems;
    private int currentPage;

    private List<String> pageParam = new ArrayList<>(1);
    private List<T> currentItems;

    private AbstractApi api;
    private MultivaluedMap<String, String> queryParams;
    private Object[] pathArgs;

    private static JacksonJson jacksonJson = new JacksonJson();
    private static ObjectMapper mapper = jacksonJson.getObjectMapper();
    private JavaType javaType;

    /**
     * Creates a Pager instance to access the API through the specified path and query parameters.
     * 
     * @param api the AbstractApi implementation to communicate through
     * @param type the GitLab4J type that will be contained in the List
     * @param itemsPerPage items per page
     * @param queryParams HTTP query params
     * @param pathArgs HTTP path arguments
     * @throws GitLabApiException if any error occurs
     */
    Pager(AbstractApi api, Class<T> type, int itemsPerPage, MultivaluedMap<String, String> queryParams, Object... pathArgs) throws GitLabApiException {

        javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);

        // Make sure the per_page parameter is present
        if (queryParams == null) {
            queryParams = new GitLabApiForm().withParam(PER_PAGE_PARAM, itemsPerPage).asMap();
        } else {
            queryParams.remove(PER_PAGE_PARAM);
            queryParams.add(PER_PAGE_PARAM, Integer.toString(itemsPerPage));
        }

        // Set the page param to 1
        pageParam = new ArrayList<>();
        pageParam.add("1");
        queryParams.put(PAGE_PARAM, pageParam);
        Response response = api.get(Response.Status.OK, queryParams, pathArgs);

        try {
            currentItems = mapper.readValue((InputStream) response.getEntity(), javaType);
        } catch (IOException e) {
            throw new GitLabApiException(e);
        }

        this.api = api;
        this.queryParams = queryParams;
        this.pathArgs = pathArgs;
        this.itemsPerPage = getHeaderValue(response, PER_PAGE);
        totalPages = getHeaderValue(response, TOTAL_PAGES_HEADER);
        totalItems = getHeaderValue(response, TOTAL_HEADER);
    }

    /**
     * Get the specified integer header value from the Response instance.
     * 
     * @param response the Response instance to get the value from
     * @param key the HTTP header key to get the value for
     * @return the specified integer header value from the Response instance
     * @throws GitLabApiException if any error occurs
     */
    private int getHeaderValue(Response response, String key) throws GitLabApiException {

        String value = response.getHeaderString(key);
        value = (value != null ? value.trim() : null);
        if (value == null || value.length() == 0)
            throw new GitLabApiException("Missing '" + key + "' header from server");

        try {
            return (Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            throw new GitLabApiException("Invalid '" + key + "' header value (" + value + ") from server");
        }
    }

    /**
     * Sets the "page" query parameter.
     * 
     * @param page the value for the "page" query parameter
     */
    private void setPageParam(int page) {
        pageParam.set(0, Integer.toString(page));
        queryParams.put(PAGE_PARAM, pageParam);
    }

    /**
     * Get the items per page value.
     *
     * @return the items per page value
     */
    public int getItemsPerPage() {
        return (itemsPerPage);
    }

    /**
     * Get the total number of pages returned by the GitLab API.
     *
     * @return the total number of pages returned by the GitLab API
     */
    public int getTotalPages() {
        return (totalPages);
    }

    /**
     * Get the total number of items (T instances) returned by the GitLab API.
     *
     * @return the total number of items (T instances) returned by the GitLab API
     */
    public int getTotalItems() {
        return (totalItems);
    }

    /**
     * Get the current page of the iteration.
     *
     * @return the current page of the iteration
     */
    public int getCurrentPage() {
        return (currentPage);
    }

    /**
     * Returns the true if there are additional pages to iterate over, otherwise returns false.
     *
     * @return true if there are additional pages to iterate over, otherwise returns false
     */
    @Override
    public boolean hasNext() {
        return (currentPage < totalPages);
    }

    /**
     * Returns the next List in the iteration containing the next page of objects.
     *
     * @return the next List in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     * @throws RuntimeException if a GitLab API error occurs, will contain a wrapped GitLabApiException with the details of the error
     */
    @Override
    public List<T> next() {
        return (page(currentPage + 1));
    }

    /**
     * Returns the first page of List. Will rewind the iterator.
     *
     * @return the first page of List
     * @throws GitLabApiException if any error occurs
     */
    public List<T> first() throws GitLabApiException {
        return (page(1));
    }

    /**
     * Returns the last page of List. Will set the iterator to the end.
     *
     * @return the last page of List
     * @throws GitLabApiException if any error occurs
     */
    public List<T> last() throws GitLabApiException {
        return (page(totalPages));
    }

    /**
     * Returns the previous page of List. Will set the iterator to the previous page.
     *
     * @return the previous page of List
     * @throws GitLabApiException if any error occurs
     */
    public List<T> previous() throws GitLabApiException {
        return (page(currentPage - 1));
    }

    /**
     * Returns the current page of List.
     *
     * @return the current page of List
     * @throws GitLabApiException if any error occurs
     */
    public List<T> current() throws GitLabApiException {
        return (page(currentPage));
    }

    /**
     * Returns the specified page of List.
     *
     * @param pageNumber the page to get
     * @return the specified page of List
     * @throws NoSuchElementException if the iteration has no more elements
     * @throws RuntimeException if a GitLab API error occurs, will contain a wrapped GitLabApiException with the details of the error
     */
    public List<T> page(int pageNumber) {

        if (pageNumber > totalPages) {
            throw new NoSuchElementException();
        } else if (pageNumber < 1) {
            throw new NoSuchElementException();
        }

        if (currentPage == 0 && pageNumber == 1) {
            currentPage = 1;
            return (currentItems);
        }

        if (currentPage == pageNumber) {
            return (currentItems);
        }

        try {

            setPageParam(pageNumber);
            Response response = api.get(Response.Status.OK, queryParams, pathArgs);
            currentItems = mapper.readValue((InputStream) response.getEntity(), javaType);
            currentPage = pageNumber;
            return (currentItems);

        } catch (GitLabApiException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
