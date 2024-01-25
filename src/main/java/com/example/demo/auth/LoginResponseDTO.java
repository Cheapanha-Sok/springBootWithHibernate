package com.example.demo.auth;

import com.example.demo.account.Account;

public class LoginResponseDTO {
    private Account account;
    private String jwt;

    public LoginResponseDTO(){

    }
    public LoginResponseDTO(Account account , String jwt){
        this.account= account;
        this.jwt = jwt;
    }
    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public String getJwt(){
        return jwt;
    }
    public void setJwt(String jwt){
        this.jwt = jwt;
    }
}
