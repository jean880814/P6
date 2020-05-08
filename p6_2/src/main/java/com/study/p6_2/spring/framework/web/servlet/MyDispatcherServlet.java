package com.study.p6_2.spring.framework.web.servlet;

import com.study.p6_2.spring.framework.beans.MyBeanWrapper;
import com.study.p6_2.spring.framework.context.MyApplicationContext;
import com.study.p6_2.spring.mvcframework.annotation.MyController;
import com.study.p6_2.spring.mvcframework.annotation.MyRequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class MyDispatcherServlet extends HttpServlet {
    private static final String CONFIG = "contextConfigLocation";
    private List<MyHandlerMapping> handlerMappings = new ArrayList<MyHandlerMapping>();
    private Map<MyHandlerMapping, MyHandlerAdapter> handlerAdapters = new HashMap<MyHandlerMapping,
            MyHandlerAdapter>();
    private List<MyViewResolver> viewResolvers = new ArrayList<MyViewResolver>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        }catch (Exception e){
            resp.getWriter().write("<font size='25' color='blue'>500 Exception</font><br/>Details:<br/>" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s","\r\n") + "<font color='green'><i>Copyright@GupaoEDU</i></font>");
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        MyHandlerMapping handlerMapping = getHandler(req);
        MyModelAndView modelAndView = null;
        if (handlerMapping == null) {
            modelAndView = new MyModelAndView("404", null);
        } else {
            MyHandlerAdapter myHandlerAdapter = getAdapter(handlerMapping);
            modelAndView= myHandlerAdapter == null ?  null : myHandlerAdapter.handle(req, resp, handlerMapping);
        }
        processDispatchResult(req, resp, modelAndView);
    }

    private void processDispatchResult(HttpServletRequest request,HttpServletResponse response,
                                       MyModelAndView mv) throws Exception {
        if (mv == null) {return;}
        for (MyViewResolver myViewResolver : viewResolvers) {
            MyView myView = myViewResolver.resolveViewName(mv.getViewName(), null);
            if (myView != null) {
                myView.render(mv.getModel(), request ,response);
                return;
            }
        }
    }

    private MyHandlerAdapter getAdapter(MyHandlerMapping handlerMapping) {
        MyHandlerAdapter myHandlerAdapter = handlerAdapters.get(handlerMapping);
        return myHandlerAdapter.supports(handlerMapping) ? myHandlerAdapter : null;
    }

    private MyHandlerMapping getHandler(HttpServletRequest req) {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (MyHandlerMapping handler : handlerMappings) {
            Matcher matcher = handler.pattern.matcher(url);
            //如果没有匹配上继续下一个匹配
            if(!matcher.matches()){ continue; }
            return handler;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        initStrategies(new MyApplicationContext(config.getInitParameter(CONFIG)));
    }

    private void initStrategies(MyApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化模板处理器
        initThemeResolver(context);
        //handlerMapping
        initHandlerMappings(context);
        //初始化参数适配器
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);
        //初始化视图转换器
        initViewResolvers(context);
        //
        initFlashMapManager(context);
    }

    private void initFlashMapManager(MyApplicationContext context) {
    }

    private void initViewResolvers(MyApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath =
                this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new MyViewResolver(template));
        }
    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {
    }

    private void initHandlerAdapters(MyApplicationContext context) {
        for (MyHandlerMapping handlerMapping : this.handlerMappings){
            this.handlerAdapters.put(handlerMapping,new MyHandlerAdapter());
        }
    }

    private void initHandlerMappings(MyApplicationContext context) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanDefinitionNames) {
                MyBeanWrapper controller = (MyBeanWrapper) context.getBean(beanName);
                Class<?> clazz = controller.getWrappedClass();
                if (!clazz.isAnnotationPresent(MyController.class)) {
                    continue;
                }
                MyRequestMapping MyRequestMapping = clazz.getAnnotation(MyRequestMapping.class);
                String baseUrl = StringUtils.isEmpty(MyRequestMapping.value()) ? "" : MyRequestMapping.value();
                for (Method method : clazz.getMethods()) {
                    if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                        continue;
                    }
                    MyRequestMapping methodAnnotation = method.getAnnotation(MyRequestMapping.class);
                    String url = ("/" + baseUrl + "/" + methodAnnotation.value()).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(url);
                    handlerMappings.add(new MyHandlerMapping(controller.getWrappedInstance(), pattern, method));
                    log.info("Mapping: " + url + " , " + method);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void initThemeResolver(MyApplicationContext context) {
    }

    private void initLocaleResolver(MyApplicationContext context) {
    }

    private void initMultipartResolver(MyApplicationContext context) {
    }
}
