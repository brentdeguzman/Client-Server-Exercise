package com._98labs.exercises.sockets.server;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadProperties {
    private static Properties properties;
    private static void loadProperties() throws Exception {
        String filePath = LoadProperties.class.getClassLoader().getResource("serverConfig.properties").getPath();
        FileInputStream readFile = new FileInputStream(filePath);
        properties = new Properties();
        properties.load(readFile);
    }
    public static String portProperty() throws Exception{
        loadProperties();
        return properties.getProperty("port");
    }
    public static String terminateProperty() throws Exception{
        loadProperties();
        return properties.getProperty("terminateValue");
    }
}
