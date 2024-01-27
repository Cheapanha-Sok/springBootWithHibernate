package com.example.demo.auth;

import com.example.demo.account.Account;

public class LoginResponseDTO {
    private Account account;
    private String accessToken;

    public LoginResponseDTO(){

    }
     LoginResponseDTO(Account account , String accessToken){
        this.account= account;
        this.accessToken = accessToken;
    }
    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public String getAccessToken(){
        return accessToken;
    }
    public void setAccessToken(String jwt){
        this.accessToken = accessToken;
    }
}
