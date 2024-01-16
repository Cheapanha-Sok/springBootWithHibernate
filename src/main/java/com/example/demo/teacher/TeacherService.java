package com.example.demo.teacher;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.student.Student;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    @Transactional
    public boolean deleteTeacher(Long teacher_id) {
        boolean isExist = teacherRepository.existsById(teacher_id);
        if (isExist){
            teacherRepository.deleteById(teacher_id);
            return true;
        }else{
            return false;
        }

    }
    @Transactional
    public boolean updateTeacher(Long teacherId, Teacher updateTeacher) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()){
            Teacher existingTeacher = teacher.get();
            if (updateTeacher.getTeacherName() != null && !updateTeacher.getTeacherName().isEmpty()){
                existingTeacher.setTeacherName(updateTeacher.getTeacherName());
            }
            if (updateTeacher.getGender() != null && !updateTeacher.getGender().isEmpty()){
                existingTeacher.setGender(updateTeacher.getGender());
            }
            if (updateTeacher.getDob()!= null){
                existingTeacher.setDob(updateTeacher.getDob());
            }
            if (updateTeacher.getAddress() != null && !updateTeacher.getAddress().isEmpty()){
                existingTeacher.setAddress(updateTeacher.getAddress());
            }
            if (updateTeacher.getPhoneNumber() !=null && !updateTeacher.getPhoneNumber().isEmpty()){
                updateTeacher.setPhoneNumber(updateTeacher.getPhoneNumber());
            }
            teacherRepository.save(existingTeacher);
            return true;
        }
        return false;
    }
    @Transactional
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
