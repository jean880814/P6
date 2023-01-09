package com.study.p6_1.chain_pattern;

public class ChainTest {
    public static void main(String[] args) {
        Handler.Builder builder = new Handler.Builder();
        builder.add(new ValidHandler()).add(new LoginHandler()).add(new AuthHandler());
        User user = new User();
        user.setUserName("jean");
        user.setPassWord("1233");
        builder.build().process(user);
    }
}
