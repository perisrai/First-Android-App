package com.example.peris.myapp;

/**
 * Created by Peris on 3/1/2018.
 */

public class Student {
    private String name;
    private String username;
    private String password;
    private String confirm_password;
    private String email;
    private String contact;

    public Student(String name, String username, String password, String confirm_password, String
                   email, String contact){
    this.name= name;
    this.username= username;
    this.password= password;
    this.confirm_password= password;
    this.email= email;
    this.contact=contact;
    }

    public Student(String name, String email, String contact) {
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}