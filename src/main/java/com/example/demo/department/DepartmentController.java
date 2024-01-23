package com.example.demo.department;

import com.example.demo.faculty.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Department>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping(path = "{department_id}")
    public ResponseEntity<?> getDepartment(@PathVariable("department_id") Long departmentId) {
        Optional<Department> department = departmentService.getDepartment(departmentId);
        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping(path = "{faculty_id}")
    public ResponseEntity<?> createDepartment(@RequestBody Department department, @PathVariable("faculty_id") Long facultyId) {
        if (departmentService.addNewDepartment(department, facultyId)) {
            return ResponseEntity.created(URI.create("/api/v1/department/" + department.getDepartmentId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "{department_id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("department_id") Long departmentId) {
        boolean isDeleted = departmentService.deleteDepartment(departmentId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(path = "{department_id}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable Long department_id,
            @RequestBody Department department
    ) {
        boolean isUpdated = departmentService.updateDepartment(department_id, department);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
