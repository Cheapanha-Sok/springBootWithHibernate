package com.example.demo.account;

import com.example.demo.course.Course;
import com.example.demo.role.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    public Account(){

    }

    public Account(Long accountId , String userName , String password , String phoneNumber ){
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public Account( String userName , String password , String phoneNumber ){
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable(name = "role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns =@JoinColumn (name = "role_id"))
    private List<Role> role;



}
