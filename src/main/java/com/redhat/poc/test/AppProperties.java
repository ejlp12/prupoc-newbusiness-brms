package com.redhat.poc.test;

import java.util.ResourceBundle;

public class AppProperties {
	private static ResourceBundle properties;
	private static String containerId;
	
	static {
		properties = ResourceBundle.getBundle("Application");
		containerId = properties.getString("brms.containerId");
	}
	
	public static String getString(String key) {
		return properties.getString(key);
	}
}
