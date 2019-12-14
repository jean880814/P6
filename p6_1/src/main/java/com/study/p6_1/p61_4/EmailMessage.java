package com.study.p6_1.p61_4;

public class EmailMessage implements Message {
    @Override
    public MsgResult send(Long uid, String message) {
        return new MsgResult(200, "发送成功", "email消息发送" + uid + message);
    }
}
