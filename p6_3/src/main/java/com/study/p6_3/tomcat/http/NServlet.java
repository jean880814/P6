package com.study.p6_3.tomcat.http;

public abstract class NServlet {
    public void service(NRequest request, NResponse response) throws Exception {
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    protected abstract void doGet(NRequest request, NResponse response) throws Exception;
    protected abstract void doPost(NRequest request, NResponse response) throws Exception;

}
