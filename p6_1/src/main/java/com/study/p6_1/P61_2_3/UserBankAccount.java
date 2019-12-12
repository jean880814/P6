package com.study.p6_1.P61_2_3;

import java.io.*;

public class UserBankAccount extends Bank implements Cloneable, Serializable {
    private String username;
    private UserAccountDetail userAccountDetail;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    private UserBankAccount deepClone() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            UserBankAccount obj = (UserBankAccount) objectInputStream.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserBankAccount lowClone(UserBankAccount account) {
        UserBankAccount newAccount = new UserBankAccount();
        newAccount.setUsername(account.getUsername());
        newAccount.setUserAccountDetail(account.getUserAccountDetail());
        return newAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAccountDetail getUserAccountDetail() {
        return userAccountDetail;
    }

    public void setUserAccountDetail(UserAccountDetail userAccountDetail) {
        this.userAccountDetail = userAccountDetail;
    }
}
