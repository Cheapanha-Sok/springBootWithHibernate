package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/course/")
@CrossOrigin("*")
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
    public ResponseEntity<Course> getCourse(@PathVariable("course_id") Long courseId) {
        return courseService.getCourse(courseId);
    }

    @PostMapping(path = "{department_id}")
    public ResponseEntity<HttpStatus> createCourse(@RequestBody Course course, @PathVariable("department_id") Long departmentId) {
        return courseService.createCourse(course, departmentId);
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long courseId) {
        return courseService.removeCourse(courseId);
    }

    @PutMapping("{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(courseId, updatedCourse);
    }
}