package com.study.p6_2.spring.mvcframework.v2;

import com.study.p6_2.spring.mvcframework.annotation.*;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDispatcherServlet extends HttpServlet {
    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<String>();
    private Map<String, Object> ioc = new HashMap<String, Object>();
    private List<HandleMapping> handlerMapping = new ArrayList<HandleMapping>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        doScaner(contextConfig.getProperty("scanPackage"));
        doInstance();
        doAutowired();
        doHandleMapping();
        System.out.println("GP Spring framework is init.");
    }

    private void doAutowired() {
        if(ioc.isEmpty()){ return; }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Object instance = entry.getValue();
            for (Field declaredField : instance.getClass().getDeclaredFields()) {
                if (!declaredField.isAnnotationPresent(MyAutowired.class)) {continue;}
                MyAutowired myAutowired = declaredField.getAnnotation(MyAutowired.class);
                String beanName = ! StringUtils.isEmpty(myAutowired.value()) ? myAutowired.value().trim() : declaredField.getType().getName();
                declaredField.setAccessible(true);
                try {
                    declaredField.set(instance, ioc.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }

        }

    }

    private void doHandleMapping() {
        if(ioc.isEmpty()){ return; }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Object instance = entry.getValue();
            if (!instance.getClass().isAnnotationPresent(MyController.class)){continue;}
            MyRequestMapping MyRequestMapping = instance.getClass().getAnnotation(MyRequestMapping.class);
            String baseUrl = StringUtils.isEmpty(MyRequestMapping.value()) ? "" : MyRequestMapping.value();
            for (Method method : instance.getClass().getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)){continue;}
                MyRequestMapping methodAnnotation = method.getAnnotation(MyRequestMapping.class);
                String url = ("/" + baseUrl + "/" + methodAnnotation.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(url);
                handlerMapping.add(new HandleMapping(instance, pattern, method));
                System.out.println("Mapped " + url + "," + method);
            }


        }

    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    ioc.put(toLowerFirstCase(clazz.getSimpleName()), clazz.newInstance());
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    MyService service = clazz.getAnnotation(MyService.class);
                    String beanName = !"".equals(service.value()) ? service.value() : toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    for (Class<?> clazzInterface : clazz.getInterfaces()) {
                        if (ioc.containsKey(clazzInterface.getName())) {
                            throw new Exception("The beanName is exists!!");
                        }
                        ioc.put(clazzInterface.getName(), instance);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            contextConfig.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doScaner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());

        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScaner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName()).replace(".class", "");
                classNames.add(className);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandleMapping handleMapping = getHandler(req);
        if(handleMapping == null){
            //如果没有匹配上，返回404错误
            resp.getWriter().write("404 Not Found");
            return;
        }
        Method method = handleMapping.getMethod();
        List<Param> params = handleMapping.getParams();
        //保存请求的url参数列表
        Map<String,String[]> parameterMap = req.getParameterMap();
        //保存赋值参数的位置
        Object [] paramValues = new Object[params.size()];

        for (Param entry : params) {
            String value = Arrays.toString(parameterMap.get(entry.getName())).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
            paramValues[entry.getIndex()] = convert(entry.getType(), value);
            if (entry.getName().equals(HttpServletRequest.class.getName())) {
                paramValues[entry.getIndex()] = req;
            }
            if (entry.getName().equals(HttpServletResponse.class.getName())) {
                paramValues[entry.getIndex()] = resp;
            }
        }
        Object invoke = method.invoke(handleMapping.getController(), paramValues);
        if (invoke != null) {
            resp.getWriter().write(invoke.toString());
        }

    }

    private HandleMapping getHandler(HttpServletRequest req) throws Exception{
        if(handlerMapping.isEmpty()){ return null; }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (HandleMapping handler : handlerMapping) {
            Matcher matcher = handler.pattern.matcher(url);
            //如果没有匹配上继续下一个匹配
            if(!matcher.matches()){ continue; }
            return handler;
        }
        return null;
    }

    private Object convert(Class<?> type,String value){
        if(Integer.class == type){
            return Integer.valueOf(value);
        }
        return value;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    class Param {
        String name;
        int index;
        Class<?> type;

        public Param(String name, int index, Class<?> type) {
            this.name = name;
            this.index = index;
            this.type = type;
        }

        public Class<?> getType() {
            return type;
        }

        public void setType(Class<?> type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    class HandleMapping{
        Object controller;
        Pattern pattern;
        Method method;
        List<Param> params = new ArrayList<Param>();

        public HandleMapping(Object controller, Pattern pattern, Method method) {
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
}
