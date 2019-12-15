package com.study.p6_1.p61_5;

import com.study.p6_1.p61_4.MessageSender;
import com.study.p6_1.p61_4.MsgResult;

public class MessageSenderAdapter extends MessageSender {
    private Long userId;
    private String message;

    public MessageSenderAdapter(Long userId, String message) {
        super(userId, message);
        this.userId = userId;
        this.message = message;
    }

    public MsgResult sendSms(){
        return this.process(SmsMessageAdapter.class);
    }
    public MsgResult sendEmail(){
        return this.process(EmailMessageAdapter.class);
    }
    public MsgResult sendWechat(){
        return this.process(WechatMessageAdapter.class);
    }

    private MsgResult process(Class<? extends MessageAdapter> clazz) {
        try {
            MessageAdapter messageAdapter = clazz.newInstance();
            if (messageAdapter.support(messageAdapter)) {
                return messageAdapter.send(userId, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
