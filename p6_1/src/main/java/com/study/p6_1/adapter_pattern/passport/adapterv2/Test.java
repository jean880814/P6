package com.study.p6_1.adapter_pattern.passport.adapterv2;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        IPassportForThird adapterforqq = new PassportForQQAdapter("qwedasdasd");
        IPassportForThird adapterforwechat = new PassportForWechatAdapter("23sdads");
        IPassportForThird adapterforweibo = new PassportForWeiboAdapter("123123");
        adapterforweibo.loginForAdapter();
    }
}
