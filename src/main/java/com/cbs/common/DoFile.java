package com.cbs.common;
import java.io.IOException;
import  java.util.Properties;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoFile {
    public static String fileName;
    public static String filed;
    public static String data;
    static final Logger logger = LoggerFactory.getLogger(DoFile.class);

    public String getDataByFiled(String fileName,String filed) throws IOException {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = DoFile.class.getClassLoader().getResourceAsStream("resources/application.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        logger.info("properties.getProperty(filed)");
        return properties.getProperty(filed);
    }

    //写文件
    public void writeLog(String filepath,String logInfo){

    }
}
