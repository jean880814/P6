package com.study.p6_1.strategy_pattern;

public interface Message {
    MsgResult send(Long uid, String message);
}
