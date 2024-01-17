package com.example.demo.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/")
public class RoleController {
    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping("/role")
    public ResponseEntity<?> getAllRole() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Role> roleList = roleService.getAllRole();
        if (!roleList.isEmpty()) {
            map.put("status", 1);
            map.put("Role", roleList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/role/{role_id}")
    public ResponseEntity<?> getCourse(@PathVariable("role_id") Long roleId) {
        Optional<Role> role = roleService.getRole(roleId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (role.isPresent()) {
            map.put("status", 1);
            map.put("data", role);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Role with id" + roleId + "not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(path = "/role")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        roleService.createRole(role);
        map.put("status", 1);
        map.put("message", "Role is Saved Successfully");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/role/{role_id}")
    public ResponseEntity<?> deleteRole(@PathVariable("role_id")Long roleId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isDelete = roleService.deleteRole(roleId);
        if (isDelete) {
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Role with id " + roleId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = "/role/{role_id}")
    public ResponseEntity<?> updateRole(
            @PathVariable("role_id") Long role_id,
            @RequestBody Role updatedRole
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isUpdated = roleService.updateRole(role_id, updatedRole);
        if (isUpdated) {
            map.put("status", 1);
            map.put("message", "Record is updated successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Role with id " + role_id + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
