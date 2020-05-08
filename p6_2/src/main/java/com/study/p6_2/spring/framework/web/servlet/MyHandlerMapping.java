package com.study.p6_2.spring.framework.web.servlet;

import com.study.p6_2.spring.mvcframework.annotation.MyRequestParam;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MyHandlerMapping {
    Object controller;
    Pattern pattern;
    Method method;
    List<Param> params = new ArrayList<Param>();

    public MyHandlerMapping(Object controller, Pattern pattern, Method method) {
        this.controller = controller;
        this.pattern = pattern;
        this.method = method;
        initParamIndexMapping(method);
    }

    private void initParamIndexMapping(Method method) {
        Parameter[] parameters = method.getParameters();
        Class<?> [] paramsTypes = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] b =  parameterAnnotations[i];
            for (Annotation a : b) {
                if (a instanceof MyRequestParam) {
                    String value= ((MyRequestParam) a).value().trim();
                    String paramName = ! StringUtils.isEmpty(value) ? value : parameters[i].getName();
                    params.add(new Param(paramName, i, paramsTypes[i]));
                }
            }
        }

        for (int i = 0; i < paramsTypes.length ; i ++) {
            Class<?> type = paramsTypes[i];
            if(type == HttpServletRequest.class ||
                    type == HttpServletResponse.class){
                params.add(new Param(type.getName(), i, type));
            }
        }
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
