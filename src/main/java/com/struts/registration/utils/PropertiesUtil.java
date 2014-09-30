package com.struts.registration.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties prop;

    static {
        InputStream is;
        prop = new Properties();
        is = PropertiesUtil.class.getClassLoader().getResourceAsStream("/settings.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return prop.getProperty(key);
    }
}
