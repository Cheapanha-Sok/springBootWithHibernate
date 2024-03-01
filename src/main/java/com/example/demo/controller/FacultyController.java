package com.example.demo.controller;

import com.example.demo.model.Faculty;
import com.example.demo.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/faculty/")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Faculty>> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @GetMapping(path = "{faculty_id}")
    public ResponseEntity<Optional<Faculty>> getFaculty(@PathVariable("faculty_id") Long facultyId) {
        return facultyService.getFaculty(facultyId);
    }

    @PostMapping
    public ResponseEntity<URI> createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }
    @DeleteMapping(path = "{faculty_id}")
    public ResponseEntity<HttpStatus> deleteFaculty(@PathVariable("faculty_id") Long facultyId) {
        return facultyService.deleteFaculty(facultyId);
    }
    @PutMapping(path = "{faculty_id}")
    public ResponseEntity<HttpStatus> updateFaculty(@PathVariable("faculty_id") Long facultyId, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(facultyId , faculty);
    }
}
