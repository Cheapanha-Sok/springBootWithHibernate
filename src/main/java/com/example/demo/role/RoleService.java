package com.example.demo.role;

import com.example.demo.customsException.NotFoundHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public ResponseEntity<Iterable<Role>> getAllRole(){
        return ResponseEntity.ok(roleRepository.findAll());
    }
    public ResponseEntity<Optional<Role> >getRole(Long roleId){
        Optional<Role>role = roleRepository.findById(roleId);
        if (role.isPresent()){
            return ResponseEntity.ok(role);
        }
        throw new NotFoundHandler("Role with id=" + roleId + " not found");

    }
    public ResponseEntity<URI> createRole(Role role) {
        roleRepository.save(role);
        System.out.println(role);
        return ResponseEntity.created(URI.create("/api/v1/role/" + role.getRoleId())).build();
    }
    public ResponseEntity<HttpStatus> deleteRole(Long roleId){
        boolean isExist = roleRepository.existsById(roleId);
        if (isExist){
            roleRepository.deleteById(roleId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundHandler("Role with id=" + roleId + " not found");
    }
    public ResponseEntity<Role> updateRole(Long roleId, Role updatedRole) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            Role existingRole = roleOptional.get();
            if (updatedRole.getRoleName() != null && !updatedRole.getRoleName().isEmpty()) {
                existingRole.setRoleName(updatedRole.getRoleName());
            }
            roleRepository.save(existingRole);
            return ResponseEntity.ok(existingRole);
        }
        throw new NotFoundHandler("Role with id=" + roleId + " not found");
    }
}
