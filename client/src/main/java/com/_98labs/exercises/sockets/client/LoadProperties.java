package com._98labs.exercises.sockets.client;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadProperties {
    private static Properties properties;
    private static boolean propertiesLoaded = false;
    private static void loadProperties() throws Exception {
        if (propertiesLoaded) {
            return; //avoid loading properties multiple times
        }
        String filePath = LoadProperties.class.getClassLoader().getResource("clientConfig.properties").getPath();
        FileInputStream readFile = new FileInputStream(filePath);
        properties = new Properties();
        properties.load(readFile);
    }
    public static String hostProperty() throws Exception{
        loadProperties();
        return properties.getProperty("localhost");
    }
    public static String portProperty() throws Exception{
        loadProperties();
        return properties.getProperty("port");
    }
    public static String terminateProperty() throws Exception{
        loadProperties();
        return properties.getProperty("terminateValue");
    }
    public static String decimalProperty() throws Exception{
        loadProperties();
        return properties.getProperty("decimalPattern");
    }
    public static String integerProperty() throws Exception{
        loadProperties();
        return properties.getProperty("integerPattern");
    }
}
