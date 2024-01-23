package com.example.demo.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/role/")
public class RoleController {
    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Role>> getAllRole() {
        return ResponseEntity.ok(roleService.getAllRole());
    }
    @GetMapping("{role_id}")
    public ResponseEntity<?> getCourse(@PathVariable("role_id") Long roleId) {
        Optional<Role> role = roleService.getRole(roleId);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role){
        roleService.createRole(role);
        return ResponseEntity.created(URI.create("/api/v1/role/" + role.getRoleId())).build();
    }
    @DeleteMapping(path = "{role_id}")
    public ResponseEntity<?> deleteRole(@PathVariable("role_id")Long roleId) {
        boolean isDelete = roleService.deleteRole(roleId);
        if (isDelete) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(path = "{role_id}")
    public ResponseEntity<?> updateRole(
            @PathVariable("role_id") Long role_id,
            @RequestBody Role updatedRole
    ) {
        boolean isUpdated = roleService.updateRole(role_id, updatedRole);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
