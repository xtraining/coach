package com.coach.common;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {
    private static Log log = LogFactory.getLog(Config.class);
    private static String CONFIG_FILENAME = "base.properties";
    private static Properties prop = null;

    public Config() {
        if (prop == null) {
            loadProperties();
        }
    };

    private synchronized static void loadProperties() {
        try {
            InputStream is = Config.class.getResourceAsStream("/"
                    + CONFIG_FILENAME);
            prop = new Properties();
            prop.load(is);
        } catch (Exception e) {
            System.err.println("读取配置文件失败！！！");
            prop = null;
            log.error(e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        if (prop == null) {
            loadProperties();
        }
        String value = prop.getProperty(key);
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    public static String getGBKProperty(String key) {
        String value = getProperty(key);
        try {
            value = new String(value.getBytes("ISO8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value.trim();
    }

    public static String getUTF8KProperty(String key) {
        String value = getProperty(key);
        try {
            value = new String(value.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {
        if (prop == null) {
            loadProperties();
        }
        String value = prop.getProperty(key, defaultValue);
        if (value == null) {
            return null;
        }
        return value.trim();
    }
    public static String getGBKProperty(String key, String defaultValue) {
        String value = getProperty(key, defaultValue);
        try {
            value = new String(value.getBytes("ISO8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value.trim();
    }

    public static String getUTFProperty(String key, String defaultValue) {
        String value = getProperty(key, defaultValue);
        try {
            value = new String(value.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value.trim();
    }

    public static String getPropertyValuetoreplace(String key,
                                                   String arrValue[], String replaceChar) {
        String propertyValue = getGBKProperty(key);
        for (int i = 0; i < arrValue.length; i++) {
            propertyValue = propertyValue.replace(
                    replaceChar.trim() + Integer.toString(i),
                    arrValue[i].trim());
        }
        return propertyValue;
    }

    public static void setPropertyValue(String key, String value) {
        prop.put(key, value);
    }
    
}