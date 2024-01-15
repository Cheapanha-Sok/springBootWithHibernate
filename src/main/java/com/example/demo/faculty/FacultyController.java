package com.example.demo.faculty;

import com.example.demo.department.Department;
import com.example.demo.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/faculty")
public class FacultyController {
    private final FacultyService facultyService;
    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping
    public List<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }
    @GetMapping(path = "{faculty_id}")
    public Optional<Faculty> getFaculty(@PathVariable("faculty_id")Long facultyId){
        return facultyService.getFaculty(facultyId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFaculty(@RequestBody Faculty faculty){
        facultyService.createFaculty(faculty);
    }
    @DeleteMapping(path = "{faculty_id}")
    public void deleteFaculty(@PathVariable("faculty_id") Long facultyId){
        facultyService.deleteFaculty(facultyId);
    }
    @PutMapping(path = "{faculty_id}")
    public void updateFaculty(
            @PathVariable("faculty_id") Long facultyId,
            @PathVariable(required = false) String facultyName,
            @PathVariable(required = false) String deanName,
            @PathVariable(required = false) Integer officeNumber

    ){
        facultyService.updateFaculty(facultyId,facultyName,deanName,officeNumber);
    }
}
