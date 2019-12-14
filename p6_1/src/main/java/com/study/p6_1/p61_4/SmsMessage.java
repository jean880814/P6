package com.study.p6_1.p61_4;

public class SmsMessage implements Message {
    @Override
    public MsgResult send(Long uid, String message) {
        return new MsgResult(200, "发送成功", "sms消息发送" + uid + message);
    }
}
