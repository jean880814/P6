package com.study.p6_2.p62_2.framework.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyHandlerAdapter {
    public boolean supports(Object handler) {
        return handler instanceof  MyHandlerMapping;
    }

    public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MyHandlerMapping handlerMapping = (MyHandlerMapping) handler;
        List<Param> params = handlerMapping.getParams();
        Map<String,String[]> reqParameterMap = request.getParameterMap();
        Object [] paramValues = new Object[params.size()];
        for (Param entry : params) {
            String value = Arrays.toString(reqParameterMap.get(entry.getName())).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
            paramValues[entry.getIndex()] = convert(entry.getType(), value);
            if (entry.getName().equals(HttpServletRequest.class.getName())) {
                paramValues[entry.getIndex()] = request;
            }
            if (entry.getName().equals(HttpServletResponse.class.getName())) {
                paramValues[entry.getIndex()] = response;
            }
        }
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
        if (result == null) {return null;}
        if(handlerMapping.getMethod().getReturnType() ==
                MyModelAndView.class){
            return (MyModelAndView)result;
        }
        return null;
    }

    private Object convert(Class<?> clazz, String value) {
        if(clazz == String.class){
            return value;
        }else if(clazz == Integer.class){
            return Integer.valueOf(value);
        }else if(clazz == int.class){
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }
    }
}
