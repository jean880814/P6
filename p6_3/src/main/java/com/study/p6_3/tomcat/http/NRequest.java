package com.study.p6_3.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

public class NRequest {
    private final HttpRequest req;
    private final ChannelHandlerContext ctx;
    public NRequest(ChannelHandlerContext ctx, HttpRequest req) {
        this.req = req;
        this.ctx = ctx;
    }

    public String getUrl() {
        return req.getUri();
    }

    public String getMethod() {
        return req.getMethod().name();
    }
}
