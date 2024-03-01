package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/department/")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Department>> getAllDepartment() {
        return departmentService.getAllDepartment();
    }
    @GetMapping(path = "{department_id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("department_id") Long departmentId) {
        return departmentService.getDepartment(departmentId);
    }
    @PostMapping(path = "{faculty_id}")
    public ResponseEntity<URI> createDepartment(@RequestBody Department department, @PathVariable("faculty_id") Long facultyId) {
        return departmentService.addNewDepartment(department , facultyId);
    }

    @DeleteMapping(path = "{department_id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("department_id") Long departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }
    @PutMapping(path = "{department_id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long department_id,
            @RequestBody Department department
    ) {
        return departmentService.updateDepartment(department_id , department);
    }

}
