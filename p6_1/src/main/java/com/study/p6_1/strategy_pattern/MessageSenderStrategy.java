package com.study.p6_1.strategy_pattern;

public enum MessageSenderStrategy {
    EMAIL("email", new EmailMessage()),
    SMS("sms", new SmsMessage()),
    WECHAT("wechat", new WechatMessage()),
    MSG_DEFAULT("default", new EmailMessage());
    private String type;
    private Message message;

    private MessageSenderStrategy(String type, Message message) {
        this.type = type;
        this.message = message;
    }

    public Message getMessage() {
        return MessageSenderStrategy.MSG_DEFAULT.message;
    }

    public Message getMessage(String type) {
        MessageSenderStrategy[] values = MessageSenderStrategy.values();
        for (MessageSenderStrategy messageType : values) {
            if (type.equals(messageType.type)) {
                return messageType.message;
            }
        }
        return MessageSenderStrategy.MSG_DEFAULT.message;
    }
}
