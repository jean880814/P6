package com.study.p6_1.strategy_pattern;

public class MessageSenderStrategy {
    private Long userId;
    private String message;

    public MessageSenderStrategy(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public MsgResult send(String messageType){
        Message message1 = MessageSenderStrategyFactory.MSG_DEFAULT.getMessage(messageType);
        return message1.send(userId, message);
    }

}
