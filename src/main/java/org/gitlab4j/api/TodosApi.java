/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Greg Messner <greg@messners.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.gitlab4j.api;

import org.gitlab4j.api.models.Todo;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class implements the client side API for the GitLab Todos API.
 */
public class TodosApi extends AbstractApi {

    public TodosApi(GitLabApi gitLabApi) {
        super(gitLabApi);
    }

    /**
     * Get a list of all pending todos for the current user.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @return a list of pages in todo
     * @throws GitLabApiException if any exception occurs
     */
    public List<Todo> getTodos() throws GitLabApiException {
        return (getTodos(null, null, null, null, null, null, getDefaultPerPage()).all());
    }

    /**
     * Get a list of pages in all pending todos for the current user.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @param page    the page to get
     * @param perPage the number of todo-pages per page
     * @return a list of pages in todo for the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Todo> getTodos(int page, int perPage) throws GitLabApiException {
        return (getTodos(null, null, null, null, null, null, page, perPage));
    }

    /**
     * Get a list of all todos for the current user.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @param itemsPerPage the number of todo that will be fetched per page
     * @return a Pager containing the Todos for the user
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Todo> getTodos(int itemsPerPage) throws GitLabApiException {
        return (getTodos(null, null, null, null, null, null, itemsPerPage));
    }

    /**
     * Returns a list of todos. When no filter is applied,
     * it returns all pending todos for the current user.
     * Different filters allow the user to precise the request.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @param action       the action to be filtered. Can be assigned, mentioned, build_failed, marked, approval_required, unmergeable or directly_addressed.
     * @param authorId     the ID of an author
     * @param projectId    the ID of a project
     * @param groupId      the ID of a group
     * @param state        the state of the todo. Can be either pending or done
     * @param type         the type of a todo. Can be either Issue or MergeRequest
     * @param itemsPerPage the number of todo that will be fetched per page
     * @return a list of pages in todo for the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public Pager<Todo> getTodos(TodoAction action, Integer authorId, Integer projectId, Integer groupId, TodoState state, TodoType type, int itemsPerPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm()
                .withParam("action", action, false)
                .withParam("author_id", authorId, false)
                .withParam("project_id", projectId, false)
                .withParam("group_id", groupId, false)
                .withParam("state", state, false)
                .withParam("type", type, false);
        return (new Pager<Todo>(this, Todo.class, itemsPerPage, formData.asMap(), "todos"));
    }

    /**
     * Returns a list of todos. When no filter is applied,
     * it returns all pending todos for the current user.
     * Different filters allow the user to precise the request.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @param action    the action to be filtered. Can be assigned, mentioned, build_failed, marked, approval_required, unmergeable or directly_addressed.
     * @param authorId  the ID of an author
     * @param projectId the ID of a project
     * @param groupId   the ID of a group
     * @param state     the state of the todo. Can be either pending or done
     * @param type      the type of a todo. Can be either Issue or MergeRequest
     * @param page      the page to get
     * @param perPage   the number of todo-pages per page
     * @return a list of pages in todo for the specified range
     * @throws GitLabApiException if any exception occurs
     */
    public List<Todo> getTodos(TodoAction action, Integer authorId, Integer projectId, Integer groupId, TodoState state, TodoType type, int page, int perPage) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm(page, perPage)
                .withParam("action", action, false)
                .withParam("author_id", authorId, false)
                .withParam("project_id", projectId, false)
                .withParam("group_id", groupId, false)
                .withParam("state", state, false)
                .withParam("type", type, false);
        Response response = get(Response.Status.OK, formData.asMap(), "todos");
        return (response.readEntity(new GenericType<List<Todo>>() {
        }));
    }

    /**
     * Get a Stream of all todos for the current user.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @return Stream of Todos
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Todo> getTodoStream() throws GitLabApiException {
        return (getTodos(null, null, null, null, null, null, getDefaultPerPage()).stream());
    }

    /**
     * Get a Stream of all todos for the current user with pagination support.
     *
     * <pre><code>GitLab Endpoint: GET /todos</code></pre>
     *
     * @param action    the action to be filtered. Can be assigned, mentioned, build_failed, marked, approval_required, unmergeable or directly_addressed.
     * @param authorId  the ID of an author
     * @param projectId the ID of a project
     * @param groupId   the ID of a group
     * @param state     the state of the todo. Can be either pending or done
     * @param type      the type of a todo. Can be either Issue or MergeRequest
     * @return Stream of Todos
     * @throws GitLabApiException if any exception occurs
     */
    public Stream<Todo> getTodoStream(TodoAction action, Integer authorId, Integer projectId, Integer groupId, TodoState state, TodoType type) throws GitLabApiException {
        return (getTodos(action, authorId, projectId, groupId, state, type, getDefaultPerPage()).stream());
    }

    /**
     * Marks a single pending todo given by its ID for the current user as done.
     * The todo marked as done is returned in the response.
     *
     * <pre><code>GitLab Endpoint: POST /todos/:id/mark_as_done</code></pre>
     *
     * @param todoId the ID of a todi
     * @return todo instance with info on the created page
     * @throws GitLabApiException if any exception occurs
     */
    public Todo markAsDone(Integer todoId) throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm();
        Response response = post(Response.Status.OK, formData, "todos", todoId, "mark_as_done");
        return (response.readEntity(Todo.class));
    }

    /**
     * Marks all pending todos for the current user as done.
     *
     * <pre><code>GitLab Endpoint: POST /todos/mark_as_done</code></pre>
     *
     * @throws GitLabApiException if any exception occurs
     */
    public void markAsDone() throws GitLabApiException {
        GitLabApiForm formData = new GitLabApiForm();
        Response response = post(Response.Status.NO_CONTENT, formData, "todos", "mark_as_done");
    }
}
