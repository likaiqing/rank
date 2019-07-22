package com.pandatv.tools;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by likaiqing on 2017/10/30.
 */
public class PropertiesUtil {
    public static Map<String, String> getProperties(String filepath) {
        Map<String, String> map = new HashMap<String, String>();

        Properties pps = new Properties();
        try {
            pps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            return map;
        }
        Enumeration<String> enum1 = (Enumeration<String>) pps.propertyNames();
        while (enum1.hasMoreElements()) {
            String strKey = enum1.nextElement();
            String strValue = pps.getProperty(strKey);
            map.put(strKey, strValue.trim());
        }
        return map;
    }

    public static String getPropertiesByKey(String filepath, String key) {

        Properties pps = new Properties();
        try {
            pps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return pps.getProperty(key, "").trim();
    }
    public static String getPropertiesByKey(String filepath, String key,String df) {

        Properties pps = new Properties();
        try {
            pps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return pps.getProperty(key, df).trim();
    }
}
