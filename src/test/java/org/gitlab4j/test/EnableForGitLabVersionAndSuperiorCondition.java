package org.gitlab4j.test;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.platform.commons.util.AnnotationUtils;
import org.opentest4j.TestAbortedException;

public class EnableForGitLabVersionAndSuperiorCondition implements InvocationInterceptor {

	private static final ConditionEvaluationResult ENABLED = ConditionEvaluationResult
			.enabled("@EnableForGitLabVersionAndSuperiorCondition is not present");

	@Override
	public void interceptTestTemplateMethod(Invocation<Void> invocation,
			ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
		Method testMethod = extensionContext.getRequiredTestMethod();
		List<Object> arguments = invocationContext.getArguments();
		
		List<Object> containers = arguments.stream().filter(o -> {
			if (o instanceof GitLabContainer) {
				return true;
			}
			return false;
		})
		.collect(Collectors.toList());
		
		if (containers.size() != 1) {
			throw new ExtensionConfigurationException(
				String.format(
					"Method %s should declare 1 GitLabContainer. It's actually declare %d.", 
					testMethod.getName(), 
					containers.size()
				)
			);
		}
		
		GitLabContainer gitlabContainer = (GitLabContainer) containers.get(0);
		
		
		AnnotatedElement element = extensionContext.getElement().orElse(null);
		ConditionEvaluationResult result = AnnotationUtils.findAnnotation(element, EnableForGitLabVersionAndSuperior.class) //
				.map(annotation -> toResult(annotation, gitlabContainer)) //
				.orElse(ENABLED);
		
		if (result.isDisabled()) {
			throw new TestAbortedException(result.getReason().get());
		}
				
		invocation.proceed();
		
	}

	private ConditionEvaluationResult toResult(EnableForGitLabVersionAndSuperior annotation, GitLabContainer gitlabContainer) {
		Version minimalVersion = new Version(annotation.version());
		Version currentVersion = gitlabContainer.parsedVersion;
		
		if (currentVersion.isBefore(minimalVersion)) {
			return ConditionEvaluationResult.disabled(String.format("The minimal version of GitLab for this test is %s. Current execution is against GitLab %s", minimalVersion.version(), currentVersion.version()));
		}
		return ConditionEvaluationResult.enabled(minimalVersion.toString());
	}
}


