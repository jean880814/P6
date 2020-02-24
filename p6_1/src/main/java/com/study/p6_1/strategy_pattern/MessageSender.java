package com.study.p6_1.strategy_pattern;

public class MessageSender {
    private Long userId;
    private String message;

    public MessageSender(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public MsgResult send(String messageType){
        Message message1 = MessageSenderStrategy.MSG_DEFAULT.getMessage(messageType);
        return message1.send(userId, message);
    }

}
