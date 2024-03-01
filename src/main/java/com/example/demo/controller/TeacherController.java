package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/teacher")
public class  TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Teacher>>getAllTeacher(){
        return teacherService.getAllTeacher();
    }
    @GetMapping(path = "{teacher_id}")
    public ResponseEntity<Optional<Teacher>> getTeacher(@PathVariable("teacher_id") Long teacherId){
        return teacherService.getTeacher(teacherId);
    }
    @PostMapping("{course_id}")
    public ResponseEntity<URI> createTeacher(@RequestBody Teacher teacher , @PathVariable("course_id") Long courseId){
        return teacherService.createTeacher(teacher , courseId);
    }
    @DeleteMapping(path = "{teacher_id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long teacher_id){
        return teacherService.deleteTeacher(teacher_id);
    }
    @PutMapping(path = "{teacher_id}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable ("teacher_id")Long teacherId,
            @RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacherId , teacher);

    }
}
