package com.example.demo.role;

import com.example.demo.account.Account;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String authority;

    public Role(){

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return authority;
    }

    public void setRoleName(String authority) {
        this.authority = authority;
    }

    public Role(Long roleId , String authority){
        this.roleId=roleId;
        this.authority=authority;
    }
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<Account> accounts;


    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
