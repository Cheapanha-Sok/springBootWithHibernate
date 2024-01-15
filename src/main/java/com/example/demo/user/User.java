package com.example.demo.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Integer role;
    public User(){

    }
    public User(Long userId , String userName ,String password, Integer role){
        this.password=password;
        this.userId=userId;
        this.userName=userName;
        this.role=role;
    }
    public User( String userName , String password,Integer role){
        this.userName=userName;
        this.password= password;
        this.role=role;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
    @Override
    public String toString(){
        return "User{"+
                " User Id=" + userId+
                ", User Name" + userName+ '\''+
                ", User Password" + password+ '\''+
                ", User Role" + role+
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
