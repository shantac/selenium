package com.letterboxd.selenium.util;

import org.apache.commons.lang3.StringUtils;

public class PropertyUtils {

	public static String getValue(String key) {
		String property = System.getProperty(key);
		if (StringUtils.isEmpty(property)) {
			property = System.getenv(key);
		}
		return property;
	}

}
