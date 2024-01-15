package com.example.demo.teacher;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll() ;
    }
    public Optional<Teacher> getTeacher(Long teacherId){
        return teacherRepository.findById(teacherId);
    }

    public void createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long teacher_id) {
        boolean isExist = teacherRepository.existsById(teacher_id);
        if (!isExist){
            throw new IllegalStateException("Teacher with id " + teacher_id + " not found" );
        }
        teacherRepository.deleteById(teacher_id);
    }

    public void updateTeacher(Long teacherId, String teacherName, String gender, LocalDate dob, String address, String phoneNumber) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("Teacher with id"+ teacherId+"not founded"));
        if (teacherName != null && !teacherName.isEmpty()){
            teacher.setTeacherName(teacherName);
        }
        if (gender != null && !gender.isEmpty()){
            teacher.setGender(gender);
        }
        if (dob!= null){
            teacher.setDob(dob);
        }
        if (address != null && !address.isEmpty()){
            teacher.setAddress(address);
        }
        if (phoneNumber !=null && !phoneNumber.isEmpty()){
            teacher.setPhoneNumber(phoneNumber);
        }
        teacherRepository.save(teacher);
    }

    public void assignCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()->
                new IllegalStateException("Teacher with id "+ teacherId + " not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new IllegalStateException("Course with id " + courseId + " not found"));
        if (teacher.getCourses().contains(courseId)) {
            throw new IllegalStateException("Teacher with id " + teacherId + " is already Assign with id " + courseId);
        }
        teacher.setCourse(course);
        teacherRepository.save(teacher);
    }
}
