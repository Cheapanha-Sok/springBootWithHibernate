package com.example.demo.auth;

public class FormLogin {
    private String email;
    private String password;

    public FormLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String username) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
