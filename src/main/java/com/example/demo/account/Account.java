package com.example.demo.account;

import com.example.demo.role.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "Account" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id",nullable = false)
    private Long accountId;
    @Column(name = "username",nullable = false , length = 20)
    private String userName;
    @Column(name = "email" , nullable = false , length = 50)
    private String email;
    @Column(name = "password",nullable = false , length = 60)
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


    public void setUserName(String userName) {
        this.userName = userName;
    }



    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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
