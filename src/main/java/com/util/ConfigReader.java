package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    
    private static Properties properties;
    private static final String PROPERTY_FILE_PATH = "src/test/resources/config.properties";

    // Static block to initialize and load properties once when the class is loaded
    static {
        try (FileInputStream fis = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file at " + PROPERTY_FILE_PATH);
        }
    }

    /**
     * Call this method to get values matching your property keys
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Key '" + key + "' not found in the config.properties file.");
        }
    }
}
