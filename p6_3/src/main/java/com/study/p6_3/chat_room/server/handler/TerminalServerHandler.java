package com.study.p6_3.chat_room.server.handler;

import com.study.p6_3.chat_room.processor.MsgProcessor;
import com.study.p6_3.chat_room.protocol.IMMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class TerminalServerHandler extends SimpleChannelInboundHandler<IMMessage> {
    public static final TerminalServerHandler INSTAINCE = new TerminalServerHandler();
    private TerminalServerHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IMMessage imMessage) throws Exception {
        MsgProcessor.INSTANCE.handlerMsg(channelHandlerContext.channel(), imMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("Socket Client: 与客户端断开连接:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
