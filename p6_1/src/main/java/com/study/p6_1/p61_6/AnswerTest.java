package com.study.p6_1.p61_6;

import com.google.common.eventbus.EventBus;

public class AnswerTest {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Teacher());
        Question q = new Question();
        q.setUserName("Jean");
        q.setContent("Guava提问测试");
        eventBus.post(q);
    }
}
