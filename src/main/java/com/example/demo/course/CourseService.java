package com.example.demo.course;

import com.example.demo.department.Department;
import com.example.demo.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourse(Long course_id) {
        return courseRepository.findById(course_id);
    }

    public boolean createCourse(Course course , Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            course.setDepartments(List.of(department.get()));
            courseRepository.save(course);
            return true;
        }
        return false;
    }
    public boolean deleteCourse(Long courseId) {
        boolean isExist = courseRepository.existsById(courseId);
        if (isExist) {
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    public boolean updateCourse(Long courseId, Course updatedCourse) {
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
            return true;
        }
        return false;
    }
}
