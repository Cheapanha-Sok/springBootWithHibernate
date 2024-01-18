package com.example.demo.course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCourse() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Course> course = courseService.getAllCourse();
        if (!course.isEmpty()) {
            map.put("status", 1);
            map.put("Course", course);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{course_id}")
    public ResponseEntity<?> getCourse(@PathVariable("course_id") Long courseId) {
        Optional<Course> course = courseService.getCourse(courseId);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (!course.isEmpty()) {
            map.put("status", 1);
            map.put("data", course);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Course with id" + courseId + "not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "{department_id}")
    public ResponseEntity<?> createCourse(@RequestBody Course course , @PathVariable("department_id")Long departmentId) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isCreated = courseService.createCourse(course , departmentId);
        if (isCreated){
            map.put("status", 1);
            map.put("message", "Course is Saved Successfully");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
        map.put("status", 0);
        map.put("message", "department with id " + departmentId + " not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    }

    @DeleteMapping(path = "{course_id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long course_id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isDelete = courseService.deleteCourse(course_id);
        if (isDelete) {
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Course with id " + course_id + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping(path = "{course_id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable("course_id") Long courseId,
            @RequestBody Course updatedCourse
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean isUpdated = courseService.updateCourse(courseId, updatedCourse);
        if (isUpdated) {
            map.put("status", 1);
            map.put("message", "Record is updated successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Course with id " + courseId + " not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}