package com.mist.cloudtestingplatform.model;




/**
 * Created by Prophet on 2016/12/20.
 */
public class User extends BaseModel{

    public static String ADMIN = "admin";
    public static String USER = "user";

    private int id;
    private String username;
    private String password;
    private String email;
    private String role;



    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
