package com.example.demo.accountTeacher;

import jakarta.persistence.*;

@Entity
@Table(name = "accountTeacher")
public class AccountTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;

    public AccountTeacher(){

    }
    public AccountTeacher(Long accountId , String userName , String password){
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
    }
    public AccountTeacher(String userName , String password){
        this.userName = userName;
        this.password =password;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
