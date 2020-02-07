package com.study.p6_2.p62_2.framework.aop.support;

import com.study.p6_2.p62_2.framework.aop.aspect.MyAfterReturningAdviceInterceptor;
import com.study.p6_2.p62_2.framework.aop.aspect.MyAfterThrowingAdviceInterceptor;
import com.study.p6_2.p62_2.framework.aop.aspect.MyAroundAdviceInterceptor;
import com.study.p6_2.p62_2.framework.aop.aspect.MyMethodBeforeAdviceInterceptor;
import com.study.p6_2.p62_2.framework.aop.config.MyAopConfig;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MyAdvisedSupport {
    private Class<?> targetClass;
    private Object target;
    private MyAopConfig config;
    private Pattern pointCutClassPattern;
    private transient Map<Method, List<Object>> methodCache = new HashMap<Method, List<Object>>();
    public MyAdvisedSupport(MyAopConfig config) {
        this.config = config;
    }

    public boolean pointCutMatch() {
        return this.pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    private void parse() {
        String pointCut = config.getPointCut()
                .replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");
        String pointCutForClassRegex = pointCut.substring(0,pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(
                pointCutForClassRegex.lastIndexOf(" ") + 1));

        try {
            Pattern pattern = Pattern.compile(pointCut);
            Class<?> aspectClass = Class.forName(config.getAspectClass());
            Map<String,Method> aspectMethods = new HashMap<String,Method>();
            for(Method method : aspectClass.getDeclaredMethods()) {
                aspectMethods.put(method.getName(), method);
            }
            for (Method m : this.targetClass.getDeclaredMethods()) {
                String methodString = m.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }
                if(pattern.matcher(methodString).matches()){
                    List<Object> chain = new LinkedList<Object>();
                    if (!StringUtils.isEmpty(config.getAspectBefore())) {
                        chain.add(new MyMethodBeforeAdviceInterceptor(aspectMethods.get(config.getAspectBefore()), aspectClass.newInstance()));
                    }
                    if (!StringUtils.isEmpty(config.getAspectAfter())) {
                        chain.add(new MyAfterReturningAdviceInterceptor(aspectMethods.get(config.getAspectAfter()), aspectClass.newInstance()));
                    }
                    if (!StringUtils.isEmpty(config.getAspectAfterThrow())) {
                        chain.add(new MyAfterThrowingAdviceInterceptor(aspectMethods.get(config.getAspectAfterThrow()), aspectClass.newInstance()));
                    }
                    if (!StringUtils.isEmpty(config.getAspectAround())) {
                        chain.add(new MyAroundAdviceInterceptor(aspectMethods.get(config.getAspectAround()), aspectClass.newInstance()));
                    }
                    methodCache.put(m, chain);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method) throws NoSuchMethodException {
        List<Object> cached = methodCache.get(method);
        if(cached == null){
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());

            cached = methodCache.get(m);

            //底层逻辑，对代理方法进行一个兼容处理
            this.methodCache.put(m,cached);
        }

        return cached;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
