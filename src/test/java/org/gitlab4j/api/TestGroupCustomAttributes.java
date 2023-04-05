/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 Greg Messner <greg@messners.com>
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.gitlab4j.api.models.CustomAttribute;
import org.gitlab4j.api.models.Group;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * In order for these tests to run you must set the following properties in ~/test-gitlab4j.properties
 *
 * TEST_NAMESPACE
 * TEST_GROUP
 * TEST_HOST_URL
 * TEST_PRIVATE_TOKEN
 *
 * If any of the above are NULL, all tests in this class will be skipped.
 */
@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestGroupCustomAttributes extends AbstractIntegrationTest {

    private static final String TEST_CUSTOM_ATTRIBUTE_KEY = "GitLab4JCustomAttributeTestKey";
    private static final String TEST_CUSTOM_ATTRIBUTE_VALUE = "CustomAttributeValue";

    private static GitLabApi gitLabApi;
    private static Group testGroup;

    public TestGroupCustomAttributes() {
        super();
    }

    @BeforeAll
    public static void setup() {

        // Must setup the connection to the GitLab test server and get the test Group instance
        gitLabApi = baseTestSetup();
        testGroup = getTestGroup();

        deleteAllTestCustomAttributes();
    }

    @AfterAll
    public static void teardown() throws GitLabApiException {
        deleteAllTestCustomAttributes();
    }

    private static void deleteAllTestCustomAttributes() {
        if (gitLabApi != null) {
            try {
                List<CustomAttribute> customAttributes = gitLabApi.getGroupApi().getCustomAttributes(testGroup);
                if (customAttributes != null) {
                    for (CustomAttribute customAttribute : customAttributes) {
                        if (customAttribute.getKey().startsWith(TEST_CUSTOM_ATTRIBUTE_KEY)) {
                            gitLabApi.getGroupApi().deleteCustomAttribute(testGroup, customAttribute.getKey());
                        }
                    }
                }
            } catch (GitLabApiException ignore) {
            }
        }
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    private CustomAttribute createCustomAttribute(String key, String value) throws GitLabApiException {
        return (gitLabApi.getGroupApi().setCustomAttribute(testGroup, key, value));
    }

    @Test
    public void testCreate() throws GitLabApiException {

        CustomAttribute customAttribute = createCustomAttribute(TEST_CUSTOM_ATTRIBUTE_KEY, TEST_CUSTOM_ATTRIBUTE_VALUE);
        assertNotNull(customAttribute);
        assertEquals(TEST_CUSTOM_ATTRIBUTE_KEY, customAttribute.getKey());
        assertEquals(TEST_CUSTOM_ATTRIBUTE_VALUE, customAttribute.getValue());
    }

    @Test
    public void testUpdate() throws GitLabApiException {

        assumeTrue(testGroup != null);

        String key = TEST_CUSTOM_ATTRIBUTE_KEY + "TestUpdate";
        String value = TEST_CUSTOM_ATTRIBUTE_VALUE;
        CustomAttribute customAttribute = createCustomAttribute(key, value);
        assertNotNull(customAttribute);
        assertEquals(key, customAttribute.getKey());
        assertEquals(value, customAttribute.getValue());

        value = TEST_CUSTOM_ATTRIBUTE_VALUE + " (updated)";
        customAttribute = gitLabApi.getGroupApi().setCustomAttribute(testGroup, key, value);
        assertEquals(key, customAttribute.getKey());
        assertEquals(value, customAttribute.getValue());
    }

    @Test
    public void testGetCustomAttribute() throws GitLabApiException {

        assumeTrue(testGroup != null);

        String key = TEST_CUSTOM_ATTRIBUTE_KEY + "TestGet";
        String value = TEST_CUSTOM_ATTRIBUTE_VALUE + " (test get)";
        CustomAttribute newCustomAttribute = createCustomAttribute(key, value);
        assertNotNull(newCustomAttribute);

        Optional<CustomAttribute> customAttribute =
                gitLabApi.getGroupApi().geOptionalCustomAttribute(testGroup, key);
        assertTrue(customAttribute.isPresent());
        assertEquals(key, customAttribute.get().getKey());
        assertEquals(value, customAttribute.get().getValue());
    }

    @Test
    public void testListCustomAttributes() throws GitLabApiException {

        assumeTrue(testGroup != null);

        String key = TEST_CUSTOM_ATTRIBUTE_KEY + "TestList";
        String value = TEST_CUSTOM_ATTRIBUTE_VALUE + " (test list)";
        CustomAttribute newCustomAttribute = createCustomAttribute(key, value);
        assertNotNull(newCustomAttribute);

        List<CustomAttribute> customAttributes = gitLabApi.getGroupApi().getCustomAttributes(testGroup);
        assertNotNull(customAttributes);
        for (CustomAttribute customAttribute : customAttributes) {
            if (key.equals(customAttribute.getKey())) {
                assertEquals(value, customAttribute.getValue());
                break;
            }
        }
    }

    @Test
    public void testDeleteCustomAttribute() throws GitLabApiException {

        assumeTrue(testGroup != null);

        String key = TEST_CUSTOM_ATTRIBUTE_KEY + "TestDelete";
        String value = TEST_CUSTOM_ATTRIBUTE_VALUE + " (test delete)";
        createCustomAttribute(key, value);

        Stream<CustomAttribute> stream = gitLabApi.getGroupApi().getCustomAttributesStream(testGroup);
        Optional<CustomAttribute> match = stream.filter(c -> c.getKey().equals(key)).findFirst();
        assertTrue(match.isPresent());

        gitLabApi.getGroupApi().deleteCustomAttribute(testGroup, key);

        stream = gitLabApi.getGroupApi().getCustomAttributesStream(testGroup);
        match = stream.filter(c -> c.getKey().equals(key)).findFirst();
        assertFalse(match.isPresent());
    }
}
