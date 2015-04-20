package com.unico.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by krsna on 20/04/15.
 */
public final class Props {
    private static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        String propFileName = "config.properties";
        InputStream inputStream = Props.class.getResourceAsStream(propFileName);
        try {
            if (inputStream != null)
                properties.load(inputStream);
            else
                throw new FileNotFoundException("property file '" + propFileName + "' not found in classpath");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new Properties();
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }
}
