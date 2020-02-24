package com.study.p6_1.strategy_pattern;

public class WechatMessage implements Message {
    @Override
    public MsgResult send(Long uid, String message) {
        return new MsgResult(200, "发送成功", "wechat消息发送" + uid + message);
    }
}
