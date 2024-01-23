package com.example.demo.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Iterable<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping(path = "{faculty_id}")
    public ResponseEntity<?> getFaculty(@PathVariable("faculty_id") Long facultyId) {
        Optional<Faculty> faculty = facultyService.getFaculty(facultyId);
        if (faculty.isPresent()) {
            return ResponseEntity.ok(faculty.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createFaculty(@RequestBody Faculty faculty) {
        facultyService.createFaculty(faculty);
        return ResponseEntity.created(URI.create("/api/v1/faculty/" + faculty.getFacultyId())).build();
    }

    @DeleteMapping(path = "{faculty_id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable("faculty_id") Long facultyId) {
        boolean isDeleted = facultyService.deleteFaculty(facultyId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(path = "{faculty_id}")
    public ResponseEntity<?> updateFaculty(@PathVariable("faculty_id") Long facultyId, @RequestBody Faculty faculty) {
        boolean isUpdated = facultyService.updateFaculty(facultyId, faculty);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
