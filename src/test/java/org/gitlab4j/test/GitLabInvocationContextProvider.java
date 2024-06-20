package org.gitlab4j.test;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Stream;

import org.gitlab4j.test.SetupTeardownLauncherSessionListener.GitLabContainers;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

public class GitLabInvocationContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		return true;
	}

	@Override
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		return GitLabContainers.containers().stream().map(this::invocationContext);
	}

	private TestTemplateInvocationContext invocationContext(GitLabContainer gitLabcontainer) {
		return new TestTemplateInvocationContext() {

            @Override
            public String getDisplayName(int invocationIndex) {
              return gitLabcontainer.version;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
              return asList(
                  new ParameterResolver() {
                      @Override
                      public boolean supportsParameter(ParameterContext parameterCtx, ExtensionContext extensionCtx) {
                          return parameterCtx.getParameter().getType().equals(GitLabContainer.class);
                      }

                      @Override
                      public Object resolveParameter(ParameterContext parameterCtx, ExtensionContext extensionCtx) {
                          return gitLabcontainer;
                      }
                  });
            }
        };
	}
	
}