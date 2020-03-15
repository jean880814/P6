package com.study.p6_1.strategy_pattern;

public class MessageSendTest {
    public static void main(String[] args) {
        MessageSenderStrategy sender = new MessageSenderStrategy(1l, "test");
        System.out.println(sender.send(MessageSenderStrategyFactory.EMAIL.getType()));
    }
}
