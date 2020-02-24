package com.study.p6_1.adapter_pattern;

public class MessageSendAdapterTest {
    public static void main(String[] args) {
        MessageSenderAdapter adapter = new MessageSenderAdapter(1l, "test");
        System.out.println(adapter.sendEmail());
        System.out.println(adapter.sendSms());
        System.out.println(adapter.sendWechat());
    }
}
