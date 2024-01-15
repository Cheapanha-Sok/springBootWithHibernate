package com.example.demo.department;

import com.example.demo.faculty.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllDepartment() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Department> departmentList = departmentService.getAllDepartment();
        if (!departmentList.isEmpty()) {
            map.put("status", 1);
            map.put("Department", departmentList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{department_id}")
    public ResponseEntity<?> getDepartment(@PathVariable("department_id") Long departmentId) {
        Optional<Department> department = departmentService.getDepartment(departmentId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (department.isPresent()) {
            map.put("status", 1);
            map.put("data", department);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Department with id " + departmentId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "{faculty_id}")
    public ResponseEntity<?> createDepartment(@RequestBody Department department, @PathVariable("faculty_id") Long facultyId) {
        boolean isAdded = departmentService.addNewDepartment(department, facultyId);
        Map<String, Object> map = new LinkedHashMap<>();
        if (isAdded) {
            map.put("status", 1);
            map.put("message", "Department is created successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Faculty with id " + facultyId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{department_id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("department_id") Long departmentId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isDelete = departmentService.deleteDepartment(departmentId);
        if (isDelete) {
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Department with id " + departmentId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(path = "{department_id}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable Long department_id,
            @RequestBody Department department
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isEdited = departmentService.updateDepartment(department_id, department);
        if (isEdited) {
            map.put("status", 1);
            map.put("message", "Record is updated successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Department with id " + department_id + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
