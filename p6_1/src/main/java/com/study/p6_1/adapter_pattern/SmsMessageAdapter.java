package com.study.p6_1.adapter_pattern;

import com.study.p6_1.strategy_pattern.MsgResult;

public class SmsMessageAdapter implements MessageAdapter {
    @Override
    public Boolean support(Object m) {
        return m instanceof SmsMessageAdapter;
    }

    @Override
    public MsgResult send(Long uid, String message) {
        return new MsgResult(200, "发送成功", "sms消息发送" + uid + message);
    }
}
