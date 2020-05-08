package com.study.p6_2.mybatis.Jbatis.session;

public class JSqlSessionFactory {
    private JCoufiguration coufiguration;
    public JSqlSessionFactory build(){
        coufiguration = new JCoufiguration();
        return this;
    }

    public JSqlSession openSession(){
        return new JSqlSession(coufiguration);
    }
}
