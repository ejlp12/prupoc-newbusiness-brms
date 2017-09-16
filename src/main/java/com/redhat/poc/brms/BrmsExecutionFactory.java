package com.redhat.poc.brms;

public class BrmsExecutionFactory {
	
	public static int SERVICE_LOCATION_LOCAL = 1;
	public static int SERVICE_LOCATION_REMOTE = 2;
	
	public static BrmsBatchExecutionServiceInf getBatchService(int serviceLocation) {
		
		BrmsBatchExecutionServiceInf service = null;
		switch (serviceLocation) {
		
		case 2:
			service = new BrmsBatchExecutionService();
		case 1:
		default:
			service = new BrmsBatchExecutionLocalService();
		}
		return service;
		
	}
}
