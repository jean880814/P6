package com.study.p6_1.prototype_pattern;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAccountDetail implements Serializable {
    private String account;
    private String type;


    @Override
    public String toString() {
        return "UserAccountDetail{" +
                "account='" + account + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
