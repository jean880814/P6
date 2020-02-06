package com.study.p6_2.p62_2.framework.beans.support;

import com.study.p6_2.p62_2.framework.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyBeanDefinitionReader {
    private List<String> registyBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();
    private final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader(String ... configs){
        for (String config : configs) {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(config.replaceAll("classpath:", ""));
            try {
                this.config.load(is);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        doScan(config.getProperty(SCAN_PACKAGE));
    }

    private void doScan(String scanPackage) {
        //转换为文件路径，实际上就是把.替换为/就 OK 了
        URL url = this.getClass().getClassLoader().getResource("/" +
                scanPackage.replaceAll("\\.","/"));
        File filepath = new File(url.getFile());
        for (File file : filepath.listFiles()) {
            if(file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());
            } else {
                if(!file.getName().endsWith(".class")){ continue;}
                String className = (scanPackage + "." + file.getName().replace(".class",""));
                registyBeanClasses.add(className);
            }
        }
    }

    public List<MyBeanDefinition> loadDefinitions() throws ClassNotFoundException {
        List<MyBeanDefinition> beanDefinitionList = new ArrayList<MyBeanDefinition>();
        for (String beanClass : registyBeanClasses) {
            Class<?> clazz = Class.forName(beanClass);
            if (clazz.isInterface()) {continue;}
            beanDefinitionList.add(convertToBeanDefinition(toLowerFirstCase(clazz.getSimpleName()), clazz.getName()));
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> in : interfaces) {
                beanDefinitionList.add(convertToBeanDefinition(toLowerFirstCase(in.getSimpleName()), clazz.getName()));
            }
        }
        return beanDefinitionList;
    }

    private MyBeanDefinition convertToBeanDefinition(String factoryName, String className) {
        MyBeanDefinition myBeanDefinition = new MyBeanDefinition();
        myBeanDefinition.setBeanClassName(className);
        myBeanDefinition.setFactoryBeanName(factoryName);
        return myBeanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return config;
    }
}

