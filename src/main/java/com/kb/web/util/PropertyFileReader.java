package com.kb.web.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyFileReader {

	final static Logger logger = Logger.getLogger(PropertyFileReader.class);
	private static PropertyFileReader propertyFileReader = new PropertyFileReader();
	private static Map<String, String> propertyMap;

	private PropertyFileReader() {
		loadProperties();
	}

	public static String getProperty(String propertyName) {
		return propertyMap.get(propertyName);
	}

	private static void setProperty(String propertyKey, String propertyValue) {
		propertyMap.put(propertyKey, propertyValue);
	}

	public static PropertyFileReader getInstance() {
		return propertyFileReader;
	}

	private void loadProperties() {
		Properties properties = new Properties();
		InputStream inputStream = null;

		// String propertyFileName = System.getProperty("property_jvm");
		String propertyFileName = "application.properties";
		try {
			// inputStream = new FileInoutStream(propertyFileName);//from JVM property
			ClassLoader classLoader = getClass().getClassLoader();
			inputStream = classLoader.getResourceAsStream(propertyFileName);
			properties.load(inputStream);

			Enumeration<?> e = properties.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = properties.getProperty(key);
				propertyMap.put(key, value);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
