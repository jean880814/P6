package com.study.p6_3.chat_room.processor;

import com.alibaba.fastjson.JSONObject;
import com.study.p6_3.chat_room.protocol.IMEncoder;
import com.study.p6_3.chat_room.protocol.IMMessage;
import com.study.p6_3.chat_room.protocol.IMP;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MsgProcessor {
    public static final MsgProcessor INSTANCE = new MsgProcessor();
    private IMEncoder imEncoder = new IMEncoder();
    private static ChannelGroup onlines = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static final AttributeKey<String> NICK_NAME = AttributeKey.valueOf("nickName");
    public static final AttributeKey<String> IP_ADDR = AttributeKey.valueOf("ipAddr");
    public static final AttributeKey<JSONObject> ATTRS = AttributeKey.valueOf("attrs");
    public static final AttributeKey<String> FROM = AttributeKey.valueOf("from");
    private MsgProcessor(){}
    public String getAddress(Channel client){
        return client.remoteAddress().toString().replaceFirst("/","");
    }

    private Long sysTime(){
        return System.currentTimeMillis();
    }

    public void handlerMsg(Channel channel, IMMessage message){
        if(null == message){ return; }
        String addr = getAddress(channel);
        if (IMP.LOGIN.getName().equals(message.getCmd())){
            onlines.add(channel);
            channel.attr(NICK_NAME).getAndSet(message.getSender());
            channel.attr(IP_ADDR).getAndSet(addr);
            channel.attr(FROM).getAndSet(message.getTerminal());
            for (Channel client : onlines) {
                if(channel == client){
                    message = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlines.size(), "已与服务器建立连接！");
                }else{
                    message = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlines.size(), getNickName(channel) + "加入");
                }

                if("Console".equals(client.attr(FROM).get())){
                    client.writeAndFlush(message);
                    continue;
                }
                client.writeAndFlush(new TextWebSocketFrame(imEncoder.encode(message)));
            }
        }
        if (IMP.CHAT.getName().equals(message.getCmd())) {
            for (Channel client : onlines) {
                if(channel == client){
                    message.setSender("你");
                } else {
                    message.setSender(getNickName(channel));
                }
                if("Console".equals(client.attr(FROM).get()) & !(channel == client)){
                    client.writeAndFlush(message);
                    continue;
                }
                client.writeAndFlush(new TextWebSocketFrame(imEncoder.encode(message)));
            }
        }
        if (IMP.LOGOUT.getName().equals(message.getCmd())){
            if(getNickName(channel) == null){ return; }
            for (Channel client : onlines) {
                if(channel == client){
                    message = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlines.size() - 1, "你离开");
                }else{
                    message = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlines.size() - 1 , getNickName(client) + "离开");
                }
                if("Console".equals(client.attr(FROM).get())){
                    client.writeAndFlush(message);
                    continue;
                }
                client.writeAndFlush(new TextWebSocketFrame(imEncoder.encode(message)));
            }
            onlines.remove(channel);
        }
        if (IMP.FLOWER.getName().equals(message.getCmd())) {
            JSONObject attrs = getAttrs(channel);
            long currTime = sysTime();
            if(null != attrs){
                long lastTime = attrs.getLongValue("lastFlowerTime");
                //60秒之内不允许重复刷鲜花
                int secends = 10;
                long sub = currTime - lastTime;
                if(sub < 1000 * secends){
                    message.setSender("you");
                    message.setCmd(IMP.SYSTEM.getName());
                    message.setContent("您送鲜花太频繁," + (secends - Math.round(sub / 1000)) + "秒后再试");

                    String content = imEncoder.encode(message);
                    channel.writeAndFlush(new TextWebSocketFrame(content));
                    return;
                }
            }

            //正常送花
            for (Channel client : onlines) {
                if (channel == client) {
                    message.setSender("你");
                    message.setContent("你给大家送了一波鲜花雨");
                    setAttrs(client, "lastFlowerTime", currTime);
                }else{
                    message.setSender(getNickName(client));
                    message.setContent(getNickName(client) + "送来一波鲜花雨");
                }
                message.setTime(sysTime());

                String content = imEncoder.encode(message);
                client.writeAndFlush(new TextWebSocketFrame(content));
            }
        }

    }

    private String getNickName(Channel client) {
        return client.attr(NICK_NAME).get();
    }

    /**
     * 获取扩展属性
     * @param client
     * @return
     */
    public JSONObject getAttrs(Channel client){
        try{
            return client.attr(ATTRS).get();
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 获取扩展属性
     * @param client
     * @return
     */
    private void setAttrs(Channel client,String key,Object value){
        try{
            JSONObject json = client.attr(ATTRS).get();
            json.put(key, value);
            client.attr(ATTRS).set(json);
        }catch(Exception e){
            JSONObject json = new JSONObject();
            json.put(key, value);
            client.attr(ATTRS).set(json);
        }
    }
}
