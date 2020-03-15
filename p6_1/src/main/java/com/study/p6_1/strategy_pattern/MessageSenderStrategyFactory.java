package com.study.p6_1.strategy_pattern;

public enum MessageSenderStrategyFactory {
    EMAIL("email", new EmailMessage()),
    SMS("sms", new SmsMessage()),
    WECHAT("wechat", new WechatMessage()),
    MSG_DEFAULT("default", new EmailMessage());
    private String type;
    private Message message;

    private MessageSenderStrategyFactory(String type, Message message) {
        this.type = type;
        this.message = message;
    }

    public String getType(){
        return this.type;
    }

    public Message getMessage() {
        return MessageSenderStrategyFactory.MSG_DEFAULT.message;
    }

    public Message getMessage(String type) {
        MessageSenderStrategyFactory[] values = MessageSenderStrategyFactory.values();
        for (MessageSenderStrategyFactory messageType : values) {
            if (type.equals(messageType.type)) {
                return messageType.message;
            }
        }
        return MessageSenderStrategyFactory.MSG_DEFAULT.message;
    }
}
