package com.example.demo.role;

import com.example.demo.account.Account;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    public Role(){

    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role(Long roleId , String roleName){
        this.roleId=roleId;
        this.roleName=roleName;
    }
    public Role(String roleName){
        this.roleName = roleName;
    }
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<Account> accounts;
    @Override
    public String toString() {
        return "Roles {" +
                " Role Id=" + roleId +
                " ,Role Name =" + roleName+
                '}';
    }

}
