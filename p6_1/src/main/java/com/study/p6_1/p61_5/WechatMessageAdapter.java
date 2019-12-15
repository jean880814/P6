package com.study.p6_1.p61_5;

import com.study.p6_1.p61_4.MsgResult;

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
