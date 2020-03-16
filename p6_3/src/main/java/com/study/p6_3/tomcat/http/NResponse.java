package com.study.p6_3.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

public class NResponse {
    private final HttpRequest req;
    private final ChannelHandlerContext ctx;
    public NResponse(ChannelHandlerContext ctx, HttpRequest req) {
        this.req = req;
        this.ctx = ctx;
    }

    public void write(String out) throws UnsupportedEncodingException {
        try {
            if (out == null || out.length() == 0) {
                return;
            }
            // 设置 http协议及请求头信息
            FullHttpResponse response = new DefaultFullHttpResponse(
                    // 设置http版本为1.1
                    HttpVersion.HTTP_1_1,
                    // 设置响应状态码
                    HttpResponseStatus.OK,
                    // 将输出值写出 编码为UTF-8
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8")));

            response.headers().set("Content-Type", "text/html;");
            // 当前是否支持长连接
            if (HttpUtil.isKeepAlive(req)) {
                // 设置连接内容为长连接
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response);
        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
