package com.study.p6_3.chat_room.server.handler;

import com.study.p6_3.chat_room.processor.MsgProcessor;
import com.study.p6_3.chat_room.protocol.IMDecoder;
import com.study.p6_3.chat_room.protocol.IMMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static final WebSocketServerHandler INSTAINCE = new WebSocketServerHandler();
    private IMDecoder imDecoder = new IMDecoder();
    private WebSocketServerHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame webSocketFrame) throws Exception {
        try {
             IMMessage imMessage = imDecoder.decode(webSocketFrame.text());
            if (imMessage == null) {
                channelHandlerContext.channel().pipeline().remove(this);
            }
            MsgProcessor.INSTANCE.handlerMsg(channelHandlerContext.channel(), imMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            channelHandlerContext.channel().pipeline().remove(this);
        }
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            log.info("web socket 握手成功。");
            WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            String requestUri = handshakeComplete.requestUri();
            log.info("requestUri:[{}]", requestUri);
            String subproTocol = handshakeComplete.selectedSubprotocol();
            log.info("subproTocol:[{}]", subproTocol);
            handshakeComplete.requestHeaders().forEach(entry -> log.info("header key:[{}] value:[{}]", entry.getKey(), entry.getValue()));
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("Socket Client: 与客户端断开连接:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
