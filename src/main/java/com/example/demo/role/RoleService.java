package com.example.demo.role;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Iterable<Role> getAllRole(){
        return roleRepository.findAll();
    }
    public Optional<Role> getRole(Long roleId){
        return roleRepository.findById(roleId);
    }
    public void createRole(Role role) {
        roleRepository.save(role);
    }
    public boolean deleteRole(Long roleId){
        boolean isExist = roleRepository.existsById(roleId);
        if (isExist){
            roleRepository.deleteById(roleId);
            return true;
        }
        return false;
    }
    public boolean updateRole(Long courseId, Role updatedRole) {
        Optional<Role> roleOptional = roleRepository.findById(courseId);
        if (roleOptional.isPresent()) {
            Role existingRole = roleOptional.get();
            if (updatedRole.getRoleName() != null && !updatedRole.getRoleName().isEmpty()) {
                existingRole.setRoleName(updatedRole.getRoleName());
            }
            roleRepository.save(existingRole);
            return true;
        }
        return false;
    }
}
