package com.example.demo.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> getAllFaculty() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Faculty> faculties = facultyService.getAllFaculty();
        if (!faculties.isEmpty()) {
            map.put("status", 1);
            map.put("Faculty", faculties);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{faculty_id}")
    public ResponseEntity<?> getFaculty(@PathVariable("faculty_id") Long facultyId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<Faculty> faculty = facultyService.getFaculty(facultyId);
        if (faculty.isPresent()) {
            map.put("status", 1);
            map.put("data", faculty);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Faculty with id" + facultyId + "not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        facultyService.createFaculty(faculty);
        map.put("status", 1);
        map.put("message", "Faculty is Saved Successfully");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{faculty_id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable("faculty_id") Long facultyId) {
        boolean isDeleted = facultyService.deleteFaculty(facultyId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (isDeleted) {
            map.put("status", 1);
            map.put("message", "Faculty is remove successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Faculty with id " + facultyId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = "{faculty_id}")
    public ResponseEntity<?> updateFaculty(@PathVariable("faculty_id") Long facultyId, @RequestBody Faculty faculty) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isUpdated = facultyService.updateFaculty(facultyId, faculty);
        if (isUpdated) {
            map.put("status", 1);
            map.put("message", "Faculty is updated successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Faculty with id " + facultyId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
