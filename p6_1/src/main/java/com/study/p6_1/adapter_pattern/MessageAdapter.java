package com.study.p6_1.adapter_pattern;

import com.study.p6_1.strategy_pattern.MsgResult;

public interface MessageAdapter {
    Boolean support(Object m);
    MsgResult send(Long uid, String message);
}
