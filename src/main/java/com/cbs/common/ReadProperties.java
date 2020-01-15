package com.cbs.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadProperties {
    static final String propertiesFilename = "application.properties";
    private static Properties props = new Properties();

    public static Properties readProperties(String propertiesFilename) {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "/" + propertiesFilename));
            try {
                props.load(inputStream);
            } catch (IOException e) {

            }
        } catch (FileNotFoundException e) {

        }
        return props;
    }


}
