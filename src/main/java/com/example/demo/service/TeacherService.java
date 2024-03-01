package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.response.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    public ResponseEntity<Iterable<Teacher>> getAllTeacher() {
        return ResponseEntity.ok(teacherRepository.findAll()) ;
    }
    public ResponseEntity<Optional<Teacher> >getTeacher(Long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()){
            return ResponseEntity.ok(teacher);
        }
        throw new NotFoundException("Teacher with id" + teacherId + " not found");
    }

    public ResponseEntity<URI> createTeacher(Teacher teacher , Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()){
            teacher.setCourses(List.of(course.get()));
            teacherRepository.save(teacher);
            return ResponseEntity.created(URI.create("/api/v1/teacher/" + teacher.getTeacherId())).build();
        }
        throw new NotFoundException("Course with id" + courseId + " not found");

    }

    public ResponseEntity<HttpStatus> deleteTeacher(Long teacher_id) {
        boolean isExist = teacherRepository.existsById(teacher_id);
        if (isExist){
            teacherRepository.deleteById(teacher_id);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Teacher with id" + teacher_id + " not found");

    }

    public ResponseEntity<Teacher> updateTeacher(Long teacherId, Teacher updateTeacher) {
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
            return ResponseEntity.ok(existingTeacher);
        }
        throw new NotFoundException("Teacher with id" + teacherId + " not found");
    }
}
