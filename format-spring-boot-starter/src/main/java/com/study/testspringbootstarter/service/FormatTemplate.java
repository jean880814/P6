package com.study.testspringbootstarter.service;

import com.study.testspringbootstarter.config.FormatProperties;

public class FormatTemplate {
    private MyProcessor processor;
    private FormatProperties formatProperties;
    public FormatTemplate(MyProcessor processor, FormatProperties formatProperties){
        this.processor = processor;
        this.formatProperties = formatProperties;
    }

    public <T> String doFormat(T obj){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("begin:Execute format").append("<br/>");
        stringBuilder.append("properties:").append(processor.format(formatProperties.getInfo())).append("<br/>");
        stringBuilder.append("Obj format result:").append(processor.format(obj)).append("<br/>");
        return stringBuilder.toString();
    }
}
