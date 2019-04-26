package org.gitlab4j.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This interface is used as a category for JUnit allowing integration tests to be skipped 
 * while running unit tests.
 * <p>To mark a test class as an integration test add the following lines before the class declaration:</p>
 *
 * <pre><code>
 * import org.junit.experimental.categories.Category;
 *
 * @Category(org.gitlab4j.api.IntegrationTest.class)
 * </code></pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IntegrationTest {
}
