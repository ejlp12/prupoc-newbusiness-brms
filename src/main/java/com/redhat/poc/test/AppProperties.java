package com.redhat.poc.test;

import java.util.ResourceBundle;

public class AppProperties {
	private static ResourceBundle properties;
	public static String CONTAINER_ID;
	
	static {
		properties = ResourceBundle.getBundle("Application");
		CONTAINER_ID = properties.getString("brms.containerId");
	}
	
	public static String getString(String key) {
		return properties.getString(key);
	}
}
