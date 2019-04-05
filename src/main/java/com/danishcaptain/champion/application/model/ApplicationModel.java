package com.danishcaptain.champion.application.model;

import com.danishcaptain.champion.application.ExecuteException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ApplicationModel {
    private HashMap<String, Properties> propertyMap = new HashMap<>();

    public void init() throws ExecuteException {
    }

    public void start() throws ExecuteException {
    }

    public void stop() throws ExecuteException {
    }

    public void initProperties(String keySet) throws ExecuteException {
        Properties p = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(keySet+".properties");
        if (is == null) {
            throw new RuntimeException("missing properties: "+keySet);
        } else {
            try {
                p.load(is);
            } catch (IOException e) {
                throw new ExecuteException(e);
            }
            propertyMap.put(keySet, p);
        }
    }

    public String getProperty(String keySet, String key) {
        Properties p = propertyMap.get(keySet);
        if (p == null) {
            throw new RuntimeException("missing properties: "+keySet);
        } else {
            String value = p.getProperty(key);
            return value;
        }
    }

    public String getProperty(String keySet, String key, String defaultValue) {
        String value = getProperty(keySet, key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

}
