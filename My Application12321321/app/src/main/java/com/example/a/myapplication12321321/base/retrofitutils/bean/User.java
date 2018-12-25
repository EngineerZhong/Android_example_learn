package com.example.a.myapplication12321321.base.retrofitutils.bean;

/**
 * Time:2018/10/30 - 17:36
 * Author:Zhongwb
 * Description:
 */
public class User {

    private String username;
    private String password;
    private int age;
    private String enjoy;
    private String token;

    public User(String username, String password, int age, String enjoy, String token) {
        super();
        this.username = username;
        this.password = password;
        this.age = age;
        this.enjoy = enjoy;
        this.token = token;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEnjoy() {
        return enjoy;
    }
    public void setEnjoy(String enjoy) {
        this.enjoy = enjoy;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
