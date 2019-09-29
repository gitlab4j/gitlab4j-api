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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 * <p>
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 * <p>
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Category(IntegrationTest.class)
public class TestTodosApi extends AbstractIntegrationTest {

    private static GitLabApi gitLabApi;

    public TestTodosApi() {
        super();
    }

    @BeforeClass
    public static void setup() {
        // Must setup the connection to the GitLab test server and get the test Project instance
        gitLabApi = baseTestSetup();
    }

    @Before
    public void beforeMethod() {
        assumeNotNull(gitLabApi);
    }


    @Test
    public void testGetTodos() throws GitLabApiException {
        List<Todo> todos = gitLabApi.getTodosApi().getTodos();
        assertNotNull(todos);
    }

    @Test
    public void testMarkAsDone() throws GitLabApiException {
        gitLabApi.getTodosApi().markAsDone();

        List<Todo> todos = gitLabApi.getTodosApi().getTodos();
        assertNotNull(todos);
        assertEquals(0, todos.size());
    }
}
