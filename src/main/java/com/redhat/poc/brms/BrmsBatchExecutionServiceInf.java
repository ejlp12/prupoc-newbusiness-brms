package com.redhat.poc.brms;

import java.util.List;

public interface BrmsBatchExecutionServiceInf {

	public List<Object> execute(List<Object> factData, String ruleFlowGroup);
}
