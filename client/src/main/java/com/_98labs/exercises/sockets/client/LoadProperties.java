package com._98labs.exercises.sockets.client;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadProperties {
    private static Properties properties;
    private static void loadProperties() throws Exception {
        String filePath = LoadProperties.class.getClassLoader().getResource("clientConfig.properties").getPath();
        FileInputStream readFile = new FileInputStream(filePath);
        properties = new Properties();
        properties.load(readFile);
    }
    public static String hostProperty() throws Exception{
        loadProperties();
        String localhost = properties.getProperty("localhost");
        return localhost;
    }
    public static String portProperty() throws Exception{
        loadProperties();
        String port = properties.getProperty("port");
        return port;
    }
    public static String terminateProperty() throws Exception{
        loadProperties();
        String terminateValue = properties.getProperty("terminateValue");
        return terminateValue;
    }
    public static String decimalProperty() throws Exception{
        loadProperties();
        String decimalPattern = properties.getProperty("decimalPattern");
        return decimalPattern;
    }
    public static String integerProperty() throws Exception{
        loadProperties();
        String integerPattern = properties.getProperty("integerPattern");
        return integerPattern;
    }
}
