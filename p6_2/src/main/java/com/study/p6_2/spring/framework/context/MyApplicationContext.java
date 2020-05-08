package com.study.p6_2.spring.framework.context;

import com.study.p6_2.spring.framework.aop.MyAopProxy;
import com.study.p6_2.spring.framework.aop.MyCglibAopProxy;
import com.study.p6_2.spring.framework.aop.MyJdkDynamicAopProxy;
import com.study.p6_2.spring.framework.aop.config.MyAopConfig;
import com.study.p6_2.spring.framework.aop.support.MyAdvisedSupport;
import com.study.p6_2.spring.framework.beans.MyBeanWrapper;
import com.study.p6_2.spring.framework.beans.config.MyBeanDefinition;
import com.study.p6_2.spring.framework.beans.support.MyBeanDefinitionReader;
import com.study.p6_2.spring.framework.beans.support.MyDefaultListableBeanFactory;
import com.study.p6_2.spring.framework.core.MyBeanFactory;
import com.study.p6_2.spring.mvcframework.annotation.MyAutowired;
import com.study.p6_2.spring.mvcframework.annotation.MyController;
import com.study.p6_2.spring.mvcframework.annotation.MyService;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {
    private String [] configLoactions;
    private MyBeanDefinitionReader reader;

    //单例的 IOC 容器缓存
    private final Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();
    //通用的 IOC 容器
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public MyApplicationContext(String ... configs){
        this.configLoactions = configs;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1、定位，定位配置文件
        reader = new MyBeanDefinitionReader(this.configLoactions);
        //2、加载配置文件，扫描相关的类，把它们封装成 BeanDefinition
        List<MyBeanDefinition> beanDefinitionList = reader.loadDefinitions();
        //3、注册，把配置信息放到容器里面(伪 IOC 容器)
        doRegisterBeanDefinition(beanDefinitionList);
        //4、把不是延时加载的类，有提前初始化
        doAutowrited();
    }
    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) throws Exception {
        for (MyBeanDefinition myBeanDefinition : beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(myBeanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + myBeanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(myBeanDefinition.getFactoryBeanName(), myBeanDefinition);
        }
    }

    private void doAutowrited() throws Exception {
        for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            MyBeanDefinition myBeanDefinition = beanDefinitionEntry.getValue();
            if (!myBeanDefinition.isLazyInit()) {
                getBean(beanDefinitionEntry.getKey());
            }
        }
    }


    @Override
    public Object getBean(String beanName) throws Exception {
        MyBeanDefinition myBeanDefinition = beanDefinitionMap.get(beanName);
        return doCreateBean(beanName, myBeanDefinition);

    }

    private Object doCreateBean(String beanName, MyBeanDefinition myBeanDefinition) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Object instance = instantiateBean(myBeanDefinition);
        if (instance == null) {return null;}
        MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);
        populateBean(beanName,instance);
        return this.factoryBeanInstanceCache.get(beanName);
    }

    private void populateBean(String beanName, Object instance) throws IllegalAccessException {
        Class<?> clazz = instance.getClass();
        if (clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class)) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {continue;}
                MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                String value = myAutowired.value();
                if (StringUtils.isEmpty(value)) {
                    value = toLowerFirstCase(field.getType().getSimpleName());
                }
                field.setAccessible(true);
                if(this.factoryBeanInstanceCache.get(value) == null){ continue; }
                field.set(instance, this.factoryBeanInstanceCache.get(value).getWrappedInstance());
            }
        }
    }

    private Object instantiateBean(MyBeanDefinition myBeanDefinition) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object instance = null;
        String className = myBeanDefinition.getBeanClassName();
        if(this.singletonObjects.containsKey(className)){
            instance = this.singletonObjects.get(className);
        }else{
            Class<?> clazz = Class.forName(className);
            instance = clazz.newInstance();
            MyAdvisedSupport advisedSupport = initAdvisedSupport();
            advisedSupport.setTarget(instance);
            advisedSupport.setTargetClass(clazz);
            if (advisedSupport.pointCutMatch()){
                instance = createAopProxy(advisedSupport).getProxy();
            }

            this.singletonObjects.put(className,instance);
            this.singletonObjects.put(myBeanDefinition.getFactoryBeanName(),instance);
        }
        return instance;
    }

    private MyAopProxy createAopProxy(MyAdvisedSupport advisedSupport) {
        if (advisedSupport.getTargetClass().getInterfaces().length > 0) {
            return new MyJdkDynamicAopProxy(advisedSupport);
        }
        return new MyCglibAopProxy(advisedSupport);
    }

    private MyAdvisedSupport initAdvisedSupport() {
        MyAopConfig config = new MyAopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAround(this.reader.getConfig().getProperty("aspectAround"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new MyAdvisedSupport(config);
    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new  String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }

    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
