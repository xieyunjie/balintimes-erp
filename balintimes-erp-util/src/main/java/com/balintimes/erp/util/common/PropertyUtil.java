package com.balintimes.erp.util.common;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by AlexXie on 2015/9/2.
 */
public class PropertyUtil {

    @Test
    public void readFile() {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream("/settings.properties");
            //prop.load();
            prop.load(in);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + prop.getProperty(key));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFile() {
        initLog4jProperties();
    }

    private String Log4jP = "settings.properties";

    private void initLog4jProperties() {
        //未打包时读取配置
        String file = this.getClass().getClassLoader().getResource(Log4jP).getFile();
        System.out.println(file);
        if (new java.io.File(file).exists()) {
            PropertyConfigurator.configure(file);
            System.out.println(file);
            System.out.println("未打包时读取配置");
            return;
        }

        //读取jar包外配置文件
        file = System.getProperty("user.dir") + "/conf/" + Log4jP;
        if (new java.io.File(file).exists()) {
            PropertyConfigurator.configure(file);
            System.out.println("读取jar包外配置文件");
            System.out.println(file);
            return;
        }
        //读取jar包内配置文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(Log4jP);
        Properties p = new Properties();
        try {
            p.load(in);
            PropertyConfigurator.configure(p);
            System.out.println("读取jar包内配置文件");
            System.out.println(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(file);
    }

}
