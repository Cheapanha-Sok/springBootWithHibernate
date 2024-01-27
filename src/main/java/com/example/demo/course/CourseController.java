package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1/course/")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Course>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("{course_id}")
    public ResponseEntity<?> getCourse(@PathVariable("course_id") Long courseId) {
        Optional<Course> course = courseService.getCourse(courseId);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "{department_id}")
    public ResponseEntity<?> createCourse(@RequestBody Course course, @PathVariable("department_id") Long departmentId) {
        boolean isCreated = courseService.createCourse(course, departmentId);
        if (isCreated) {
            return ResponseEntity.created(URI.create("/api/v1/course/" + course.getCourseId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        boolean isDeleted = courseService.deleteCourse(courseId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        boolean isUpdated = courseService.updateCourse(courseId, updatedCourse);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }