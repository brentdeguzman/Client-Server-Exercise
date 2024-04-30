package com._98labs.exercises.sockets.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    private static Properties properties;
    private static void loadProperties() throws IOException {
        String filePath = LoadProperties.class.getClassLoader().getResource("serverConfig.properties").getPath();
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
    public static String terminateProperty() throws Exception{
        return getProperty("terminateValue");
    }
}
