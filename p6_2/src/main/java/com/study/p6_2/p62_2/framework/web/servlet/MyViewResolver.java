package com.study.p6_2.p62_2.framework.web.servlet;

import java.io.File;
import java.util.Locale;

public class MyViewResolver {
    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";
    private File templateFile;
    private String viewName;
    public MyViewResolver(File file){
        this.viewName = file.getName().split("\\.")[0];
        this.templateFile = file;
    }

    public MyView resolveViewName(String viewName, Locale locale) throws Exception {
        return this.viewName.equals(viewName.split("\\.")[0]) ? new MyView(this.templateFile) : null;
    }
}
