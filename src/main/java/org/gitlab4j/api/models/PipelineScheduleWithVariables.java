package org.gitlab4j.api.models;

import java.util.List;

public class PipelineScheduleWithVariables extends PipelineSchedule {
	private List<Variable> variables;

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
}
