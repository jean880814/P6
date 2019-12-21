package com.study.p6_1.p61_6;

import com.google.common.eventbus.Subscribe;

public class Teacher {

    @Subscribe
    public void answer(Question question){
        System.out.println("===============================");
        System.out.println("老师，你好！\n" +
                "您收到了一个提问，希望您解答，问题内容如下：\n" +
                question.getContent() + "\n" + "提问者：" + question.getUserName());
    }
}
