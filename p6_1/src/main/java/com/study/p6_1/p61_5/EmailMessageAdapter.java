package com.study.p6_1.p61_5;

import com.study.p6_1.p61_4.MsgResult;

public class EmailMessageAdapter implements MessageAdapter {
    @Override
    public Boolean support(Object m) {
        return m instanceof EmailMessageAdapter;
    }

    @Override
    public MsgResult send(Long uid, String message) {
        return new MsgResult(200, "发送成功", "email消息发送" + uid + message);
    }
}
