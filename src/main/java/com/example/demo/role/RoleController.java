package com.example.demo.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/role/")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Role>> getAllRole() {
        return roleService.getAllRole();
    }
    @GetMapping("{role_id}")
    public ResponseEntity<Optional<Role>> getRole(@PathVariable("role_id") Long roleId) {
        return roleService.getRole(roleId);
    }
    @PostMapping
    public ResponseEntity<URI> createRole(@RequestBody Role role){
        return roleService.createRole(role);

    }
    @DeleteMapping(path = "{role_id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("role_id")Long roleId) {
        return roleService.deleteRole(roleId);
    }
    @PutMapping(path = "{role_id}")
    public ResponseEntity<Role> updateRole(
            @PathVariable("role_id") Long role_id,
            @RequestBody Role updatedRole
    ) {
        return roleService.updateRole(role_id , updatedRole);
    }
}
