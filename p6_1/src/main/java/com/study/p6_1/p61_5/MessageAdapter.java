package com.study.p6_1.p61_5;

import com.study.p6_1.p61_4.MsgResult;

public interface MessageAdapter {
    Boolean support(Object m);
    MsgResult send(Long uid, String message);
}
