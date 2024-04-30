package com._98labs.exercises.sockets.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    private static Properties properties;
    private static boolean propertiesLoaded = false;
    private static void loadProperties() throws IOException {
        if (propertiesLoaded) {
            return; //avoid loading properties multiple times
        }
        String filePath = LoadProperties.class.getClassLoader().getResource("clientConfig.properties").getPath();
        FileInputStream readFile = new FileInputStream(filePath);
        properties = new Properties();
        properties.load(readFile);
    }
    private static String getProperty(String key) throws IOException {
        loadProperties();
        String property = properties.getProperty(key);
        if (property == null) {
            throw new IOException("Property '" + key + "' not found");
        }
        return property;
    }
    public static String portProperty() throws Exception{
        return getProperty("port");
    }
    public static String hostProperty() throws Exception{
        return getProperty("localhost");
    }

    public static String terminateProperty() throws Exception{
        return getProperty("terminateValue");
    }
    public static String decimalProperty() throws Exception{
        return getProperty("decimalPattern");
    }
    public static String integerProperty() throws Exception{
        return getProperty("integerPattern");
    }
}
