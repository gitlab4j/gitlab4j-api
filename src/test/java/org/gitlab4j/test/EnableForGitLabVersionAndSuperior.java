package org.gitlab4j.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(EnableForGitLabVersionAndSuperiorCondition.class)
public @interface EnableForGitLabVersionAndSuperior {

	/**
	 * The minimal version on which this test will be executed.
	 */
	String version() default "";
	
}
