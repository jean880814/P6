package com.study.p6_6.p6_6_1_2;

public class Person {
    private Integer id;
    private String username;
    private String password;

    public static int calc(int a, int b){
        a = 1;
        int c = a + b;
        Person person = new Person();
        person.setId(c);
        return c;
    }

    public static void main(String[] args) {
        calc(1 , 2);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
