package com.example.demo.account;

import com.example.demo.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "Account" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "username")
    private String userName;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "password")
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


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }


    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
