package com.example.demo.account;

import com.example.demo.course.Course;
import com.example.demo.role.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Account" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id",nullable = false)
    private Long accountId;
    @Column(name = "username",nullable = false)
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "password",nullable = false)
    private String password;

    public Account(){

    }

    public Account(Long accountId , String userName , String password , String email ){
        this.accountId = accountId;
        this.userName = userName;
        this.email = email;
        this.password = password;

    }
    public Account(String userName , String password , String email ){
        this.userName = userName;
        this.email = email;
        this.password = password;

    }
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns =@JoinColumn (name = "role_id"))
    private List<Role> roles;

    @Override
    public String toString() {
        return "Account{" +
                " Account Id=" + accountId +
                ", User Name=" + userName + '\'' +
                ", Email=" + email + '\'' +
                ", Password=" + password +
                '}';
    }
}
