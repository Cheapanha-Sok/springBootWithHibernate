package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.response.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public ResponseEntity<Course> getCourse(Long course_id) {
        Optional<Course> course = courseRepository.findById(course_id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        }
        throw new NotFoundException("Course with id=" + course_id + " not found");
    }

    public ResponseEntity<HttpStatus> createCourse(Course course, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            course.setDepartments(List.of(department.get()));
            courseRepository.save(course);
            return ResponseEntity.ok().build();
        }
        throw new NotFoundException("Department with id=" + departmentId + " not found");
    }

    public ResponseEntity<HttpStatus> removeCourse(Long courseId) {
        boolean isExist = courseRepository.existsById(courseId);
        if (isExist) {
            courseRepository.deleteById(courseId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Course with id=" + courseId + " not found");
    }

    public ResponseEntity<Course> updateCourse(Long courseId, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();
            if (updatedCourse.getCourseName() != null && !updatedCourse.getCourseName().isEmpty()) {
                existingCourse.setCourseName(updatedCourse.getCourseName());
            }
            if (updatedCourse.getCredit() != null) {
                existingCourse.setCredit(updatedCourse.getCredit());
            }
            if (updatedCourse.getType() != null && !updatedCourse.getType().isEmpty()) {
                existingCourse.setType(updatedCourse.getType());
            }
            courseRepository.save(existingCourse);
            return ResponseEntity.ok(existingCourse);
        }
        throw new NotFoundException("Course with id=" + courseId + " not found");
    }
}
