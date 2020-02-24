package com.study.p6_1.adapter_pattern;

import com.study.p6_1.strategy_pattern.MsgResult;

public class WechatMessageAdapter implements MessageAdapter {
    @Override
    public Boolean support(Object m) {
        return m instanceof WechatMessageAdapter;
    }

    @Override
    public MsgResult send(Long uid, String message) {
        return new MsgResult(200, "发送成功", "wechat消息发送" + uid + message);
    }
}
