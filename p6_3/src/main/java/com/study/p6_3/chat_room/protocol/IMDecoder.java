package com.study.p6_3.chat_room.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMDecoder extends ByteToMessageDecoder {

    private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        try{
            int length = byteBuf.readableBytes();
            byte[] bytes = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);
            IMMessage content = new MessagePack().read(bytes, IMMessage.class);
            if (content == null) {
                channelHandlerContext.channel().pipeline().remove(this);
            }
            list.add(content);
            byteBuf.clear();
        } catch (Exception e){
            channelHandlerContext.channel().pipeline().remove(this);
        }
    }

    public IMMessage decode(String msg) {
        if (null == msg || "".equals(msg.trim())) {
            return null;
        }
        try {
            Matcher m = pattern.matcher(msg);
            String header = "";
            String content = "";
            if (m.matches()) {
                header = m.group(1);
                content = m.group(3);
            }

            String[] heards = header.split("\\]\\[");
            long time = 0;
            try {
                time = Long.parseLong(heards[1]);
            } catch (Exception e) {
            }
            String nickName = heards[2];
            //昵称最多十个字
            nickName = nickName.length() < 10 ? nickName : nickName.substring(0, 9);

            if (msg.startsWith("[" + IMP.LOGIN.getName() + "]")) {
                return new IMMessage(heards[0], heards[3], time, nickName);
            } else if (msg.startsWith("[" + IMP.CHAT.getName() + "]")) {
                return new IMMessage(heards[0], time, nickName, content);
            } else if (msg.startsWith("[" + IMP.FLOWER.getName() + "]")) {
                return new IMMessage(heards[0], heards[3], time, nickName);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
