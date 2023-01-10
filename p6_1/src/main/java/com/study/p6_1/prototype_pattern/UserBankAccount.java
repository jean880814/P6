package com.study.p6_1.prototype_pattern;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.*;

@Data
public class UserBankAccount extends Bank implements Cloneable, Serializable {
    private String username;
    private UserAccountDetail userAccountDetail;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    public UserBankAccount jsonClone(){
        return JSONObject.parseObject(JSONObject.toJSONString(this), UserBankAccount.class);
    }

    public UserBankAccount deepClone()  {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);

            UserBankAccount obj = (UserBankAccount) objectInputStream.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UserBankAccount lowClone(UserBankAccount account) {
        UserBankAccount newAccount = new UserBankAccount();
        newAccount.setUsername(account.getUsername());
        newAccount.setUserAccountDetail(account.getUserAccountDetail());
        return newAccount;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        UserBankAccount account = new UserBankAccount();
        UserAccountDetail userAccountDetail = new UserAccountDetail();
        userAccountDetail.setAccount("aaa");
        userAccountDetail.setType("1");
        account.setUsername("Jean");
        account.setUserAccountDetail(userAccountDetail);

        UserBankAccount lowClone = account.jsonClone();
        lowClone.getUserAccountDetail().setAccount("bbb");

        System.out.println(account == lowClone);
        System.out.println("account.getuserAccountDetail" + account.getUserAccountDetail());
        System.out.println("lowClone.getuserAccountDetail" + lowClone.getUserAccountDetail());
    }

}
