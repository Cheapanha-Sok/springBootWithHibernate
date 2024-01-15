package com.example.demo.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }
    @GetMapping(path = "{department_id}")
    public Optional<Department> getDepartment(@PathVariable("department_id") Long departmentId) {
        return departmentService.getDepartment(departmentId);
    }
    @PostMapping(path = "{faculty_id}")
    public void createDepartment(@RequestBody Department department, @PathVariable("faculty_id") Long facultyId) {
        departmentService.addNewDepartment(department, facultyId);
    }
    @DeleteMapping(path = "{department_id}")
    public void deleteDepartment(@PathVariable("department_id") Long departmentId)
    {
        departmentService.deleteDepartment(departmentId);

    }
    @PutMapping(path = "{department_id}")
    public void updateDepartment(
            @PathVariable Long department_id,
            @RequestParam(required = false) String department_name,
            @RequestParam (required = false) String headName,
            @RequestParam(required = false) Integer office_number
    ){
        departmentService.updateDepartment(department_id,department_name,headName,office_number);
    }

}
