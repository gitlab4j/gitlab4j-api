package org.gitlab4j.api;

import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/Test*.class"})
@IncludeCategory(IntegrationTest.class)
public class IntegrationTestSuite {

    @BeforeClass
    public static void suiteSetup() {
        System.out.println("********************************************************");
        System.out.println("                  Test Suite Setup");
        System.out.println("********************************************************");

        // TODO Create default test resources if not present
    }
}
