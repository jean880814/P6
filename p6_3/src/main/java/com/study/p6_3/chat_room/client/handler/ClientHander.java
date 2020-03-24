package com.study.p6_3.chat_room.client.handler;

import com.study.p6_3.chat_room.protocol.IMMessage;
import com.study.p6_3.chat_room.protocol.IMP;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ClientHander extends ChannelInboundHandlerAdapter {
    final String nickName;
    private ChannelHandlerContext ctx;

    public ClientHander(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        IMMessage imMessage = new IMMessage(IMP.LOGIN.getName(), "Console", System.currentTimeMillis(), this.nickName);
        sendMsg(imMessage);
        new Session().start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        IMMessage m = (IMMessage) msg;
        System.out.println((null == m.getSender() ? "" : (m.getSender() + ":")) + removeHtmlTag(m.getContent()));
    }

    private static String removeHtmlTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("与服务器断开连接:" + cause.getMessage());
        ctx.close();
    }

    class Session extends Thread {
        @Override
        public void run() {
            System.out.println(nickName + ",你好，请在控制台输入对话内容");
            IMMessage message = null;
            Scanner scanner = new Scanner(System.in);
            do {
                if (scanner.hasNext()) {
                    String input = scanner.nextLine();
                    if ("exit".equals(input)) {
                        message = new IMMessage(IMP.LOGOUT.getName(), "Console", System.currentTimeMillis(), nickName);
                    } else {
                        message = new IMMessage(IMP.CHAT.getName(), "Console", System.currentTimeMillis(), nickName, input);
                    }
                }
            }
            while (sendMsg(message));
            scanner.close();
        }
    }

    private boolean sendMsg(IMMessage msg) {
        ctx.channel().writeAndFlush(msg);
        System.out.println("继续输入开始对话...");
        return msg.getCmd().equals(IMP.LOGOUT) ? false : true;
    }
}
